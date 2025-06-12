package com.nep.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.reflect.TypeToken;
import com.nep.controller.NepsFeedbackViewController;
import com.nep.controller.NepsSelectAqiViewController;
import com.nep.po.Supervisor;
import com.nep.service.SupervisorService;
import com.nep.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SupervisorServiceImpl implements SupervisorService {

    private static final Logger logger = LoggerFactory.getLogger(SupervisorService.class);
    private static final String JSON_DATA_DIR = "src/main/resources/NepDatas/JSONData/";
    private static final String SUPERVISOR_FILE = "supervisor.json";

    @Override
    public boolean login(String loginCode,String password) {
        String ProPaht = System.getProperty("user.dir") + "/src/main/resources/NepDatas/JSONData/";
        System.out.println("要读取的文件路径: " + ProPaht + "supervisor.json");
        List<Supervisor> slist = null;
        try {
            slist = JsonUtil.readListfromJson(
                    "NepDatas/JSONData/supervisor.json",
                    new TypeReference<List<Supervisor>>() {}
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("读取到的用户数据: " + slist);
        boolean isLogin = false;
        for(Supervisor s:slist){
            if(s.getTelId().equals(loginCode) && s.getPassword().equals(password)){
                NepsSelectAqiViewController.supervisor = s;
                NepsFeedbackViewController.supervisor = s;
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean register(Supervisor supervisor) throws IOException {
        if (supervisor == null) {
            logger.error("注册失败：传入的Supervisor对象为null");
            return false;
        }

        // 构建完整文件路径
        Path baseDir = Paths.get(System.getProperty("user.dir"), JSON_DATA_DIR);
        Path filePath = baseDir.resolve(SUPERVISOR_FILE);
        Path tempFilePath = filePath.resolveSibling(filePath.getFileName() + ".tmp");

        // 确保目录存在
        if (!Files.exists(baseDir)) {
            try {
                Files.createDirectories(baseDir);
                logger.info("创建数据目录成功: {}", baseDir);
            } catch (IOException e) {
                logger.error("无法创建数据目录: {}", baseDir, e);
                return false;
            }
        }

        // 读取现有数据
        List<Supervisor> supervisorList;
        try {
            supervisorList = JsonUtil.readListfromJson(
                    filePath.toString(),
                    new TypeReference<List<Supervisor>>() {}
            );
            logger.info("成功读取现有用户数据，共 {} 条记录", supervisorList.size());
        } catch (Exception e) { // 捕获所有异常
            // 如果文件不存在或读取失败，创建新列表
            if (Files.notExists(filePath)) {
                logger.info("用户数据文件不存在，将创建新文件");
                supervisorList = new ArrayList<>();
            } else {
                logger.error("读取用户数据失败: {}", filePath, e);
                throw new IOException("读取用户数据失败", e);
            }
        }

        // 检查TelId是否已存在
        Set<String> existingTelIds = supervisorList.stream()
                .map(Supervisor::getTelId)
                .collect(Collectors.toCollection(HashSet::new));

        if (existingTelIds.contains(supervisor.getTelId())) {
            logger.warn("注册失败：TelId已存在 - {}", supervisor.getTelId());
            return false;
        }

        // 创建新列表并添加新用户
        List<Supervisor> updatedList = new ArrayList<>(supervisorList);
        updatedList.add(supervisor);
        logger.info("准备添加新用户: {}", supervisor.getTelId());

        // 使用临时文件确保写入原子性
        try {
            JsonUtil.writeListToJson(updatedList, tempFilePath.toString());
            logger.debug("临时文件写入成功: {}", tempFilePath);

            Files.move(tempFilePath, filePath, StandardCopyOption.REPLACE_EXISTING);
            logger.info("用户注册成功: {}", supervisor.getTelId());
            return true;
        } catch (IOException e) {
            try {
                Files.deleteIfExists(tempFilePath);
            } catch (IOException cleanupEx) {
                logger.error("清理临时文件失败: {}", tempFilePath, cleanupEx);
            }

            logger.error("保存用户数据失败: {}", filePath, e);
            throw new IOException("保存用户数据失败", e);
        }
    }
}

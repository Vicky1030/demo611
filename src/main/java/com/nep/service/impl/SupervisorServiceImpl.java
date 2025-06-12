package com.nep.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nep.controller.NepsFeedbackViewController;
import com.nep.controller.NepsSelectAqiViewController;
import com.nep.po.Supervisor;
import com.nep.service.SupervisorService;
import com.nep.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class SupervisorServiceImpl implements SupervisorService {

    private static final Logger logger = LoggerFactory.getLogger(SupervisorServiceImpl.class);

    private static final Path SUPERVISOR_JSON_PATH = Paths.get(
            System.getProperty("user.dir"), "NepDatas", "JSONData", "supervisor.json");

    @Override
    public boolean login(String loginCode, String password) {
        System.out.println("要读取的文件路径: " + SUPERVISOR_JSON_PATH);

        List<Supervisor> slist;
        try {
            if (!Files.exists(SUPERVISOR_JSON_PATH)) {
                logger.warn("用户数据文件不存在: {}", SUPERVISOR_JSON_PATH);
                return false;
            }

            slist = JsonUtil.readListFromFileSystem(
                    SUPERVISOR_JSON_PATH.toString(),
                    new TypeReference<List<Supervisor>>() {});
        } catch (IOException e) {
            throw new RuntimeException("读取 supervisor.json 文件失败", e);
        }

        System.out.println("读取到的用户数据: " + slist);

        for (Supervisor s : slist) {
            if (s.getTelId().equals(loginCode) && s.getPassword().equals(password)) {
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
            logger.error("注册失败：传入的 Supervisor 对象为 null");
            return false;
        }

        // 确保目录存在
        Path parentDir = SUPERVISOR_JSON_PATH.getParent();
        if (!Files.exists(parentDir)) {
            Files.createDirectories(parentDir);
            logger.info("创建用户数据目录: {}", parentDir);
        }

        List<Supervisor> supervisorList;

        // 尝试读取现有数据
        if (Files.exists(SUPERVISOR_JSON_PATH)) {
            try {
                supervisorList = JsonUtil.readListFromFileSystem(
                        SUPERVISOR_JSON_PATH.toString(),
                        new TypeReference<List<Supervisor>>() {});
                logger.info("读取用户数据成功，共 {} 条", supervisorList.size());
            } catch (IOException e) {
                logger.error("读取用户数据失败", e);
                throw new IOException("读取用户数据失败", e);
            }
        } else {
            supervisorList = new ArrayList<>();
            logger.info("用户数据文件不存在，将创建新列表");
        }

        // 重复性校验
        boolean exists = supervisorList.stream()
                .anyMatch(s -> s.getTelId().equals(supervisor.getTelId()));

        if (exists) {
            logger.warn("注册失败：TelId 已存在 - {}", supervisor.getTelId());
            return false;
        }

        supervisorList.add(supervisor);
        logger.info("准备注册新用户: {}", supervisor.getTelId());

        // 临时写入
        Path tempFile = SUPERVISOR_JSON_PATH.resolveSibling("supervisor.json.tmp");

        try {
            JsonUtil.writeListToJson(supervisorList, tempFile.toString());
            Files.move(tempFile, SUPERVISOR_JSON_PATH, StandardCopyOption.REPLACE_EXISTING);
            logger.info("用户注册成功: {}", supervisor.getTelId());
            return true;
        } catch (IOException e) {
            Files.deleteIfExists(tempFile);
            logger.error("保存用户数据失败", e);
            throw new IOException("保存用户数据失败", e);
        }
    }
}

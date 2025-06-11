package com.nep.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static <T> List<T> readListFromJson(InputStream inputStream, TypeReference<List<T>> typeReference) throws IOException {
        return objectMapper.readValue(inputStream, typeReference);
    }
    /**
     * 从 classpath 下读取 JSON 文件并反序列化为 List<T>
     */
    public static <T> List<T> readListFromJson(String resourcePath, TypeReference<List<T>> typeRef) {
        try (InputStream inputStream = JsonUtil.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                System.err.println("❌ 资源不存在：" + resourcePath);
                return Collections.emptyList();
            }
            return objectMapper.readValue(inputStream, typeRef);
        } catch (Exception e) {
            System.err.println("❌ 读取失败：" + resourcePath);
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * 将 List<T> 写入指定磁盘路径的 JSON 文件（不是 classpath）
     * 推荐：路径写为 user.dir 开头，或自己指定
     */
    public static <T> void writeListToJson(List<T> list, String filePath) {
        try {
            // 创建父目录（如不存在）
            Files.createDirectories(Paths.get(filePath).getParent());

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), list);
            System.out.println("✅ 写入成功：" + filePath);
        } catch (Exception e) {
            System.err.println("❌ 写入失败：" + filePath);
            e.printStackTrace();
        }
    }
}

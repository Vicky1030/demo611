package com.nep.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.util.*;

public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        // 配置ObjectMapper忽略未知字段
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> List<T> readListfromJson(String resourcePath, TypeReference<List<T>> typeRef) throws IOException {
        // 修正路径格式 - 移除开头的斜杠（如果有）
        if (resourcePath.startsWith("/")) {
            resourcePath = resourcePath.substring(1);
        }

        // 使用类加载器获取资源
        URL resourceUrl = JsonUtil.class.getClassLoader().getResource(resourcePath);
        if (resourceUrl == null) {

            throw new FileNotFoundException("资源不存在: " + resourcePath);
        }

        try (InputStream inputStream = resourceUrl.openStream()) {
            return objectMapper.readValue(inputStream, typeRef);
        }
    }

    /**
     * 从InputStream读取JSON并反序列化为List<T>
     */
    public static <T> List<T> readListFromJson(InputStream inputStream, TypeReference<List<T>> typeRef) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("InputStream不能为null");
        }
        return objectMapper.readValue(inputStream, typeRef);
    }

    /**
     * 从文件系统读取JSON文件并反序列化为List<T>
     */
    public static <T> List<T> readListFromFileSystem(String filePath, TypeReference<List<T>> typeRef) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new FileNotFoundException("文件不存在: " + filePath);
        }
        return objectMapper.readValue(path.toFile(), typeRef);
    }

    /**
     * 将List<T>写入JSON文件（支持原子写入）
     */
    public static <T> void writeListToJson(List<T> list, String filePath) throws IOException {
        // 确保父目录存在
        Path parentDir = Paths.get(filePath).getParent();
        if (parentDir != null && !Files.exists(parentDir)) {
            Files.createDirectories(parentDir);
        }

        // 创建临时文件
        Path tempFilePath = Paths.get(filePath + ".tmp");

        try {
            // 写入临时文件
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(tempFilePath.toFile(), list);

            // 原子性地替换目标文件
            Files.move(tempFilePath, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("✅ 写入成功：" + filePath);
        } catch (Exception e) {
            // 发生错误时删除临时文件
            if (Files.exists(tempFilePath)) {
                Files.delete(tempFilePath);
            }
            System.err.println("❌ 写入失败：" + filePath);
            throw e;
        }
    }

    /**
     * 从类路径读取JSON文件并反序列化为单个对象
     */
    public static <T> T readObjectFromJson(String resourcePath, Class<T> clazz) throws IOException {
        URL resourceUrl = JsonUtil.class.getClassLoader().getResource(resourcePath);
        if (resourceUrl == null) {
            throw new FileNotFoundException("资源不存在: " + resourcePath);
        }

        try (InputStream inputStream = resourceUrl.openStream()) {
            return objectMapper.readValue(inputStream, clazz);
        }
    }

    /**
     * 将对象写入JSON文件
     */
    public static <T> void writeObjectToJson(T object, String filePath) throws IOException {
        // 确保父目录存在
        Path parentDir = Paths.get(filePath).getParent();
        if (parentDir != null && !Files.exists(parentDir)) {
            Files.createDirectories(parentDir);
        }

        // 创建临时文件
        Path tempFilePath = Paths.get(filePath + ".tmp");

        try {
            // 写入临时文件
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(tempFilePath.toFile(), object);

            // 原子性地替换目标文件
            Files.move(tempFilePath, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("✅ 写入成功：" + filePath);
        } catch (Exception e) {
            // 发生错误时删除临时文件
            if (Files.exists(tempFilePath)) {
                Files.delete(tempFilePath);
            }
            System.err.println("❌ 写入失败：" + filePath);
            throw e;
        }
    }

}

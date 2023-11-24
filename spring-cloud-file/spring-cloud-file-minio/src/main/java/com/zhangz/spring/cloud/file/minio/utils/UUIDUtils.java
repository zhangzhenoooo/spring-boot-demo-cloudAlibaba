package com.zhangz.spring.cloud.file.minio.utils;

import java.util.UUID;

public class UUIDUtils {
    public static String randomUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        return str.replace("-", "");
    }
}

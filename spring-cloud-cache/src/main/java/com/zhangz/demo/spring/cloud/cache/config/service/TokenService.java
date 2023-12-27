package com.zhangz.demo.spring.cloud.cache.config.service;

/*
 *@Author：zhangz
 *@Package：com.zhangz.demo.spring.cloud.cache.config.service
 *@Project：spring-cloud
 *@name：TokenService
 *@Date：2023/12/26  10:35
 *@Filename：TokenService
 */

public interface TokenService {
    String cacheToken(String userId);
    String cacheToken(String userId, String wxSessionKey);
    String getUserIdByTokenPhone(String token);
    String getOpenIdByTokenWxSession(String token);

    String getUserLoginTokenCacheKey();
    String getUserLoginTokenCacheKey(String token);

    String getUserWxSessionCacheKey(String session);

    boolean isExpired(String token);

    void refreshWxSession(String session_key,String openid);
    
}

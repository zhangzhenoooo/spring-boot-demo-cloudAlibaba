package com.zhangz.demo.spring.cloud.cache.config.service.impl;

import com.zhangz.demo.spring.cloud.cache.config.service.TokenService;
import com.zhangz.spring.cloud.common.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/*
 * @Author：zhangz
 * 
 * @Package：com.zhangz.demo.spring.cloud.cache.config.service.impl
 * 
 * @Project：spring-cloud
 * 
 * @name：TokenServiceImpl
 * 
 * @Date：2023/12/26 10:36
 * 
 * @Filename：TokenServiceImpl
 */
@Service
@Slf4j
public class TokenServiceImpl implements TokenService {

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public String cacheToken(String userId) {
        log.info("用户【{}】登录生成token...", userId);
        String uuid32 = UUIDUtils.getUUID32();
        String key = getUserLoginTokenCacheKey(uuid32);
        redisTemplate.opsForValue().set(key, userId, 8, TimeUnit.HOURS);
        return uuid32;
    }

    @Override
    public String cacheToken(String userId, String wxSessionKey) {
        String key = getUserWxSessionCacheKey(wxSessionKey);
        redisTemplate.opsForValue().set(key, userId, 8, TimeUnit.HOURS);
        return wxSessionKey;
    }

    @Override
    public String getUserIdByTokenPhone(String token) {
        String key = "userToken:" + token;
        Object o = redisTemplate.opsForValue().get(key);
        if (null == o) {
            return null;
        } else {
            return (String)o;
        }
    }

    @Override
    public String getOpenIdByTokenWxSession(String token) {
        String key = this.getUserWxSessionCacheKey(token);
        Object o = redisTemplate.opsForValue().get(key);
        if (null == o) {
            return null;
        } else {
            return (String)o;
        }
    }

    @Override
    public String getUserLoginTokenCacheKey() {
        String uuid32 = UUIDUtils.getUUID32();
        String key = "userToken:" + uuid32;
        return key;
    }

    @Override
    public String getUserLoginTokenCacheKey(String token) {
        String key = "userToken:" + token;
        return key;
    }

    @Override
    public String getUserWxSessionCacheKey(String session) {
        String key = "userToken:" + session;
        return key;
    }

    @Override
    public boolean isExpired(String token) {
        String userLoginTokenCacheKey = this.getUserLoginTokenCacheKey(token);
        Object o = redisTemplate.opsForValue().get(userLoginTokenCacheKey);
        return null == o;
    }

    @Override
    public void refreshWxSession(String session_key,String userId) {
        String key = this.getUserWxSessionCacheKey(session_key);
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Object o = valueOperations.get(key);
        if (null != o){
            return;
        }
        valueOperations.set(key,userId,8,TimeUnit.HOURS);
    }

}

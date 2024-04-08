package com.example.demo.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@Configuration
@EnableCaching
@Slf4j
@EnableScheduling
public class CacheConfig  {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("tasks");
    }

    @CacheEvict(allEntries = true, value = "tasks")
    @Scheduled(cron = " 0 0/1 * * * ? ")
    public void reportCacheEvict() {
        log.info("Flush cache {} ", new Date());
        cacheManager().getCache("tasks").clear();
    }
}
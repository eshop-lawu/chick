package com.lawu.chick.operator.api;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lawu.chick.cache.service.EggConfigCacheService;
import com.lawu.chick.cache.service.co.EggConfigCache;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EggConfigCacheServiceImplTest {
    
    @Autowired EggConfigCacheService eggConfigCacheService;
    
    @Ignore
    @Test
    public void saveCacheChickBaseInfo() {
        EggConfigCache config = new EggConfigCache();
        config.setLayEggsTotal(new BigDecimal(100));
        config.setMaxLayEggs(new BigDecimal(0.25D));
        eggConfigCacheService.saveCacheEggConfig(config);
    }
    
    @Ignore
    @Test
    public void getCacheChickBaseInfo() {
        EggConfigCache config = eggConfigCacheService.getCacheEggConfig();
        Assert.assertNotNull(config);
    }
}

package com.lawu.chick.operator.api;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lawu.chick.cache.service.ChickBaseConfigCacheService;
import com.lawu.chick.cache.service.co.ChickBaseConfigCO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChickBaseConfigCacheServiceImplTest {
    
    @Autowired ChickBaseConfigCacheService chickBaseConfigCacheService;
    
    @Ignore
    @Test
    public void saveCacheChickBaseInfo() {
        ChickBaseConfigCO config = new ChickBaseConfigCO();
        List<String> chickEggProdTime = new ArrayList<>();
        chickEggProdTime.add("10:00");
        chickEggProdTime.add("14:00");
        chickEggProdTime.add("18:00");
        chickEggProdTime.add("22:00");
        config.setChickEggProdTime(chickEggProdTime);
        config.setChickLayingEggsTime(60);
        chickBaseConfigCacheService.saveCacheChickBaseInfo(config);
    }
    
    @Ignore
    @Test
    public void getCacheChickBaseInfo() {
        ChickBaseConfigCO config = chickBaseConfigCacheService.getCacheChickBaseInfo();
        Assert.assertNotNull(config);
    }
}

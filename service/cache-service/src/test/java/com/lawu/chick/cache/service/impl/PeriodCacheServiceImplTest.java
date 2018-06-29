package com.lawu.chick.cache.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lawu.chick.cache.service.PeriodCacheService;

/**
 * @author Leach
 * @date 2018/4/28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CacheServiceApplicationTest.class)
public class PeriodCacheServiceImplTest extends EmbeddedRedis {

    @Autowired
    private PeriodCacheService periodCacheService;

    @Test
    public void increaseDaily() {
        int val = periodCacheService.increaseDaily("unit_test", 1);
        Assert.assertEquals(1, val);
        val = periodCacheService.increaseDaily("unit_test", 2);
        Assert.assertEquals(3, val);
    }
}

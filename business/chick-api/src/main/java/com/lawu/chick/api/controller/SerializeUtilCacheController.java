package com.lawu.chick.api.controller;

import com.lawu.chick.api.dto.SerializeBeanDTO;
import com.lawu.chick.cache.service.SerializeUtilCacheService;
import com.lawu.chick.cache.service.enums.RedisDataTypeEnum;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 缓存数据反序列化
 */
@RestController
@RequestMapping(value = "serializeUtilCache/")
public class SerializeUtilCacheController extends BaseController {

    @Autowired
    private SerializeUtilCacheService serializeUtilCacheService;

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public Result<SerializeBeanDTO> get(@RequestParam String redisKey, @RequestParam String keyType, @RequestParam int serializeFlag) {
        String value = serializeUtilCacheService.get(redisKey, RedisDataTypeEnum.get(keyType), serializeFlag);
        SerializeBeanDTO rtn = new SerializeBeanDTO();
        rtn.setJsonStr(value);
        return successGet(rtn);
    }

}
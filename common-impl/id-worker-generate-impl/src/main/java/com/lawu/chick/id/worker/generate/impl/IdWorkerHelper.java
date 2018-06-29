package com.lawu.chick.id.worker.generate.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.lawu.id.worker.generate.service.IdWorkerService;

/**
 * 
 * @author jiangxinjun
 * @createDate 2018年4月24日
 * @updateDate 2018年4月24日
 */
public class IdWorkerHelper {
    
    /**
     * 远程访问服务
     */
    @Autowired
    private IdWorkerService idWorkerService;
    
    /**
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2017年10月25日
     * @updateDate 2018年4月13日
     */
    public String generate(BizIdType bizIdType) {
        return bizIdType.getPrefix() + idWorkerService.generate();
    }
}

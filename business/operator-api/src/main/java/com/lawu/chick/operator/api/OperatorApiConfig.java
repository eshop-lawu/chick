package com.lawu.chick.operator.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 运营平台配置
 * @author jiangxinjun
 * @createDate 2018年4月25日
 * @updateDate 2018年4月25日
 */
@Component
public class OperatorApiConfig {

    @Value(value = "${fastdfs.trackerServers}")
    private String trackerServers;

    @Value(value = "${fastdfs.trackerHttpPort}")
    private int trackerHttpPort;

    public String getTrackerServers() {
        return trackerServers;
    }

    public int getTrackerHttpPort() {
        return trackerHttpPort;
    }

}

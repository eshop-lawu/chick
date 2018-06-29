package com.lawu.chick.service;

import java.util.List;

import com.lawu.chick.service.bo.RedpacketSendRecordBO;
import com.lawu.chick.service.param.RedpacketSendRecordParam;

/**
 * @author meishuquan
 * @date 2018/4/27.
 */
public interface RedpacketSendRecordService {

    /**
     * 保存红包发放记录
     *
     * @param param
     * @author meishuquan
     */
    Long saveRedpacketSendRecord(RedpacketSendRecordParam param);

    /**
     * 发放红包
     *
     * @param id
     * @author meishuquan
     */
    void sendRedpacket(Long id);

    /**
     * 查询发放中的红包
     *
     * @param offset
     * @param pageSize
     * @return
     * @author meishuquan
     */
    List<RedpacketSendRecordBO> listRedpacketSendRecord(int offset, int pageSize);

    /**
     * 更新红包发放结果
     *
     * @param param
     * @author meishuquan
     */
    void updateRedpacketSendResult(RedpacketSendRecordParam param);

}

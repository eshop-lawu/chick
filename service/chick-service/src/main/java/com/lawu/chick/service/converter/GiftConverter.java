package com.lawu.chick.service.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.chick.repository.domain.GiftDO;
import com.lawu.chick.service.bo.GiftBO;

/**
 * @author meishuquan
 * @date 2018/5/16.
 */
public class GiftConverter {

    public static GiftBO convertBO(GiftDO giftDO) {
        if (giftDO == null) {
            return null;
        }

        GiftBO giftBO = new GiftBO();
        giftBO.setId(giftDO.getId());
        giftBO.setName(giftDO.getName());
        giftBO.setImgPath(giftDO.getImgPath());
        giftBO.setPrice(giftDO.getPrice());
        giftBO.setInventory(giftDO.getInventory());
        giftBO.setStatus(giftDO.getStatus());
        giftBO.setType(giftDO.getType());
        giftBO.setEggQuantity(giftDO.getEggQuantity());
        giftBO.setDescription(giftDO.getDescription());
        giftBO.setGmtModified(giftDO.getGmtModified());
        giftBO.setGmtCreate(giftDO.getGmtCreate());
        return giftBO;
    }

    public static List<GiftBO> convertBOS(List<GiftDO> giftDOS) {
        List<GiftBO> giftBOS = new ArrayList<>();
        if (giftDOS == null || giftDOS.isEmpty()) {
            return giftBOS;
        }

        for (GiftDO giftDO : giftDOS) {
            giftBOS.add(convertBO(giftDO));
        }
        return giftBOS;
    }

}

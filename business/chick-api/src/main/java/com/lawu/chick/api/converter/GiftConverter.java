package com.lawu.chick.api.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.chick.api.dto.GiftDTO;
import com.lawu.chick.service.bo.GiftBO;
import com.lawu.chick.service.enums.GiftTypeEnum;

/**
 * @author meishuquan
 * @date 2018/5/16.
 */
public class GiftConverter {

    public static GiftDTO convertDTO(GiftBO giftBO) {
        if (giftBO == null) {
            return null;
        }

        GiftDTO giftDTO = new GiftDTO();
        giftDTO.setId(giftBO.getId());
        giftDTO.setName(giftBO.getName());
        giftDTO.setImgPath(giftBO.getImgPath());
        giftDTO.setPrice(giftBO.getPrice());
        giftDTO.setInventory(giftBO.getInventory());
        giftDTO.setTypeEnum(GiftTypeEnum.getEnum(giftBO.getType()));
        giftDTO.setEggQuantity(giftBO.getEggQuantity());
        giftDTO.setDescription(giftBO.getDescription());
        return giftDTO;
    }

    public static List<GiftDTO> convertDTOS(List<GiftBO> giftBOS) {
        List<GiftDTO> giftDTOS = new ArrayList<>();
        if (giftBOS == null || giftBOS.isEmpty()) {
            return giftDTOS;
        }

        for (GiftBO giftBO : giftBOS) {
            giftDTOS.add(convertDTO(giftBO));
        }
        return giftDTOS;
    }

}

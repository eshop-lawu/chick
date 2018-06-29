package com.lawu.chick.operator.api.conterver;

import java.util.ArrayList;
import java.util.List;

import com.lawu.chick.operator.api.dto.GiftDTO;
import com.lawu.chick.service.bo.GiftBO;
import com.lawu.chick.service.enums.GiftStatusEnum;
import com.lawu.chick.service.enums.GiftTypeEnum;

/**
 * @author meishuquan
 * @date 2018/5/16.
 */
public class GiftConverter {

    public static GiftDTO convertDTO(GiftBO giftBO) {
        GiftDTO giftDTO = new GiftDTO();
        if (giftBO == null) {
            return giftDTO;
        }

        giftDTO.setId(giftBO.getId());
        giftDTO.setName(giftBO.getName());
        giftDTO.setImgPath(giftBO.getImgPath());
        giftDTO.setPrice(giftBO.getPrice());
        giftDTO.setInventory(giftBO.getInventory());
        giftDTO.setStatusEnum(GiftStatusEnum.getEnum(giftBO.getStatus()));
        giftDTO.setStatusDes(GiftStatusEnum.getEnum(giftBO.getStatus()).getName());
        giftDTO.setTypeEnum(GiftTypeEnum.getEnum(giftBO.getType()));
        giftDTO.setTypeDes(GiftTypeEnum.getEnum(giftBO.getType()).getName());
        giftDTO.setEggQuantity(giftBO.getEggQuantity());
        giftDTO.setDescription(giftBO.getDescription());
        giftDTO.setGmtCreate(giftBO.getGmtCreate());
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

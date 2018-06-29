package com.lawu.chick.operator.api.conterver;

import java.util.ArrayList;
import java.util.List;

import com.lawu.chick.operator.api.dto.EggConfigDTO;
import com.lawu.chick.operator.api.dto.EggExchangeRedpacketDTO;
import com.lawu.chick.service.bo.EggConfigBO;
import com.lawu.chick.service.bo.EggExchangeRedpacketBO;

/**
 * @author meishuquan
 * @date 2018/4/25.
 */
public class SysConfigConverter {

    public static EggConfigDTO convertEggConfigDTO(EggConfigBO configBO) {
        EggConfigDTO configDTO = new EggConfigDTO();
        if (configBO == null) {
            return configDTO;
        }

        configDTO.setExchangeRedpacketEggs(configBO.getExchangeRedpacketEggs());
        configDTO.setMaxHouseEggs(configBO.getMaxHouseEggs());
        configDTO.setMaxLayEggs(configBO.getMaxLayEggs());
        configDTO.setLayEggsTotal(configBO.getLayEggsTotal());
        configDTO.setEggEffectiveTime(configBO.getEggEffectiveTime());
        configDTO.setWxSendName(configBO.getWxSendName());
        configDTO.setWxWishing(configBO.getWxWishing());
        configDTO.setWxActName(configBO.getWxActName());
        configDTO.setWxRemark(configBO.getWxRemark());
        configDTO.setMinGuaranteedValue(configBO.getMinGuaranteedValue());
        configDTO.setMaxGuaranteedValue(configBO.getMaxGuaranteedValue());
        configDTO.setRedpacketDTOS(convertRedpacketDTOS(configBO.getRedpacketBOS()));
        return configDTO;
    }

    private static List<EggExchangeRedpacketDTO> convertRedpacketDTOS(List<EggExchangeRedpacketBO> redpacketBOS) {
        List<EggExchangeRedpacketDTO> redpacketDTOS = new ArrayList<>();
        if (redpacketBOS == null || redpacketBOS.isEmpty()) {
            return redpacketDTOS;
        }

        for (EggExchangeRedpacketBO redpacketBO : redpacketBOS) {
            EggExchangeRedpacketDTO redpacketDTO = new EggExchangeRedpacketDTO();
            redpacketDTO.setMinPrice(redpacketBO.getMinPrice());
            redpacketDTO.setMaxPrice(redpacketBO.getMaxPrice());
            redpacketDTO.setPriceRate(redpacketBO.getPriceRate());
            redpacketDTOS.add(redpacketDTO);
        }
        return redpacketDTOS;
    }

}

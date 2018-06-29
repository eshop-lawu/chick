package com.lawu.chick.api.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.chick.api.dto.EggRecordDTO;
import com.lawu.chick.service.bo.RangelandEventRecordBO;
import com.lawu.chick.service.enums.EventRecordDirectionEnum;

/**
 * @author meishuquan
 * @date 2018/5/10.
 */
public class RangelandEventRecordConverter {

    public static EggRecordDTO convertEggRecordDTO(RangelandEventRecordBO eventRecordBO) {
        if (eventRecordBO == null) {
            return null;
        }

        EggRecordDTO eggRecordDTO = new EggRecordDTO();
        eggRecordDTO.setTitle(eventRecordBO.getTitle());
        eggRecordDTO.setDirectionEnum(EventRecordDirectionEnum.getEnum(eventRecordBO.getDirection()));
        eggRecordDTO.setVal(eventRecordBO.getVal());
        eggRecordDTO.setGmtCreate(eventRecordBO.getGmtCreate());
        return eggRecordDTO;
    }

    public static List<EggRecordDTO> convertEggRecordDTOS(List<RangelandEventRecordBO> eventRecordBOS) {
        List<EggRecordDTO> eggRecordDTOS = new ArrayList<>();
        if (eventRecordBOS == null || eventRecordBOS.isEmpty()) {
            return eggRecordDTOS;
        }

        for (RangelandEventRecordBO eventRecordBO : eventRecordBOS) {
            eggRecordDTOS.add(convertEggRecordDTO(eventRecordBO));
        }
        return eggRecordDTOS;
    }

}

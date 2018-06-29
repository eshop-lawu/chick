package com.lawu.chick.operator.api.conterver;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.lawu.chick.operator.api.dto.UserListDTO;
import com.lawu.chick.operator.service.bo.UserListBO;
import com.lawu.chick.operator.service.dto.constants.StatusEnum;

/**
 * @author zhangyong
 * @date 2018/4/25.
 */
public class UserConverter {
    public static UserListDTO coverDTO(UserListBO userListBO) {
        if (userListBO == null) {
            return null;
        }
        UserListDTO userListDTO = new UserListDTO();
        userListDTO.setAccount(userListBO.getAccount());
        userListDTO.setId(userListBO.getId());
        if (StringUtils.isEmpty(userListBO.getName())) {
            userListDTO.setName("");
        } else {
            userListDTO.setName(userListBO.getName());
        }
        userListDTO.setGmtCreate(userListBO.getGmtCreate());
        userListDTO.setPassword(userListBO.getPassword());
        if (StatusEnum.STATUS_DISABLE == userListBO.getStatusEnum()) {
            userListDTO.setStatus("已禁用");
        } else {
            userListDTO.setStatus("已启用");
        }
        return userListDTO;
    }

    public static List<UserListDTO> coverDTOS(List<UserListBO> records) {
        if (records.isEmpty()) {
            return new ArrayList<>();
        }
        List<UserListDTO> userListDTOS = new ArrayList<UserListDTO>();
        for (UserListBO userListBO : records) {
            UserListDTO userListDTO = new UserListDTO();
            userListDTO.setAccount(userListBO.getAccount());
            userListDTO.setId(userListBO.getId());
            if (StringUtils.isEmpty(userListBO.getName())) {
                userListDTO.setName("");
            } else {
                userListDTO.setName(userListBO.getName());
            }
            userListDTO.setGmtCreate(userListBO.getGmtCreate());
            userListDTO.setPassword(userListBO.getPassword());
            if (StatusEnum.STATUS_DISABLE == userListBO.getStatusEnum()) {
                userListDTO.setStatus("已禁用");
            } else {
                userListDTO.setStatus("已启用");
            }
            userListDTOS.add(userListDTO);
        }
        return userListDTOS;
    }
}

package com.lawu.chick.operator.service.converter;

import com.lawu.chick.operator.repository.domain.UserDO;
import com.lawu.chick.operator.service.bo.UserBO;
import com.lawu.chick.operator.service.bo.UserListBO;
import com.lawu.chick.operator.service.dto.constants.StatusEnum;


/**
 * @author zhangyong
 * @date 2017/4/19.
 */
public class UserConverter {

    public static UserBO convertBO(UserDO userDO) {
        if(userDO == null){
            return null;
        }
        UserBO userBO = new UserBO();
        userBO.setId(userDO.getId());
        userBO.setAccount(userDO.getAccount());
        userBO.setPassword(userDO.getPassword());
        userBO.setName(userDO.getName());
        userBO.setStatus(userDO.getStatus());
        return userBO;
    }

    public static UserListBO cover(UserDO userDO) {
        if(userDO == null){
            return null;
        }
        UserListBO userListBO = new UserListBO();
        userListBO.setId(userDO.getId());
        userListBO.setAccount(userDO.getAccount());
        userListBO.setName(userDO.getName());
        userListBO.setGmtCreate(userDO.getGmtCreate());
        userListBO.setPassword(userDO.getPassword());
        userListBO.setStatusEnum(StatusEnum.getEnum(userDO.getStatus()));
        return userListBO;
    }

   /* public static List<UserListDTO> coverDTOS(List<UserListBO> records) {
        if(records.isEmpty()){
            return new ArrayList<>();
        }
        List<UserListDTO> userListDTOS = new ArrayList<UserListDTO>();
        for(UserListBO userListBO : records){
            UserListDTO userListDTO = new UserListDTO();
            userListDTO.setAccount(userListBO.getAccount());
            userListDTO.setId(userListBO.getId());
            if(StringUtils.isEmpty(userListBO.getName())){
                userListDTO.setName("");
            }else{
                userListDTO.setName(userListBO.getName());
            }
            userListDTO.setGmtCreate(userListBO.getGmtCreate());
            userListDTO.setPassword(userListBO.getPassword());
            if(StatusEnum.STATUS_DISABLE.val == userListBO.getStatusEnum().val){
                userListDTO.setStatus("已禁用");
            }else{
                userListDTO.setStatus("已启用");
            }
            userListDTOS.add(userListDTO);
        }
        return userListDTOS;
    }

    public static UserListDTO coverDTO(UserListBO userListBO) {
        if(userListBO == null){
            return null;
        }
        UserListDTO userListDTO = new UserListDTO();
        userListDTO.setAccount(userListBO.getAccount());
        userListDTO.setId(userListBO.getId());
        if(StringUtils.isEmpty(userListBO.getName())){
            userListDTO.setName("");
        }else{
            userListDTO.setName(userListBO.getName());
        }
        userListDTO.setGmtCreate(userListBO.getGmtCreate());
        userListDTO.setPassword(userListBO.getPassword());
        if(StatusEnum.STATUS_DISABLE.val == userListBO.getStatusEnum().val){
            userListDTO.setStatus("已禁用");
        }else{
            userListDTO.setStatus("已启用");
        }
        return userListDTO;
    }*/
}

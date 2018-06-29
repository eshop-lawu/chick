package com.lawu.chick.operator.service.dto.constants;

/**
 * 
 * @author jiangxinjun
 * @createDate 2018年4月24日
 * @updateDate 2018年4月24日
 */
public enum LogTitleEnum {
	

    PERMSION_ADD(ModuleEnum.OPERATOR, "新增权限"),
    PERMSION_DELETE(ModuleEnum.OPERATOR, "删除权限"),
    PERMSION_UPDATE(ModuleEnum.OPERATOR, "修改权限"),
    PROPERTY_ADD(ModuleEnum.OPERATOR, "新增系统参数"),
    PROPERTY_DELETE(ModuleEnum.OPERATOR, "删除权限"),
    PROPERTY_UPDATE(ModuleEnum.OPERATOR, "修改权限"),
    USER_ADD(ModuleEnum.OPERATOR, "新增用户"),
    USER_UPDATE(ModuleEnum.OPERATOR, "修改用户"),
    USER_DELETE(ModuleEnum.OPERATOR, "删除用户"),
    USER_DISABLE(ModuleEnum.OPERATOR, "禁用用户"),
    USER_ENABLE(ModuleEnum.OPERATOR, "禁用用户"),
    RESET_PWD(ModuleEnum.OPERATOR, "重置密码"),
    ROLE_ADD(ModuleEnum.OPERATOR, "添加角色"),
    ROLE_UPDATE(ModuleEnum.OPERATOR, "修改角色"),
    ROLE_DELETE(ModuleEnum.OPERATOR, "删除角色"),

    EDIT_SIGN_RULE(ModuleEnum.SIGN, "编辑签到配置"),
    //小鸡模块

    CHICK_DOWN(ModuleEnum.CHICK, "小鸡下架"),
    EGG_CONFIG(ModuleEnum.EGG, "更新鸡蛋配置"),
    CHICK_CONFIG(ModuleEnum.CHICK, "更新小鸡配置"),
    REGANLE_CONFIG(ModuleEnum.RANGELAND, "更新牧场配置"),
    PRODUCT_ADD(ModuleEnum.PRODUCT, "新增商品"),
    PRODUCT_UPDATE(ModuleEnum.PRODUCT, "修改商品"),
    PRODUCT_UP(ModuleEnum.PRODUCT, "商品上架"),
    PRODUCT_DOWN(ModuleEnum.PRODUCT, "商品下架"),
    EGG_EXCHANGE_GOLE_SEND(ModuleEnum.EGG, "发放黄金"),
    EGG_EXCHANGE_REDPACKET_SEND(ModuleEnum.EGG, "发放红包"),
    GIFT_ADD(ModuleEnum.GIFT, "保存礼品信息"),

    TASK_REWARDS_CONFIG(ModuleEnum.TASK_REWARDS, "保存任务奖励配置");

    private ModuleEnum model;
    private String name;

    public ModuleEnum getModel() {
        return model;
    }

    public String getName() {
        return name;
    }

    LogTitleEnum(ModuleEnum model, String name) {
        this.model = model;
        this.name = name;
    }
}

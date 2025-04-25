package com.example.demo.utils;

/**
 * 错误编码，由5位数字组成，前2位为模块编码，后3位为业务编码
 * <p>
 * 如：10001（10代表系统模块，001代表业务代码）
 * </p>
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
public interface ErrorCode {
    int INTERNAL_SERVER_ERROR = 500;
    int UNAUTHORIZED = 401;
    int FORBIDDEN = 403;

    int NOT_NULL = 10001;
    int DB_RECORD_EXISTS = 10002;
    int PARAMS_GET_ERROR = 10003;
    int ACCOUNT_PASSWORD_ERROR = 10004;
    int ACCOUNT_DISABLE = 10005;
    int IDENTIFIER_NOT_NULL = 10006;
    int CAPTCHA_ERROR = 10007;
    int SUB_MENU_EXIST = 10008;
    int PASSWORD_ERROR = 10009;
    int ACCOUNT_NOT_EXIST = 10010;
    int SUPERIOR_DEPT_ERROR = 10011;
    int SUPERIOR_MENU_ERROR = 10012;
    int DATA_SCOPE_PARAMS_ERROR = 10013;
    int DEPT_SUB_DELETE_ERROR = 10014;
    int DEPT_USER_DELETE_ERROR = 10015;
    int JSON_FORMAT_ERROR = 10016;
    int ACCOUNT_EXIST = 10017;
    int SUPERIOR_REGION_ERROR = 10018;
    int REGION_SUB_DELETE_ERROR = 10019;
    int MOBILE_EXISTS_ERROR = 10020;
    int SMS_CODE_ERROR = 10021;

    //sys系统模块
    int SYS_UPDATE_FIXED_POST_ERROR = 20001; //固定岗位不可修改异常
    int SYS_ADD_FIXED_POST_ERROR = 20002; //固定岗位不可新增
    int SYS_VILLAGE_ID_ERROR = 20003;//villageId缺失异常


    //人脸模块
    int FACE_CREAT_CLIENT_ERROR = 40001;//创建客户端失败
    int FACE_DETECT_LIVING_ERROR = 40002;//识别失败
    int FACE_RECOGNIZEG_ERROR = 40003;//图片认证
    int FACE_ADD_ERROR = 40004;//增加人脸
    int FACE_DELETE_ERROR = 40005;//删除人脸
    int FACE_COMPARE_ERROR = 40006;//1：1对比
    int FACE_SELECT_ERROR = 40007;//1：N查找
    int FACE_ENTITY_ADD_ERROR = 40008;//增加人脸实体
    int FACE_ENTITY_DELETE_ERROR = 40009;//删除人脸实体
    int FACE_ENTITY_GET_ERROR = 40010;//查找人脸实体
    int FACE_DB_ADD_ERROR = 40011;//增加人脸数据库
    int FACE_DB_DELETE_ERROR = 40012;//删除人脸数据库
    int FACE_DB_SELECT_ERROR = 400013;//查找人脸数据库
    int FACE_NOT_EXIST = 40014;//用户没有人脸数据
    int FACE_IMAGE_NOT_EXIST = 40015;//人脸图片不存在
    int FACE_IMAGE_ERROR = 40016;//人脸图片有问题
}

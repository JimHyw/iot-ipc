package com.example.demo.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
public class LoginVo {

    private String userName;//用户名
    private String password;//密码
    private String ip;//摄像头所在ip
    private Integer prot;//摄像头所在端口
    private Integer lDChannel =1;//使用预览通道
    private Integer lUserID;//
    private Integer lPlayID;//
    private String plsyStatus;//
    private String plsyMessage;//
    private String loginStatus;//
    private String loginMessage;//





}

package com.example.demo.dto.response;

import lombok.Data;

@Data
public class IpcDTO {

    private String userName ;//登录名
    private String passWord ;//登录密码

    private String ip ;//超脑IP

    private String port ;//端口

    private String rtspPort ;//rtsp端口
    private String channels ;//通道号
    private String resolution ;//分辨率
    private String playType ;//播放标识:预览=preview,回放=back

    private String httpIP ;//httpIP

    private String httpPort ;//httpPort
    private String wsIP ;//websocketIp
    private String wsPort ;//websocket端口

    private String devName;//摄像头名称
    private String devCode;//设备编号

    private String startTime;// 开始时间
    private String endTime;//结束时间

    public IpcDTO() {
    }
    public IpcDTO(String userName, String passWord, String ip, String rtspPort, String channels, String resolution, String playType, String httpIP, String httpPort,String wsIP, String wsPort, String devCode, String devName) {
        this.userName = userName;
        this.passWord = passWord;
        this.ip = ip;
        this.rtspPort = rtspPort;
        this.channels = channels;
        this.resolution = resolution;
        this.playType = playType;
        this.httpIP = httpIP;
        this.httpPort = httpPort;
        this.wsIP = wsIP;
        this.wsPort = wsPort;
        this.devCode = devCode;
        this.devName = devName;
    }
}

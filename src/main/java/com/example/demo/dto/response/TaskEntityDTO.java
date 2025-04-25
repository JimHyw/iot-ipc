package com.example.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskEntityDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private  String taskId;//进程id
    private  String ipcName;//摄像头名称
//    @JsonIgnore
    private  String rtspUrl;//rtsp推送流
    @JsonIgnore
    private  String playUrl;//播放流:可以是预览或回放
    @JsonIgnore
    private  String cmdStr;//执行命令
//    private  Process process;
//    private  Thread thread;



}

package com.example.demo.api;


import com.example.demo.utils.DealProcessStream;
import com.example.demo.utils.Result;
import com.example.demo.utils.RtspToMP4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestCtl {
    private final Map<Integer, Process> map = new HashMap<>();
    @Autowired
    private RtspToMP4 rtspToMP4;

    @ResponseBody
    @RequestMapping("/preview1")
    public Result<String> preview(String ip, String port, String userName, String passWord, String channels, String startKey, HttpServletResponse arg1) {
        Result<String> result = new Result<>();
        String msg = "预览推流失败";
        int code = -1;
        String taskId = "";
        try {
            long startTime = System.currentTimeMillis();
            System.out.println("开始执行");
            String current = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date());
            //主码流播放
            String cmdStr = "ffmpeg  -rtsp_transport tcp -i \"rtsp://" + userName + ":" + passWord + "@" + ip + ":" + port + "/Streaming/Channels/" + channels + "01\" -q 0 -f mpegts -codec:v mpeg1video -s 1024x768 http://127.0.0.1:8081/supersecret/" + startKey;
            System.out.println(current + "====cmdStr===" + cmdStr);

            Runtime rt = Runtime.getRuntime();
            Process process = rt.exec(cmdStr);
            //必须加上这两个，不然推流会停止
            new Thread(new DealProcessStream(process.getInputStream())).start();
            new Thread(new DealProcessStream(process.getErrorStream())).start();
            System.out.println("执行结束");
            System.out.println("开启ffmpeg进程耗时：" + (System.currentTimeMillis() - startTime));

            process.waitFor();
            msg = "预览推流成功";
            code = 0;
        } catch (Throwable t) {
            System.out.println(t);
            t.printStackTrace();
        }
        result.setData(taskId);
        result.setMsg(msg);
        result.setCode(code);

        arg1.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST");
        arg1.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        arg1.setHeader("Access-Control-Max-Age", "86400");
        arg1.setHeader("Access-Control-Allow-Origin", "*");
        return result;

    }


    @ResponseBody
    @RequestMapping("/playback")
    public Result<String> playback(String ip, String port, String userName, String passWord, String channels, String startKey, HttpServletResponse arg1) {
        Result<String> result = new Result<>();
        String msg = "回放推流失败";
        int code = -1;
        try {
            long startTime = System.currentTimeMillis();
            System.out.println("开始执行");
            //主码流播放
//            String cmdStr = "ffmpeg -rtsp_transport tcp -i \"rtsp://admin:pwd666@192.168.2.195:554/Streaming/tracks/101?starttime=20221121T105010Z&endtime=20221121T110510Z\" -q 0 -f mpegts -codec:v mpeg1video -s 1366x768 http://127.0.0.1:8081/testxxx/live1yyy";
            String cmdStr = "ffmpeg -rtsp_transport tcp -i \"rtsp://" + userName + ":" + passWord + "@" + ip + ":" + port + "/Streaming/tracks/" + channels + "01?starttime=20221122T105010Z&endtime=20221122T110510Z\" -q 0 -f mpegts -codec:v mpeg1video -s 480x320 http://127.0.0.1:8081/supersecret/" + startKey;
            System.out.println("cmdStr===" + cmdStr);
            Runtime rt = Runtime.getRuntime();
            Process process = rt.exec(cmdStr);
            //必须加上这两个，不然推流会停止
            new Thread(new DealProcessStream(process.getInputStream())).start();
            new Thread(new DealProcessStream(process.getErrorStream())).start();
            System.out.println("执行结束");
            System.out.println("开启ffmpeg进程耗时：" + (System.currentTimeMillis() - startTime));
            process.waitFor();
            msg = "回放推流成功";
            code = 0;
        } catch (Throwable t) {
            System.out.println(t);
            t.printStackTrace();
        }

        result.setData(msg);
        result.setMsg(msg);
        result.setCode(code);

        arg1.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST");
        arg1.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        arg1.setHeader("Access-Control-Max-Age", "86400");
        return result;

    }


    @ResponseBody
    @RequestMapping("/preview")
    public Result<String> testPreview(String ip, String port, String userName, String passWord, String channels, String startKey, HttpServletResponse arg1) {

        Result<String> result = new Result<>();
        try {

            String ffmpegPath = "D:\\ffmpeg\\bin\\ffmpeg.exe";
            String streamUrl = "rtsp://" + userName + ":" + passWord + "@" + ip + ":" + port + "/Streaming/Channels/" + channels + "01";
            Process process = rtspToMP4.testPreview(ffmpegPath, streamUrl, ip, port, userName, passWord, channels, startKey);
            if (null != process) {
                map.put(789, process);
//            return new Result().ok("Start");
            }

            arg1.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST");
            arg1.setHeader("Access-Control-Allow-Headers", "x-requested-with");
            arg1.setHeader("Access-Control-Max-Age", "86400");
            arg1.setHeader("Access-Control-Allow-Origin", "*");
            JavaPID.getFfmpegPid();
        } catch (IOException e1) {
            e1.printStackTrace();
            System.out.println(e1.getMessage() + "====ppp");
        }

        return result;

    }

    //关闭ffmpeg进程
    @ResponseBody
    @RequestMapping("/closesFfmpeg")
    public Result<String> closesFfmpeg(HttpServletResponse arg1) {
        Result<String> result = new Result<>();

        arg1.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST");
        arg1.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        arg1.setHeader("Access-Control-Max-Age", "86400");
        arg1.setHeader("Access-Control-Allow-Origin", "*");

        try {
            long startTime = System.currentTimeMillis();
            String fPids1 = JavaPID.getFfmpegPid();
            System.out.println("准备杀死ffmpeg进程：" + fPids1);
            KillProcessTest.closesFfmpeg();

            String fPids2 = JavaPID.getFfmpegPid();
            System.out.println("杀死后ffmpeg进程的pid：" + fPids2);
            System.out.println("耗时：---------------" + (System.currentTimeMillis() - startTime));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return result;

    }

}
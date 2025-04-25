package com.example.demo.api;


import com.example.demo.utils.DealProcessStream;
import com.example.demo.utils.ProcessUtil;
import com.example.demo.utils.Result;
import com.example.demo.utils.RtspToHttp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test2")
public class TestCtl2 {
    private final Map<Integer, Process> map = new HashMap<>();
    @Autowired
    private RtspToHttp rtspToHttp;

    @ResponseBody
    @RequestMapping("/ipc1")
    public Result<String> ipc1(HttpServletResponse arg1) {
        closesFfmpeg(arg1);
        int i = 0;
        while (i <= 5000) {
            i++;
        }
        Result<String> result = new Result<>();
        String msg = "预览推流失败";
        int code = -1;
        String taskId = "";
        try {
            long startTime = System.currentTimeMillis();
            System.out.println("开始执行");
            String current = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date());
            //主码流播放11111111
            String cmdStr1 = "ffmpeg  -rtsp_transport  tcp  -i  rtsp://admin:pwd235@192.168.2.141:554/Streaming/Channels/101  -q 0  -f mpegts  -codec:v  mpeg1video  -s  480x320 http://127.0.0.1:8081/supersecret/pre141 ";
            System.out.println(current + "====cmdStr1===" + cmdStr1);

            Runtime rt1 = Runtime.getRuntime();
            Process process1 = rt1.exec(cmdStr1);
            //必须加上这两个，不然推流会停止
            new Thread(new DealProcessStream(process1.getInputStream())).start();
            new Thread(new DealProcessStream(process1.getErrorStream())).start();
            System.out.println("执行结束");

//            process1.waitFor();


            //主码流播放222222222
            String cmdStr2 = "ffmpeg  -rtsp_transport  tcp  -i  rtsp://admin:pwd666@192.168.2.195:554/Streaming/Channels/1201  -q 0  -f mpegts  -codec:v  mpeg1video  -s  480x320 http://127.0.0.1:8081/supersecret/pre195D12 ";
            System.out.println(current + "====cmdStr2===" + cmdStr2);

            Runtime rt2 = Runtime.getRuntime();
            Process process2 = rt2.exec(cmdStr2);
            //必须加上这两个，不然推流会停止
            new Thread(new DealProcessStream(process2.getInputStream())).start();
            new Thread(new DealProcessStream(process2.getErrorStream())).start();
            System.out.println("执行结束");

//            process2.waitFor();


            //主码流播放333333333
            String cmdStr3 = "ffmpeg  -rtsp_transport  tcp  -i  rtsp://admin:pwd666@192.168.2.195:554/Streaming/Channels/101  -q 0  -f mpegts  -codec:v  mpeg1video  -s  480x320 http://127.0.0.1:8081/supersecret/pre195 ";
            System.out.println(current + "====cmdStr3===" + cmdStr3);

            Runtime rt3 = Runtime.getRuntime();
            Process process3 = rt3.exec(cmdStr3);
            //必须加上这两个，不然推流会停止
            new Thread(new DealProcessStream(process3.getInputStream())).start();
            new Thread(new DealProcessStream(process3.getErrorStream())).start();
            System.out.println("执行结束");

//            process3.waitFor();

            //主码流播放4444
            String cmdStr4 = "ffmpeg  -rtsp_transport  tcp  -i  rtsp://admin:pwd666@192.168.2.195:554/Streaming/Channels/1301  -q 0  -f mpegts  -codec:v  mpeg1video  -s  480x320 http://127.0.0.1:8081/supersecret/pre195D13 ";
            System.out.println(current + "====cmdStr4===" + cmdStr4);

            Runtime rt4 = Runtime.getRuntime();
            Process process4 = rt4.exec(cmdStr4);
            //必须加上这两个，不然推流会停止
            new Thread(new DealProcessStream(process4.getInputStream())).start();
            new Thread(new DealProcessStream(process4.getErrorStream())).start();
            System.out.println("执行结束");

            process1.waitFor();
            process2.waitFor();
            process3.waitFor();
            process4.waitFor();
//            process4.waitFor();
            System.out.println("开启ffmpeg进程耗时：" + (System.currentTimeMillis() - startTime));

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
    @RequestMapping("/ipc2")
    public Result<String> ipc2(HttpServletResponse arg1) {
        closesFfmpeg(arg1);
        int i = 0;
        while (i <= 5000) {
            i++;
        }
        Result<String> result = new Result<>();
        String msg = "预览推流失败";
        int code = -1;
        String taskId = "";
        try {
            long startTime = System.currentTimeMillis();
            System.out.println("开始执行");
            String current = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date());
            //主码流播放11111111
            String cmdStr1 = "ffmpeg  -rtsp_transport  tcp  -i  rtsp://admin:pwd666@192.168.2.195:554/Streaming/Channels/201  -q 0  -f mpegts  -codec:v  mpeg1video  -s  480x320 http://127.0.0.1:8081/supersecret/pre195D2 ";
            System.out.println(current + "====cmdStr1===" + cmdStr1);

            Runtime rt1 = Runtime.getRuntime();
            Process process1 = rt1.exec(cmdStr1);
            //必须加上这两个，不然推流会停止
            new Thread(new DealProcessStream(process1.getInputStream())).start();
            new Thread(new DealProcessStream(process1.getErrorStream())).start();
            System.out.println("执行结束");


            //主码流播放222222222
            String cmdStr2 = "ffmpeg  -rtsp_transport  tcp  -i  rtsp://admin:pwd666@192.168.2.195:554/Streaming/Channels/301  -q 0  -f mpegts  -codec:v  mpeg1video  -s  480x320 http://127.0.0.1:8081/supersecret/pre195D3 ";
            System.out.println(current + "====cmdStr2===" + cmdStr2);

            Runtime rt2 = Runtime.getRuntime();
            Process process2 = rt2.exec(cmdStr2);
            //必须加上这两个，不然推流会停止
            new Thread(new DealProcessStream(process2.getInputStream())).start();
            new Thread(new DealProcessStream(process2.getErrorStream())).start();
            System.out.println("执行结束");


            //主码流播放333333333
            String cmdStr3 = "ffmpeg  -rtsp_transport  tcp  -i  rtsp://admin:pwd666@192.168.2.195:554/Streaming/Channels/401  -q 0  -f mpegts  -codec:v  mpeg1video  -s  480x320 http://127.0.0.1:8081/supersecret/pre195D4 ";
            System.out.println(current + "====cmdStr3===" + cmdStr3);

            Runtime rt3 = Runtime.getRuntime();
            Process process3 = rt3.exec(cmdStr3);
            //必须加上这两个，不然推流会停止
            new Thread(new DealProcessStream(process3.getInputStream())).start();
            new Thread(new DealProcessStream(process3.getErrorStream())).start();
            System.out.println("执行结束");

//            process3.waitFor();

            //主码流播放4444
            String cmdStr4 = "ffmpeg  -rtsp_transport  tcp  -i  rtsp://admin:pwd666@192.168.2.195:554/Streaming/Channels/501  -q 0  -f mpegts  -codec:v  mpeg1video  -s  480x320 http://127.0.0.1:8081/supersecret/pre195D5 ";
            System.out.println(current + "====cmdStr4===" + cmdStr4);

            Runtime rt4 = Runtime.getRuntime();
            Process process4 = rt4.exec(cmdStr4);
            //必须加上这两个，不然推流会停止
            new Thread(new DealProcessStream(process4.getInputStream())).start();
            new Thread(new DealProcessStream(process4.getErrorStream())).start();
            System.out.println("执行结束");

            process1.waitFor();
            process2.waitFor();
            process3.waitFor();
            process4.waitFor();
            System.out.println("开启ffmpeg进程耗时：" + (System.currentTimeMillis() - startTime));

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
    @RequestMapping("/ipc3")
    public Result<String> ipc3(HttpServletResponse arg1) {
        closesFfmpeg(arg1);
        int i = 0;
        while (i <= 5000) {
            i++;
//            System.out.println(i+"===");
        }
//        System.out.println("9999999999999===");
        Result<String> result = new Result<>();
        String msg = "预览推流失败";
        int code = -1;
        String taskId = "";
        try {
            long startTime = System.currentTimeMillis();
            System.out.println("开始执行");
            String current = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date());
            //主码流播放11111111
            String cmdStr1 = "ffmpeg  -rtsp_transport  tcp  -i  rtsp://admin:pwd666@192.168.2.195:554/Streaming/Channels/601  -q 0  -f mpegts  -codec:v  mpeg1video  -s  480x320 http://127.0.0.1:8081/supersecret/pre195D6 ";
            System.out.println(current + "====cmdStr1===" + cmdStr1);

            Runtime rt1 = Runtime.getRuntime();
            Process process1 = rt1.exec(cmdStr1);
            //必须加上这两个，不然推流会停止
            new Thread(new DealProcessStream(process1.getInputStream())).start();
            new Thread(new DealProcessStream(process1.getErrorStream())).start();
            System.out.println("执行结束");


            //主码流播放222222222
            String cmdStr2 = "ffmpeg  -rtsp_transport  tcp  -i  rtsp://admin:pwd666@192.168.2.195:554/Streaming/Channels/1101  -q 0  -f mpegts  -codec:v  mpeg1video  -s  480x320 http://127.0.0.1:8081/supersecret/pre195D11 ";
            System.out.println(current + "====cmdStr2===" + cmdStr2);

            Runtime rt2 = Runtime.getRuntime();
            Process process2 = rt2.exec(cmdStr2);
            //必须加上这两个，不然推流会停止
            new Thread(new DealProcessStream(process2.getInputStream())).start();
            new Thread(new DealProcessStream(process2.getErrorStream())).start();
            System.out.println("执行结束");


            //主码流播放333333333
            String cmdStr3 = "ffmpeg  -rtsp_transport  tcp  -i  rtsp://admin:pwd666@192.168.2.195:554/Streaming/Channels/901  -q 0  -f mpegts  -codec:v  mpeg1video  -s  480x320 http://127.0.0.1:8081/supersecret/pre195D9 ";
            System.out.println(current + "====cmdStr3===" + cmdStr3);

            Runtime rt3 = Runtime.getRuntime();
            Process process3 = rt3.exec(cmdStr3);
            //必须加上这两个，不然推流会停止
            new Thread(new DealProcessStream(process3.getInputStream())).start();
            new Thread(new DealProcessStream(process3.getErrorStream())).start();
            System.out.println("执行结束");

//            process3.waitFor();

            //主码流播放4444
            String cmdStr4 = "ffmpeg  -rtsp_transport  tcp  -i  rtsp://admin:pwd666@192.168.2.195:554/Streaming/Channels/1001  -q 0  -f mpegts  -codec:v  mpeg1video  -s  480x320 http://127.0.0.1:8081/supersecret/pre195D10 ";
            System.out.println(current + "====cmdStr4===" + cmdStr4);

            Runtime rt4 = Runtime.getRuntime();
            Process process4 = rt4.exec(cmdStr4);
            //必须加上这两个，不然推流会停止
            new Thread(new DealProcessStream(process4.getInputStream())).start();
            new Thread(new DealProcessStream(process4.getErrorStream())).start();
            System.out.println("执行结束");

            process1.waitFor();
            process2.waitFor();
            process3.waitFor();
            process4.waitFor();
            System.out.println("开启ffmpeg进程耗时：" + (System.currentTimeMillis() - startTime));

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
    @RequestMapping("/preview1")
    public Result<String> testPreview(String ip, String port, String userName, String passWord, String channels, String startKey, HttpServletResponse arg1) {

        Result<String> result = new Result<>();
        try {

            String ffmpegPath = "D:\\ffmpeg\\bin\\ffmpeg.exe";
            String streamUrl = "rtsp://" + userName + ":" + passWord + "@" + ip + ":" + port + "/Streaming/Channels/" + channels + "01";
            Process process = rtspToHttp.testPreview(ffmpegPath, streamUrl, ip, port, userName, passWord, channels, startKey);
            if (null != process) {
                map.put(789, process);
//            return new Result().ok("Start");
            }

            arg1.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST");
            arg1.setHeader("Access-Control-Allow-Headers", "x-requested-with");
            arg1.setHeader("Access-Control-Max-Age", "86400");
            arg1.setHeader("Access-Control-Allow-Origin", "*");
        } catch (Exception e1) {
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
            ProcessUtil.closeAllFFmpeg();

            System.out.println("耗时：---------------" + (System.currentTimeMillis() - startTime));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return result;

    }

}
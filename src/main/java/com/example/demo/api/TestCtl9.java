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
@RequestMapping("/test9")
public class TestCtl9 {
    @Autowired
    private RtspToHttp rtspToHttp;

    @ResponseBody
    @RequestMapping("/ipc1")
    public Result<Map<String, String>> ipc1(HttpServletResponse arg1) {
        arg1.setContentType("text/json; charset=utf-8");
        arg1.setHeader("Access-Control-Allow-Origin", "*");
        closesFfmpeg(arg1);
        int i = 0;
        while (i <= 5000) {
            i++;
        }
        Result<Map<String, String>> result = new Result<>();
        Map<String, String> resultMap = new HashMap<>();
        String msg = "预览推流失败";
        int code = -1;
        String taskId = "";
        try {
            String ipcKey = "pre141D1";
            long startTime = System.currentTimeMillis();
            System.out.println("开始执行");
            String current = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date());
            //主码流播放11111111
            String cmdStr1 = "ffmpeg  -rtsp_transport  tcp  -i  rtsp://admin:pwd235@192.168.2.141:554/Streaming/Channels/101  -q 0  -f mpegts  -codec:v  mpeg1video  -s  300x200 http://127.0.0.1:8081/supersecret/" + ipcKey;
            System.out.println(current + "====cmdStr1===" + cmdStr1);

            Runtime rt1 = Runtime.getRuntime();
            Process process1 = rt1.exec(cmdStr1);
            //必须加上这两个，不然推流会停止
            new Thread(new DealProcessStream(process1.getInputStream())).start();
            new Thread(new DealProcessStream(process1.getErrorStream())).start();
            System.out.println("执行结束");

            resultMap.put("url1", "ws://127.0.0.1:8082/" + ipcKey);
            ipcKey = "pre195D12";
            //主码流播放222222222
            String cmdStr2 = "ffmpeg  -rtsp_transport  tcp  -i  rtsp://admin:pwd666@192.168.2.195:554/Streaming/Channels/1201  -q 0  -f mpegts  -codec:v  mpeg1video  -s  300x200 http://127.0.0.1:8081/supersecret/" + ipcKey;
            System.out.println(current + "====cmdStr2===" + cmdStr2);

            Runtime rt2 = Runtime.getRuntime();
            Process process2 = rt2.exec(cmdStr2);
            //必须加上这两个，不然推流会停止
            new Thread(new DealProcessStream(process2.getInputStream())).start();
            new Thread(new DealProcessStream(process2.getErrorStream())).start();
            System.out.println("执行结束");
            resultMap.put("url2", "ws://127.0.0.1:8082/" + ipcKey);
            ipcKey = "pre195D6";
            //主码流播放333333333
            String cmdStr3 = "ffmpeg  -rtsp_transport  tcp  -i  rtsp://admin:pwd666@192.168.2.195:554/Streaming/Channels/601  -q 0  -f mpegts  -codec:v  mpeg1video  -s  300x200 http://127.0.0.1:8081/supersecret/" + ipcKey;
            System.out.println(current + "====cmdStr3===" + cmdStr3);

            Runtime rt3 = Runtime.getRuntime();
            Process process3 = rt3.exec(cmdStr3);
            //必须加上这两个，不然推流会停止
            new Thread(new DealProcessStream(process3.getInputStream())).start();
            new Thread(new DealProcessStream(process3.getErrorStream())).start();
            System.out.println("执行结束");
            resultMap.put("url3", "ws://127.0.0.1:8082/" + ipcKey);
            ipcKey = "pre195D13";
            //主码流播放4444
            String cmdStr4 = "ffmpeg  -rtsp_transport  tcp  -i  rtsp://admin:pwd666@192.168.2.195:554/Streaming/Channels/1301  -q 0  -f mpegts  -codec:v  mpeg1video  -s  300x200 http://127.0.0.1:8081/supersecret/" + ipcKey;
            System.out.println(current + "====cmdStr4===" + cmdStr4);

            Runtime rt4 = Runtime.getRuntime();
            Process process4 = rt4.exec(cmdStr4);
            //必须加上这两个，不然推流会停止
            new Thread(new DealProcessStream(process4.getInputStream())).start();
            new Thread(new DealProcessStream(process4.getErrorStream())).start();
            System.out.println("执行结束");
            resultMap.put("url4", "ws://127.0.0.1:8082/" + ipcKey);


            ipcKey = "pre195D2";
            System.out.println("开始执行");
            //主码流播放11111111
            String cmdStr5 = "ffmpeg  -rtsp_transport  tcp  -i  rtsp://admin:pwd666@192.168.2.195:554/Streaming/Channels/201  -q 0  -f mpegts  -codec:v  mpeg1video  -s  300x200 http://127.0.0.1:8081/supersecret/" + ipcKey;
            System.out.println(current + "====cmdStr5===" + cmdStr5);

            resultMap.put("url4", "ws://127.0.0.1:8082/" + ipcKey);
            ipcKey = "pre195D3";
            Runtime rt5 = Runtime.getRuntime();
            Process process5 = rt5.exec(cmdStr5);
            //必须加上这两个，不然推流会停止
            new Thread(new DealProcessStream(process5.getInputStream())).start();
            new Thread(new DealProcessStream(process5.getErrorStream())).start();
            System.out.println("执行结束");
            resultMap.put("url5", "ws://127.0.0.1:8082/" + ipcKey);


            //主码流播放222222222
            String cmdStr6 = "ffmpeg  -rtsp_transport  tcp  -i  rtsp://admin:pwd666@192.168.2.195:554/Streaming/Channels/301  -q 0  -f mpegts  -codec:v  mpeg1video  -s  300x200 http://127.0.0.1:8081/supersecret/" + ipcKey;
            System.out.println(current + "====cmdStr6===" + cmdStr6);

            ipcKey = "pre195D4";
            Runtime rt6 = Runtime.getRuntime();
            Process process6 = rt6.exec(cmdStr6);
            //必须加上这两个，不然推流会停止
            new Thread(new DealProcessStream(process6.getInputStream())).start();
            new Thread(new DealProcessStream(process6.getErrorStream())).start();
            System.out.println("执行结束");
            resultMap.put("url6", "ws://127.0.0.1:8082/" + ipcKey);

            //主码流播放333333333
            String cmdStr7 = "ffmpeg  -rtsp_transport  tcp  -i  rtsp://admin:pwd666@192.168.2.195:554/Streaming/Channels/401  -q 0  -f mpegts  -codec:v  mpeg1video  -s  300x200 http://127.0.0.1:8081/supersecret/" + ipcKey;
            System.out.println(current + "====cmdStr7===" + cmdStr7);

            ipcKey = "pre195D5";
            Runtime rt7 = Runtime.getRuntime();
            Process process7 = rt7.exec(cmdStr7);
            //必须加上这两个，不然推流会停止
            new Thread(new DealProcessStream(process7.getInputStream())).start();
            new Thread(new DealProcessStream(process7.getErrorStream())).start();
            System.out.println("执行结束");
            resultMap.put("url7", "ws://127.0.0.1:8082/" + ipcKey);

            //主码流播放4444
            String cmdStr8 = "ffmpeg  -rtsp_transport  tcp  -i  rtsp://admin:pwd666@192.168.2.195:554/Streaming/Channels/501  -q 0  -f mpegts  -codec:v  mpeg1video  -s  300x200 http://127.0.0.1:8081/supersecret/" + ipcKey;
            System.out.println(current + "====cmdStr8===" + cmdStr8);
            ipcKey = "pre195D11";
            Runtime rt8 = Runtime.getRuntime();
            Process process8 = rt8.exec(cmdStr8);
            //必须加上这两个，不然推流会停止
            new Thread(new DealProcessStream(process8.getInputStream())).start();
            new Thread(new DealProcessStream(process8.getErrorStream())).start();
            System.out.println("执行结束");
            resultMap.put("url8", "ws://127.0.0.1:8082/" + ipcKey);

            //主码流播放222222222
            String cmdStr9 = "ffmpeg  -rtsp_transport  tcp  -i  rtsp://admin:pwd666@192.168.2.195:554/Streaming/Channels/1101  -q 0  -f mpegts  -codec:v  mpeg1video  -s  300x200 http://127.0.0.1:8081/supersecret/" + ipcKey;
            System.out.println(current + "====cmdStr9===" + cmdStr9);

            Runtime rt9 = Runtime.getRuntime();
            Process process9 = rt9.exec(cmdStr9);
            //必须加上这两个，不然推流会停止
            new Thread(new DealProcessStream(process9.getInputStream())).start();
            new Thread(new DealProcessStream(process9.getErrorStream())).start();
            System.out.println("执行结束");
            ipcKey = "pre195D13";
            resultMap.put("url9", "ws://127.0.0.1:8082/" + ipcKey);


            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        process1.waitFor();
                        process2.waitFor();
                        process3.waitFor();
                        process4.waitFor();
                        process5.waitFor();
                        process6.waitFor();
                        process7.waitFor();
                        process8.waitFor();
                        process9.waitFor();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            });

            System.out.println("开启ffmpeg进程耗时：" + (System.currentTimeMillis() - startTime));

            msg = "预览推流成功";
            code = 0;
        } catch (Throwable t) {
            System.out.println(t);
            t.printStackTrace();
        }
        result.setData(resultMap);
        result.setMsg(msg);
        result.setCode(code);

        arg1.setContentType("text/json; charset=utf-8");
        arg1.setHeader("Access-Control-Allow-Origin", "*");
        System.out.println("result：" + result);
        return result;

    }

    //关闭ffmpeg进程
    @ResponseBody
    @RequestMapping("/closesFfmpeg")
    public Result<String> closesFfmpeg(HttpServletResponse arg1) {
        Result<String> result = new Result<>();

//        arg1.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST");
//        arg1.setHeader("Access-Control-Allow-Headers", "x-requested-with");
//        arg1.setHeader("Access-Control-Max-Age", "86400");
//        arg1.setHeader("Access-Control-Allow-Origin", "*");

        try {
            long startTime = System.currentTimeMillis();
            ProcessUtil.closeAllFFmpeg();

            System.out.println("耗时：---------------" + (System.currentTimeMillis() - startTime));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        arg1.setContentType("text/json; charset=utf-8");
        arg1.setHeader("Access-Control-Allow-Origin", "*");
        return result;

    }

}
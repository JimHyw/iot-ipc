package com.example.demo.utils;

import cc.eguid.FFmpegCommandManager.FFmpegManager;
import cc.eguid.FFmpegCommandManager.FFmpegManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class VideoUtil {
    @Autowired
    private static FFmpegManager manager;

    /**
     * 关闭进程
     *
     * @param tasker
     * @return
     */
    public static boolean stopTranscoding(String tasker) {
        if (!VideoUtil.taskerIsRun(tasker)) return true;
        return manager.stop(tasker);
    }

    /**
     * 判断当前推流进程是否存在
     *
     * @param appName 进程名称
     * @return 进程名称, 为"0"时表示进程不存在
     */
    public static boolean taskerIsRun(String appName) {
        return (manager.queryAll().size() > 0 && manager.query(appName) != null);
    }

    public void stopFFMPEG(String taskNameId) throws ParseException {
        if (manager != null) {
        } else {
            manager = new FFmpegManagerImpl();
        }
        manager.stop(taskNameId);
    }

    /**
     * 开始推流 testPreview
     *
     * @return 返回结果集{msg:"",tasker:""}
     */
    public Map<String, String> testPreview(String ip, String port, String userName, String passWord, String channels, String startKey) {
        if (manager == null) {
            manager = new FFmpegManagerImpl();
        }
        Map<String, String> result = new HashMap<>();
        int code = 0;
        String[] resultNames = {"成功", "初始化失败", "注册前请关闭预览", "注册失败", "通道获取失败"};
        String tasker = "cccc";
        long startTime = System.currentTimeMillis();
        System.out.println("开始执行");
        String current = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date());
        //主码流播放
        String cmdStr = "ffmpeg  -rtsp_transport tcp -i \"rtsp://" + userName + ":" + passWord + "@" + ip + ":" + port + "/Streaming/Channels/" + channels + "01\" -q 0 -f mpegts -codec:v mpeg1video -s 1024x768 http://127.0.0.1:8081/supersecret/" + startKey;
        System.out.println(current + "====cmdStr===" + cmdStr);
        String start1 = manager.start(tasker, cmdStr);
        //查询全部
        Collection infoList = manager.queryAll();
        System.out.println(infoList);
        return result;
    }

    /**
     * 开始推流 testPreview
     *
     * @return 返回结果集{msg:"",tasker:""}
     */
    public String testPreview1(String ip, String port, String userName, String passWord, String channels, String startKey) throws Exception {
        if (manager == null) {
            manager = new FFmpegManagerImpl();
        }
        Map<String, String> result = new HashMap<>();
        int code = 0;
        String[] resultNames = {"成功", "初始化失败", "注册前请关闭预览", "注册失败", "通道获取失败"};
        String tasker = "xxxxx";
        long startTime = System.currentTimeMillis();
        System.out.println("开始执行");
        String current = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date());

        //主码流播放
        Map<String, String> map = new HashMap<>();
        map.put("appName", tasker);//进程名
//        map.put("input", "rtsp://admin:pwd666@192.168.2.177:554/h264/ch1/main/av_stream?starttime=20221118162821Z&endtime=20221118163821Z");//组装rtsp流
        map.put("input", "rtsp://" + userName + ":" + passWord + "@" + ip + ":" + port + "/Streaming/Channels/" + channels + "01");//组装rtsp流
        map.put("output", "http://127.0.0.1:8081/supersecret/" + startKey);//rtmp流.live为nginx-rtmp的配置
        map.put("q", "0");
        map.put("f", "mpegts");
        map.put("codec:v", "mpeg1video");
        map.put("s", "1024x768");
        // 执行任务，id就是appName，如果执行失败返回为null
        String start = manager.start(map);

//原始命令
//        Map<String,String> map = new HashMap<>();
//        map.put("appName", appName);//进程名
//        map.put("input", "rtsp://admin:pwd666@192.168.2.177:554/h264/ch1/main/av_stream?starttime=20221118162821Z&endtime=20221118163821Z");//组装rtsp流
//        map.put("output", "rtmp://localhost:1935/live/");//rtmp流.live为nginx-rtmp的配置
//        map.put("codec", "h264");
//        map.put("fmt", "flv");
//        map.put("fps", "60");
//        map.put("rs", "1920x1080");
//        map.put("twoPart", "1");


        //查询全部
        Collection infoList = manager.queryAll();
        System.out.println(infoList + "========");
        return start;
    }

}  
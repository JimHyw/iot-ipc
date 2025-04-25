package com.example.demo.api;


import com.example.demo.ApplicationService;
import com.example.demo.dto.response.IpcDTO;
import com.example.demo.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/testCtlNew")
public class TestCtlNew {
    @Autowired
//    private RtspToMP4 rtspToMP4;

    /**
     *
     * @param dto 摄像头对象
     * @return
     */
    private static String getPriewCmd(IpcDTO dto, String playKey) {
        String cmdStr = "";
        String userName = dto.getUserName();//登录名
        String passWord = dto.getPassWord();//登录密码
        String ip = dto.getIp();//ip
        String rtspPort = dto.getRtspPort();//rtsp端口
        String channels = dto.getChannels();//通道号
        String resolution = dto.getResolution();//分辨率
//        String startKey = dto.getStartKey();//播放标识
        String wsIP = dto.getWsIP();//websocketIp
        String httpIP = dto.getHttpIP();//httpIP
//        String wsPort = dto.getWsPort();//websocket端口
        String httpPort = dto.getHttpPort();//http端口
//        String devCode = dto.getDevCode();//摄像头唯一标识


        //也就是说-vcodec和-codec:v等价?

        String sb = "ffmpeg" +
                " -i" +
                " \"rtsp://" +
                userName +
                ":" +
                passWord +
                "@" +
                ip +
                ":" +
                rtspPort +
                "/Streaming" +
                "/Channels/" +
                channels +
                "01\"" +
                " -q" +
                " 0" +
                " -f" +
                " mpegts" +
//        sb.append(" -codec:v");
                " -vcodec" +
                " mpeg1video" +
                " -s " +
                resolution +
                " http://" +
                httpIP +
                ":" +
                httpPort +
                "/supersecret/" +
                playKey;

        cmdStr = "ffmpeg  -i \"rtsp://" + userName + ":" + passWord + "@" + ip + ":" + rtspPort + "/Streaming/Channels/" + channels
                + "01\" -q 0 -f mpegts -codec:v mpeg1video -s " + resolution + " http://" + httpIP + ":" + httpPort + "/supersecret/" + playKey;


//        cmdStr = "ffmpeg  -i \"rtsp://"+userName+":"+passWord+"@"+ip+":"+rtspPort+"/Streaming/Channels/"+channels+"01\" -q 0 -f mpegts -codec:v mpeg1video -s "+resolution+" http://"+httpIP+":"+httpPort+"/supersecret/"+playKey;
//        cmdStr = "ffmpeg  -i \"rtsp://"+userName+":"+passWord+"@"+ip+":"+port+"/Streaming/Channels/"+channels+"01\" -q 0 -f mpegts -codec:v mpeg1video -s "+resolution+" http://127.0.0.1:8081/supersecret/"+startKey;
//        System.out.println("====cmdStr==="+cmdStr);
        return sb;
//        return cmdStr;
    }

    /**
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param dto       摄像头对象
     * @return
     */
    private static String getPlaybackCmd(String startTime, String endTime, IpcDTO dto, String playKey) {
        String cmdStr = "";
        String userName = dto.getUserName();//登录名
        String passWord = dto.getPassWord();//登录密码
        String ip = dto.getIp();//ip
        String rtspPort = dto.getRtspPort();//rtsp端口
        String channels = dto.getChannels();//通道号
        String resolution = dto.getResolution();//分辨率
//        String startKey = dto.getStartKey();//播放标识
        String wsIP = dto.getWsIP();//websocketIp
        String httpIP = dto.getHttpIP();//httpIP
//        String wsPort = dto.getWsPort();//websocket端口
        String httpPort = dto.getHttpPort();//http端口
//        String devCode = dto.getDevCode();//摄像头唯一标识
//        ffmpeg -rtsp_transport tcp -i "rtsp://admin:pwd235@192.168.2.141:554/Streaming/tracks/101?starttime=20221122T105010Z&endtime=20221122T110510Z" -q 0 -f mpegts -codec:v mpeg1video -s 480x320 http://127.0.0.1:8081/supersecret/back141


        String sb = "ffmpeg" +
                " -rtsp_transport" +
                " tcp" +
                " -i" +
                " \"rtsp://" +
                userName +
                ":" +
                passWord +
                "@" +
                ip +
                ":" +
                rtspPort +
                "/Streaming" +
                "/tracks/" +
                channels +
//        sb.append("01\"");
                "01" +
                "?" +
                "starttime" +
                "=" +
                startTime +
                "&" +
                "endtime" +
                "=" +
                endTime +
                "\"" +
                " -q" +
                " 0" +
                " -f" +
                " mpegts" +
//        sb.append(" -codec:v");
                " -vcodec" +
                " mpeg1video" +
                " -s " +
                resolution +
                " http://" +
                httpIP +
                ":" +
                httpPort +
                "/supersecret/" +
                playKey;


        cmdStr = "ffmpeg -rtsp_transport tcp -i \"rtsp://" + userName + ":" + passWord + "@" + ip + ":" + rtspPort + "/Streaming/tracks/" + channels + "01?starttime=" + startTime + "&endtime=" + endTime + "\" -q 0 -f mpegts -codec:v mpeg1video -s " + resolution + " http://" + httpIP + ":" + httpPort + "/supersecret/" + playKey;
//        System.out.println("====cmdStr==="+cmdStr);
        return sb;
//        return cmdStr;
    }

    @ResponseBody
    @RequestMapping("/big1")
    public Result<Map<String, String>> big1(HttpServletResponse arg1, String playKey) {
        System.out.println("====playKey=============================================" + playKey);
        String devCode = "";
        if (playKey.contains("$")) {
            devCode = playKey.substring(0, playKey.indexOf("$"));
        }
        System.out.println("====devCode=============================================" + devCode);


        arg1.setContentType("text/json; charset=utf-8");
        arg1.setHeader("Access-Control-Allow-Origin", "*");
        //先关闭ffmpeg进程
        closesFfmpeg(arg1);
        //再重新推流
        int i = 0;
        while (i <= 5000) {
            i++;
        }
        Result<Map<String, String>> result = new Result<>();
        Map<String, String> resultMap = new HashMap<>();
        String msg = "预览推流失败";
        int code = -1;
        try {

            long startTime = System.currentTimeMillis();
            System.out.println("开始执行");
            String current = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date());
            System.out.println("====开始时间===" + current);
            System.out.println("====devCode==-------------------------------------------=" + devCode);
            IpcDTO ipc = ApplicationService.ipcDTOMap.get(devCode);//根据设备编号获取摄像头信息
            ipc.setResolution("1366x768");//重置大屏分辨率

            String randomStr = RandomUtil.getRandomNumber(4);
            playKey = devCode + "$" + randomStr;
            String cmdStr = getPriewCmd(ipc, playKey);

            System.out.println("====cmdStr===" + cmdStr);
            Runtime rt = Runtime.getRuntime();
            Process process = rt.exec(cmdStr);
            //必须加上这两个，不然推流会停止
            new Thread(new DealProcessStream(process.getInputStream())).start();
            new Thread(new DealProcessStream(process.getErrorStream())).start();
            resultMap.put("url", "ws://" + ipc.getWsIP() + ":" + ipc.getWsPort() + "/" + playKey);
            System.out.println("执行结束");


            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
//                        process.waitFor();

                        Integer status = process.waitFor();
                        if (status == 0) {
                            System.out.println("waitFor=====================执行结束");
                        }
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

    @ResponseBody
    @RequestMapping("/ipc4")
    public Result<Map<String, String>> ipc4(HttpServletResponse arg1) {
        System.out.println("====ipc4=============================================");
        arg1.setContentType("text/json; charset=utf-8");
        arg1.setHeader("Access-Control-Allow-Origin", "*");
        //先关闭ffmpeg进程
        closesFfmpeg(arg1);
        //再重新推流
        int i = 0;
        while (i <= 5000) {
            i++;
        }
        Result<Map<String, String>> result = new Result<>();
        Map<String, String> resultMap = new HashMap<>();
        String msg = "预览推流失败";
        int code = -1;
        try {

            long startTime = System.currentTimeMillis();
            System.out.println("开始执行");
            String current = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date());
            System.out.println("====开始时间===" + current);

            Map<String, IpcDTO> ipcDTOMap = ApplicationService.ipcDTOMap;
            Set<String> keys = ipcDTOMap.keySet();
            List<IpcDTO> ipcs = new ArrayList<>();

            Integer j = 1;
            for (String key : keys) {
                ipcs.add(ipcDTOMap.get(key));
                if (j >= 4) {
                    break;
                }
                j++;
            }
            ipcs.forEach(item -> item.setResolution("480x320"));
            Integer k = 1;
            Map<String, Process> processMap = new HashMap<>();
            if (ipcs != null && ipcs.size() > 0) {
                for (IpcDTO dto : ipcs) {
                    String devCode = dto.getDevCode();
                    String randomStr = RandomUtil.getRandomNumber(4);
                    String playKey = devCode + "$" + randomStr;
                    String cmdStr = getPriewCmd(dto, playKey);
                    System.out.println("====cmdStr===" + cmdStr);
                    Runtime rt = Runtime.getRuntime();
                    Process process = rt.exec(cmdStr);
                    processMap.put("process" + k, process);
                    //必须加上这两个，不然推流会停止
                    new Thread(new DealProcessStream(process.getInputStream())).start();
                    new Thread(new DealProcessStream(process.getErrorStream())).start();
                    resultMap.put("url" + k, "ws://" + dto.getWsIP() + ":" + dto.getWsPort() + "/" + playKey);
                    System.out.println("执行结束");
                    k++;
                }
            }


            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (processMap != null && processMap.size() > 0) {
                            Set<String> processKeys = processMap.keySet();
                            for (String processKey : processKeys) {
                                Process process = processMap.get(processKey);
                                Integer status = process.waitFor();
                                if (status == 0) {
                                    System.out.println("waitFor=====================执行结束");
                                }
                            }
                        }
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


    @ResponseBody
    @RequestMapping("/playBack")
    public Result<Map<String, String>> playBack(HttpServletResponse arg1, String playKey, String startTime, String endTime) {
        System.out.println("====playKey=============================================" + playKey);
        String devCode = playKey.substring(0, playKey.indexOf("$"));
        System.out.println("====devCode=============================================" + devCode);
        System.out.println("====startTime=============================================" + startTime);
        System.out.println("====endTime=============================================" + endTime);
        try {
            startTime = TimeUtil.getTime(startTime);
            endTime = TimeUtil.getTime(endTime);
        } catch (Exception e) {
            e.printStackTrace();
        }

        arg1.setContentType("text/json; charset=utf-8");
        arg1.setHeader("Access-Control-Allow-Origin", "*");
        //先关闭ffmpeg进程
        closesFfmpeg(arg1);
        //再重新推流
        int i = 0;
        while (i <= 5000) {
            i++;
        }
        Result<Map<String, String>> result = new Result<>();
        Map<String, String> resultMap = new HashMap<>();
        String msg = "回放推流失败";
        int code = -1;
        try {

            IpcDTO ipc = ApplicationService.ipcDTOMap.get(devCode);//根据设备编号获取摄像头信息
            ipc.setResolution("1024x768");//重置大屏分辨率


            System.out.println("开始执行");
            long startTime1 = System.currentTimeMillis();
            String current = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date());
//            System.out.println("====开始时间==="+current);
            String randomStr = RandomUtil.getRandomNumber(4);
            playKey = devCode + "$" + randomStr;
            String cmdStr = getPlaybackCmd(startTime, endTime, ipc, playKey);
            System.out.println("====cmdStr===" + cmdStr);
            Runtime rt = Runtime.getRuntime();
            Process process = rt.exec(cmdStr);
            //必须加上这两个，不然推流会停止
            new Thread(new DealProcessStream(process.getInputStream())).start();
            new Thread(new DealProcessStream(process.getErrorStream())).start();
            resultMap.put("url", "ws://" + ipc.getWsIP() + ":" + ipc.getWsPort() + "/" + playKey);
            System.out.println("执行结束");


            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        process.waitFor();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            });

            System.out.println("开启ffmpeg进程耗时：" + (System.currentTimeMillis() - startTime1));

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

}
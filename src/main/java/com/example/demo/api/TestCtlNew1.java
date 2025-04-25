package com.example.demo.api;


import cc.eguid.FFmpegCommandManager.FFmpegManager;
import cc.eguid.FFmpegCommandManager.entity.TaskEntity;
import com.example.demo.ApplicationService;
import com.example.demo.dto.response.IpcDTO;
import com.example.demo.dto.response.TaskEntityDTO;
import com.example.demo.utils.RandomUtil;
import com.example.demo.utils.Result;
import com.example.demo.utils.TimeUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 进程管理
 */
@RestController
@RequestMapping("/testCtlNew1")
public class TestCtlNew1 {
    static FFmpegManager manager = ApplicationService.manager;

    /**
     * 获取原始rtsp视频流
     *
     * @param dto
     * @return
     */
    private static String getRtspUrl(IpcDTO dto) {
        String userName = dto.getUserName();//登录名
        String passWord = dto.getPassWord();//登录密码
        String ip = dto.getIp();//ip
        String rtspPort = dto.getRtspPort();//rtsp端口
        String channels = dto.getChannels();//通道号


        String sb = "rtsp://" +
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
                "01";
        return sb;
    }

    /**
     * 获取播放视频流
     *
     * @param dto
     * @param playKey
     * @return
     */
    private static String getPlayUrl(IpcDTO dto, String playKey) {
        String httpIP = dto.getHttpIP();//httpIP
        String httpPort = dto.getHttpPort();//http端口
        //也就是说-vcodec和-codec:v等价?
        String sb = " http://" +
                httpIP +
                ":" +
                httpPort +
                "/supersecret/" +
                playKey;

        return sb;
    }

    private static String buildPriewCmd(IpcDTO dto, String playKey) {
        String userName = dto.getUserName();//登录名
        String passWord = dto.getPassWord();//登录密码
        String ip = dto.getIp();//ip
        String rtspPort = dto.getRtspPort();//rtsp端口
        String channels = dto.getChannels();//通道号
        String resolution = dto.getResolution();//分辨率
        String wsIP = dto.getWsIP();//websocketIp
        String httpIP = dto.getHttpIP();//httpIP
        String httpPort = dto.getHttpPort();//http端口
        //也就是说-vcodec和-codec:v等价?


        String sb = "ffmpeg" +
                " -i" +
                " rtsp://" +
//        sb.append(" \"rtsp://");
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
                "01" +
//        sb.append("01\"");
                " -q" +
                " 0" +
                " -f" +
                " mpegts" +
                " -codec:v" +
//        sb.append(" -vcodec");
                " mpeg1video" +
                " -s " +
                resolution +
                " http://" +
                httpIP +
                ":" +
                httpPort +
                "/supersecret/" +
                playKey;

        return sb;
    }

    /**
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param dto       摄像头对象
     * @return
     */
    private static String buildPlaybackCmd(String startTime, String endTime, IpcDTO dto, String playKey) {
        String userName = dto.getUserName();//登录名
        String passWord = dto.getPassWord();//登录密码
        String ip = dto.getIp();//ip
        String rtspPort = dto.getRtspPort();//rtsp端口
        String channels = dto.getChannels();//通道号
        String resolution = dto.getResolution();//分辨率
        String wsIP = dto.getWsIP();//websocketIp
        String httpIP = dto.getHttpIP();//httpIP
        String httpPort = dto.getHttpPort();//http端口

        String sb = "ffmpeg" +
                " -rtsp_transport" +
                " tcp" +
                " -i" +
                " rtsp://" +
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
                "01" +
                "?" +
                "starttime" +
                "=" +
                startTime +
                "&" +
                "endtime" +
                "=" +
                endTime +
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

        return sb;
    }


    @ResponseBody
    @RequestMapping("/big1")
    public Result<Map<String, String>> big1(HttpServletResponse arg1, String playKey) {
        System.out.println("====playKey=============================================" + playKey);
        String devCode = "";
        if (playKey.contains("_")) {
            devCode = playKey.substring(0, playKey.indexOf("_"));
        }
        System.out.println("====devCode=============================================" + devCode);


        arg1.setContentType("text/json; charset=utf-8");
        arg1.setHeader("Access-Control-Allow-Origin", "*");
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
            IpcDTO dto = ApplicationService.ipcDTOMap.get(devCode);//根据设备编号获取摄像头信息
            Map<String, IpcDTO> ipcDTOMap = ApplicationService.ipcDTOMap;
            if (dto != null) {
                dto.setResolution("1366x768");//重置大屏分辨率
            }

            String playType = dto.getPlayType();//播放标识:预览=preview,回放=back
            //                    String  randomStr = RandomUtil.getRandomNumber();
            playKey = devCode + "_" + playType;

            String beginTime = "20221128T1112000Z";
            String endTime = "20221129T1212000Z";

            String cmdStr = "";
//            if(StringUtils.equals(playType,"preview")){
            if (playType.equals("preview")) {
                cmdStr = buildPriewCmd(dto, playKey);
            } else {
                cmdStr = buildPlaybackCmd(beginTime, endTime, dto, playKey);
            }
            String taskId = "task_" + playKey;//进程id
            TaskEntityDTO taskEntityDTO = new TaskEntityDTO();
            //先从taskMap中检查是否存在该进程，若存在，则取出
            Map<String, TaskEntityDTO> taskMap = ApplicationService.taskMap;
            boolean taskFlag = false;
            if (taskMap != null && taskMap.size() > 0) {
                taskFlag = ApplicationService.taskMap.containsKey(taskId);
            }
            if (taskFlag) {
                taskEntityDTO = ApplicationService.taskMap.get(taskId);
            } else {
                //把信息添加到进程Map中
                String rtspUrl = getRtspUrl(dto);//rtsp推送流
                String playUrl = getPlayUrl(dto, playKey);//播放流:可以是预览或回

                taskEntityDTO.setRtspUrl(rtspUrl);
                taskEntityDTO.setPlayUrl(playUrl);
                taskEntityDTO.setCmdStr(cmdStr);
                taskEntityDTO.setTaskId(taskId);
                IpcDTO ipc = ipcDTOMap.get(devCode);
                String ipcName = ipc.getDevName();
                taskEntityDTO.setIpcName(ipcName);
                ApplicationService.taskMap.put(taskId, taskEntityDTO);
                String start = manager.start(taskId, cmdStr);
                System.out.println("====start=taskId==" + start);
            }
            resultMap.put("url", "ws://" + dto.getWsIP() + ":" + dto.getWsPort() + "/" + playKey);
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
//        closesFfmpeg(arg1);
        //再重新推流
//        int i =0;
//        while (i<=5000){
//            i++;
//        }
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
//            Integer j = 1;
            for (String key : keys) {
                Random randNum = new Random();
                boolean randomRes = randNum.nextBoolean();
                if (randomRes) {
                    ipcs.add(ipcDTOMap.get(key));
                }
//                ipcs.add(ipcDTOMap.get(key));
                if (ipcs.size() >= 4) {
                    break;
                }
//                j++;
            }
            System.out.println("====ipcs数量=======================" + ipcs.size());
            ipcs.forEach(item -> item.setResolution("480x320"));
            Integer k = 1;
//            Map<String,Process> processMap = new HashMap<>();
            if (ipcs != null && ipcs.size() > 0) {
                for (IpcDTO dto : ipcs) {
                    String devCode = dto.getDevCode();//设备编号
                    String playType = dto.getPlayType();//播放标识:预览=preview,回放=back
                    String playKey = devCode + "_" + playType;

                    String beginTime = "20221122T025200Z";
                    String endTime = "20221123T025200Z";

                    String cmdStr = "";
                    if (playType.equals("preview")) {
                        cmdStr = buildPriewCmd(dto, playKey);
                    } else {
                        cmdStr = buildPlaybackCmd(beginTime, endTime, dto, playKey);
                    }

                    String taskId = "task_" + playKey;//进程id
                    TaskEntityDTO taskEntityDTO = new TaskEntityDTO();
                    //先从taskMap中检查是否存在该进程，若存在，则取出
                    Map<String, TaskEntityDTO> taskMap = ApplicationService.taskMap;
                    boolean taskFlag = false;
                    if (taskMap != null && taskMap.size() > 0) {
                        taskFlag = ApplicationService.taskMap.containsKey(taskId);
                    }
                    if (taskFlag) {
                        taskEntityDTO = ApplicationService.taskMap.get(taskId);
                    } else {
                        //把信息添加到进程Map中
                        String rtspUrl = getRtspUrl(dto);//rtsp推送流
                        String playUrl = getPlayUrl(dto, playKey);//播放流:可以是预览或回

                        taskEntityDTO.setRtspUrl(rtspUrl);
                        taskEntityDTO.setPlayUrl(playUrl);
                        taskEntityDTO.setCmdStr(cmdStr);
                        taskEntityDTO.setTaskId(taskId);
                        IpcDTO ipc = ipcDTOMap.get(devCode);
                        String ipcName = ipc.getDevName();
                        taskEntityDTO.setIpcName(ipcName);
                        ApplicationService.taskMap.put(taskId, taskEntityDTO);
                        String start = manager.start(taskId, cmdStr);
                        System.out.println("====start=taskId==" + start);
                    }
                    resultMap.put("url" + k, "ws://" + dto.getWsIP() + ":" + dto.getWsPort() + "/" + playKey);
                    System.out.println("执行结束");
                    k++;
                }
            }


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
    @RequestMapping("/playBack")
    public Result<Map<String, String>> playBack(HttpServletResponse arg1, String playKey, String startTime, String endTime) {
        System.out.println("====playKey============================195D1_back=================" + playKey);
        boolean has_ = playKey.contains("_");
        String devCode = "";
        if (has_) {
            devCode = playKey.substring(0, playKey.indexOf("_"));
        }
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
        Result<Map<String, String>> result = new Result<>();
        Map<String, String> resultMap = new HashMap<>();
        String msg = "回放推流失败";
        int code = -1;
        try {
            Map<String, IpcDTO> ipcDTOMap = ApplicationService.ipcDTOMap;
            IpcDTO dto = ApplicationService.ipcDTOMap.get(devCode);//根据设备编号获取摄像头信息
            dto.setResolution("1024x768");//重置大屏分辨率
            System.out.println("开始执行");
            long startTime1 = System.currentTimeMillis();

            String playType = "back";//播放标识:预览=preview,回放=back
            String randomStr = RandomUtil.getRandomNumber(4);
            playKey = devCode + "_" + playType + "_" + randomStr;
            String cmdStr = buildPlaybackCmd(startTime, endTime, dto, playKey);
            //直接开新进程
            String taskId = "task_" + playKey;//进程id
            TaskEntityDTO taskEntityDTO = new TaskEntityDTO();
            //把信息添加到进程Map中
            String rtspUrl = getRtspUrl(dto);//rtsp推送流
            String playUrl = getPlayUrl(dto, playKey);//播放流:可以是预览或回

            taskEntityDTO.setRtspUrl(rtspUrl);
            taskEntityDTO.setPlayUrl(playUrl);
            taskEntityDTO.setCmdStr(cmdStr);
            taskEntityDTO.setTaskId(taskId);
            IpcDTO ipc = ipcDTOMap.get(devCode);
            String ipcName = ipc.getDevName();
            taskEntityDTO.setIpcName(ipcName);
            ApplicationService.taskMap.put(taskId, taskEntityDTO);
            String start = manager.start(taskId, cmdStr);
            System.out.println("====start=taskId==" + start);
            resultMap.put("url", "ws://" + dto.getWsIP() + ":" + dto.getWsPort() + "/" + playKey);
            resultMap.put("myTask", start);//把自身进程设置进去
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
    /**
     * 查询某进程状态
     * @return
     */

    /**
     * 查询所有进程
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/taskQueryAll")
    public Result<ArrayList<TaskEntityDTO>> taskQueryAll() {
        Result<ArrayList<TaskEntityDTO>> result = new Result<>();
        Collection<TaskEntity> infoList = manager.queryAll();
        ArrayList<TaskEntityDTO> infoList1 = new ArrayList<>();

        if (infoList != null && infoList.size() > 0) {
            for (TaskEntity task : infoList) {
                TaskEntityDTO dto = new TaskEntityDTO();
                Process process = task.getProcess();
                if (process != null) {
                    String taskId = task.getId();
                    dto = ApplicationService.taskMap.get(taskId);
                } else {
                    dto.setTaskId(null);
                }
                infoList1.add(dto);
            }
        }
        result.setData(infoList1);
        System.out.println("所有ffmpeg任务：" + infoList1);
        System.out.println("result：" + result);
        return result;

    }

    /**
     * 判断当前推流进程是否存在
     *
     * @param taskId 进程名称
     * @return status=true存活，status=false已销毁
     */
    @ResponseBody
    @RequestMapping("/taskerIsRun")
    public Result<Boolean> taskerIsRun(String taskId) {
        Result<Boolean> result = new Result<>();
        Boolean status = manager.queryAll().size() > 0 && manager.query(taskId) != null;
        System.out.println("status：-----------" + status);
        result.setData(status);
        return result;
    }

    //关闭由manager管理的ffmpeg进程
    @ResponseBody
    @RequestMapping("/closesFfmpeg")
    public Result<String> stopAllTask(HttpServletResponse arg1) {
        Result<String> result = new Result<>();

        try {
            long startTime = System.currentTimeMillis();
            Collection<TaskEntity> infoList = manager.queryAll();
            System.out.println("所有ffmpeg进程：-----------" + infoList);
            int stopStatus = manager.stopAll();
            ApplicationService.taskMap.clear();//清空
            if (stopStatus == 1) {
                System.out.println("所有ffmpeg进程已停止：-----------" + stopStatus);
            }
            System.out.println("耗时：---------------" + (System.currentTimeMillis() - startTime));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return result;

    }

    /**
     * 关闭某进程
     *
     * @param taskId 进程id
     * @return
     */
    @ResponseBody
    @RequestMapping("/stopTaskId")
    public Result<Boolean> stopTaskId(String taskId) {
        Result<Boolean> result = new Result<>();
        TaskEntity task = manager.query(taskId);
        if (task != null) {
            System.out.println("task：-----------" + task);
            boolean stopStatus = manager.stop(taskId);
            Map<String, TaskEntityDTO> taskMap = ApplicationService.taskMap;
            boolean taskFlag = false;
            if (taskMap != null && taskMap.size() > 0) {
                taskFlag = ApplicationService.taskMap.containsKey(taskId);
                if (taskFlag) {
                    ApplicationService.taskMap.remove(taskId);//从进程Map中移除该taskId
                }
            }
            result.setData(stopStatus);
            System.out.println("ffmpeg任务是否已关闭：" + stopStatus);
        }
//        arg1.setContentType("text/json; charset=utf-8");
//        arg1.setHeader("Access-Control-Allow-Origin", "*");
        System.out.println("result：" + result);
        return result;

    }

}
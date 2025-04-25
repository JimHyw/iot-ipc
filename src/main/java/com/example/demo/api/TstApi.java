package com.example.demo.api;

import cc.eguid.FFmpegCommandManager.FFmpegManager;
import com.example.demo.ApplicationService;
import com.example.demo.utils.ProcessUtil;
import com.example.demo.utils.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/tst")
public class TstApi {
    static FFmpegManager manager = ApplicationService.manager;

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

    @ResponseBody
    @RequestMapping("/palyview")
    public Result<String> palyview(HttpServletResponse arg1) {
        closesFfmpeg(arg1);
        Result<String> result = new Result<>();
        String msg = "预览推流失败";
        int code = -1;

        String cmdStr1 = "ffmpeg  -rtsp_transport  tcp  -i  rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mp4  -q 0  -f mpegts  -codec:v  mpeg1video  -s  480x320 http://127.0.0.1:8081/supersecret/palyview ";
        System.out.println("====cmdStr1===" + cmdStr1);

        String taskId = "task_123";//进程id
        String start = manager.start(taskId, cmdStr1);
        System.out.println("====start=taskId==" + start);
        msg = "预览推流成功";
        code = 0;

        result.setMsg(msg);
        result.setCode(code);

        arg1.setContentType("text/json; charset=utf-8");
        arg1.setHeader("Access-Control-Allow-Origin", "*");
        System.out.println("result：" + result);
        return result;
    }
}

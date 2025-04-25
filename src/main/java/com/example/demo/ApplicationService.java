package com.example.demo;

import cc.eguid.FFmpegCommandManager.FFmpegManager;
import cc.eguid.FFmpegCommandManager.FFmpegManagerImpl;
import com.example.demo.dto.response.IpcDTO;
import com.example.demo.dto.response.TaskEntityDTO;
import com.example.demo.utils.ProcessUtil;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ApplicationService implements CommandLineRunner, DisposableBean {
    //ffmpeg工具管理
    public final static FFmpegManager manager = new FFmpegManagerImpl();

    //摄像头信息
    public static Map<String, IpcDTO> ipcDTOMap = new HashMap<String, IpcDTO>();
    //进程信息
    public static Map<String, TaskEntityDTO> taskMap = new HashMap<>();

    @Override
    public void run(String... args) throws Exception {


        System.out.println("springboot启动后执行-----");

        //默认4路摄像头分辨率：480x320；9路为：300x200
        List<IpcDTO> ipcPreviews = new ArrayList<>();//预览摄像头
        List<IpcDTO> ipcPlaybacks = new ArrayList<>();//回放
        //摄像头预览：
        IpcDTO ipc195_1 = new IpcDTO("admin", "pwd666", "192.168.2.195", "554", "1", "480x320", "preview", "127.0.0.1", "8181", "127.0.0.1", "8182", "195D1", "移动营业厅出入大门");
        IpcDTO ipc195_2 = new IpcDTO("admin", "pwd666", "192.168.2.195", "554", "2", "480x320", "preview", "127.0.0.1", "8181", "127.0.0.1", "8182", "195D2", "移动营业厅业务柜台");
        IpcDTO ipc195_3 = new IpcDTO("admin", "pwd666", "192.168.2.195", "554", "3", "480x320", "preview", "127.0.0.1", "8181", "127.0.0.1", "8182", "195D3", "移动营业厅手机维修");
        IpcDTO ipc195_4 = new IpcDTO("admin", "pwd666", "192.168.2.195", "554", "4", "480x320", "preview", "127.0.0.1", "8181", "127.0.0.1", "8182", "195D4", "营业厅手机维修");
        IpcDTO ipc195_5 = new IpcDTO("admin", "pwd666", "192.168.2.195", "554", "5", "480x320", "preview", "127.0.0.1", "8181", "127.0.0.1", "8182", "195D5", "集装装后面通道");
        IpcDTO ipc195_6 = new IpcDTO("admin", "pwd666", "192.168.2.195", "554", "6", "480x320", "preview", "127.0.0.1", "8181", "127.0.0.1", "8182", "195D6", "宝山公司西侧消");
//        IpcDTO ipc195_7 = new IpcDTO("admin", "pwd666", "192.168.2.195", "554", "7", "480x320", "preview", "127.0.0.1", "8181","127.0.0.1", "8182", "195D7", "萤石云");//===============可用
//        IpcDTO ipc195_8 = new IpcDTO("admin", "pwd666", "192.168.2.195", "554", "8", "480x320", "preview", "127.0.0.1", "8181", "127.0.0.1", "8182","195D8", "无无无无");
        IpcDTO ipc195_9 = new IpcDTO("admin", "pwd666", "192.168.2.195", "554", "9", "480x320", "preview", "127.0.0.1", "8181", "127.0.0.1", "8182", "195D9", "宝山公司2楼研");
        IpcDTO ipc195_10 = new IpcDTO("admin", "pwd666", "192.168.2.195", "554", "10", "480x320", "preview", "127.0.0.1", "8181", "127.0.0.1", "8182", "195D10", "宝山公司3楼展厅");
        IpcDTO ipc195_11 = new IpcDTO("admin", "pwd666", "192.168.2.195", "554", "11", "480x320", "preview", "127.0.0.1", "8181", "127.0.0.1", "8182", "195D11", "宝山公司餐厅通道");
        IpcDTO ipc195_12 = new IpcDTO("admin", "pwd666", "192.168.2.195", "554", "12", "480x320", "preview", "127.0.0.1", "8181", "127.0.0.1", "8182", "195D12", "宝山公司 展厅半球");
        IpcDTO ipc195_13 = new IpcDTO("admin", "pwd666", "192.168.2.195", "554", "13", "480x320", "preview", "127.0.0.1", "8181", "127.0.0.1", "8182", "195D13", "Camera 01");


        IpcDTO ipc141_1 = new IpcDTO("admin", "pwd235", "192.168.2.141", "554", "1", "480x320", "preview", "127.0.0.1", "8181", "127.0.0.1", "8182", "141D1", "研发部测试摄像头");
        IpcDTO ipc141_2 = new IpcDTO("admin", "pwd235", "192.168.2.141", "554", "2", "480x320", "preview", "127.0.0.1", "8181", "127.0.0.1", "8182", "141D2", "IPCamera 04");
//        IpcDTO ipc141_3 = new IpcDTO("admin", "pwd235", "192.168.2.141", "554", "3", "480x320", "preview", "127.0.0.1", "8181", "127.0.0.1", "8182","141D3", "IPCamera 04");
//        IpcDTO ipc141_4 = new IpcDTO("admin", "pwd235", "192.168.2.141", "554", "4", "480x320", "preview", "127.0.0.1", "8181", "127.0.0.1", "8182","141D4", "IPCamera 04");
        IpcDTO ipc141_5 = new IpcDTO("admin", "pwd235", "192.168.2.141", "554", "5", "480x320", "preview", "127.0.0.1", "8181", "127.0.0.1", "8182", "141D5", "宝山公司会议室");
        IpcDTO ipc141_6 = new IpcDTO("admin", "pwd235", "192.168.2.141", "554", "6", "480x320", "preview", "127.0.0.1", "8181", "127.0.0.1", "8182", "141D6", "移动营业厅厅手");

        //摄像头回放：
        IpcDTO ipc15 = new IpcDTO("admin", "pwd666", "192.168.2.195", "554", "1", "480x320", "back", "127.0.0.1", "8181", "127.0.0.1", "8182", "195D1", "移动营业厅出入大门");
        IpcDTO ipc16 = new IpcDTO("admin", "pwd666", "192.168.2.195", "554", "2", "480x320", "back", "127.0.0.1", "8181", "127.0.0.1", "8182", "195D2", "移动营业厅业务柜台");
        IpcDTO ipc17 = new IpcDTO("admin", "pwd666", "192.168.2.195", "554", "3", "480x320", "back", "127.0.0.1", "8181", "127.0.0.1", "8182", "195D3", "移动营业厅手机维修");
        IpcDTO ipc18 = new IpcDTO("admin", "pwd666", "192.168.2.195", "554", "4", "480x320", "back", "127.0.0.1", "8181", "127.0.0.1", "8182", "195D4", "营业厅手机维修");
        IpcDTO ipc19 = new IpcDTO("admin", "pwd666", "192.168.2.195", "554", "5", "480x320", "back", "127.0.0.1", "8181", "127.0.0.1", "8182", "195D5", "集装装后面通道");
        IpcDTO ipc20 = new IpcDTO("admin", "pwd666", "192.168.2.195", "554", "6", "480x320", "back", "127.0.0.1", "8181", "127.0.0.1", "8182", "195D6", "宝山公司西侧消");
        IpcDTO ipc21 = new IpcDTO("admin", "pwd666", "192.168.2.195", "554", "7", "480x320", "back", "127.0.0.1", "8181", "127.0.0.1", "8182", "195D7", "萤石云");
//        IpcDTO ipc21 = new IpcDTO("admin", "pwd666", "192.168.2.195", "554", "7", "480x320", "back", "127.0.0.1", "8181","127.0.0.1", "8182", "195D8", "萤石云");
        IpcDTO ipc22 = new IpcDTO("admin", "pwd666", "192.168.2.195", "554", "9", "480x320", "back", "127.0.0.1", "8181", "127.0.0.1", "8182", "195D9", "宝山公司2楼研");
        IpcDTO ipc23 = new IpcDTO("admin", "pwd666", "192.168.2.195", "554", "10", "480x320", "back", "127.0.0.1", "8181", "127.0.0.1", "8182", "195D10", "宝山公司3楼展厅");
        IpcDTO ipc24 = new IpcDTO("admin", "pwd666", "192.168.2.195", "554", "11", "480x320", "back", "127.0.0.1", "8181", "127.0.0.1", "8182", "195D11", "宝山公司餐厅通道");
        IpcDTO ipc25 = new IpcDTO("admin", "pwd666", "192.168.2.195", "554", "12", "480x320", "back", "127.0.0.1", "8181", "127.0.0.1", "8182", "195D12", "宝山公司 展厅半球");
        IpcDTO ipc26 = new IpcDTO("admin", "pwd666", "192.168.2.195", "554", "13", "480x320", "back", "127.0.0.1", "8181", "127.0.0.1", "8182", "195D13", "Camera 01");
        IpcDTO ipc27 = new IpcDTO("admin", "pwd235", "192.168.2.141", "554", "1", "480x320", "back", "127.0.0.1", "8181", "127.0.0.1", "8182", "141D1", "研发部测试摄像头");
        IpcDTO ipc28 = new IpcDTO("admin", "pwd235", "192.168.2.141", "554", "2", "480x320", "back", "127.0.0.1", "8181", "127.0.0.1", "8182", "141D2", "IPCamera 04");
//        IpcDTO ipc29 = new IpcDTO("admin", "pwd235", "192.168.2.141", "554", "2", "480x320", "back", "127.0.0.1", "8181", "127.0.0.1", "8182","141D3", "IPCamera 04");
//        IpcDTO ipc30 = new IpcDTO("admin", "pwd235", "192.168.2.141", "554", "2", "480x320", "back", "127.0.0.1", "8181", "127.0.0.1", "8182","141D4", "IPCamera 04");
        IpcDTO ipc31 = new IpcDTO("admin", "pwd235", "192.168.2.141", "554", "2", "480x320", "back", "127.0.0.1", "8181", "127.0.0.1", "8182", "141D5", "宝山公司会议室");
        IpcDTO ipc32 = new IpcDTO("admin", "pwd235", "192.168.2.141", "554", "2", "480x320", "back", "127.0.0.1", "8181", "127.0.0.1", "8182", "141D6", "移动营业厅厅手");


        ipcPreviews.add(ipc195_1);
        ipcPreviews.add(ipc195_2);
        ipcPreviews.add(ipc195_3);
        ipcPreviews.add(ipc195_4);
        ipcPreviews.add(ipc195_5);
        ipcPreviews.add(ipc195_6);
//        ipcPreviews.add(ipc195_7);//可用
//        ipcPreviews.add(ipc195_8);
        ipcPreviews.add(ipc195_9);
        ipcPreviews.add(ipc195_10);
        ipcPreviews.add(ipc195_11);
        ipcPreviews.add(ipc195_12);
        ipcPreviews.add(ipc195_13);
        ipcPreviews.add(ipc141_1);
        ipcPreviews.add(ipc141_2);
//        ipcPreviews.add(ipc195_3);
//        ipcPreviews.add(ipc195_4);
        ipcPreviews.add(ipc195_5);
        ipcPreviews.add(ipc195_6);


        //预览集合
        if (ipcPreviews != null && ipcPreviews.size() > 0) {
            for (IpcDTO dto : ipcPreviews) {
                ipcDTOMap.put(dto.getDevCode(), dto);
            }
        }

        ipcPlaybacks.add(ipc15);
        ipcPlaybacks.add(ipc16);
        ipcPlaybacks.add(ipc17);
        ipcPlaybacks.add(ipc18);
        ipcPlaybacks.add(ipc19);
        ipcPlaybacks.add(ipc20);
        //ipcPlaybacks.add(ipc21);
        ipcPlaybacks.add(ipc22);
        ipcPlaybacks.add(ipc23);
        ipcPlaybacks.add(ipc24);
        ipcPlaybacks.add(ipc25);
        ipcPlaybacks.add(ipc26);
        ipcPlaybacks.add(ipc27);
        ipcPlaybacks.add(ipc28);
        //ipcPlaybacks.add(ipc29);
        //ipcPlaybacks.add(ipc30);
        ipcPlaybacks.add(ipc31);
        ipcPlaybacks.add(ipc32);
        //回放集合
//        if(ipcPlaybacks!=null&&ipcPlaybacks.size()>0) {
//            for (IpcDTO dto : ipcPlaybacks) {
//                ipcDTOMap.put(dto.getDevCode(), dto);
//            }
//        }

    }

    @Override
    public void destroy() throws Exception {
        System.out.println("------------springboot关闭前执行");
        /***********************杀死由manager管理的ffmpeg进程***********************************/
//        Collection infoList=manager.queryAll();
//        System.out.println("所有ffmpeg任务："+infoList);
//        TaskEntity task = manager.query("task111");
//        System.out.println("task："+task);
//        manager.stopAll();
//        System.out.println("kkkkkk："+infoList);
        /**********************************************************/


        /***********************杀死本电脑所有ffmpeg进程***********************************/
        ProcessUtil.closeAllFFmpeg();
        /**********************************************************/
    }
}

package com.example.demo.api;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JavaPID {
    public static void main(String[] args) {
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        String name = runtime.getName();
        System.out.println("当前进程的标识为：" + name);

        int index = name.indexOf("@");

        if (index != -1) {
            int pid = Integer.parseInt(name.substring(0, index));
            System.out.println("当前进程的PID为：" + pid);
        }

        try {
            //这里休息60秒，是为了在windows管理器查看该应用程序的进程PID
//            JavaPID pid = new JavaPID();
            getFfmpegPid();
            Thread.sleep(60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    //进程获取
    public static String getFfmpegPid() throws IOException {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        // System.out.println(name);
        String[] names = name.split("@");
        int pid1 = Integer.parseInt(names[0]);
        // 创建系统进程
        ProcessBuilder pb = new ProcessBuilder("tasklist");
        Process p = pb.start();
        BufferedReader out = new BufferedReader(new InputStreamReader(new BufferedInputStream(p.getInputStream()), Charset.forName("GB2312")));
        // System.out.println("Window 系统进程列表");
        String ostr;
        List<String> pid = new ArrayList<>();
        while ((ostr = out.readLine()) != null) pid.add(ostr);
        String[] exess = {"ffmpeg.exe"};
        List<String> exes = new ArrayList<>(Arrays.asList(exess));
        String idp = "";
        idp = pid1 + ",";

        for (String ex : exes) {
            for (String s : pid) {
                String m = s.replace(" ", "");
                if (m.contains(ex)) {
                    //System.out.println(s);
                    idp += m.replace(ex, "").substring(0, m.replace(ex, "").lastIndexOf("Console")) + ",";
                    //id.add(m.replace(ex,"").substring(0,m.replace(ex,"").lastIndexOf("Console")));
                }
            }
        }

        idp = idp.substring(0, idp.length() - 1);

        System.out.println("当前ffmpeg进程的PID为===========：" + idp);
//        renderJson(Co.ok("data", Ret.ok("PID", idp)));
        return idp;
    }
}
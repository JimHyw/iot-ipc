package com.example.demo.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class KillProcessTest {
    public static void closesFfmpeg() {
        /*
         * 下面先是获取进程列表
         */
        Runtime runtime = Runtime.getRuntime();
        List tasklist = new ArrayList();
        try {
            Process process = runtime.exec("tasklist");
//            获取控制台输出
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
            String s = "";
            while ((s = br.readLine()) != null) {// 去掉第一行的空行
                if ("".equals(s)) {
                    continue;
                }
                tasklist.add(s + " ");
            }

            // 获取每列最长的长度
            String maxRow = tasklist.get(1) + "";
            //根据空格截取tasklist中双横线，并且每个maxCol[],都表明了每一个进程的镜像名称、pid、会话名、会话#、内存使用的字符串最长长度
            String[] maxCol = maxRow.split(" ");
            // 定义映像名称数组
            String[] taskName = new String[tasklist.size()];
            // 定义 PID数组
            String[] taskPid = new String[tasklist.size()];
            // 会话名数组
            String[] taskSessionName = new String[tasklist.size()];
            // 会话#数组
            String[] taskSession = new String[tasklist.size()];
            // 内存使用 数组
            String[] taskNec = new String[tasklist.size()];
//  循环tasklist
            for (int i = 0; i < tasklist.size(); i++) {
                //取出每一行的tasklist值，比如：System     4   Services    0   2,652 K
                String data = tasklist.get(i) + "";
                // 循环每一列，从 0-5
                for (int j = 0; j < maxCol.length; j++) {
                    switch (j) {
                        case 0:
                            //这里存取的值包含空格
                            taskName[i] = data.substring(0, maxCol[j].length() + 1);
                            //截取data，便于下一case取值
                            data = data.substring(maxCol[j].length() + 1);
//                            System.out.println("data="+data);
                            break;
                        case 1:
                            taskPid[i] = data.substring(0, maxCol[j].length() + 1);
                            System.out.println("taskPid=" + taskPid[i] + "   \t" + taskPid[i].length());
                            data = data.substring(maxCol[j].length() + 1);
                            break;
                        case 2:
                            taskSessionName[i] = data.substring(0, maxCol[j].length() + 1);
                            data = data.substring(maxCol[j].length() + 1);
                            break;
                        case 3:
                            taskSession[i] = data.substring(0, maxCol[j].length() + 1);
                            data = data.substring(maxCol[j].length() + 1);
                            break;
                        case 4:
                            taskNec[i] = data;
                            break;
                    }
                }
            }
            for (int i = 0; i < taskName.length; i++) {
                //打印进程列表
                //System.out.println(taskName[i]+" "+taskPid[i]+" "+taskSessionName[i]+" "+taskSession[i]+" "+taskNec[i]);
                //根据进程名称找到PID，杀掉notepad.exe
                if (taskName[i].contains("ffmpeg.exe")) {
//                if(taskName[i].contains("notepad.exe")) {
                    //杀死进程
                    Runtime.getRuntime().exec("taskkill /F /PID " + taskPid[i]);
//                    System.out.println(taskPid[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        closesFfmpeg();
    }


}
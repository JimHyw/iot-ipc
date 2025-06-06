package com.example.demo.utils;

import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * TODO:
 *
 * @Author: ZHANG
 * @create: 2021/8/27 16:11
 */
@Component
public class RtspToMP4 {

    public class In implements Runnable{
        private InputStream inputStream;

        public In(InputStream inputStream) {
            this.inputStream = inputStream;
        }
        @Override
        public void run() {
            try {
                //转成字符输入流
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "gbk");
                int len = -1;
                char[] c = new char[1024];
                //读取进程输入流中的内容
                while ((len = inputStreamReader.read(c)) != -1) {
                    String s = new String(c, 0, len);
//                    System.out.print(s);
                }
            }catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage()+"iiiiiiiiiiiiiiii");
            }
        }
    }

    public Process StartRecord(String ffmpegPath,String streamUrl, String FilePath){
        ProcessBuilder processBuilder = new ProcessBuilder();
        //定义命令内容
        List<String> command = new ArrayList<>();
        command.add(ffmpegPath);
        command.add("-rtsp_transport");
        command.add("tcp");
        command.add("-y");
        command.add("-i");
        command.add(streamUrl);
        command.add("-c");
        command.add("copy");
        command.add("-f");
        command.add("mp4");
        command.add(FilePath);
        processBuilder.command(command);
        System.out.println("脚本：" + command.toString());
        //将标准输入流和错误输入流合并，通过标准输入流读取信息
        processBuilder.redirectErrorStream(true);
        try {
            //启动进程
            Process process = processBuilder.start();
            System.out.println("开始时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
            //获取输入流
            InputStream inputStream = process.getInputStream();
            Thread inThread = new Thread(new In(inputStream));
            inThread.start();
            return process;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public Process testPreview(String ffmpegPath,String streamUrl,String ip, String port,String userName,String passWord, String channels, String startKey){
        ProcessBuilder processBuilder = new ProcessBuilder();
        //定义命令内容
        List<String> command = new ArrayList<>();
        command.add(ffmpegPath);
        command.add("-rtsp_transport");
        command.add("tcp");
        command.add("-i");
        command.add(streamUrl);
        command.add("-q");
        command.add("0");
        command.add("-f");
        command.add("mpegts");
        command.add("-codec:v");
        command.add("mpeg1video");
        command.add("-s");
        command.add("1024x768");
        command.add("http://127.0.0.1:8081/supersecret/"+startKey);
        processBuilder.command(command);
        System.out.println("脚本---------------：" + command.toString());
        //将标准输入流和错误输入流合并，通过标准输入流读取信息
        processBuilder.redirectErrorStream(true);
        try {
            //启动进程
            Process process = processBuilder.start();
            System.out.println("开始时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
            //获取输入流
            InputStream inputStream = process.getInputStream();
            Thread inThread = new Thread(new In(inputStream));
            inThread.start();
            return process;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage()+"ooooooooooooo");
        }
        return null;
    }



    public boolean stopRecord(Process process) {
        try {
            OutputStream os = process.getOutputStream();
            os.write("q".getBytes());
            // 一定要刷新
            os.flush();
            os.close();
        } catch (Exception err) {
            err.printStackTrace();
            return false;
        }
        return true;
    }
}

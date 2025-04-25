package com.example.demo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DealProcessStream extends Thread {
    private InputStream inputStream;

    public DealProcessStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void run() {
        InputStreamReader inputStreamReader = null;
        BufferedReader br = null;
        try {
            inputStreamReader = new InputStreamReader(
                    inputStream);
            br = new BufferedReader(inputStreamReader);
            // 打印信息
            String line = null;
            while ((line = br.readLine()) != null) {
//                System.out.println(line+"------------");// 是否打印信息
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            ioe.printStackTrace();
        }finally {
            try {
                br.close();
                inputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
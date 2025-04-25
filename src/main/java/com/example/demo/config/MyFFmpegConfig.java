package com.example.demo.config;

import cc.eguid.FFmpegCommandManager.config.FFmpegConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyFFmpegConfig {
    @Bean
    public FFmpegConfig FFmpegConfig() {
        FFmpegConfig params = new FFmpegConfig();

        params.setPath("D:/ffmpeg/bin");
        params.setSize(100);
        params.setDebug(true);

        return params;
    }
}

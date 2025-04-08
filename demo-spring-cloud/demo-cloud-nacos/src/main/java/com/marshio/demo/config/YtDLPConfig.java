package com.marshio.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
@ConfigurationProperties(prefix = YtDLPConfig.YT_DLP)
public class YtDLPConfig {

    protected static final String YT_DLP = "yt-dlp";

    /**
     * 可执行文件路径，如linux/mac下的 yt-dlp，windows下的C:/user/demo/.yt-dlp/yt-dlp.exe
     */
    private String path = "yt-dlp";
}

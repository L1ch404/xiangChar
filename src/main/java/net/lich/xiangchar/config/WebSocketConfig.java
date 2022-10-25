package net.lich.xiangchar.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Author chenli
 * @ClassName WebScoketConfig
 * @Description
 * @Date 2022/6/10
 **/
@Configurable
@EnableWebSocket
public class WebSocketConfig {
    @Bean("serverEndpointExporter")
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}

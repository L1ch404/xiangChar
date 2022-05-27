package net.lich.xiangchar.handle;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.java.Log;
import net.lich.xiangchar.cache.GamerCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author chenli
 * @ClassName MainHandle
 * @Description
 * @Date 2022/5/27
 **/
@ServerEndpoint(value = "/xiangChar/{sessionId}/pingPong")
@Component
@Log
public class MainHandle {
    /**
     * 用来存放每个客户端对应的 MainHandle  对象
     */
    private static ConcurrentHashMap<String, MainHandle> webSocketMap = new ConcurrentHashMap<>();

    @Autowired
    private GamerCache<MainHandle> gamerCache;
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    public Session session;

    /**
     * 接收 id
     */
    private String sessionId;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sessionId") String sessionId) {
        this.session = session;
        this.sessionId = sessionId;
        if (gamerCache.containsKey(sessionId)) {
            gamerCache.remove(sessionId);
            gamerCache.put(sessionId, this);
        } else {
            gamerCache.put(sessionId, this);
        }
        log.info("");
        try {
            sendMessage("Connection succeeded！");
        } catch (IOException e) {
            log.warning(e.getMessage());
            e.printStackTrace();
        }
    }


    @OnMessage
    public void onMessage(JSONObject message, Session session) throws IOException {
//        JSONObject jsonObject = JSON.parseObject(message);
//        String key = jsonObject.get(ViapConstants.REDIS_MESSAGE_KEY).toString();
//        String value = jsonObject.get(ViapConstants.REDIS_MESSAGE_VALUE).toString();
        sendMessage(key,value);
        log.info("onMessage# User name: " + userName + " message,message: " + message);
    }

    /**
     * 发送到目标服务器上去
     * @param message
     * @param sessionId
     * @throws IOException
     */
    public void sendMessage(String message,String sessionId) throws IOException {
        MainHandle mainHandle = (MainHandle) gamerCache.get(sessionId);
        mainHandle.session.getBasicRemote().sendText(message);
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

}

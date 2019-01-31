package com.sevenXnetworks.treasure.web.controller;

import com.sevenXnetworks.treasure.config.BeginTimeConfig;
import com.sevenXnetworks.treasure.config.WebSocketEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/12/26 11:43
 * @Version 1.0
 */
@Slf4j
@ServerEndpoint(value = "/websocket", encoders = WebSocketEncoder.class)
@Component
public class WebSocketServer {
    private static int onlineCount = 0;
    private static CopyOnWriteArrayList<WebSocketServer> webSocketSet = new CopyOnWriteArrayList<>();
    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
        log.info("有新连接加入！当前在线人数为" + getOnlineCount());
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Connect Success");
            map.put("game_state", BeginTimeConfig.gameState);
            if (BeginTimeConfig.groupId != null) {
                map.put("group_id", BeginTimeConfig.groupId);
            }
            sendMessage(map);
        } catch (IOException | EncodeException ex) {
            log.error("WebSocket IO 异常");
        }
    }

    @OnClose
    public void onClose() throws IOException {
        this.session.close();
        webSocketSet.remove(this);
        subOnlineCount();
        log.info("有一链接关闭！当前在线人数" + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("来自客户端的消息：" + message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
    }

    public void sendMessage(Object obj) throws IOException, EncodeException {
        this.session.getBasicRemote().sendObject(obj);
    }

    // 群发自定义消息
    public static void sendInfo(Object obj) {
        for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(obj);
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    // 游戏结束
    public static void clearSocket() throws IOException {
        for (WebSocketServer item : webSocketSet) {
            item.session.close();
            webSocketSet.remove(item);
        }
        WebSocketServer.onlineCount = 0;
    }

    private static synchronized int getOnlineCount() {
        return onlineCount;
    }

    private static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    private static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}

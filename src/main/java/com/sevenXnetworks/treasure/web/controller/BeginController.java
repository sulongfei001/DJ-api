package com.sevenXnetworks.treasure.web.controller;

import com.sevenXnetworks.treasure.config.BeginTimeConfig;
import com.sevenXnetworks.treasure.utils.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @Description
 * @Author sulongfei
 * @Date 19-1-28 上午11:27
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/progress")
public class BeginController {
    private static Timer timer = new Timer();
    private static TimerTask timerTask = null;

    @ResponseBody
    @RequestMapping(value = "/start_game", method = RequestMethod.GET)
    public Map<String, Object> startGame() {
        Map<String, Object> result = new HashMap<>();
        result.put("operationResult", true);
        if (BeginTimeConfig.gameState == 1 && StringUtils.isNotBlank(BeginTimeConfig.groupId)) {
            return result;
        }
        try {
            Map<String, Object> map = beginGame();
            WebSocketServer.sendInfo(map);
            timer.purge();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    Map<String, Object> map = finishGame();
                    WebSocketServer.sendInfo(map);
                }
            };
            timer.schedule(timerTask, 10 * 60 * 1000);
        } catch (Exception e) {
            result.put("operationResult", false);
            e.printStackTrace();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public Map<String, Object> statusGame() {
        Map<String, Object> state = new HashMap<>();
        if (BeginTimeConfig.gameState == 1 && StringUtils.isNotBlank(BeginTimeConfig.groupId)) {
            state.put("status", true);
        } else {
            state.put("status", false);
        }
        return state;
    }

    @ResponseBody
    @RequestMapping(value = "/end_game", method = RequestMethod.GET)
    public Map<String, Object> endGame() {
        Map<String, Object> result = new HashMap<>();
        result.put("operationResult", true);
        stopTimerTask();
        if (BeginTimeConfig.gameState == 0 && StringUtils.isBlank(BeginTimeConfig.groupId)) {
            return result;
        }
        try {
            Map<String, Object> map = finishGame();
            WebSocketServer.sendInfo(map);
        } catch (Exception e) {
            result.put("operationResult", false);
            e.printStackTrace();
        }
        return result;
    }

    private Map<String, Object> finishGame() {
        String groupId = BeginTimeConfig.groupId;
        BeginTimeConfig.gameState = 0;
        BeginTimeConfig.groupId = null;
        Map<String, Object> map = new HashMap<>();
        map.put("type", "end_game");
        map.put("game_state", BeginTimeConfig.gameState);
        map.put("group_id", groupId);
        return map;
    }

    private Map<String, Object> beginGame() {
        BeginTimeConfig.gameState = 1;
        BeginTimeConfig.groupId = UUID.randomUUID().toString();
        Map<String, Object> map = new HashMap<>();
        map.put("type", "start_game");
        map.put("game_state", BeginTimeConfig.gameState);
        map.put("group_id", BeginTimeConfig.groupId);
        return map;
    }

    public void stopTimerTask() {
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
    }

}

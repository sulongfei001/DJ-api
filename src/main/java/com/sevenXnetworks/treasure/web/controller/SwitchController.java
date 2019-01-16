package com.sevenXnetworks.treasure.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/12/26 13:45
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/switch_mode")
public class SwitchController {

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Map<String, Object> switchMode() {
        Map<String, Object> result = new HashMap<>();
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("type", "switch_mode");
            WebSocketServer.sendInfo(map);
            result.put("operationResult", true);
        } catch (Exception e) {
            result.put("operationResult", false);
            e.printStackTrace();
        }
        return result;
    }
}

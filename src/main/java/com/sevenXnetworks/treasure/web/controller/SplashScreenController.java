package com.sevenXnetworks.treasure.web.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author sulongfei
 * @Date 2018/12/26 13:45
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/splash_screen")
public class SplashScreenController {

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Map<String, Object> splashScreen(@RequestParam String userName) {
        Map<String, Object> result = new HashMap<>();
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("type", "splash_screen");
            map.put("userName", userName);
            WebSocketServer.sendInfo(map);
            result.put("operationResult", true);
        } catch (Exception e) {
            result.put("operationResult", false);
            e.printStackTrace();
        }
        return result;
    }
}

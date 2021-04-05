package com.billy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.billy.schedule.Schedule;
import com.billy.util.HttpClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

//ab -n 500 -c 100 -p ./postdata.txt -T application/json "http://localhost:8000/consume/begin"
@RestController
@RequestMapping("/consume")
public class ConsumeController {

    @Autowired
    private Schedule customSchedule;

    private final Logger logger = LoggerFactory.getLogger(ConsumeController.class);

    @PostMapping("/begin")
    public Map<String, String> consume(@RequestBody Map<String, String> body) {
        long start = System.currentTimeMillis();
        Map<String, String> result = customSchedule.begin(body);
        logger.info("cost " + (System.currentTimeMillis() - start) + "ms");
        return result;
    }

    @PostMapping("/test")
    public Map<String, String> test(@RequestBody Map<String, String> body) {
        String result = HttpClientUtils.sendHttpPostJson("http://localhost:8000/provider/ecif", JSON.toJSONString(body));
        return JSON.parseObject(result, new TypeReference<Map<String, String>>() {
        });
    }
}

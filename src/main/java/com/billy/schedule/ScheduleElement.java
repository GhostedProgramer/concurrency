package com.billy.schedule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.billy.util.HttpClientUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

@NoArgsConstructor
@AllArgsConstructor
public class ScheduleElement implements Callable<Map<String, String>> {

    CountDownLatch latch;
    String url;
    Map<String, String> paramMap;

    Logger logger = LoggerFactory.getLogger(ScheduleElement.class);

    public ScheduleElement(String _url, Map<String, String> _paramMap) {
        this.url = _url;
        this.paramMap = _paramMap;
    }

    public ScheduleElement(String _url, Map<String, String> _paramMap, CountDownLatch _latch) {
        this.url = _url;
        this.paramMap = _paramMap;
        this.latch = _latch;
    }

    /**
     * 同步调用方式
     */
    public Map<String, String> callSynchronize() {
        String paramStr = JSON.toJSONString(paramMap);
        //实际使用为异步请求,且2s内未响应返回默认值
        String responseContent = HttpClientUtils.sendHttpPostJson(url, paramStr);
        return JSON.parseObject(responseContent, new TypeReference<Map<String, String>>() {
        });
    }

    /**
     * 异步调用方式
     */
    @Override
    public Map<String, String> call() {
        String paramStr = JSON.toJSONString(paramMap);
        //实际使用为异步请求,且2s内未响应返回默认值
        String responseContent = HttpClientUtils.sendHttpPostJson(url, paramStr);
        Map<String, String> resultMap = JSON.parseObject(responseContent, new TypeReference<Map<String, String>>() {
        });
//        logger.info("url is " + url);
        latch.countDown();
        return resultMap;
    }
}

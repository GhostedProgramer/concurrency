package com.billy.schedule;

import com.billy.config.ScheduleConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Schedule {

    @Autowired
    ScheduleConfig config;

    Logger logger = LoggerFactory.getLogger(Schedule.class);

    static ExecutorService threadPool = Executors.newFixedThreadPool(32);

    public Map<String, String> begin(Map<String, String> ecifMap) {
        Map<String, String> ecifResult = fromEcif(ecifMap);
//        logger.info("outer service");
        Map<String, String> toEngine = forOuterService(ecifResult);
        return fromEngine(toEngine);
    }

    private Map<String, String> fromEcif(Map<String, String> ecifMap) {
        return new ScheduleElement(config.getEcifUrl(), ecifMap).callSynchronize();
    }

    private Map<String, String> fromEngine(Map<String, String> engineMap) {
        return new ScheduleElement(config.getEngineUrl(), engineMap).callSynchronize();
    }

    abstract Map<String, String> forOuterService(Map<String, String> paramMap);


}

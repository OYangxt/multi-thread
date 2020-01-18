package com.oyxt.example.redis.impl;

import com.oyxt.example.redis.MissionRedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 20190712713
 * @date 2020/1/16 9:45
 */
@Component
public class MissionRedisDaoImpl implements MissionRedisDao {
    @Autowired
    private StringRedisTemplate template;
    @Override
    public void updateResult(String taskId, Map<String, String> value) {
        template.boundHashOps("oyxt:task:result:" + taskId).putAll(value);
        template.expire("oyxt:task:result:" + taskId,30, TimeUnit.HOURS);
    }

    @Override
    public void updateMission(String taskId, Map<String, String> mission) {
        template.boundHashOps("oyxt:task:mission:" + taskId).putAll(mission);
    }

    @Override
    public Map<String,String> findResultByTaskId(String taskId) {
        Map<String, String> map = template.<String,String>boundHashOps("oyxt:task:result:" + taskId).entries();
        return map;
    }
}

package com.oyxt.example.service.impl;

import com.oyxt.example.redis.MissionRedisDao;
import com.oyxt.example.service.MissionService;
import com.oyxt.example.service.task.MyTask;
import com.oyxt.example.util.HttpResult;
import com.oyxt.example.util.JsonTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author 20190712713
 * @date 2020/1/16 9:59
 */
@Slf4j
@Service
public class MissionServiceImpl implements MissionService {
    @Autowired
    private MissionRedisDao missionRedisDao;

    @Override
    public void run(Integer num, String mission) {
        Map<String, String> resultMap ;
        Map<String, String> missionMap = new HashMap<>(1);

        String[] missionArray = mission.split(";");

        ExecutorService pool = Executors.newFixedThreadPool(num);
        List<Future> list = new ArrayList<>();
        for(int i = 0; i < missionArray.length; i++) {
            missionMap.put("mission", missionArray[i]);
            missionMap.put("taskId", String.valueOf(i));
            missionRedisDao.updateMission(String.valueOf(i),missionMap);

            Callable myTask = new MyTask(String.valueOf(i), missionArray[i]);
            Future f = pool.submit(myTask);
            list.add(f);
        }
        pool.shutdown();

        for (Future f : list) {
            try {
                String taskResult = f.get().toString();
                log.info("任务的返回值为: {}",taskResult);
                resultMap = JsonTools.toMap(taskResult);
                String taskId = resultMap.get("taskId");
                //任务返回值结果缓存到redis中
                missionRedisDao.updateResult(taskId,resultMap);

            } catch (Exception e) {
                log.info("获取任务返回值异常{}", e);
            }
        }

    }

    @Override
    public Map<String, String> findTaskResult(String taskId) {

        return missionRedisDao.findResultByTaskId(taskId);
    }

}

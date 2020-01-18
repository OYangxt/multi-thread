package com.oyxt.example.redis;

import java.util.Map;

/**
 * Redis操作接口
 * @author 20190712713
 * @date 2020/1/16 9:42
 */
public interface MissionRedisDao {
    /**
     * 任务返回值存储缓存
     * @param taskId 任务id
     * @param result 任务执行后，返回的结果
     */
    void updateResult(String taskId, Map<String, String> result);

    /**
     * 任务命令缓存
     * @param taskId 任务id
     * @param mission 任务待执行的命令
     */
    void updateMission(String taskId, Map<String, String> mission);

    /**
     * 根据任务Id查询任务执行的结果
     * @param taskId 任务id
     * @return
     */
    Map<String,String> findResultByTaskId(String taskId);
}

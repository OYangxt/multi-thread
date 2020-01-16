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
     * @param taskId
     * @param result
     */
    void updateResult(String taskId, Map<String, String> result);

    /**
     * 任务命令缓存
     * @param taskId
     * @param mission
     */
    void updateMission(String taskId, Map<String, String> mission);
}

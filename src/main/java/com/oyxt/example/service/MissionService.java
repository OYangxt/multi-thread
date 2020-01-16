package com.oyxt.example.service;

/**
 * @author 20190712713
 * @date 2020/1/16 9:55
 */
public interface MissionService {
    /**
     * 执行任务
     * @param num 任务数量
     * @param mission 下发的命令
     */
    void run(Integer num, String mission);
}

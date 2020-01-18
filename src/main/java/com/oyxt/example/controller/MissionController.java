package com.oyxt.example.controller;

import com.oyxt.example.service.MissionService;
import com.oyxt.example.util.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 20190712713
 * @date 2020/1/16 10:35
 */
@RestController
@RequestMapping("/task")
@Slf4j
public class MissionController {
    @Autowired
    private MissionService missionService;
    @GetMapping("exec")
    public HttpResult execTask(Integer taskNum, String mission){

        try {
            missionService.run(taskNum, mission);
            return HttpResult.returnSuccess("任务执行完毕！");
        } catch (Exception e) {
            log.info("线程中断，执行失败{}",e);
            return HttpResult.returnFail("线程执行中断，任务失败");

        }

    }

    @GetMapping("get/{id}")
    public HttpResult getTaskResult(@PathVariable(value = "id") String taskId) {
        try {
            Map<String, String> taskResult = missionService.findTaskResult(taskId);
            return HttpResult.returnSuccess(taskResult);
        } catch (Exception e) {
            log.info("任务结果查询失败{}",e);
            return HttpResult.returnFail("查询失败！");
        }

    }
}

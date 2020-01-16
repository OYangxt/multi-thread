package com.oyxt.example.controller;

import com.oyxt.example.service.MissionService;
import com.oyxt.example.util.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

            log.info("线程异常，执行失败{}",e);

        }
        return HttpResult.returnFail("任务执行失败！");
    }
}

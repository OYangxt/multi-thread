package com.oyxt.example.service.task;

import com.oyxt.example.util.JsonTools;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author 20190712713
 * @date 2020/1/15 18:02
 */
@Slf4j
public class MyTask implements Callable<String> {
    private String taskId;
    private String mission;

    public MyTask(String taskId, String mission) {
        this.taskId = taskId;
        this.mission = mission;
    }

    @Override
    public String call() throws Exception {
        log.info("任务{}已启动，接收到的命令为：{}",taskId,mission);
        Map<String, String> resultMap = new HashMap<>(2);

        String result;
        switch (mission) {
            case "叫爸爸":
                result = "滚犊子";
                break;
            case "说绕口令":
                result = "八百标兵奔北坡，炮兵并排北边跑；炮兵怕把标兵碰，标兵怕碰炮兵炮！";
                break;
            case "读诗":
                result = "窗前明月光，疑是地上霜；举头望明月，低头思故乡！";
                break;
            case "吃饭":
                result = "凉皮，冰峰，肉夹馍！";
                break;
            case "打球":
                result = "足球，篮球，羽毛球！";
                break;
            default:
                result = "请输入有效命令,如叫爸爸...";
        }
        resultMap.put("taskId",taskId);
        resultMap.put("result",result);
        resultMap.put("mission",mission);
        return JsonTools.toJsonStr(resultMap);
    }
}

package com.example.xxljobclient.handler;

import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

@Component
public class SampleXxlJobHandler extends IJobHandler {

    @Override
    @XxlJob(value = "sampleXxlJobHandler")
    public void execute() throws Exception {
        System.out.println("自动任务" + this.getClass().getSimpleName() + "执行");
    }
}

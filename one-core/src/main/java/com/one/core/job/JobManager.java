package com.one.core.job;

import com.one.core.executor.ExecutorSetting;
import com.one.core.executor.LocalStreamExecutor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JobManager {
    private LocalStreamExecutor localStreamExecutor;

    public static JobManager build(ExecutorSetting executorSetting){
        JobManager jobManager = new JobManager();
        jobManager.initExecutor(executorSetting);
        return jobManager;
    }


    public void initExecutor(ExecutorSetting executorSetting){
        this.localStreamExecutor = new LocalStreamExecutor(executorSetting);
    }



}

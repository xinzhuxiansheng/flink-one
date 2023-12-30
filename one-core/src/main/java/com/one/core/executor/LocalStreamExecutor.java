package com.one.core.executor;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.RestOptions;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 本地调试
 */
public class LocalStreamExecutor {
    private static final Logger logger = LoggerFactory.getLogger(LocalStreamExecutor.class);

    private StreamExecutionEnvironment environment;

    public LocalStreamExecutor(ExecutorSetting executorSetting){
        Configuration configuration = Configuration.fromMap(executorSetting.getConfig());
        if(configuration.contains(RestOptions.PORT)){
            this.environment = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(configuration);
        }else{
            this.environment = StreamExecutionEnvironment.createLocalEnvironment(configuration);
        }
    }


}

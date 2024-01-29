package com.one.executor;

import com.one.assertion.Asserts;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.RestOptions;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class LocalStreamExecutor extends Executor {

    /*
       根据 ExecutorSetting 参数，来构造本地运行的 job 环境
     */
    public LocalStreamExecutor(ExecutorSetting executorSetting) {
        this.executorSetting = executorSetting;
        if (Asserts.isNotNull(executorSetting.getConfig())) {
            Configuration configuration = Configuration.fromMap(executorSetting.getConfig());
            if (configuration.contains(RestOptions.PORT)) {
                this.environment = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(configuration);
            } else {
                this.environment = StreamExecutionEnvironment.createLocalEnvironment(configuration);
            }
        } else {
            this.environment = StreamExecutionEnvironment.createLocalEnvironment();
        }
        /*
            调用父类方法，再由 executorSetting 对象 对下面参数进行调整
            environment.setParallelism()
            environment.getConfig().configure()
            这步操作 有些多余，因为 environment 是常驻对象，后期有改动，既然在创建时已添加 configuration,那又何必还在 init();
            例如 并行度参数无法再 Configuration 设置，且 init() 也只做了 设置并行度
            // [优化]
         */
        init();
    }


}

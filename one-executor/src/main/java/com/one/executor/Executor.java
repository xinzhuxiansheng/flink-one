package com.one.executor;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
    Executor 扮演 工厂角色，来构造不同的 job 提交模式
 */
public abstract class Executor {
    private static final Logger logger = LoggerFactory.getLogger(Executor.class);
    protected StreamExecutionEnvironment environment;
    protected CustomTableEnvironment stEnvironment;
    protected ExecutorSetting executorSetting;
    protected boolean useSqlFragment = true;

    public static Executor build() {
        return new LocalStreamExecutor(ExecutorSetting.DEFAULT);
    }

    protected void init() {
        setEnvParallelism(executorSetting);
        initStreamExecutionEnvironment();
    }

    public void setEnvParallelism(ExecutorSetting executorSetting){
        if (executorSetting.isValidParallelism()) {
            environment.setParallelism(executorSetting.getParallelism());
        }
    }

//    public void initEnvironment() {
//        updateEnvironment(executorSetting);
//    }

    public void updateEnvironment(ExecutorSetting executorSetting) {
        setEnvParallelism(executorSetting);

        if (executorSetting.getConfig() != null) {
            Configuration configuration = Configuration.fromMap(executorSetting.getConfig());
            environment.getConfig().configure(configuration, null);
        }
    }

    private void initStreamExecutionEnvironment() {
        updateStreamExecutionEnvironment(executorSetting);
    }

    private void updateStreamExecutionEnvironment(ExecutorSetting executorSetting) {
        useSqlFragment = executorSetting.isUseSqlFragment();

        CustomTableEnvironment newestEnvironment = createCustomTableEnvironment();
        CustomTableEnvironmentContext.set(newestEnvironment);
        stEnvironment = newestEnvironment;

        final Configuration configuration = stEnvironment.getConfig().getConfiguration();
        if (executorSetting.isValidJobName()) {
            configuration.setString(PipelineOptions.NAME.key(), executorSetting.getJobName());
        }

        setConfig.put(PipelineOptions.NAME.key(), executorSetting.getJobName());
        if (executorSetting.getConfig() != null) {
            for (Map.Entry<String, String> entry : executorSetting.getConfig().entrySet()) {
                configuration.setString(entry.getKey(), entry.getValue());
            }
        }
    }

    abstract CustomTableEnvironment createCustomTableEnvironment();
}

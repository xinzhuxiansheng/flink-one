package com.one.executor;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;

@Getter
@Setter
public class ExecutorSetting {
    private static final Logger logger = LoggerFactory.getLogger(ExecutorSetting.class);
    public static final ExecutorSetting DEFAULT = new ExecutorSetting(0, 1, true);

    public static final String CHECKPOINT_CONST = "checkpoint";
    public static final String PARALLELISM_CONST = "parallelism";
    public static final String USE_SQL_FRAGMENT = "useSqlFragment";
    public static final String USE_STATEMENT_SET = "useStatementSet";
    public static final String USE_BATCH_MODEL = "useBatchModel";
    public static final String SAVE_POINT_PATH = "savePointPath";
    public static final String JOB_NAME = "jobName";
    public static final String CONFIG_CONST = "config";

    private static final ObjectMapper mapper = new ObjectMapper();
    private boolean useBatchModel;
    private Integer checkpoint;
    private Integer parallelism;
    private boolean useSqlFragment;
    private boolean useStatementSet;
    private String savePointPath;
    private String jobName;
    private Map<String, String> config;

    public ExecutorSetting(Integer checkpoint, Integer parallelism, boolean useSqlFragment) {
        this(checkpoint, parallelism, useSqlFragment, null, null);
    }
    public ExecutorSetting(Integer checkpoint, Integer parallelism, boolean useSqlFragment, String savePointPath,
                           String jobName) {
        this(checkpoint, parallelism, useSqlFragment, savePointPath, jobName, null);
    }
    public ExecutorSetting(Integer checkpoint, Integer parallelism, boolean useSqlFragment, String savePointPath) {
        this(checkpoint, parallelism, useSqlFragment, savePointPath, null, null);
    }
    public ExecutorSetting(Integer checkpoint, Integer parallelism, boolean useSqlFragment, String savePointPath,
                           String jobName, Map<String, String> config) {
        this(checkpoint, parallelism, useSqlFragment, false, false, savePointPath, jobName, config);
    }
    public ExecutorSetting(Integer checkpoint, Integer parallelism, boolean useSqlFragment, boolean useStatementSet,
                           boolean useBatchModel, String savePointPath, String jobName,
                           Map<String, String> config) {
        this.checkpoint = checkpoint;
        this.parallelism = parallelism;
        this.useSqlFragment = useSqlFragment;
        this.useStatementSet = useStatementSet;
        this.useBatchModel = useBatchModel;
        this.savePointPath = savePointPath;
        this.jobName = jobName;
        this.config = config;
    }

    public boolean isValidParallelism() {
        return this.getParallelism() != null && this.getParallelism() > 0;
    }

}

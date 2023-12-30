package com.one.core.executor;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class ExecutorSetting {
    // 扩展配置项
    private Map<String, String> config;
}

package com.example.config.tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

public class HtmlTask extends DefaultTask {
    @TaskAction
    public void unzip() {
        System.out.println("> 测试执行");
    }
}

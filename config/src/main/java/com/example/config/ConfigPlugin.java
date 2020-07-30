package com.example.config;

import com.example.config.tasks.HtmlTask;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class ConfigPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getTasks().register("unzipHtml", HtmlTask.class);
    }
}
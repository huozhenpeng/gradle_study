### gradle plugin
    
    1、新建java项目：config   存放自定义的plugin，包括自定义的task
        修改build.gradle
        ```
        gradlePlugin {
            plugins {
                version {
                    id = "com.example.config"
                    implementationClass = "com.example.config.ConfigPlugin"
                }
            }
        }
        
        ```
    2、修改setting.gradle    includeBuild("./config")
    3、新建module：library  
        修改build.gradle,在起始位置加入（一定是在起始位置）
        ```
        plugins {
            id("com.example.config")
        }
        //测试是否生效
        project.afterEvaluate {
            preBuild.dependsOn 'unzipHtml'
        }
        ```
        
### gradle plugin

    1、上面的第三步直接放到主module中也是可以的
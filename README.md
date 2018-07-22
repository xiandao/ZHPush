# ZHPush
一个混合推送框架

### 如图

![image](pic/code.png)

![image](pic/logcat.png)


### 用法

1. 在你的项目根目录下的build.gradle文件添加下面一行代码

```
    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' } // 添加这一行
        }
    }
```

2. 在你的应用模块下的build.gradle添加依赖

```
    dependencies {
        implementation 'com.github.xiandao:ZHPush:1.3' // 添加这一行
    }
```

  
  

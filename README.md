# 本公司所有SDK的依赖库  
## 实例代码请切换仓库到对应分支

# 使用方法
project build.graddle
```
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://github.com/h4de5ing/KonSDK/raw/master/repository' }
    }
}
```
module

```
dependencies {
    implementation 'com.github.h4de5ing.sg7800sdk:sg7800sdk:1.0-20210513'
}
```
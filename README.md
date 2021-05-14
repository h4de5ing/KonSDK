# SG7800SDK 使用方法

# 添加依赖使用方法
project build.graddle
```java
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://github.com/h4de5ing/KonSDK/raw/master/repository' }
    }
}
```
module build.gradle

```java
dependencies {
    implementation 'com.github.h4de5ing.sg7800sdk:sg7800sdk:1.0-20210513'
}
```

# 读取温度
```
需要读取温度的页面继承SDKTemBaseActivity类实现如果方法：
bindIp() 验证服务器ip地址
bindPort() 验证服务器端口
bindMac()  自行扫描蓝牙，选择温度测量硬件设备的蓝牙MAC地址
updateTem(tem:String) 读取温度的结果
```

# 读取有源标签

```
SPUtils.init(ip, port) { 这里返回读取到的MAC地址}
ip 验证服务器ip地址
port 验证服务器端口
SPUtils.close 关闭有源标签读取
```


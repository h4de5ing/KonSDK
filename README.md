# SG7800SDK 使用方法

# 添加依赖使用方法
project build.graddle
```gradle
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://github.com/h4de5ing/KonSDK/raw/master/repository' }
    }
}
```
module build.gradle

```gradle
dependencies {
    implementation 'com.github.h4de5ing.sg7800sdk:sg7800sdk:1.0-20210513'
}
```

# 读取温度
```
需要读取温度的页面继承SDKTemBaseActivity类实现如下方法：  
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

# 扫描头扫描条码 二维码请在系统app(扫描服务0 v1.10.5)中设置  
- 接收扫描数据广播  
ACTION:com.rfid.SCAN  
data:data  
```kotlin
   val intentFilter = IntentFilter()
        val action = "com.rfid.SCAN"
        val data = "data"
        intentFilter.addAction(action)
        registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                if (action == intent.action) {
                    var data = intent.getByteArrayExtra(data)
                    data?.apply {
                        Log.d("scan", "scanQrCode = [$data]")
                    }
                }
            }
        }, intentFilter)
```

# 自定义按键监听  
- SG7800设备左边按键F3 右边按键F5  
```kotlin
   private var keyReceiver: BroadcastReceiver? = null
    private fun registerReceiver() {
        keyReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                var keyCode = intent.getIntExtra("keyCode", 0)
                if (keyCode == 0) keyCode = intent.getIntExtra("keycode", 0)
                val keyDown = intent.getBooleanExtra("keydown", false)
                if (keyDown && keyCode == KeyEvent.KEYCODE_F3) {
                    //监听到左边按键按下
                }
            }
        }
        val filter = IntentFilter()
        filter.addAction("android.rfid.FUN_KEY")
        filter.addAction("android.intent.action.FUN_KEY")
        registerReceiver(keyReceiver, filter)
    }

    private fun unregisterReceiver() {
        if (keyReceiver != null)
            unregisterReceiver(keyReceiver)
    }
```

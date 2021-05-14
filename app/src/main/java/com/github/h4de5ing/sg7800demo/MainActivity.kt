package com.github.h4de5ing.sg7800demo

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import com.github.h4de5ing.sg7800sdk.SDKTemBaseActivity
import com.github.h4de5ing.sg7800sdk.SPUtils
import kotlinx.android.synthetic.main.activity_main.*

/**
 * SDK 文档
 * 读取温度  需要读取温度的页面继承SDKTemBaseActivity 这个类
 * bindIp() 验证服务器ip地址
 * bindPort() 验证服务器端口
 * bindMac()  自行扫描蓝牙，选择温度测量硬件设备的蓝牙MAC地址
 * updateTem(tem:String) 读取温度的结果
 * 读取有源标签信息
 * SPUtils.init(ip, port) { updateTv("MAC:${it}") }
 * ip 验证服务器ip地址
 * port 验证服务器端口
 * 回调的信息即是读取到的有源标签信息
 * SPUtils.close 关闭有源标签读取
 */
class MainActivity : SDKTemBaseActivity() {
    private val ip = "172.16.1.246"
    private val port = 9001
    override fun bindIp(): String = ip
    override fun bindPort() = port

    //返回你需要绑定的蓝牙mac地址
    override fun bindMac(): String = "84:C2:E4:23:0C:16"

    //返回的温度
    override fun updateTem(tem: String) = updateTv("温度${tem}")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv.movementMethod = ScrollingMovementMethod()
        SPUtils.init(ip, port) { updateTv("MAC:${it}") }
        readTem.setOnClickListener { test() }
    }

    private fun updateTv(str: String) {
        runOnUiThread {
            tv.append("$str\n")
            val offset = tv.lineCount * tv.lineHeight - tv.height
            if (offset >= 2000) {
                tv.text = ""
            } else {
                tv.scrollTo(0, if (offset > 0) offset else 0)
            }
        }
    }
}
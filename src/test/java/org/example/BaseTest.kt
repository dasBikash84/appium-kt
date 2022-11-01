package org.example

import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.options.UiAutomator2Options
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass
import java.net.URL

abstract class BaseTest{

    companion object{
        private const val DEVICE_NAME = "emulator-5554"
        private const val APK_PATH = "/media/bikash/data/work/Trainning/appium/data/APKFiles/resources/ApiDemos-debug.apk"
        private const val url = "http://127.0.1.1:4723"
    }

    private lateinit var driver: AndroidDriver
    private lateinit var options: UiAutomator2Options

    @BeforeClass
    fun init(){
        options = UiAutomator2Options().apply {
            setDeviceName(DEVICE_NAME)
            setApp(APK_PATH)
        }
        driver = AndroidDriver(URL(url), options)
    }


    @AfterClass
    fun cleanAll(){
        driver.quit()
    }

    protected fun runWithDriver(task:AndroidDriver.()->Unit){
        driver.task()
    }
}
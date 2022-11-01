package org.example

import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.options.UiAutomator2Options
import org.openqa.selenium.WebDriver.Timeouts
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass
import java.net.URL
import java.time.Duration

abstract class BaseTest{

    companion object{
        private const val DEVICE_NAME = "emulator-5554"
        private const val APK_PATH = "/media/bikash/data/work/Trainning/appium/data/APKFiles/resources/ApiDemos-debug.apk"
        private const val url = "http://127.0.1.1:4723"
    }

    private lateinit var driver: AndroidDriver
    private lateinit var options: UiAutomator2Options

    protected open val currentDeviceName = DEVICE_NAME
    protected open val apkPath = APK_PATH

    protected open val appActivity : String? = null

    @BeforeClass
    fun init(){
        options = UiAutomator2Options().apply {
//            setDeviceName(currentDeviceName)
            setApp(apkPath)

            if (appActivity.isPresent){
                setAppWaitActivity(appActivity.get())
            }else {
                setAppWaitActivity("*")
            }
        }
        driver = AndroidDriver(URL(url), options)
    }


    @AfterClass
    fun cleanAll(){
        driver.quit()
    }

    protected fun runWithDriver(timeoutSec: Long = 2L,task:AndroidDriver.()->Unit){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeoutSec))
        driver.task()
    }
}
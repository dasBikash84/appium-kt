package org.example

import io.appium.java_client.AppiumBy
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.options.UiAutomator2Options
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.NotFoundException
import org.openqa.selenium.WebDriver.Timeouts
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebElement
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
        Thread.sleep(2000)
    }

    protected fun AndroidDriver.findElementByAccessibilityId(accessibilityId:String) : WebElement =
        findElement{ findElement(AppiumBy.accessibilityId(accessibilityId)) }
    protected fun AndroidDriver.findElementByXpath(xpath:String) : WebElement =
        findElement{ findElement(AppiumBy.xpath(xpath)) }
    protected fun AndroidDriver.findElementById(id:String) : WebElement =
        findElement{ findElement(AppiumBy.id(id)) }

    protected fun AndroidDriver.doLongPress(elementForLongClick: WebElement?,duration:Long = 2000) {
        (this as JavascriptExecutor).executeScript(
            "mobile: longClickGesture", mapOf(
                "elementId" to (elementForLongClick as RemoteWebElement).id,
                "duration" to duration
            )
        )
    }

    protected fun AndroidDriver.scrollToText(text:String) {
        findElement(
            AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector()).scrollIntoView(text(\"${text}\"));"
            )
        )
    }
    private fun AndroidDriver.findElement(getterFunc:AndroidDriver.()->WebElement) : WebElement {
        var canScrollMore : Boolean
        do {
            try {
                return getterFunc()
            }catch (ex:Throwable){
                println(ex.message)
            }
            canScrollMore = scroll()

        }while (canScrollMore)
        return getterFunc()
    }
    private fun AndroidDriver.scroll(
        left : Long = 100,
        top : Long = 100,
        width : Long = 200,
        height : Long = 500,
        down : Boolean = true
    ) : Boolean =
        (this as JavascriptExecutor).executeScript(
            "mobile: scrollGesture", mapOf(
                "left" to left,
                "top" to top,
                "width" to width,
                "height" to height,
                "direction" to if (down) "down" else "up",
                "percent" to 50.0
            )
        ) as Boolean

}
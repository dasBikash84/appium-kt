package org.example

import io.appium.java_client.AppiumBy
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.options.UiAutomator2Options
import org.testng.Assert
import org.testng.annotations.Test
import java.io.File
import java.net.URL
import java.time.Duration

class VerboseTest{

    companion object{
        private const val appiumServerUrl = "http://127.0.1.1:4723"
    }

    private val currentDeviceName = ""

    private val apkPath = "./apks/ApiDemos-debug.apk"
    private val waitActivity : String? = null

    @Test
    fun runVerboseTest(){

        val capabilities: UiAutomator2Options = UiAutomator2Options().apply {

            if (currentDeviceName.isNotBlank().not()) {
                setDeviceName(currentDeviceName)
            }

            setApp(File(apkPath).absolutePath)

            if (waitActivity.isNullOrBlank().not()){
                setAppWaitActivity(appActivity.get())
            }else {
                setAppWaitActivity("*")
            }

        }

        val driver: AndroidDriver = AndroidDriver(URL(appiumServerUrl), capabilities)

        driver.run {

            manage().timeouts().implicitlyWait(Duration.ofSeconds(2))

            findElement(AppiumBy.accessibilityId("Preference")).click()
            findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"3. Preference dependencies\"]")).click()
            val checkBox = findElement(AppiumBy.id("android:id/checkbox"))
            if (!checkBox.isSelected){
                checkBox.click()
            }
            findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[2]/android.widget.RelativeLayout")).click()
            val popUpTitle = findElement(AppiumBy.id("android:id/alertTitle")).text
            Assert.assertEquals(popUpTitle,"WiFi settings")
            val editText = findElement(AppiumBy.id("android:id/edit"))
            editText.sendKeys("xxxxx Wifi")
            editText.clear()
            editText.sendKeys("First Wifi")
            findElement(AppiumBy.id(("android:id/button1"))).click()
            Thread.sleep(2000)
            quit()
        }
    }

}
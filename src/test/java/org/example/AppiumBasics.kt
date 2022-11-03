package org.example

import io.appium.java_client.AppiumBy
import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebElement
import org.testng.Assert
import org.testng.annotations.Test


class AppiumBasics : BaseTest() {
    @Test
    fun test101() {
        runWithDriver {
            findElementByAccessibilityId("Preference").click()
            findElementByXpath("//android.widget.TextView[@content-desc=\"3. Preference dependencies\"]").click()
            val checkBox = findElementById("android:id/checkbox")
            println(checkBox.isSelected)
            if (!checkBox.isSelected){
                checkBox.click()
            }
            findElementByXpath("/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[2]/android.widget.RelativeLayout").click()
            val popUpTitle = findElementById("android:id/alertTitle").text
            Assert.assertEquals(popUpTitle,"WiFi settings")
            findElementById("android:id/edit").sendKeys("Bivor Wifi")
            findElementById("android:id/button1").click()
        }
    }

    @Test
    fun longPress(){
        runWithDriver {
            findElementByXpath("//android.widget.TextView[@content-desc=\"Views\"]").click()
            findElementByAccessibilityId("Expandable Lists").click()
            findElementByAccessibilityId("1. Custom Adapter").click()
            doLongPress(findElementByXpath("(//android.widget.TextView)[2]"))
        }
    }

    @Test
    fun scrollTest(){
        runWithDriver {
            findElementByAccessibilityId("Views").click()
            findElementByAccessibilityId("WebView").click()
        }
    }



}
package org.example

import io.appium.java_client.AppiumBy
import org.openqa.selenium.By
import org.testng.Assert
import org.testng.annotations.Test


class AppiumBasics : BaseTest() {
    @Test
    fun test101() {
        runWithDriver {
            findElement(AppiumBy.accessibilityId("Preference")).click()
            findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"3. Preference dependencies\"]")).click()
            val checkBox = findElement(AppiumBy.id("android:id/checkbox"))
            println(checkBox.isSelected)
            if (!checkBox.isSelected){
                checkBox.click()
            }
            findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[2]/android.widget.RelativeLayout")).click()
            val popUpTitle = findElement(By.id("android:id/alertTitle")).text
            Assert.assertEquals(popUpTitle,"WiFi settings")
            findElement(By.id("android:id/edit")).sendKeys("Bivor Wifi")
            findElement(By.id("android:id/button1")).click()
        }
    }
}
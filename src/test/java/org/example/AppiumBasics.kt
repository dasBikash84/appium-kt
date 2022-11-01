package org.example

import io.appium.java_client.AppiumBy
import org.testng.annotations.Test


class AppiumBasics : BaseTest() {
    @Test
    fun test101() {
        runWithDriver {
            findElement(AppiumBy.accessibilityId("Preference")).click()
            findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"3. Preference dependencies\"]")).click()
            findElement(AppiumBy.id("android:id/checkbox")).click()
            findElement(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[2]/android.widget.RelativeLayout")).click()
        }
    }
}
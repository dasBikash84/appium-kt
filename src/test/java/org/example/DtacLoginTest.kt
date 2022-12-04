package org.example

import io.appium.java_client.AppiumBy
import org.testng.annotations.Test


class DtacLoginTest : BaseTest() {

    override val apkPath: String
        get() = "./apks/dtac_staging_uatClient_99.30.4.apk"

    override val waitActivity: String
        get() = "com.portonics.dtac.ui.home.HomeActivity"

    @Test
    fun loginTest() {
        runWithDriver(timeoutSec = 2) {

            findElementById("th.co.crie.tron2.android.dev:id/text_view_picker").click()
            findElementByXpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/androidx.recyclerview.widget.RecyclerView/android.widget.TextView[1]")
                .click()
            findElementByXpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.appcompat.widget.LinearLayoutCompat/android.widget.TextView")
                .click()
            findElementById("th.co.crie.tron2.android.dev:id/btnLogin").click()
            findElementById("th.co.crie.tron2.android.dev:id/etMsisdn").sendKeys("0880799445")
            findElementById("th.co.crie.tron2.android.dev:id/btnSignIn").click()
            findElementById("th.co.crie.tron2.android.dev:id/pin_view_otp").sendKeys("1234")
            Thread.sleep(4000)
        }
    }
}
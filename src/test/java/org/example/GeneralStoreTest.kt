package org.example

import io.appium.java_client.AppiumBy
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.annotations.Test
import java.time.Duration
import kotlin.test.assertEquals


class GeneralStoreTest : BaseTest() {

    override val apkPath: String
        get() = "./apks/General-Store.apk"

    @Test
    fun profileSetTest() {
        runWithDriver{

            val countryName = "Cuba"

            findElementById("com.androidsample.generalstore:id/nameField").sendKeys("Holly White")
            hideKeyboard()
            findElementById("com.androidsample.generalstore:id/radioFemale").click()
            findElementById("com.androidsample.generalstore:id/spinnerCountry").click()
            scrollToText(countryName)
            findElementByXpath("//android.widget.TextView[@text='${countryName}']").click()
            findElementById("com.androidsample.generalstore:id/btnLetsShop").click()
        }
    }

    @Test
    fun nameToastTest() {
        runWithDriver{
            findElementById("com.androidsample.generalstore:id/btnLetsShop").click()
            assertEquals("Please enter your name", findElementByXpath("//android.widget.Toast[1]").getAttribute("name"))
        }
    }

    @Test
    fun testAddToCart(){
        profileSetTest()
        runWithDriver {
            val productName = "Jordan 6 Rings"
            scrollToText(productName)
            findElements(AppiumBy.id("com.androidsample.generalstore:id/productName")).indexOfFirst {
                it.text == productName
            }.run {
                findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']"))[this].click()
            }
            findElementById("com.androidsample.generalstore:id/appbar_btn_cart").click()
            val wait = WebDriverWait(this, Duration.ofSeconds(5))
            wait.until(
                ExpectedConditions.attributeContains(
                    findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")),
                    "text",
                    "Cart"
                )
            )
            assertEquals(productName,findElementById("com.androidsample.generalstore:id/productName").text)
        }
    }

    @Test
    fun cartSumTest(){
        profileSetTest()
        runWithDriver {
            findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']"))
                .take(2).asSequence().forEach { it.click() }

            findElementById("com.androidsample.generalstore:id/appbar_btn_cart").click()
            val wait = WebDriverWait(this, Duration.ofSeconds(5))
            wait.until(
                ExpectedConditions.attributeContains(
                    findElement(AppiumBy.id("com.androidsample.generalstore:id/toolbar_title")),
                    "text",
                    "Cart"
                )
            )
            val priceSum = findElements(AppiumBy.id("com.androidsample.generalstore:id/productPrice")).map {
                it.text.substring(1).toDouble()
            }.reduce { sum, data -> sum + data }
            val cartSum = findElementById("com.androidsample.generalstore:id/totalAmountLbl").text
                            .split(" ")[1].toDouble()
            assertEquals(priceSum,cartSum)
        }
    }
}
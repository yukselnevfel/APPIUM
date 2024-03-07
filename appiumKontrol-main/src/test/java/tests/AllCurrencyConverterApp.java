package tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.openqa.selenium.OutputType;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Driver;
import utilities.ReusableMethods;

import java.io.File;
import java.io.IOException;

public class AllCurrencyConverterApp {
    AndroidDriver<AndroidElement>driver= Driver.getAndroidDriver();

    @Test
    public void allCurrencyTest() throws InterruptedException, IOException {
// all currency uygulamasinin yuklendigi dogulanir
        Assert.assertTrue( driver.isAppInstalled("com.smartwho.SmartAllCurrencyConverter"));
// uygulamanin acildigi dogrulanir
Assert.assertTrue(driver.findElementByXPath("//*[@text='CURRENCY CONVERTER']").isDisplayed());
// cevirmek istedigimiz para birimi zloty olarak secilir
        ReusableMethods.koordinatTiklamaMethodu(435,347,1500);
        ReusableMethods.scrollWithUiScrollable("PLN");
       // ReusableMethods.scrollWithUiScrollable("Polish Zloty");
// cevirelecek olan para birimi Tl olarak secilir
        ReusableMethods.koordinatTiklamaMethodu(431,470,1500);//424 482
        ReusableMethods.scrollWithUiScrollable("Turkish Lira");
// cevrilen tutar screenShot olarak kaydedilir
        ReusableMethods.scrollWithUiScrollable("1");
        ReusableMethods.scrollWithUiScrollable("5");
        ReusableMethods.scrollWithUiScrollable("3");
        ReusableMethods.scrollWithUiScrollable("8");
        /*
        File file =driver.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File("ExchangeRateForZlotyToTL.jpg")); // veya .Png formatında kaydedilir!
         */
        ReusableMethods.getScreenshot("nevfel");
// Ardindan zloty nin tl karsiligi olan tl degeri kaydedilir
        AndroidElement exchangeResult= driver.findElementById("com.smartwho.SmartAllCurrencyConverter:id/EditTextCurrencyB");
        String sonucText=exchangeResult.getText();
// kullaniciya sms olarak bildirilir

    }

}

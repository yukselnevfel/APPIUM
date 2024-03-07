package tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Driver;

public class AllCurrencyConverterApp {
    AndroidDriver<AndroidElement>driver= Driver.getAndroidDriver();

    @Test
    public void allCurrencyTest(){
// all currency uygulamasinin yuklendigi dogulanir
        Assert.assertTrue( driver.isAppInstalled("com.smartwho.SmartAllCurrencyConverter"));
// uygulamanin acildigi dogrulanir
// cevirmek istedigimiz para birimi zloty olarak secilir
// cevirelecek olan para birimi Tl olarak secilir
// cevrilen tutar screenShot olarak kaydedilir
// Ardindan zloty nin tl karsiligi olan tl degeri kaydedilir
// kullaniciya sms olarak bildirilir

    }

}

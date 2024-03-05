package tests;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.aspectj.lang.annotation.Before;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ArabamApp {

    AndroidDriver<AndroidElement>driver;
    @BeforeTest
    public void arabamSetUp() throws MalformedURLException {
        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Pixel 2");
        // capabilities.setCapability("deviceName","Pixel 2");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"10.0");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
        capabilities.setCapability("appPackage","com.dogan.arabam");
        //Hangi uygulama üzerinde çalışmak istiyorsak o uygulamaya ait appPackage degeri
        // yani uygulamanın kimlik bilgisi
        capabilities.setCapability("appActivity","com.dogan.arabam.presentation.feature.home.HomeActivity");
        // AppPackage da belirlenen uygulamanin hangi sayfasindan baslanacak oldugunu belirlemek icin girilen activity degeridir.
        // Eger appPackage girilirse bunu da yazmak zorundayız!
        driver=new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

    }

    @Test
    public void arabamTest() throws InterruptedException {
       // driver.activateApp("com.dogan.arabam");

        // uygulamanin basarili bir sekilde yuklendigi dogrulanir
        Assert.assertTrue(driver.isAppInstalled("com.dogan.arabam"));
        // uygulaminin basarili bir sekilde acildigi dogrulanir
        Assert.assertTrue(driver.findElementById("com.dogan.arabam:id/ivArabamLogo").isDisplayed());
        // alt menuden ilan ara butonuna tiklanir
        driver.findElementByXPath("//*[@text='İlan Ara']").click();
        // kategori olarak otomobil secilir
        //driver.findElementByXPath("//*[@text='Otomobil']").click();//tıklama yapmak için klasik yöntem.
        Thread.sleep(1500);
        TouchAction action=new TouchAction<>(driver);
        action
                .press(PointOption.point(994,500))//
                .release()
                .perform();
        Thread.sleep(1500);

        // arac olarak Volkswagen secilir
        for (int i=0; i<6; i++) {
            action.press(PointOption.point(482, 1516))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(750)))
                    .moveTo(PointOption.point(482, 320))
                    .release()
                    .perform();
        }
        Thread.sleep(1500);
        driver.findElementByXPath("//*[@text='Volkswagen']").click();
        // arac markasi olarak passat secilir
        driver.findElementByXPath("//*[@text='Passat']").click();
        // 1.4 TSI BlueMotion secilir
        driver.findElementByXPath("//*[@text='1.4 TSi BlueMotion']").click();
        Thread.sleep(1000);
        // Paket secimi yapilir
        action
                .press(PointOption.point(500,700))
                .release()
                .perform();
        Thread.sleep(1000);

        // Ucuzdan pahaliya siralama yaparak filtreleme yapilir
        driver.findElementById("com.dogan.arabam:id/textViewSorting").click();
        Thread.sleep(1500);
        driver.findElementByXPath("//*[@text='Fiyat - Ucuzdan Pahalıya']").click();
        // Gelen en ucuz aracin 500.000 tl den buyuk oldugu dogrulanir
        AndroidElement enUcuzAracFiyatiElementi=driver.findElementByXPath("(//*[@resource-id='com.dogan.arabam:id/tvPrice'])[1]");
        String aracinSonFiyati=enUcuzAracFiyatiElementi.getText();
        System.out.println(aracinSonFiyati);
        Assert.assertTrue(Integer.parseInt(aracinSonFiyati)>500000);
    }


}

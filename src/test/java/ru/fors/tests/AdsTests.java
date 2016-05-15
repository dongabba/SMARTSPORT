package ru.fors.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fors.pages.AdsPageDE;
import ru.fors.pages.MainPage;
import ru.fors.pages.MainPageDE;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import static org.junit.Assert.assertTrue;

/**
 * Created by Alexander Zhaleiko on 15.05.2016.
 */
public class AdsTests extends TestBase {
    @BeforeMethod
    @Parameters({"username", "password"})
    public void testStatus(String username, String password){
        if (driver == null){
            init();
            userLogin(username, password);
        } if (driver.getTitle().equals("Login")){
            userLogin(username, password);
        }
    }


    @Features("Работа с объектом \"Объявление\"")
    @Stories("Создание объявления")
    @Test
    public void userCreateAdsTest(){
        MainPageDE mainPageDE = new MainPageDE(driver);
        mainPageDE.userGoToMainPage();
        AdsPageDE adsPageDE = mainPageDE.userGoToAdsPage();
        assertTrue("Не открылась страница Объявления", adsPageDE.isAdsPageOpen());
        adsPageDE.userClickCreateButton();
        assertTrue("Не открылась страница создвния объявления", adsPageDE.isCreateAdsPageOpen());
        adsPageDE.userAddAds();
        assertTrue("Не удалось создать объявление", adsPageDE.isAdsAdd());
    }
}

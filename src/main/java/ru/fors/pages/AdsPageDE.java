package ru.fors.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.Random;

/**
 * Created by Alexander Zhaleiko on 12.05.2016.
 */
public class AdsPageDE extends ObjectsPage{
    AdsPageDE(WebDriver driver) {
        super(driver);
    }

    private By pageTitle = By.xpath("//th[text()='Ankündigungen']");
    private By createAdsPageTitle = By.xpath("//th[text()='Anmeldungskarte']");
    private By adsName = By.id("P45_TITLE");
    private By adsText = By.id("P45_DESCRIPTION");


    @Step("Проверяем открылась ли страница Объявления")
    public boolean isAdsPageOpen(){
        return ensurePageLoaded(pageTitle);
    }
    @Step("Проверяем открылась ли страница создания нового объявления")
    public boolean isCreateAdsPageOpen(){
        return ensurePageLoaded(createAdsPageTitle);
    }
    @Step("Пользователь создает объявление")
    public void userAddAds(){
        userSetAdsName();
        userSetAdsText();
        userClickCreateButton();
    }
    @Step("Пользователь указывает текст объявления")
    private void userSetAdsText() {
        type(adsText, "This is new ads. It could be your advertising");
    }
    @Step("Пользователь указывает название объявления")
    private void userSetAdsName() {
        Random random = new Random();
        ads.setName("This is new ads_"+random.nextInt(1000));
        type(adsName, ads.getName());
    }
    @Step("Проверяем добавилось ли объявление")
    public boolean isAdsAdd(){
        try{
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[text()='"+ads.getName()+"']")));
            return true;
        } catch (Exception e){
            return false;
        }
    }


}

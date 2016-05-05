package ru.fors.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.fors.data.Coach;
import ru.fors.data.Player;
import ru.fors.data.Team;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.ArrayList;

/**
 * Created by Alexander Zhaleiko on 04.05.2016.
 */
public class ObjectsPage extends Page{
    public ObjectsPage(WebDriver driver) {
        super(driver);
    }
    By successMessage = By.id("success-message");
    By createButton = By.linkText("erstellen");
    static Coach coach = new Coach();

    @Step("Проверяем создался ли объект")
    public boolean isCreatedOk(){
        try
        {
            wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
            return true;
        } catch (Exception e)
        {
            return false;
        }
    }
    @Step("Пользователь нажимает кнопку создать объект")
    public void userClickCreateButton() {
        click(createButton);
    }

}

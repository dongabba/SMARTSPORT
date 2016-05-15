package ru.fors.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.allure.annotations.Step;

public class MainPage extends Page {

	public MainPage(WebDriver driver) {
		super(driver);
	}
	private By userRole = By.id("username");
	private By DE_MainPageLInk = By.linkText("DE");

    @Step("Проверяем открылась ли страница")
	public boolean isPageLoaded(){
        return ensurePageLoaded(userRole);
    }

	@Step("Проверяем роль пользователя")
	public String getUserRole(){
		return getElementText(userRole);
	}
	
	@Step("Пользователь выходит из системы")
	LoginPage userLogOut(By logoutLink){
		click(logoutLink);
		return new LoginPage(driver);
	}

	@Step("Пользователь переходит на немецкую версию сайта")
	public MainPageDE userGoToMainPageDE(){
		click(DE_MainPageLInk);
		return new MainPageDE(driver);
	}
}

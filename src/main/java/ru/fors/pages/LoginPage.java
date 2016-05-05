package ru.fors.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;

public class LoginPage extends Page {
	


	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}


	By usernameField = By.id("P101_USERNAME");
	By passwordField = By.id("P101_PASSWORD");
	By loginButton = By.id("P101_LOGIN");


	@Step("Пользователь входит в систему {0}")
	public MainPage userLogin(String username, String password){
		type(usernameField, username);
		type(passwordField, password);
		click(loginButton);
		return new MainPage(driver); 
		
	}
}

package ru.fors.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ru.fors.pages.LoginPage;
import ru.fors.pages.MainPage;
import ru.fors.pages.MainPageDE;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import static org.junit.Assert.*;


public class LoginTests extends TestBase {

	@BeforeMethod
	@Parameters({"username", "password"})
	public void testStatus(String username, String password){
		if (driver == null){
			init();
			userLogin(username, password);
		} if (driver.getTitle().equals("login")){
			userLogin(username, password);
		}
	}
	
	@Features("Авторизация в системе")
	@Stories("Вход/Выход из системы")
	@Test
	@Parameters({"username", "password", "role"})
	public void userLoginTest(String username, String password, String role){
		LoginPage loginPage = new LoginPage(driver);
		MainPage mainPage = loginPage.userLogin(username, password);
		assertTrue("Не совпадают роли пользователей", mainPage.getUserRole().contains(role));
		MainPageDE mainPageDE = mainPage.userGoToMainPageDE();
		assertTrue("Не открылась немецкая версия сайта", mainPageDE.isPageLoaded());
		mainPageDE.userLogOut();
		assertTrue("Не произведен выход на страницу авторизации", loginPage.getPageTitle().equals("Login"));
	}
	

}

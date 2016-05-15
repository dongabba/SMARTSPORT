package ru.fors.tests;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import ru.fors.data.Player;
import ru.fors.data.Team;
import ru.fors.pages.LoginPage;
import ru.fors.pages.MainPage;
import ru.fors.utils.Browser;
import ru.fors.utils.PropertyLoader;
import ru.fors.utils.WebDriverFactory;

public class TestBase {
	
	protected static WebDriver driver;
	private String baseUrl;

	
	@BeforeTest
	public void init(){
		baseUrl = PropertyLoader.loadProperty("site.url");
		Browser browser = new Browser();
		browser.setName(PropertyLoader.loadProperty("browser.name"));
		driver = WebDriverFactory.getInstance(browser);
		driver.manage().window().maximize();
		driver.get(baseUrl);
		
	}
	
	
	@AfterTest
	public static void tearDown() {
		if (driver !=null) {
			driver.quit();
			driver = null;
		}
	}

	public void userLogin(String username, String password){
		LoginPage loginPage = new LoginPage(driver);
		MainPage mainPage = loginPage.userLogin(username, password);
		if (!mainPage.getPageTitle().contains("Hauptseite."))
		{
			mainPage.userGoToMainPageDE();
		}
	}
	
	static WebDriver getWebDriver(){
		return driver;
	}
	
	
	

}

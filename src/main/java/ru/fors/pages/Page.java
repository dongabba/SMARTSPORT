package ru.fors.pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.fors.utils.PropertyLoader;

public class Page {

	protected WebDriver driver;
	protected WebDriverWait wait;

	public Page(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Long.parseLong(PropertyLoader.loadProperty("wait")));
	}

	Date currentDate = new Date(System.currentTimeMillis());
	Date yesterday = new Date(System.currentTimeMillis()-86400000);
	Date competitionEndDate = new Date(System.currentTimeMillis()+259200000);
	DateFormat timeFormat = new SimpleDateFormat("HHmmss");
	DateFormat date = new SimpleDateFormat("dd.MM.YYYY");

	public boolean ensurePageLoaded(By element){
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));
			return true;
		} catch (Exception e){
			return false;
		}

	}

	public String getPageTitle(){
		return driver.getTitle();
	}
	
	public void type(By element, String string){
		driver.findElement(element).clear();
		driver.findElement(element).sendKeys(string);
	}
	
	public void click(By element){
		int count =0;
		while (count <5) {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(element));
				driver.findElement(element).click();
				break;
			} catch (Exception e) {
				System.out.println("Count: " + count + " click exception" );
				count = count + 1;
			}
		}
	}
	
	public String getElementText(By element){
		return driver.findElement(element).getText();
	}
	
	public String getUrl(){
		return driver.getCurrentUrl();
	}

	public void clickOn3Link(By element, By element1, By element2){
		int count = 0;
		while (count < 5) {
			try {
				click(element);
				wait.until(ExpectedConditions.elementToBeClickable(element1));
				click(element1);
				wait.until(ExpectedConditions.elementToBeClickable(element2));
				click(element2);
				break;
			} catch (Exception e) {
				System.out.println("Count: " + count + " click exception" );
				count = count + 1;
			}
		}
	}

	public void clickOn2Link(By element, By element1){
		int count = 0;
		while (count < 5) {
			try {
				click(element);
				wait.until(ExpectedConditions.elementToBeClickable(element1));
				click(element1);
				break;
			} catch (Exception e) {
				System.out.println("Count: " + count + " click exception" );
				count = count + 1;
			}
		}
	}

}

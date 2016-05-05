package ru.fors.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by Alexander Zhaleiko on 04.05.2016.
 */
public class MatchesPageDE extends ObjectsPage{
    public MatchesPageDE(WebDriver driver) {
        super(driver);
    }

    By pageTitle = By.xpath("//th[text()='Spiele']");
    By matchDate = By.id("P2041_TGM_DATE");
    By team2Field = By.id("P2041_TGM_RIVAL");

    @Step("Проверяем открылась ли страница матчи")
    public boolean isMatchesPageLoaded(){
        return ensurePageLoaded(pageTitle);
    }

    @Step("Проверяем открылась ли страница создания матча")
    public boolean isMatchCreatedPageLoaded(){
        return ensurePageLoaded(matchDate);
    }
    @Step("Пользователь устанавливает дату матча")
    public void userTypeMatchDate(){
        type(matchDate, date.format(currentDate));
        System.out.println("Date: "+date.format(yesterday));
    }
    @Step("Пользователь выбирает команду")
    public void userSelectTeam1() {
        Select select = new Select(driver.findElement(By.id("P2041_TGM_TEAM_ID")));
        select.selectByVisibleText("Dinamo192912");
    }
    @Step("Пользователь выбирает состав")
    public void userSelectRoster(){
        Select select = new Select(driver.findElement(By.id("P2041_TGM_TEAM_TP_CODE")));
        select.selectByValue("TEAM1");
    }

    @Step("Пользователь выбирает тип соревнования")
    public void userSelectCompetitionType(){
        Select select = new Select(driver.findElement(By.id("P2041_TGM_TYPE_CODE")));
        select.selectByValue("FRIEND");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("P2041_TGM_TOURNAMENT_ID")));
    }

    @Step("Пользователь выбирает тип выбора соперника")
    public void userSelectTypeTeam2(){
        Select select = new Select(driver.findElement(By.id("P2041_RIVAL_ENTER_TYPE")));
        select.selectByValue("2");
        wait.until(ExpectedConditions.elementToBeClickable(team2Field));
    }

    @Step("Пользователь вводит команду соперника")
    public void userTypeTeam2(){
        type(team2Field, "FC Belshina Bobryisk");
    }

    @Step("Пользователь выбирает время начала")
    public void userSelectStartTime(){
        Select select = new Select(driver.findElement(By.id("P2041_TGM_TIME_HOUR")));
        select.selectByValue("11");
        Select select1 = new Select(driver.findElement(By.id("P2041_TGM_TIME_MIN")));
        select1.selectByValue("15");
    }

    @Step("Пользователь выбирает время конца матча")
    public void userSelectEndTime(){
        Select select = new Select(driver.findElement(By.id("P2041_TGM_TIME_HOUR_END")));
        select.selectByValue("13");
    }

    @Step("Пользователь создает матч")
    public void userCreateMatch(){
        userTypeMatchDate();
        userSelectTeam1();
        userSelectRoster();
        userSelectCompetitionType();
        userSelectTypeTeam2();
        userTypeTeam2();
        userSelectStartTime();
        userSelectEndTime();
        userClickCreateButton();
    }





}

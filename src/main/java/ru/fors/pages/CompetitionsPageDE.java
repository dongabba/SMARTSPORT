package ru.fors.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;
import java.util.Set;

/**
 * Created by Alexander Zhaleyko on 12.05.2016.
 */
public class CompetitionsPageDE extends ObjectsPage{
    public CompetitionsPageDE(WebDriver driver) {
        super(driver);
    }

    private By pageTitle = By.xpath("//th[text()='Wettkämpfe']");
    private By createNewCompetitionsPage = By.xpath("//th[text()='Wichtigste Angaben']");
    private By citySelectButton = By.xpath(".//*[@id='P2210_COUNTRY_ID_holder']//img");
    private By competitionName = By.id("P2210_NAME");
    private By startDate = By.id("P2210_DATE_START");
    private By endDate = By.id("P2210_DATE_END");
    private By teamCount = By.id("P2210_TEAM_COUNT");

    public boolean isCompetitionsPageLoaded(){
        return ensurePageLoaded(pageTitle);
    }

    public boolean isCreateNewCompetitionsPageLoaded(){
        return ensurePageLoaded(createNewCompetitionsPage);
    }

    public void userCreateCompetition(){
        userSelectCity();
        userTypeCompetitionName();
        userSetStartDate();
        userSetEndDate();
        userSetTeamCount();
        userClickCreateButton();
    }

    private void userSetTeamCount() {
        type(teamCount, "4");
    }

    private void userSetEndDate() {
        type(endDate, date.format(competitionEndDate));
    }

    private void userSetStartDate() {
        type(startDate, date.format(currentDate));
    }

    private void userTypeCompetitionName() {
        Random random = new Random();
        competition.setName("championship_"+Integer.toString(random.nextInt(1000)));
        type(competitionName, competition.getName());
    }

    private void userSelectCity() {
        String originalWindow = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();
        click(citySelectButton);
        String newWindow = (new WebDriverWait(driver, 10))
                .until(new ExpectedCondition<String>() {
                           public String apply(WebDriver driver) {
                               Set<String> newWindowsSet = driver.getWindowHandles();
                               newWindowsSet.removeAll(oldWindowsSet);
                               return newWindowsSet.size() > 0 ?
                                       newWindowsSet.iterator().next() : null;
                           }
                       }
                );
        driver.switchTo().window(newWindow);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(getValueFromList(cityForTeams))));
        click(By.linkText(getValueFromList(cityForTeams)));
        driver.switchTo().window(originalWindow);
    }

}

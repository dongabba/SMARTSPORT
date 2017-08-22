package ru.fors.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.Set;

/**
 * Created by Alexander Zhaleiko on 12.05.2016.
 */
public class ScoutedPlayerPageDE extends ObjectsPage {
    ScoutedPlayerPageDE(WebDriver driver) {
        super(driver);
    }

    private By pageTitle = By.xpath("//th[text()='Spielerübersicht']");
    private By creatScoutedPlayerPageTitle = By.xpath("//th[text()='Wichtigste Angaben']");
    private By playerNameField = By.id("P208_FAM");
    private By playerFamilyField = By.id("P208_NAM");
    private By playerBornDate = By.id("P208_BORN_DATE");
    private By playerBornYear = By.id("P208_BORN_YEAR");
    private By playerPosition = By.id("P208_POSITIONS_COL3_2");
    private By editButton = By.linkText("bearbeiten");
    private By button = By.linkText("hinzufügen");
    private By clubPageLink = By.linkText("Transfer in den Klub");
    private By transferToClubDate = By.id("P218_IN_DATE");
    private By teamNameFromField = By.id("P208_TEAM_NAME");
    private By citySelectButton = By.xpath(".//*[@id='P208_TEAM_CITY_holder']//img");
    private By selectorSelectButton = By.xpath(".//*[@id='P208_SELECTOR_ID_holder']//img");


    @Step("Проверяем открылась ли страница просматриваемых игроков")
    public boolean isScoutedPlayersPageOpen(){
        return ensurePageLoaded(pageTitle);
    }
    @Step("Проверяем открылась ли страница создания просматриваемого игрока")
    public boolean isCreateScoutedPlayerPageOpen(){
        return ensurePageLoaded(creatScoutedPlayerPageTitle);
    }
    @Step("Пользователь создает просматриваемого игрока")
    public void userCreateScoutedPlayer(){
        userTypeName();
        userTypeFamily();
        userSetBirthDate();
        userTypeBirthDate();
        userSetPosition();
        userSelectTeamEnterType();
        userTypeTeamNameFrom();
        userSelectCity();
        userSelectCareerType();
        userSelectSelector();
        userClickCreateButton();
    }
    @Step("Пользователь указывает позицию игрока")
    private void userSetPosition() {
        click(playerPosition);
    }
    @Step("Пользователь указывает год игрока")
    private void userTypeBirthDate() {
        type(playerBornYear, "1984");
    }
    @Step("Пользователь указывает дату рождения игрока")
    private void userSetBirthDate() {
        type(playerBornDate, "22.06.1984");
    }
    @Step("Пользователь указывает фамилию игрока")
    private void userTypeFamily() {
        scoutedPlayer.setFamily(getValueFromList(familysDE));
        type(playerFamilyField, scoutedPlayer.getFamily());
    }
    @Step("Пользователь указывает имя игрока")
    private void userTypeName() {
        scoutedPlayer.setName(getValueFromList(namesDE));
        type(playerNameField, scoutedPlayer.getName());
    }
    @Step("Проверяем создался ли игрок")
    public boolean isScoutedPlayerCreated(){
        try
        {
            wait.until(ExpectedConditions.visibilityOfElementLocated(editButton));
            return true;
        } catch (Exception e){
            return false;
        }
    }
    @Step("Пользователь переводит игрока в клуб")
    public void userTransferScoutedPlayerToClub() {
        userSearchPlayer();
        userOpenPlayer();
        userGoToClubPage();
        userClickToButton();
        userSetDate();
        userClickCreateButton();
    }
    @Step("Пользователь указывает дату")
    private void userSetDate() {
        type(transferToClubDate, date.format(currentDate));
    }

    private void userClickToButton() {
        click(button);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//th[text()='Transfer in den Klub']")));
    }
    @Step("Пользователь открывает страницу клуба")
    private void userGoToClubPage() {
        click(clubPageLink);
        wait.until(ExpectedConditions.visibilityOfElementLocated(button));
    }
    @Step("Пользователь открывает игрока")
    private void userOpenPlayer() {
        click(searchResultLink);
        wait.until(ExpectedConditions.visibilityOfElementLocated(clubPageLink));
    }
    @Step("Пользователь выполняет поиск игрока")
    private void userSearchPlayer() {
        type(searchField, scoutedPlayer.getFamily());
        click(findButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchComplete));
    }

    @Step("Пользователь выбирает тип ввода команды вручную")
    private void userSelectTeamEnterType(){
        Select select = new Select(driver.findElement(By.id("P208_TEAM_ENTER_TYPE")));
        select.selectByValue("2");
    }

    @Step("Пользователь вводит название команды")
    private void userTypeTeamNameFrom(){
        type(teamNameFromField, "Dinamo");
    }

    @Step("Пользователь выбирает город")
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
        //wait.until(ExpectedConditions.invisibilityOfElementLocated(cityLink));
        driver.switchTo().window(originalWindow);
    }

    @Step("Пользователь выбирает тип карьеры")
    private void userSelectCareerType(){
        Select select = new Select(driver.findElement(By.id("P208_CAREER_TYPE_ID")));
        select.selectByValue("36938");
    }

    @Step("Пользователь выбирает селекционера")
    private void userSelectSelector() {
        String originalWindow = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();
        click(selectorSelectButton);
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
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("ккк к.к.")));
        click(By.linkText("ккк к.к."));
        //wait.until(ExpectedConditions.invisibilityOfElementLocated(cityLink));
        driver.switchTo().window(originalWindow);
    }
}

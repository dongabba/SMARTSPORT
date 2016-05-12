package ru.fors.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by Alexander Zhaleiko on 12.05.2016.
 */
public class ScoutedPlayerPageDE extends ObjectsPage {
    public ScoutedPlayerPageDE(WebDriver driver) {
        super(driver);
    }

    private By pageTitle = By.xpath("//th[text()='Spielerübersicht']");
    private By creatScoutedPlayerPageTitle = By.xpath("//th[text()='Wichtigste Angaben']");
    private By playerNameField = By.id("P208_FAM");
    private By playerFamilyField = By.id("P208_NAM");
    private By playerBornDate = By.id("P208_BORN_DATE");
    private By playerBornYear = By.id("P208_BORN_YEAR");
    private By playerPosition = By.id("P208_POSITIONS_COL3_4");
    private By editButton = By.linkText("bearbeiten");
    private By button = By.linkText("hinzufügen");
    private By clubPageLink = By.linkText("Transfer in den Klub");
    private By transferToClubDate = By.id("P218_IN_DATE");



    public boolean isScoutedPlayersPageOpen(){
        return ensurePageLoaded(pageTitle);
    }

    public boolean isCreateScoutedPlayerPageOpen(){
        return ensurePageLoaded(creatScoutedPlayerPageTitle);
    }

    public void userCreateScoutedPlayer(){
        userTypeName();
        userTypeFamily();
        userSetBirthDate();
        userTypeBirthDate();
        userSetPosition();
        userClickCreateButton();
    }

    private void userSetPosition() {
        click(playerPosition);
    }

    private void userTypeBirthDate() {
        type(playerBornYear, "1984");
    }

    private void userSetBirthDate() {
        type(playerBornDate, "22.06.1984");
    }

    private void userTypeFamily() {
        scoutedPlayer.setFamily(getValueFromList(familysDE));
        type(playerFamilyField, scoutedPlayer.getFamily());
    }

    private void userTypeName() {
        scoutedPlayer.setName(getValueFromList(namesDE));
        type(playerNameField, scoutedPlayer.getName());
    }

    public boolean isScoutedPlayerCreated(){
        try
        {
            wait.until(ExpectedConditions.visibilityOfElementLocated(editButton));
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public void userTransferScoutedPlayerToClub() {
        userSearchPlayer();
        userOpenPlayer();
        userGoToClubPage();
        userClickToButton();
        userSetDate();
        userClickCreateButton();
    }

    private void userSetDate() {
        type(transferToClubDate, date.format(currentDate));
    }

    private void userClickToButton() {
        click(button);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//th[text()='Transfer in den Klub']")));
    }

    private void userGoToClubPage() {
        click(clubPageLink);
        wait.until(ExpectedConditions.visibilityOfElementLocated(button));
    }

    private void userOpenPlayer() {
        click(searchResultLink);
        wait.until(ExpectedConditions.visibilityOfElementLocated(clubPageLink));
    }

    private void userSearchPlayer() {
        type(searchField, scoutedPlayer.getFamily());
        click(findButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchComplete));
    }

}

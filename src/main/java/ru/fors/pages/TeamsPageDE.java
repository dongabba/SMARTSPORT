package ru.fors.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.fors.data.Coach;
import ru.fors.data.Player;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.Random;
import java.util.Set;

/**
 * Created by Alexander Zhaleiko on 04.05.2016.
 */
public class TeamsPageDE extends ObjectsPage {
    public TeamsPageDE(WebDriver driver) {
        super(driver);
    }

    private By pageTitle = By.xpath("//th[text()='Mannschaften']");
    private By teamLink = By.linkText("Mannschaften");
    private By createTeamPageTitle = By.linkText("Mannschaft");
    private By teamNameField = By.id("P1306_NAME");
    private By shortTeamNameField = By.id("P1306_SHORT_NAME");
    private By citySelectButton = By.xpath("//*[@id='P1306_CITY_ID_holder']//img");
    private By descriptionField = By.id("P1306_DESCRIPTION");
    private By searchField = By.id("apexir_SEARCH");
    private By searchResultLink = By.xpath("//tbody/tr[2]/td[1]/a/img");
    private By searchComplete = By.id("apexir_CONTROL_PANEL_COMPLETE");
    private By findButton = By.id("apexir_btn_SEARCH");
    private By teamPlayersLink = By.linkText("Spieler");
    private By teamPlayersTitle = By.xpath("//th[text()='Spieler']");
    private By addPlayerButton = By.linkText("hinzufügen");
    private By selectPlayerButton = By.xpath(".//*[@id='P313_PLAYER_ID_holder']//img");
    private By selectCoachButton = By.xpath(".//*[@id='P353_COACH_ID_holder']//img");
    private By playerNumber = By.id("P313_PLAYER_NUMBER");
    private By teamCoachesLink = By.linkText("Trainerrat");
    private By teamCoachesTitle = By.xpath("//th[text()='Trainerrat']");



    public boolean ensureTeamsPageLoaded() {
        return ensurePageLoaded(pageTitle);
    }

    public boolean ensureCreateTeamPageLoaded() {
        return ensurePageLoaded(createTeamPageTitle);
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
        //wait.until(ExpectedConditions.invisibilityOfElementLocated(cityLink));
        driver.switchTo().window(originalWindow);
    }

    @Step("Пользователь указывает имя команды")
    private void userTypeTeamName(String teamName) {
        type(teamNameField, teamName);
    }
    @Step("Пользователь указывает сокращенное имя команды")
    private void userTypeShortTeamName(String shortTeamName) {
        type(shortTeamNameField, shortTeamName);
    }
    @Step("Пользователь указывает возрастную группу команды")
    private void userSelectAgeGroup() {
        Select select = new Select(driver.findElement(By.id("P1306_AGE_GROUP")));
        select.selectByValue("394");
    }
    
    @Step("Пользователь указывает описание для команды")
    private void userTypeDescription(String description) {
        type(descriptionField, description);
    }

    @Step("Пользователь создает команду")
    public void userCreateTeam() {
        Random random = new Random();
        team.setName(getValueFromList(teamsDe)+Integer.toString(random.nextInt(99)));
        userTypeTeamName(team.getName());
        userTypeShortTeamName("FC");
        userSelectCity();
        userSelectAgeGroup();
        userTypeDescription(team.getName());
        userClickCreateButton();
    }

    @Step("Пользователь редактирует команду")
    public void userEditTeamForAddPlayers() {
        userSearchTeam(team.getName());
        userOpenTeam();
        userGoToPlayersTeam();
    }
    @Step("Пользователь редактирует команду")
    public void userEditTeamForAddCoaches() {
        userSearchTeam(team.getName());
        userOpenTeam();
        userGoToCoachesTeam();
    }
    @Step("Пользователь переходит в раздел тренеры команды")
    private void userGoToCoachesTeam() {
        click(teamCoachesLink);
        wait.until(ExpectedConditions.visibilityOfElementLocated(teamCoachesTitle));
    }


    @Step("Пользователь переходит в раздел команды")
    public void userClickTeamsLink() {
        click(teamLink);
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
    }

    @Step("Пользователь выполняет поиск команды")
    private void userSearchTeam(String teamName) {
        type(searchField, teamName);
        System.out.println("Выполняем поиск команды: " + team.getName());
        click(findButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchComplete));
    }

    @Step("Пользователь открывает найденную команду")
    private void userOpenTeam() {
        click(searchResultLink);
        wait.until(ExpectedConditions.visibilityOfElementLocated(createTeamPageTitle));
    }

    @Step("Пользователь переходит в раздел игроки команды")
    private void userGoToPlayersTeam() {
        click(teamPlayersLink);
        wait.until(ExpectedConditions.visibilityOfElementLocated(teamPlayersTitle));
    }

    @Step("Пользователь нажмимает кнопку добавить игрока")
    private void userClickAddPlayerButton(){
        click(addPlayerButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(selectPlayerButton));

    }

    @Step("Пользователь нажмимает кнопку добавить тренерв")
    private void userClickAddCoachButton(){
        click(addPlayerButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(selectCoachButton));

    }

    @Step("Пользователь выбирает игрока в команду")
    private void userSelectPlayerForTeam(String playerFamily, String playerName){
        By player = By.linkText(playerFamily+" "+playerName);
        String originalWindow = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();
        click(selectPlayerButton);
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
        wait.until(ExpectedConditions.elementToBeClickable(player));
        click(player);
        //wait.until(ExpectedConditions.invisibilityOfElementLocated(cityLink));
        driver.switchTo().window(originalWindow);
    }

    @Step("Пользователь выбирает тренера в команду")
    private void userSelectCoachForTeam(String coachFamily, String coachName){
        By coach = By.linkText(coachFamily+" "+coachName);
        String originalWindow = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();
        click(selectCoachButton);
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
        wait.until(ExpectedConditions.elementToBeClickable(coach));
        click(coach);
        //wait.until(ExpectedConditions.invisibilityOfElementLocated(cityLink));
        driver.switchTo().window(originalWindow);
    }

    @Step("Пользователь указывает номер игрока")
    private void userTypePlayerNumber(){
        Random random = new Random();
        type(playerNumber, Integer.toString(random.nextInt(99)+1));
    }
    @Step("Пользователь указывает основную позицию игрока")
    private void userSelectPlayerPosition(){
        String [] playerPositionValues = {"22913", "22988", "22989", "22990"};
        Random random = new Random();
        Select select = new Select(driver.findElement(By.id("P313_POSITION_MAIN_ID")));
        select.selectByValue(playerPositionValues[random.nextInt(4)]);
    }

    @Step("Пользователь указывает дополнительную позицию игрока")
    private void userSelectPlayerSubPosition(){
        String [] playerSubPositionValues = {"23138","22914","23108","23000"
                ,"23110"
                ,"23109"
                ,"23107"
                ,"23116"
                ,"23112"
                ,"23115"
                ,"23113"
                ,"23114"
                ,"23111"
                ,"23117"
                ,"23118"
                ,"23119"
                ,"23120"
                ,"23121"
                ,"23122"
                ,"23123"
                ,"23124"
                ,"23125"
                ,"23126"
                ,"23127"
                ,"23128"
                ,"23129"
                ,"23130"
                ,"23149"
                ,"23150"};
        Random random = new Random();
        Select select = new Select(driver.findElement(By.id("P313_POSITION_ID")));
        select.selectByValue(playerSubPositionValues[random.nextInt(28)]);
    }

    
    @Step("Пользователь добавляет тренера в команду")
    public void userAddCoachToTeam(){
        System.out.println("coachList size: "+coachList.size());
        for (Coach coach: coachList){
            userClickAddCoachButton();
            userSelectCoachForTeam(coach.getFamily(), coach.getName());
            userClickCreateButton();
            wait.until(ExpectedConditions.presenceOfElementLocated(successMessage));
        }

    }

    @Step("Пользователь добавляет игроков в команду")
    public void userAddPlayersToTeam(){
        System.out.println("playerList size: "+playerList.size());
        for (Player player : playerList){
            userClickAddPlayerButton();
            wait.until(ExpectedConditions.visibilityOfElementLocated(selectPlayerButton));
            userSelectPlayerForTeam(player.getFamily(), player.getName());
            userTypePlayerNumber();
            userSelectPlayerPosition();
            userSelectPlayerSubPosition();
            userClickCreateButton();
            wait.until(ExpectedConditions.presenceOfElementLocated(successMessage));
        }

    }



}

package ru.fors.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.fors.data.Team;
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

    By pageTitle = By.xpath("//th[text()='Mannschaften']");
    By teamLink = By.linkText("Mannschaften");
    By createTeamPageTitle = By.xpath("//th[text()='Basisinformationen']");
    By teamNameField = By.id("P1306_NAME");
    By shortTeamNameField = By.id("P1306_SHORT_NAME");
    By citySelectButton = By.xpath("//*[@id='P1306_CITY_ID_holder']//img");
    By ageGroupSelect = By.id("P1306_AGE_GROUP");
    By descriptionField = By.id("P1306_DESCRIPTION");
    By searchField = By.id("apexir_SEARCH");
    By searchResultLink = By.xpath("//tbody/tr[2]/td[1]/a/img");
    By searchComplete = By.id("apexir_CONTROL_PANEL_COMPLETE");
    By findButton = By.id("apexir_btn_SEARCH");
    By teamPlayersLink = By.linkText("Spieler");
    By teamPlayersTitle = By.xpath("//th[text()='Spieler']");
    By addPlayerButton = By.linkText("hinzufügen");
    By selectPlayerButton = By.xpath(".//*[@id='P313_PLAYER_ID_holder']//img");
    By selectCoachButton = By.xpath(".//*[@id='P353_COACH_ID_holder']//img");
    By playerNumber = By.id("P313_PLAYER_NUMBER");
    By teamCoachesLink = By.linkText("Trainerrat");
    By teamCoachesTitle = By.xpath("//th[text()='Trainerrat']");



    public boolean ensureTeamsPageLoaded() {
        return ensurePageLoaded(pageTitle);
    }

    public boolean ensurecreateTeamPageLoaded() {
        return ensurePageLoaded(createTeamPageTitle);
    }


    public void userSelectCity() {
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

    public void userTypeTeamName(String teamName) {
        type(teamNameField, teamName);
    }

    public void userTypeShortTeamName(String shortTeamName) {
        type(shortTeamNameField, shortTeamName);
    }

    public void userSelectAgeGroup() {
        Select select = new Select(driver.findElement(By.id("P1306_AGE_GROUP")));
        select.selectByValue("394");
    }

    public void userTypeDescription(String description) {
        type(descriptionField, description);
    }

    public void userCreateTeam() {
        team.setName(getValueFromList(teamsDe));
        userTypeTeamName(team.getName());
        userTypeShortTeamName("FC");
        userSelectCity();
        userSelectAgeGroup();
        userTypeDescription(team.getName());
        userClickCreateButton();
        System.out.println("Создана команда: " + team.getName());
    }

    public void userEditTeam() {
        userSearchTeam(team.getName());
        userOpenTeam();
        userGoToPlayersTeam();
    }

    public void userEditTeam2() {
        userSearchTeam(team.getName());
        userOpenTeam();
        userGoToCoachesTeam();
    }

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
    public void userSearchTeam(String teamName) {
        type(searchField, teamName);
        System.out.println("Выполняем поиск команды: " + team.getName());
        click(findButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchComplete));
    }

    @Step("Пользователь открывает найденную команду")
    public void userOpenTeam() {
        click(searchResultLink);
        wait.until(ExpectedConditions.visibilityOfElementLocated(createTeamPageTitle));
    }

    @Step("Пользователь переходит в раздел игроки команды")
    public void userGoToPlayersTeam() {
        click(teamPlayersLink);
        wait.until(ExpectedConditions.visibilityOfElementLocated(teamPlayersTitle));
    }

    @Step("Пользователь нажмимает кнопку добавить игрока")
    public void userClickAddPlayerButton(){
        click(addPlayerButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(selectPlayerButton));

    }

    @Step("Пользователь нажмимает кнопку добавить тренерв")
    public void userClickAddCoachButton(){
        click(addPlayerButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(selectCoachButton));

    }

    @Step("Пользователь выбирает игрока в команду")
    public void userSelectPlayerForTeam(String playerFamily, String playerName){
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

    @Step("Пользователь добавляет тренера в команду")
    public void userSelectCoachForTeam(String coachFamily, String coachName){
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
    public void userTypePlayerNumber(){
        Random random = new Random();
        type(playerNumber, Integer.toString(random.nextInt(99)));
    }
    @Step("Пользователь указывает основную позицию игрока")
    public void userSelectPlayerPosition(){
        String [] playerPositionValues = {"22913", "22987", "22988", "22989", "22990"};
        Random random = new Random();
        Select select = new Select(driver.findElement(By.id("P313_POSITION_MAIN_ID")));
        select.selectByValue(playerPositionValues[random.nextInt(4)]);
    }

    @Step("Пользователь указывает дополнительную позицию игрока")
    public void userSelectPlayerSubPosition(){
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


    @Step("Пользователь добавляет игрока в команду")
    public void userAddPlayerToTeam(){
        userClickAddPlayerButton();
        userSelectPlayerForTeam(player.getFamily(), player.getName());
        userTypePlayerNumber();
        userSelectPlayerPosition();
        userSelectPlayerSubPosition();
        userClickCreateButton();
    }

    @Step("Пользователь добавляет тренера в команду")
    public void userAddCoachToTeam(){
        System.out.println("coachList size: "+coachList.size());
        for (int i=0; i<coachList.size();i++){
            userClickAddCoachButton();
            userSelectCoachForTeam(coachList.get(i).getFamily(), coachList.get(i).getName());
            userClickCreateButton();
            wait.until(ExpectedConditions.presenceOfElementLocated(successMessage));
        }

    }

    @Step("Пользователь добавляет игрока в команду")
    public void userAddPlayerToTeam2(){
        System.out.println("playerList size: "+playerList.size());
        for (int i=0; i<playerList.size();i++){
            userClickAddPlayerButton();
            userSelectPlayerForTeam(playerList.get(i).getFamily(), playerList.get(i).getName());
            userTypePlayerNumber();
            userSelectPlayerPosition();
            userSelectPlayerSubPosition();
            userClickCreateButton();
            wait.until(ExpectedConditions.presenceOfElementLocated(successMessage));
        }

    }



}

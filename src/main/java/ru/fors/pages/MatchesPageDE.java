package ru.fors.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.fors.data.Coach;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.Random;
import java.util.Set;

/**
 * Created by Alexander Zhaleiko on 04.05.2016.
 */
public class MatchesPageDE extends ObjectsPage{
    MatchesPageDE(WebDriver driver) {
        super(driver);
    }

    private By pageTitle = By.xpath("//th[text()='Spiele']");
    private By protocolpageTitle = By.xpath("//th[text()='Protokoll']");
    private By matchDate = By.id("P2041_TGM_DATE");
    private By team2Field = By.id("P2041_TGM_RIVAL");
    private By statusField = By.id("P2043_TGM_STATUS_CODE_DISPLAY");
    private By editMatchButton = By.linkText("bearbeiten");
    private By deleteMatchButton = By.linkText("löschen");
    private By saveMatchButton = By.linkText("speichern");
    private By closeGameButton = By.linkText("spiel beenden");
    private By editProtocolButton = By.id("B351828634086123992");
    private By addEventToProtocolButton = By.linkText("hinzufügen");
    private By cancelButton = By.linkText("zurück");
    private By raitings1stPlayer = By.xpath(".//*[@id='report_R426286155205963830']/tbody/tr[2]//tr[3]/td[6]");
    private By comprtitionSelectButton = By.xpath(".//*[@id='P2041_TGM_TOURNAMENT_ID_holder']//img");
    private static int raitingsCount = 0;


    @Step("Проверяем открылась ли страница матчи")
    public boolean isMatchesPageLoaded(){
        return ensurePageLoaded(pageTitle);
    }

    @Step("Проверяем открылась ли страница создания матча")
    public boolean isMatchCreatedPageLoaded(){
        return ensurePageLoaded(matchDate);
    }
    @Step("Пользователь устанавливает дату матча")
    private void userTypeMatchDate(){
        type(matchDate, date.format(currentDate));
        System.out.println("Date: "+date.format(yesterday));
    }
    @Step("Пользователь выбирает команду")
    private void userSelectTeam1() {
        Select select = new Select(driver.findElement(By.id("P2041_TGM_TEAM_ID")));
        select.selectByVisibleText(team.getName());
    }
    @Step("Пользователь выбирает состав")
    private void userSelectRoster(){
        Select select = new Select(driver.findElement(By.id("P2041_TGM_TEAM_TP_CODE")));
        select.selectByValue("TEAM1");
    }

    @Step("Пользователь выбирает тип соревнования")
    private void userSelectCompetitionType(){
        Select select = new Select(driver.findElement(By.id("P2041_TGM_TYPE_CODE")));
        select.selectByVisibleText("Wettbewerb");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("P2041_TGM_TOURNAMENT_ID")));
    }

    @Step("Пользователь выбирает название соревнования")
    private void userSelectCompetitionName(){
        String originalWindow = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();
        click(comprtitionSelectButton);
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
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(competition.getName())));
        click(By.linkText(competition.getName()));
        //wait.until(ExpectedConditions.invisibilityOfElementLocated(cityLink));
        driver.switchTo().window(originalWindow);
    }

    @Step("Пользователь выбирает тип выбора соперника")
    private void userSelectTypeTeam2(){
        Select select = new Select(driver.findElement(By.id("P2041_RIVAL_ENTER_TYPE")));
        select.selectByValue("2");
        wait.until(ExpectedConditions.elementToBeClickable(team2Field));
    }

    @Step("Пользователь вводит команду соперника")
    private void userTypeTeam2(){
        type(team2Field, "FC Torpedo Moscow");
    }

    @Step("Пользователь выбирает время начала")
    private void userSelectStartTime(){
        Select select = new Select(driver.findElement(By.id("P2043_TGM_TIME_HOUR")));
        select.selectByValue("11");
        Select select1 = new Select(driver.findElement(By.id("P2043_TGM_TIME_MIN")));
        select1.selectByValue("15");
    }

    @Step("Пользователь выбирает время конца матча")
    private void userSelectEndTime(){
        Select select = new Select(driver.findElement(By.id("P2043_TGM_TIME_HOUR_END")));
        select.selectByValue("13");
    }

    @Step("Пользователь создает матч")
    public void userCreateMatch(){
        userTypeMatchDate();
        userSelectTeam1();
        userSelectRoster();
        userSelectCompetitionType();
        userSelectCompetitionName();
        userSelectTypeTeam2();
        userTypeTeam2();
        userClickCreateButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        wait.until(ExpectedConditions.visibilityOfElementLocated(statusField));
    }

    @Step("Пользователь проверяет статус матча")
    public String checkMatchStatus(){
        return getElementText(statusField);
    }

    @Step("Пользователь ищет матч")
    private void userFindMatch(){
        userSearchObject(team.getName());
    }

    @Step("Пользователь открывает найденный матч")
    private void userOpenMatch() {
        click(searchResultLink);
        wait.until(ExpectedConditions.visibilityOfElementLocated(statusField));
    }

    @Step("Пользователь меняет статус матча на Запланированно")
    public void userReplaceMatchStatusToPlan(){
        userFindMatch();
        userOpenMatch();
        click(editMatchButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(deleteMatchButton));
        userSelectStartTime();
        userSelectEndTime();
        click(saveMatchButton);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(deleteMatchButton));
        wait.until(ExpectedConditions.visibilityOfElementLocated(statusField));
    }

    @Step("Пользователь меняет статус матча на Завершен")
    public void userReplaceMatchStatusToApply(){
        userFindMatch();
        userOpenMatch();
        click(editMatchButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(closeGameButton));
        click(closeGameButton);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(closeGameButton));
        wait.until(ExpectedConditions.visibilityOfElementLocated(statusField));
    }

    @Step("Пользователш добавляет событие Гол в протокол")
    private void userAddEventToMatchProtocol(String event1, String id){
        Select player = new Select(driver.findElement(By.id("f04_"+id)));
        Random random = new Random();
        int i = random.nextInt(playerList.size()-1);
        player.selectByVisibleText(playerList.get(i).getFamily()+" "+playerList.get(i).getName()+" ("+team.getName()+")");
        Select event = new Select(driver.findElement(By.id("f05_"+id)));
        event.selectByVisibleText(event1);
        int time = random.nextInt(90);
        type(By.id("f06_"+id), Integer.toString(time));
    }

    @Step("Пользователь редактирует протокол матча")
    public void userEditMatchProtocol(){
        userFindMatch();
        userOpenMatch();
        click(editMatchButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(closeGameButton));
        click(editProtocolButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(protocolpageTitle));
        click(addEventToProtocolButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("f04_0001")));
        userAddEventToMatchProtocol("Tor", "0001");
        click(addEventToProtocolButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("f04_0002")));
        userAddEventToMatchProtocol("Gelbe Karten", "0002");
        click(addEventToProtocolButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("f04_0003")));
        userAddEventToMatchProtocol("Tor", "0003");
        click(addEventToProtocolButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("f04_0004")));
        userAddEventToMatchProtocol("Verschossene Elfmeter", "0004");
        click(saveMatchButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        click(cancelButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(editMatchButton));

    }
    @Step("Пользователь выбирает отображение рейтинга")
    private void userSelectRaitings(){
        Select select = new Select(driver.findElement(By.id("P2043_TYPE_VIEW")));
        select.selectByVisibleText("Rating");
        wait.until(ExpectedConditions.presenceOfElementLocated(raitings1stPlayer));
    }
    @Step("Пользователь тренера для выстапления/отображения рейтинга")
    private void userSelectCoach(String family, String name){
        Select coach = new Select(driver.findElement(By.id("P2041_COACH")));
        coach.selectByVisibleText(family+" "+name);
    }


    @Step("Пользователь проставляет рейтинг игрокам")
    public void userSetPlayerRaiting(){
        userFindMatch();
        userOpenMatch();
        click(editMatchButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(closeGameButton));
        userSelectRaitings();
        for (Coach coach:coachList){
            userSelectCoach(coach.getFamily(), coach.getName());
            wait.until(ExpectedConditions.presenceOfElementLocated(raitings1stPlayer));
            for (int i=0; i<playerList.size(); i++){
                int p=i+3;
                click(By.xpath(".//*[@id='report_R426286155205963830']/tbody/tr[2]//tr["+p+"]/td[6]//div[2]"));
                click(By.xpath(".//*[@id='report_R426286155205963830']/tbody/tr[2]//tr["+p+"]/td[7]//div[5]"));
                click(By.xpath(".//*[@id='report_R426286155205963830']/tbody/tr[2]//tr["+p+"]/td[8]//div[4]"));
                click(By.xpath(".//*[@id='report_R426286155205963830']/tbody/tr[2]//tr["+p+"]/td[9]//div[5]"));
                click(By.xpath(".//*[@id='report_R426286155205963830']/tbody/tr[2]//tr["+p+"]/td[10]//div[6]"));
            }
        }
        click(saveMatchButton);
    }

    @Step("Пользователь проверяет рейтинг игроков")
    public void userCheckPlayerRaiting(){
        userFindMatch();
        userOpenMatch();
        click(editMatchButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(closeGameButton));
        userSelectRaitings();
        for (Coach coach:coachList){
            userSelectCoach(coach.getFamily(), coach.getName());
            wait.until(ExpectedConditions.presenceOfElementLocated(raitings1stPlayer));
            for (int i=0; i<playerList.size(); i++){
                int p=i+3;
                if (driver.findElement(By.xpath("//*[@id='report_R426286155205963830']/tbody/tr[2]//tr["+p+"]/td[11]//div[4]")).getAttribute("class").contains("star-rating-on")){
                    raitingsCount=raitingsCount+1;
                }
            }
        }
        click(saveMatchButton);
    }

    public boolean isRaitingExist(){
        return raitingsCount == 4;
    }







}

package ru.fors.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.fors.data.Coach;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.Set;

/**
 * Created by Alexander Zhaleiko on 05.05.2016.
 */
public class CoachPageDE extends ObjectsPage{
    public CoachPageDE(WebDriver driver) {
        super(driver);
    }
    By pageTitle = By.xpath("//th[text()='Kontakte']");
    By createdCoachPageTitle = By.xpath("//th[text()='Basisinformationen']");
    By coachFamily = By.id("P23_FAM");
    By coachName = By.id("P23_NAM");
    By coachBirthPlace = By.id("P23_BIRTH_PLACE");
    By coachBornDate = By.id("P23_BORN_DD");
    By contactPage = By.linkText("Kontakte");
    By coachPage = By.linkText("Trainer");
    By coachTeams = By.cssSelector("a[href*='P23_MENU_SELECTED:TEAMS']");
    By coachTeamsTitle = By.xpath("//th[text()='Mannschaften ']");
    By addTeamLink = By.linkText("hinzufügen");
    By teamLink = By.xpath(".//*[@id='P352_TEAM_ID_holder']//img");
    By trainingTeamStartDate = By.id("P352_START_DATE");


    @Step("Проверяем открылась ли страница тренеры")
    public boolean isCoachPageLoaded(){
        return ensurePageLoaded(pageTitle);
    }

    @Step("Проверяем открылась ли страница создания тренера")
    public boolean isCoachCreatedPageLoaded(){
        return ensurePageLoaded(createdCoachPageTitle);
    }

    @Step("Пользователь указыват имя тренера")
    public void userTypeCoachName(String name){
        type(coachName, name);
    }

    @Step("Пользователь указыват фамилию тренера")
    public void userTypeCoachFamily(String family){
        type(coachFamily, family);
    }

    @Step("Пользователь указыват город рождения тренера")
    public void userTypeCoachBirthPlace(){
        type(coachBirthPlace, getValueFromList(citiesDE));
    }

    @Step("Пользователь указыват год рождения тренера")
    public void userTypeCoachBornDate(){
        type(coachBornDate, "18.01.1971");
    }

    /*@Step("Пользователь создает тренера")
    public void userCreateCoach(){
        userTypeCoachFamily();
        userTypeCoachName();
        userTypeCoachBirthPlace();
        userTypeCoachBornDate();
        userClickCreateButton();
        coachList.add(coach);
    }
    */

    @Step("Пользователь создает тренера")
    public void userCreateCoach2(){
        for (int i=0; i<3; i++){
            userGoToCoaches();
            userClickCreateButton();
            wait.until(ExpectedConditions.presenceOfElementLocated(coachFamily));
            Coach coach = new Coach();
            coach.setFamily(getValueFromList(familysDE));
            coach.setName(getValueFromList(namesDE));
            userTypeCoachFamily(coach.getFamily());
            userTypeCoachName(coach.getName());
            userTypeCoachBirthPlace();
            userTypeCoachBornDate();
            userClickCreateButton();
            wait.until(ExpectedConditions.presenceOfElementLocated(successMessage));
            userGoToContacts();
            coachList.add(coach);
        }
    }

    public void printCoaches(){
        for (int i=0; i< coachList.size(); i++){
            System.out.println(coachList.get(i).getName()+" "+coachList.get(i).getFamily());
        }
    }

    @Step("Пользователь переходит в раздел Контакты")
    public void userGoToContacts(){
        click(contactPage);
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
    }

    @Step("Пользователь переходит в раздел Тренеры")
    public void userGoToCoaches(){
        click(coachPage);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("P12_ACTUAL_SELECTOR")));
    }

    @Step("Пользователь отображает всех тренеров")
    public void userSelectAllCoaches(){
        Select select = new Select(driver.findElement(By.id("P12_ACTUAL_SELECTOR")));
        select.selectByValue("ALL");
       // wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(coach.getFamily())));
    }

    /*@Step("Пользователь открывает созданного тренера")
    public void userOpenCoaches(){
        click(By.linkText(coach.getFamily()));
        wait.until(ExpectedConditions.visibilityOfElementLocated(createdCoachPageTitle));
    }
    */
    @Step("Пользователь переходит к редактированию команды тренера")
    public void userGoToCoachTeam(){
        click(coachTeams);
        wait.until(ExpectedConditions.visibilityOfElementLocated(coachTeamsTitle));
    }

    @Step("Пользователь переходит к странице добавления команды для тренера")
    public void userOpenAddTeamPage(){
        click(addTeamLink);
        wait.until(ExpectedConditions.visibilityOfElementLocated(teamLink));
    }

    @Step("Пользователь назначает тренеру команду")
    public void userSelectCoachTeam(){
        String originalWindow = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();
        click(teamLink);
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
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(team.getName())));
        click(By.linkText(team.getName()));
        //wait.until(ExpectedConditions.invisibilityOfElementLocated(cityLink));
        driver.switchTo().window(originalWindow);
    }

    @Step("Пользователь дату начала работы тренера в команде")
    public void userSetStartDate(){
        type(trainingTeamStartDate, date.format(currentDate));
    }

    @Step("Пользователь устанавливает команду для тренера")
    public void userSetCoachTeam(){
        userGoToContacts();
        userGoToCoaches();
        userSelectAllCoaches();
       // userOpenCoaches();
        userGoToCoachTeam();
        userOpenAddTeamPage();
        userSelectCoachTeam();
        userSetStartDate();
        userClickCreateButton();
    }

}

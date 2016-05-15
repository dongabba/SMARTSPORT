package ru.fors.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by Alexander Zhaleiko on 02.05.2016.
 */
public class MainPageDE extends MainPage {
    public MainPageDE(WebDriver driver) {
        super(driver);
    }
    private By logoutLink = By.linkText("Beenden"); //кнопка Выход
    private By teamsLink = By.linkText("Mannschaften"); //команды
    private By matchesLink = By.linkText("Spiele"); //матчи
    private By mainPageLink = By.linkText("Startseite");//главная
    private By exercisesPageLink = By.linkText("Register der Übungen"); //реестр упражнений
    private By trainingsPageLink = By.linkText("Trainings");
    private By competitionsPageLink = By.linkText("Wettkämpfe");
    private By employeeLink = By.linkText("Mitarbeiter");
    private By eventPageLink = By.linkText("Ereignisse");
    private By adsPageLink = By.linkText("Ankündigungen");
    private By scoutedPlayersPageLink = By.linkText("Spielerübersicht"); //просматриваемые игроки



    @Step("Проверяем открылась ли страница")
    public boolean isPageLoaded(){
        return ensurePageLoaded(logoutLink);
    }

    @Step("Пользователь выходит из системы")
    public LoginPage userLogOut(){
        return userLogOut(logoutLink);
    }

    @Step("Пользователь переходит в раздел команды")
    public TeamsPageDE userGoToTeamsPage(){
        click(teamsLink);
        return new TeamsPageDE(driver);
    }

    @Step("Пользователь переходит в раздел матчи")
    public MatchesPageDE userGoToMatchesPage(){
        click(matchesLink);
        return new MatchesPageDE(driver);
    }


    @Step("Пользователь открывает главную страницу")
    public void userGoToMainPage(){
        click(mainPageLink);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("R371129962098587179")));
    }

    @Step("Пользователь переходит на страницу реестр упражнений")
    public ExercisesPageDE userGoToExercisesPage(){
        click(exercisesPageLink);
        return new ExercisesPageDE(driver);
    }

    @Step("Пользователь переходит на страницу тренировки")
    public TrainingsPageDE userGoToTrainingsPage(){
        click(trainingsPageLink);
        return new TrainingsPageDE(driver);
    }

    @Step("Пользователь переходит на страницу соревнования")
    public CompetitionsPageDE userGoToCompetitionsPage(){
        click(competitionsPageLink);
        return new CompetitionsPageDE(driver);
    }

    @Step("Пользователь переходит на страницу сотрудники")
    public EmployeePageDE userGoToEmployeePage(){
        click(employeeLink);
        return new EmployeePageDE(driver);
    }

    @Step("Пользователь переходит на страницу события")
    public EventPageDE userGoToEventsPage(){
        click(eventPageLink);
        return new EventPageDE(driver);
    }

    @Step("Пользователь переходит на страницу объявления")
    public AdsPageDE userGoToAdsPage(){
        click(adsPageLink);
        return new AdsPageDE(driver);
    }

    @Step("Пользователь переходит на страницу просматриваемых игроков")
    public ScoutedPlayerPageDE userGoToScoutedPlayerPage(){
        click(scoutedPlayersPageLink);
        return new ScoutedPlayerPageDE(driver);
    }

}

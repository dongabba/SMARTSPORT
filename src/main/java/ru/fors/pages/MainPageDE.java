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
    By logoutLink = By.linkText("Beenden"); //кнопка Выход
    By teamsLink = By.linkText("Mannschaften"); //команды
    By playersLink = By.linkText("Spieler"); //игроки
    By matchesLink = By.linkText("Spiele"); //матчи
    By coachLink = By.linkText("Trainer"); //тренеры
    By mainPageLink = By.linkText("Startseite");//главная
    By exercisesPageLink = By.linkText("Register der Übungen"); //реестр упражнений



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

    @Step("Пользователь переходит в раздел игроки")
    public PlayersPageDE userGoToPlayersPage(){
        click(playersLink);
        return new PlayersPageDE(driver);
    }

    @Step("Пользователь переходит в раздел матчи")
    public MatchesPageDE userGoToMatchesPage(){
        click(matchesLink);
        return new MatchesPageDE(driver);
    }

    @Step("Пользователь переходит в раздел тренеры")
    public CoachPageDE userGoToСoachesPage(){
        click(coachLink);
        return new CoachPageDE(driver);
    }

    @Step("Пользователь открывает главную страницу")
    public void userGoToMainPage(){
        click(mainPageLink);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("R453335050170429910")));
    }

    @Step("Пользователь переходит на страницу реестр упражнений")
    public ExercisesPageDE userGoToExercisesPage(){
        click(exercisesPageLink);
        return new ExercisesPageDE(driver);
    }

}

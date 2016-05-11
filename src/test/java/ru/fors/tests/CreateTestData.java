package ru.fors.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fors.pages.*;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import static org.junit.Assert.assertTrue;

/**
 * Created by Alexander Zhaleyko on 10.05.2016.
 */
public class CreateTestData extends TestBase{
    @BeforeMethod
    @Parameters({"username", "password"})
    public void testStatus(String username, String password){
        if (driver == null){
            init();
            userLogin(username, password);
        } if (driver.getTitle().equals("Login")){
            userLogin(username, password);
        }
    }

    @Features("Создание тестовых данных")
    @Stories("Создание тренеров")
    @Test
    public void userCreateCoachTest(){
        MainPage mainPage = new MainPage(driver);
        MainPageDE mainPageDE = mainPage.userGoToMainPageDE();
        CoachPageDE coachPageDE = new CoachPageDE(driver);
        coachPageDE.userCreateCoach2();
        coachPageDE.printCoaches();
    }

    @Features("Создание тестовых данных")
    @Stories("Создание игрока")
    @Test
    public void userCreatePlayerTest(){
        MainPage mainPage = new MainPage(driver);
        MainPageDE mainPageDE = mainPage.userGoToMainPageDE();
        PlayersPageDE playersPageDE = new PlayersPageDE(driver);
        playersPageDE.userCreatePlayer();
        playersPageDE.userPrintPlayers();
    }

    @Features("Работа с объектом \"Команда\"")
    @Stories("Создание команды")
    @Test
    public void userCreateTeamTest(){
        MainPage mainPage = new MainPage(driver);
        MainPageDE mainPageDE = mainPage.userGoToMainPageDE();
        TeamsPageDE teamsPageDE = mainPageDE.userGoToTeamsPage();
        assertTrue("Не открылся раздел Команды", teamsPageDE.ensureTeamsPageLoaded());
        teamsPageDE.userClickCreateButton();
        assertTrue("Не открылся раздел создания команды", teamsPageDE.ensurecreateTeamPageLoaded());
        teamsPageDE.userCreateTeam();
        assertTrue("Не создалась команда", teamsPageDE.isCreatedOk());
    }
    @Features("Работа с объектом \"Команда\"")
    @Stories("Добавление в команду тренеров")
    @Test
    public void userAddCoachesToTeamTest(){
        TeamsPageDE teamsPageDE = new TeamsPageDE(driver);
        teamsPageDE.userClickTeamsLink();
        teamsPageDE.userEditTeam2();
        teamsPageDE.userAddCoachToTeam();
    }

    @Features("Работа с объектом \"Команда\"")
    @Stories("Добавление в команду игроков")
    @Test
    public void userAddPlayersToTeamTest(){
        TeamsPageDE teamsPageDE = new TeamsPageDE(driver);
        teamsPageDE.userClickTeamsLink();
        teamsPageDE.userEditTeam();
        teamsPageDE.userAddPlayerToTeam2();
    }

    @Features("Работа с объектом \"Матч\"")
    @Stories("Создание матча")
    @Test
    public void userCreateMatchTest(){
        MainPage mainPage = new MainPage(driver);
        MainPageDE mainPageDE = mainPage.userGoToMainPageDE();
        mainPageDE.userGoToMainPage();
        MatchesPageDE matchesPageDE = mainPageDE.userGoToMatchesPage();
        assertTrue("Не открылся раздел Матч", matchesPageDE.isMatchesPageLoaded());
        matchesPageDE.userClickCreateButton();
        assertTrue("Не открылся раздел создания матча", matchesPageDE.isMatchCreatedPageLoaded());
        matchesPageDE.userCreateMatch();
        assertTrue("Не создалася матч", matchesPageDE.isCreatedOk());
        assertTrue("Статус матча отличный от Новый", matchesPageDE.checkMatchStatus().equals("Neue"));
    }

    @Features("Работа с объектом \"Матч\"")
    @Stories("Изменение статуса матча с Новый на Запланированно")
    @Test
    public void userChangeMatchStatusToPlanTest(){
        MainPage mainPage = new MainPage(driver);
        MainPageDE mainPageDE = mainPage.userGoToMainPageDE();
        mainPageDE.userGoToMainPage();
        MatchesPageDE matchesPageDE = mainPageDE.userGoToMatchesPage();
        assertTrue("Не открылся раздел Матч", matchesPageDE.isMatchesPageLoaded());
        matchesPageDE.userReplaceMatchStatusToPlan();
        assertTrue("Статус матча не изменился на Запланированно", matchesPageDE.checkMatchStatus().equals("Geplante"));
    }

    @Features("Работа с объектом \"Матч\"")
    @Stories("Изменение статуса матча с Новый на Запланированно")
    @Test
    public void userChangeMatchStatusToApplyTest(){
        MainPage mainPage = new MainPage(driver);
        MainPageDE mainPageDE = mainPage.userGoToMainPageDE();
        mainPageDE.userGoToMainPage();
        MatchesPageDE matchesPageDE = mainPageDE.userGoToMatchesPage();
        assertTrue("Не открылся раздел Матч", matchesPageDE.isMatchesPageLoaded());
        matchesPageDE.userReplaceMatchStatusToApply();
        assertTrue("Статус матча не изменился на Завершен", matchesPageDE.checkMatchStatus().equals("Durchgeführte"));
    }

    @Features("Работа с объектом \"Матч\"")
    @Stories("Изменение протокола матча")
    @Test
    public void userChangeMatchProtocolTest(){
        MainPage mainPage = new MainPage(driver);
        MainPageDE mainPageDE = mainPage.userGoToMainPageDE();
        mainPageDE.userGoToMainPage();
        MatchesPageDE matchesPageDE = mainPageDE.userGoToMatchesPage();
        assertTrue("Не открылся раздел Матч", matchesPageDE.isMatchesPageLoaded());
        matchesPageDE.userEditMatchProtocol();
        //assertTrue("Статус матча не изменился на Завершен", matchesPageDE.checkMatchStatus().equals("Durchgeführte"));
    }

    @Features("Работа с объектом \"Матч\"")
    @Stories("Изменение протокола матча")
    @Test
    public void userSetPlayerRaitingsTest(){
        MainPage mainPage = new MainPage(driver);
        MainPageDE mainPageDE = mainPage.userGoToMainPageDE();
        mainPageDE.userGoToMainPage();
        MatchesPageDE matchesPageDE = mainPageDE.userGoToMatchesPage();
        assertTrue("Не открылся раздел Матч", matchesPageDE.isMatchesPageLoaded());
        matchesPageDE.userSetPlayerRaiting();
        //assertTrue("Статус матча не изменился на Завершен", matchesPageDE.checkMatchStatus().equals("Durchgeführte"));
    }

}

package ru.fors.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fors.pages.*;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import static org.junit.Assert.assertTrue;

/**
 * Created by Alexander Zhaleiko on 04.05.2016.
 */
public class TeamsTests extends TestBase{
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
    @Stories("Редактирование команды")
    @Test
    public void userEditTeamTest(){
        TeamsPageDE teamsPageDE = new TeamsPageDE(driver);
        teamsPageDE.userClickTeamsLink();
        teamsPageDE.userEditTeam();
        teamsPageDE.userAddPlayerToTeam();
        assertTrue("Не добавился игрок в команду", teamsPageDE.isCreatedOk());
    }

    @Features("Работа с объектом \"Игрок\"")
    @Stories("Создание игрока")
    @Test
    public void userCreatePlayerTest(){
        MainPage mainPage = new MainPage(driver);
        MainPageDE mainPageDE = mainPage.userGoToMainPageDE();
        PlayersPageDE playersPageDE = mainPageDE.userGoToPlayersPage();
        assertTrue("Не открылся раздел Игроки", playersPageDE.ensurePlayersPageOpen());
        playersPageDE.userClickCreateButton();
        assertTrue("Не открылся раздел создания игрока", playersPageDE.ensureCreatePageOpen());
        playersPageDE.userCreatePlayer();
        assertTrue("Не создалася игрок", playersPageDE.isCreatedOk());
    }

    @Features("Работа с объектом \"Матч\"")
    @Stories("Создание матча")
    @Test
    public void userCreateMatchTest(){
        MainPage mainPage = new MainPage(driver);
        MainPageDE mainPageDE = mainPage.userGoToMainPageDE();
        MatchesPageDE matchesPageDE = mainPageDE.userGoToMatchesPage();
        assertTrue("Не открылся раздел Матч", matchesPageDE.isMatchesPageLoaded());
        matchesPageDE.userClickCreateButton();
        assertTrue("Не открылся раздел создания матча", matchesPageDE.isMatchCreatedPageLoaded());
        matchesPageDE.userCreateMatch();
        assertTrue("Не создалася матч", matchesPageDE.isCreatedOk());
    }

    @Features("Работа с объектом \"Тренер\"")
    @Stories("Создание тренера")
    @Test
    public void userCreateCoachTest(){
        MainPage mainPage = new MainPage(driver);
        MainPageDE mainPageDE = mainPage.userGoToMainPageDE();
        CoachPageDE coachPageDE = new CoachPageDE(driver);
        for (int i=0; i<3; i++) {
            mainPageDE.userGoToСoachesPage();
            assertTrue("Не открылся раздел Тренер", coachPageDE.isCoachPageLoaded());
            coachPageDE.userClickCreateButton();
            assertTrue("Не открылся раздел создания тренера", coachPageDE.isCoachCreatedPageLoaded());
            //oachPageDE.userCreateCoach();
            assertTrue("Не создалася тренер", coachPageDE.isCreatedOk());
            coachPageDE.userGoToContacts();
        }
        coachPageDE.printCoaches();
    }

    @Features("Работа с объектом \"Тренер\"")
    @Stories("Добавление тренера в команду")
    @Test
    public void userAddCoachToTeamTest(){
        MainPage mainPage = new MainPage(driver);
        MainPageDE mainPageDE = mainPage.userGoToMainPageDE();
        mainPageDE.userGoToMainPage();
        CoachPageDE coachPageDE = new CoachPageDE(driver);
        coachPageDE.userSetCoachTeam();
        assertTrue("Тренер не добавлен в команду", coachPageDE.isCreatedOk());
    }
}

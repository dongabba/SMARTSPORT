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
        teamsPageDE.userEditTeam2();
        teamsPageDE.userAddCoachToTeam();
    }

}

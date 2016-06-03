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
        } if (driver.getTitle().equals("SmartSport")){
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
        assertTrue("Не открылся раздел создания команды", teamsPageDE.ensureCreateTeamPageLoaded());
        teamsPageDE.userCreateTeam();
        assertTrue("Не создалась команда", teamsPageDE.isCreatedOk());
    }
    @Features("Работа с объектом \"Команда\"")
    @Stories("Добавление в команду тренеров")
    @Test
    public void userAddCoachesToTeamTest(){
        TeamsPageDE teamsPageDE = new TeamsPageDE(driver);
        teamsPageDE.userClickTeamsLink();
        teamsPageDE.userEditTeamForAddCoaches();
        teamsPageDE.userAddCoachToTeam();
        assertTrue("Ошибка при добавлении игроков", teamsPageDE.isCreatedOk());
    }

    @Features("Работа с объектом \"Команда\"")
    @Stories("Добавление в команду игроков")
    @Test
    public void userAddPlayersToTeamTest(){
        TeamsPageDE teamsPageDE = new TeamsPageDE(driver);
        teamsPageDE.userClickTeamsLink();
        teamsPageDE.userEditTeamForAddPlayers();
        teamsPageDE.userAddPlayersToTeam();
        assertTrue("Ошибка при добавлении игроков", teamsPageDE.isCreatedOk());
    }
}

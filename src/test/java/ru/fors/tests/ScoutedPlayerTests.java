package ru.fors.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fors.pages.MainPageDE;
import ru.fors.pages.ScoutedPlayerPageDE;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import static org.junit.Assert.assertTrue;

/**
 * Created by Alexander Zhaleiko on 15.05.2016.
 */
public class ScoutedPlayerTests extends TestBase{
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


    @Features("Работа с объектом \"Просматриваемый игрок\"")
    @Stories("Создание просматриваемого игрока")
    @Test
    public void userCreateScoutedPlayerTest(){
        MainPageDE mainPageDE = new MainPageDE(driver);
        mainPageDE.userGoToMainPage();
        ScoutedPlayerPageDE scoutedPlayerPageDE = mainPageDE.userGoToScoutedPlayerPage();
        assertTrue("Не открылась страница Просматриваемые игроки", scoutedPlayerPageDE.isScoutedPlayersPageOpen());
        scoutedPlayerPageDE.userClickCreateButton();
        assertTrue("Не открылась страница создания просматриваемого игрока", scoutedPlayerPageDE.isCreateScoutedPlayerPageOpen());
        scoutedPlayerPageDE.userCreateScoutedPlayer();
       //assertTrue("Не удалось создать просматриваемого игрока", scoutedPlayerPageDE.isScoutedPlayerCreated());
    }

    @Features("Работа с объектом \"Просматриваемый игрок\"")
    @Stories("Добавление просматриваемого игрока в команду")
    @Test
    public void userAddScoutedPlayerToTeamTest(){
        MainPageDE mainPageDE = new MainPageDE(driver);
        mainPageDE.userGoToMainPage();
        ScoutedPlayerPageDE scoutedPlayerPageDE = mainPageDE.userGoToScoutedPlayerPage();
        assertTrue("Не открылась страница Просматриваемые игроки", scoutedPlayerPageDE.isScoutedPlayersPageOpen());
        scoutedPlayerPageDE.userTransferScoutedPlayerToClub();
        //assertTrue("Не удалось добавить просматриваемого игрока в клуб", scoutedPlayerPageDE.isCreatedOk());
    }
}

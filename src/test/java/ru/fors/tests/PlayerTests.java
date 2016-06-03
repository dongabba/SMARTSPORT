package ru.fors.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fors.pages.MainPage;
import ru.fors.pages.MainPageDE;
import ru.fors.pages.PlayersPageDE;
import ru.fors.pages.TeamsPageDE;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import static org.junit.Assert.assertTrue;

/**
 * Created by Alexander Zhaleiko on 04.05.2016.
 */
public class PlayerTests extends TestBase{
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

    @Features("Работа с объектом \"Игрок\"")
    @Stories("Создание первого игрока")
    @Test
    public void userCreateFirstPlayerTest(){
        PlayersPageDE playersPageDE = new PlayersPageDE(driver);
        playersPageDE.userCreatePlayer();
        assertTrue("Ошибка при сохранении игрока", playersPageDE.isCreatedOk());
        playersPageDE.userPrintPlayers();
    }

    @Features("Работа с объектом \"Игрок\"")
    @Stories("Создание второго игрока")
    @Test
    public void userCreateSecondPlayerTest(){
        PlayersPageDE playersPageDE = new PlayersPageDE(driver);
        playersPageDE.userCreatePlayer();
        assertTrue("Ошибка при сохранении игрока", playersPageDE.isCreatedOk());
        playersPageDE.userPrintPlayers();
    }


}

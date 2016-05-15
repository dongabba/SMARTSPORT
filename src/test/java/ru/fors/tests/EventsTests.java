package ru.fors.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fors.pages.EventPageDE;
import ru.fors.pages.MainPage;
import ru.fors.pages.MainPageDE;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import static org.junit.Assert.assertTrue;

/**
 * Created by Alexander Zhaleiko on 15.05.2016.
 */
public class EventsTests extends TestBase {
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
    @Features("Работа с объектом \"Событие\"")
    @Stories("Создание события")
    @Test
    public void userCreateEventTest(){
        MainPageDE mainPageDE = new MainPageDE(driver);
        mainPageDE.userGoToMainPage();
        EventPageDE eventPageDE = mainPageDE.userGoToEventsPage();
        assertTrue("Не открылась страница События", eventPageDE.isEventsPageOpen());
        eventPageDE.userClickCreateButton();
        assertTrue("Не открылась страница создвния события", eventPageDE.isCreateEventPageOpen());
        eventPageDE.userCreateEvent();
        assertTrue("Не удалось создать событие", eventPageDE.isCreatedOk());
    }
}


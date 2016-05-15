package ru.fors.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fors.pages.CoachPageDE;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import static org.junit.Assert.assertTrue;

/**
 * Created by Alexander Zhaleiko on 15.05.2016.
 */
public class CoachTests extends TestBase{
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

    @Features("Работа с объектом \"Тренер\"")
    @Stories("Создание первого тренера")
    @Test
    public void userCreateFirstCoachTest(){
        CoachPageDE coachPageDE = new CoachPageDE(driver);
        coachPageDE.userCreateCoach();
        assertTrue("Ошибка при сохранении тренера", coachPageDE.isCreatedOk());
        coachPageDE.printCoaches();
    }

    @Features("Работа с объектом \"Тренер\"")
    @Stories("Создание второго тренера")
    @Test
    public void userCreateSecondCoachTest(){
        CoachPageDE coachPageDE = new CoachPageDE(driver);
        coachPageDE.userCreateCoach();
        assertTrue("Ошибка при сохранении тренера", coachPageDE.isCreatedOk());
        coachPageDE.printCoaches();
    }
}

package ru.fors.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fors.pages.ExercisesPageDE;
import ru.fors.pages.MainPage;
import ru.fors.pages.MainPageDE;
import ru.fors.pages.TrainingsPageDE;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import static org.junit.Assert.assertTrue;

/**
 * Created by Alexander Zhaleiko on 15.05.2016.
 */
public class TrainingTests extends TestBase {
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








    @Features("Работа с объектом \"Реестр упражнений\"")
    @Stories("Создание группы упражнений")
    @Test
    public void userCreateExercisesGroupTest(){
        MainPageDE mainPageDE = new MainPageDE(driver);
        mainPageDE.userGoToMainPage();
        ExercisesPageDE exercisesPageDE = mainPageDE.userGoToExercisesPage();
        assertTrue("Не открылся раздел Реестр упражнений", exercisesPageDE.isExercisesPageOpen());
        exercisesPageDE.userClickCreateExercisesGroupButton();
        assertTrue("Не открылась страница создания группы упражнений", exercisesPageDE.isCreateExercisesGroupPageOpen());
        exercisesPageDE.userCreateExercisesGroup();
        assertTrue("Не удалось создать группу упражнений", exercisesPageDE.isCreatedOk());
    }

    @Features("Работа с объектом \"Реестр упражнений\"")
    @Stories("Создание упражнения")
    @Test
    public void userCreateExercisesTest(){
        MainPageDE mainPageDE = new MainPageDE(driver);
        mainPageDE.userGoToMainPage();
        ExercisesPageDE exercisesPageDE = mainPageDE.userGoToExercisesPage();
        assertTrue("Не открылся раздел Реестр упражнений", exercisesPageDE.isExercisesPageOpen());
        exercisesPageDE.userCreateExercise();
        assertTrue("Не удалось создать группу упражнений", exercisesPageDE.isCreatedOk());
    }

    @Features("Работа с объектом \"Тренировка\"")
    @Stories("Создание тренировки")
    @Test
    public void userCreateTrainingTest(){
        MainPageDE mainPageDE = new MainPageDE(driver);
        mainPageDE.userGoToMainPage();
        TrainingsPageDE trainingsPageDE = mainPageDE.userGoToTrainingsPage();
        assertTrue("Не открылся раздел Тренировки", trainingsPageDE.isTrainingsPageOpen());
        trainingsPageDE.userClickCreateButton();
        assertTrue("Не открылась страница создания тренировки", trainingsPageDE.isCreateTrainingPageOpen());
        trainingsPageDE.userCreateTraining();
        assertTrue("Не удалось создать тренировку", trainingsPageDE.isCreatedOk());
    }

    @Features("Работа с объектом \"Тренировка\"")
    @Stories("Добавление в тренировку упражнения")
    @Test
    public void userAddExerciseToTrainingTest(){
        MainPageDE mainPageDE = new MainPageDE(driver);
        mainPageDE.userGoToMainPage();
        TrainingsPageDE trainingsPageDE = mainPageDE.userGoToTrainingsPage();
        assertTrue("Не открылся раздел Тренировки", trainingsPageDE.isTrainingsPageOpen());
        trainingsPageDE.userAddExerciseToTraining();
    }
}

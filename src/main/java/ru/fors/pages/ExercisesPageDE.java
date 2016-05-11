package ru.fors.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.Random;

/**
 * Created by Alexander Zhaleiko on 11.05.2016.
 */
public class ExercisesPageDE extends ObjectsPage{
    public ExercisesPageDE(WebDriver driver) {
        super(driver);
    }

    By pageTitle = By.xpath("//th[text()='Übungen']");
    By createExercisesPageTitle = By.xpath("//th[text()='Karteikarte der Gruppe Übungen']");
    By createExercisesGroupButton = By.linkText("gruppe hinzufugen");
    By exercisesGroupName = By.id("P2020_EXT_NAME");
    By exercisesGroupMission = By.id("P2020_EXT_MISSION");

    public boolean isExercisesPageOpen(){
        return ensurePageLoaded(pageTitle);
    }

    public boolean isCreateExercisesGroupPageOpen(){
        return ensurePageLoaded(createExercisesPageTitle);
    }
    @Step("Пользователь нажимает кнопку Добавить группу упражнений")
    public void userClickCreateExercisesGroupButton(){
        click(createExercisesGroupButton);
    }

    @Step("Пользователь вводит название группы упражнений")
    private void userTypeExercisesGroupName(){
        Random random = new Random();
        exercises.setExercisesGroup("group_"+Integer.toString(random.nextInt(1000)));
        type(exercisesGroupName, exercises.getExercisesGroup());
    }

    @Step("Пользователь указывает цель упражнений")
    private void userTypeExercisesGroupMission(){
        type(exercisesGroupMission, "Kiks");
    }

    @Step("Пользователь создает группу упражнений")
    public void userCreateExercisesGroup(){
        userTypeExercisesGroupName();
        userTypeExercisesGroupMission();
        userClickCreateButton();
    }





}

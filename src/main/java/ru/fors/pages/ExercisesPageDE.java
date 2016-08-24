package ru.fors.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.Random;

/**
 * Created by Alexander Zhaleiko on 11.05.2016.
 */
public class ExercisesPageDE extends ObjectsPage{
    ExercisesPageDE(WebDriver driver) {
        super(driver);
    }

    private By pageTitle = By.xpath("//th[text()='Übungen']");
    private By createExercisesGroupPageTitle = By.xpath("//th[text()='Register der Übungen']");
    private By createExercisesGroupButton = By.linkText("gruppe hinzufügen");
    private By exercisesGroupName = By.id("P2020_EXT_NAME");
    private By exercisesGroupMission = By.id("P2020_EXT_MISSION");
    private By createExercisesButton = By.linkText("übung hinzufügen");
    private By createExercisesPageTitle = By.xpath("//th[text()='Übungskarte']");
    private By exerciseName = By.id("P2020_EXR_NAME");
    private By exerciseAgeGroup18 = By.id("P2020_EXR_AGE_GROUPS_12");
    private By exerciseTimeMin = By.id("P2020_EXR_TIME_MIN");
    private By exerciseTimeMax = By.id("P2020_EXR_TIME_MAX");
    private By ball = By.id("f02_0002");
    private By gate = By.id("f02_0011");
    private By exerciseDescription = By.id("P2020_EXR_DESCRIPTION");

    public boolean isExercisesPageOpen(){
        return ensurePageLoaded(pageTitle);
    }

    public boolean isCreateExercisesGroupPageOpen(){
        return ensurePageLoaded(createExercisesGroupPageTitle);
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

    @Step("Пользователь выбирает группу упражнений")
    private void userSelectExercisesGroup(){
        String tooltip = exercises.getExercisesGroup();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[tooltip=\""+tooltip+"\"]")));
        click(By.cssSelector("a[tooltip=\""+tooltip+"\"]"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(createExercisesGroupPageTitle));
    }

    @Step("Пользователь нажимает кнопку создать упражнение")
    private void userClickCreateExerciseButton(){
        click(createExercisesButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(createExercisesPageTitle));
    }

    @Step("Пользователь заполняет данные для тренировки")
    private void userSetTrainingAttributes(){
        Random random = new Random();
        exercises.setExerciseName("exercise_"+Integer.toString(random.nextInt(1000)));
        type(exerciseName, exercises.getExerciseName());
        click(exerciseAgeGroup18);
        type(exerciseTimeMin, "15");
        type(exerciseTimeMax, "30");
        type(ball, "12");
        type(gate, "2");
        type(exerciseDescription, "Kiks to ball");
    }

    @Step("Пользователь создает упражнение")
    public void userCreateExercise(){
        userSelectExercisesGroup();
        userClickCreateExerciseButton();
        userSetTrainingAttributes();
        userClickCreateButton();
    }






}

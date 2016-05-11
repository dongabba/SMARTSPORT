package ru.fors.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by Alexander Zhaleiko on 11.05.2016.
 */
public class TrainingsPageDE extends ObjectsPage {
    public TrainingsPageDE(WebDriver driver) {
        super(driver);
    }

    By pageTitle = By.xpath("//th[text()='Trainings']");
    By pageEditTrainingTitle = By.xpath("//th[text()='Aufgaben']");
    By trainingDate = By.id("P2031_TRG_DATE");
    By timeFrom = By.id("P2031_TRG_FROM");
    By timeTo = By.id("P2031_TRG_TO");
    By saveTrainingButton = By.cssSelector("img[title='Erstellen']");
    By saveTrainingButton2 = By.cssSelector("img[title='Speichern']");
    By editTrainingButton = By.cssSelector("img[title='Bearbeiten']");
    By addExerciseButton = By.cssSelector("img[title='Добавить задачу']");
    By selectExerciseButton = By.linkText("wählen");
    By trainingNameCheckbox = By.cssSelector("input[type='checkbox']");

    public boolean isTrainingsPageOpen(){
        return ensurePageLoaded(pageTitle);
    }

    public boolean isCreateTrainingPageOpen(){
        return ensurePageLoaded(trainingDate);
    }

    private void userSetTrainingDate(){
        type(trainingDate,date.format(currentDate));
    }

    private void userSetTrainingTime(){
        type(timeFrom, "09:00");
        type(timeTo, "10:00");
    }

    private void userSelectTeam(){
        Select trgTeam = new Select(driver.findElement(By.id("P2031_TRG_TEAM_IDS")));
        trgTeam.selectByVisibleText(team.getName()+" (U-18)");
    }

    private void userSelectPlace(){
        Select stadium = new Select(driver.findElement(By.id("P2031_TRG_PLACE_ID")));
        stadium.selectByValue("45");
    }

    public void userClickSaveTrainingButton(){
        click(saveTrainingButton);
    }

    private void userClickSaveTrainingButton2(){
        click(saveTrainingButton2);
    }

    public void userCreateTraining(){
        userSetTrainingDate();
        userSetTrainingTime();
        userSelectTeam();
        userSelectPlace();
        userClickSaveTrainingButton();
    }

    @Step("Пользователь открывает найденную команду")
    private void userOpenTraining() {
        click(searchResultLink);
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageEditTrainingTitle));
    }

    private void userClickEditTrainingsButton(){
        click(editTrainingButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(addExerciseButton));
    }

    private void userClickAddExerciseButton(){
        click(addExerciseButton);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("page2039"));
    }


    public void userAddExerciseToTraining(){
        userSearchObject(team.getName());
        //userSearchObject("FC Union (U-18)");
        userOpenTraining();
        userClickEditTrainingsButton();
        userClickAddExerciseButton();
        userSelectExercise();
        userClickSelectExerciseButton();
        userClickSaveTrainingButton2();
    }

    private void userClickSelectExerciseButton() {
        click(selectExerciseButton);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(selectExerciseButton));
        driver.switchTo().defaultContent();

    }

    private void userSelectExercise() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(trainingNameCheckbox));
        click(trainingNameCheckbox);

    }


}

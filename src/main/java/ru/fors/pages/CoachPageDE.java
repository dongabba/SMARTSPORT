package ru.fors.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.fors.data.Coach;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by Alexander Zhaleiko on 05.05.2016.
 */
public class CoachPageDE extends ObjectsPage{
    public CoachPageDE(WebDriver driver) {
        super(driver);
    }
    private By coachFamily = By.id("P23_FAM");
    private By coachName = By.id("P23_NAM");
    private By coachBirthPlace = By.id("P23_BIRTH_PLACE");
    private By coachBornDate = By.id("P23_BORN_DD");

    private By coachPage = By.linkText("Trainer");

    @Step("Пользователь указывает имя тренера")
    private void userTypeCoachName(String name){
        type(coachName, name);
    }

    @Step("Пользователь указывает фамилию тренера")
    private void userTypeCoachFamily(String family){
        type(coachFamily, family);
    }

    @Step("Пользователь указывает город рождения тренера")
    private void userTypeCoachBirthPlace(){
        type(coachBirthPlace, getValueFromList(citiesDE));
    }

    @Step("Пользователь указывает дату рождения тренера")
    private void userTypeCoachBornDate(){
        type(coachBornDate, "18.01.1971");
    }


    @Step("Пользователь создает тренера")
    public void userCreateCoach(){
            userGoToContacts();
            userGoToCoaches();
            userClickCreateButton();
            wait.until(ExpectedConditions.presenceOfElementLocated(coachFamily));
            Coach coach = new Coach();
            coach.setFamily(getValueFromList(familysDE));
            coach.setName(getValueFromList(namesDE));
            userTypeCoachFamily(coach.getFamily());
            userTypeCoachName(coach.getName());
            userTypeCoachBirthPlace();
            userTypeCoachBornDate();
            userClickCreateButton();
            coachList.add(coach);
    }

    public void printCoaches(){
        for (Coach coach:coachList){
            System.out.println(coach.getName()+" "+coach.getFamily());
        }
    }

    @Step("Пользователь переходит в раздел Тренеры")
    private void userGoToCoaches(){
        click(coachPage);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("P12_ACTUAL_SELECTOR")));
    }
}

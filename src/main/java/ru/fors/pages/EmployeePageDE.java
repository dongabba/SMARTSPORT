package ru.fors.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by Alexander Zhaleiko on 12.05.2016.
 */
public class EmployeePageDE extends ObjectsPage{
    EmployeePageDE(WebDriver driver) {
        super(driver);
    }

    private By pageTitle = By.xpath("//th[text()='Kontakte']");
    private By createdEmployeePageTitle = By.xpath("//th[text()='Wichtigste Angaben']");
    private By employeeFamily = By.id("P23_FAM");
    private By employeeName = By.id("P23_NAM");
    private By employeeBirthPlace = By.id("P23_BIRTH_PLACE");
    private By employeeBornDate = By.id("P23_BORN_DD");


    public boolean isEmployeePageLoaded(){
        return ensurePageLoaded(pageTitle);
    }

    public boolean isCreateNewEmployeePageLoaded(){
        return ensurePageLoaded(createdEmployeePageTitle);
    }


    @Step("Пользователь указыват имя сотрудника")
    private void userTypeEmployeeName(String name){
        type(employeeName, name);
    }

    @Step("Пользователь указыват фамилию сотрудника")
    private void userTypeEmployeeFamily(String family){
        type(employeeFamily, family);
    }

    @Step("Пользователь указыват город рождения сотрудника")
    private void userTypeEmployeeBirthPlace(){
        type(employeeBirthPlace, getValueFromList(citiesDE));
    }

    @Step("Пользователь указыват год рождения сотрудника")
    private void userTypeEmployeeBornDate(){
        type(employeeBornDate, "18.01.1971");
    }

    @Step("Пользователь создает сотрудника")
    public void userCreateEmployee(){
        employee.setName(getValueFromList(namesDE));
        employee.setFamily(getValueFromList(familysDE));
        userTypeEmployeeFamily(employee.getFamily());
        userTypeEmployeeName(employee.getName());
        userTypeEmployeeBirthPlace();
        userTypeEmployeeBornDate();
        userClickCreateButton();
    }

}

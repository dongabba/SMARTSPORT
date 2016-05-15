package ru.fors.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fors.pages.EmployeePageDE;
import ru.fors.pages.MainPage;
import ru.fors.pages.MainPageDE;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import static org.junit.Assert.assertTrue;

/**
 * Created by Alexander Zhaleiko on 15.05.2016.
 */
public class EmployeeTests extends TestBase {

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

    @Features("Работа с объектом \"Сотрудник\"")
    @Stories("Создание сотрудника")
    @Test
    public void userCreateEmployeeTest(){
        MainPageDE mainPageDE = new MainPageDE(driver);
        mainPageDE.userGoToMainPage();
        EmployeePageDE employeePageDE = mainPageDE.userGoToEmployeePage();
        assertTrue("Не открылась страница Сотрудники", employeePageDE.isEmployeePageLoaded());
        employeePageDE.userClickCreateButton();
        assertTrue("Не открылась страница создвния сотрудника", employeePageDE.isCreateNewEmployeePageLoaded());
        employeePageDE.userCreateEmployee();
        assertTrue("Не удалось создать сотрудника", employeePageDE.isCreatedOk());
    }
}

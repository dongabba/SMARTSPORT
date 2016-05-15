package ru.fors.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.fors.pages.CompetitionsPageDE;
import ru.fors.pages.MainPage;
import ru.fors.pages.MainPageDE;
import ru.fors.pages.MatchesPageDE;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import static org.junit.Assert.assertTrue;

/**
 * Created by Alexander Zhaleiko on 05.05.2016.
 */
public class MatchTests extends TestBase {
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
    @Features("Работа с объектом \"Соревнование\"")
    @Stories("Создание соревнования")
    @Test
    public void userCreateCompetitionTest(){
        MainPageDE mainPageDE = new MainPageDE(driver);
        mainPageDE.userGoToMainPage();
        CompetitionsPageDE competitionsPageDE = mainPageDE.userGoToCompetitionsPage();
        assertTrue("Не открылась страница Соревнования", competitionsPageDE.isCompetitionsPageLoaded());
        competitionsPageDE.userClickCreateButton();
        assertTrue("Не открылась страница создвния соревнования", competitionsPageDE.isCreateNewCompetitionsPageLoaded());
        competitionsPageDE.userCreateCompetition();
        assertTrue("Не удалось создать соревнование", competitionsPageDE.isCreatedOk());
    }

    @Features("Работа с объектом \"Матч\"")
    @Stories("Создание матча")
    @Test
    public void userCreateMatchTest(){
        MainPageDE mainPageDE = new MainPageDE(driver);
        mainPageDE.userGoToMainPage();
        MatchesPageDE matchesPageDE = mainPageDE.userGoToMatchesPage();
        assertTrue("Не открылся раздел Матч", matchesPageDE.isMatchesPageLoaded());
        matchesPageDE.userClickCreateButton();
        assertTrue("Не открылся раздел создания матча", matchesPageDE.isMatchCreatedPageLoaded());
        matchesPageDE.userCreateMatch();
        assertTrue("Не создалася матч", matchesPageDE.isCreatedOk());
        assertTrue("Статус матча отличный от Новый", matchesPageDE.checkMatchStatus().equals("Neue"));
    }

    @Features("Работа с объектом \"Матч\"")
    @Stories("Изменение статуса матча с Новый на Запланированно")
    @Test
    public void userChangeMatchStatusToPlanTest(){
        MainPageDE mainPageDE = new MainPageDE(driver);
        mainPageDE.userGoToMainPage();
        MatchesPageDE matchesPageDE = mainPageDE.userGoToMatchesPage();
        assertTrue("Не открылся раздел Матч", matchesPageDE.isMatchesPageLoaded());
        matchesPageDE.userReplaceMatchStatusToPlan();
        assertTrue("Статус матча не изменился на Запланированно", matchesPageDE.checkMatchStatus().equals("Geplante"));
    }

    @Features("Работа с объектом \"Матч\"")
    @Stories("Изменение статуса матча с Запланированно на Завершен")
    @Test
    public void userChangeMatchStatusToApplyTest(){
        MainPageDE mainPageDE = new MainPageDE(driver);
        mainPageDE.userGoToMainPage();
        MatchesPageDE matchesPageDE = mainPageDE.userGoToMatchesPage();
        assertTrue("Не открылся раздел Матч", matchesPageDE.isMatchesPageLoaded());
        matchesPageDE.userReplaceMatchStatusToApply();
        assertTrue("Статус матча не изменился на Завершен", matchesPageDE.checkMatchStatus().equals("Durchgeführte"));
    }

    @Features("Работа с объектом \"Матч\"")
    @Stories("Изменение протокола матча")
    @Test
    public void userChangeMatchProtocolTest(){
        MainPageDE mainPageDE = new MainPageDE(driver);
        mainPageDE.userGoToMainPage();
        MatchesPageDE matchesPageDE = mainPageDE.userGoToMatchesPage();
        assertTrue("Не открылся раздел Матч", matchesPageDE.isMatchesPageLoaded());
        matchesPageDE.userEditMatchProtocol();
    }

    @Features("Работа с объектом \"Матч\"")
    @Stories("Выставление рейтинга игрокам")
    @Test
    public void userSetPlayerRaitingsTest(){
        MainPageDE mainPageDE = new MainPageDE(driver);
        mainPageDE.userGoToMainPage();
        MatchesPageDE matchesPageDE = mainPageDE.userGoToMatchesPage();
        assertTrue("Не открылся раздел Матч", matchesPageDE.isMatchesPageLoaded());
        matchesPageDE.userSetPlayerRaiting();
    }

    @Features("Работа с объектом \"Матч\"")
    @Stories("Проверка выставленного рейтинга игрокам")
    @Test
    public void userCheckPlayerRaitingsTest(){
        MainPageDE mainPageDE = new MainPageDE(driver);
        mainPageDE.userGoToMainPage();
        MatchesPageDE matchesPageDE = mainPageDE.userGoToMatchesPage();
        assertTrue("Не открылся раздел Матч", matchesPageDE.isMatchesPageLoaded());
        matchesPageDE.userCheckPlayerRaiting();
        assertTrue("Рейтинг не отображается", matchesPageDE.isRaitingExist());
    }
}

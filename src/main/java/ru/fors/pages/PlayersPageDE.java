package ru.fors.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import ru.fors.data.Player;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by Alexander Zhaleiko on 04.05.2016.
 */
public class PlayersPageDE extends ObjectsPage{
    public PlayersPageDE(WebDriver driver) {
        super(driver);
    }
    private By familyPlayerField = By.id("P23_FAM");
    private By namePlayerField = By.id("P23_NAM");
    private By birthPlacePlayer = By.id("P23_BIRTH_PLACE");
    private By bornDatePlayer = By.id("P23_BORN_DD");
    private By mainPositionCheckBox = By.id("P23_POSITIONS_COL_MAIN_3");
    private By subPositionCheckBox = By.id("P23_POSITIONS_COL3_2");
    private By playersLink = By.linkText("Spieler");
    private By playerStatusSelect = By.id("P12_ACTUAL_SELECTOR");
    @Step("Пользователь указывает фамилию игрока")
    private void userTypePlayerFamily(String family){
        type(familyPlayerField, family);
    }
    @Step("Пользователь указывает имя игрока")
    private void userTypePlayerName(String name){
        type(namePlayerField, name);
    }
    @Step("Пользователь указывает место рождения игрока")
    private void userTypePlayerBirthPlace(String place){
        type(birthPlacePlayer, place);
    }
    @Step("Пользователь указывает дату рождения игрока")
    private void userTypePlayerBornDate(String date){
        type(bornDatePlayer, date);
    }
    @Step("Пользователь указывает рабочую ногу игрока")
    private void userSelectPlayerFoot(){
        Select select = new Select(driver.findElement(By.id("P23_DOMIN_FOOT")));
        select.selectByValue("LEFT");
    }
    @Step("Пользователь указывает основную позицию игрока")
    private void userSelectPlayerPosition(){
        click(mainPositionCheckBox);
    }
    @Step("Пользователь указывает дополнительную позицию игрока")
    private void userSelectPlayerSubposition(){
        click(subPositionCheckBox);
    }
    @Step("Пользователь создает игрока")
    public void userCreatePlayer(){
            userGoToContacts();
            userGoToPlayersPage();
            userClickCreateButton();
            wait.until(ExpectedConditions.visibilityOfElementLocated(familyPlayerField));
            Player player = new Player();
            player.setName(getValueFromList(namesDE));
            player.setFamily(getValueFromList(familysDE));
            userTypePlayerFamily(player.getFamily());
            userTypePlayerName(player.getName());
            userTypePlayerBirthPlace(getValueFromList(citiesDE));
            userTypePlayerBornDate("01.08.1984");
            userSelectPlayerFoot();
            userSelectPlayerPosition();
            userSelectPlayerSubposition();
            userClickCreateButton();
            playerList.add(player);

    }

    public void userPrintPlayers(){
        for (Player player: playerList){
            System.out.println(player.getName()+" "+player.getFamily());
        }
    }
    @Step("Пользователь переходит в раздел игроки")
    private void userGoToPlayersPage() {
        click(playersLink);
        wait.until(ExpectedConditions.visibilityOfElementLocated(playerStatusSelect));
    }


}

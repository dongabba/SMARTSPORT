package ru.fors.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.fors.data.Player;

/**
 * Created by Alexander Zhaleiko on 04.05.2016.
 */
public class PlayersPageDE extends ObjectsPage{
    public PlayersPageDE(WebDriver driver) {
        super(driver);
    }
    By pageTitle = By.xpath("//th[text()='Kontakte']");
    By familyPlayerField = By.id("P23_FAM");
    By namePlayerField = By.id("P23_NAM");
    By birthPlacePlayer = By.id("P23_BIRTH_PLACE");
    By bornDatePlayer = By.id("P23_BORN_DD");
    By mainPositionCheckBox = By.id("P23_POSITIONS_COL_MAIN_3");
    By subPositionCheckBox = By.id("P23_POSITIONS_COL2_6");

    public boolean ensurePlayersPageOpen(){
        return ensurePageLoaded(pageTitle);
    }

    public boolean ensureCreatePageOpen(){
        return ensurePageLoaded(familyPlayerField);
    }

    public void userTypePlayerFamily(String family){
        type(familyPlayerField, family);
    }

    public void userTypePlayerName(String name){
        type(namePlayerField, name);
    }

    public void userTypePlayerBirthPlace(String place){
        type(birthPlacePlayer, place);
    }

    public void userTypePlayerBornDate(String date){
        type(bornDatePlayer, date);
    }

    public void userSelectPlayerFoot(){
        Select select = new Select(driver.findElement(By.id("P23_DOMIN_FOOT")));
        select.selectByValue("LEFT");
    }

    public void userSelectPlayerPosition(){
        click(mainPositionCheckBox);
    }

    public void userSelectPlayerSubposition(){
        click(subPositionCheckBox);
    }

    public void userCreatePlayer(){
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
    }




}

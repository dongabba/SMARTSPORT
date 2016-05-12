package ru.fors.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Alexander Zhaleyko on 12.05.2016.
 */
public class EventPageDE extends ObjectsPage{


    public EventPageDE(WebDriver driver) {
        super(driver);
    }

    private By pageTitle = By.xpath("//th[text()='Ereignisse']");
    private By createEventPageTitle = By.xpath("//th[text()='Wichtigste Angaben']");
    private By eventNameField = By.id("P2061_CEV_NAME");
    private By eventDateField = By.id("P2061_CEV_DATE");



    public boolean isEventsPageOpen(){
        return ensurePageLoaded(pageTitle);
    }

    public boolean isCreateEventPageOpen(){
        return ensurePageLoaded(createEventPageTitle);
    }

    public void userCreateEvent(){
        userSelectEventType();
        userSetEventName();
        userSetEventDate();
        userSetTimeFrom();
        userSetTimeTo();
        userClickCreateButton();
    }

    private void userSetTimeTo() {
        Select timeFrom = new Select(driver.findElement(By.id("P2061_CEV_TIME_HOUR")));
        timeFrom.selectByVisibleText("9");
    }

    private void userSetTimeFrom() {
        Select timeFrom = new Select(driver.findElement(By.id("P2061_CEV_TIME_HOUR_END")));
        timeFrom.selectByVisibleText("11");

    }

    private void userSetEventDate() {
        event.setDate(date.format(currentDate));
        type(eventDateField, event.getDate());
    }

    private void userSetEventName() {
        Random eventName = new Random();
        event.setName("event_"+eventName.nextInt(1000));
        type(eventNameField, event.getName());
    }

    private void userSelectEventType() {
        String [] eventTypes = {"Sonstiges", "Termin", "Mahlzeit", "Prozeduren", "Auswärtsspiel ", "Übung", "Medizinische Untersuchung", "Erinnerung"};
        Random random = new Random();
        event.setType(eventTypes[random.nextInt(7)]);
        Select eventType = new Select(driver.findElement(By.id("P2061_CEV_TYPE_CODE")));
        eventType.selectByVisibleText(event.getType());
    }

}

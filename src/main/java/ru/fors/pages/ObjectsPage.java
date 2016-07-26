package ru.fors.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.fors.data.*;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Alexander Zhaleiko on 04.05.2016.
 */
public class ObjectsPage extends Page{
    public ObjectsPage(WebDriver driver) {
        super(driver);
    }
    By successMessage = By.id("success-message");
    private By createButton = By.linkText("erstellen");
    private By contactPage = By.linkText("Kontakte");
    private By contactPageTitle = By.xpath("//th[text()='Kontakte']");
    By searchField = By.id("apexir_SEARCH");
    By searchResultLink = By.xpath("//tbody/tr[2]/td[1]/a/img");
    By searchComplete = By.id("apexir_CONTROL_PANEL_COMPLETE");
    By findButton = By.id("apexir_btn_SEARCH");

    List<String> namesDE = Arrays.asList("Engelbert", "Helge", "Achim", "Erdmann", "Helmfried", "Nickolaus", "Adelbert", "Erhard", "Helmut", "Niklaus", "Adolf", "Erich", "Helmuth", "Alban", "Ernst", "Hendrik", "Ortwin", "Albrecht", "Erwin", "Henning", "Othmar", "Alfons", "Eugen", "Heribert", "Ottmar", "Alois", "Evert", "Hermann", "Otto", "Aloysius", "Hermenegild", "Ottokar", "Alwin", "Ferdi", "Hilbert", "Ottomar", "Amand", "Fester", "Hildebert", "Anselm", "Filibert", "Hildebrand", "Pankraz", "Ansgar", "Franz", "Hinrich", "Parsifal", "Armin", "Frej", "Horst", "Philipp", "Arndt", "Friedemann", "Huppert", "Poldi", "Arne", "Friedhelm", "Arnold", "Friedhold", "Ignatz", "Raffael", "August", "Friedrich", "Immanuel", "Reimund", "Aurel", "Fritz", "Ingo", "Reiner", "Ingolf", "Rambert", "Baldur", "Gabi", "Ivo", "Reimund", "Baptist", "Gabriel", "Rein", "Bartholomäus", "Gebbert", "Jochem", "Reiner", "Bastian", "Gebhard", "Jochen", "Reinhard", "Beat", "Geert", "Jochim", "Reinhold", "Benedikt", "Georg", "Johann", "Reto", "Benno", "Gerald", "Jörg", "Rüdiger", "Bernd", "Gerd", "Jürgen", "Bernhard", "Gereon", "Sascha", "Berthold", "Gerfried", "Karlmann", "Sepp", "Bonifaz", "Gerhard", "Karsten", "Seppel", "Bruno", "Gerhardt", "Kasimir", "Siegbert", "Burkhard", "Gerhold", "Kaspar", "Siegfried", "Gernot", "Kayetan", "Sieghard", "Carl", "Gero", "Kolman", "Siegmund", "Carsten", "Gerold", "Mose", "Helfried", "Xaver");
    List<String> familysDE = Arrays.asList("Mueller", "Schmidt", "Schneider", "Fischer", "Weber", "Schulz", "Wagner", "Becker", "Hoffmann", "Schaefer", "Koch", "Bauer", "Richter", "Klein", "Schroeder", "Wolf", "Neumann", "Schwarz", "Zimmermann", "Krueger", "Braun", "Schmitz", "Hartmann", "Lange", "Schmitt", "Werner", "Krause", "Meier", "Schmid", "Lehmann", "Schultze", "Maier", "Koehler", "Herrmann", "Walter", "Koenig", "Mayer", "Huber", "Kaiser", "Fuchs", "Peters", "Moeller", "Scholz", "Lang", "Weiss", "Jung", "Hahn");
    List<String> citiesDE = Arrays.asList("Bayern", "Baden-Württemberg", "Nordrhein-Westfalen", "Hessen", "Sachsen", "Niedersachsen", "Rheinland-Pfalz", "Thüringen", "Brandenburg", "Sachsen-Anhalt", "Mecklenburg-Vorpommern", "Schleswig-Holstein", "Saarland", "Bremen", "Berlin", "Hamburg");
    List<String> teamsDe = Arrays.asList("Alemannia", "FC Erzgebirge", "TG Viktoria", "FC Union", "BFC Dynamo", "BFC Viktoria 1889", "Energie", "Borussia", "Fortuna", "Eintracht", "FC Rot-Weiß", "FC Ingolstadt", "FC Gütersloh 2000", "VfR Heilbronn", "FC Carl Zeiss", "FC Kaiserslautern", "Kickers", "Holstein", "FC Lokomotive", "Red Bull", "FC Bayern", "FC Hansa", "FC Schalke 04", "SSV Buer", "FC St. Pauli", "SV Sandhausen", "FC Zeitz");
    List<String> cityForTeams = Arrays.asList("ADELAIDA (Australia)","BAKU (AZERBAYDZHAN)","Bruges (Belgien)","BRYUSSEL` (Belgien)","GENK (Belgien)","BARANOVICHI (BELORUSSIYA)","BREST (BELORUSSIYA)","GOMEL` (BELORUSSIYA)","GRODNO (BELORUSSIYA)","MINSK (BELORUSSIYA)","MOGILEV (BELORUSSIYA)","NOVOPOLOTSK (BELORUSSIYA)","SOLIGORSK (BELORUSSIYA)","VITEBSK (BELORUSSIYA)","BELU-ORIZONTI (BRAZILIYA)","RIO-DE-ZHANEYRO (BRAZILIYA)","SAN-PAULU (BRAZILIYA)","ESB`YERG (Dänemark)","KOPENGAGEN (Dänemark)","APENSEN (Deutschland)","BERLIN (Deutschland)","BREMEN (Deutschland)","DORTMUND (Deutschland)","DYUSSEL`DORF (Deutschland)","GAMBURG (Deutschland)","GEL`ZENKIRKHEN (Deutschland)","KHOFFENKHAYM (Deutschland)","MYUNKHEN (Deutschland)","OLEG (Deutschland)","BARSELONA (ESPAÑA)","GOROD1 (ESPAÑA)","VALLADOLID (ESPAÑA)","TALLIN (Estland)","ESPOO (Finnland)","KHEL`SINKI (Finnland)","YARO (Finnland)","KAL`VI (Frankreich)","LION (Frankreich)","MONTEGYU (Frankreich)","Paris (Frankreich)","AFINY (Griechenland)","SALONIKI (Griechenland)","TBILISI (GRUZIYA)","BERGAMO (Italien)","BOLON`YA (Italien)","EMPOLI (Italien)","Milan (Italien)","PISTOYYA (Italien)","RIM (Italien)","TURIN (Italien)","TURIN, BORGARO-TORINEZE (Italien)","VERONA (Italien)","VERONA, SAN-BONIFACHO (Italien)","VICHENTSA (Italien)","TEL`-AVIV (IZRAIL`)","AKTAU (KAZAKHSTAN)","AKTOBE (KAZAKHSTAN)","ALMA-ATA (KAZAKHSTAN)","ALMATY (KAZAKHSTAN)","ASTANA (KAZAKHSTAN)","ATYRAU (KAZAKHSTAN)","PAVLODAR (KAZAKHSTAN)","PETROPAVLOVSK (KAZAKHSTAN)","SEMEY (KAZAKHSTAN)","SEMIPALATINSK (KAZAKHSTAN)","TARAZ (KAZAKHSTAN)","UST`-KAMENOGORSK (KAZAKHSTAN)","SPLIT (KHORVATIYA)","ZAGREB (KHORVATIYA)","BISHKEK (KIRGIZIYA)","KANT (KIRGIZIYA)","KARAKOL (KIRGIZIYA)","LATGALIYA (Lettland)","LIYEPAYA (Lettland)","RIGA (Lettland)","YURMALA (Lettland)","ZEMGALE (Lettland)","KAUNAS (Litauen)","PALANGA (Litauen)","VIL`NYUS (Litauen)","KISHINEV (MOLDAVIYA)","TIRASPOL` (MOLDAVIYA)","AMSTERDAM (Niederlande)","APELDORN (Niederlande)","ARNEM (Niederlande)","DEVENTER (Niederlande)","ENSKHEDE (Niederlande)","EYNDKHOVEN (Niederlande)","GAAGA (Niederlande)","MAASTRIKHT (Niederlande)","ROTTERDAM (Niederlande)","KRISTIANSANN (Norwegen)","STAVANGER (Norwegen)","ABU-DABI (OB`YEDINENNYYE ARABSKIYE EMIRATY )","INSBRUK (Österreich)","L`YEZH (Österreich)","RID (Österreich)","VENA (Österreich)","VINER-NOYSHTADT (Österreich)","VOL`FSBERG (Österreich)","ZAL`TSBURG (Österreich)","11111 (Österreich)","BELOSTOK (Polen)","KRAKOV (Polen)","LODZ` (Polen)","LYUBIN (Polen)","POZNAN` (Polen)","VARSHAVA (Polen)","ZHERMANOVITSE (Polen)","LISSABON (Portugal)","PORTU (Portugal)","MAL`ME (Schweden)","Aarau (Schweiz)","Aargau (Schweiz)","Andermatt (Schweiz)","Anzere (Schweiz)","Appenzell (Schweiz)","Appenzell Ausserrhoden (Schweiz)","Appenzell Innerrhoden (Schweiz)","Arosa (Schweiz)","Ascona (Schweiz)","Bad Ragaz (Schweiz)","Bad Zurzach (Schweiz)","Baden (Schweiz)","Basel (Schweiz)","Basel-Landschaft (Schweiz)","Basel-Stadt (Schweiz)","Beatenberg (Schweiz)","Bellinzona (Schweiz)","Bern (Schweiz)","Bettmeralp (Schweiz)","Biel (Schweiz)","Breil-Brigels (Schweiz)","Brienz (Schweiz)","Brigg (Schweiz)","Brissago (Schweiz)","Brunnen (Schweiz)","Bürchen (Schweiz)","Bürgenstock (Schweiz)","Celerina (Schweiz)","Champery (Schweiz)","Champex (Schweiz)","Chavannes de Bogis (Schweiz)","Churwalden (Schweiz)","Cours (Schweiz)","Crans-Montana (Schweiz)","Davos (Schweiz)","Davos-Platz (Schweiz)","Disentis (Schweiz)","Egerkingen (Schweiz)","Emmetten (Schweiz)","Engelberg (Schweiz)","Esha (Schweiz)","Fisch (Schweiz)","Flims (Schweiz)","Flumserberg (Schweiz)","Freiburg (Schweiz)","Frutigen (Schweiz)","Genf (Schweiz)","Giswil (Schweiz)","Glarus (Schweiz)","Gordola (Schweiz)","Grächen (Schweiz)","Graubunden (Schweiz)","Grindelwald (Schweiz)","Grion (Schweiz)","Gstaad (Schweiz)","Hasliberg (Schweiz)","Horw (Schweiz)","Innertkirchen (Schweiz)","Interlaken (Schweiz)","Intragna (Schweiz)","Izetvald (Schweiz)","Jura (Schweiz)","Kandersteg (Schweiz)","Klosters (Schweiz)","Kreuzlingen (Schweiz)","Laax (Schweiz)","Lausanne (Schweiz)","Lauter (Schweiz)","Lauzon (Schweiz)","Le Châble (Schweiz)","Le Crozet (Schweiz)","Le Diablere (Schweiz)","Lenk (Schweiz)","Lenzerheide (Schweiz)","Leukerbad (Schweiz)","Leukerbad / LB Locke (Schweiz)","Leysin (Schweiz)","Locarno (Schweiz)","Lugano (Schweiz)","Luzern (Schweiz)","Luzern (Schweiz)","Martigny (Schweiz)","Meiringen (Schweiz)","Mendrisio (Schweiz)","Meyenfeld (Schweiz)","Montreux (Schweiz)","Mürren (Schweiz)","Murten (Schweiz)","Nanda (Schweiz)","Nendaz (Schweiz)","Neuenburg (Schweiz)","Nidwalden (Schweiz)","Oberwald (Schweiz)","Obwalden (Schweiz)","Parpan (Schweiz)","Pontresina (Schweiz)","Poschiavo (Schweiz)","Riederalp (Schweiz)","Riga Kulm (Schweiz)","Ringgenberg (Schweiz)","Saanen (Schweiz)","Saas-Fee (Schweiz)","Saas-Grund (Schweiz)","Sachseln (Schweiz)","Samedan (Schweiz)","Samnaun (Schweiz)","San Bernardino (Schweiz)","Sarnen (Schweiz)","Schaffhausen (Schweiz)","Schaffhausen (Schweiz)","Schweiz (Schweiz)","Schwyz (Schweiz)","Scuol (Schweiz)","Sedrun (Schweiz)","Seelisberg (Schweiz)","Shezer (Schweiz)","Silvaplana (Schweiz)","Solothurn (Schweiz)","St. Croix (Schweiz)","St. Gallen (Schweiz)","St. Moritz (Schweiz)","Taesh (Schweiz)","Tash (Schweiz)","Tessin (Schweiz)","Thurgau (Schweiz)","Tong (Schweiz)","Uri (Schweiz)","Verbier (Schweiz)","Vevey (Schweiz)","Veysonnaz (Schweiz)","Villars (Schweiz)","Vira (Schweiz)","Vitznau (Schweiz)","Waadt (Schweiz)","Wallis (Schweiz)","Walross (Schweiz)","Walzer (Schweiz)","Weggis (Schweiz)","Wengen (Schweiz)","Wilderswil (Schweiz)","Winterthur (Schweiz)","Zermatt (Schweiz)","Zernez (Schweiz)","Zёrenberg (Schweiz)","Zug (Schweiz)","Zuoz (Schweiz)","Zurich (Schweiz)","Zweisimmen (Schweiz)","BRATISLAVA (Slowakei)","ZHILINA (Slowakei)","MARIBOR (Slowenien)","LA-KORUN`YA (Spanien)","MADRID (Spanien)","MALAGA (Spanien)","VALLADOLID (Spanien)","LIBERETS (Tschechien)","PRAGA (Tschechien)","TEPLITSE (Tschechien)","BUDAPESHT (Ungarn)","D`OR (Ungarn)");

    static Team team = new Team();
    static Exercises exercises = new Exercises();
    static Competition competition = new Competition();
    static Employee employee = new Employee();
    static Event event = new Event();
    static Ads ads = new Ads();
    static ScoutedPlayer scoutedPlayer = new ScoutedPlayer();
    static ArrayList<Coach> coachList = new ArrayList<Coach>();
    static ArrayList<Player> playerList = new ArrayList<Player>();

    String getValueFromList(List<String> list){
        int i= list.size();
        Random random = new Random();
        return list.get(random.nextInt(i));
    }

    @Step("Проверяем создался ли объект")
    public boolean isCreatedOk(){
        try
        {
            wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
            return true;
        } catch (Exception e)
        {
            return false;
        }
    }
    @Step("Пользователь нажимает кнопку создать объект")
    public void userClickCreateButton() {
        click(createButton);
    }

    @Step("Пользователь переходит в раздел Контакты")
    void userGoToContacts(){
        click(contactPage);
        wait.until(ExpectedConditions.visibilityOfElementLocated(contactPageTitle));
    }

    @Step("Пользователь выполняет поиск объекта")
    void userSearchObject(String objName) {
        type(searchField, objName);
        click(findButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchComplete));
    }



}

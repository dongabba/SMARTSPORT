package ru.fors.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.fors.data.Coach;
import ru.fors.data.Exercises;
import ru.fors.data.Player;
import ru.fors.data.Team;
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
    By createButton = By.linkText("erstellen");
    By contactPage = By.linkText("Kontakte");
    By contactPageTitle = By.xpath("//th[text()='Kontakte']");
    By searchField = By.id("apexir_SEARCH");
    By searchResultLink = By.xpath("//tbody/tr[2]/td[1]/a/img");
    By searchComplete = By.id("apexir_CONTROL_PANEL_COMPLETE");
    By findButton = By.id("apexir_btn_SEARCH");

    List<String> namesDE = Arrays.asList("Engelbert", "Helge", "Achim", "Erdmann", "Helmfried", "Nickolaus", "Adelbert", "Erhard", "Helmut", "Niklaus", "Adolf", "Erich", "Helmuth", "Alban", "Ernst", "Hendrik", "Ortwin", "Albrecht", "Erwin", "Henning", "Othmar", "Alfons", "Eugen", "Heribert", "Ottmar", "Alois", "Evert", "Hermann", "Otto", "Aloysius", "Hermenegild", "Ottokar", "Alwin", "Ferdi", "Hilbert", "Ottomar", "Amand", "Fester", "Hildebert", "Anselm", "Filibert", "Hildebrand", "Pankraz", "Ansgar", "Franz", "Hinrich", "Parsifal", "Armin", "Frej", "Horst", "Philipp", "Arndt", "Friedemann", "Huppert", "Poldi", "Arne", "Friedhelm", "Arnold", "Friedhold", "Ignatz", "Raffael", "August", "Friedrich", "Immanuel", "Reimund", "Aurel", "Fritz", "Ingo", "Reiner", "Ingolf", "Rambert", "Baldur", "Gabi", "Ivo", "Reimund", "Baptist", "Gabriel", "Rein", "Bartholomäus", "Gebbert", "Jochem", "Reiner", "Bastian", "Gebhard", "Jochen", "Reinhard", "Beat", "Geert", "Jochim", "Reinhold", "Benedikt", "Georg", "Johann", "Reto", "Benno", "Gerald", "Jörg", "Rüdiger", "Bernd", "Gerd", "Jürgen", "Bernhard", "Gereon", "Sascha", "Berthold", "Gerfried", "Karlmann", "Sepp", "Bonifaz", "Gerhard", "Karsten", "Seppel", "Bruno", "Gerhardt", "Kasimir", "Siegbert", "Burkhard", "Gerhold", "Kaspar", "Siegfried", "Gernot", "Kayetan", "Sieghard", "Carl", "Gero", "Kolman", "Siegmund", "Carsten", "Gerold", "Mose", "Helfried", "Xaver");
    List<String> familysDE = Arrays.asList("Mueller", "Schmidt", "Schneider", "Fischer", "Weber", "Schulz", "Wagner", "Becker", "Hoffmann", "Schaefer", "Koch", "Bauer", "Richter", "Klein", "Schroeder", "Wolf", "Neumann", "Schwarz", "Zimmermann", "Krueger", "Braun", "Schmitz", "Hartmann", "Lange", "Schmitt", "Werner", "Krause", "Meier", "Schmid", "Lehmann", "Schultze", "Maier", "Koehler", "Herrmann", "Walter", "Koenig", "Mayer", "Huber", "Kaiser", "Fuchs", "Peters", "Moeller", "Scholz", "Lang", "Weiss", "Jung", "Hahn");
    List<String> citiesDE = Arrays.asList("Bayern", "Baden-Württemberg", "Nordrhein-Westfalen", "Hessen", "Sachsen", "Niedersachsen", "Rheinland-Pfalz", "Thüringen", "Brandenburg", "Sachsen-Anhalt", "Mecklenburg-Vorpommern", "Schleswig-Holstein", "Saarland", "Bremen", "Berlin", "Hamburg");
    List<String> teamsDe = Arrays.asList("Alemannia", "FC Erzgebirge", "TG Viktoria", "FC Union", "BFC Dynamo", "BFC Viktoria 1889", "Energie", "Borussia", "Fortuna", "Eintracht", "FC Rot-Weiß", "FC Ingolstadt", "FC Gütersloh 2000", "VfR Heilbronn", "FC Carl Zeiss", "FC Kaiserslautern", "Kickers", "Holstein", "FC Lokomotive", "Red Bull", "FC Bayern", "FC Hansa", "FC Schalke 04", "SSV Buer", "FC St. Pauli", "SV Sandhausen", "FC Zeitz");
    List<String> cityForTeams = Arrays.asList("INSBRUK (Austria)", "L`YEZH (Austria)", "RID (Austria)", "VENA (Austria)", "VINER-NOYSHTADT (Austria)", "VOL`FSBERG (Austria)", "ZAL`TSBURG (Austria)", "BAKU (AZERBAYDZHAN)", "Bruges (Belgium)", "BRYUSSEL` (Belgium)", "GENK (Belgium)", "BARANOVICHI (BELORUSSIYA)", "BORISOV (BELORUSSIYA)", "BREST (BELORUSSIYA)", "GOMEL` (BELORUSSIYA)", "GRODNO (BELORUSSIYA)", "MINSK (BELORUSSIYA)", "MOGILEV (BELORUSSIYA)", "NOVOPOLOTSK (BELORUSSIYA)", "SOLIGORSK (BELORUSSIYA)", "VITEBSK (BELORUSSIYA)", "BELU-ORIZONTI (BRAZILIYA)", "RIO-DE-ZHANEYRO (BRAZILIYA)", "SAN-PAULU (BRAZILIYA)", "LIBERETS (CHEKHIYA)", "PRAGA (CHEKHIYA)", "TEPLITSE (CHEKHIYA)", "ESB`YERG (Denmark)", "KOPENGAGEN (Denmark)", "BARSELONA (ESPAÑA)", "GOROD1 (ESPAÑA)", "MADRID (ESPAÑA)", "VALLADOLID (ESPAÑA)", "TALLIN (ESTONIYA)", "ESPOO (FINLYANDIYA)", "KHEL`SINKI (FINLYANDIYA)", "YARO (FINLYANDIYA)", "KAL`VI (France)", "LION (France)", "MONTEGYU (France)", "Paris (France)", "APENSEN (Germany)", "BERLIN (Germany)", "BREMEN (Germany)", "DORTMUND (Germany)", "DYUSSEL`DORF (Germany)", "GAMBURG (Germany)", "GEL`ZENKIRKHEN (Germany)", "KHOFFENKHAYM (Germany)", "MYUNKHEN (Germany)", "OLEG (Germany)", "AFINY (GRETSIYA)", "SALONIKI (GRETSIYA)", "TBILISI (GRUZIYA)", "BERGAMO (Italy)", "BOLON`YA (Italy)", "EMPOLI (Italy)", "Milan (Italy)", "PISTOYYA (Italy)", "RIM (Italy)", "TURIN (Italy)", "TURIN, BORGARO-TORINEZE (Italy)", "VERONA (Italy)", "VERONA, SAN-BONIFACHO (Italy)", "VICHENTSA (Italy)", "TEL`-AVIV (IZRAIL`)", "AKTAU (KAZAKHSTAN)", "AKTOBE (KAZAKHSTAN)", "ALMA-ATA (KAZAKHSTAN)", "ALMATY (KAZAKHSTAN)", "ASTANA (KAZAKHSTAN)", "ATYRAU (KAZAKHSTAN)", "PAVLODAR (KAZAKHSTAN)", "PETROPAVLOVSK (KAZAKHSTAN)", "SEMEY (KAZAKHSTAN)", "SEMIPALATINSK (KAZAKHSTAN)", "TARAZ (KAZAKHSTAN)", "UST`-KAMENOGORSK (KAZAKHSTAN)", "SPLIT (KHORVATIYA)", "ZAGREB (KHORVATIYA)", "BISHKEK (KIRGIZIYA)", "KANT (KIRGIZIYA)", "KARAKOL (KIRGIZIYA)", "LATGALIYA (LATVIYA)", "LIYEPAYA (LATVIYA)", "RIGA (LATVIYA)", "YURMALA (LATVIYA)", "ZEMGALE (LATVIYA)", "KAUNAS (LITVA)", "PALANGA (LITVA)", "VIL`NYUS (LITVA)", "KISHINEV (MOLDAVIYA)", "TIRASPOL` (MOLDAVIYA)", "AMSTERDAM (NIDERLANDY)", "APELDORN (NIDERLANDY)", "ARNEM (NIDERLANDY)", "DEVENTER (NIDERLANDY)", "ENSKHEDE (NIDERLANDY)", "EYNDKHOVEN (NIDERLANDY)", "GAAGA (NIDERLANDY)", "MAASTRIKHT (NIDERLANDY)", "ROTTERDAM (NIDERLANDY)", "KRISTIANSANN (NORVEGIYA)", "STAVANGER (NORVEGIYA)", "ABU-DABI (OB`YEDINENNYYE ARABSKIYE EMIRATY )", "BELOSTOK (POL`SHA)", "KRAKOV (POL`SHA)", "LODZ` (POL`SHA)", "LYUBIN (POL`SHA)", "POZNAN` (POL`SHA)", "VARSHAVA (POL`SHA)", "ZHERMANOVITSE (POL`SHA)", "LISSABON (PORTUGALIYA)", "PORTU (PORTUGALIYA)");

    public static Team team = new Team();
    public static Exercises exercises = new Exercises();
    public static ArrayList<Coach> coachList = new ArrayList<Coach>();
    public static ArrayList<Player> playerList = new ArrayList<Player>();

    public String getValueFromList(List<String> list){
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
    public void userGoToContacts(){
        click(contactPage);
        wait.until(ExpectedConditions.visibilityOfElementLocated(contactPageTitle));
    }

    @Step("Пользователь выполняет поиск объекта")
    public void userSearchObject(String objName) {
        type(searchField, objName);
        click(findButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchComplete));
    }



}

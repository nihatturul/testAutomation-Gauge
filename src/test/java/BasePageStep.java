import com.thoughtworks.gauge.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.WaitTool;

public class BasePageStep extends BasePageUtil {



    @Step("saniye bekle <e>")
    public void sleeped(int saniye){
        sleep(saniye);
        System.out.println(saniye + " UYUDU......");
    }
    public static String convertTurkishChar(String string) {
        string = string.replace("ç", "c");
        string = string.replace("ö", "o");
        string = string.replace("ş", "s");
        string = string.replace("ğ", "g");
        string = string.replace("ü", "u");
        string = string.replace("ı", "i");
        string = string.replace("Ç", "C");
        string = string.replace("Ö", "O");
        string = string.replace("Ş", "S");
        string = string.replace("Ğ", "G");
        string = string.replace("Ü", "U");
        string = string.replace("İ", "I");
        return string;
    }



    @Step("<id> id li elemente tıkla")
    public void clickObjectById(String id) {
        waitForPresenceByElement(By.id(id)).click();
    }

    @Step("<css> css li elemente tıkla")
    public void clickObjectByCss(String css) {
        waitForPresenceByElement(By.cssSelector(css)).click();
    }


    @Step("ilk <class> class li elemente tıkla")
    public void clickObjectByClass(String clas) {
        waitForPresenceByElement(By.className(clas)).click();
    }


    @Step("<xpath> xpath li elemente tıkla")
    public void clickObjectByxpath(String xpath) {
        waitForPresenceByElement(By.xpath(xpath)).click();
    }

    @Step("<linktext> linktext li elemente tıkla")
    public void clickObjectBylinktext(String linktext) {
        waitForPresenceByElement(By.linkText(linktext)).click();
    }
    @Step({"Find element with key <key> and compare text to <saveKey>",
            "<key> alanını bul ve değerini <saveKey> ile karşılaştır"})
    public void findElementAndCompare(String key, String saveKey) {

    }

    @Step({"Hover element with key <key>",
            "<key> alanının üstüne gel"})
    public void mouseHoverElementStep(String key) {

    }
    @Step({"<key> alanına kaydır"})
    public void scrollToElement(String key) {
        scrollToElementToBeVisible(key);
    }

     @Step("<id> id li alana <text> değerini gir")
    public void sendKeyById(String id,String text) {
        waitForPresenceByElement(By.id(id)).sendKeys(text);
    }

    @Step("<xpath> class li alana <text> değerini gir")
    public void sendKeyByXpath(String xpath,String text) {
        waitForPresenceByElement(By.xpath(xpath)).sendKeys(text);
    }

    @Step("<css> css li alana <text> değerini gir")
    public void sendKeyByCss(String css,String text) {
        waitForPresenceByElement(By.cssSelector(css)).sendKeys(text);
    }

    @Step("<class> class li alana <text> değerini gir")
    public void sendKeyByClass(String clas,String text) {
        waitForPresenceByElement(By.className(clas)).sendKeys(text);
    }




    private void scrollToElementToBeVisible(String key) {

    }


}

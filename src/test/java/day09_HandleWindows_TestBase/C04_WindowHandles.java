package day09_HandleWindows_TestBase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Set;

public class C04_WindowHandles {
    WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        //driver.quit();
    }

    @Test
    public void test01() {
        driver.get("https://the-internet.herokuapp.com/windows");
        String ilkSayfaWindowHandleDegeri = driver.getWindowHandle();
        System.out.println("ilk sayfa handle degeri :" + ilkSayfaWindowHandleDegeri);
        WebElement sayfadakiYaziElementi = driver.findElement(By.xpath("//h3"));
        String expectedYAzi = "Opening a new window";
        String actualYazi = sayfadakiYaziElementi.getText();
        Assert.assertEquals(expectedYAzi, actualYazi);

        String expectedTitle = "The Internet";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);


        driver.findElement(By.linkText("Click Here")).click();
 /*
        yeni sayfaya gecebilmek icin oncelikle ikinciSayfaWindowHandleDegeri'ni bulmamiz gerekir
        bunun icin driver.getWindowHandles() method'unu kullanarak
        acik olan tum sayfalarin window handle degerlerini alip bir set'e assign ederiz.
        ilk sayfanin window handle degerini zaten biliyoruz
        Set'deki window handle degerlerini kontrol edip
        ilk sayfanin handle degerine esit olmayan
        ikinci sayfanin woindow handle degeridir deriz
         */
        Set<String> tumSayfalarHandleDegerleri = driver.getWindowHandles();
        System.out.println("tum sayfalar handle degerleri : " + tumSayfalarHandleDegerleri);

        String Ik??nciSayfaWindowHandleDegeri="";

        for (String each :tumSayfalarHandleDegerleri
             ) {
            if(!each.equals(ilkSayfaWindowHandleDegeri)){
                Ik??nciSayfaWindowHandleDegeri = each;
            }
        }

        // artik ikinci sayfanin window handle degerini biliyoruz
        // rahatlikla sayfalar arasii gecis yapabiliriz
        driver.switchTo().window(Ik??nciSayfaWindowHandleDegeri);

        //??? Acilan yeni pencerenin sayfa ba??l??????n??n (title) ???New Window??? oldugunu dogrulayin.

        String expectedIkinciTitle = "New Window";
        String actualIkinciTitle = driver.getTitle();
        Assert.assertEquals(expectedIkinciTitle, actualIkinciTitle);




        // String Ik??nc??SayfWindowHandleDegeri = driver.getWindowHandle(); driveri yeni ac??lan sayfaya gondermedi??imiz i??in
        // sayfan??n window handle degeri ayn?? c??kar
        //System.out.println("1. sayfa :" + ilkSayfaWindowHandleDegeri);
        //System.out.println("2. sayfa :" +Ik??nc??SayfWindowHandleDegeri);


        //



    }
}

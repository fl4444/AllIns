import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AllInventsTests extends ApplicationManager {

    // 1 проверка тайтла
    @Test( priority = 1 )
    public void checkTitle()  {

          Assert.assertEquals(wd.getTitle(), getProperty("title"));
    }

    // 2 проверка меню с городом
    @Test( priority = 1 )
    public void checkCity() {
        wd.findElement(By.cssSelector("span.linkDotted")).click();
        Assert.assertEquals(wd.findElement(By.cssSelector("td.form-head-name.fs-18.fst-b")).getText(),
                getProperty("city"));
    }

    // 3 проверка добавление товара из распродажи
    @Test( priority = 1 )
    public void checkSaleBacket()  {
        wd.get(getProperty("web.base") + "sales/rasprodazha/");
        wd.findElement(By.cssSelector("div.tile-box.product")).click(); //в карточку товара
        String saleInCard = wd.findElement(By.xpath("//*[@id=\"card-resale-sale\"]/div[2]/span[1]")).getText();
        wd.findElement(By.xpath("//*[@id=\"card-resale-sale\"]/div[2]/div[2]/a")).click();
        String saleInBacket = wd.findElement(By.cssSelector("div.sum-wrapper")).getText();

        Assert.assertTrue(saleInBacket.contains(saleInCard));

    }

    // 4 выбор случайного региона, в котором возможна курьерская доставка
    @Test( priority = 1 )
    public void checkRandomCityDelivery()  {
        wd.findElement(By.cssSelector("span.button-new-red.-inline.retail-sale-buy")).click();

        Assert.assertEquals(wd.findElement(By.cssSelector("wd.td.form-head-name.fs-18.fst-b")).getText(),
                getProperty("city"));
    }

    //5 проверка нажатия кнопки "В корзину" для случайного товара с признаком "Лучшая цена"
    @Test( priority = 1 )
    public void checkButtonBestSale()  {
        wd.findElement(By.cssSelector("span.button-new-red.-inline.retail-sale-buy")).click();

        Assert.assertEquals(wd.findElement(By.cssSelector("wd.td.form-head-name.fs-18.fst-b")).getText(),
                getProperty("city"));
    }

    //6 автотест, который узнаёт телефон отдела подбора персонала
    @Test( priority = 1 )
    public void checkNumberPersonalDepartment()  {
        wd.get(getProperty("web.base") + "contacts/1.html");

        Assert.assertEquals(wd.findElement(By.xpath("//td[contains(text(),'Отдел подбора персонала')]/../td[2]")).getText(),
                getProperty("number"));
    }
}



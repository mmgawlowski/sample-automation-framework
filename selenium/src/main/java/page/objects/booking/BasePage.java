package page.objects.booking;

import org.openqa.selenium.support.PageFactory;
import driver.manager.DriverManager;

public abstract class BasePage {

    public BasePage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

}

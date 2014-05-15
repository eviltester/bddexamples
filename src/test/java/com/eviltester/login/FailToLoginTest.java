package com.eviltester.login;

import com.eviltester.tracks.LoginPage;
import com.eviltester.tracks.TracksAccount;
import com.eviltester.tracks.TracksSite;
import com.eviltester.tracks.TracksSiteNavigator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FailToLoginTest {

    // a first test to let me check
    // my investigation of the site

    private static TracksSiteNavigator tracks;
    private static TracksAccount tracksAccount;
    private static WebDriver driver;

    @BeforeClass
    public static void prepForTest(){
        driver = new FirefoxDriver();
        tracksAccount = new TracksAccount();
        tracks = new TracksSiteNavigator(driver, new TracksSite(tracksAccount.tracks_url));
}

    @Test
    public void failToLoginToTracksWithWrongPassword(){

        tracks.gotoLoginPage();

        final LoginPage loginPage = new LoginPage(driver);

        loginPage.typeUserName(tracksAccount.userName);
        loginPage.typePassword("bob");
        loginPage.signIn();

        assertThat(driver.getCurrentUrl(), endsWith(tracks.getLoginPageURL()));

        // only really need one of these - either wait or assert
        new WebDriverWait(driver,10).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return loginPage.loginUnsuccessfulMessageIsPresent();
            }
        });

        assertThat(loginPage.loginUnsuccessfulMessageIsPresent(), is(true));
    }


    @After
    public void logout(){
        tracks.gotoLogoutPage();
    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }

}
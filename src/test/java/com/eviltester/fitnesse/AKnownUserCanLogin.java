package com.eviltester.fitnesse;

import com.eviltester.tracks.LoginPage;
import com.eviltester.tracks.TracksAccount;
import com.eviltester.tracks.TracksSite;
import com.eviltester.tracks.TracksSiteNavigator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/*

This BDD example project also used FitNesse in live demos,
to contrast with Cucumber.

FitNesse not included in this source file project.
The basic wiki page that drives this fixture was...


\BddTutorial\CanLoginToTheApplication

with the content below

!contents -R2 -g -p -f -h

|import|
|com.eviltester.fitnesse|

A known user should be able to login with their username and password

!| A known user can login |
| username | password | success? |
| user | password | loggedin |
| user | badword | failed |

 */
public class AKnownUserCanLogin {

    private WebDriver driver;
    private TracksSiteNavigator tracks;
    private TracksAccount tracksAccount;
    private String username;
    private String password;
    private String successCode;

    public AKnownUserCanLogin(){}


    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;

    }

    public String success(){
        return successCode;
    }

    public void execute(){
        loginWithKnownUser();
    }

    private void loginWithKnownUser(){

        successCode="loggedin";

        tracksAccount = new TracksAccount();
        driver = new FirefoxDriver();

        try{
            tracks = new TracksSiteNavigator(driver, new TracksSite(tracksAccount.tracks_url));

            tracks.gotoLoginPage();

            LoginPage loginPage = new LoginPage(driver);

            loginPage.typeUserName(this.username);
            loginPage.typePassword(this.password);
            loginPage.signIn();

            assertThat(driver.getTitle(), is(TracksAccount.getTracksTitleHeader() + "List tasks"));
        }catch(AssertionError e){
            successCode = "failed";
        }


        driver.get(tracksAccount.tracks_url + "/logout");
        driver.quit();
    }
}

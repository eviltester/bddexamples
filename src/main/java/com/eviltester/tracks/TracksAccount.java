package com.eviltester.tracks;

public class TracksAccount{

    // http://bitnami.org/stack/tracks#virtualMachine

    // I setup the default install with user, password

    /* Remember to setup the username and password that you are going to use for tracks
     * otherwise you will end up with the defaults below */
    private static final String TRACKS_USERNAME_KEY = "TRACKS_USERNAME";
    private static final String TRACKS_PASSWORD_KEY = "TRACKS_PASSWORD";
    private static final String TRACKS_URL_KEY = "TRACKS_URL";
    private static final String TRACKS_TITLE_HEADER_KEY = "TRACKS_TITLE_HEADER";


    public final String userName;
    public final String password;
    public final String tracks_url;

    public TracksAccount(){
        userName = System.getProperty(TRACKS_USERNAME_KEY, "user");
        password = System.getProperty(TRACKS_PASSWORD_KEY, "password");

        //tracks_url = System.getProperty(TRACKS_URL_KEY, "http://localhost");  // default to running locally

        // found an online demo version
        tracks_url = System.getProperty(TRACKS_URL_KEY, "https://tracks.tra.in");  // a demo server

    }

    public static String getTracksTitleHeader(){
        return System.getProperty(TRACKS_TITLE_HEADER_KEY, "tracks.tra.in: ");    // a demo server
        // return System.getProperty(TRACKS_TITLE_HEADER_KEY, "TRACKS::");       // local host
    }

}

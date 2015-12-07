package com.jsphdev.adapter.WebService;

/**
 * Created by vikramn on 11/14/15.
 */
public interface IWebServiceConstants {

    public static final String IP_ADDRESS="128.237.192.56";
    public static final String GET_USER_URL = "http://"+IP_ADDRESS+":8080/NewBalanceRemoteServer/balancewebapp/user/loginuser/";
    public static final String GET_USER_PROFILE_URL = "http://"+IP_ADDRESS+":8080/NewBalanceRemoteServer/balancewebapp/profile/getprofile/";
    public static final String REGISTER_USER_URL = "http://"+IP_ADDRESS+":8080/NewBalanceRemoteServer/balancewebapp/user/createuser/";
    public static final String REGISTER_PROFILE_URL = "http://"+IP_ADDRESS+":8080/NewBalanceRemoteServer/balancewebapp/profile/createprofile/";

    public static final String GET_USER_EVENTS = "http://"+IP_ADDRESS+":8080/NewBalanceRemoteServer/balancewebapp/event/getevents/";
    public static final String CREATE_EVENT = "http://"+IP_ADDRESS+":8080/NewBalanceRemoteServer/balancewebapp/event/createevent/";

    public static final String GET_LATEST_EVENTS = "http://"+IP_ADDRESS+":8080/NewBalanceRemoteServer/balancewebapp/event/getlatestevents/";
    public static final String REGISTER_EVENT_URL = "http://"+IP_ADDRESS+":8080/NewBalanceRemoteServer/balancewebapp/event/registerevent/";


    public static final String CREATE_USER_PROFILE_URL = "http://10.0.0.223:8080/BalanceRemoteServer/balancewebapp/profile/createprofile";
    public static final String CREATE_LOCATION_URL = "http://10.0.0.223:8080/BalanceRemoteServer/balancewebapp/location/createlocation";
}

package com.example.quizstar.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPrefrences {

    private static SharedPrefrences gardenSharedPrfs;
    private SharedPreferences appSharedPrefs;
    private SharedPreferences.Editor prefsEditor;

    private static final String sharedprfstag = "RUFIL_PROCUREMENT";
    private static final String prf_login_status = "loginStatus";
    private static final String prf_user_detail = "userDetail";
    private static final String pref_login_dateTime = "loginDateTime";
    private static final String pref_phone = "";

    private static final String prf_UserId = "";
    private static final int count = 0;
    private String userTypeV = null;
    private String prf_UserIdV = null;


    public SharedPrefrences(Context context) {
        this.appSharedPrefs = context.getSharedPreferences(sharedprfstag, Context.MODE_PRIVATE);
        this.prefsEditor = appSharedPrefs.edit();
    }

    public static SharedPrefrences getsharedprefInstance(Context con) {
        if (gardenSharedPrfs == null)
            gardenSharedPrfs = new SharedPrefrences(con);
        return gardenSharedPrfs;
    }


    public void setLoginStatus(boolean status) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putBoolean(prf_login_status, status);
        prefsEditor.apply();
    }








    /*public void setPhone(String phone) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString(pref_phone, phone);
        prefsEditor.apply();
    }

    public String getPhone() {
        return appSharedPrefs.getString(pref_phone, "empty");
    }*/


    public void setUserEmailId(String UserId) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString(prf_UserId, UserId);
        prefsEditor.apply();
    }

    public String getUserEmailId() {
        return appSharedPrefs.getString(prf_UserId, prf_UserIdV);
    }

    // user Data

    public void setUserName(String UserName) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("name", UserName);
        prefsEditor.apply();
    }

    public String getUserName() {
        return appSharedPrefs.getString("name", "");
    }

    public void setUserLastName(String UserLastName) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("UserLastName", UserLastName);
        prefsEditor.apply();
    }

    public String getUserLastName() {
        return appSharedPrefs.getString("UserLastName", "");
    }


    public void setUseremail(String UserEmail) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("UserEmail", UserEmail);
        prefsEditor.apply();
    }

    public String getUseremail() {
        return appSharedPrefs.getString("UserEmail", "");
    }


    public void setUserProfile(String UserProfile) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("UserProfile", UserProfile);
        prefsEditor.apply();
    }

    public String getUserProfile() {
        return appSharedPrefs.getString("UserProfile", "");
    }


    public void setUserRegion(String UserProfile) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("UserR", UserProfile);
        prefsEditor.apply();
    }

    public String getUserRegion() {
        return appSharedPrefs.getString("UserR", "");
    }


    // END UserData

    public void setUserAuth(String UserProfile) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("Auth", UserProfile);
        prefsEditor.apply();
    }

    public String getUserAuth() {
        return appSharedPrefs.getString("Auth", "Auth");
    }


    public void setsess_id(String sess_id) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("sess_id", sess_id);
        prefsEditor.apply();
    }

    public String getsess_id() {
        return appSharedPrefs.getString("sess_id", "");
    }


    public void setUserPassword(String userType) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("userType", userType);
        prefsEditor.apply();
    }

    public String getUserPassword() {
        return appSharedPrefs.getString("userType", userTypeV);
    }


    public void setCount(int count1) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putInt("count", count1);
        prefsEditor.apply();
    }

    public int getCount() {
        return appSharedPrefs.getInt("count", 0);
    }


    public void setCartCount(int count1) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putInt("count", count1);
        prefsEditor.apply();
    }

    public int getCartCount() {
        return appSharedPrefs.getInt("count", 0);
    }

    public void setCircleDistance(String distance) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("circle_distance", distance);
        prefsEditor.apply();
    }

    public String getCircleDistance() {
        return appSharedPrefs.getString("circle_distance", "empty");
    }

    public void setCheckIn(boolean status) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putBoolean("is_check_in", status);
        prefsEditor.apply();
    }

    public boolean isCheckIn() {
        return appSharedPrefs.getBoolean("is_check_in", false);
    }

    public void setCheckInPosition(String position) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("check_in_position", position);
        prefsEditor.apply();
    }

    public String getCheckInPosition() {
        return appSharedPrefs.getString("check_in_position", "");
    }

    public void setCheckInLatitude(String checkInLatitude) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("check_in_latitude", checkInLatitude);
        prefsEditor.apply();
    }

    public String getCheckInLatitude() {
        return appSharedPrefs.getString("check_in_latitude", "");
    }

    public void setCheckInLongitude(String checkInLongitude) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("check_in_longitude", checkInLongitude);
        prefsEditor.apply();
    }

    public String getCheckInLongitude() {
        return appSharedPrefs.getString("check_in_longitude", "");
    }

    public void setCheckInLocationName(String locationName) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("check_in_location_name", locationName);
        prefsEditor.apply();
    }

    public String getCheckInLocationName() {
        return appSharedPrefs.getString("check_in_location_name", "");
    }

    public void setUserDetail(String userDetail) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString(prf_user_detail, userDetail);
        prefsEditor.apply();
    }


    public void setDayStart(boolean status) {
        prefsEditor.putBoolean("IS_DAY_START", status);
        prefsEditor.commit();
    }


    public boolean isLoggedIn() {
        return appSharedPrefs.getBoolean(prf_login_status, false);
    }

    public boolean isDayStart() {
        return appSharedPrefs.getBoolean("IS_DAY_START", false);
    }

    public void setMappedBmcDetails(String mappedBmcDetails) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString("mapped_bmc_detail", mappedBmcDetails);
        prefsEditor.apply();
    }


    public void setLongitude(String longitude) {
        prefsEditor.putString("LONGITUDE", longitude);
        prefsEditor.commit();
    }

    public String getLongitude() {
        return appSharedPrefs.getString("LONGITUDE", null);
    }

    public void setLatitude(String latitude) {
        prefsEditor.putString("LATITUDE", latitude);
        prefsEditor.commit();
    }

    public String getLatitude() {
        return appSharedPrefs.getString("LATITUDE", null);
    }

    public void setstateSelection(String statid) {
        prefsEditor.putString("STATE", statid);
        prefsEditor.commit();
    }

    public String getStateId() {
        return appSharedPrefs.getString("STATE", null);
    }
}

package com.nodeflux.university;

/**
 * Created by cmmuk_000 on 12/10/2016.
 */

public class University {

    private String Name;
    private String City;
    private String Url;
    private String Logo;

    public University() {
    }

    public University(String city, String logo, String name, String url) {
        City = city;
        Logo = logo;
        Name = name;
        Url = url;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String logo) {
        Logo = logo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}

package com.book.core.model;

public class City {
    private Integer id;

    private String cityName;

    private String cityUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getCityUrl() {
        return cityUrl;
    }

    public void setCityUrl(String cityUrl) {
        this.cityUrl = cityUrl == null ? null : cityUrl.trim();
    }
}
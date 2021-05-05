package ru.laptev.carshstat.model;

public enum Company {
    YANDEX_DRIVE("Yandex Drive"),
    CITY_DRIVE("City Drive"),
    DELIMOBIL("Delimobil");

    private String companyName;

    Company(String name) {
        this.companyName = name;
    }
}

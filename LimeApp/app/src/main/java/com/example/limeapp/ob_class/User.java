package com.example.limeapp.ob_class;

import java.util.HashMap;
import java.util.List;

public class User {
    String name, number, password, age, Aboniment_start_date, Aboniment_end_date, Aboniment_status, Group_t_start_date, Group_t_end_date, Group_t_status, image, Afreeze_days, Gfreeze_days, Afreeze_date, Gfreeze_date, CountOfGT;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAboniment_start_date() {
        return Aboniment_start_date;
    }

    public void setAboniment_start_date(String aboniment_start_date) {
        Aboniment_start_date = aboniment_start_date;
    }

    public String getAboniment_end_date() {
        return Aboniment_end_date;
    }

    public void setAboniment_end_date(String aboniment_end_date) {
        Aboniment_end_date = aboniment_end_date;
    }

    public String getAboniment_status() {
        return Aboniment_status;
    }

    public void setAboniment_status(String aboniment_status) {
        Aboniment_status = aboniment_status;
    }

    public String getGroup_t_start_date() {
        return Group_t_start_date;
    }

    public void setGroup_t_start_date(String group_t_start_date) {
        Group_t_start_date = group_t_start_date;
    }

    public String getGroup_t_end_date() {
        return Group_t_end_date;
    }

    public void setGroup_t_end_date(String group_t_end_date) {
        Group_t_end_date = group_t_end_date;
    }

    public String getGroup_t_status() {
        return Group_t_status;
    }

    public void setGroup_t_status(String group_t_status) {
        Group_t_status = group_t_status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAfreeze_days() {
        return Afreeze_days;
    }

    public void setAfreeze_days(String afreeze_days) {
        Afreeze_days = afreeze_days;
    }

    public String getGfreeze_days() {
        return Gfreeze_days;
    }

    public void setGfreeze_days(String gfreeze_days) {
        Gfreeze_days = gfreeze_days;
    }

    public String getAfreeze_date() {
        return Afreeze_date;
    }

    public void setAfreeze_date(String afreeze_date) {
        Afreeze_date = afreeze_date;
    }

    public String getGfreeze_date() {
        return Gfreeze_date;
    }

    public void setGfreeze_date(String gfreeze_date) {
        Gfreeze_date = gfreeze_date;
    }

    public String getCountOfGT() {
        return CountOfGT;
    }

    public void setCountOfGT(String countOfGT) {
        CountOfGT = countOfGT;
    }

    public HashMap<String, User_Buys> getUser_buys() {
        return user_buys;
    }

    public void setUser_buys(HashMap<String, User_Buys> user_buys) {
        this.user_buys = user_buys;
    }

    public User(String name, String number, String password, String age, String aboniment_start_date, String aboniment_end_date, String aboniment_status, String group_t_start_date, String group_t_end_date, String group_t_status, String image, String afreeze_days, String gfreeze_days, String afreeze_date, String gfreeze_date, String countOfGT, HashMap<String, User_Buys> user_buys) {
        this.name = name;
        this.number = number;
        this.password = password;
        this.age = age;
        Aboniment_start_date = aboniment_start_date;
        Aboniment_end_date = aboniment_end_date;
        Aboniment_status = aboniment_status;
        Group_t_start_date = group_t_start_date;
        Group_t_end_date = group_t_end_date;
        Group_t_status = group_t_status;
        this.image = image;
        Afreeze_days = afreeze_days;
        Gfreeze_days = gfreeze_days;
        Afreeze_date = afreeze_date;
        Gfreeze_date = gfreeze_date;
        CountOfGT = countOfGT;
        this.user_buys = user_buys;
    }

    HashMap<String, User_Buys> user_buys;
}

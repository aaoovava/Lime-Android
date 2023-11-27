package com.example.limeapp.ob_class;

public class User_Buys {
    String name_of_buy, cost;

    public User_Buys(String name_of_buy, String cost) {
        this.name_of_buy = name_of_buy;
        this.cost = cost;
    }

    public String getName_of_buy() {
        return name_of_buy;
    }

    public void setName_of_buy(String name_of_buy) {
        this.name_of_buy = name_of_buy;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}

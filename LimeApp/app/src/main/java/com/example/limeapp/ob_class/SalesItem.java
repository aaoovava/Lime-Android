package com.example.limeapp.ob_class;

public class SalesItem {
    private String name, description;
    private String prise;

    public SalesItem(String name, String description, String prise) {
        this.name = name;
        this.description = description;
        this.prise = prise;
    }
    public SalesItem(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrise() {
        return prise;
    }

    public void setPrise(String prise) {
        this.prise = prise;
    }
}

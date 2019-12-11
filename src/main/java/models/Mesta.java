package models;

public class Mesta {
    private String id;
    private String mesto;

    public Mesta(String id, String mesto) {
        this.id = id;
        this.mesto = mesto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }
}

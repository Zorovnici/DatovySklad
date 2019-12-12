package models;

public class ZaznamHasKlucoveSlovo {
    private String zaznam_id;
    private String klucove_slovo_id;

    public ZaznamHasKlucoveSlovo(String zaznam_id, String klucove_slovo_id) {
        this.zaznam_id = zaznam_id;
        this.klucove_slovo_id = klucove_slovo_id;
    }

    public String getZaznam_id() {
        return zaznam_id;
    }

    public void setZaznam_id(String zaznam_id) {
        this.zaznam_id = zaznam_id;
    }

    public String getKlucove_slovo_id() {
        return klucove_slovo_id;
    }

    public void setKlucove_slovo_id(String klucove_slovo_id) {
        this.klucove_slovo_id = klucove_slovo_id;
    }
}

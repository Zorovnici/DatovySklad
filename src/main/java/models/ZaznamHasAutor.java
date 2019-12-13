package models;

public class ZaznamHasAutor {
    private String zaznam_id;
    private String autor_id;

    public ZaznamHasAutor(String zaznam_id, String autor_id) {
        this.zaznam_id = zaznam_id;
        this.autor_id = autor_id;
    }

    public String getZaznam_id() {
        return zaznam_id;
    }

    public void setZaznam_id(String zaznam_id) {
        this.zaznam_id = zaznam_id;
    }

    public String getAutor_id() {
        return autor_id;
    }

    public void setAutor_id(String autor_id) {
        this.autor_id = autor_id;
    }
}

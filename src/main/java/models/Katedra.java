package models;

public class Katedra {
    private String id;
    private String katedra;
    private String fakultafk;

    public Katedra(String id, String katedra, String fakultafk) {
        this.id = id;
        this.katedra = katedra;
        this.fakultafk = fakultafk;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKatedra() {
        return katedra;
    }

    public void setKatedra(String katedra) {
        this.katedra = katedra;
    }

    public String getFakultafk() {
        return fakultafk;
    }

    public void setFakultafk(String fakultafk) {
        this.fakultafk = fakultafk;
    }
}

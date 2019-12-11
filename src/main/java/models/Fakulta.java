package models;

public class Fakulta {
    private String id;
    private String fakulta;

    public Fakulta(String id, String fakulta) {
        this.id = id;
        this.fakulta = fakulta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFakulta() {
        return fakulta;
    }

    public void setFakulta(String fakulta) {
        this.fakulta = fakulta;
    }
}

package models;

public class Roky {
    private String id;
    private String roky;

    public Roky(String id, String roky) {
        this.id = id;
        this.roky = roky;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoky() {
        return roky;
    }

    public void setRoky(String roky) {
        this.roky = roky;
    }
}

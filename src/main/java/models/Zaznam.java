package models;

public class Zaznam {
    private String id;
    private String name;
    private String arch_cislo;
    private String kat_epc;
    private String ISSN;
    private String ISBN;
    private String strany;

    public Zaznam(String id, String name, String arch_cislo, String kat_epc, String ISSN, String ISBN, String strany) {
        this.id = id;
        this.name = name;
        this.arch_cislo = arch_cislo;
        this.kat_epc = kat_epc;
        this.ISSN = ISSN;
        this.ISBN = ISBN;
        this.strany = strany;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArch_cislo() {
        return arch_cislo;
    }

    public void setArch_cislo(String arch_cislo) {
        this.arch_cislo = arch_cislo;
    }

    public String getKat_epc() {
        return kat_epc;
    }

    public void setKat_epc(String kat_epc) {
        this.kat_epc = kat_epc;
    }

    public String getISSN() {
        return ISSN;
    }

    public void setISSN(String ISSN) {
        this.ISSN = ISSN;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getStrany() {
        return strany;
    }

    public void setStrany(String strany) {
        this.strany = strany;
    }
}

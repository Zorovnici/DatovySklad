import models.*;

import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String hresko_urlMysql = "jdbc:mysql://localhost:3306/hresko?allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String kaminska_urlMysql = "jdbc:mysql://localhost:3306/kaminska?allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String kulcsar_urlMysql = "jdbc:mysql://localhost:3306/fberg_lf?allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String sutorcik_urlPostgresql = "jdbc:postgresql://localhost:5432/postgres";
        String nasa_DB = "jdbc:mysql://localhost:3306/mydb?allowPublicKeyRetrieval=true&serverTimezone=UTC";


        String mysqlName = "username v mysql";
        String postrgresName =  "username v postgres";
        String passwd = " heslo pre pouzivatelov ja som mal take iste pri mysql aj postgres preto len jedno heslo *";
        Connection hresko_mysqlConnection = DriverManager.getConnection(hresko_urlMysql,mysqlName,passwd);
        Connection kaminska_mysqlConnection = DriverManager.getConnection(kaminska_urlMysql,mysqlName,passwd);
        Connection kulcsar_mysqlConnection = DriverManager.getConnection(kulcsar_urlMysql,mysqlName,passwd);
        Connection sutorcik_postgreConnection = DriverManager.getConnection(sutorcik_urlPostgresql,postrgresName,passwd); //keby maš ine heslo tak ho daj tu kde je postres *
        Connection nasa_DBConnection = DriverManager.getConnection(nasa_DB,mysqlName,passwd);

        Statement hresko_statement = hresko_mysqlConnection.createStatement();
        Statement kaminska_statement = kaminska_mysqlConnection.createStatement();
        Statement kulcsar_statement = kulcsar_mysqlConnection.createStatement();
        Statement sutorcik_statement = sutorcik_postgreConnection.createStatement();
        Statement nasa_DB_statement = nasa_DBConnection.createStatement();

        //Select autorov
        ResultSet hresko_resultSet_autor = hresko_statement.executeQuery("select * from authors");
        ResultSet kaminska_resultSet_autor = kaminska_statement.executeQuery("select * from author");
        ResultSet kulcsar_resultSet_autor = kulcsar_statement.executeQuery("select * from autori");
        ResultSet sutorcik_resultSet_autor = sutorcik_statement.executeQuery("select * from autor");
        ResultSet nasa_DB_resultSet_autor = nasa_DB_statement.executeQuery("select * from autor");

        ArrayList<Autor> hresko_autorList = new ArrayList<>();
        ArrayList<Autor> kaminska_autorList = new ArrayList<>();
        ArrayList<Autor> kulcsar_autorList = new ArrayList<>();
        ArrayList<Autor> sutorcik_autorList = new ArrayList<>();
        ArrayList<Autor> nasa_DB_autorList = new ArrayList<>();

        Autor autor;

        //vyberanie autorov
        while (hresko_resultSet_autor.next()){
            String[] parse = hresko_resultSet_autor.getString("name").split(",");
            if (parse.length>1) {
                autor = new Autor(hresko_resultSet_autor.getString("id"), parse[1], parse[0]);
                hresko_autorList.add(autor);
            } else {
                autor = new Autor(hresko_resultSet_autor.getString("id"), "", parse[0]);
                hresko_autorList.add(autor);
            }
        }

        while (kaminska_resultSet_autor.next()) {
            autor = new Autor(kaminska_resultSet_autor.getString("id_author"),kaminska_resultSet_autor.getString("first_name"),kaminska_resultSet_autor.getString("last_name"));
            kaminska_autorList.add(autor);
        }

        while (kulcsar_resultSet_autor.next()) {
            autor = new Autor(kulcsar_resultSet_autor.getString("autor_id"),kulcsar_resultSet_autor.getString("meno"),kulcsar_resultSet_autor.getString("priezvisko"));
            kulcsar_autorList.add(autor);
        }

        while (sutorcik_resultSet_autor.next()) {
            autor = new Autor(sutorcik_resultSet_autor.getString("autor_id"),sutorcik_resultSet_autor.getString("meno"),sutorcik_resultSet_autor.getString("priezvisko"));
            sutorcik_autorList.add(autor);
        }

        while (nasa_DB_resultSet_autor.next()) {
            autor = new Autor(nasa_DB_resultSet_autor.getString("idAutor"),nasa_DB_resultSet_autor.getString("Meno"),nasa_DB_resultSet_autor.getString("Priezvisko"));
            nasa_DB_autorList.add(autor);
        }

        System.out.println("Autori");
        System.out.println("Hresko: " + hresko_autorList.size() +" "+ "Kaminska: " + kaminska_autorList.size() +" "+ "Kulcsar: " +  kulcsar_autorList.size() +" "+ "Sutorcik: " + sutorcik_autorList.size() +" "+ "Naša DB: " +  nasa_DB_autorList.size() + "\n");


        //select kat epc
        ResultSet hresko_resultSet_epcCat = hresko_statement.executeQuery("select epc_cat from epcs");
        ResultSet kaminska_resultSet_epcCat = kaminska_statement.executeQuery("select category_EPC from publication");
        ResultSet kulcsar_resultSet_epcCat = kulcsar_statement.executeQuery("select * from kategorie");
        ResultSet sutorcik_resultSet_epcCat = sutorcik_statement.executeQuery("select kat_epc from praca");
        ResultSet nasa_DB_resultSet_epcCat = nasa_DB_statement.executeQuery("select * from kat_epc");

        KatEpc katEpc;

        ArrayList<KatEpc> hresko_epcCatList = new ArrayList<>();
        ArrayList<KatEpc> kaminska_epcCatList = new ArrayList<>();
        ArrayList<KatEpc> kulcsar_epcCatList = new ArrayList<>();
        ArrayList<KatEpc> sutorcik_epcCatList = new ArrayList<>();
        ArrayList<KatEpc> nasa_DB_epcCatList = new ArrayList<>();

        //vyberanie kat_epc
        while (hresko_resultSet_epcCat.next()){
            katEpc = new KatEpc("", hresko_resultSet_epcCat.getString("epc_cat"));
            hresko_epcCatList.add(katEpc);
        }
        while (kaminska_resultSet_epcCat.next()){
            katEpc = new KatEpc("", kaminska_resultSet_epcCat.getString("category_EPC"));
            kaminska_epcCatList.add(katEpc);
        }
        while (kulcsar_resultSet_epcCat.next()){
            katEpc = new KatEpc(kulcsar_resultSet_epcCat.getString("kategoria_id"), kulcsar_resultSet_epcCat.getString("kod"));
            kulcsar_epcCatList.add(katEpc);
        }
        while (sutorcik_resultSet_epcCat.next()){
            katEpc = new KatEpc("", sutorcik_resultSet_epcCat.getString("kat_epc"));
            sutorcik_epcCatList.add(katEpc);
        }

        while (nasa_DB_resultSet_epcCat.next()){
            katEpc = new KatEpc(nasa_DB_resultSet_epcCat.getString("idkatepc"), nasa_DB_resultSet_epcCat.getString("katepc"));
            nasa_DB_epcCatList.add(katEpc);
        }

        System.out.println("Kat Epc");
        System.out.println("Hresko: " + hresko_epcCatList.size() +" "+ "Kaminska: " +  kaminska_epcCatList.size() +" "+ "Kulcsar: " +  kulcsar_epcCatList.size() +" "+  "Sutorcik: " +  sutorcik_epcCatList.size() +" "+ "Naša DB: " +  nasa_DB_epcCatList.size() + "\n");

        //select klucove slova
        ResultSet hresko_resultSet_keyword = hresko_statement.executeQuery("select * from keywords");
        ResultSet kaminska_resultSet_keyword = kaminska_statement.executeQuery("select * from keyword");
        ResultSet kulcsar_resultSet_keyword = kulcsar_statement.executeQuery("select * from klucove_slova");
        ResultSet sutorcik_resultSet_keyword = sutorcik_statement.executeQuery("select klucove_slovo from klucove_slovo");
        ResultSet nasa_DB_resultSet_keyword = nasa_DB_statement.executeQuery("select * from klucove_slova");

        ArrayList<Keyword> hresko_keywordList = new ArrayList<>();
        ArrayList<Keyword> kaminska_keywordList = new ArrayList<>();
        ArrayList<Keyword> kulcsar_keywordList = new ArrayList<>();
        ArrayList<Keyword> sutorcik_keywordList = new ArrayList<>();
        ArrayList<Keyword> nasa_DB_keywordList = new ArrayList<>();

        Keyword keyword;

        while (hresko_resultSet_keyword.next()){
            keyword = new Keyword(hresko_resultSet_keyword.getString("id"),hresko_resultSet_keyword.getString("name"));
            hresko_keywordList.add(keyword);
        }

        while (kaminska_resultSet_keyword.next()){
            keyword = new Keyword(kaminska_resultSet_keyword.getString("id_keyword"),kaminska_resultSet_keyword.getString("value"));
            kaminska_keywordList.add(keyword);
        }

        while (kulcsar_resultSet_keyword.next()){
            keyword = new Keyword(kulcsar_resultSet_keyword.getString("klucove_slovo_id"),kulcsar_resultSet_keyword.getString("klucove_slovo"));
            kulcsar_keywordList.add(keyword);
        }

        while (sutorcik_resultSet_keyword.next()){
            keyword = new Keyword("",sutorcik_resultSet_keyword.getString("klucove_slovo"));
            sutorcik_keywordList.add(keyword);
        }

        while (nasa_DB_resultSet_keyword.next()){
            keyword = new Keyword(nasa_DB_resultSet_keyword.getString("idkluc_slova"),nasa_DB_resultSet_keyword.getString("kluc_slova"));
            nasa_DB_keywordList.add(keyword);
        }

        System.out.println("Klucove Slova");
        System.out.println("Hresko: " + hresko_keywordList.size() + " " + "Kaminska: " +  kaminska_keywordList.size() + " " + "Kulcsar: " +  kulcsar_keywordList.size() + " " + "Sutorcik: " +  sutorcik_keywordList.size() +" "+ "Naša DB: " +  nasa_DB_keywordList.size() + "\n");

        //Katedra
        ResultSet hresko_resultSet_katedra = hresko_statement.executeQuery("select workplace from epcs");
        ResultSet kaminska_resultSet_katedra = kaminska_statement.executeQuery("select * from department");
        ResultSet kulcsar_resultSet_katedra = kulcsar_statement.executeQuery("select * from pracoviska");
        ResultSet sutorcik_resultSet_katedra = sutorcik_statement.executeQuery("select * from pracovisko");
        ResultSet nasa_DB_resultSet_katedra = nasa_DB_statement.executeQuery("select * from katedra");

        ArrayList<Katedra> hresko_katedraList = new ArrayList<>();
        ArrayList<Katedra> kaminska_katedraList = new ArrayList<>();
        ArrayList<Katedra> kulcsar_katedraList = new ArrayList<>();
        ArrayList<Katedra> sutorcik_katedraList = new ArrayList<>();
        ArrayList<Katedra> nasa_DB_katedraList = new ArrayList<>();

        Katedra katedra;

        while (hresko_resultSet_katedra.next()) {
            katedra = new Katedra("", hresko_resultSet_katedra.getString("workplace"), "");
            hresko_katedraList.add(katedra);
        }

        while (kaminska_resultSet_katedra.next()) {
            katedra = new Katedra(kaminska_resultSet_katedra.getString("id_department"), kaminska_resultSet_katedra.getString("name"), kaminska_resultSet_katedra.getString("id_faculty"));
            kaminska_katedraList.add(katedra);
        }

        while (kulcsar_resultSet_katedra.next()) {
            katedra = new Katedra(kulcsar_resultSet_katedra.getString("pracovisko_id"), kulcsar_resultSet_katedra.getString("nazov"), kulcsar_resultSet_katedra.getString("fakulta_id"));
            kulcsar_katedraList.add(katedra);
        }

        while (sutorcik_resultSet_katedra.next()) {
            katedra = new Katedra(sutorcik_resultSet_katedra.getString("pracovisko_id"), sutorcik_resultSet_katedra.getString("nazov"), sutorcik_resultSet_katedra.getString("fakulta_id"));
            sutorcik_katedraList.add(katedra);
        }

        while (nasa_DB_resultSet_katedra.next()) {
            katedra = new Katedra(nasa_DB_resultSet_katedra.getString("idKatedra"), nasa_DB_resultSet_katedra.getString("Katedra"), nasa_DB_resultSet_katedra.getString("Fakulta_idFakulta"));
            nasa_DB_katedraList.add(katedra);
        }

        System.out.println("Katedra");
        System.out.println("Hresko: " + hresko_katedraList.size() + " " + "Kaminska: " +  kaminska_katedraList.size() + " " + "Kulcsar: " +  kulcsar_katedraList.size() + " " + "Sutorcik: " +  sutorcik_katedraList.size() +" "+ "Naša DB: " +  nasa_DB_katedraList.size() + "\n");

        //Fakulta
        ResultSet kaminska_resultSet_fakulta = kaminska_statement.executeQuery("select * from faculty");
        ResultSet kulcsar_resultSet_fakulta = kulcsar_statement.executeQuery("select * from fakulty");
        ResultSet sutorcik_resultSet_fakulta = sutorcik_statement.executeQuery("select * from fakulta");
        ResultSet nasa_DB_resultSet_fakulta = nasa_DB_statement.executeQuery("select * from fakulta");

        ArrayList<Fakulta> kaminska_fakultaList = new ArrayList<>();
        ArrayList<Fakulta> kulcsar_fakultaList = new ArrayList<>();
        ArrayList<Fakulta> sutorcik_fakultaList = new ArrayList<>();
        ArrayList<Fakulta> nasa_DB_fakultaList = new ArrayList<>();

        Fakulta fakulta;

        while (kaminska_resultSet_fakulta.next()) {
            fakulta = new Fakulta(kaminska_resultSet_fakulta.getString("id_faculty"),kaminska_resultSet_fakulta.getString("name"));
            kaminska_fakultaList.add(fakulta);
        }

        while (kulcsar_resultSet_fakulta.next()) {
            fakulta = new Fakulta(kulcsar_resultSet_fakulta.getString("fakulta_id"),kulcsar_resultSet_fakulta.getString("nazov"));
            kulcsar_fakultaList.add(fakulta);
        }

        while (sutorcik_resultSet_fakulta.next()) {
            fakulta = new Fakulta(sutorcik_resultSet_fakulta.getString("fakulta_id"),sutorcik_resultSet_fakulta.getString("nazov"));
            sutorcik_fakultaList.add(fakulta);
        }

        while (nasa_DB_resultSet_fakulta.next()) {
            fakulta = new Fakulta(nasa_DB_resultSet_fakulta.getString("idFakulta"),nasa_DB_resultSet_fakulta.getString("Fakulta"));
            nasa_DB_fakultaList.add(fakulta);
        }
        System.out.println("Fakulta");
        System.out.println("Kaminska: " + kaminska_fakultaList.size() + " " + "Kulcsar: " +  kulcsar_fakultaList.size() + " " + "Sutorcik: " +  sutorcik_fakultaList.size() +" "+ "Naša DB: " +  nasa_DB_fakultaList.size() + "\n");

        //Mesta
        ResultSet kaminska_resultSet_mesto = kaminska_statement.executeQuery("select place from publication");
        ResultSet sutorcik_resultSet_mesto = sutorcik_statement.executeQuery("select * from praca");
        ResultSet nasa_DB_resultSet_mesto = nasa_DB_statement.executeQuery("select * from mesta");

        ArrayList<Mesta> kaminska_mestoList = new ArrayList<>();
        ArrayList<Mesta> sutorcik_mestoList = new ArrayList<>();
        ArrayList<Mesta> nasa_DB_mestoList = new ArrayList<>();

        Mesta mesta;

        while (kaminska_resultSet_mesto.next()) {
            mesta = new Mesta("",kaminska_resultSet_mesto.getString("place"));
            kaminska_mestoList.add(mesta);
        }

        while (sutorcik_resultSet_mesto.next()) {
            mesta = new Mesta("",sutorcik_resultSet_mesto.getString("miesto_vydania"));
            sutorcik_mestoList.add(mesta);
        }

        while (nasa_DB_resultSet_mesto.next()) {
            mesta = new Mesta(nasa_DB_resultSet_mesto.getString("idMesta"),nasa_DB_resultSet_mesto.getString("Mesto"));
            nasa_DB_mestoList.add(mesta);
        }
        System.out.println("Mesta");
        System.out.println("Kaminska: " +kaminska_mestoList.size() + " " + "Sutorcik: " +  sutorcik_mestoList.size() +" "+ "Naša DB: " +  nasa_DB_mestoList.size()  + "\n");

        //Zaznam
        ResultSet hresko_resultSet_zaznam = hresko_statement.executeQuery("select * from epcs");
        ResultSet kaminska_resultSet_zaznam = kaminska_statement.executeQuery("select * from publication");
        ResultSet kulcsar_resultSet_zaznam = kulcsar_statement.executeQuery("select * from diela");
        ResultSet sutorcik_resultSet_zaznam = sutorcik_statement.executeQuery("select * from praca");
        ResultSet nasa_DB_resultSet_zaznam = nasa_DB_statement.executeQuery("select * from zaznam");

        ArrayList<Zaznam> hresko_zaznamList = new ArrayList<>();
        ArrayList<Zaznam> kaminska_zaznamList = new ArrayList<>();
        ArrayList<Zaznam> kulcsar_zaznamList = new ArrayList<>();
        ArrayList<Zaznam> sutorcik_zaznamList = new ArrayList<>();
        ArrayList<Zaznam> nasa_DB_zaznamList = new ArrayList<>();

        Zaznam zaznam;

        while (hresko_resultSet_zaznam.next()) {
            zaznam = new Zaznam(hresko_resultSet_zaznam.getString("id"), hresko_resultSet_zaznam.getString("title"), hresko_resultSet_zaznam.getString("arch_num"), hresko_resultSet_zaznam.getString("epc_cat"), hresko_resultSet_zaznam.getString("issn"), hresko_resultSet_zaznam.getString("isbn"), hresko_resultSet_zaznam.getString("numberOfPages"));
            hresko_zaznamList.add(zaznam);
        }

        while (kaminska_resultSet_zaznam.next()) {
            zaznam = new Zaznam(kaminska_resultSet_zaznam.getString("id_publication"), kaminska_resultSet_zaznam.getString("title"), kaminska_resultSet_zaznam.getString("arch_num"), kaminska_resultSet_zaznam.getString("category_EPC"), kaminska_resultSet_zaznam.getString("ISBNISSN"), "", kaminska_resultSet_zaznam.getString("pages"));
            kaminska_zaznamList.add(zaznam);
        }

        while (kulcsar_resultSet_zaznam.next()) {
            zaznam = new Zaznam(kulcsar_resultSet_zaznam.getString("dielo_id"), kulcsar_resultSet_zaznam.getString("nazov"), kulcsar_resultSet_zaznam.getString("archivacne_cislo"), kulcsar_resultSet_zaznam.getString("kategoria_id"), kulcsar_resultSet_zaznam.getString("ISSN"), kulcsar_resultSet_zaznam.getString("ISBN"), kulcsar_resultSet_zaznam.getString("strany"));
            kulcsar_zaznamList.add(zaznam);
        }

        while (sutorcik_resultSet_zaznam.next()) {
            zaznam = new Zaznam(sutorcik_resultSet_zaznam.getString("praca_id"), sutorcik_resultSet_zaznam.getString("nazov"), sutorcik_resultSet_zaznam.getString("archiv_id"), sutorcik_resultSet_zaznam.getString("kat_epc"), sutorcik_resultSet_zaznam.getString("issn"), sutorcik_resultSet_zaznam.getString("isbn"), sutorcik_resultSet_zaznam.getString("pocet_stran"));
            sutorcik_zaznamList.add(zaznam);
        }

        while (nasa_DB_resultSet_zaznam.next()) {
            zaznam = new Zaznam(nasa_DB_resultSet_zaznam.getString("idZaznam"), nasa_DB_resultSet_zaznam.getString("Nazov"), nasa_DB_resultSet_zaznam.getString("arch_cislo"), nasa_DB_resultSet_zaznam.getString("fkkat_epc"), nasa_DB_resultSet_zaznam.getString("ISSN"), nasa_DB_resultSet_zaznam.getString("ISBN"), nasa_DB_resultSet_zaznam.getString("pocet_stran"));
            nasa_DB_zaznamList.add(zaznam);
        }

        System.out.println("Zaznamy");
        System.out.println("Hresko: " + hresko_zaznamList.size() +" " + "Kaminska: " +  kaminska_zaznamList.size() +" "+ "Kulcsar: " +  kulcsar_zaznamList.size() +" " + sutorcik_zaznamList.size() +" "+ "Naša DB: " +  nasa_DB_zaznamList.size() + "\n");

        //Roky
        ResultSet hresko_resultSet_roky = hresko_statement.executeQuery("select * from epcs");
        ResultSet kaminska_resultSet_roky = kaminska_statement.executeQuery("select * from publication");
        ResultSet kulcsar_resultSet_roky = kulcsar_statement.executeQuery("select * from diela");
        ResultSet sutorcik_resultSet_roky = sutorcik_statement.executeQuery("select * from praca");
        ResultSet nasa_DB_resultSet_roky = nasa_DB_statement.executeQuery("select * from roky");

        ArrayList<Roky> hresko_rokyList = new ArrayList<>();
        ArrayList<Roky> kaminska_rokyList = new ArrayList<>();
        ArrayList<Roky> kulcsar_rokyList = new ArrayList<>();
        ArrayList<Roky> sutorcik_rokyList = new ArrayList<>();
        ArrayList<Roky> nasa_DB_rokyList = new ArrayList<>();

        Roky roky;

        while (hresko_resultSet_roky.next()) {
            roky = new Roky("", hresko_resultSet_roky.getString("year"));
            hresko_rokyList.add(roky);
        }

        while (kaminska_resultSet_roky.next()) {
            roky = new Roky("", kaminska_resultSet_roky.getString("year"));
            kaminska_rokyList.add(roky);
        }

        while (kulcsar_resultSet_roky.next()) {
            roky = new Roky("", kulcsar_resultSet_roky.getString("rok_vydania"));
            kulcsar_rokyList.add(roky);
        }

        while (sutorcik_resultSet_roky.next()) {
            roky = new Roky("", sutorcik_resultSet_roky.getString("rok_vydania"));
            sutorcik_rokyList.add(roky);
        }

        while (nasa_DB_resultSet_roky.next()) {
            roky = new Roky(nasa_DB_resultSet_roky.getString("idRoky"), nasa_DB_resultSet_roky.getString("roky"));
            nasa_DB_rokyList.add(roky);
        }

        System.out.println("Roky");
        System.out.println("Hresko: " + hresko_rokyList.size() +" " + "Kaminska: " +  kaminska_rokyList.size() +" "+ "Kulcsar: " + kulcsar_rokyList.size() +" " + "Sutorcik: " +  sutorcik_rokyList.size() +" "+ "Naša DB: " + nasa_DB_rokyList.size() + "\n");


        //Zaznam has autors
        ResultSet hresko_resultSet_zaznam_autor = hresko_statement.executeQuery("select * from epcs_authors");
        ResultSet kaminska_resultSet_zaznam_autor = kaminska_statement.executeQuery("select * from author_publication");
        ResultSet kulcsar_resultSet_zaznam_autor = kulcsar_statement.executeQuery("select * from autor_dielo_pracovisko");
        ResultSet sutorcik_resultSet_zaznam_autor = sutorcik_statement.executeQuery("select * from praca_autor_pracovisko");
        ResultSet nasa_DB_resultSet_zaznam_autor = nasa_DB_statement.executeQuery("select * from zaznam_has_autor");

        ArrayList<ZaznamHasAutor> hresko_zaznam_autor_List = new ArrayList<>();
        ArrayList<ZaznamHasAutor> kaminska_zaznam_autor_List = new ArrayList<>();
        ArrayList<ZaznamHasAutor> kulcsar_zaznam_autor_List = new ArrayList<>();
        ArrayList<ZaznamHasAutor> sutorcik_zaznam_autor_List = new ArrayList<>();
        ArrayList<ZaznamHasAutor> nasa_DB_zaznam_autor_List = new ArrayList<>();

        ZaznamHasAutor zaznamHasAutor;
        while (hresko_resultSet_zaznam_autor.next()) {
            zaznamHasAutor = new ZaznamHasAutor(hresko_resultSet_zaznam_autor.getString("epc_id"), hresko_resultSet_zaznam_autor.getString("author_id"));
            hresko_zaznam_autor_List.add(zaznamHasAutor);
        }

        while (kaminska_resultSet_zaznam_autor.next()) {
            zaznamHasAutor = new ZaznamHasAutor(kaminska_resultSet_zaznam_autor.getString("id_publication"), kaminska_resultSet_zaznam_autor.getString("id_author"));
            kaminska_zaznam_autor_List.add(zaznamHasAutor);
        }

        while (kulcsar_resultSet_zaznam_autor.next()) {
            zaznamHasAutor = new ZaznamHasAutor(kulcsar_resultSet_zaznam_autor.getString("dielo_id"), kulcsar_resultSet_zaznam_autor.getString("autor_id"));
            kulcsar_zaznam_autor_List.add(zaznamHasAutor);
        }

        while (sutorcik_resultSet_zaznam_autor.next()) {
            zaznamHasAutor = new ZaznamHasAutor(sutorcik_resultSet_zaznam_autor.getString("praca_id"), sutorcik_resultSet_zaznam_autor.getString("autor_id"));
            sutorcik_zaznam_autor_List.add(zaznamHasAutor);
        }

        while (nasa_DB_resultSet_zaznam_autor.next()) {
            zaznamHasAutor = new ZaznamHasAutor(nasa_DB_resultSet_zaznam_autor.getString("fkzaznam"), nasa_DB_resultSet_zaznam_autor.getString("fkautor"));
            nasa_DB_zaznam_autor_List.add(zaznamHasAutor);
        }

        System.out.println("Zaznam has autor");
        System.out.println("Hresko: " + hresko_zaznam_autor_List.size() +" " + "Kaminska: " +  kaminska_zaznam_autor_List.size() +" "+ "Kulcsar: " + kulcsar_zaznam_autor_List.size() +" " + "Sutorcik: " +  sutorcik_zaznam_autor_List.size() +" "+ "Naša DB: " + nasa_DB_zaznam_autor_List.size() + "\n");


        // Zaznam has klucove slova
        ResultSet hresko_resultSet_zaznam_keyword = hresko_statement.executeQuery("select * from epcs_keywords");
        ResultSet kaminska_resultSet_zaznam_keyword = kaminska_statement.executeQuery("select * from publication_keyword");
        ResultSet kulcsar_resultSet_zaznam_keyword = kulcsar_statement.executeQuery("select * from dielo_klucove_slovo");
        ResultSet sutorcik_resultSet_zaznam_keyword = sutorcik_statement.executeQuery("select * from klucove_slovo");
        ResultSet nasa_DB_resultSet_zaznam_keyword = nasa_DB_statement.executeQuery("select * from zaznam_has_klucove_slova");

        ArrayList<ZaznamHasKlucoveSlovo> hresko_zaznam_keyword_List = new ArrayList<>();
        ArrayList<ZaznamHasKlucoveSlovo> kaminska_zaznam_keyword_List = new ArrayList<>();
        ArrayList<ZaznamHasKlucoveSlovo> kulcsar_zaznam_keyword_List = new ArrayList<>();
        ArrayList<ZaznamHasKlucoveSlovo> sutorcik_zaznam_keyword_List = new ArrayList<>();
        ArrayList<ZaznamHasKlucoveSlovo> nasa_DB_zaznam_keyword_List = new ArrayList<>();

        ZaznamHasKlucoveSlovo zaznamHasKlucoveSlovo;

        while (hresko_resultSet_zaznam_keyword.next()) {
            zaznamHasKlucoveSlovo = new ZaznamHasKlucoveSlovo(hresko_resultSet_zaznam_keyword.getString("epc_id"), hresko_resultSet_zaznam_keyword.getString("keyword_id"));
            hresko_zaznam_keyword_List.add(zaznamHasKlucoveSlovo);
        }

        while (kaminska_resultSet_zaznam_keyword.next()) {
            zaznamHasKlucoveSlovo = new ZaznamHasKlucoveSlovo(kaminska_resultSet_zaznam_keyword.getString("id_publication"), kaminska_resultSet_zaznam_keyword.getString("id_keyword"));
            kaminska_zaznam_keyword_List.add(zaznamHasKlucoveSlovo);
        }

        while (kulcsar_resultSet_zaznam_keyword.next()) {
            zaznamHasKlucoveSlovo = new ZaznamHasKlucoveSlovo(kulcsar_resultSet_zaznam_keyword.getString("dielo_id"), kulcsar_resultSet_zaznam_keyword.getString("klucove_slovo_id"));
            kulcsar_zaznam_keyword_List.add(zaznamHasKlucoveSlovo);
        }

        while (sutorcik_resultSet_zaznam_keyword.next()) {
            zaznamHasKlucoveSlovo = new ZaznamHasKlucoveSlovo(sutorcik_resultSet_zaznam_keyword.getString("praca_id"), sutorcik_resultSet_zaznam_keyword.getString("klucove_slovo"));
            sutorcik_zaznam_keyword_List.add(zaznamHasKlucoveSlovo);
        }

        while (nasa_DB_resultSet_zaznam_keyword.next()) {
            zaznamHasKlucoveSlovo = new ZaznamHasKlucoveSlovo(nasa_DB_resultSet_zaznam_keyword.getString("fkzaznam"), nasa_DB_resultSet_zaznam_keyword.getString("fkklucoveslova"));
            nasa_DB_zaznam_keyword_List.add(zaznamHasKlucoveSlovo);
        }

        System.out.println("Zaznam has keyword");
        System.out.println("Hresko: " + hresko_zaznam_keyword_List.size() +" " + "Kaminska: " +  kaminska_zaznam_keyword_List.size() +" "+ "Kulcsar: " + kulcsar_zaznam_keyword_List.size() +" " + "Sutorcik: " +  sutorcik_zaznam_keyword_List.size() +" "+ "Naša DB: " + nasa_DB_zaznam_keyword_List.size() + "\n");

    }
}

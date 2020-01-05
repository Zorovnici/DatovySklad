

import java.sql.*;
import java.util.*;

public class Main2 {

    public static void main(String[] args) throws SQLException {
        String starSchemaMysql = "jdbc:mysql://localhost:3306/newstarschema?allowPublicKeyRetrieval=true&serverTimezone=UTC";
        List<String> idPocetStran = new ArrayList<>();
        List<String> idZaznam = new ArrayList<>();
        List<String> pocetStran = new ArrayList<>();

        String mysqlName = "root";
        String passwd = "teamcity";
        Connection starSchemaConnection = DriverManager.getConnection(starSchemaMysql, mysqlName, passwd);

        Statement starSchema_statement = starSchemaConnection.createStatement();

        /*ResultSet nasa_DB_resultSet_zaznam = starSchema_statement.executeQuery("select * from pocet_stran");

        while (nasa_DB_resultSet_zaznam.next()) {
            idPocetStran.add(nasa_DB_resultSet_zaznam.getString("idPocetStran"));
            idZaznam.add(nasa_DB_resultSet_zaznam.getString("newId"));
            pocetStran.add(nasa_DB_resultSet_zaznam.getString("pocet_stran"));
        }

        System.out.println(idPocetStran.size()+"-"+idZaznam.size()+"-"+pocetStran.size());
        System.out.println(idZaznam.get(2)+"-"+idPocetStran.get(2)+"-"+pocetStran.get(2));

        Iterator<String> idPS = idPocetStran.iterator();
        Iterator<String> idZa = idZaznam.iterator();
        Iterator<String> ps = pocetStran.iterator();
        int sum = 0;
        boolean is=false;

        while (idPS.hasNext() & idZa.hasNext() & ps.hasNext()){
            String idpocstran = idPS.next();
            String idzaznam = idZa.next();
            String pocstran = ps.next();
            //System.out.println("-"+pocstran+"-");
            if (pocstran.equals("")) {
                //pocstran = "0";

                is=true;
            }
            else {
                pocstran = pocstran.replaceAll(" ", "");
                pocstran = pocstran.replaceAll("S", "");
                pocstran = pocstran.replaceAll("s", "");
                pocstran = pocstran.replaceAll("p", "");
                pocstran = pocstran.replaceAll("P", "");
                pocstran = pocstran.replaceAll("\\.", "");
                //System.out.println(pocstran);
            }

            if (pocstran.contains("-")){
                String str[] = pocstran.split("-");
                Set<Integer> strlist = new HashSet<>();
                for (String s : str){
                    strlist.add(Integer.parseInt(s));
                }
                int max = Collections.max(strlist);
                int min = Collections.min(strlist);
                sum = max - min ;
                //System.out.println(pocstran+"-"+sum);
            }
            else {
               // System.out.println(pocstran);
                if (is)
                {
                    sum = 0;
                    is=false;
                }
                else{
                    if (pocstran.equals("null"))
                        sum=0;
                    else if (pocstran.equals(""))
                        sum=0;
                    else
                    sum = Integer.parseInt(pocstran);
                }

            }

            String sql = "UPDATE faktova_tabulka SET idPocetStran="+idpocstran+",pocetStranDiela="+sum+" WHERE idZaznam="+idzaznam;
            System.out.println(sql);
            starSchema_statement.executeUpdate(sql);

        }
*/
       // Multimap<String, String> map = ArrayListMultimap.create();
        //Map<String, String> map = new HashMap<>();
        List<String> idZaz = new ArrayList<>();
        List<String> count = new ArrayList<>();
        ResultSet nasa_DB_resultSet_zaznam = starSchema_statement.executeQuery("select idZaznam,COUNT(idKey) from zaznam_key GROUP BY idZaznam ORDER BY idZaznam");

        while (nasa_DB_resultSet_zaznam.next()){
            idZaz.add(nasa_DB_resultSet_zaznam.getString(1));
            count.add(nasa_DB_resultSet_zaznam.getString(2));
        }

        Iterator<String> itzaz = idZaz.iterator();
        Iterator<String> itcount = count.iterator();
        while (itzaz.hasNext() & itcount.hasNext()){
            String sql = "UPDATE faktova_tabulka SET pocetKlucovychSlov="+itcount.next()+" WHERE idZaznam="+itzaz.next();
            System.out.println(sql);
            starSchema_statement.executeUpdate(sql);
        }






    }

}

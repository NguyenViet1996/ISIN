package Project8;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProcessDataOnDatabase {

    private String url;

    public ProcessDataOnDatabase(String url) {
        this.url = url;
    }

   public void setDataFromDatabase(String url) {
         this.url = url;
   };

   public List<Isin> getDataFromDatabase() {
       Connection con = null;
       Statement stmt = null;
       List<Isin> listIsin = new ArrayList<>();
       try {
           con = DriverManager.getConnection(url,"SA","");
           stmt = con.createStatement();
           ResultSet rs = stmt.executeQuery("select * from ISINS");
           while(rs.next()) {
               String nameIsin = rs.getString("name_isin");
               int quantity = rs.getInt("quantity");
               int price = rs.getInt("price");
               Isin isin = new Isin(nameIsin,quantity,price);
               listIsin.add(isin);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return listIsin;

   }

    public void updateIsinList( List<Isin> dataAfterProcessing) throws SQLException {
       Connection con = null;
       con = DriverManager.getConnection(url,"SA","");
       String sql = "update ISINS set quantity = ? WHERE name_isin = ? ";
       try {
           for(Isin isin : dataAfterProcessing) {
               PreparedStatement ps = con.prepareStatement(sql);
               int quantity = isin.getQuantityIsin();
               String nameIsin = isin.getNameIsin();
               ps.setInt(1,quantity);
               ps.setString(2,nameIsin);
               int result = ps.executeUpdate();
               System.out.println(result);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }

    }

}

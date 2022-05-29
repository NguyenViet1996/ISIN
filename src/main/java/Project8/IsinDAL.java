package Project8;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IsinDAL {

    private final String url;

    public IsinDAL(String url) {
        this.url = url;
    }

   public List<Isin> getDataFromDatabase() {
       List<Isin> listIsin = new ArrayList<>();
       try {
           Connection con = DriverManager.getConnection(url,"SA","");
           Statement stmt = con.createStatement();
           ResultSet rs = stmt.executeQuery("select * from ISINS");
           while(rs.next()) {
               String nameIsin = rs.getString("name_isins");
               int quantity = rs.getInt("quantity");
               int price = rs.getInt("price");
               Isin isin = new Isin(nameIsin,quantity,price);
               listIsin.add(isin);
           }
           con.close();
       } catch (Exception e) {
           e.printStackTrace();
       }
       return listIsin;

   }

    public void updateIsinList( List<Isin> dataAfterProcessing) throws SQLException {
        Connection con = DriverManager.getConnection(url,"SA","");
       String sql = "update ISINS set quantity = ? WHERE name_isins = ? ";
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
           con.close();
       } catch (Exception e) {
           e.printStackTrace();
       }

    }

}

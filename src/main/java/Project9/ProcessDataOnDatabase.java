package Project9;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProcessDataOnDatabase {

		public ProcessDataOnDatabase() {
		}

		public List<Isin> getDataFromDatabase(String url, String userName, String password) {
				Connection con = null;
				Statement stmt = null;
				List<Isin> listIsin = new ArrayList<>();
				try {
						con = DriverManager.getConnection(url,userName,password);
						stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery("select * from ISINS");
						while(rs.next()) {
								String nameIsin = rs.getString("name_isins");
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

		public void updateIsinList( List<Isin> dataAfterProcessing,String url, String userName, String password) throws SQLException {
				Connection con = null;
				con = DriverManager.getConnection(url,userName,password);
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
				} catch (Exception e) {
						e.printStackTrace();
				}

		}

}

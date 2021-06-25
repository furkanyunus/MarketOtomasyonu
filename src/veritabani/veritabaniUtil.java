package veritabani;


import java.sql.Connection;
import java.sql.DriverManager;

public class veritabaniUtil {
	static Connection conn=null;
	public static Connection Baglan() {
	try {
			
			conn=DriverManager.getConnection("jdbc:mysql://localhost/projemdb?useUnicode=true&useLegacyDatetimCode=false&serverTimezone=Turkey", "root", "mysql");
			return conn;
	} catch (Exception e) {
		System.out.println(e.getMessage().toString());
		
		return null;
	
	   }


	
	}
}

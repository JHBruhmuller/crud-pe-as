package crud;

import java.sql.*	;



public class ConexaoMySQL {
	public static String status = "Não conectou...";
	
	public ConexaoMySQL() {  
		
	}
	public static java.sql.Connection getConexaoMySQL() {
    Connection connection = null;    
    String driverName = "com.mysql.cj.jdbc.Driver";
    
  	try {
  		Class.forName(driverName);
  	} catch (ClassNotFoundException e) {
  		e.printStackTrace();
  	}

  	String serverName = "localhost"; 
  	String mydatabase = "Crud";   	
  	String url = "jdbc:mysql://" + serverName + "/" + mydatabase; 
  	String username = "root"; 
  	String password = "";   	 
  	
  	try {
  		connection = DriverManager.getConnection(url, username, password);
  	} catch (SQLException e) {
  		System.out.println(status);
  		e.printStackTrace();
  	}
      
    return connection;
}


}


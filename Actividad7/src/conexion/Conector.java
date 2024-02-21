package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conector {
	// El nombre de BBDD (ad7) es el indicado en el Script de creación junto a esta clase
	// Es necesario especificar url, usuario y contraseña de la BBDD destino
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/ad7";
    static final String DB_USER = "root";
    static final String DB_PASS = "b19l21";
    
	public Conector() {
		registerDriver();
    }
    
	protected Connection getConnection() {
        try {
			return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
	}

    protected void closeConnection(Connection connection) {
        if (connection != null) {
            try {
            	connection.close();
            } catch (SQLException e) {
    			System.out.println("No se ha podido cerrar la conexión con la BD");
                e.printStackTrace();
            }
        }
    }
    
    private void registerDriver() {
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");;
        } catch (ClassNotFoundException e) {
			System.out.println("No se ha encontrado el driver para MySQL");
			return;
		}
		System.out.println("Se ha cargado el Driver de MySQL");
    }
}

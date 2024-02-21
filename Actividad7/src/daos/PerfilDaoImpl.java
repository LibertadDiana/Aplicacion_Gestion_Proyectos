package daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.Conector;
import javabeans.Perfil;

public class PerfilDaoImpl extends Conector implements IntPerfilDao{

	@Override
	public boolean create(Perfil record) {
		int responseCommand = 0;
		Connection connection = this.getConnection();
		
		try {
			responseCommand = connection.createStatement().executeUpdate(
				"INSERT INTO perfiles (id_perfi,nombre,precio_hora)"	+ 
				"VALUES(" + 
					record.getIdPerfi() + ",'"+ 
					record.getNombre() + "',"+ 
					record.getPrecioHora() +
				")"
			);
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
		
		this.closeConnection(connection);
		
		return responseCommand != 0;
	}

	@Override
	public boolean update(Perfil record) {
		int responseCommand = 0;
		Connection connection = this.getConnection();
		
		try {
			responseCommand = connection.createStatement().executeUpdate(
				"UPDATE perfiles " +
				"SET nombre = '"+ record.getNombre() +"', " +
					"precio_hora = " + record.getPrecioHora() + " " +
				"WHERE id_perfi = " + record.getIdPerfi()
			);
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
			
		this.closeConnection(connection);
		
		return responseCommand != 0;
	}

	@Override
	public boolean delete(int id) {
		int responseCommand = 0;
		Connection connection = this.getConnection();
		
		try {
			responseCommand = connection.createStatement().executeUpdate(
				"DELETE FROM perfiles "	+ 
				"WHERE id_perfi = " + id
			);
			
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
			
		this.closeConnection(connection);
		
		return responseCommand != 0;
	}

	@Override
	public List<Perfil> findAll() {
		List<Perfil> resultado = new ArrayList<Perfil>();
		Connection connection = this.getConnection();
		
		try {
			ResultSet rs = connection.createStatement().executeQuery(
				"SELECT * "	+ 
				"FROM perfiles"
			);
			while(rs.next()) {
				resultado.add(new Perfil(
					rs.getInt("id_perfi"),
					rs.getString("nombre"),
					rs.getFloat("precio_hora")
				));
			}
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
			
		this.closeConnection(connection);

		return resultado;
	}

	@Override
	public Perfil findById(int id) {
		Perfil resultado = null;
		Connection connection = this.getConnection();
		
		try {
			ResultSet rs = connection.createStatement().executeQuery(
				"SELECT * "	+ 
				"FROM perfiles "	+ 
				"WHERE id_perfi = " + id
			);
			if(rs.next()) {
				resultado = new Perfil(
					rs.getInt("id_perfi"),
					rs.getString("nombre"),
					rs.getFloat("precio_hora")
				);
			}
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
			
		this.closeConnection(connection);
		
		return resultado;
	}
}

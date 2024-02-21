package daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.Conector;
import javabeans.Departamento;

public class DepartamentoDaoImpl extends Conector implements IntDepartamentoDao{

	@Override
	public boolean create(Departamento record) {
		int responseCommand = 0;
		Connection connection = this.getConnection();
		
		try {
			responseCommand = connection.createStatement().executeUpdate(
				"INSERT INTO departamentos (id_depar,nombre,direccion)"	+ 
				"VALUES(" + 
					record.getIdDepar() + ",'"+ 
					record.getNombre() +"','" + 
					record.getDireccion() + 
				"')"
			);
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
		
		this.closeConnection(connection);
		
		return responseCommand != 0;
	}

	@Override
	public boolean update(Departamento record) {
		int responseCommand = 0;
		Connection connection = this.getConnection();
		
		try {
			responseCommand = connection.createStatement().executeUpdate(
				"UPDATE departamentos " +
				"SET nombre = '"+ record.getNombre() +"'," +
					"direccion = '" + record.getDireccion() + "' " +
				"WHERE id_depar = " + record.getIdDepar()
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
				"DELETE FROM departamentos "	+ 
				"WHERE id_depar = " + id
			);
			
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
			
		this.closeConnection(connection);
		
		return responseCommand != 0;
	}

	@Override
	public List<Departamento> findAll() {
		List<Departamento> resultado = new ArrayList<Departamento>();
		Connection connection = this.getConnection();
		
		try {
			ResultSet rs = connection.createStatement().executeQuery(
				"SELECT * "	+ 
				"FROM departamentos"
			);
			while(rs.next()) {
				resultado.add(new Departamento(
					rs.getInt("id_depar"),
					rs.getString("nombre"),
					rs.getString("direccion")
				));
			}
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
			
		this.closeConnection(connection);

		return resultado;
	}

	@Override
	public Departamento findById(int id) {
		Departamento resultado = null;
		Connection connection = this.getConnection();
		
		try {
			ResultSet rs = connection.createStatement().executeQuery(
				"SELECT * "	+ 
				"FROM departamentos "	+ 
				"WHERE id_depar = " + id
			);
			if(rs.next()) {
				resultado = new Departamento(
					rs.getInt("id_depar"),
					rs.getString("nombre"),
					rs.getString("direccion")
				);
			}
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
			
		this.closeConnection(connection);
		
		return resultado;
	}
}

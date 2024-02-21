package daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.Conector;
import javabeans.Cliente;

public class ClienteDaoImpl extends Conector implements IntClienteDao{

	@Override
	public boolean create(Cliente record) {
		int responseCommand = 0;
		Connection connection = this.getConnection();
		
		try {
			responseCommand = connection.createStatement().executeUpdate(
				"INSERT INTO clientes (cif,nombre,apellidos,domicilio,facturacion_anual,numero_empleados)"	+ 
				"VALUES('" + 
					record.getCif() + "','"+ 
					record.getNombre() +"','" + 
					record.getApellidos() + "','"+ 
					record.getDomicilio() + "',"+ 
					record.getFacturacionAnual() + ","+ 
					record.getNumeroEmpleados() +
				")"
			);
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
		
		this.closeConnection(connection);
		
		return responseCommand != 0;
	}

	@Override
	public boolean update(Cliente record) {
		int responseCommand = 0;
		Connection connection = this.getConnection();
		
		try {
			responseCommand = connection.createStatement().executeUpdate(
				"UPDATE clientes " +
				"SET nombre = '"+ record.getNombre() +"'," +
					"apellidos = '" + record.getApellidos() + "'," +
					"domicilio = '"+ record.getDomicilio() +"'," +
					"facturacion_anual = "+ record.getFacturacionAnual() +"," +
					"numero_empleados = "+ record.getNumeroEmpleados() +" " +
				"WHERE cif = '" + record.getCif() + "'"
			);
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
			
		this.closeConnection(connection);
		
		return responseCommand != 0;
	}

	@Override
	public boolean delete(String cif) {
		int responseCommand = 0;
		Connection connection = this.getConnection();
		
		try {
			responseCommand = connection.createStatement().executeUpdate(
				"DELETE FROM clientes "	+ 
				"WHERE cif = '" + cif + "'"
			);
			
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
			
		this.closeConnection(connection);
		
		return responseCommand != 0;
	}

	@Override
	public List<Cliente> findAll() {
		List<Cliente> resultado = new ArrayList<Cliente>();
		Connection connection = this.getConnection();
		
		try {
			ResultSet rs = connection.createStatement().executeQuery(
				"SELECT * "	+ 
				"FROM clientes"
			);
			while(rs.next()) {
				resultado.add(new Cliente(
					rs.getString("cif"),
					rs.getString("nombre"),
					rs.getString("apellidos"),
					rs.getString("domicilio"),
					rs.getFloat("facturacion_anual"),
					rs.getInt("numero_empleados")
				));
			}
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
			
		this.closeConnection(connection);

		return resultado;
	}

	@Override
	public Cliente findById(String cif) {
		Cliente resultado = null;
		Connection connection = this.getConnection();
		
		try {
			ResultSet rs = connection.createStatement().executeQuery(
				"SELECT * "	+ 
				"FROM clientes "	+ 
				"WHERE cif = '" + cif + "'"
			);
			if(rs.next()) {
				resultado = new Cliente(
					rs.getString("cif"),
					rs.getString("nombre"),
					rs.getString("apellidos"),
					rs.getString("domicilio"),
					rs.getFloat("facturacion_anual"),
					rs.getInt("numero_empleados")
				);
			}
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
			
		this.closeConnection(connection);
		
		return resultado;
	}
}

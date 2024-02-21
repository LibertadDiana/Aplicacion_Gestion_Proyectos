package daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import conexion.Conector;
import javabeans.Departamento;
import javabeans.Empleado;
import javabeans.Perfil;

public class EmpleadoDaoImpl extends Conector implements IntEmpleadoDao{

	@Override
	public boolean create(Empleado record) {
		int responseCommand = 0;
		Connection connection = this.getConnection();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			responseCommand = connection.createStatement().executeUpdate(
				"INSERT INTO empleados (id_empl,nombre,apellidos,email,password,salario,comision,fecha_ingreso,fecha_nacimiento,genero,id_perfi,id_depar) "	+ 
				"VALUES(" + 
					record.getIdEmpl() + ",'"+ 
					record.getNombre() + "','"+ 
					record.getApellidos() + "','"+ 
					record.getEmail() + "','"+ 
					record.getPassword() + "',"+ 
					record.getSalario() + ","+ 
					record.getComision() + ",'"+ 
					formatter.format(record.getFechaIngreso()) + "','"+ 
					formatter.format(record.getFechaNacimiento()) + "','"+ 
					record.getGenero() + "',"+ 
					record.getIdPerfi() + ","+ 
					record.getIdDepar() +
				")"
			);
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
		
		this.closeConnection(connection);
		
		return responseCommand != 0;
	}

	@Override
	public boolean update(Empleado record) {
		int responseCommand = 0;
		Connection connection = this.getConnection();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			responseCommand = connection.createStatement().executeUpdate(
				"UPDATE empleados " +
				"SET nombre = '"+ record.getNombre() +"'," +
					"apellidos = '" + record.getApellidos() + "'," +
					"email = '"+ record.getEmail() +"'," +
					"password = '"+ record.getPassword() +"'," +
					"salario = "+ record.getSalario() +"," +
					"comision = "+ record.getComision() +"," +
					"fecha_ingreso = '"+ formatter.format(record.getFechaIngreso()) +"'," +
					"fecha_nacimiento = '"+ formatter.format(record.getFechaNacimiento()) +"'," +
					"genero = '"+ record.getGenero() +"'," +
					"id_perfi = "+ record.getIdPerfi() +"," +
					"id_depar = "+ record.getIdDepar() +" " +
				"WHERE id_empl = " + record.getIdEmpl()
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
				"DELETE FROM empleados "	+ 
				"WHERE id_empl = " + id
			);
			
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
			
		this.closeConnection(connection);
		
		return responseCommand != 0;
	}

	@Override
	public List<Empleado> findAll() {
		List<Empleado> resultado = new ArrayList<Empleado>();
		Connection connection = this.getConnection();
		
		try {
			ResultSet rs = connection.createStatement().executeQuery(
				"SELECT * "	+ 
				"FROM empleados"
			);
			while(rs.next()) {
				resultado.add(new Empleado(
					rs.getInt("id_empl"),
					rs.getString("nombre"),
					rs.getString("apellidos"),
					rs.getString("email"),
					rs.getString("password"),
					rs.getFloat("salario"),
					rs.getFloat("comision"),
					rs.getDate("fecha_ingreso"),
					rs.getDate("fecha_nacimiento"),
					rs.getString("genero").charAt(0),
					rs.getInt("id_perfi"),
					rs.getInt("id_depar")
				));
			}
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
			
		this.closeConnection(connection);

		return resultado;
	}

	@Override
	public Empleado findById(int id) {
		Empleado resultado = null;
		Connection connection = this.getConnection();
		
		try {
			ResultSet rs = connection.createStatement().executeQuery(
				"SELECT e.id_empl,e.nombre,e.apellidos,e.email,e.password,e.salario,e.comision,e.fecha_ingreso,e.fecha_nacimiento,e.genero, "	+ 
						"p.id_perfi,p.nombre AS nombre_perfil, p.precio_hora," +
						"d.id_depar,d.nombre AS nombre_depar,d.direccion " +
				"FROM empleados e " + 
				"INNER JOIN perfiles p ON p.id_perfi = e.id_perfi " + 
				"INNER JOIN departamentos d ON d.id_depar = e.id_depar " + 
				"WHERE id_empl = " + id
			);
			if(rs.next()) {
				resultado = new Empleado(
					rs.getInt("id_empl"),
					rs.getString("nombre"),
					rs.getString("apellidos"),
					rs.getString("email"),
					rs.getString("password"),
					rs.getFloat("salario"),
					rs.getFloat("comision"),
					rs.getDate("fecha_ingreso"),
					rs.getDate("fecha_nacimiento"),
					rs.getString("genero").charAt(0),
					rs.getInt("id_perfi"),
					rs.getInt("id_depar")
				);
				resultado.setPerfil(new Perfil(
					rs.getInt("id_perfi"),
					rs.getString("nombre_perfil"),
					rs.getFloat("precio_hora")
				));
				resultado.setDepartamento(new Departamento(
					rs.getInt("id_depar"),
					rs.getString("nombre_depar"),
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
	public List<Empleado> empleadosByDepartamento(int idDepar) {
		List<Empleado> resultado = new ArrayList<Empleado>();
		Connection connection = this.getConnection();
		
		try {
			ResultSet rs = connection.createStatement().executeQuery(
				"SELECT * "	+ 
				"FROM empleados " + 
				"WHERE id_depar = " + idDepar
			);
			while(rs.next()) {
				resultado.add(new Empleado(
					rs.getInt("id_empl"),
					rs.getString("nombre"),
					rs.getString("apellidos"),
					rs.getString("email"),
					rs.getString("password"),
					rs.getFloat("salario"),
					rs.getFloat("comision"),
					rs.getDate("fecha_ingreso"),
					rs.getDate("fecha_nacimiento"),
					rs.getString("genero").charAt(0),
					rs.getInt("id_perfi"),
					rs.getInt("id_depar")
				));
			}
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
			
		this.closeConnection(connection);

		return resultado;
	}

	
	@Override
	public List<Empleado> empleadosBySexo(char sexo) {
		List<Empleado> resultado = new ArrayList<Empleado>();
		Connection connection = this.getConnection();
		
		try {
			ResultSet rs = connection.createStatement().executeQuery(
				"SELECT * "	+ 
				"FROM empleados " + 
				"WHERE genero = '" + sexo +"'"
			);
			while(rs.next()) {
				resultado.add(new Empleado(
					rs.getInt("id_empl"),
					rs.getString("nombre"),
					rs.getString("apellidos"),
					rs.getString("email"),
					rs.getString("password"),
					rs.getFloat("salario"),
					rs.getFloat("comision"),
					rs.getDate("fecha_ingreso"),
					rs.getDate("fecha_nacimiento"),
					rs.getString("genero").charAt(0),
					rs.getInt("id_perfi"),
					rs.getInt("id_depar")
				));
			}
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
			
		this.closeConnection(connection);

		return resultado;
	}

	
	@Override
	public List<Empleado> empleadosByApellido(String subcadena) {
		List<Empleado> resultado = new ArrayList<Empleado>();
		Connection connection = this.getConnection();
		
		try {
			ResultSet rs = connection.createStatement().executeQuery(
				"SELECT * "	+ 
				"FROM empleados " + 
				"WHERE apellidos like '%"+subcadena+"%'"
			);
			while(rs.next()) {
				resultado.add(new Empleado(
					rs.getInt("id_empl"),
					rs.getString("nombre"),
					rs.getString("apellidos"),
					rs.getString("email"),
					rs.getString("password"),
					rs.getFloat("salario"),
					rs.getFloat("comision"),
					rs.getDate("fecha_ingreso"),
					rs.getDate("fecha_nacimiento"),
					rs.getString("genero").charAt(0),
					rs.getInt("id_perfi"),
					rs.getInt("id_depar")
				));
			}
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
			
		this.closeConnection(connection);

		return resultado;
	}

	
	@Override
	public double salarioTotal() {
		double resultado = 0;
		Connection connection = this.getConnection();
		
		try {
			ResultSet rs = connection.createStatement().executeQuery(
				"SELECT SUM(salario) salarioTotal "	+ 
				"FROM empleados"
			);
			if(rs.next()) {
				resultado = rs.getDouble("salarioTotal");
			}
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
			
		this.closeConnection(connection);
		
		return resultado;
	}

	
	@Override
	public double salarioTotal(int idDepar) {
		double resultado = 0;
		Connection connection = this.getConnection();
		
		try {
			ResultSet rs = connection.createStatement().executeQuery(
				"SELECT SUM(salario) salarioTotal "	+ 
				"FROM empleados " + 
				"WHERE id_depar = " + idDepar
			);
			if(rs.next()) {
				resultado = rs.getDouble("salarioTotal");
			}
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
			
		this.closeConnection(connection);
		
		return resultado;
	}
}

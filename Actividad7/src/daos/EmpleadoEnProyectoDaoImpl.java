package daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import conexion.Conector;
import javabeans.Cliente;
import javabeans.Departamento;
import javabeans.Empleado;
import javabeans.EmpleadoEnProyecto;
import javabeans.Perfil;
import javabeans.Proyecto;

public class EmpleadoEnProyectoDaoImpl extends Conector implements IntEmpleadoEnProyectoDao{

	@Override
	public boolean create(EmpleadoEnProyecto record) {
		int responseCommand = 0;
		Connection connection = this.getConnection();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			responseCommand = connection.createStatement().executeUpdate(
				"INSERT INTO empleado_en_proyecto (numero_orden,id_proyecto,id_empl,horas_asignadas,fecha_incorporacion)"	+ 
				"VALUES(" + 
					record.getNumeroOrden() + ",'"+ 
					record.getIdProyecto() +"'," + 
					record.getIdEmpl() + ","+ 
					record.getHorasAsignadas() + ",'"+ 
					formatter.format(record.getFechaIncorporacion()) +
				"')"
			);
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
		
		this.closeConnection(connection);
		
		return responseCommand != 0;
	}

	@Override
	public boolean update(EmpleadoEnProyecto record) {
		int responseCommand = 0;
		Connection connection = this.getConnection();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			responseCommand = connection.createStatement().executeUpdate(
				"UPDATE empleado_en_proyecto " +
				"SET id_proyecto = '"+ record.getIdProyecto() +"'," +
					"id_empl = '" + record.getIdEmpl() + "'," +
					"horas_asignadas = '"+ record.getHorasAsignadas() +"'," +
					"fecha_incorporacion = '"+ formatter.format(record.getFechaIncorporacion()) +"' " +
				"WHERE numero_orden = " + record.getNumeroOrden()
			);
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
			
		this.closeConnection(connection);
		
		return responseCommand != 0;
	}

	@Override
	public boolean delete(int orden) {
		int responseCommand = 0;
		Connection connection = this.getConnection();
		
		try {
			responseCommand = connection.createStatement().executeUpdate(
				"DELETE FROM empleado_en_proyecto "	+ 
				"WHERE numero_orden = " + orden 
			);
			
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
			
		this.closeConnection(connection);
		
		return responseCommand != 0;
	}

	@Override
	public List<EmpleadoEnProyecto> findAll() {
		List<EmpleadoEnProyecto> resultado = new ArrayList<EmpleadoEnProyecto>();
		Connection connection = this.getConnection();
		
		try {
			ResultSet rs = connection.createStatement().executeQuery(
				"SELECT * "	+ 
				"FROM empleado_en_proyecto"
			);
			while(rs.next()) {
				resultado.add(new EmpleadoEnProyecto(
					rs.getInt("numero_orden"),
					rs.getString("id_proyecto"),
					rs.getInt("id_empl"),
					rs.getInt("horas_asignadas"),
					rs.getDate("fecha_incorporacion")
				));
			}
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
			
		this.closeConnection(connection);

		return resultado;
	}

	@Override
	public EmpleadoEnProyecto findById(int orden) {
		EmpleadoEnProyecto resultado = null;
		Connection connection = this.getConnection();
		
		try {
			ResultSet rs = connection.createStatement().executeQuery(
				"SELECT ep.numero_orden,ep.horas_asignadas,ep.fecha_incorporacion, "	+ 
					"e.id_empl,e.nombre,e.apellidos,e.email,e.password,e.salario,e.comision,e.fecha_ingreso,e.fecha_nacimiento,e.genero, "	+ 
					"p.id_perfi,p.nombre AS nombre_perfil,p.precio_hora,"	+ 
					"d.id_depar,d.nombre AS nombre_depar,d.direccion, "	+ 
					"pr.id_proyecto,pr.descripcion,pr.fecha_inicio,pr.fecha_fin_previsto,pr.fecha_fin_real,pr.venta_prevista,pr.costes_previsto,pr.coste_real,pr.estado,pr.jefe_proyecto, "	+ 
					"c.cif,c.nombre AS nombre_cliente,c.apellidos AS apellidos_cliente,c.domicilio,c.facturacion_anual,c.numero_empleados, "	+ 
					"j.id_empl AS id_empl_jefe,j.nombre AS nombre_jefe,j.apellidos AS apellidos_jefe,j.email AS email_jefe,j.password AS password_jefe,j.salario AS salario_jefe,j.comision AS comision_jefe,j.fecha_ingreso AS fecha_ingreso_jefe,j.fecha_nacimiento AS fecha_nacimiento_jefe,j.genero AS genero_jefe,j.id_perfi AS id_perfi_jefe,j.id_depar AS id_depar_jefe "	+ 
				"FROM empleado_en_proyecto ep "	+ 
				"INNER JOIN empleados e ON e.id_empl = ep.id_empl " +
				"INNER JOIN perfiles p ON p.id_perfi = e.id_perfi " +
				"INNER JOIN departamentos d ON d.id_depar = e.id_depar " +
				"INNER JOIN proyectos pr ON pr.id_proyecto = ep.id_proyecto " +
				"INNER JOIN clientes c ON c.cif = pr.cif " +
				"INNER JOIN empleados j ON j.id_empl = pr.jefe_proyecto " +
				"WHERE numero_orden = " + orden
			);
			if(rs.next()) {
				resultado = new EmpleadoEnProyecto(
					rs.getInt("numero_orden"),
					rs.getString("id_proyecto"),
					rs.getInt("id_empl"),
					rs.getInt("horas_asignadas"),
					rs.getDate("fecha_incorporacion")
				);
				Empleado empleado = new Empleado(
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
				empleado.setPerfil(new Perfil(
					rs.getInt("id_perfi"),
					rs.getString("nombre_perfil"),
					rs.getFloat("precio_hora")
				));
				empleado.setDepartamento(new Departamento(
					rs.getInt("id_depar"),
					rs.getString("nombre_depar"),
					rs.getString("direccion")
				));
				Proyecto proyecto = new Proyecto(
					rs.getString("id_proyecto"),
					rs.getString("descripcion"),
					rs.getDate("fecha_inicio"),
					rs.getDate("fecha_fin_previsto"),
					rs.getDate("fecha_fin_real"),
					rs.getFloat("venta_prevista"),
					rs.getFloat("costes_previsto"),
					rs.getFloat("coste_real"),
					rs.getString("estado"),
					rs.getInt("jefe_proyecto"),
					rs.getString("cif")
				);
				proyecto.setCliente(new Cliente(
					rs.getString("cif"),
					rs.getString("nombre_cliente"),
					rs.getString("apellidos_cliente"),
					rs.getString("domicilio"),
					rs.getFloat("facturacion_anual"),
					rs.getInt("numero_empleados")
				));
				if(proyecto.getJefeProyecto() != 0) {
					proyecto.setJefe(new Empleado(
						rs.getInt("id_empl_jefe"),
						rs.getString("nombre_jefe"),
						rs.getString("apellidos_jefe"),
						rs.getString("email_jefe"),
						rs.getString("password_jefe"),
						rs.getFloat("salario_jefe"),
						rs.getFloat("comision_jefe"),
						rs.getDate("fecha_ingreso_jefe"),
						rs.getDate("fecha_nacimiento_jefe"),
						rs.getString("genero_jefe").charAt(0),
						rs.getInt("id_perfi_jefe"),
						rs.getInt("id_depar_jefe")
					));
				}
				resultado.setEmpleado(empleado);
				resultado.setProyecto(proyecto);
			}
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
			
		this.closeConnection(connection);
		
		return resultado;
	}

	@Override
	public List<EmpleadoEnProyecto> empleadosByProyecto(String codigoProyecto) {
		List<EmpleadoEnProyecto> resultado = new ArrayList<EmpleadoEnProyecto>();
		Connection connection = this.getConnection();
		
		try {
			ResultSet rs = connection.createStatement().executeQuery(
				"SELECT * "	+ 
				"FROM empleado_en_proyecto " + 
				"WHERE id_proyecto = '" + codigoProyecto + "'"
			);
			while(rs.next()) {
				resultado.add(new EmpleadoEnProyecto(
					rs.getInt("numero_orden"),
					rs.getString("id_proyecto"),
					rs.getInt("id_empl"),
					rs.getInt("horas_asignadas"),
					rs.getDate("fecha_incorporacion")
				));
			}
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
			
		this.closeConnection(connection);

		return resultado;
	}

	@Override
	public int asignarEmpleadosAProyecto(List<EmpleadoEnProyecto> empleados) {
		int empleadosAsignados = 0;
		for(EmpleadoEnProyecto empleado:empleados) {
			this.create(empleado);
			empleadosAsignados++;
		}
		return empleadosAsignados;
	}

	@Override
	public int horasAsignadasAProyecto(String codigoProyecto) {
		int resultado = 0;
		Connection connection = this.getConnection();
		
		try {
			ResultSet rs = connection.createStatement().executeQuery(
				"SELECT SUM(horas_asignadas) totalHoras " + 
				"FROM empleado_en_proyecto " + 
				"WHERE id_proyecto = '" + codigoProyecto + "'"
			);
			if(rs.next()) {
				resultado = rs.getInt("totalHoras");
			}
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
			
		this.closeConnection(connection);
		
		return resultado;
	}

	@Override
	public double costeActualDeProyecto(String codigoProyecto) {
		double resultado = 0;
		Connection connection = this.getConnection();
		
		try {
			ResultSet rs = connection.createStatement().executeQuery(
				"SELECT SUM(ep.horas_asignadas*p.precio_hora) costeActual " + 
				"FROM empleado_en_proyecto ep " + 
				"INNER JOIN empleados e ON e.id_empl = ep.id_empl " + 
				"INNER JOIN perfiles p ON p.id_perfi = e.id_perfi " + 
				"WHERE ep.id_proyecto = '" + codigoProyecto + "'"
			);
			if(rs.next()) {
				resultado = rs.getDouble("costeActual");
			}
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
			
		this.closeConnection(connection);
		
		return resultado;
	}

	@Override
	public double margenActualProyecto(String codigoProyecto) {
		double resultado = 0;
		Connection connection = this.getConnection();
		
		try {
			ResultSet rs = connection.createStatement().executeQuery(
				"SELECT pr.venta_prevista-SUM(ep.horas_asignadas*p.precio_hora) margenActual " + 
				"FROM empleado_en_proyecto ep " + 
				"INNER JOIN proyectos pr ON pr.id_proyecto = ep.id_proyecto " + 
				"INNER JOIN empleados e ON e.id_empl = ep.id_empl " + 
				"INNER JOIN perfiles p ON p.id_perfi = e.id_perfi " + 
				"WHERE ep.id_proyecto = '" + codigoProyecto + "'"
			);
			if(rs.next()) {
				resultado = rs.getDouble("margenActual");
			}
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
			
		this.closeConnection(connection);
		
		return resultado;
	}
}

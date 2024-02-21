package daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import conexion.Conector;
import javabeans.Cliente;
import javabeans.Empleado;
import javabeans.Proyecto;

public class ProyectoDaoImpl extends Conector implements IntProyectoDao{

	@Override
	public boolean create(Proyecto record) {
		int responseCommand = 0;
		Connection connection = this.getConnection();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			responseCommand = connection.createStatement().executeUpdate(
				"INSERT INTO proyectos (id_proyecto,descripcion,fecha_inicio,fecha_fin_previsto,fecha_fin_real,venta_prevista,costes_previsto,coste_real,estado,jefe_proyecto,cif)"	+ 
				"VALUES('" + 
					record.getIdProyecto() + "','"+ 
					record.getDescripcion() + "','"+ 
					formatter.format(record.getFechaInicio()) + "','"+ 
					formatter.format(record.getFechaFin()) + "','"+ 
					formatter.format(record.getFechaFinReal()) + "',"+ 
					record.getVentaPrevisto() + ","+ 
					record.getCostesPrevisto() + ","+ 
					record.getCosteReal() + ",'"+ 
					record.getEstado() + "',"+ 
					record.getJefeProyecto() + ",'"+ 
					record.getCif() +
				"')"
			);
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
		
		this.closeConnection(connection);
		
		return responseCommand != 0;
	}

	@Override
	public boolean update(Proyecto record) {
		int responseCommand = 0;
		Connection connection = this.getConnection();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			responseCommand = connection.createStatement().executeUpdate(
				"UPDATE proyectos " +
				"SET descripcion = '"+ record.getDescripcion() +"'," +
					"fecha_inicio = '" + formatter.format(record.getFechaInicio()) + "'," +
					"fecha_fin_previsto = '"+ formatter.format(record.getFechaFin()) +"'," +
					"fecha_fin_real = '"+ formatter.format(record.getFechaFinReal()) +"'," +
					"venta_prevista = "+ record.getVentaPrevisto() +"," +
					"costes_previsto = "+ record.getCostesPrevisto() +"," +
					"coste_real = "+ record.getCosteReal() +"," +
					"estado = '"+ record.getEstado() +"'," +
					"jefe_proyecto = "+ record.getJefeProyecto() +"," +
					"cif = '"+ record.getCif() + "' " +
				"WHERE id_proyecto = '" + record.getIdProyecto() + "'"
			);
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
			
		this.closeConnection(connection);
		
		return responseCommand != 0;
	}

	@Override
	public boolean delete(String id) {
		int responseCommand = 0;
		Connection connection = this.getConnection();
		
		try {
			responseCommand = connection.createStatement().executeUpdate(
				"DELETE FROM proyectos "	+ 
				"WHERE id_proyecto = '" + id + "'"
			);
			
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
			
		this.closeConnection(connection);
		
		return responseCommand != 0;
	}

	@Override
	public List<Proyecto> findAll() {
		List<Proyecto> resultado = new ArrayList<Proyecto>();
		Connection connection = this.getConnection();
		
		try {
			ResultSet rs = connection.createStatement().executeQuery(
				"SELECT * "	+ 
				"FROM proyectos"
			);
			while(rs.next()) {
				resultado.add(new Proyecto(
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
				));
			}
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
			
		this.closeConnection(connection);

		return resultado;
	}

	@Override
	public Proyecto findById(String id) {
		Proyecto resultado = null;
		Connection connection = this.getConnection();
		
		try {
			ResultSet rs = connection.createStatement().executeQuery(
				"SELECT p.id_proyecto,p.descripcion,p.fecha_inicio,p.fecha_fin_previsto,p.fecha_fin_real,p.venta_prevista,p.costes_previsto,p.coste_real,p.estado,p.jefe_proyecto, " + 
					"c.cif,c.nombre AS nombre_cliente,c.apellidos AS apellidos_cliente,c.domicilio,c.facturacion_anual,c.numero_empleados, " + 
					"e.id_empl,e.nombre,e.apellidos,e.email,e.password,e.salario,e.comision,e.fecha_ingreso,e.fecha_nacimiento,e.genero,e.id_perfi,e.id_depar "	+ 
				"FROM proyectos p "	+ 
				"INNER JOIN clientes c ON c.cif = p.cif "	+ 
				"INNER JOIN empleados e ON e.id_empl = p.jefe_proyecto "	+ 
				"WHERE id_proyecto = '" + id + "'"
			);
			if(rs.next()) {
				resultado = new Proyecto(
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
				resultado.setCliente(new Cliente(
					rs.getString("cif"),
					rs.getString("nombre_cliente"),
					rs.getString("apellidos_cliente"),
					rs.getString("domicilio"),
					rs.getFloat("facturacion_anual"),
					rs.getInt("numero_empleados")
				));
				if(resultado.getJefeProyecto() != 0) {
					resultado.setJefe(new Empleado(
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
			}
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
			
		this.closeConnection(connection);
		
		return resultado;
	}

	@Override
	public List<Proyecto> proyectosByEstado(String estado) {
		List<Proyecto> resultado = new ArrayList<Proyecto>();
		Connection connection = this.getConnection();
		
		try {
			ResultSet rs = connection.createStatement().executeQuery(
				"SELECT * "	+ 
				"FROM proyectos " + 
				"WHERE estado = '" + estado + "'"
			);
			while(rs.next()) {
				resultado.add(new Proyecto(
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
				));
			}
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
			
		this.closeConnection(connection);

		return resultado;
	}

	@Override
	public List<Proyecto> proyectosByCliente(String cif) {
		List<Proyecto> resultado = new ArrayList<Proyecto>();
		Connection connection = this.getConnection();
		
		try {
			ResultSet rs = connection.createStatement().executeQuery(
				"SELECT * "	+ 
				"FROM proyectos " + 
				"WHERE cif = '" + cif + "'"
			);
			while(rs.next()) {
				resultado.add(new Proyecto(
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
				));
			}
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
			
		this.closeConnection(connection);

		return resultado;
	}

	@Override
	public List<Proyecto> proyectosByJefeProyectoAndByEstado(int jefeProyecto, String estado) {
		List<Proyecto> resultado = new ArrayList<Proyecto>();
		Connection connection = this.getConnection();
		
		try {
			ResultSet rs = connection.createStatement().executeQuery(
				"SELECT * "	+ 
				"FROM proyectos " + 
				"WHERE jefe_proyecto = " + jefeProyecto +
				 " AND estado = '" + estado + "'"
			);
			while(rs.next()) {
				resultado.add(new Proyecto(
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
				));
			}
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
			
		this.closeConnection(connection);

		return resultado;
	}

	@Override
	public double importesVentaProyectosTerminados() {
		double resultado = 0;
		Connection connection = this.getConnection();
		
		try {
			ResultSet rs = connection.createStatement().executeQuery(
				"SELECT SUM(venta_prevista) totalVenta " + 
				"FROM proyectos " + 
				"WHERE fecha_fin_real < curdate()"
			);
			if(rs.next()) {
				resultado = rs.getDouble("totalVenta");
			}
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
			
		this.closeConnection(connection);
		
		return resultado;
	}

	@Override
	public double margenBrutoProyectosTerminados() {
		double resultado = 0;
		Connection connection = this.getConnection();
		
		try {
			ResultSet rs = connection.createStatement().executeQuery(
				"SELECT SUM(venta_prevista)-SUM(coste_real) margenBruto " + 
				"FROM proyectos " + 
				"WHERE fecha_fin_real < curdate()"
			);
			if(rs.next()) {
				resultado = rs.getDouble("margenBruto");
			}
		} catch (SQLException e) {
            throw new RuntimeException(e);
		}
			
		this.closeConnection(connection);
		
		return resultado;
	}

	@Override
	public int diasATerminoProyectoActivo(String codigoProyecto) {
		Proyecto proyecto = this.findById(codigoProyecto);
		return proyecto.diasATermino();
	}
}

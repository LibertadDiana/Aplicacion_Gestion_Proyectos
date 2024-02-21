package testing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import daos.ClienteDaoImpl;
import daos.DepartamentoDaoImpl;
import daos.EmpleadoDaoImpl;
import daos.EmpleadoEnProyectoDaoImpl;
import daos.IntClienteDao;
import daos.IntDepartamentoDao;
import daos.IntEmpleadoDao;
import daos.IntEmpleadoEnProyectoDao;
import daos.IntPerfilDao;
import daos.IntProyectoDao;
import daos.PerfilDaoImpl;
import daos.ProyectoDaoImpl;
import javabeans.Cliente;
import javabeans.Departamento;
import javabeans.Empleado;
import javabeans.EmpleadoEnProyecto;
import javabeans.Perfil;
import javabeans.Proyecto;

public class PruebaEmpleadoEnProyecto {
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		IntEmpleadoDao servicioEmpleado = new EmpleadoDaoImpl();
		IntClienteDao servicioCliente = new ClienteDaoImpl();
		IntProyectoDao servicioProyecto = new ProyectoDaoImpl();
		IntPerfilDao servicioPerfil = new PerfilDaoImpl();
		IntDepartamentoDao servicioDpto = new DepartamentoDaoImpl();
		IntEmpleadoEnProyectoDao servicioEmpProy = new EmpleadoEnProyectoDaoImpl();

		// Carga de dependencias
		Cliente cliente = new Cliente("A-00000001", "Juan", "Pozo", "", 30000, 27);
		Cliente cliente2 = new Cliente("A-00000002", "Diana", "Gallego", null, 0, 0);
		servicioCliente.create(cliente);
		servicioCliente.create(cliente2);
		
		Perfil perfil = new Perfil(1, "Manager", 25);
		Perfil perfil2 = new Perfil(2, null, 10);
		servicioPerfil.create(perfil);
		servicioPerfil.create(perfil2);
		
		Departamento dpto = new Departamento(1, "RRHH", "Calle cortada N32");
		Departamento dpto2 = new Departamento(2, "I+D", null);
		servicioDpto.create(dpto);
		servicioDpto.create(dpto2);
		
		Empleado empleado = new Empleado(1, "Marcos", "García", " mgarcia@gmail.com ", "1MG", 30000, 12, formatter.parse("12/03/2000"), formatter.parse("10/02/1980"), 'H', 1, 1);
		Empleado empleado2 = new Empleado(2, "Mar", "Gallego"," mgallego@gmail.com ", "2MG", 40000, 15, formatter.parse("12/03/2000"), formatter.parse("27/04/1988"), 'M', 1, 1);
		Empleado empleado3 = new Empleado(3, "Zoltán", "Kodaly", " zkodaly@gmail.com ", "3ZK", 50000, 20, formatter.parse("12/03/2000"), formatter.parse("07/06/1962"),'H', 2, 2);
		servicioEmpleado.create(empleado);
		servicioEmpleado.create(empleado2);
		servicioEmpleado.create(empleado3);
		
		Proyecto proyecto = new Proyecto("P1", "Pruebas", formatter.parse("01/01/2023"), formatter.parse("01/02/2023"), formatter.parse("21/01/2023"), 30000, 20000, 40000, "Terminado", 1, "A-00000001");
		Proyecto proyecto2 = new Proyecto("P2", "Creación", formatter.parse("01/01/2023"),formatter.parse("01/03/2023"), formatter.parse("02/03/2023"), 40000, 30000, 40000, "Iniciado", 1, "A-00000001");
		servicioProyecto.create(proyecto);
		servicioProyecto.create(proyecto2);

		// Carga de elementos para hacer pruebas
		EmpleadoEnProyecto record1 = new EmpleadoEnProyecto(1,"P1",1,200,formatter.parse("11/01/2023"));
		EmpleadoEnProyecto record2 = new EmpleadoEnProyecto(2,"P1",2,250,formatter.parse("15/01/2023"));
		EmpleadoEnProyecto record3 = new EmpleadoEnProyecto(3,"P2",1,300,formatter.parse("11/02/2023"));
		EmpleadoEnProyecto record4 = new EmpleadoEnProyecto(4,"P2",2,350,formatter.parse("11/02/2023"));
		
		System.out.println("Crear distintos casos:");
		servicioEmpProy.create(record1);
		servicioEmpProy.create(record2);
		servicioEmpProy.create(record3);
		servicioEmpProy.create(record4);
		System.out.println();
		
		System.out.println("Consultar uno:");
		EmpleadoEnProyecto recordPrueba = servicioEmpProy.findById(2);
		System.out.println(">>> " + recordPrueba);
		System.out.println(">>> costeHorasAsignadas()=" + recordPrueba.costeHorasAsignadas());
		System.out.println();

		System.out.println("Modificar uno:");
		System.out.println("<<< " + recordPrueba);
		recordPrueba.setIdProyecto("P2");
		recordPrueba.setIdEmpl(2);
		recordPrueba.setHorasAsignadas(100);
		recordPrueba.setFechaIncorporacion(formatter.parse("20/02/2023"));
		System.out.println(">>> " + recordPrueba);
		servicioEmpProy.update(recordPrueba);
		System.out.println();
		
		System.out.println("Consultar todo:");
		for(EmpleadoEnProyecto record: servicioEmpProy.findAll()) {
			System.out.println(">>> " + record);
		}
		System.out.println();

		System.out.println("Consultar por proyecto \"P2\":");
		for(EmpleadoEnProyecto record: servicioEmpProy.empleadosByProyecto("P2")) {
			System.out.println(">>> " + record);
		}
		System.out.println();

		System.out.println("Añadir empleado 3 a proyectos P1 y P2:");
		List<EmpleadoEnProyecto> nuevos = new ArrayList<EmpleadoEnProyecto>();
		nuevos.add(new EmpleadoEnProyecto(5,"P1",3,100,formatter.parse("03/02/2023")));
		nuevos.add(new EmpleadoEnProyecto(6,"P2",3,100,formatter.parse("13/02/2023")));
		System.out.println(">>> Cantidad registros añadidos: " + servicioEmpProy.asignarEmpleadosAProyecto(nuevos));
		for(EmpleadoEnProyecto record: servicioEmpProy.findAll()) {
			System.out.println(">>> " + record);
		}
		System.out.println();
		
		System.out.println("Consultar horas asignadas a proyecto \"P2\":");
		System.out.println(">>> " + servicioEmpProy.horasAsignadasAProyecto("P2"));
		System.out.println();
		
		System.out.println("Consultar coste actual proyecto \"P2\":");
		System.out.println(">>> " + servicioEmpProy.costeActualDeProyecto("P2"));
		System.out.println();
		
		System.out.println("Consultar margen actual proyecto \"P2\":");
		System.out.println(">>> " + servicioEmpProy.margenActualProyecto("P2"));
		System.out.println();
		
		System.out.println("Eliminar todo:");
		servicioEmpProy.delete(1);
		servicioEmpProy.delete(2);
		servicioEmpProy.delete(3);
		servicioEmpProy.delete(4);
		servicioEmpProy.delete(5);
		servicioEmpProy.delete(6);

		// Limpiar dependencias
		servicioProyecto.delete("P1");
		servicioProyecto.delete("P2");
		servicioEmpleado.delete(1);
		servicioEmpleado.delete(2);
		servicioEmpleado.delete(3);
		servicioPerfil.delete(1);
		servicioPerfil.delete(2);
		servicioDpto.delete(1);
		servicioDpto.delete(2);
		servicioCliente.delete("A-00000001");
		servicioCliente.delete("A-00000002");
	}
}

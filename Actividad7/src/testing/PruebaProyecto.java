package testing;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import daos.ClienteDaoImpl;
import daos.DepartamentoDaoImpl;
import daos.EmpleadoDaoImpl;
import daos.IntClienteDao;
import daos.IntDepartamentoDao;
import daos.IntEmpleadoDao;
import daos.IntPerfilDao;
import daos.IntProyectoDao;
import daos.PerfilDaoImpl;
import daos.ProyectoDaoImpl;
import javabeans.Cliente;
import javabeans.Departamento;
import javabeans.Empleado;
import javabeans.Perfil;
import javabeans.Proyecto;

public class PruebaProyecto {
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		IntEmpleadoDao servicioEmpleado = new EmpleadoDaoImpl();
		IntClienteDao servicioCliente = new ClienteDaoImpl();
		IntProyectoDao servicioProyecto = new ProyectoDaoImpl();
		IntPerfilDao servicioPerfil = new PerfilDaoImpl();
		IntDepartamentoDao servicioDpto = new DepartamentoDaoImpl();

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
		servicioEmpleado.create(empleado);
		servicioEmpleado.create(empleado2);
		

		// Carga de elementos para hacer pruebas
		Proyecto record1 = new Proyecto("P1", "Pruebas", formatter.parse("01/01/2023"), formatter.parse("01/02/2023"), formatter.parse("21/01/2023"), 30000, 20000, 40000, "Terminado", 1, "A-00000001");
		Proyecto record2 = new Proyecto("P2", "Creación", formatter.parse("01/01/2023"),formatter.parse("01/03/2023"), formatter.parse("02/03/2023"), 40000, 30000, 40000, "Iniciado", 1, "A-00000001");
		Proyecto record3 = new Proyecto("P3", "Implementación", formatter.parse("01/01/2023"), formatter.parse("01/04/2023"), formatter.parse("01/04/2023"), 50000, 30000, 40000, "Iniciado",1, "A-00000001");
		Proyecto record4 = new Proyecto("P4", "Análisis", formatter.parse("01/01/2023"), formatter.parse("01/05/2023"), formatter.parse("01/06/2023"), 60000, 60000,  40000, "Planificado", 1, "A-00000001");
		
		System.out.println("Crear distintos casos:");
		servicioProyecto.create(record1);
		servicioProyecto.create(record2);
		servicioProyecto.create(record3);
		servicioProyecto.create(record4);
		System.out.println();

		// Los métodos de clase, al ser de tipo consulta, se han incluido en "toString"
		// por lo que se pueden comprobar con la visualización de los registros mostrados
		System.out.println("Consultar uno:");
		Proyecto recordPrueba = servicioProyecto.findById("P2");
		System.out.println(">>> " + recordPrueba);
		System.out.println();

		System.out.println("Modificar uno:");
		System.out.println("<<< " + recordPrueba);
		recordPrueba.setDescripcion("Gestión Hotel");
		recordPrueba.setFechaInicio(formatter.parse("01/03/2023"));
		recordPrueba.setFechaFin(formatter.parse("01/04/2023"));
		recordPrueba.setFechaFinReal(formatter.parse("01/05/2023"));
		recordPrueba.setVentaPrevisto(80000);
		recordPrueba.setCostesPrevisto(60000);
		recordPrueba.setCosteReal(65000);
		recordPrueba.setEstado("Planificada");
		recordPrueba.setJefeProyecto(2);
		recordPrueba.setCif("A-00000002");
		System.out.println(">>> " + recordPrueba);
		servicioProyecto.update(recordPrueba);
		System.out.println();
		
		System.out.println("Consultar todo:");
		for(Proyecto record: servicioProyecto.findAll()) {
			System.out.println(">>> " + record);
		}
		System.out.println();

		System.out.println("Consultar por cliente \"A-00000001\":");
		for(Proyecto record: servicioProyecto.proyectosByCliente("A-00000001")) {
			System.out.println(">>> " + record);
		}
		System.out.println();

		System.out.println("Consultar por estado \"Planificado\":");
		for(Proyecto record: servicioProyecto.proyectosByEstado("Planificado")) {
			System.out.println(">>> " + record);
		}
		System.out.println();

		System.out.println("Consultar por jefe \"1\" y estado \"Planificado\":");
		for(Proyecto record: servicioProyecto.proyectosByJefeProyectoAndByEstado(1, "Planificado")) {
			System.out.println(">>> " + record);
		}
		System.out.println();

		System.out.println("Consultar margen proyectos terminados:");
		System.out.println(">>> " + servicioProyecto.margenBrutoProyectosTerminados());
		System.out.println();
		
		System.out.println("Consultar días a término proyecto \"P3\":");
		System.out.println(">>> " + servicioProyecto.diasATerminoProyectoActivo("P3"));
		System.out.println();
		
		System.out.println("Eliminar todo:");
		servicioProyecto.delete("P1");
		servicioProyecto.delete("P2");
		servicioProyecto.delete("P3");
		servicioProyecto.delete("P4");

		// Limpiar dependencias
		servicioEmpleado.delete(1);
		servicioEmpleado.delete(2);
		servicioPerfil.delete(1);
		servicioPerfil.delete(2);
		servicioDpto.delete(1);
		servicioDpto.delete(2);
		servicioCliente.delete("A-00000001");
		servicioCliente.delete("A-00000002");
	}
}

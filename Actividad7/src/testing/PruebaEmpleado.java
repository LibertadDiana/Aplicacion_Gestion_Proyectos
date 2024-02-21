package testing;

import daos.IntEmpleadoDao;
import daos.IntPerfilDao;
import daos.PerfilDaoImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import daos.DepartamentoDaoImpl;
import daos.EmpleadoDaoImpl;
import daos.IntDepartamentoDao;
import javabeans.Departamento;
import javabeans.Empleado;
import javabeans.Perfil;

public class PruebaEmpleado {
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		IntEmpleadoDao servicio = new EmpleadoDaoImpl();
		IntPerfilDao servicioPerfil = new PerfilDaoImpl();
		IntDepartamentoDao servicioDpto = new DepartamentoDaoImpl();

		// Carga de dependencias
		Perfil perfil = new Perfil(1, "Manager", 25);
		Perfil perfil2 = new Perfil(2, null, 10);
		servicioPerfil.create(perfil);
		servicioPerfil.create(perfil2);
		
		Departamento dpto = new Departamento(1, "RRHH", "Calle cortada N32");
		Departamento dpto2 = new Departamento(2, "I+D", null);
		servicioDpto.create(dpto);
		servicioDpto.create(dpto2);
		

		// Carga de elementos para hacer pruebas
		Empleado record1 = new Empleado(1, "Marcos", "García", " mgarcia@gmail.com ", "1MG", 30000, 12, formatter.parse("12/03/2000"), formatter.parse("10/02/1980"), 'H', 1, 1);
		Empleado record2 = new Empleado(2, "Mar", "Gallego"," mgallego@gmail.com ", "2MG", 40000, 15, formatter.parse("12/03/2000"), formatter.parse("27/04/1988"), 'M', 1, 1);
		Empleado record3 = new Empleado(3, "Zoltán", "Kodaly", " zkodaly@gmail.com ", "3ZK", 50000, 20, formatter.parse("12/03/2000"), formatter.parse("07/06/1962"),'H', 1,1 );
		Empleado record4 = new Empleado(4, "Rita", "Fernández", " rfernandez@gmail.com ", "4RF", 60000, 25,  formatter.parse("12/03/2000"), formatter.parse("10/07/1975"), 'M', 1,1);
		
		System.out.println("Crear distintos casos:");
		servicio.create(record1);
		servicio.create(record2);
		servicio.create(record3);
		servicio.create(record4);
		System.out.println();
		
		// Los métodos de clase, al ser de tipo consulta, se han incluido en "toString"
		// por lo que se pueden comprobar con la visualización de los registros mostrados
		System.out.println("Consultar uno:");
		Empleado recordPrueba = servicio.findById(1);
		System.out.println(">>> " + recordPrueba);
		System.out.println();

		System.out.println("Modificar uno:");
		System.out.println("<<< " + recordPrueba);
		recordPrueba.setNombre("Maria");
		recordPrueba.setApellidos("Vázquez");
		recordPrueba.setEmail("mvazquez@gmail.com");
		recordPrueba.setPassword("1MV");
		recordPrueba.setSalario(50000);
		recordPrueba.setComision(4);
		recordPrueba.setFechaIngreso(new Date(13/03/2000));
		recordPrueba.setFechaNacimiento(new Date(13/03/2000));
		recordPrueba.setGenero('M');
		recordPrueba.setIdPerfi(2);
		recordPrueba.setIdDepar(2);
		System.out.println(">>> " + recordPrueba);
		servicio.update(recordPrueba);
		System.out.println();
		
		System.out.println("Consultar todo:");
		for(Empleado record: servicio.findAll()) {
			System.out.println(">>> " + record);
		}
		System.out.println();

		System.out.println("Consultar por apellido \"al\":");
		for(Empleado record: servicio.empleadosByApellido("al")) {
			System.out.println(">>> " + record);
		}
		System.out.println();

		System.out.println("Consultar por departamento \"1\":");
		for(Empleado record: servicio.empleadosByDepartamento(1)) {
			System.out.println(">>> " + record);
		}
		System.out.println();

		System.out.println("Consultar por departamento \"M\":");
		for(Empleado record: servicio.empleadosBySexo('M')) {
			System.out.println(">>> " + record);
		}
		System.out.println();

		System.out.println("Consultar salario total por departamento \"1\":");
		System.out.println(">>> " + servicio.salarioTotal(1));
		System.out.println();
		
		System.out.println("Consultar salario total:");
		System.out.println(">>> " + servicio.salarioTotal());
		System.out.println();
		
		System.out.println("Eliminar todo:");
		servicio.delete(1);
		servicio.delete(2);
		servicio.delete(3);
		servicio.delete(4);

		// Limpiar dependencias
		servicioPerfil.delete(1);
		servicioPerfil.delete(2);
		servicioDpto.delete(1);
		servicioDpto.delete(2);
	}
}
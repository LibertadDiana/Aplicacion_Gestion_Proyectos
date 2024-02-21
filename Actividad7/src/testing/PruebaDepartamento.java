package testing;

import daos.DepartamentoDaoImpl;
import daos.IntDepartamentoDao;
import javabeans.Departamento;

public class PruebaDepartamento {

	public static void main(String[] args) {
		// Carga de elementos para hacer pruebas
		Departamento record1 = new Departamento(1, "RRHH", "Calle cortada N32");
		Departamento record2 = new Departamento(2, "I+D", null);
		Departamento record3 = new Departamento(3, "Logística", "Plaza Cuadrada");
		Departamento record4 = new Departamento(4, "Desarrollo", null);
		IntDepartamentoDao servicio = new DepartamentoDaoImpl();

		System.out.println("Crear distintos casos:");
		servicio.create(record1);
		servicio.create(record2);
		servicio.create(record3);
		servicio.create(record4);
		System.out.println();

		System.out.println("Consultar uno:");
		Departamento recordPrueba = servicio.findById(2);
		System.out.println(">>> " + recordPrueba);
		System.out.println();

		System.out.println("Modificar uno:");
		System.out.println("<<< " + recordPrueba);
		recordPrueba.setNombre("Planificación");
		recordPrueba.setDireccion("Avenida Gorgona");
		System.out.println(">>> " + recordPrueba);
		servicio.update(recordPrueba);
		System.out.println();
		
		System.out.println("Consultar todo:");
		for(Departamento record: servicio.findAll()) {
			System.out.println(">>> " + record);
		}
		System.out.println();

		System.out.println("Eliminar todo:");
		servicio.delete(1);
		servicio.delete(2);
		servicio.delete(3);
		servicio.delete(4);
	}
}

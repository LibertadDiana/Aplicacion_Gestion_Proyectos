package testing;

import daos.ClienteDaoImpl;
import daos.IntClienteDao;
import javabeans.Cliente;

public class PruebaCliente {

	public static void main(String[] args) {
		// Carga de elementos para hacer pruebas
		Cliente record1 = new Cliente("A-00000001", "Juan", "Pozo", "", 30000, 27);
		Cliente record2 = new Cliente("A-00000002", "Diana", "Gallego", null, 0, 0);
		Cliente record3 = new Cliente("A-00000003", "Fernando", "Fuentes", "Polígono El Collado", 23000, 3);
		Cliente record4 = new Cliente("A-00000004", "Blanca", "Alcántara", "Avenida Gran Vía", 1000000, 45);
		IntClienteDao servicio = new ClienteDaoImpl();

		System.out.println("Crear distintos casos:");
		servicio.create(record1);
		servicio.create(record2);
		servicio.create(record3);
		servicio.create(record4);
		System.out.println();

		System.out.println("Consultar uno:");
		Cliente recordPrueba = servicio.findById("A-00000003");
		System.out.println(">>> " + recordPrueba);
		System.out.println();

		System.out.println("Modificar uno:");
		System.out.println("<<< " + recordPrueba);
		recordPrueba.setNombre("Lucas");
		recordPrueba.setApellidos("Espinosa");
		recordPrueba.setDomicilio("Avenida Gorgona");
		recordPrueba.setFacturacionAnual(33);
		recordPrueba.setNumeroEmpleados(1);
		System.out.println(">>> " + recordPrueba);
		servicio.update(recordPrueba);
		System.out.println();
		
		System.out.println("Consultar todo:");
		for(Cliente record: servicio.findAll()) {
			System.out.println(">>> " + record);
		}
		System.out.println();

		System.out.println("Eliminar todo:");
		servicio.delete("A-00000001");
		servicio.delete("A-00000002");
		servicio.delete("A-00000003");
		servicio.delete("A-00000004");
	}
}

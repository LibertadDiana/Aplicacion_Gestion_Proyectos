package principales;

import java.util.List;
import java.util.Scanner;

import daos.IntClienteDao;
import daos.ClienteDaoImpl;
import javabeans.Cliente;

public class GestionClientes {

	public static void main(String[] args) {
		IntClienteDao servicioCliente = new ClienteDaoImpl();
		try (Scanner sn = new Scanner(System.in)) {

			String option;
			boolean salir = false;
			while(!salir) {
				System.out.println("Escoge una opción.");
				System.out.println("1. Alta del Cliente");
				System.out.println("2. Buscar un Cliente");
				System.out.println("3. Mostrar Todos.");
				System.out.println("4. Eliminar un cliente");
				System.out.println("5. Salir");
				
				// Se opta por leer caracteres por si el usuario introduce algo que no sea un número.
				option = sn.nextLine();
				
				while(option.isEmpty() || option.isBlank()) {
					option = sn.nextLine();
				}
				
				if(option.length() == 1) {
					switch(option.charAt(0)) {
					case '1':
						altaCliente(servicioCliente, sn);
						break;
					case '2':
						mostrarCliente(servicioCliente, sn);
						break;
					case '3':
						mostrarTodos(servicioCliente);
						break;
					case '4':
						bajaCliente(servicioCliente, sn);
						break;
					case '5':
						salir = true;
						break;
					default:
						System.out.println("Escriba números entre 1 y 5");
					}
				}else {
					System.out.println("Es necesario que escriba un único número");
				}
			}
		}
	}

	private static void altaCliente(IntClienteDao servicioCliente, Scanner sn) {
		Cliente nuevoCliente = new Cliente();
		System.out.println("Introduzca su CIF siguiendo el ejemplo: X-XXXXXXXX.");
		nuevoCliente.setCif(sn.nextLine());
		System.out.println("Introduzca su nombre.");
		nuevoCliente.setNombre(sn.nextLine());
		System.out.println("Introduzca sus apellidos");
		nuevoCliente.setApellidos(sn.nextLine());
		System.out.println("Introduzca su domicilio.");
		nuevoCliente.setDomicilio(sn.nextLine());
		System.out.println("Introduzca su facturación anual.");
		nuevoCliente.setFacturacionAnual(sn.nextFloat());
		System.out.println("Introduzca su número de empleados.");
		nuevoCliente.setNumeroEmpleados(sn.nextInt());

		if(servicioCliente.findById(nuevoCliente.getCif()) != null) {
			System.out.println("El CIF indicado ya existe.");
		}else {
			if(servicioCliente.create(nuevoCliente)) {
				System.out.println("Cliente registrado.");
			}else {
				System.out.println("No se ha registrado el cliente.");
			}
		}
		
		System.out.println();
	}
	private static void mostrarCliente(IntClienteDao servicioCliente, Scanner sn) {
		System.out.println("Introduzca el CIF del cliente.");
		String cif = sn.next();
		
		Cliente clienteBuscado = servicioCliente.findById(cif);
		if(clienteBuscado != null) {
			System.out.println(">>>" + clienteBuscado.toString());
		}else {
			System.out.println("No se ha encontrado el cliente.");
		}
		System.out.println();
	}
	private static void mostrarTodos(IntClienteDao servicioCliente) {
		List<Cliente> clientes = servicioCliente.findAll();
		for(Cliente cliente:clientes) {
			System.out.println(">>>" + cliente.toString());
		}
		System.out.println();
	}
	private static void bajaCliente(IntClienteDao servicioCliente, Scanner sn) {
		System.out.println("Introduzca el CIF del cliente.");
		String cif = sn.next();
		
		if(servicioCliente.delete(cif)) {
			System.out.println("Cliente eliminado.");
		}else {
			System.out.println("No se ha encontrado el cliente.");
		}
		System.out.println();
	}
}

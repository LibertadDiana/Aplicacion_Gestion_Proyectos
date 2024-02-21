package testing;

import daos.IntPerfilDao;
import daos.PerfilDaoImpl;
import javabeans.Perfil;

public class PruebaPerfil {

	public static void main(String[] args) {
		// Carga de elementos para hacer pruebas
		Perfil record1 = new Perfil(1, "Manager", 25);
		Perfil record2 = new Perfil(2, null, 10);
		Perfil record3 = new Perfil(3, "Analista", 30);
		Perfil record4 = new Perfil(4, "Programador", 20);
		IntPerfilDao servicio = new PerfilDaoImpl();

		System.out.println("Crear distintos casos:");
		servicio.create(record1);
		servicio.create(record2);
		servicio.create(record3);
		servicio.create(record4);
		System.out.println();

		System.out.println("Consultar uno:");
		Perfil recordPrueba = servicio.findById(3);
		System.out.println(">>> " + recordPrueba);
		System.out.println();

		System.out.println("Modificar uno:");
		System.out.println("<<< " + recordPrueba);
		recordPrueba.setNombre("Gestor");
		recordPrueba.setPrecioHora(7);
		System.out.println(">>> " + recordPrueba);
		servicio.update(recordPrueba);
		System.out.println();
		
		System.out.println("Consultar todo:");
		for(Perfil record: servicio.findAll()) {
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

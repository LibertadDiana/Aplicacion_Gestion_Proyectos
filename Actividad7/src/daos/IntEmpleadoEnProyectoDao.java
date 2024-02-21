package daos;

import java.util.List;

import javabeans.EmpleadoEnProyecto;

public interface IntEmpleadoEnProyectoDao {
	 boolean create(EmpleadoEnProyecto record);
	 boolean update(EmpleadoEnProyecto record);
	 boolean delete(int orden);
	 List<EmpleadoEnProyecto> findAll();
	 EmpleadoEnProyecto findById(int orden);
	 List<EmpleadoEnProyecto> empleadosByProyecto(String codigoProyecto);
	 int asignarEmpleadosAProyecto(List<EmpleadoEnProyecto> empleados);
	 int horasAsignadasAProyecto(String codigoProyecto);
	 double costeActualDeProyecto(String codigoProyecto);
	 double margenActualProyecto(String codigoProyecto);
}

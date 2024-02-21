package daos;

import java.util.List;

import javabeans.Empleado;

public interface IntEmpleadoDao {
	 boolean create(Empleado record);
	 boolean update(Empleado record);
	 boolean delete(int id);
	 List<Empleado> findAll();
	 Empleado findById(int id);
	 List<Empleado> empleadosByDepartamento(int idDepar);
	 List<Empleado> empleadosBySexo(char sexo);
	 List<Empleado> empleadosByApellido(String subcadena);
	 double salarioTotal();
	 double salarioTotal(int idDepar);
}

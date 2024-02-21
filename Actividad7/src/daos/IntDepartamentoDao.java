package daos;

import java.util.List;

import javabeans.Departamento;

public interface IntDepartamentoDao {
	 boolean create(Departamento record);
	 boolean update(Departamento record);
	 boolean delete(int id);
	 List<Departamento> findAll();
	 Departamento findById(int id);
}

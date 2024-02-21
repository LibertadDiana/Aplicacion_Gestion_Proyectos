package daos;

import java.util.List;

import javabeans.Cliente;

public interface IntClienteDao {
	 boolean create(Cliente record);
	 boolean update(Cliente record);
	 boolean delete(String cif);
	 List<Cliente> findAll();
     Cliente findById(String cif);
}

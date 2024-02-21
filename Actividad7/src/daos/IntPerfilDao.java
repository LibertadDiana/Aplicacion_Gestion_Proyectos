package daos;

import java.util.List;

import javabeans.Perfil;

public interface IntPerfilDao {
	 boolean create(Perfil record);
	 boolean update(Perfil record);
	 boolean delete(int id);
	 List<Perfil> findAll();
	 Perfil findById(int id);
}

package daos;

import java.util.List;

import javabeans.Proyecto;

public interface IntProyectoDao {
	 boolean create(Proyecto record);
	 boolean update(Proyecto record);
	 boolean delete(String id);
	 List<Proyecto> findAll();
	 Proyecto findById(String id);
	 List<Proyecto> proyectosByEstado(String estado);
	 List<Proyecto> proyectosByCliente(String cif);
	 List<Proyecto> proyectosByJefeProyectoAndByEstado(int jefeProyecto, String estado);
	 double importesVentaProyectosTerminados();
	 double margenBrutoProyectosTerminados();
	 int diasATerminoProyectoActivo(String codigoProyecto);
}

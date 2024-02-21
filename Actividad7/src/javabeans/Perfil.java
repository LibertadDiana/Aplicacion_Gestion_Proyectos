package javabeans;

public class Perfil {
	private int idPerfi;
	private String nombre;
	private float precioHora;
	
	public Perfil(int idPerfi, String nombre, float precioHora) {
		this.idPerfi = idPerfi;
		this.nombre = nombre;
		this.precioHora = precioHora;
	}
	
	public int getIdPerfi() {
		return idPerfi;
	}
	public void setIdPerfi(int idPerfi) {
		this.idPerfi = idPerfi;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getPrecioHora() {
		return precioHora;
	}
	public void setPrecioHora(float precioHora) {
		this.precioHora = precioHora;
	}

	@Override
	public String toString() {
		return "Perfil [idPerfi=" + idPerfi + ", nombre=" + nombre + ", precioHora=" + precioHora + "]";
	}	
}
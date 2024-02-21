package javabeans;

import java.util.Date;

public class EmpleadoEnProyecto {
	private int numeroOrden;
	private String idProyecto;
	private int idEmpl;
	private int horasAsignadas;
	private Date fechaIncorporacion;
	private Proyecto proyecto;
	private Empleado empleado;
	
	public EmpleadoEnProyecto(int numeroOrden, String idProyecto, int idEmpl, int horasAsignadas,
			Date fechaIncorporacion) {
		this.numeroOrden = numeroOrden;
		this.idProyecto = idProyecto;
		this.idEmpl = idEmpl;
		this.horasAsignadas = horasAsignadas;
		this.fechaIncorporacion = fechaIncorporacion;
	}
	
	public int getNumeroOrden() {
		return numeroOrden;
	}
	public void setNumeroOrden(int numeroOrden) {
		this.numeroOrden = numeroOrden;
	}
	public String getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(String idProyecto) {
		this.idProyecto = idProyecto;
	}
	public int getIdEmpl() {
		return idEmpl;
	}
	public void setIdEmpl(int idEmpl) {
		this.idEmpl = idEmpl;
	}
	public int getHorasAsignadas() {
		return horasAsignadas;
	}
	public void setHorasAsignadas(int horasAsignadas) {
		this.horasAsignadas = horasAsignadas;
	}
	public Date getFechaIncorporacion() {
		return fechaIncorporacion;
	}
	public void setFechaIncorporacion(Date fechaIncorporacion) {
		this.fechaIncorporacion = fechaIncorporacion;
	}
	public Proyecto getProyecto() {
		return proyecto;
	}
	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	
	public double costeHorasAsignadas() {
		return this.horasAsignadas * this.empleado.getPerfil().getPrecioHora();
	}

	@Override
	public String toString() {
		return "EmpleadoEnProyecto [numeroOrden=" + numeroOrden + ", idProyecto=" + idProyecto + ", idEmpl=" + idEmpl
				+ ", horasAsignadas=" + horasAsignadas + ", fechaIncorporacion=" + fechaIncorporacion
				+ "]";
	}
	
}

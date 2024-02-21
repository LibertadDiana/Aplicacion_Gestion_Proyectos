package javabeans;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Proyecto {
	private String idProyecto;
	private String descripcion;
	private Date fechaInicio;
	private Date fechaFin;
	private Date fechaFinReal;
	private float ventaPrevisto;
	private float costesPrevisto;
	private float costeReal;
	private String estado;
	private int jefeProyecto;
	private String cif;
	private Empleado jefe;
	private Cliente cliente;

	public Proyecto(String idProyecto, String descripcion, Date fechaInicio, Date fechaFin, Date fechaFinReal,
			float ventaPrevisto, float costesPrevisto, float costeReal, String estado, int jefeProyecto, String cif) {
		this.idProyecto = idProyecto;
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fechaFinReal = fechaFinReal;
		this.ventaPrevisto = ventaPrevisto;
		this.costesPrevisto = costesPrevisto;
		this.costeReal = costeReal;
		this.estado = estado;
		this.jefeProyecto = jefeProyecto;
		this.cif = cif;
	}
	
	public String getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(String idProyecto) {
		this.idProyecto = idProyecto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Date getFechaFinReal() {
		return fechaFinReal;
	}
	public void setFechaFinReal(Date fechaFinReal) {
		this.fechaFinReal = fechaFinReal;
	}
	public float getVentaPrevisto() {
		return ventaPrevisto;
	}
	public void setVentaPrevisto(float ventaPrevisto) {
		this.ventaPrevisto = ventaPrevisto;
	}
	public float getCostesPrevisto() {
		return costesPrevisto;
	}
	public void setCostesPrevisto(float costesPrevisto) {
		this.costesPrevisto = costesPrevisto;
	}
	public float getCosteReal() {
		return costeReal;
	}
	public void setCosteReal(float costeReal) {
		this.costeReal = costeReal;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getJefeProyecto() {
		return jefeProyecto;
	}
	public void setJefeProyecto(int jefeProyecto) {
		this.jefeProyecto = jefeProyecto;
	}
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public Empleado getJefe() {
		return jefe;
	}
	public void setJefe(Empleado jefe) {
		this.jefe = jefe;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public double margenPrevisto() {
		return this.ventaPrevisto - this.costesPrevisto;
	}
	public double margenReal() {
		return this.ventaPrevisto - this.costeReal;
	}
	public double diferenciaGastos() {
		return this.costeReal - this.costesPrevisto;
	}
	public int diferenciaFinPrevistoReal() {
		long fechaFinMilisegundos = this.fechaFin.getTime();
		long fechaFinRealMilisegundos = this.fechaFinReal.getTime();
		
		long diferenciaMilisegundos = 0;
		if(fechaFinMilisegundos > fechaFinRealMilisegundos)
			diferenciaMilisegundos = fechaFinMilisegundos - fechaFinRealMilisegundos;
		else
			diferenciaMilisegundos = fechaFinRealMilisegundos - fechaFinMilisegundos;

        TimeUnit time = TimeUnit.DAYS; 
        return (int)time.convert(diferenciaMilisegundos, TimeUnit.MILLISECONDS);
	}
	public int diasATermino() {
		long fechaFinMilisegundos = this.fechaFin.getTime();
		long hoyMilisegundos = new Date().getTime();
		
		long diferenciaMilisegundos = 0;
		if(fechaFinMilisegundos > hoyMilisegundos)
			diferenciaMilisegundos = fechaFinMilisegundos - hoyMilisegundos;

        TimeUnit time = TimeUnit.DAYS; 
        return (int)time.convert(diferenciaMilisegundos, TimeUnit.MILLISECONDS);
	}

	@Override
	public String toString() {
		return "Proyecto [idProyecto=" + idProyecto + ", descripcion=" + descripcion + ", fechaInicio=" + fechaInicio
				+ ", fechaFin=" + fechaFin + ", fechaFinReal=" + fechaFinReal + ", ventaPrevisto=" + ventaPrevisto
				+ ", costesPrevisto=" + costesPrevisto + ", costeReal=" + costeReal + ", estado=" + estado
				+ ", jefeProyecto=" + jefeProyecto + ", cif=" + cif + ", margenPrevisto()=" + margenPrevisto()
				+ ", margenReal()=" + margenReal() + ", diferenciaGastos()=" + diferenciaGastos()
				+ ", diferenciaFinPrevistoReal()=" + diferenciaFinPrevistoReal() + ", diasATermino()=" + diasATermino()
				+ "]";
	}
}

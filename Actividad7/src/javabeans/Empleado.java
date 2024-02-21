package javabeans;

import java.util.Date;

public class Empleado {
	private int idEmpl;
	private String nombre;
	private String apellidos;
	private String email;
	private String password;
	private float salario;
	// Se ha añadido "comision" para poder implementar el método "salarioBruto"
	private float comision; 
	private Date fechaIngreso;
	private Date fechaNacimiento;
	// Se ha añadido "genero" para poder implementar el método "literalSexo" en esta clase 
	// y "empleadosBySexo" de "EmpleadoDao"
	private char genero;
	private int idPerfi;
	private int idDepar;
	private Perfil perfil;
	private Departamento departamento;
	
	
	public Empleado(int idEmpl, String nombre, String apellidos, String email, String password, float salario,
			float comision, Date fechaIngreso, Date fechaNacimiento, char genero, int idPerfi, int idDepar) {
		this.idEmpl = idEmpl;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.password = password;
		this.salario = salario;
		this.comision = comision;
		this.fechaIngreso = fechaIngreso;
		this.fechaNacimiento = fechaNacimiento;
		this.genero = genero;
		this.idPerfi = idPerfi;
		this.idDepar = idDepar;
	}
	
	public int getIdEmpl() {
		return idEmpl;
	}
	public void setIdEmpl(int idEmpl) {
		this.idEmpl = idEmpl;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public float getSalario() {
		return salario;
	}
	public void setSalario(float salario) {
		this.salario = salario;
	}
	public float getComision() {
		return comision;
	}
	public void setComision(float comision) {
		this.comision = comision;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public char getGenero() {
		return genero;
	}
	public void setGenero(char genero) {
		this.genero = genero;
	}
	public int getIdPerfi() {
		return idPerfi;
	}
	public void setIdPerfi(int idPerfi) {
		this.idPerfi = idPerfi;
	}
	public int getIdDepar() {
		return idDepar;
	}
	public void setIdDepar(int idDepar) {
		this.idDepar = idDepar;
	}	
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public double salarioBruto(){
		double resultado = salario + comision;
		return resultado;
	}
	public double salarioMensual(int meses) {
		if(meses == 0) {
			return 0;
		}
		double resultado = this.salarioBruto() / meses;
		return resultado;
	}
	public String literalSexo() {
		switch(genero) {
		case 'H', 'h':
			return "Hombre";
		case 'M', 'm':
			return "Mujer";
		}
		String resultado = String.valueOf(genero);
		return resultado;
	}
	public String obtenerEmail() {
		String primeraLetra = String.valueOf(nombre.charAt(0));
		String primerApell = apellidos;
		int inicio = 0;
		int fin = apellidos.indexOf(" ");
		if(fin > 0) {
			primerApell = apellidos.substring(inicio,fin);
		}
		
		String resultado = primeraLetra.toLowerCase() + primerApell.toLowerCase();
		return resultado;
	}
	public String nombreCompleto(){
		return(nombre+ " " + apellidos);
	}

	@Override
	public String toString() {
		return "Empleado [idEmpl=" + idEmpl + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email
				+ ", password=" + password + ", salario=" + salario + ", comision=" + comision + ", fechaIngreso="
				+ fechaIngreso + ", fechaNacimiento=" + fechaNacimiento + ", genero=" + genero + ", idPerfi=" + idPerfi
				+ ", idDepar=" + idDepar + ", salarioBruto()=" + salarioBruto() + ", literalSexo()=" + literalSexo()
				+ ", obtenerEmail()=" + obtenerEmail() + ", nombreCompleto()=" + nombreCompleto() + "]";
	}
}

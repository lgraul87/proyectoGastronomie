package modelo.instalacion;

import modelo.limitdb.LimitsDB;

public class Instalacion implements IInstalacion, LimitsDB {

	private String sNombreInstalacion; // PK
	private String sDescripcion;
	private String sDireccion; // NN
	private float fCuotaMensual; // NN

	public Instalacion(String sNombreInstalacion, String sDireccion, float fCuotaMensual) {
		setsNombreInstalacion(sNombreInstalacion);
		setsDireccion(sDireccion);
		setfCuotaMensual(fCuotaMensual);

	}

	public Instalacion(String sNombreInstalacion, String sDescripcion, String sDireccion, float fCuotaMensual) {
		setsNombreInstalacion(sNombreInstalacion);
		setsDescripcion(sDescripcion);
		setsDireccion(sDireccion);
		setfCuotaMensual(fCuotaMensual);
	}
	
	public Instalacion(String sNombreInstalacion) {
		setsNombreInstalacion(sNombreInstalacion);
	}

	@Override
	public String getsNombreInstalacion() {
		return this.sNombreInstalacion;
	}

	private boolean setsNombreInstalacion(String sNombreInstalacion) { // PK
		boolean bValido = false;
		if (sNombreInstalacion != null && sNombreInstalacion.length() > MIN_CHAR_0
				&& sNombreInstalacion.length() <= MAX_CHAR_30) {
			this.sNombreInstalacion = sNombreInstalacion;
			bValido = true;
		}
		return bValido;
	}

	@Override
	public String getsDescripcion() {
		return this.sDescripcion;
	}

	@Override
	public boolean setsDescripcion(String sDescripcion) {
		boolean bValido = false;
		if (sDescripcion != null && sDescripcion.length() > MIN_CHAR_0 && sDescripcion.length() <= MAX_CHAR_100) {
			this.sDescripcion = sDescripcion;
			bValido = true;
		}
		return bValido;
	}

	@Override
	public String getsDireccion() {
		return this.sDireccion;
	}

	@Override
	public boolean setsDireccion(String sDireccion) {
		boolean bValido = false;
		if (sDireccion != null && sDireccion.length() > MIN_CHAR_0 && sDireccion.length() <= MAX_CHAR_40) {
			this.sDireccion = sDireccion;
			bValido = true;
		}
		return bValido;
	}

	@Override
	public float getfCuotaMensual() {
		return this.fCuotaMensual;
	}

	@Override
	public boolean setfCuotaMensual(float fCuotaMensual) {
		boolean bValido = false;
		if (fCuotaMensual <= MAX_FLOAT_3000 && fCuotaMensual > MIN_FLOAT_0) {
			this.fCuotaMensual = fCuotaMensual;
			bValido = true;
		} else {
			this.fCuotaMensual = -1;
		}
		return bValido;
	}

	@Override
	public boolean checkInstalacion() {
		boolean bValido = false;
		if (this.sNombreInstalacion != null && this.sDireccion != null && this.fCuotaMensual != -1) {
			bValido = true;
		}
		return bValido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sNombreInstalacion == null) ? 0 : sNombreInstalacion.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean bIgual = false;
		Instalacion other = (Instalacion) obj;
		if (this != null && other != null && this.checkInstalacion() && other.checkInstalacion()
				&& this.sNombreInstalacion.equals(other.sNombreInstalacion)) {
			bIgual = true;
		}
		return bIgual;
	}

	@Override
	public String toString() {
		return "Instalacion: " + this.sNombreInstalacion + " ."
		//
				+ "  --Descripcion: " + this.sDescripcion
				//
				+ "  --Direccion: " + this.sDireccion
				//
				+ "  --Cuota Mensual: " + this.fCuotaMensual
				//
				+ "\n-------------------------------------------------------------------------------------\n";
		//
	}

}

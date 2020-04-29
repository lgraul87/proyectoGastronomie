package modelo;

public interface IInstalacion {
	
	

	public String getsNombreInstalacion();

	public String getsDescripcion();

	public boolean setsDescripcion(String sDescripcion);

	public String getsDireccion();

	public boolean setsDireccion(String sDireccion);

	public float getfCuotaMensual();

	public boolean setfCuotaMensual(float fCuotaMensual);

	public boolean checkInstalacion();

	public int hashCode();

	public boolean equals(Object obj);

	public String toString();
}

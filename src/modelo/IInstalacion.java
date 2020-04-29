package modelo;

public interface IInstalacion {
	public final int MAX_CHAR_NOMBRE = 30;
	public final int MAX_CHAR_DESCRIPCION = 100;
	public final int MAX_CHAR_DIRECCION = 40;
	public final float MAX_CUOTA_MENSUAL = 3000;
	public final float MIN_CUOTA_MENSUAL = 0;

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

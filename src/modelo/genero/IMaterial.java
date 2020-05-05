package modelo.genero;

public interface IMaterial {

	public String getsNombreMaterial();

	public float getfPrecio();

	public boolean setfPrecio(float fPrecio);

	public short getShStock();

	public boolean setShStock(short shStock);

	public String getsTipo();

	public boolean setsTipo(String sTipo);

	public boolean checkMaterial();

	public int hashCode();

	public boolean equals(Object obj);

	public String toString();

}

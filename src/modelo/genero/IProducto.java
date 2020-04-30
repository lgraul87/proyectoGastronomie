package modelo.genero;

public interface IProducto {
	
	public String getsNombreProducto();

	public float getfPrecio();

	public boolean setfPrecio(float fPrecio);

	public short getShStock();

	public boolean setShStock(short shStock);

	public boolean checkProducto();

	public int hashCode();

	public boolean equals(Object obj);

	public String toString();

}

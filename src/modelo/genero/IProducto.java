package modelo.genero;

public interface IProducto {

	public final String CARTA_BEBIDA = "Carta_bebida";
	public final String CARTA_COMIDA = "Carta_comida";
	public final String DERIVADOS = "Derivados";

	public String getsNombreProducto();

	public float getfPrecio();

	public boolean setfPrecio(float fPrecio);

	public short getShStock();

	public boolean setShStock(short shStock);

	public String getsTipo();

	public boolean setsTipo(String sTipo);

	public boolean checkProducto();

	public int hashCode();

	public boolean equals(Object obj);

	public String toString();

}

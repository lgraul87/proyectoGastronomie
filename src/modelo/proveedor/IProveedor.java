package modelo.proveedor;

public interface IProveedor {

	public String getsNombreProveedor();

	public int getiTelefono();

	public boolean setiTelefono(int iTelefono);

	public String getsCorreoElectronico();

	public boolean setsCorreoElectronico(String sCorreoElectronico);

	public String getsDireccion();

	public boolean setsDireccion(String sDireccion);

	public TipoProveedor getoTipoProveedor();

	public boolean setoTipoProveedor(TipoProveedor oTipoProveedor);

	public boolean checkProveedor();

	public int hashCode();

	public boolean equals(Object obj);

	public String toString();

}

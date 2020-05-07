package controlador;

import modelo.genero.Producto;

public interface IProductoController {

	public boolean add(Producto oProducto);

	public boolean remove(String sNombre);

	public boolean search(String sNombre);

	public String mostrarProducto(String sNombre);

	public String mostrarProductos();

	public String mostrarCartaBebida();

	public Producto obtenerProducto(String sBebida);

	public boolean update(Producto oProducto, byte bCantidad);

	public String mostrarCartaComida();

}

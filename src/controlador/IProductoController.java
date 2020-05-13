package controlador;

import controlador.database.GeneralController;
import modelo.genero.Producto;

public interface IProductoController {

	public boolean add(Producto oProducto);

	public boolean remove(Producto oProducto);

	public boolean search(Producto oProducto);

	public Producto obtenerProducto(Producto oProducto);

	public boolean update(Producto oProducto, byte bCantidad);

	public String mostrarProducto(Producto oProducto);

	public String mostrarProductos();

	public String mostrarCartaBebida();

	public String mostrarCartaComida();
	
	public int contarExistencias(Producto oProducto, GeneralController controlGeneral);

}

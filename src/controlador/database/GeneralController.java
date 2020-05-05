package controlador.database;

import controlador.InstalacionController;
import controlador.LineaPedidoController;
import controlador.MaterialController;
import controlador.PagoController;
import controlador.PedidoController;
import controlador.ProductoController;
import controlador.ProveedorController;
import controlador.UsuarioController;

public class GeneralController {

	private ConexionDB conexionDB;
	private UsuarioController usuarioController;
	private ProveedorController proveedorController;
	private MaterialController materialController;
	private ProductoController productoController;
	private InstalacionController instalacionController;
	private PagoController pagoController;
	private PedidoController pedidoController;
	private LineaPedidoController lineaPedidoController;

	public GeneralController(String sDatabase) {

		this.conexionDB = new ConexionDB(sDatabase);
		this.usuarioController = new UsuarioController();
		this.proveedorController = new ProveedorController();
		this.materialController = new MaterialController();
		this.productoController = new ProductoController();
		this.instalacionController = new InstalacionController();
		this.pagoController = new PagoController();
		this.pedidoController = new PedidoController();
		this.lineaPedidoController = new LineaPedidoController();
	}

	public UsuarioController getUsuarioController() {
		return this.usuarioController;
	}

	public ProveedorController getProveedorController() {
		return proveedorController;
	}

	public MaterialController getMaterialController() {
		return materialController;
	}

	public ProductoController getProductoController() {
		return this.productoController;
	}

	public InstalacionController getInstalacionController() {
		return instalacionController;
	}

	public ConexionDB getConexionDB() {
		return this.conexionDB;
	}

	public PagoController getPagoController() {
		return pagoController;
	}

	public PedidoController getPedidoController() {
		return pedidoController;
	}

	public LineaPedidoController getLineaPedidoController() {
		return lineaPedidoController;
	}

}

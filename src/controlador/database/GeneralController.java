package controlador.database;

import controlador.LineaPedidoController;
import controlador.PagoController;
import controlador.PedidoController;
import controlador.ProductoController;

public class GeneralController {

	private ConexionDB conexionDB;
	private ProductoController productoController;
	private PagoController pagoController;
	private PedidoController pedidoController;
	private LineaPedidoController lineaPedidoController;

	public GeneralController(String sDatabase) {
		
		this.conexionDB = new ConexionDB(sDatabase);
		this.productoController = new ProductoController();
		this.pagoController = new PagoController();
		this.pedidoController = new PedidoController();
		this.lineaPedidoController = new LineaPedidoController();
	}

	public ProductoController getProductoController() {
		return this.productoController;
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

package controlador.database;

import controlador.ProductoController;

public class GeneralController {
    private ProductoController productoController;
    
    public GeneralController(String sDatabase) {
	
	new ConexionDB(sDatabase);	
    }

	public ProductoController getProductoController() {
		return this.productoController;
	}    

   

   
    
    

   
}

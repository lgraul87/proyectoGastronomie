package vista.principal;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import controlador.database.ConexionDB;
import controlador.database.GeneralController;
import validaciones.L;
import vista.genero.MaterialView;
import vista.genero.ProductoView;
import vista.instalacion.InstalacionView;
import vista.pago.PagoView;
import vista.pedido.PedidoView;
import vista.proveedor.ProveedorView;
import vista.usuario.UsuarioView;

public class Gastronomie {
	
	public static GeneralController controlGeneral = new GeneralController("raulbase");

	public static void main(String[] args) {

		start();
	}

	public static void start() {
		byte bOption;
		do {
			bOption = (byte) L.valida("\n\n"
					//
					+ "*  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *"
					//
					+ "\n·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·"
					//
					+ "\n		BIENVENIDO AL BAR GASTRONOMIE"
					//
					+ "\n·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·  ·"
					//
					+ "\n*  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *"
					//
					+ "\n"
					//
					+ "\n  --Para tomar aqui:    	(1)"
					//
					+ "\n  --Para llevar:     		(2)"
					//
					+ "\n  --Salir:           		(3)"
					//
					+ "\n"
					//
					+ "\n  --Admin:			(4)"
					//
					+ "\n", 1, 4, 3);
			//

			if (bOption == 1) {
				PedidoView.tomarNotaLocal(controlGeneral);
			} else if (bOption == 2) {
				PedidoView.tomarNotaTakeAway(controlGeneral);
			} else if (bOption == 3) {
				System.out.println("Buen dia, adios");
				ConexionDB.disconnectDatabase();
			} else if (bOption == 4) {
				String sUsuarioLogin = UsuarioView.buscarUsuarioLogin(controlGeneral);
				if (!sUsuarioLogin.equals("Cliente") && !sUsuarioLogin.equals("")) {
					seleccionOperaciones();
				}
				
			}
		} while (bOption != 3);
	}

	public static void seleccionOperaciones() {
		byte bOption;
		do {
			bOption = (byte) L.valida(""
					//
					+ "*  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *"
					//
					+ "\n		ADMINISTRACION"
					//
					+ "\n*  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *"
					//
					+ "\n"
					//
					+ "\n  --Operaciones usuario: 			(1)"
					//
					+ "\n  --Operaciones instalacion:     		(2)"
					//
					+ "\n  --Operaciones proveedor: 			(3)"
					//
					+ "\n  --Operaciones material: 			(4)"
					//
					+ "\n  --Operaciones Producto: 			(5)"
					//
					+ "\n  --Operaciones Pago: 				(6)"
					//
					+ "\n  --Operaciones Pedido: 			(7)"
					//
					+ "\n  --Salir: 					(8)"
					//
					+ "\n", 1, 8, 3);
			//

			if (bOption == 1) {
				UsuarioView.operacionesUsuario(controlGeneral);
			} else if (bOption == 2) {
				InstalacionView.operacionesInstalacion(controlGeneral);
			} else if (bOption == 3) {
				ProveedorView.operacionesProveedor(controlGeneral);
			} else if (bOption == 4) {
				MaterialView.operacionesMaterial(controlGeneral);
			}else if (bOption == 5) {
				ProductoView.operacionesProducto(controlGeneral);
			}else if (bOption == 6) {
				PagoView.operacionesPago(controlGeneral);
			}else if (bOption == 7) {
				PedidoView.operacionesPedido(controlGeneral);
			}
		} while (bOption != 8);
	}

	public static String encryptThisString(String input) 
    { 
        try { 
            // getInstance() method is called with algorithm SHA-512 
            MessageDigest md = MessageDigest.getInstance("SHA-512"); 
  
            // digest() method is called 
            // to calculate message digest of the input string 
            // returned as array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
  
            // Add preceding 0s to make it 32 bit 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
  
            // return the HashText 
            return hashtext; 
        } 
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    } 
}

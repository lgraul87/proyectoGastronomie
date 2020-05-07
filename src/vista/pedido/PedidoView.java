package vista.pedido;

import java.util.Date;

import controlador.database.GeneralController;
import modelo.genero.Material;
import modelo.genero.Producto;
import modelo.instalacion.Instalacion;
import modelo.pago.MetodoPago;
import modelo.pago.Pago;
import modelo.pedido.LineaPedido;
import modelo.pedido.Pedido;
import modelo.proveedor.Proveedor;
import modelo.usuario.Usuario;
import validaciones.L;

public class PedidoView {

	/****************************************************************************************************
	 * PARA CLIENTES
	 ****************************************************************************************************/
	public static void tomarNotaLocal(GeneralController controlGeneral) {

		/*
		 * System.out.println(controlGeneral.getProductoController().mostrarCartaBebida(
		 * ));
		 * 
		 * String sBebida = L.leer("Introduce la bebida");
		 * 
		 * byte bCantidad = (byte) L.valida("Cantidad: (bebida)", 1, 30, 3);
		 * 
		 * String sNombrePersona = L.leer("Me dices tu nombre:");
		 * 
		 * String sApellidos = L.leer("Me dices tu apellido:");
		 * 
		 * String sDni = L.leer("Me dices tu DNI:");
		 * 
		 * String sMetodoPedido = asignarMetodoPedido();
		 * 
		 * Producto oProducto =
		 * controlGeneral.getProductoController().obtenerProducto(sBebida);
		 * 
		 * TipoUsuario oTipoUsuario = new TipoUsuario("Cliente");
		 * 
		 * Usuario oUsuario = new Usuario(sNombrePersona, sDni, sApellidos,
		 * oTipoUsuario);
		 * 
		 * MetodoPedido oMetodoPedido = new MetodoPedido(sMetodoPedido);
		 * 
		 * Pedido oPedido = new Pedido(controlGeneral.getPedidoController().asignarId(),
		 * new Date(), oMetodoPedido);
		 * 
		 * Instalacion oInstalacion = new Instalacion("Gastronomie", "C/Comercio, 72",
		 * 1000);
		 * 
		 * Pedido oPedido = new Pedido(controlGeneral.getPedidoController().asignarId(),
		 * new Date(), oUsuario, oPedido, oInstalacion);
		 * 
		 * LineaPedido oLineaPedido = new
		 * LineaPedido(controlGeneral.getLineaPedidoController().asignarId(), oPedido,
		 * oProducto, bCantidad);
		 * 
		 * controlGeneral.getLineaPedidoController().upDateLineaPedido(oLineaPedido);
		 */
	}

	public static String asignarMetodoPedido() {
		String sMetodoPedido = "";

		byte bOption = (byte) L.valida(""
				//
				+ "Elige metodo de pago"
				//
				+ "\n  --Efectivo: (1)"
				//
				+ "\n  --Tarjeta: (2)", 1, 2, 3);

		if (bOption == 1) {
			sMetodoPedido = "Efectivo";

		} else if (bOption == 2) {
			sMetodoPedido = "Tarjeta";

		}
		return sMetodoPedido;
	}

	public static void tomarNotaTakeAway(GeneralController controlGeneral) {
		// TODO Auto-generated method stub

	}

	/****************************************************************************************************
	 * ADMIN
	 ****************************************************************************************************/
	public static void operacionesPedido(GeneralController controlGeneral) {
		byte bOption;
		do {
			bOption = (byte) L.valida(""
					//
					+ "*  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *"
					//
					+ "\n	ADMINISTRACION"
					//
					+ "\n*  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *"
					//
					+ "\n"
					//
					+ "\n  --Aniadir Pedido (Compras): 		(1)"
					//
					+ "\n  --Borrar Pedido (Compras):     	(2)"
					//
					+ "\n  --Buscar Pedido (Compras): 		(3)"
					//
					+ "\n  --Mostrar Pedido (Compras): 		(4)"
					//
					+ "\n  --Salir: 				(5)", 1, 5, 3);
			//

			if (bOption == 1) {
				aniadirPedido(controlGeneral);
			} else if (bOption == 2) {
				borrarPedido(controlGeneral);
			} else if (bOption == 3) {
				buscarPedido(controlGeneral);
			} else if (bOption == 4) {
				mostrarPedido(controlGeneral);
			}
		} while (bOption != 5);
	}

	/***********************************************************************************************
	 * ANIADIR LINEA DE PEDIDO
	 ***********************************************************************************************/
	private static void aniadirPedido(GeneralController controlGeneral) {

		String sResultado = "No se pudo aniadir.";
		byte bOptionPago = 0;
		int iId = 1;
		String sDni;
		String sPago = null;
		byte bOption = 0;
		byte bOptionGenero = 0;
		byte bCantidad = 0;
		Producto oProducto;
		float fPrecio = 0;
		Proveedor oProveedor = null;
		sDni = L.leer("Dni: (Usuario)");

		Usuario oUsuario = determinarUsuarioPedido(sDni, controlGeneral);

		if (oUsuario != null) {

			boolean errorControl = true;
			do {
				try {
					bOptionGenero = (byte) L.valida("Es un: " + "\nProducto: (1)" + "\nMaterial: (2)", 1, 2, 3);
					errorControl = false;
				} catch (NumberFormatException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} while (errorControl);

			// ###### PRODUCTO ################################################
			if (bOptionGenero == 1) {

				String sNombreProducto = L.leer("Nombre: (Producto)");

				if (determinarProducto(sNombreProducto, controlGeneral)) {

					oProveedor = obtenerProveedorProducto(sNombreProducto, controlGeneral);

					errorControl = true;
					do {
						try {
							fPrecio = (float) L.valida("Precio: (Producto)", 0.1F, 3000F, 2);
							errorControl = false;
						} catch (NumberFormatException e) {
							System.out.println(e.getMessage());
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					} while (errorControl);

					errorControl = true;
					do {
						try {
							bCantidad = (byte) L.valida("Cantidad (Unidades)", 1, 100, 3);
							errorControl = false;
						} catch (NumberFormatException e) {
							System.out.println(e.getMessage());
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					} while (errorControl);

					oProducto = new Producto(sNombreProducto, fPrecio, (short) bCantidad, "Derivados");

					errorControl = true;
					do {
						try {
							bOptionPago = (byte) L.valida("¿Pago incluido?: " + "\nSi: (1)" + "\nNo: (2)", 1, 2, 3);
							errorControl = false;
						} catch (NumberFormatException e) {
							System.out.println(e.getMessage());
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					} while (errorControl);

					if (bOptionPago == 1) {

						errorControl = true;
						do {
							try {
								bOption = (byte) L.valida(
										"Elige metodo de pago: " + "\nEfectivo: (1)" + "\nTarjeta: (2)", 1, 2, 3);
								errorControl = false;
							} catch (NumberFormatException e) {
								System.out.println(e.getMessage());
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
						} while (errorControl);

						if (bOption == 1) {
							sPago = "efectivo";
						}
						if (bOption == 2) {
							sPago = "tarjeta";
						}
						MetodoPago oMetodoPago = new MetodoPago(sPago);

						Pago oPago = new Pago(iId, new Date(), oMetodoPago);

						String sNombreLocal = L.leer("Nombre: (Instalacion o Local)");

						Instalacion oInstalacion = determinarInstalacion(sNombreLocal, controlGeneral);

						if (oInstalacion != null) {

							Pedido oPedido = new Pedido(iId, new Date(), oUsuario, oPago, oInstalacion);

							LineaPedido oLineaPedido = new LineaPedido(iId, oPedido, oProducto, bCantidad, "Compra",
									oProveedor);

							if (controlGeneral.getLineaPedidoController().add(oLineaPedido)) {
								sResultado = "Aniadido correctamente";
							}
						}

					} else if (bOptionPago == 2) {

						String sNombreLocal = L.leer("Nombre: (Instalacion o Local)");

						Instalacion oInstalacion = determinarInstalacion(sNombreLocal, controlGeneral);

						if (oInstalacion != null) {
							Pedido oPedido = new Pedido(iId, new Date(), oUsuario, oInstalacion);

							LineaPedido oLineaPedido = new LineaPedido(iId, oPedido, oProducto, bCantidad, "Compra",
									oProveedor);

							if (controlGeneral.getLineaPedidoController().addSinPago(oLineaPedido)) {
								sResultado = "Aniadido correctamente";
							}
						}

					}

				}

				// ###### MATERIAL ################################################

			} else if (bOptionGenero == 2) {

				String sNombreMaterial = L.leer("Nombre: (Material)");

				if (determinarMaterial(sNombreMaterial, controlGeneral)) {

					oProveedor = obtenerProveedorMaterial(sNombreMaterial, controlGeneral);

					errorControl = true;
					do {
						try {
							fPrecio = (float) L.valida("Precio: (Material)", 0.1F, 3000F, 2);
							errorControl = false;
						} catch (NumberFormatException e) {
							System.out.println(e.getMessage());
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					} while (errorControl);

					errorControl = true;
					do {
						try {
							bCantidad = (byte) L.valida("Cantidad (Unidades)", 1, 100, 3);
							errorControl = false;
						} catch (NumberFormatException e) {
							System.out.println(e.getMessage());
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					} while (errorControl);

					Material oMaterial = null;
					oMaterial = new Material(sNombreMaterial, fPrecio, (short) bCantidad, "Derivados");

					errorControl = true;
					do {
						try {
							bOptionPago = (byte) L.valida("¿Pago incluido?: " + "\nSi: (1)" + "\nNo: (2)", 1, 2, 3);
							errorControl = false;
						} catch (NumberFormatException e) {
							System.out.println(e.getMessage());
						} catch (Exception e) {
							System.out.println(e.getMessage());
						}
					} while (errorControl);

					if (bOptionPago == 1) {

						errorControl = true;
						do {
							try {
								bOption = (byte) L.valida(
										"Elige metodo de pago: " + "\nEfectivo: (1)" + "\nTarjeta: (2)", 1, 2, 3);
								errorControl = false;
							} catch (NumberFormatException e) {
								System.out.println(e.getMessage());
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
						} while (errorControl);

						if (bOption == 1) {
							sPago = "efectivo";
						}
						if (bOption == 2) {
							sPago = "tarjeta";
						}
						MetodoPago oMetodoPago = new MetodoPago(sPago);

						Pago oPago = new Pago(iId, new Date(), oMetodoPago);

						String sNombreLocal = L.leer("Nombre: (Instalacion o Local)");

						Instalacion oInstalacion = determinarInstalacion(sNombreLocal, controlGeneral);

						if (oInstalacion != null) {

							Pedido oPedido = new Pedido(iId, new Date(), oUsuario, oPago, oInstalacion);

							LineaPedido oLineaPedido = new LineaPedido(iId, oPedido, oMaterial, bCantidad, "Compra",
									oProveedor);

							if (controlGeneral.getLineaPedidoController().add(oLineaPedido)) {
								sResultado = "Aniadido correctamente";
							}
						}

					} else if (bOptionPago == 2) {

						String sNombreLocal = L.leer("Nombre: (Instalacion o Local)");

						Instalacion oInstalacion = determinarInstalacion(sNombreLocal, controlGeneral);

						if (oInstalacion != null) {
							Pedido oPedido = new Pedido(iId, new Date(), oUsuario, oInstalacion);

							LineaPedido oLineaPedido = new LineaPedido(iId, oPedido, oMaterial, bCantidad, "Compra",
									oProveedor);

							if (controlGeneral.getLineaPedidoController().addSinPago(oLineaPedido)) {
								sResultado = "Aniadido correctamente";
							}
						}

					}

				}

			}

			// --------------------------------------------------------------

		}
		System.out.println(sResultado);
	}

	private static Proveedor obtenerProveedorMaterial(String sNombreMaterial, GeneralController controlGeneral) {
		Proveedor oProveedor = null;

		oProveedor = controlGeneral.getMaterialProveedorController().obtenerProveedorMaterial(sNombreMaterial);
		return oProveedor;
	}

	private static boolean determinarMaterial(String sNombreMaterial, GeneralController controlGeneral) {
		boolean bEncontrado = false;

		if (controlGeneral.getMaterialProveedorController().determinarMaterial(sNombreMaterial)) {
			bEncontrado = true;
		}

		return bEncontrado;
	}

	private static Proveedor obtenerProveedorProducto(String sNombreProducto, GeneralController controlGeneral) {
		Proveedor oProveedor = null;

		oProveedor = controlGeneral.getProductoProveedorController().obtenerProveedorProducto(sNombreProducto);
		return oProveedor;
	}

	private static boolean determinarProducto(String sNombreProducto, GeneralController controlGeneral) {

		boolean bEncontrado = false;

		if (controlGeneral.getProductoProveedorController().determinarProducto(sNombreProducto)) {
			bEncontrado = true;
		}

		return bEncontrado;
	}

	private static Instalacion determinarInstalacion(String sNombreLocal, GeneralController controlGeneral) {
		Instalacion oInstalacion = null;

		oInstalacion = controlGeneral.getInstalacionController().determinarInstalacion(sNombreLocal);
		return oInstalacion;
	}

	private static Usuario determinarUsuarioPedido(String sDni, GeneralController controlGeneral) {

		Usuario oUsuario = controlGeneral.getUsuarioController().determinarUsuarioPedido(sDni);

		return oUsuario;
	}

	/***********************************************************************************************
	 * BORRAR LINEA DE PEDIDO
	 ***********************************************************************************************/

	private static void borrarPedido(GeneralController controlGeneral) {

		int iNumero = 0;

		String sResultado = "No se pudo borrar la linea de pedido";

		String sAccion = controlGeneral.getLineaPedidoController().mostrarPedidosPorId();
		System.out.println(sAccion);
		if (!sAccion.equals("No hay lineas de pedidos")) {

			boolean errorControl = true;
			do {
				try {
					iNumero = (int) L.valida("Dime id: (Linea de Pedido)", 1, 100000, 1);
					errorControl = false;
				} catch (NumberFormatException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} while (errorControl);

			if (controlGeneral.getLineaPedidoController().remove(iNumero)) {
				sResultado = "Linea de Pedido borrado";
			}

			System.out.println(sResultado);

		}
	}

	/***********************************************************************************************
	 * BUSCAR LINEA DE PEDIDO
	 ***********************************************************************************************/
	private static void buscarPedido(GeneralController controlGeneral) {
		String sResultado = "Linea de Pedido no registrado";

		String sAccion = controlGeneral.getLineaPedidoController().mostrarPedidosPorId();
		System.out.println(sAccion);
		if (!sAccion.equals("No hay lineas de pedidos")) {

			int iNumero = 0;
			boolean errorControl = true;
			do {
				try {
					iNumero = (int) L.valida("Dime id: (Pedido)", 1, 100000, 1);
					errorControl = false;
				} catch (NumberFormatException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} while (errorControl);

			if (controlGeneral.getLineaPedidoController().search(iNumero)) {
				sResultado = "Linea registrada";
			}
			System.out.println(sResultado);
		} else {
			System.out.println(sAccion);
		}
	}

	/***********************************************************************************************
	 * MOSTRAR PEDIDO
	 ***********************************************************************************************/
	private static void mostrarPedido(GeneralController controlGeneral) {

		String sResultado = null;
		String sAccion = controlGeneral.getLineaPedidoController().mostrarPedidosPorId();
		if (!sAccion.equals("No hay lineas de pedidos")) {

			byte bOption = 0;

			boolean errorControl = true;

			do {
				try {
					bOption = (byte) L.valida(""
							//
							+ "El pedido de un proveedor: 		(1)"
							//
							+ "\nTodos los pedidos: 			(2)", 1, 2, 3);

					errorControl = false;

				} catch (NumberFormatException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} while (errorControl);

			if (bOption == 1) {
				String sNombreProveedor = null;
				
				sNombreProveedor = L.leer("Nombre: (Proveedor)");
				

				if (controlGeneral.getLineaPedidoController().searchPedidoProveedor(sNombreProveedor)) {
					sResultado = controlGeneral.getLineaPedidoController().mostrarPedidoProveedor(sNombreProveedor);

				} else {
					sResultado = "Pedido no encontrado";
				}

			} else if (bOption == 2) {
				sResultado = controlGeneral.getLineaPedidoController().mostrarPedidos();
			}
			System.out.println(sResultado);
		} else {
			System.out.println(sAccion);
		}
	}
}

package vista.pago;

import controlador.database.GeneralController;
import modelo.pago.MetodoPago;
import modelo.pago.Pago;
import validaciones.L;

public class PagoView {

	/***********************************************************************************************
	 * ADMIN
	 ***********************************************************************************************/
	public static void operacionesPago(GeneralController controlGeneral) {
		boolean errorControl = true;
		byte bOption = 0;
		do {
			try {
				bOption = (byte) L.valida(""
						//
						+ "*  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *"
						//
						+ "\n					ADMINISTRACION"
						//
						+ "\n*  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *"
						//
						+ "\n"
						//
						+ "\n  --Aniadir Pago (Compra): 	(1)"
						//
						+ "\n  --Borrar Pago (Compra):     	(2)"
						//
						+ "\n  --Buscar Pago (Compra): 	(3)"
						//
						+ "\n  --Mostrar Pago (Compra): 	(4)"
						//
						+ "\n  --Salir: 			(5)", 1, 5, 3);

				errorControl = false;

			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			//

			if (bOption == 1) {
				aniadirPago(controlGeneral);
			} else if (bOption == 2) {
				borrarPago(controlGeneral);
			} else if (bOption == 3) {
				buscarPago(controlGeneral);
			} else if (bOption == 4) {
				mostrarPago(controlGeneral);
			}
		} while (errorControl);
	}

	/***********************************************************************************************
	 * ANIADIR PAGO
	 ***********************************************************************************************/
	public static void aniadirPago(GeneralController controlGeneral) {

		String sResultado = "No se pudo aniadir.";
		String sPago = null;
		int iId = 0;
		int bOption = 0;

		Pago oPago = null;

		boolean errorControl = true;
		do {
			try {
				bOption = (byte) L.valida("Elige metodo de pago: " + "\nEfectivo: (1)" + "\nTarjeta: (2)", 1, 2, 3);
				errorControl = false;
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while (errorControl);

		if (bOption == 1) {
			sPago = "Efectivo";
		} else if (bOption == 2) {
			sPago = "Tarjeta";
		}

		MetodoPago oMetodoPago = new MetodoPago(sPago);

		oPago = new Pago(iId, new java.util.Date(), oMetodoPago);

		if (controlGeneral.getPagoController().add(oPago)) {
			sResultado = "Aniadido correctamente.";
		}
		System.out.println(sResultado);
	}

	/***********************************************************************************************
	 * BORRAR PAGO
	 ***********************************************************************************************/

	public static void borrarPago(GeneralController controlGeneral) {

		int iIdPago = 0;
		String sResultado = "No hay pagos";

		if (!controlGeneral.getPagoController().mostrarPagos().equals("No hay pagos")) {

			sResultado = "No se pudo borrar el pago";

			String sAccion = controlGeneral.getPagoController().mostrarPagos();
			System.out.println(sAccion);
			if (!sAccion.equals("No hay pagos")) {

				boolean errorControl = true;
				do {
					try {
						iIdPago = (int) L.valida("Dime id: (Pago)", 1, 100000, 1);
						errorControl = false;
					} catch (NumberFormatException e) {
						System.out.println(e.getMessage());
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				} while (errorControl);

				Pago oPago = new Pago(iIdPago);

				if (controlGeneral.getPagoController().remove(oPago)) {
					sResultado = "Pago borrado";
				}
			}
			System.out.println(sResultado);

		}
	}

	/***********************************************************************************************
	 * BUSCAR PAGO
	 ***********************************************************************************************/
	public static void buscarPago(GeneralController controlGeneral) {
		String sResultado = "No hay pagos";

		if (!controlGeneral.getPagoController().mostrarPagos().equals("No hay pagos")) {

			System.out.println(controlGeneral.getPagoController().mostrarPagos());
			sResultado = "Pago no registrado";

			int iIdPago = 0;
			boolean errorControl = true;
			do {
				try {
					iIdPago = (int) L.valida("Dime id: (Pago)", 1, 100000, 1);
					errorControl = false;
				} catch (NumberFormatException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} while (errorControl);

			Pago oPago = new Pago(iIdPago);

			if (controlGeneral.getPagoController().search(oPago)) {
				sResultado = "Pago registrado";
			}

		}
		System.out.println(sResultado);

	}

	/***********************************************************************************************
	 * MOSTRAR PAGO
	 ***********************************************************************************************/
	public static void mostrarPago(GeneralController controlGeneral) {

		String sResultado = "No hay pagos";

		if (!controlGeneral.getPagoController().mostrarPagos().equals("No hay pagos")) {

			int iIdPago = 0;

			byte bOption = 0;

			boolean errorControl = true;

			do {
				try {
					bOption = (byte) L.valida(""
							//
							+ "Un pago: 		(1)"
							//
							+ "\nLista de pagos: 	(2)", 1, 2, 3);

					errorControl = false;

				} catch (NumberFormatException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} while (errorControl);

			if (bOption == 1) {

				errorControl = true;
				do {
					try {
						iIdPago = (int) L.valida("Dime id: (Pago)", 1, 100000, 1);

						errorControl = false;

					} catch (NumberFormatException e) {
						System.out.println(e.getMessage());
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				} while (errorControl);

				Pago oPago = new Pago(iIdPago);

				if (controlGeneral.getPagoController().search(oPago)) {
					sResultado = controlGeneral.getPagoController().mostrarPago(oPago);

				} else {
					sResultado = "Pago no encontrado";
				}

			} else if (bOption == 2) {
				sResultado = controlGeneral.getPagoController().mostrarPagos();
			}
		}

		System.out.println(sResultado);

	}
}

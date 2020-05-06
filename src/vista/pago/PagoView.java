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
		byte bOption;
		do {
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
					+ "\n  --Aniadir Pago (Compra): (1)"
					//
					+ "\n  --Borrar Pago (Compra):     (2)"
					//
					+ "\n  --Buscar Pago (Compra): (3)"
					//
					+ "\n  --Mostrar Pago (Compra): (4)"
					//
					+ "\n  --Salir: (5)", 1, 5, 3);
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
		} while (bOption != 5);
	}

	/***********************************************************************************************
	 * ANIADIR PAGO
	 ***********************************************************************************************/
	private static void aniadirPago(GeneralController controlGeneral) {

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
			sPago = "efectivo";
		} else if (bOption == 2) {
			sPago = "tarjeta";
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

	private static void borrarPago(GeneralController controlGeneral) {

		int iNumero = 0;

		String sResultado = "No se pudo borrar el pago";

		String sAccion = controlGeneral.getPagoController().mostrarPagos();
		System.out.println(sAccion);
		if (!sAccion.equals("No hay pagos")) {

			boolean errorControl = true;
			do {
				try {
					iNumero = (int) L.valida("Dime id: (Pago)", 1, 100000, 1);
					errorControl = false;
				} catch (NumberFormatException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} while (errorControl);

			if (controlGeneral.getPagoController().remove(iNumero)) {
				sResultado = "Pago borrado";
			}

			System.out.println(sResultado);

		}
	}

	/***********************************************************************************************
	 * BUSCAR PAGO
	 ***********************************************************************************************/
	private static void buscarPago(GeneralController controlGeneral) {
		String sResultado = "Pago no registrado";

		String sAccion = controlGeneral.getPagoController().mostrarPagos();
		if (!sAccion.equals("No hay pagos")) {

			int iNumero = 0;
			boolean errorControl = true;
			do {
				try {
					iNumero = (int) L.valida("Dime id: (Pago)", 1, 100000, 1);
					errorControl = false;
				} catch (NumberFormatException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} while (errorControl);

			if (controlGeneral.getPagoController().search(iNumero)) {
				sResultado = "Pago registrado";
			}
			System.out.println(sResultado);
		} else {
			System.out.println(sAccion);
		}
	}

	/***********************************************************************************************
	 * MOSTRAR PAGO
	 ***********************************************************************************************/
	private static void mostrarPago(GeneralController controlGeneral) {

		String sResultado = null;
		int iId = 0;

		String sAccion = controlGeneral.getPagoController().mostrarPagos();
		if (!sAccion.equals("No hay pagos")) {

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
						iId = (int) L.valida("Dime id: (Pago)", 1, 100000, 1);

					} catch (NumberFormatException e) {
						System.out.println(e.getMessage());
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				} while (errorControl);

				if (controlGeneral.getPagoController().search(iId)) {
					sResultado = controlGeneral.getPagoController().mostrarPago(iId);

				} else {
					sResultado = "Pago no encontrado";
				}

			} else if (bOption == 2) {
				sResultado = controlGeneral.getPagoController().mostrarPagos();
			}
			System.out.println(sResultado);
		}else {
			System.out.println(sAccion);
		}
	}
}

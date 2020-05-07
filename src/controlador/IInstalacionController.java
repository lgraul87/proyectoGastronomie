package controlador;

import modelo.instalacion.Instalacion;

public interface IInstalacionController {

	public boolean add(Instalacion oInstalacion);

	public boolean remove(String sNombre);

	public boolean search(String sNombre);

	public String mostrarInstalacion(String sNombre);

	public String mostrarInstalaciones();

	public Instalacion determinarInstalacion(String sNombreLocal);

}

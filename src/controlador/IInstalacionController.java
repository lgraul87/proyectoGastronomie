package controlador;

import modelo.instalacion.Instalacion;

public interface IInstalacionController {

	public boolean add(Instalacion oInstalacion);

	public boolean remove(Instalacion oInstalacion);

	public boolean search(Instalacion oInstalacion);

	public String mostrarInstalacion(Instalacion oInstalacion);

	public String mostrarInstalaciones();

	public Instalacion determinarInstalacion(Instalacion oInstalacion);

}

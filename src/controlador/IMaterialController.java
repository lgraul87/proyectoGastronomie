package controlador;

import modelo.genero.Material;

public interface IMaterialController {

	public boolean add(Material oMaterial);

	public boolean remove(String sNombre);

	public boolean search(String sNombre);

	public String mostrarMaterial(String sNombre);

	public String mostrarMateriales();

}

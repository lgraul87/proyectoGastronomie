package controlador;

import modelo.genero.Material;

public interface IMaterialController {

	public boolean add(Material oMaterial);

	public boolean remove(Material oMaterial);

	public boolean search(Material oMaterial);

	public String mostrarMaterial(Material oMaterial);

	public String mostrarMateriales();

}

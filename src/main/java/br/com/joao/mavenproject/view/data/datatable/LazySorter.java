package br.com.joao.mavenproject.view.data.datatable;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import br.com.joao.mavenproject.domain.Usuario;

public class LazySorter implements Comparator<Usuario> {

	private String sortField;

	private SortOrder sortOrder;

	public LazySorter(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	public int compare(Usuario usuario1, Usuario usuario2) {
		try {
			Object value1 = Usuario.class.getField(this.sortField).get(usuario1);
			Object value2 = Usuario.class.getField(this.sortField).get(usuario2);

			int value = ((Comparable) value1).compareTo(value2);

			return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

}

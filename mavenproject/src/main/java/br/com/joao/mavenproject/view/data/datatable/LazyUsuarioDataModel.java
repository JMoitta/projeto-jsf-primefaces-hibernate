package br.com.joao.mavenproject.view.data.datatable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.joao.mavenproject.domain.Usuario;

public class LazyUsuarioDataModel extends LazyDataModel<Usuario> {

	private static final long serialVersionUID = 1L;
	private List<Usuario> datasource;

	public LazyUsuarioDataModel(List<Usuario> datasource) {
		this.datasource = datasource;
	}

	@Override
	public Usuario getRowData(String rowKey) {
		for (Usuario usuario : datasource) {
			if (usuario.getId().equals(rowKey))
				return usuario;
		}

		return null;
	}

	@Override
	public Object getRowKey(Usuario usuario) {
		return usuario.getId();
	}
	
	@Override
    public List<Usuario> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {
        List<Usuario> data = new ArrayList<Usuario>();
 
        //filter
        for(Usuario usuario : datasource) {
            boolean match = true;
 
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(usuario.getClass().getField(filterProperty).get(usuario));
 
                        if(filterValue == null || fieldValue.startsWith(filterValue.toString())) {
                            match = true;
                    }
                    else {
                            match = false;
                            break;
                        }
                    } catch(Exception e) {
                        match = false;
                    }
                }
            }
 
            if(match) {
                data.add(usuario);
            }
        }
 
        //sort
        if(sortField != null) {
            Collections.sort(data, new LazySorter(sortField, sortOrder));
        }
 
        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);
 
        //paginate
        if(dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }
}

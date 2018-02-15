package br.com.joao.mavenproject.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.joao.mavenproject.dao.DaoGeneric;
import br.com.joao.mavenproject.domain.Usuario;

@ManagedBean(name = "usuarioView")
@ViewScoped
public class UsuarioView implements Serializable {

	private static final long serialVersionUID = -5476241976371132931L;

	private DaoGeneric<Usuario> daoGeneric = new DaoGeneric<>();

	private Usuario usuarioSelecionado;

	private List<Usuario> usuarios = new ArrayList<>();

	@PostConstruct
	public void init() {
		carregarUsuarios();
	}

	public String salvar() {
		daoGeneric.merge(this.usuarioSelecionado);
		this.usuarioSelecionado = new Usuario();
		carregarUsuarios();
		return "";
	}

	public String remove(){
		daoGeneric.deletePorId(this.usuarioSelecionado);
		carregarUsuarios();
		return "";
	}
	public DaoGeneric<Usuario> getDaoGeneric() {
		return daoGeneric;
	}

	public void setDaoGeneric(DaoGeneric<Usuario> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}

	public Usuario getUsuarioSelecionado() {
		if (this.usuarioSelecionado == null) {
			this.usuarioSelecionado = new Usuario();
		}
		return usuarioSelecionado;
	}

	public void carregarUsuarios() {
		setUsuarios(daoGeneric.getListEntity(Usuario.class));
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}
	
	public void limpaUsuario(){
		this.usuarioSelecionado = new Usuario();
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}

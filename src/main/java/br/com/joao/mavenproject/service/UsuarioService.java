package br.com.joao.mavenproject.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.com.joao.mavenproject.domain.Usuario;

@ManagedBean(name="usuarioService")
@ApplicationScoped
public class UsuarioService {

	private final static String[] nome;
    private final static String[] telefone;
     
    static {
    	nome = new String[10];
    	nome[0] = "Joao";
    	nome[1] = "Claudia";
    	nome[2] = "Maria";
    	nome[3] = "Emanuel";
    	nome[4] = "Daniel";
    	nome[5] = "Delubio";
    	nome[6] = "Jose";
    	nome[7] = "Antonia";
        nome[8] = "Soraia";
        nome[9] = "Marcos";
         
        telefone = new String[10];
        telefone[0] = "11 1234 4321";
        telefone[1] = "11 5432 4321";
        telefone[2] = "11 1212 4321";
        telefone[3] = "44 1234 4321";
        telefone[4] = "44 1234 6789";
        telefone[5] = "44 1234 5432";
        telefone[6] = "77 1234 5555";
        telefone[7] = "77 1234 2222";
        telefone[8] = "77 1234 1111";
        telefone[9] = "77 1234 9999";
    }
     
    public List<Usuario> createUsuario(int size) {
        List<Usuario> list = new ArrayList<Usuario>();
        for(long i = 0 ; i < size ; i++) {
            list.add(new Usuario(i,getRandomNome(), "mt-" + i,getRandomTelefone()));
        }
         
        return list;
    }
     
    private String getRandomTelefone() {
		
		return telefone[(int) (Math.random() * 10)];
	}

	private String getRandomNome() {
		return nome[(int) (Math.random() * 10)];
	}

	
 
    public List<String> getNome() {
		return Arrays.asList(nome);
	}

	public List<String> getTelefone() {
		return Arrays.asList(telefone);
	}
}

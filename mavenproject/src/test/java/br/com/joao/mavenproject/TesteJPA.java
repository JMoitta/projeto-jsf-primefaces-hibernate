package br.com.joao.mavenproject;

import javax.persistence.Persistence;

public class TesteJPA {

	public static void main(String[] args) {
		Persistence.createEntityManagerFactory("mavenproject");
	}
}

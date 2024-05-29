package com.dam.Deck_Constructor_TFG.Test;


import java.util.List;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import com.dam.Deck_Constructor_TFG.Modelo.Usuario;
import com.dam.Deck_Constructor_TFG.Relaciones.HibernateUtil;

import jakarta.persistence.Query;

class TestGet {

	@Test
	void test() {
		Session sis = HibernateUtil.getSessionFactory().openSession();
		try {
		    Query query = sis.createQuery("FROM Usuario");
		    List<Usuario> usuarios = query.getResultList();
		    // Hacer algo con la lista de usuarios recuperados
		    for (Usuario usuario : usuarios) {
		        System.out.println(usuario);
		    }
		} finally {
		    sis.close();
		}
	}

}

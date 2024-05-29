package com.dam.Deck_Constructor_TFG.Test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.dam.Deck_Constructor_TFG.Modelo.Usuario;
import com.dam.Deck_Constructor_TFG.Relaciones.HibernateUtil;

class TestCrearBD_sqlite {
	@Test
	void test() {
		var sis = HibernateUtil.getSessionFactory().openSession();
		
		sis.beginTransaction();
		Usuario user = new Usuario("admin", "admin");
		
		sis.persist(user);
		
		sis.getTransaction().commit();
		
		Usuario userBD = sis.get(Usuario.class, 1);
		
		System.out.println(userBD);
		sis.close();
	}
	@Disabled
	@Test
	void testGet() {
		var sis = HibernateUtil.getSessionFactory().openSession();
		
		sis.beginTransaction();
		
		Usuario user = sis.get(Usuario.class, 1);
		
		System.out.println(user);
		
		sis.close();
	}

}

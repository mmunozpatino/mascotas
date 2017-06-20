package com.mascotas.especies.repository;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.mascotas.application.repository.Repositorio;
import com.mascotas.especies.entities.Especie;

@Stateless
@LocalBean
public class EspeciesRepository implements Repositorio<Integer, Especie> {
    @PersistenceContext(unitName = "MascotasDS")
	private EntityManager entityManager;
	
	@Override
	public void add(Especie newOne) {
		entityManager.persist(newOne);
	}

	@Override
	public void remove(Especie toDelete) {
		entityManager.remove(toDelete);
	}

	@Override
	public Especie get(Integer id) {
		return entityManager.find(Especie.class, id);
	}

	@Override
	public List<Especie> getAll() {
		String q = "SELECT p from " + Especie.class.getName() + " p ";
		TypedQuery<Especie> query = entityManager.createQuery(q, Especie.class);

		List<Especie> result = query.getResultList();
		if (result == null) {
			result = new ArrayList<Especie>();
		}
		return result;
	}

	@Override
	public long size() {
		throw new RuntimeException("No se puede acceder a todos los perfiles.");
	}
}
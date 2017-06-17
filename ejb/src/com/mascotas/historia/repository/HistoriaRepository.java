package com.mascotas.historia.repository;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.mascotas.application.repository.Repositorio;
import com.mascotas.historia.entities.Historia;

@Stateless
@LocalBean

public class HistoriaRepository implements Repositorio<Integer, Historia> {
	@PersistenceContext(unitName = "MascotasDS")
	private EntityManager entityManager;
	
	@Override
	public void add(Historia newOne) {
		entityManager.persist(newOne);
	}

	@Override
	public void remove(Historia toDelete) {
		entityManager.remove(toDelete);
	}

	@Override
	public Historia get(Integer id) {
		return entityManager.find(Historia.class, id);
	}

	@Override
	public List<Historia> getAll() {
		String q = "SELECT p from " + Historia.class.getName() + " p ";
		TypedQuery<Historia> query = entityManager.createQuery(q, Historia.class);

		List<Historia> result = query.getResultList();
		if (result == null) {
			result = new ArrayList<Historia>();
		}
		return result;
	}

	@Override
	public long size() {
		throw new RuntimeException("No se puede acceder a todos los perfiles.");
	}

	public List<Historia> getByMascota(Integer id) {
		String q = "SELECT p from " + Historia.class.getName() +
				" p JOIN p.mascota m WHERE  m.id = :id ";
		TypedQuery<Historia> query = entityManager.createQuery(q, Historia.class);
		query.setParameter("id", id);
		return query.getResultList();
	}
}
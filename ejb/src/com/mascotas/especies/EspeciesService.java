package com.mascotas.especies;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.mascotas.application.exceptions.BusinessException;
import com.mascotas.especies.dto.EspecieDTO;
import com.mascotas.especies.entities.Especie;
import com.mascotas.especies.repository.EspeciesRepository;

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)

public class EspeciesService{
	@EJB
	private EspeciesRepository especiesRepository;
	
	
	public List<EspecieDTO> findAll() throws BusinessException {
		return EspecieDTO.Factory.get(especiesRepository.getAll());	
	}
	public Especie findById(Integer id) throws BusinessException {
		return especiesRepository.get(id);
	}
}
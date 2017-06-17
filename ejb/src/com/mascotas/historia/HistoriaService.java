package com.mascotas.historia;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.mascotas.application.exceptions.BusinessException;
import com.mascotas.application.utils.StringUtils;
import com.mascotas.historia.dto.HistoriaDTO;
import com.mascotas.historia.entities.Historia;
import com.mascotas.historia.repository.HistoriaRepository;
import com.mascotas.mascotas.entities.Mascota;
import com.mascotas.mascotas.repository.MascotaRepository;

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class HistoriaService{
	@EJB
	private MascotaRepository mascotaRepository;

	@EJB
	private HistoriaRepository historiaRepository;
	
	
	public List<HistoriaDTO> findAll() throws BusinessException {
		return HistoriaDTO.Factory.get(historiaRepository.getAll());	
	}
	
	
	public HistoriaDTO findById(Integer id) throws BusinessException {
		return HistoriaDTO.Factory.get(historiaRepository.get(id));
	}

	public List<HistoriaDTO> findByMascota(Integer id) throws BusinessException {
		return HistoriaDTO.Factory.get(historiaRepository.getByMascota(id));	
	}
	
	public void addHistoria(Integer idMascota, HistoriaDTO dto){
		Mascota mascota = mascotaRepository.get(idMascota);
		
		Historia historia = new Historia();
		
		historia.setDescripcion(dto.getDescripcion());
		historia.setRecordatorio(dto.getRecordatorio());
		historia.setTitulo(dto.getTitulo());
		historia.setMascota(mascota);
		try {
			historia.setFecha(StringUtils.DATE_FORMAT.parse(dto.getFecha()));
		} catch (Exception e) {
		}
		historiaRepository.add(historia);
		
	}
	public void deleteHistoria(Integer idHistoria){
		Historia historia = historiaRepository.get(idHistoria);
		historiaRepository.remove(historia);
	}
}
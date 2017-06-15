package com.mascotas.historias;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.mascotas.application.exceptions.BusinessException;
import com.mascotas.application.exceptions.ValidationError;
import com.mascotas.application.utils.StringUtils;
import com.mascotas.historias.dto.HistoriasDTO;
import com.mascotas.historias.entities.Historia;
import com.mascotas.historias.repository.HistoriaRepository;
//import com.mascotas.seguridad.entities.Usuario;
import com.mascotas.seguridad.repository.UsuariosRepository;
//import com.mascotas.mascotas.MascotaServiceValidations;
//import com.mascotas.mascotas.repository.MascotaRepository;
import com.mascotas.historias.HistoriaServiceValidations;

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class HistoriaService {
    @EJB
	private UsuariosRepository usuarioRepository;

	@EJB
	private HistoriaRepository historiaRepository;

	@EJB
	private HistoriaServiceValidations historiaValidations;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<HistoriasDTO> findAll() {
		return (List<HistoriasDTO>) HistoriasDTO.Factory.get(historiaRepository.getAll());
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void guardarHistoria(Historia historia) {
		historiaRepository.add(historia);
	}

	/**
	 * Busca las Historias de la mascota.
	 * 
	 */
	/*
	public List<HistoriasDTO> findForLogin(tring login) throws BusinessException {
		List<ValidationError> errors = historiaValidations.validarFindForLogin(login);
		if (errors.size() > 0) {
			throw new BusinessException("Datos de usuario invalidos.", errors);
		}

		List<Historia> historias = historiaRepository.getByMascota(login);

		return HistoriasDTO.Factory.get(historias);
	}*/

	/**
	 * Actualiza los datos basicos de las mascotas.
	 * 
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int actualizarHistoria(String login, HistoriasDTO historia) throws BusinessException {
		List<ValidationError> errors = historiaValidations.validarActualizarMascota(login, historia);
		if (errors.size() > 0) {
			throw new BusinessException("Error al guardar mascota.", errors);
		}

		Historia historiaEditada = null;
		if (historia.getId() != null) {
			historiaEditada = historiaRepository.get(historia.getId());
		}

		if (historiaEditada == null) {
			//Usuario usuario = usuarioRepository.get(login);

			historiaEditada = new Historia();
			historiaEditada.setMascota(historia.getMascota());;
			historiaEditada.setTitulo(historia.getTitulo());
			historiaRepository.add(historiaEditada);
		}

		historiaEditada.setDescripcion(historia.getDescripcion());
        historiaEditada.setRecordatorio(historia.getRecordatorio());
		try {
			historiaEditada.setFecha(StringUtils.DATE_FORMAT.parse(historia.getFecha()));
		} catch (Exception e) {
		}
		historiaEditada.setTitulo(historia.getTitulo());
		
		return historiaEditada.getId();
	}
	
	public List<Historia> findByMascota(Integer id) throws BusinessException {

		return historiaRepository.getByMascota(id);	}
	public Historia findById(Integer id) throws BusinessException {

		return historiaRepository.get(id);
	}

	public void eliminarHistoria(String name, Integer id) {
		List<ValidationError> errors = historiaValidations.validarEliminarMascota(name, id);
		if (errors.size() > 0) {
			throw new BusinessException("Error al eliminar la Mascota.", errors);
		}

		
		historiaRepository.remove(historiaRepository.get(id));
	}
}

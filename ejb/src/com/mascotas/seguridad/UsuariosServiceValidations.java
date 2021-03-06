package com.mascotas.seguridad;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;

import org.jboss.crypto.CryptoUtil;

import com.mascotas.application.exceptions.BusinessException;
import com.mascotas.application.exceptions.ValidationError;
import com.mascotas.seguridad.dto.ActualizarUsuarioDTO;
import com.mascotas.seguridad.dto.RegistrarUsuarioDTO;
import com.mascotas.seguridad.entities.Usuario;
import com.mascotas.seguridad.repository.UsuariosRepository;

/**
 * Esta clase implementa todas las validaciones necesarias relacionadas con
 * UsuariosService.
 * 
 * @author Nestor
 *
 */
@Stateless
@LocalBean
public class UsuariosServiceValidations {
	@EJB
	private UsuariosRepository usuariosRepository;

	/**
	 * Valida la insercion de un usuario nuevo en la base de datos.
	 * 
	 * @param usuario
	 * @throws BusinessException
	 */
	public void validarRegistrarUsuario(RegistrarUsuarioDTO usuario) {

		List<ValidationError> errors = new ArrayList<ValidationError>();

		if (usuario.getLogin() == null || usuario.getLogin().trim().isEmpty()) {
			errors.add(new ValidationError("login", "Debe especificar un login de acceso al sistema."));
		}

		if (usuariosRepository.get(usuario.getLogin()) != null) {
			errors.add(new ValidationError("login", "El usuario ya existe en la base de datos."));
		}

		if (errors.size() > 0) {
			throw new BusinessException("Datos de usuario invalidos.", errors);
		}
	}

	/**
	 * Valida la insercion de un usuario nuevo en la base de datos.
	 * 
	 * @param usuario
	 * @throws BusinessException
	 */
	public void validarActualizarUsuario(ActualizarUsuarioDTO usuario) {
		List<ValidationError> errors = new ArrayList<ValidationError>();

		if (usuario.getRoles().size() == 0) {
			errors.add(new ValidationError("roles", "Al menos debe definir un rol para el usuario."));
		}

		if (usuario.getLogin() == null || usuario.getLogin().trim().isEmpty()) {
			errors.add(new ValidationError("login", "Debe especificar un login de acceso al sistema."));
		}

		if (usuariosRepository.get(usuario.getLogin()) == null) {
			errors.add(new ValidationError("login", "El usuario no existe en la base de datos."));
		}

		if (errors.size() > 0) {
			throw new BusinessException("Datos de usuario invalidos.", errors);
		}
	}

	/**
	 * Valida el cambio de contrase�a.
	 * 
	 * @param login
	 * @param passwordViejo
	 * @param passwordNuevo
	 */
	public void validarCambioPassword(String login, String passwordViejo, String passwordNuevo) {
		List<ValidationError> errors = new ArrayList<ValidationError>();

		Usuario usr = usuariosRepository.get(login);
		if (usr == null) {
			errors.add(new ValidationError("login", "El usuario no existe en la base de datos."));
		}

		String pw = CryptoUtil.createPasswordHash("MD5", "Base64", "UTF-8", usr.getLogin(), passwordViejo);

		if (!pw.equals(usr.getPassword())) {
			errors.add(new ValidationError("passwordViejo", "La contrase�a actual es incorrecta."));
		}

		if (errors.size() > 0) {
			throw new BusinessException("Datos de usuario invalidos.", errors);
		}
	}

	/**
	 * Valida los constraints del entity bean, estas validaciones son internas
	 * del servicio.
	 * 
	 * @param usuarioNuevo
	 * @throws ConstraintViolationException
	 */
	public void validarEntityBeanUsuario(Usuario usuarioNuevo) throws ConstraintViolationException {
		Set<ConstraintViolation<Usuario>> errors = Validation.buildDefaultValidatorFactory().getValidator()
				.validate(usuarioNuevo);
		if (!errors.isEmpty()) {
			throw new ConstraintViolationException("Errores de validacion",
					new HashSet<ConstraintViolation<?>>(errors));
		}
	}

	/**
	 * Valida la desactivacion de un usuario.
	 * 
	 * @param login
	 */
	public void validarActivarUsuario(String login) {
		List<ValidationError> errors = new ArrayList<ValidationError>();

		Usuario usr = usuariosRepository.get(login);
		if (usr == null) {
			errors.add(new ValidationError("login", "El usuario no existe en la base de datos."));
		}

		if (errors.size() > 0) {
			throw new BusinessException("Datos de usuario invalidos.", errors);
		}
	}

	/**
	 * Valida la activacion de un usuario.
	 * 
	 * @param login
	 */
	public void validarDesactivarUsuario(String login) {
		List<ValidationError> errors = new ArrayList<ValidationError>();

		Usuario usr = usuariosRepository.get(login);
		if (usr == null) {
			errors.add(new ValidationError("login", "El usuario no existe en la base de datos."));
		}

		if (errors.size() > 0) {
			throw new BusinessException("Datos de usuario invalidos.", errors);
		}
	}
}

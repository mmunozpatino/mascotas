package com.mascotas.historias.rest;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mascotas.application.dtos.FormError;
import com.mascotas.application.exceptions.BusinessException;
import com.mascotas.historias.dto.HistoriasDTO;
import com.mascotas.historias.entities.Historia;
import com.mascotas.historias.HistoriaService;
import com.mascotas.mascotas.MascotaService;
import com.mascotas.mascotas.dto.MascotaDTO;

@Stateless
@LocalBean
@Path("/historia")
public class HistoriaController {
    @EJB
	HistoriaService historiaService;

	MascotaService mascotaService;

	List<MascotaDTO> mascotasDTOs;

	List<HistoriasDTO> historiasDTOs;

	/**
	 * Busca las mactoas del usuario logueado.
	 * 
	 * @return
	 * @throws NamingException
	 */
	@Path("/")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findAll() throws NamingException {
		try {
			return Response.ok().entity(historiaService.findAll()).build();
		} catch (BusinessException e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		}
	}
	
	
	/**
	 * Busca las mactoas del usuario logueado.
	 * 
	 * @return
	 * @throws NamingException
	 */
	@Path("detalle/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findHistoria(@Context HttpServletRequest httpRequest, @PathParam("id") Integer id) throws NamingException {
		try {
			return Response.ok().entity(historiaService.findById(id)).build();
		} catch (BusinessException e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		}
	}
	
	@Path("/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findHistoriaByMascota(@Context HttpServletRequest httpRequest, @PathParam("id") Integer id) throws NamingException {
		try {
			return Response.ok().entity(historiaService.findByMascota(id)).build();
		} catch (BusinessException e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		}
	}
	/**
	 * Actualiza o agrega una mascota a la base de datos.
	 * 
	 * @return
	 * @throws NamingException
	 */
	
	@Path("/{id}")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response actualizarHistoria(@Context HttpServletRequest httpRequest, 
			@PathParam("id") Integer id, 
			HistoriasDTO historiaDTO) throws NamingException {
		try {
			int historiaId = historiaService.actualizarHistoria(httpRequest.getUserPrincipal().getName(), historiaDTO);
			return Response.ok().entity(historiaService.findById(historiaId)).build();
		} catch (BusinessException e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		}
	}
	/**
	 * Actualiza o agrega una mascota a la base de datos.
	 * 
	 * @return
	 * @throws NamingException
	 */
	@Path("/{id}/{mascotaId}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response actualizarHistoria(@Context HttpServletRequest httpRequest, 
			@PathParam("id") Integer id,
			@PathParam("mascotaId") Integer mascotaId) throws NamingException {
		try {
			historiaService.eliminarHistoria(httpRequest.getUserPrincipal().getName(), id);
			return Response.ok().build();
		} catch (BusinessException e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		}
	}

	/**
	 * Graba una mascota a la base de datos.
	 * 
	 * @return
	 * @throws NamingException
	 */
	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response agregarHistoria(@Context HttpServletRequest httpRequest, HistoriasDTO historiaDTO) throws NamingException {
		try {
			int id = historiaService.actualizarHistoria(httpRequest.getUserPrincipal().getName(), historiaDTO);
			return Response.ok().entity(historiaService.findById(id)).build();
		} catch (BusinessException e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		}
	}
}

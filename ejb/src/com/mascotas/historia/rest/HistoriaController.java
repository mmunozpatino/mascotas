package com.mascotas.historia.rest;

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
import com.mascotas.historia.HistoriaService;
import com.mascotas.historia.dto.HistoriaDTO;

@Stateless
@LocalBean
@Path("/historia")
public class HistoriaController{
	@EJB
	HistoriaService historiaService;
	
	@Path("/")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAll() throws NamingException {
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
	@Path("/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getByMascota( @PathParam("id") Integer id) throws NamingException {
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
	
	@Path("/detalle/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getById(@PathParam("id") Integer id) throws NamingException {
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
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	public Response addHistoria(@PathParam("id") Integer id, HistoriaDTO dto) throws NamingException {
		try {
			historiaService.addHistoria(id, dto);
			return Response.ok().build();
		} catch (BusinessException e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		}
	}
}
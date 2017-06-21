package com.mascotas.especies.rest;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mascotas.application.dtos.FormError;
import com.mascotas.application.exceptions.BusinessException;
import com.mascotas.especies.EspeciesService;

import javax.ws.rs.Consumes;

@Stateless
@LocalBean
@Path("/especies")
public class EspeciesController{
	@EJB
	EspeciesService especiesService;
    @Path("/")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAll() throws NamingException {
		try {
			return Response.ok().entity(especiesService.findAll()).build();
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
	public Response getById(@PathParam("id") Integer id) throws NamingException {
		try {
			return Response.ok().entity(especiesService.findById(id)).build();
		} catch (BusinessException e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		}
	}
}
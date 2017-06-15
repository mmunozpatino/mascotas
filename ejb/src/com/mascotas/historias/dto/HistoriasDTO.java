package com.mascotas.historias.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.mascotas.application.utils.StringUtils;
import com.mascotas.historias.entities.Historia;
import com.mascotas.mascotas.entities.Mascota;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoriasDTO implements Serializable {
    private static final long serialVersionUID = 1L;

	private Integer id;
	private Mascota mascota;
	private String titulo;
	private String fecha;
	private String descripcion;
    private String recordatorio;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
    public String getRecordatorio() {
		return recordatorio;
	}

	public void setRecordatorio(String recordatorio) {
		this.recordatorio = recordatorio;
	}
	
	public Mascota getMascota(){
		return this.mascota;
	}
	
	public void setMascota(Mascota mascota){
		this.mascota = mascota;
	}
	/**
	 * Factorys para crear DTO a partir de Entity
	 * 
	 * @author nestor
	 *
	 */
	public static class Factory {
		public static HistoriasDTO get(Historia historia) {
			HistoriasDTO result = new HistoriasDTO();
			result.id = historia.getId();
			result.titulo = historia.getTitulo();
            result.fecha = StringUtils.DATE_FORMAT.format(historia.getFecha());
			result.descripcion = historia.getDescripcion();
			return result;
		}

		public static List<HistoriasDTO> get(Collection<Historia> historias) {
			ArrayList<HistoriasDTO> result = new ArrayList<HistoriasDTO>();
			for (Historia p : historias) {
				result.add(get(p));
			}
			return result;
		}

	}
}

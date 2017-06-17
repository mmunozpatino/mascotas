package com.mascotas.historia.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.mascotas.application.utils.StringUtils;
import com.mascotas.historia.entities.Historia;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoriaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
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
	
	public static class Factory {
		public static HistoriaDTO get(Historia historia) {
			HistoriaDTO result = new HistoriaDTO();
			result.id = historia.getId();
			result.titulo = historia.getTitulo();
			if (historia.getFecha() != null) {
				result.fecha = StringUtils.DATE_FORMAT.format(historia.getFecha());
			}
			result.descripcion = historia.getDescripcion();
			result.recordatorio = historia.getRecordatorio();
			return result;
		}

		public static List<HistoriaDTO> get(Collection<Historia> historias) {
			ArrayList<HistoriaDTO> result = new ArrayList<HistoriaDTO>();
			for (Historia p : historias) {
				result.add(get(p));
			}
			return result;
		}
	}    
}
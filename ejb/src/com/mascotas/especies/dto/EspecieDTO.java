package com.mascotas.especies.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.mascotas.especies.entities.Especie;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EspecieDTO implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer id;
	private String nombreEspecie;

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

    public String getNombreEspecie() {
		return nombreEspecie;
	}

	public void setNombreEspecie(String especie) {
		this.nombreEspecie = especie;
	}
    public static class Factory {
		public static EspecieDTO get(Especie especie) {
			EspecieDTO result = new EspecieDTO();
			result.id = especie.getId();
			result.nombreEspecie = especie.getNombreEspecie();
			return result;
		}

		public static List<EspecieDTO> get(Collection<Especie> especies) {
			ArrayList<EspecieDTO> result = new ArrayList<EspecieDTO>();
			for (Especie p : especies) {
				result.add(get(p));
			}
			return result;
		}
	}    
}
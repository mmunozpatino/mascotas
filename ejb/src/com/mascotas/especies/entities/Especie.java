package com.mascotas.especies.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="especie")
public class Especie implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    @OrderColumn()
	@Column(nullable = false)
	private String nombreEspecie;

    public Integer getId(){
        return id;
    }
    public void setId(Integer id) {
		this.id = id;
	}
    public String getNombreEspecie() {
		return nombreEspecie;
	}

	public void setNombreEspecie(String nombre) {
		this.nombreEspecie = nombre;
	}
}
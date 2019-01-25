package com.alptekintalan.model.pojo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.transaction.Transactional;

@Entity
@Table(name = "Sube")
@Transactional
public class Sube implements Serializable {

	private static final long serialVersionUID = 2439160595430263006L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long subeId;

	@Column
	private String subeAdi;

	@Column
	private String subeAdresi;

	@Column
	private String subeTelefon;

	@Column
	private String subeFaks;

	@Column
	private String subeWebSitesi;

	@Column
	private String subeEposta;

	public Sube(long subeId, String subeAdi, String subeAdresi, String subeTelefon, String subeFaks,
			String subeWebSitesi, String subeEposta) {
		super();
		this.subeId = subeId;
		this.subeAdi = subeAdi;
		this.subeAdresi = subeAdresi;
		this.subeTelefon = subeTelefon;
		this.subeFaks = subeFaks;
		this.subeWebSitesi = subeWebSitesi;
		this.subeEposta = subeEposta;
	}

	public Sube() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getSubeId() {
		return subeId;
	}

	public void setSubeId(long subeId) {
		this.subeId = subeId;
	}

	public String getSubeAdi() {
		return subeAdi;
	}

	public void setSubeAdi(String subeAdi) {
		this.subeAdi = subeAdi;
	}

	public String getSubeAdresi() {
		return subeAdresi;
	}

	public void setSubeAdresi(String subeAdresi) {
		this.subeAdresi = subeAdresi;
	}

	public String getSubeTelefon() {
		return subeTelefon;
	}

	public void setSubeTelefon(String subeTelefon) {
		this.subeTelefon = subeTelefon;
	}

	public String getSubeFaks() {
		return subeFaks;
	}

	public void setSubeFaks(String subeFaks) {
		this.subeFaks = subeFaks;
	}

	public String getSubeWebSitesi() {
		return subeWebSitesi;
	}

	public void setSubeWebSitesi(String subeWebSitesi) {
		this.subeWebSitesi = subeWebSitesi;
	}

	public String getSubeEposta() {
		return subeEposta;
	}

	public void setSubeEposta(String subeEposta) {
		this.subeEposta = subeEposta;
	}

	@Override
	public String toString() {
		return "Sube [subeId=" + subeId + ", subeAdi=" + subeAdi + ", subeAdresi=" + subeAdresi + ", subeTelefon="
				+ subeTelefon + ", subeFaks=" + subeFaks + ", subeWebSitesi=" + subeWebSitesi + ", subeEposta="
				+ subeEposta + "]";
	}

}

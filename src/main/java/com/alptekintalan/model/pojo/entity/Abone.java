package com.alptekintalan.model.pojo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Abone")
public class Abone implements Serializable {

	private static final long serialVersionUID = -1465840286802545221L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long aboneId;

	@ManyToOne
	@JoinColumn(name = "sube_subeId")
	private Sube sube;

	@Column
	private String aboneAdi;

	@Column
	private String aboneSoyadi;

	@Column
	private String aboneEposta;

	@Column
	private boolean aktif;

	@Temporal(TemporalType.DATE)
	@Column
	private Date kayitTarihi;

	@Column
	private boolean hatali;

	public Abone() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Abone(long aboneId, Sube sube, String aboneAdi, String aboneSoyadi, String aboneEposta, boolean aktif,
			Date kayitTarihi, boolean hatali) {
		super();
		this.aboneId = aboneId;
		this.sube = sube;
		this.aboneAdi = aboneAdi;
		this.aboneSoyadi = aboneSoyadi;
		this.aboneEposta = aboneEposta;
		this.aktif = aktif;
		this.kayitTarihi = kayitTarihi;
		this.hatali = hatali;
	}

	public long getAboneId() {
		return aboneId;
	}

	public void setAboneId(long aboneId) {
		this.aboneId = aboneId;
	}

	public Sube getSube() {
		return sube;
	}

	public void setSube(Sube sube) {
		this.sube = sube;
	}

	public String getAboneAdi() {
		return aboneAdi;
	}

	public void setAboneAdi(String aboneAdi) {
		this.aboneAdi = aboneAdi;
	}

	public String getAboneSoyadi() {
		return aboneSoyadi;
	}

	public void setAboneSoyadi(String aboneSoyadi) {
		this.aboneSoyadi = aboneSoyadi;
	}

	public String getAboneEposta() {
		return aboneEposta;
	}

	public void setAboneEposta(String aboneEposta) {
		this.aboneEposta = aboneEposta;
	}

	public boolean isAktif() {
		return aktif;
	}

	public void setAktif(boolean aktif) {
		this.aktif = aktif;
	}

	public Date getKayitTarihi() {
		return kayitTarihi;
	}

	public void setKayitTarihi(Date kayitTarihi) {
		this.kayitTarihi = kayitTarihi;
	}

	public boolean isHatali() {
		return hatali;
	}

	public void setHatali(boolean hatali) {
		this.hatali = hatali;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
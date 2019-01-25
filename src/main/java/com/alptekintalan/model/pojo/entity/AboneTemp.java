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
@Table(name = "abonetemp")
public class AboneTemp implements Serializable {

	private static final long serialVersionUID = -1465840286802545221L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long aboneTempId;

	@ManyToOne
	@JoinColumn(name = "sube_subeId")
	private Sube sube;

	@Column
	private String aboneTempAdi;

	@Column
	private String aboneTempSoyadi;

	@Column
	private String aboneTempEposta;

	@Temporal(TemporalType.DATE)
	@Column
	private Date kayitTarihi;

	@Column
	private String aboneTempKey;

	public AboneTemp() {
		super();
	}

	public AboneTemp(long aboneTempId, Sube sube, String aboneTempAdi, String aboneTempSoyadi, String aboneTempEposta, Date kayitTarihi,
			String aboneTempKey) {
		super();
		this.aboneTempId = aboneTempId;
		this.sube = sube;
		this.aboneTempAdi = aboneTempAdi;
		this.aboneTempSoyadi = aboneTempSoyadi;
		this.aboneTempEposta = aboneTempEposta;
		this.kayitTarihi = kayitTarihi;
		this.aboneTempKey = aboneTempKey;
	}

	public long getAboneTempId() {
		return aboneTempId;
	}

	public void setAboneTempId(long aboneTempId) {
		this.aboneTempId = aboneTempId;
	}

	public Sube getSube() {
		return sube;
	}

	public void setSube(Sube sube) {
		this.sube = sube;
	}

	public String getAboneTempAdi() {
		return aboneTempAdi;
	}

	public void setAboneTempAdi(String aboneTempAdi) {
		this.aboneTempAdi = aboneTempAdi;
	}

	public String getAboneTempSoyadi() {
		return aboneTempSoyadi;
	}

	public void setAboneTempSoyadi(String aboneTempSoyadi) {
		this.aboneTempSoyadi = aboneTempSoyadi;
	}

	public String getAboneTempEposta() {
		return aboneTempEposta;
	}

	public void setAboneTempEposta(String aboneTempEposta) {
		this.aboneTempEposta = aboneTempEposta;
	}

	public Date getKayitTarihi() {
		return kayitTarihi;
	}

	public void setKayitTarihi(Date kayitTarihi) {
		this.kayitTarihi = kayitTarihi;
	}

	public String getAboneTempKey() {
		return aboneTempKey;
	}

	public void setAboneTempKey(String aboneTempKey) {
		this.aboneTempKey = aboneTempKey;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
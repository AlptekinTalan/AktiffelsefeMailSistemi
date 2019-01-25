package com.alptekintalan.model.pojo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.transaction.Transactional;

@Entity
@Table(name = "Devamlilik")
@Transactional
public class Devamlilik implements Serializable {

	private static final long serialVersionUID = -8411078596711421053L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long devamlilikId;

	@ManyToOne // M-1 COKTAN BIRE BAGLANTI
	@JoinColumn(name = "yoklama_yoklamaId")
	private Yoklama yoklama;

	@ManyToOne // M-1 COKTAN BIRE BAGLANTI
	@JoinColumn(name = "ozeletkinlik_etkinlikId")
	private OzelEtkinlik ozeletkinlik;

	@ManyToOne
	@JoinColumn(name = "uye_uyeId")
	private Uye uye;

	@Column
	private boolean var;

	public Devamlilik() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Devamlilik(long devamlilikId, Yoklama yoklama, OzelEtkinlik ozeletkinlik, Uye uye, boolean var) {
		super();
		this.devamlilikId = devamlilikId;
		this.yoklama = yoklama;
		this.ozeletkinlik = ozeletkinlik;
		this.uye = uye;
		this.var = var;
	}

	public long getDevamlilikId() {
		return devamlilikId;
	}

	public void setDevamlilikId(long devamlilikId) {
		this.devamlilikId = devamlilikId;
	}

	public Yoklama getYoklama() {
		return yoklama;
	}

	public void setYoklama(Yoklama yoklama) {
		this.yoklama = yoklama;
	}

	public OzelEtkinlik getOzeletkinlik() {
		return ozeletkinlik;
	}

	public void setOzeletkinlik(OzelEtkinlik ozeletkinlik) {
		this.ozeletkinlik = ozeletkinlik;
	}

	public Uye getUye() {
		return uye;
	}

	public void setUye(Uye uye) {
		this.uye = uye;
	}

	public boolean isVar() {
		return var;
	}

	public void setVar(boolean var) {
		this.var = var;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Devamlilik [devamlilikId=" + devamlilikId + ", yoklama=" + yoklama + ", ozeletkinlik=" + ozeletkinlik
				+ ", uye=" + uye + ", var=" + var + "]";
	}

}

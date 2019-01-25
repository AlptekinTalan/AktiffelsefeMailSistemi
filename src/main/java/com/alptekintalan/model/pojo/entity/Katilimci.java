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
@Table(name = "Katilimci")
@Transactional
public class Katilimci implements Serializable {

	private static final long serialVersionUID = -8411078596711421053L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long katilimciId;

	@Column
	private String katilimciAdi;

	@ManyToOne // M-1 COKTAN BIRE BAGLANTI
	@JoinColumn(name = "etkinlik_etkinlikId")
	private Etkinlik etkinlik;

	@ManyToOne // M-1 COKTAN BIRE BAGLANTI
	@JoinColumn(name = "ozeletkinlik_etkinlikId")
	private OzelEtkinlik ozeletkinlik;

	@ManyToOne
	@JoinColumn(name = "uye_uyeId")
	private Uye uye;

	public Katilimci() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Katilimci(long katilimciId, String katilimciAdi, Etkinlik etkinlik, OzelEtkinlik ozeletkinlik, Uye uye) {
		super();
		this.katilimciId = katilimciId;
		this.katilimciAdi = katilimciAdi;
		this.etkinlik = etkinlik;
		this.ozeletkinlik = ozeletkinlik;
		this.uye = uye;
	}

	public long getKatilimciId() {
		return katilimciId;
	}

	public void setKatilimciId(long katilimciId) {
		this.katilimciId = katilimciId;
	}

	public String getKatilimciAdi() {
		return katilimciAdi;
	}

	public void setKatilimciAdi(String katilimciAdi) {
		this.katilimciAdi = katilimciAdi;
	}

	public Etkinlik getEtkinlik() {
		return etkinlik;
	}

	public void setEtkinlik(Etkinlik etkinlik) {
		this.etkinlik = etkinlik;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Katilimci [katilimciId=" + katilimciId + ", katilimciAdi=" + katilimciAdi + ", etkinlik=" + etkinlik
				+ ", ozeletkinlik=" + ozeletkinlik + ", uye=" + uye + "]";
	}

}

package com.alptekintalan.model.pojo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.transaction.Transactional;

@Entity
@Table(name = "Egitmen")
@Transactional
public class Egitmen implements Serializable {

	private static final long serialVersionUID = -8411078596711421053L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long egitmenId;

	@ManyToOne // M-1 COKTAN BIRE BAGLANTI
	@JoinColumn(name = "etkinlik_etkinlikId")
	private Etkinlik etkinlik;

	@ManyToOne // M-1 COKTAN BIRE BAGLANTI
	@JoinColumn(name = "ozeletkinlik_etkinlikId")
	private OzelEtkinlik ozeletkinlik;

	@ManyToOne
	@JoinColumn(name = "uye_uyeId")
	private Uye uye;

	public Egitmen() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Egitmen(long egitmenId, Etkinlik etkinlik, OzelEtkinlik ozeletkinlik, Uye uye) {
		super();
		this.egitmenId = egitmenId;
		this.etkinlik = etkinlik;
		this.ozeletkinlik = ozeletkinlik;
		this.uye = uye;
	}

	public long getEgitmenId() {
		return egitmenId;
	}

	public void setEgitmenId(long egitmenId) {
		this.egitmenId = egitmenId;
	}

	public Etkinlik getEtkinlik() {
		return etkinlik;
	}

	public void setEtkinlik(Etkinlik etkinlik) {
		this.etkinlik = etkinlik;
	}

	public OzelEtkinlik getOzelEtkinlik() {
		return ozeletkinlik;
	}

	public void setOzelEtkinlik(OzelEtkinlik ozeletkinlik) {
		this.ozeletkinlik = ozeletkinlik;
	}

	public Uye getUye() {
		return uye;
	}

	public void setUye(Uye uye) {
		this.uye = uye;
	}

}

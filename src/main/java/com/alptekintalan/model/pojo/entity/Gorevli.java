package com.alptekintalan.model.pojo.entity;

import java.io.Serializable;

import javax.persistence.Column;
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
@Table(name = "Gorevli")
@Transactional
public class Gorevli implements Serializable {

	private static final long serialVersionUID = -8411078596711421053L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long gorevliId;

	@ManyToOne // M-1 COKTAN BIRE BAGLANTI
	@JoinColumn(name = "etkinlik_etkinlikId")
	private Etkinlik etkinlik;

	@ManyToOne // M-1 COKTAN BIRE BAGLANTI
	@JoinColumn(name = "ozeletkinlik_etkinlikId")
	private OzelEtkinlik ozeletkinlik;

	@ManyToOne
	@JoinColumn(name = "uye_uyeId")
	private Uye uye;

	public Gorevli() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Gorevli(long gorevliId, Etkinlik etkinlik, OzelEtkinlik ozeletkinlik, Uye uye) {
		super();
		this.gorevliId = gorevliId;
		this.etkinlik = etkinlik;
		this.ozeletkinlik = ozeletkinlik;
		this.uye = uye;
	}

	public long getGorevliId() {
		return gorevliId;
	}

	public void setGorevliId(long gorevliId) {
		this.gorevliId = gorevliId;
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

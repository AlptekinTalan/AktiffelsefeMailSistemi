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
import javax.transaction.Transactional;

@Entity
@Table(name = "Yoklama")
@Transactional
public class Yoklama implements Serializable {

	private static final long serialVersionUID = -8411078596711421053L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long yoklamaId;

	@Temporal(TemporalType.DATE)
	@Column
	private Date yoklamaTarihi;

	@Column
	private String yoklamaAdi;

	@Column
	private String yoklamaDersAdi;

	@ManyToOne // M-1 COKTAN BIRE BAGLANTI
	@JoinColumn(name = "ozeletkinlik_etkinlikId")
	private OzelEtkinlik ozeletkinlik;

	public Yoklama() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Yoklama(long yoklamaId, Date yoklamaTarihi, String yoklamaAdi, String yoklamaDersAdi,
			OzelEtkinlik ozeletkinlik) {
		super();
		this.yoklamaId = yoklamaId;
		this.yoklamaTarihi = yoklamaTarihi;
		this.yoklamaAdi = yoklamaAdi;
		this.yoklamaDersAdi = yoklamaDersAdi;
		this.ozeletkinlik = ozeletkinlik;
	}

	public long getYoklamaId() {
		return yoklamaId;
	}

	public void setYoklamaId(long yoklamaId) {
		this.yoklamaId = yoklamaId;
	}

	public Date getYoklamaTarihi() {
		return yoklamaTarihi;
	}

	public void setYoklamaTarihi(Date yoklamaTarihi) {
		this.yoklamaTarihi = yoklamaTarihi;
	}

	public String getYoklamaAdi() {
		return yoklamaAdi;
	}

	public void setYoklamaAdi(String yoklamaAdi) {
		this.yoklamaAdi = yoklamaAdi;
	}

	public String getYoklamaDersAdi() {
		return yoklamaDersAdi;
	}

	public void setYoklamaDersAdi(String yoklamaDersAdi) {
		this.yoklamaDersAdi = yoklamaDersAdi;
	}

	public OzelEtkinlik getOzeletkinlik() {
		return ozeletkinlik;
	}

	public void setOzeletkinlik(OzelEtkinlik ozeletkinlik) {
		this.ozeletkinlik = ozeletkinlik;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Yoklama [yoklamaId=" + yoklamaId + ", yoklamaTarihi=" + yoklamaTarihi + ", yoklamaAdi=" + yoklamaAdi
				+ ", yoklamaDersAdi=" + yoklamaDersAdi + ", ozeletkinlik=" + ozeletkinlik + "]";
	}

}

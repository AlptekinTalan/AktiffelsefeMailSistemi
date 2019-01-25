package com.alptekintalan.model.pojo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "ozeletkinlik")
@Transactional
public class OzelEtkinlik implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long etkinlikId;

	@ManyToOne
	@JoinColumn(name = "sube_subeId")
	private Sube sube;

	@Column
	private boolean etkinlikBildir;

	@Column
	private int etkinlikSeviye;

	@Column
	@Type(type = "text")
	private String etkinlikMailIcerik;

	@Column
	private String etkinlikAdi;

	@Temporal(TemporalType.DATE)
	@Column
	private Date etkinlikTarihi;

	@Column
	private String etkinlikSaati;

	@Column
	private String etkinlikAdresi;

	@Column
	private String etkinlikTuru;

	@Column
	private String tsEtkinlikTuru;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "ozeletkinlik", cascade = CascadeType.ALL)
	private Collection<Katilimci> katilimci = new ArrayList<Katilimci>();

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "ozeletkinlik", cascade = CascadeType.ALL)
	private Collection<Egitmen> egitmen = new ArrayList<Egitmen>();

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "ozeletkinlik", cascade = CascadeType.ALL)
	private Collection<Gorevli> gorevli = new ArrayList<Gorevli>();

	public OzelEtkinlik() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OzelEtkinlik(long etkinlikId, Sube sube, boolean etkinlikBildir, int etkinlikSeviye,
			String etkinlikMailIcerik, String etkinlikAdi, Date etkinlikTarihi, String etkinlikSaati,
			String etkinlikAdresi, String etkinlikTuru, String tsEtkinlikTuru, Collection<Katilimci> katilimci,
			Collection<Egitmen> egitmen, Collection<Gorevli> gorevli) {
		super();
		this.etkinlikId = etkinlikId;
		this.sube = sube;
		this.etkinlikBildir = etkinlikBildir;
		this.etkinlikSeviye = etkinlikSeviye;
		this.etkinlikMailIcerik = etkinlikMailIcerik;
		this.etkinlikAdi = etkinlikAdi;
		this.etkinlikTarihi = etkinlikTarihi;
		this.etkinlikSaati = etkinlikSaati;
		this.etkinlikAdresi = etkinlikAdresi;
		this.etkinlikTuru = etkinlikTuru;
		this.tsEtkinlikTuru = tsEtkinlikTuru;
		this.katilimci = katilimci;
		this.egitmen = egitmen;
		this.gorevli = gorevli;
	}

	public long getEtkinlikId() {
		return etkinlikId;
	}

	public void setEtkinlikId(long etkinlikId) {
		this.etkinlikId = etkinlikId;
	}

	public Sube getSube() {
		return sube;
	}

	public void setSube(Sube sube) {
		this.sube = sube;
	}

	public boolean isEtkinlikBildir() {
		return etkinlikBildir;
	}

	public void setEtkinlikBildir(boolean etkinlikBildir) {
		this.etkinlikBildir = etkinlikBildir;
	}

	public int getEtkinlikSeviye() {
		return etkinlikSeviye;
	}

	public void setEtkinlikSeviye(int etkinlikSeviye) {
		this.etkinlikSeviye = etkinlikSeviye;
	}

	public String getEtkinlikMailIcerik() {
		return etkinlikMailIcerik;
	}

	public void setEtkinlikMailIcerik(String etkinlikMailIcerik) {
		this.etkinlikMailIcerik = etkinlikMailIcerik;
	}

	public String getEtkinlikAdi() {
		return etkinlikAdi;
	}

	public void setEtkinlikAdi(String etkinlikAdi) {
		this.etkinlikAdi = etkinlikAdi;
	}

	public Date getEtkinlikTarihi() {
		return etkinlikTarihi;
	}

	public void setEtkinlikTarihi(Date etkinlikTarihi) {
		this.etkinlikTarihi = etkinlikTarihi;
	}

	public String getEtkinlikSaati() {
		return etkinlikSaati;
	}

	public void setEtkinlikSaati(String etkinlikSaati) {
		this.etkinlikSaati = etkinlikSaati;
	}

	public String getEtkinlikAdresi() {
		return etkinlikAdresi;
	}

	public void setEtkinlikAdresi(String etkinlikAdresi) {
		this.etkinlikAdresi = etkinlikAdresi;
	}

	public String getEtkinlikTuru() {
		return etkinlikTuru;
	}

	public void setEtkinlikTuru(String etkinlikTuru) {
		this.etkinlikTuru = etkinlikTuru;
	}

	public String getTsEtkinlikTuru() {
		return tsEtkinlikTuru;
	}

	public void setTsEtkinlikTuru(String tsEtkinlikTuru) {
		this.tsEtkinlikTuru = tsEtkinlikTuru;
	}

	public Collection<Katilimci> getKatilimci() {
		return katilimci;
	}

	public void setKatilimci(Collection<Katilimci> katilimci) {
		this.katilimci = katilimci;
	}

	public Collection<Egitmen> getEgitmen() {
		return egitmen;
	}

	public void setEgitmen(Collection<Egitmen> egitmen) {
		this.egitmen = egitmen;
	}

	public Collection<Gorevli> getGorevli() {
		return gorevli;
	}

	public void setGorevli(Collection<Gorevli> gorevli) {
		this.gorevli = gorevli;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "OzelEtkinlik [etkinlikId=" + etkinlikId + ", sube=" + sube + ", etkinlikBildir=" + etkinlikBildir
				+ ", etkinlikSeviye=" + etkinlikSeviye + ", etkinlikMailIcerik=" + etkinlikMailIcerik + ", etkinlikAdi="
				+ etkinlikAdi + ", etkinlikTarihi=" + etkinlikTarihi + ", etkinlikSaati=" + etkinlikSaati
				+ ", etkinlikAdresi=" + etkinlikAdresi + ", etkinlikTuru=" + etkinlikTuru + ", tsEtkinlikTuru="
				+ tsEtkinlikTuru + ", katilimci=" + katilimci + ", egitmen=" + egitmen + ", gorevli=" + gorevli + "]";
	}

}

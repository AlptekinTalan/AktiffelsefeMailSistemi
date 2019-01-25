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
@Table(name = "Mail")
@Transactional
public class Mail implements Serializable {

	private static final long serialVersionUID = 2439160595430263006L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long mailId;

	@ManyToOne
	@JoinColumn(name = "etkinlik_etkinlikId")
	private Etkinlik etkinlik;

	@ManyToOne
	@JoinColumn(name = "abone_aboneId")
	private Abone abone;

	@ManyToOne
	@JoinColumn(name = "sube_subeId")
	private Sube sube;

	@Column
	private boolean mailGonderildi;

	@Temporal(TemporalType.DATE)
	@Column
	private Date mailGonderilmeTarihi;

	@Column
	private boolean mailGonderilemedi;

	@Temporal(TemporalType.DATE)
	@Column
	private Date mailGonderilememeTarihi;

	@Column
	private String mailKey;

	public Mail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mail(long mailId, Etkinlik etkinlik, Abone abone, Sube sube, boolean mailGonderildi,
			Date mailGonderilmeTarihi, boolean mailGonderilemedi, Date mailGonderilememeTarihi, String mailKey) {
		super();
		this.mailId = mailId;
		this.etkinlik = etkinlik;
		this.abone = abone;
		this.sube = sube;
		this.mailGonderildi = mailGonderildi;
		this.mailGonderilmeTarihi = mailGonderilmeTarihi;
		this.mailGonderilemedi = mailGonderilemedi;
		this.mailGonderilememeTarihi = mailGonderilememeTarihi;
		this.mailKey = mailKey;
	}

	public long getMailId() {
		return mailId;
	}

	public void setMailId(long mailId) {
		this.mailId = mailId;
	}

	public Etkinlik getEtkinlik() {
		return etkinlik;
	}

	public void setEtkinlik(Etkinlik etkinlik) {
		this.etkinlik = etkinlik;
	}

	public Abone getAbone() {
		return abone;
	}

	public void setAbone(Abone abone) {
		this.abone = abone;
	}

	public Sube getSube() {
		return sube;
	}

	public void setSube(Sube sube) {
		this.sube = sube;
	}

	public boolean isMailGonderildi() {
		return mailGonderildi;
	}

	public void setMailGonderildi(boolean mailGonderildi) {
		this.mailGonderildi = mailGonderildi;
	}

	public boolean isMailGonderilemedi() {
		return mailGonderilemedi;
	}

	public void setMailGonderilemedi(boolean mailGonderilemedi) {
		this.mailGonderilemedi = mailGonderilemedi;
	}

	public Date getMailGonderilmeTarihi() {
		return mailGonderilmeTarihi;
	}

	public void setMailGonderilmeTarihi(Date mailGonderilmeTarihi) {
		this.mailGonderilmeTarihi = mailGonderilmeTarihi;
	}

	public Date getMailGonderilememeTarihi() {
		return mailGonderilememeTarihi;
	}

	public void setMailGonderilememeTarihi(Date mailGonderilememeTarihi) {
		this.mailGonderilememeTarihi = mailGonderilememeTarihi;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMailKey() {
		return mailKey;
	}

	public void setMailKey(String mailKey) {
		this.mailKey = mailKey;
	}

	@Override
	public String toString() {
		return "Mail [mailId=" + mailId + ", etkinlik=" + etkinlik + ", abone=" + abone + ", sube=" + sube
				+ ", mailGonderildi=" + mailGonderildi + ", mailGonderilmeTarihi=" + mailGonderilmeTarihi
				+ ", mailGonderilemedi=" + mailGonderilemedi + ", mailGonderilememeTarihi=" + mailGonderilememeTarihi
				+ ", mailKey=" + mailKey + "]";
	}

}

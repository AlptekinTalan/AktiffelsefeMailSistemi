package com.alptekintalan.model.pojo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "Uye")
public class Uye implements Serializable {

	private static final long serialVersionUID = -1465840286802545221L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uyeId;

	@ManyToOne
	@JoinColumn(name = "sube_subeId")
	private Sube sube;

	@Column
	private int uyeSeviye;

	@Column
	@NotEmpty(message = "Üye Adý Boþ Geçilemez!")
	private String uyeAdi;

	@Column
	@NotEmpty(message = "Üye Soyadý Boþ Geçilemez!")
	private String uyeSoyadi;

	@Column
	private String uyeTelEv;

	@Column
	private String uyeTelIs;

	@Column
	private String uyeTelCep;

	@Column
	private String ePosta;

	@Temporal(TemporalType.DATE)
	@Column
	private Date dogumTarihi;

	@Column
	private String meslek;

	@Column
	private String adres;

	@Temporal(TemporalType.DATE)
	@Column
	private Date kayitTarihi;

	@Column
	private boolean aktif;

	@Column
	private String durum;

	@Column
	@Basic(fetch = FetchType.LAZY)
	@Lob
	private byte[] foto;

	// ÝLGÝLÝ ALANLAR
	@Column
	private boolean ilg_konferans;
	@Column
	private boolean ilg_ekoloji;
	@Column
	private boolean ilg_sanat;
	@Column
	private boolean ilg_yardim;
	@Column
	private boolean ilg_deprem;
	@Column
	private boolean ilg_uzakdogu;
	@Column
	private boolean ilg_tiyatro;
	@Column
	private boolean ilg_fotograf;
	@Column
	private boolean ilg_geziler;
	@Column
	private boolean ilg_muzik;
	@Column
	private boolean ilg_dans;
	@Column
	private String ilg_diger;

	// HABERDAR OLUNMA YÖNTEMLERÝ
	@Column
	private boolean hbr_ePosat;
	@Column
	private boolean hbr_webSitesi;
	@Column
	private boolean hbr_facebook;
	@Column
	private boolean hbr_brosur;
	@Column
	private boolean hbr_afis;
	@Column
	private boolean hbr_arkadas;
	@Column
	private boolean hbr_sergi;
	@Column
	private boolean hbr_digerInternet;
	@Column
	private boolean hbr_yaziliBasin;
	@Column
	private String hbr_diger;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "etkinlik", cascade = CascadeType.ALL)
	private Collection<Katilimci> katilimci = new ArrayList<Katilimci>();

	@Column
	private String uyeStatu;

	@Column
	private String uyeGelisSekli;

	@Column
	private String uyeGelisSekliDiger;

	@Column
	private String uyeKanGrubu;

	@Column
	private String uyeTcNo;

	@Column
	private String uyeIlce;

	@Temporal(TemporalType.DATE)
	@Column
	private Date gelisTarihi;

	@Column
	@Type(type = "text")
	private String uyeOzelNot;

	@Temporal(TemporalType.DATE)
	@Column
	private Date ziyaretciTarihi;

	@Temporal(TemporalType.DATE)
	@Column
	private Date uyeTarihi;

	@Temporal(TemporalType.DATE)
	@Column
	private Date onSekreterTarihi;

	@Temporal(TemporalType.DATE)
	@Column
	private Date yonetimKuruluUyesiAdayiTarihi;

	@Temporal(TemporalType.DATE)
	@Column
	private Date yonetimKuruluUyesiTarihi;

	@Temporal(TemporalType.DATE)
	@Column
	private Date sekreterTarihi;

	@Temporal(TemporalType.DATE)
	@Column
	private Date egitmenTarihi;

	@Temporal(TemporalType.DATE)
	@Column
	private Date subeBaskanYardimcisiTarihi;

	@Temporal(TemporalType.DATE)
	@Column
	private Date subeBaskaniTarihi;

	@Temporal(TemporalType.DATE)
	@Column
	private Date ziyaretciTerkTarihi;

	@Temporal(TemporalType.DATE)
	@Column
	private Date uyeTerkTarihi;

	@Temporal(TemporalType.DATE)
	@Column
	private Date onSekreterTerkTarihi;

	@Temporal(TemporalType.DATE)
	@Column
	private Date yonetimKuruluUyesiAdayiTerkTarihi;

	@Temporal(TemporalType.DATE)
	@Column
	private Date yonetimKuruluUyesiTerkTarihi;

	@Temporal(TemporalType.DATE)
	@Column
	private Date sekreterTerkTarihi;

	@Temporal(TemporalType.DATE)
	@Column
	private Date egitmenTerkTarihi;

	@Temporal(TemporalType.DATE)
	@Column
	private Date subeBaskanYardimcisiTerkTarihi;

	@Temporal(TemporalType.DATE)
	@Column
	private Date subeBaskaniTerkTarihi;

	public Uye() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Uye(long uyeId, Sube sube, int uyeSeviye, String uyeAdi, String uyeSoyadi, String uyeTelEv, String uyeTelIs,
			String uyeTelCep, String ePosta, Date dogumTarihi, String meslek, String adres, Date kayitTarihi,
			boolean aktif, String durum, byte[] foto, boolean ilg_konferans, boolean ilg_ekoloji, boolean ilg_sanat,
			boolean ilg_yardim, boolean ilg_deprem, boolean ilg_uzakdogu, boolean ilg_tiyatro, boolean ilg_fotograf,
			boolean ilg_geziler, boolean ilg_muzik, boolean ilg_dans, String ilg_diger, boolean hbr_ePosat,
			boolean hbr_webSitesi, boolean hbr_facebook, boolean hbr_brosur, boolean hbr_afis, boolean hbr_arkadas,
			boolean hbr_sergi, boolean hbr_digerInternet, boolean hbr_yaziliBasin, String hbr_diger,
			Collection<Katilimci> katilimci, String uyeStatu, String uyeGelisSekli, String uyeGelisSekliDiger,
			String uyeKanGrubu, String uyeTcNo, String uyeIlce, Date gelisTarihi, String uyeOzelNot,
			Date ziyaretciTarihi, Date uyeTarihi, Date onSekreterTarihi, Date yonetimKuruluUyesiAdayiTarihi,
			Date yonetimKuruluUyesiTarihi, Date sekreterTarihi, Date egitmenTarihi, Date subeBaskanYardimcisiTarihi,
			Date subeBaskaniTarihi, Date ziyaretciTerkTarihi, Date uyeTerkTarihi, Date onSekreterTerkTarihi,
			Date yonetimKuruluUyesiAdayiTerkTarihi, Date yonetimKuruluUyesiTerkTarihi, Date sekreterTerkTarihi,
			Date egitmenTerkTarihi, Date subeBaskanYardimcisiTerkTarihi, Date subeBaskaniTerkTarihi) {
		super();
		this.uyeId = uyeId;
		this.sube = sube;
		this.uyeSeviye = uyeSeviye;
		this.uyeAdi = uyeAdi;
		this.uyeSoyadi = uyeSoyadi;
		this.uyeTelEv = uyeTelEv;
		this.uyeTelIs = uyeTelIs;
		this.uyeTelCep = uyeTelCep;
		this.ePosta = ePosta;
		this.dogumTarihi = dogumTarihi;
		this.meslek = meslek;
		this.adres = adres;
		this.kayitTarihi = kayitTarihi;
		this.aktif = aktif;
		this.durum = durum;
		this.foto = foto;
		this.ilg_konferans = ilg_konferans;
		this.ilg_ekoloji = ilg_ekoloji;
		this.ilg_sanat = ilg_sanat;
		this.ilg_yardim = ilg_yardim;
		this.ilg_deprem = ilg_deprem;
		this.ilg_uzakdogu = ilg_uzakdogu;
		this.ilg_tiyatro = ilg_tiyatro;
		this.ilg_fotograf = ilg_fotograf;
		this.ilg_geziler = ilg_geziler;
		this.ilg_muzik = ilg_muzik;
		this.ilg_dans = ilg_dans;
		this.ilg_diger = ilg_diger;
		this.hbr_ePosat = hbr_ePosat;
		this.hbr_webSitesi = hbr_webSitesi;
		this.hbr_facebook = hbr_facebook;
		this.hbr_brosur = hbr_brosur;
		this.hbr_afis = hbr_afis;
		this.hbr_arkadas = hbr_arkadas;
		this.hbr_sergi = hbr_sergi;
		this.hbr_digerInternet = hbr_digerInternet;
		this.hbr_yaziliBasin = hbr_yaziliBasin;
		this.hbr_diger = hbr_diger;
		this.katilimci = katilimci;
		this.uyeStatu = uyeStatu;
		this.uyeGelisSekli = uyeGelisSekli;
		this.uyeGelisSekliDiger = uyeGelisSekliDiger;
		this.uyeKanGrubu = uyeKanGrubu;
		this.uyeTcNo = uyeTcNo;
		this.uyeIlce = uyeIlce;
		this.gelisTarihi = gelisTarihi;
		this.uyeOzelNot = uyeOzelNot;
		this.ziyaretciTarihi = ziyaretciTarihi;
		this.uyeTarihi = uyeTarihi;
		this.onSekreterTarihi = onSekreterTarihi;
		this.yonetimKuruluUyesiAdayiTarihi = yonetimKuruluUyesiAdayiTarihi;
		this.yonetimKuruluUyesiTarihi = yonetimKuruluUyesiTarihi;
		this.sekreterTarihi = sekreterTarihi;
		this.egitmenTarihi = egitmenTarihi;
		this.subeBaskanYardimcisiTarihi = subeBaskanYardimcisiTarihi;
		this.subeBaskaniTarihi = subeBaskaniTarihi;
		this.ziyaretciTerkTarihi = ziyaretciTerkTarihi;
		this.uyeTerkTarihi = uyeTerkTarihi;
		this.onSekreterTerkTarihi = onSekreterTerkTarihi;
		this.yonetimKuruluUyesiAdayiTerkTarihi = yonetimKuruluUyesiAdayiTerkTarihi;
		this.yonetimKuruluUyesiTerkTarihi = yonetimKuruluUyesiTerkTarihi;
		this.sekreterTerkTarihi = sekreterTerkTarihi;
		this.egitmenTerkTarihi = egitmenTerkTarihi;
		this.subeBaskanYardimcisiTerkTarihi = subeBaskanYardimcisiTerkTarihi;
		this.subeBaskaniTerkTarihi = subeBaskaniTerkTarihi;
	}

	@Override
	public String toString() {
		return "Uye [uyeId=" + uyeId + ", sube=" + sube + ", uyeSeviye=" + uyeSeviye + ", uyeAdi=" + uyeAdi
				+ ", uyeSoyadi=" + uyeSoyadi + ", uyeTelEv=" + uyeTelEv + ", uyeTelIs=" + uyeTelIs + ", uyeTelCep="
				+ uyeTelCep + ", ePosta=" + ePosta + ", dogumTarihi=" + dogumTarihi + ", meslek=" + meslek + ", adres="
				+ adres + ", kayitTarihi=" + kayitTarihi + ", aktif=" + aktif + ", durum=" + durum + ", foto="
				+ Arrays.toString(foto) + ", ilg_konferans=" + ilg_konferans + ", ilg_ekoloji=" + ilg_ekoloji
				+ ", ilg_sanat=" + ilg_sanat + ", ilg_yardim=" + ilg_yardim + ", ilg_deprem=" + ilg_deprem
				+ ", ilg_uzakdogu=" + ilg_uzakdogu + ", ilg_tiyatro=" + ilg_tiyatro + ", ilg_fotograf=" + ilg_fotograf
				+ ", ilg_geziler=" + ilg_geziler + ", ilg_muzik=" + ilg_muzik + ", ilg_dans=" + ilg_dans
				+ ", ilg_diger=" + ilg_diger + ", hbr_ePosat=" + hbr_ePosat + ", hbr_webSitesi=" + hbr_webSitesi
				+ ", hbr_facebook=" + hbr_facebook + ", hbr_brosur=" + hbr_brosur + ", hbr_afis=" + hbr_afis
				+ ", hbr_arkadas=" + hbr_arkadas + ", hbr_sergi=" + hbr_sergi + ", hbr_digerInternet="
				+ hbr_digerInternet + ", hbr_yaziliBasin=" + hbr_yaziliBasin + ", hbr_diger=" + hbr_diger
				+ ", katilimci=" + katilimci + ", uyeStatu=" + uyeStatu + ", uyeGelisSekli=" + uyeGelisSekli
				+ ", uyeGelisSekliDiger=" + uyeGelisSekliDiger + ", uyeKanGrubu=" + uyeKanGrubu + ", uyeTcNo=" + uyeTcNo
				+ ", uyeIlce=" + uyeIlce + ", gelisTarihi=" + gelisTarihi + ", uyeOzelNot=" + uyeOzelNot
				+ ", ziyaretciTarihi=" + ziyaretciTarihi + ", uyeTarihi=" + uyeTarihi + ", onSekreterTarihi="
				+ onSekreterTarihi + ", yonetimKuruluUyesiAdayiTarihi=" + yonetimKuruluUyesiAdayiTarihi
				+ ", yonetimKuruluUyesiTarihi=" + yonetimKuruluUyesiTarihi + ", sekreterTarihi=" + sekreterTarihi
				+ ", egitmenTarihi=" + egitmenTarihi + ", subeBaskanYardimcisiTarihi=" + subeBaskanYardimcisiTarihi
				+ ", subeBaskaniTarihi=" + subeBaskaniTarihi + ", ziyaretciTerkTarihi=" + ziyaretciTerkTarihi
				+ ", uyeTerkTarihi=" + uyeTerkTarihi + ", onSekreterTerkTarihi=" + onSekreterTerkTarihi
				+ ", yonetimKuruluUyesiAdayiTerkTarihi=" + yonetimKuruluUyesiAdayiTerkTarihi
				+ ", yonetimKuruluUyesiTerkTarihi=" + yonetimKuruluUyesiTerkTarihi + ", sekreterTerkTarihi="
				+ sekreterTerkTarihi + ", egitmenTerkTarihi=" + egitmenTerkTarihi + ", subeBaskanYardimcisiTerkTarihi="
				+ subeBaskanYardimcisiTerkTarihi + ", subeBaskaniTerkTarihi=" + subeBaskaniTerkTarihi + "]";
	}

	public long getUyeId() {
		return uyeId;
	}

	public void setUyeId(long uyeId) {
		this.uyeId = uyeId;
	}

	public Sube getSube() {
		return sube;
	}

	public void setSube(Sube sube) {
		this.sube = sube;
	}

	public int getUyeSeviye() {
		return uyeSeviye;
	}

	public void setUyeSeviye(int uyeSeviye) {
		this.uyeSeviye = uyeSeviye;
	}

	public String getUyeAdi() {
		return uyeAdi;
	}

	public void setUyeAdi(String uyeAdi) {
		this.uyeAdi = uyeAdi;
	}

	public String getUyeSoyadi() {
		return uyeSoyadi;
	}

	public void setUyeSoyadi(String uyeSoyadi) {
		this.uyeSoyadi = uyeSoyadi;
	}

	public String getUyeTelEv() {
		return uyeTelEv;
	}

	public void setUyeTelEv(String uyeTelEv) {
		this.uyeTelEv = uyeTelEv;
	}

	public String getUyeTelIs() {
		return uyeTelIs;
	}

	public void setUyeTelIs(String uyeTelIs) {
		this.uyeTelIs = uyeTelIs;
	}

	public String getUyeTelCep() {
		return uyeTelCep;
	}

	public void setUyeTelCep(String uyeTelCep) {
		this.uyeTelCep = uyeTelCep;
	}

	public String getePosta() {
		return ePosta;
	}

	public void setePosta(String ePosta) {
		this.ePosta = ePosta;
	}

	public Date getDogumTarihi() {
		return dogumTarihi;
	}

	public void setDogumTarihi(Date dogumTarihi) {
		this.dogumTarihi = dogumTarihi;
	}

	public String getMeslek() {
		return meslek;
	}

	public void setMeslek(String meslek) {
		this.meslek = meslek;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public Date getKayitTarihi() {
		return kayitTarihi;
	}

	public void setKayitTarihi(Date kayitTarihi) {
		this.kayitTarihi = kayitTarihi;
	}

	public boolean isAktif() {
		return aktif;
	}

	public void setAktif(boolean aktif) {
		this.aktif = aktif;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public boolean isIlg_konferans() {
		return ilg_konferans;
	}

	public void setIlg_konferans(boolean ilg_konferans) {
		this.ilg_konferans = ilg_konferans;
	}

	public boolean isIlg_ekoloji() {
		return ilg_ekoloji;
	}

	public void setIlg_ekoloji(boolean ilg_ekoloji) {
		this.ilg_ekoloji = ilg_ekoloji;
	}

	public boolean isIlg_sanat() {
		return ilg_sanat;
	}

	public void setIlg_sanat(boolean ilg_sanat) {
		this.ilg_sanat = ilg_sanat;
	}

	public boolean isIlg_yardim() {
		return ilg_yardim;
	}

	public void setIlg_yardim(boolean ilg_yardim) {
		this.ilg_yardim = ilg_yardim;
	}

	public boolean isIlg_deprem() {
		return ilg_deprem;
	}

	public void setIlg_deprem(boolean ilg_deprem) {
		this.ilg_deprem = ilg_deprem;
	}

	public boolean isIlg_uzakdogu() {
		return ilg_uzakdogu;
	}

	public void setIlg_uzakdogu(boolean ilg_uzakdogu) {
		this.ilg_uzakdogu = ilg_uzakdogu;
	}

	public boolean isIlg_tiyatro() {
		return ilg_tiyatro;
	}

	public void setIlg_tiyatro(boolean ilg_tiyatro) {
		this.ilg_tiyatro = ilg_tiyatro;
	}

	public boolean isIlg_fotograf() {
		return ilg_fotograf;
	}

	public void setIlg_fotograf(boolean ilg_fotograf) {
		this.ilg_fotograf = ilg_fotograf;
	}

	public boolean isIlg_geziler() {
		return ilg_geziler;
	}

	public void setIlg_geziler(boolean ilg_geziler) {
		this.ilg_geziler = ilg_geziler;
	}

	public boolean isIlg_muzik() {
		return ilg_muzik;
	}

	public void setIlg_muzik(boolean ilg_muzik) {
		this.ilg_muzik = ilg_muzik;
	}

	public boolean isIlg_dans() {
		return ilg_dans;
	}

	public void setIlg_dans(boolean ilg_dans) {
		this.ilg_dans = ilg_dans;
	}

	public String getIlg_diger() {
		return ilg_diger;
	}

	public void setIlg_diger(String ilg_diger) {
		this.ilg_diger = ilg_diger;
	}

	public boolean isHbr_ePosat() {
		return hbr_ePosat;
	}

	public void setHbr_ePosat(boolean hbr_ePosat) {
		this.hbr_ePosat = hbr_ePosat;
	}

	public boolean isHbr_webSitesi() {
		return hbr_webSitesi;
	}

	public void setHbr_webSitesi(boolean hbr_webSitesi) {
		this.hbr_webSitesi = hbr_webSitesi;
	}

	public boolean isHbr_facebook() {
		return hbr_facebook;
	}

	public void setHbr_facebook(boolean hbr_facebook) {
		this.hbr_facebook = hbr_facebook;
	}

	public boolean isHbr_brosur() {
		return hbr_brosur;
	}

	public void setHbr_brosur(boolean hbr_brosur) {
		this.hbr_brosur = hbr_brosur;
	}

	public boolean isHbr_afis() {
		return hbr_afis;
	}

	public void setHbr_afis(boolean hbr_afis) {
		this.hbr_afis = hbr_afis;
	}

	public boolean isHbr_arkadas() {
		return hbr_arkadas;
	}

	public void setHbr_arkadas(boolean hbr_arkadas) {
		this.hbr_arkadas = hbr_arkadas;
	}

	public boolean isHbr_sergi() {
		return hbr_sergi;
	}

	public void setHbr_sergi(boolean hbr_sergi) {
		this.hbr_sergi = hbr_sergi;
	}

	public boolean isHbr_digerInternet() {
		return hbr_digerInternet;
	}

	public void setHbr_digerInternet(boolean hbr_digerInternet) {
		this.hbr_digerInternet = hbr_digerInternet;
	}

	public boolean isHbr_yaziliBasin() {
		return hbr_yaziliBasin;
	}

	public void setHbr_yaziliBasin(boolean hbr_yaziliBasin) {
		this.hbr_yaziliBasin = hbr_yaziliBasin;
	}

	public String getHbr_diger() {
		return hbr_diger;
	}

	public void setHbr_diger(String hbr_diger) {
		this.hbr_diger = hbr_diger;
	}

	public Collection<Katilimci> getKatilimci() {
		return katilimci;
	}

	public void setKatilimci(Collection<Katilimci> katilimci) {
		this.katilimci = katilimci;
	}

	public String getUyeStatu() {
		return uyeStatu;
	}

	public void setUyeStatu(String uyeStatu) {
		this.uyeStatu = uyeStatu;
	}

	public String getUyeGelisSekli() {
		return uyeGelisSekli;
	}

	public void setUyeGelisSekli(String uyeGelisSekli) {
		this.uyeGelisSekli = uyeGelisSekli;
	}

	public String getUyeGelisSekliDiger() {
		return uyeGelisSekliDiger;
	}

	public void setUyeGelisSekliDiger(String uyeGelisSekliDiger) {
		this.uyeGelisSekliDiger = uyeGelisSekliDiger;
	}

	public String getUyeKanGrubu() {
		return uyeKanGrubu;
	}

	public void setUyeKanGrubu(String uyeKanGrubu) {
		this.uyeKanGrubu = uyeKanGrubu;
	}

	public String getUyeTcNo() {
		return uyeTcNo;
	}

	public void setUyeTcNo(String uyeTcNo) {
		this.uyeTcNo = uyeTcNo;
	}

	public String getUyeIlce() {
		return uyeIlce;
	}

	public void setUyeIlce(String uyeIlce) {
		this.uyeIlce = uyeIlce;
	}

	public Date getGelisTarihi() {
		return gelisTarihi;
	}

	public void setGelisTarihi(Date gelisTarihi) {
		this.gelisTarihi = gelisTarihi;
	}

	public String getUyeOzelNot() {
		return uyeOzelNot;
	}

	public void setUyeOzelNot(String uyeOzelNot) {
		this.uyeOzelNot = uyeOzelNot;
	}

	public Date getZiyaretciTarihi() {
		return ziyaretciTarihi;
	}

	public void setZiyaretciTarihi(Date ziyaretciTarihi) {
		this.ziyaretciTarihi = ziyaretciTarihi;
	}

	public Date getUyeTarihi() {
		return uyeTarihi;
	}

	public void setUyeTarihi(Date uyeTarihi) {
		this.uyeTarihi = uyeTarihi;
	}

	public Date getOnSekreterTarihi() {
		return onSekreterTarihi;
	}

	public void setOnSekreterTarihi(Date onSekreterTarihi) {
		this.onSekreterTarihi = onSekreterTarihi;
	}

	public Date getYonetimKuruluUyesiAdayiTarihi() {
		return yonetimKuruluUyesiAdayiTarihi;
	}

	public void setYonetimKuruluUyesiAdayiTarihi(Date yonetimKuruluUyesiAdayiTarihi) {
		this.yonetimKuruluUyesiAdayiTarihi = yonetimKuruluUyesiAdayiTarihi;
	}

	public Date getYonetimKuruluUyesiTarihi() {
		return yonetimKuruluUyesiTarihi;
	}

	public void setYonetimKuruluUyesiTarihi(Date yonetimKuruluUyesiTarihi) {
		this.yonetimKuruluUyesiTarihi = yonetimKuruluUyesiTarihi;
	}

	public Date getSekreterTarihi() {
		return sekreterTarihi;
	}

	public void setSekreterTarihi(Date sekreterTarihi) {
		this.sekreterTarihi = sekreterTarihi;
	}

	public Date getEgitmenTarihi() {
		return egitmenTarihi;
	}

	public void setEgitmenTarihi(Date egitmenTarihi) {
		this.egitmenTarihi = egitmenTarihi;
	}

	public Date getSubeBaskanYardimcisiTarihi() {
		return subeBaskanYardimcisiTarihi;
	}

	public void setSubeBaskanYardimcisiTarihi(Date subeBaskanYardimcisiTarihi) {
		this.subeBaskanYardimcisiTarihi = subeBaskanYardimcisiTarihi;
	}

	public Date getSubeBaskaniTarihi() {
		return subeBaskaniTarihi;
	}

	public void setSubeBaskaniTarihi(Date subeBaskaniTarihi) {
		this.subeBaskaniTarihi = subeBaskaniTarihi;
	}

	public Date getZiyaretciTerkTarihi() {
		return ziyaretciTerkTarihi;
	}

	public void setZiyaretciTerkTarihi(Date ziyaretciTerkTarihi) {
		this.ziyaretciTerkTarihi = ziyaretciTerkTarihi;
	}

	public Date getUyeTerkTarihi() {
		return uyeTerkTarihi;
	}

	public void setUyeTerkTarihi(Date uyeTerkTarihi) {
		this.uyeTerkTarihi = uyeTerkTarihi;
	}

	public Date getOnSekreterTerkTarihi() {
		return onSekreterTerkTarihi;
	}

	public void setOnSekreterTerkTarihi(Date onSekreterTerkTarihi) {
		this.onSekreterTerkTarihi = onSekreterTerkTarihi;
	}

	public Date getYonetimKuruluUyesiAdayiTerkTarihi() {
		return yonetimKuruluUyesiAdayiTerkTarihi;
	}

	public void setYonetimKuruluUyesiAdayiTerkTarihi(Date yonetimKuruluUyesiAdayiTerkTarihi) {
		this.yonetimKuruluUyesiAdayiTerkTarihi = yonetimKuruluUyesiAdayiTerkTarihi;
	}

	public Date getYonetimKuruluUyesiTerkTarihi() {
		return yonetimKuruluUyesiTerkTarihi;
	}

	public void setYonetimKuruluUyesiTerkTarihi(Date yonetimKuruluUyesiTerkTarihi) {
		this.yonetimKuruluUyesiTerkTarihi = yonetimKuruluUyesiTerkTarihi;
	}

	public Date getSekreterTerkTarihi() {
		return sekreterTerkTarihi;
	}

	public void setSekreterTerkTarihi(Date sekreterTerkTarihi) {
		this.sekreterTerkTarihi = sekreterTerkTarihi;
	}

	public Date getEgitmenTerkTarihi() {
		return egitmenTerkTarihi;
	}

	public void setEgitmenTerkTarihi(Date egitmenTerkTarihi) {
		this.egitmenTerkTarihi = egitmenTerkTarihi;
	}

	public Date getSubeBaskanYardimcisiTerkTarihi() {
		return subeBaskanYardimcisiTerkTarihi;
	}

	public void setSubeBaskanYardimcisiTerkTarihi(Date subeBaskanYardimcisiTerkTarihi) {
		this.subeBaskanYardimcisiTerkTarihi = subeBaskanYardimcisiTerkTarihi;
	}

	public Date getSubeBaskaniTerkTarihi() {
		return subeBaskaniTerkTarihi;
	}

	public void setSubeBaskaniTerkTarihi(Date subeBaskaniTerkTarihi) {
		this.subeBaskaniTerkTarihi = subeBaskaniTerkTarihi;
	}

	public String getDurum() {
		return durum;
	}

	public void setDurum(String durum) {
		this.durum = durum;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
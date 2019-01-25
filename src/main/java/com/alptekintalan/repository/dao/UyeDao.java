package com.alptekintalan.repository.dao;

import java.util.List;

import com.alptekintalan.model.pojo.entity.Sube;
import com.alptekintalan.model.pojo.entity.Uye;

public interface UyeDao {

	public long createUye(Uye uye);

	public void deleteUye(long uyeId);

	public Uye updateUye(Uye uye);

	public List<Uye> findAllUye();

	public Uye findUye(long uyeId);

	public List<Uye> findUyeler(Object uyeAdi, String uyeTur, String statu);
	
	public List<Uye> findIcUyeler();

	public List<Uye> findUyelerBySubeId(Sube subeId);

	public List<Uye> findUyelerByNameAndLastName(String uyeAdi, String uyeSoyadi);

}

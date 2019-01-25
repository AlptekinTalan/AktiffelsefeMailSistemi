package com.alptekintalan.repository.dao;

import java.util.List;

import com.alptekintalan.model.pojo.entity.Sube;

public interface SubeDao {

	public long createSube(Sube sube);

	public void deleteSube(long subeId);

	public Sube updateSube(Sube sube);

	public List<Sube> findAllSube();

	public Sube findSube(long subeId);

	public List<Sube> findSubeler(Object subeAdi);
	
	public Sube findSubeByName(Object subeAdi);


}

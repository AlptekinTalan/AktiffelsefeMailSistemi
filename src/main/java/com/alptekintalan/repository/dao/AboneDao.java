package com.alptekintalan.repository.dao;

import java.util.List;

import com.alptekintalan.model.pojo.entity.Sube;
import com.alptekintalan.model.pojo.entity.Abone;

public interface AboneDao {

	public long createAbone(Abone abone);

	public void deleteAbone(long aboneId);

	public Abone updateAbone(Abone abone);

	public List<Abone> findAllAbone();

	public Abone findAbone(long aboneId);

	public List<Abone> findAboneler(Object aboneAdi, String aboneTur,Long subeId);

	public List<Abone> findAbonelerBySubeId(Sube subeId);
	
	public List<Abone> findAbonelerPageByPage(int page);

}

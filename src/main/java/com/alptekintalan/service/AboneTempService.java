package com.alptekintalan.service;

import java.util.List;

import com.alptekintalan.model.pojo.entity.AboneTemp;

public interface AboneTempService {

	public long createAboneTemp(AboneTemp aboneTemp);

	public void deleteAboneTemp(long aboneTempId);

	public AboneTemp updateAboneTemp(AboneTemp aboneTemp);

	public List<AboneTemp> findAllAboneTemp();

	public AboneTemp findAboneTemp(long aboneTempId);

	public List<AboneTemp> findAboneTempByKeyAndEposta(String aboneTempKey,String aboneTempEposta);
	
	public List<AboneTemp> findAboneTempler(Object aboneTempAboneAdi, String aboneTempTur,Long AboneTmpSubeId);

}

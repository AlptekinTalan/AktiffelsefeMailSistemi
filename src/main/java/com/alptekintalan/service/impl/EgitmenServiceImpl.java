package com.alptekintalan.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alptekintalan.model.pojo.entity.Egitmen;
import com.alptekintalan.repository.dao.EgitmenDao;
import com.alptekintalan.service.EgitmenService;

@Service
@Transactional
public class EgitmenServiceImpl implements EgitmenService {

	@Inject // @Autowired
	private EgitmenDao egitmenDao;

	public EgitmenServiceImpl() {
		System.out.println("EgitmenServiceImpl");
	}

	@Override
	public long createEgitmen(Egitmen egitmen) {
		return egitmenDao.createEgitmen(egitmen);
	}

	@Override
	public void deleteEgitmen(long egitmenId) {
		egitmenDao.deleteEgitmen(egitmenId);

	}

	@Override
	public Egitmen updateEgitmen(Egitmen egitmen) {
		return egitmenDao.updateEgitmen(egitmen);
	}

	@Override
	public List<Egitmen> findAllEgitmen() {
		return egitmenDao.findAllEgitmen();
	}

	@Override
	public Egitmen findEgitmen(long egitmenId) {
		return egitmenDao.findEgitmen(egitmenId);
	}
	
	@Override
	public List<Egitmen> findEgitmenlar(Object egitmenAdi, String egitmenTur) {
		return egitmenDao.findEgitmenlar(egitmenAdi, egitmenTur);
	}

}

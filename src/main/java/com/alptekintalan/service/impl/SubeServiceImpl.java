package com.alptekintalan.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alptekintalan.model.pojo.entity.Sube;
import com.alptekintalan.repository.dao.SubeDao;
import com.alptekintalan.service.SubeService;

@Service
@Transactional
public class SubeServiceImpl implements SubeService {

	@Inject // @Autowired
	private SubeDao subeDao;

	public SubeServiceImpl() {
		System.out.println("SubeServiceImpl");
	}

	@Override
	public long createSube(Sube sube) {
		return subeDao.createSube(sube);
	}

	@Override
	public void deleteSube(long subeId) {
		subeDao.deleteSube(subeId);

	}

	@Override
	public Sube updateSube(Sube sube) {
		return subeDao.updateSube(sube);
	}

	@Override
	public List<Sube> findAllSube() {
		return subeDao.findAllSube();
	}

	@Override
	public Sube findSube(long subeId) {
		return subeDao.findSube(subeId);
	}

	@Override
	public List<Sube> findSubeler(Object subeAdi) {
		return subeDao.findSubeler(subeAdi);
	}
	
	@Override
	public Sube findSubeByName(Object subeAdi) {
		return subeDao.findSubeByName(subeAdi);
	}
	

}

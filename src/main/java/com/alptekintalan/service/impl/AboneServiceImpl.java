package com.alptekintalan.service.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alptekintalan.model.pojo.entity.Sube;
import com.alptekintalan.model.pojo.entity.Abone;
import com.alptekintalan.repository.dao.AboneDao;
import com.alptekintalan.service.AboneService;

@Service
@Transactional
public class AboneServiceImpl implements AboneService {

	@Inject // @Autowired
	private AboneDao aboneDao;

	// ----------------------------
	public AboneServiceImpl() {
		System.out.println("AboneServiceImpl");
	}
	// ----------------------------

	@Override
	public long createAbone(Abone abone) {
		abone.setKayitTarihi(new Date());
		return aboneDao.createAbone(abone);
	}

	@Override
	public Abone updateAbone(Abone abone) {
		return aboneDao.updateAbone(abone);
	}

	@Override
	public void deleteAbone(long aboneId) {
		aboneDao.deleteAbone(aboneId);
	}

	@Override
	public List<Abone> findAllAbone() {
		return aboneDao.findAllAbone();
	}

	@Override
	public Abone findAbone(long aboneId) {
		return aboneDao.findAbone(aboneId);
	}

	@Override
	public List<Abone> findAboneler(Object aboneAdi, String aboneTur, Long subeId) {
		return aboneDao.findAboneler(aboneAdi, aboneTur, subeId);
	}

	@Override
	public List<Abone> findAbonelerBySubeId(Sube subeId) {
		return aboneDao.findAbonelerBySubeId(subeId);
	}

	@Override
	public List<Abone> findAbonelerPageByPage(int page) {
		return aboneDao.findAbonelerPageByPage(page);
	}
}

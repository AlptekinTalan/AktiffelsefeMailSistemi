package com.alptekintalan.service.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alptekintalan.model.pojo.entity.Etkinlik;
import com.alptekintalan.repository.dao.EtkinlikDao;
import com.alptekintalan.service.EtkinlikService;

@Service
@Transactional
public class EtkinlikServiceImpl implements EtkinlikService {

	@Inject // @Autowired
	private EtkinlikDao etkinlikDao;

	public EtkinlikServiceImpl() {
		System.out.println("EtkinlikServiceImpl");
	}

	@Override
	public long createEtkinlik(Etkinlik etkinlik) {
		return etkinlikDao.createEtkinlik(etkinlik);
	}

	@Override
	public void deleteEtkinlik(long etkinlikId) {
		etkinlikDao.deleteEtkinlik(etkinlikId);

	}

	@Override
	public Etkinlik updateEtkinlik(Etkinlik etkinlik) {
		return etkinlikDao.updateEtkinlik(etkinlik);
	}

	@Override
	public List<Etkinlik> findAllEtkinlik() {
		return etkinlikDao.findAllEtkinlik();
	}

	@Override
	public Etkinlik findEtkinlik(long etkinlikId) {
		return etkinlikDao.findEtkinlik(etkinlikId);
	}

	@Override
	public List<Etkinlik> findEtkinlikler(Object etkinlikAdi, String etkinlikTur, Date tarih1, Date tarih2) {
		return etkinlikDao.findEtkinlikler(etkinlikAdi, etkinlikTur, tarih1, tarih2);
	}

	@Override
	public List<Etkinlik> findEtkinliklerForSendMail() {
		return etkinlikDao.findEtkinliklerForSendMail();
	}

	@Override
	public List<Etkinlik> findEtkinliklerBySubeId(long subeId) {
		return etkinlikDao.findEtkinliklerBySubeId(subeId);
	}

}

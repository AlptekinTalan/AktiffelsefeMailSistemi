package com.alptekintalan.service.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alptekintalan.model.pojo.entity.OzelEtkinlik;
import com.alptekintalan.repository.dao.OzelEtkinlikDao;
import com.alptekintalan.service.OzelEtkinlikService;

@Service
@Transactional
public class OzelEtkinlikServiceImpl implements OzelEtkinlikService {

	@Inject // @Autowired
	private OzelEtkinlikDao ozelEtkinlikDao;

	public OzelEtkinlikServiceImpl() {
	}

	@Override
	public long createEtkinlik(OzelEtkinlik etkinlik) {
		return ozelEtkinlikDao.createEtkinlik(etkinlik);
	}

	@Override
	public void deleteEtkinlik(long etkinlikId) {
		ozelEtkinlikDao.deleteEtkinlik(etkinlikId);

	}

	@Override
	public OzelEtkinlik updateEtkinlik(OzelEtkinlik etkinlik) {
		return ozelEtkinlikDao.updateEtkinlik(etkinlik);
	}

	@Override
	public List<OzelEtkinlik> findAllEtkinlik() {
		return ozelEtkinlikDao.findAllEtkinlik();
	}

	@Override
	public OzelEtkinlik findEtkinlik(long etkinlikId) {
		return ozelEtkinlikDao.findEtkinlik(etkinlikId);
	}

	@Override
	public List<OzelEtkinlik> findEtkinlikler(Object etkinlikAdi, String etkinlikTur, Date tarih1, Date tarih2) {
		return ozelEtkinlikDao.findEtkinlikler(etkinlikAdi, etkinlikTur, tarih1, tarih2);
	}

	@Override
	public List<OzelEtkinlik> findEtkinliklerForSendMail() {
		return ozelEtkinlikDao.findEtkinliklerForSendMail();
	}

	@Override
	public List<OzelEtkinlik> findEtkinliklerBySubeId(long subeId) {
		return ozelEtkinlikDao.findEtkinliklerBySubeId(subeId);
	}

}

package com.alptekintalan.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alptekintalan.model.pojo.entity.OzelEtkinlik;
import com.alptekintalan.model.pojo.entity.Uye;
import com.alptekintalan.model.pojo.entity.Yoklama;
import com.alptekintalan.model.pojo.entity.Devamlilik;
import com.alptekintalan.repository.dao.DevamlilikDao;
import com.alptekintalan.service.DevamlilikService;

@Service
@Transactional
public class DevamlilikServiceImpl implements DevamlilikService {

	@Inject // @Autowired
	private DevamlilikDao devamlilikDao;

	public DevamlilikServiceImpl() {
	}

	@Override
	public long createDevamlilik(Devamlilik devamlilik) {
		return devamlilikDao.createDevamlilik(devamlilik);

	}

	@Override
	public void deleteDevamlilik(long devamlilikId) {
		devamlilikDao.deleteDevamlilik(devamlilikId);

	}

	@Override
	public Devamlilik updateDevamlilik(Devamlilik devamlilik) {
		return devamlilikDao.updateDevamlilik(devamlilik);

	}

	@Override
	public List<Devamlilik> findAllDevamlilik() {
		return devamlilikDao.findAllDevamlilik();

	}

	@Override
	public Devamlilik findDevamlilik(long devamlilikId) {
		return devamlilikDao.findDevamlilik(devamlilikId);

	}

	@Override
	public List<Devamlilik> findDevamlilik(Yoklama yoklamaId) {
		return devamlilikDao.findDevamlilik(yoklamaId);

	}

	@Override
	public List<Devamlilik> findDevamlilik(OzelEtkinlik ozelEtkinlikId) {
		return devamlilikDao.findDevamlilik(ozelEtkinlikId);

	}

	@Override
	public List<Devamlilik> findDevamlilik(Yoklama yoklama, Uye uye) {
		return devamlilikDao.findDevamlilik(yoklama, uye);

	}

}

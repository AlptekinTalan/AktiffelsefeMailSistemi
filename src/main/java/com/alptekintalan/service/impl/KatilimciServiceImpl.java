package com.alptekintalan.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alptekintalan.model.pojo.entity.Katilimci;
import com.alptekintalan.repository.dao.KatilimciDao;
import com.alptekintalan.service.KatilimciService;

@Service
@Transactional
public class KatilimciServiceImpl implements KatilimciService {

	@Inject // @Autowired
	private KatilimciDao katilimciDao;

	public KatilimciServiceImpl() {
		System.out.println("KatilimciServiceImpl");
	}

	@Override
	public long createKatilimci(Katilimci katilimci) {
		return katilimciDao.createKatilimci(katilimci);
	}

	@Override
	public void deleteKatilimci(long katilimciId) {
		katilimciDao.deleteKatilimci(katilimciId);

	}

	@Override
	public Katilimci updateKatilimci(Katilimci katilimci) {
		return katilimciDao.updateKatilimci(katilimci);
	}

	@Override
	public List<Katilimci> findAllKatilimci() {
		return katilimciDao.findAllKatilimci();
	}

	@Override
	public Katilimci findKatilimci(long katilimciId) {
		return katilimciDao.findKatilimci(katilimciId);
	}
	
	@Override
	public List<Katilimci> findKatilimcilar(Object katilimciAdi, String katilimciTur) {
		return katilimciDao.findKatilimcilar(katilimciAdi, katilimciTur);
	}

}

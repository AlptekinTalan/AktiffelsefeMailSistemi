package com.alptekintalan.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alptekintalan.model.pojo.entity.Gorevli;
import com.alptekintalan.repository.dao.GorevliDao;
import com.alptekintalan.service.GorevliService;

@Service
@Transactional
public class GorevliServiceImpl implements GorevliService {

	@Inject // @Autowired
	private GorevliDao gorevliDao;

	public GorevliServiceImpl() {
		System.out.println("GorevliServiceImpl");
	}

	@Override
	public long createGorevli(Gorevli gorevli) {
		return gorevliDao.createGorevli(gorevli);
	}

	@Override
	public void deleteGorevli(long gorevliId) {
		gorevliDao.deleteGorevli(gorevliId);

	}

	@Override
	public Gorevli updateGorevli(Gorevli gorevli) {
		return gorevliDao.updateGorevli(gorevli);
	}

	@Override
	public List<Gorevli> findAllGorevli() {
		return gorevliDao.findAllGorevli();
	}

	@Override
	public Gorevli findGorevli(long gorevliId) {
		return gorevliDao.findGorevli(gorevliId);
	}

	@Override
	public List<Gorevli> findGorevlilar(Object gorevliAdi, String gorevliTur) {
		return gorevliDao.findGorevlilar(gorevliAdi, gorevliTur);
	}

}

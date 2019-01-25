package com.alptekintalan.service.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alptekintalan.model.pojo.entity.Abone;
import com.alptekintalan.model.pojo.entity.AboneTemp;
import com.alptekintalan.repository.dao.AboneTempDao;
import com.alptekintalan.service.AboneTempService;

@Service
@Transactional
public class AboneTempServiceImpl implements AboneTempService {

	@Inject // @Autowired
	private AboneTempDao aboneTempDao;

	// ----------------------------
	public AboneTempServiceImpl() {
		System.out.println("AboneTempServiceImpl");
	}
	// ----------------------------

	@Override
	public long createAboneTemp(AboneTemp aboneTemp) {
		aboneTemp.setKayitTarihi(new Date());
		return aboneTempDao.createAboneTemp(aboneTemp);
	}

	@Override
	public AboneTemp updateAboneTemp(AboneTemp aboneTemp) {
		return aboneTempDao.updateAboneTemp(aboneTemp);
	}

	@Override
	public void deleteAboneTemp(long aboneTempId) {
		aboneTempDao.deleteAboneTemp(aboneTempId);
	}

	@Override
	public List<AboneTemp> findAllAboneTemp() {
		return aboneTempDao.findAllAboneTemp();
	}

	@Override
	public AboneTemp findAboneTemp(long aboneTempId) {
		return aboneTempDao.findAboneTemp(aboneTempId);
	}

	@Override
	public List<AboneTemp> findAboneTempByKeyAndEposta(String aboneTempKey, String aboneTempEposta) {
		return aboneTempDao.findAboneTempByKeyAndEposta(aboneTempKey, aboneTempEposta);

	}

	@Override
	public List<AboneTemp> findAboneTempler(Object aboneTempAboneAdi, String aboneTempTur, Long AboneTmpSubeId) {
		// TODO Auto-generated method stub
		return aboneTempDao.findAboneTempler(aboneTempAboneAdi, aboneTempTur, AboneTmpSubeId);
	}

}

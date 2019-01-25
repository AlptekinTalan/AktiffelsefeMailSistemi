package com.alptekintalan.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alptekintalan.model.pojo.entity.Yoklama;
import com.alptekintalan.repository.dao.YoklamaDao;
import com.alptekintalan.service.YoklamaService;

@Service
@Transactional
public class YoklamaServiceImpl implements YoklamaService {

	@Inject // @Autowired
	private YoklamaDao yoklamaDao;

	public YoklamaServiceImpl() {
	}

	@Override
	public long createYoklama(Yoklama yoklama) {
		return yoklamaDao.createYoklama(yoklama);
	}

	@Override
	public void deleteYoklama(long yoklamaId) {
		yoklamaDao.deleteYoklama(yoklamaId);
	}

	@Override
	public Yoklama updateYoklama(Yoklama yoklama) {
		return yoklamaDao.updateYoklama(yoklama);
	}

	@Override
	public List<Yoklama> findAllYoklama() {
		return yoklamaDao.findAllYoklama();
	}

	@Override
	public Yoklama findYoklama(long yoklamaId) {
		return yoklamaDao.findYoklama(yoklamaId);
	}

	@Override
	public List<Yoklama> findYoklamalar(long ozelEtkinlikId) {
		return yoklamaDao.findYoklamalar(ozelEtkinlikId);
	}

}

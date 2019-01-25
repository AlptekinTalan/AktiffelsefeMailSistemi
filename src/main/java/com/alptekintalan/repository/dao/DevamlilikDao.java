package com.alptekintalan.repository.dao;

import java.util.List;

import com.alptekintalan.model.pojo.entity.OzelEtkinlik;
import com.alptekintalan.model.pojo.entity.Uye;
import com.alptekintalan.model.pojo.entity.Yoklama;
import com.alptekintalan.model.pojo.entity.Devamlilik;

public interface DevamlilikDao {

	public long createDevamlilik(Devamlilik devamlilik);

	public void deleteDevamlilik(long devamlilikId);

	public Devamlilik updateDevamlilik(Devamlilik devamlilik);

	public List<Devamlilik> findAllDevamlilik();

	public Devamlilik findDevamlilik(long devamlilikId);

	public List<Devamlilik> findDevamlilik(Yoklama yoklamaId);

	public List<Devamlilik> findDevamlilik(OzelEtkinlik ozelEtkinlikId);

	public List<Devamlilik> findDevamlilik(Yoklama yoklama, Uye uye);

}

package com.alptekintalan.repository.dao;

import java.util.List;

import com.alptekintalan.model.pojo.entity.Gorevli;

public interface GorevliDao {

	public long createGorevli(Gorevli gorevli);

	public void deleteGorevli(long gorevliId);

	public Gorevli updateGorevli(Gorevli gorevli);

	public List<Gorevli> findAllGorevli();

	public Gorevli findGorevli(long gorevliId);

	public List<Gorevli> findGorevlilar(Object gorevliAdi, String gorevliTur);

}

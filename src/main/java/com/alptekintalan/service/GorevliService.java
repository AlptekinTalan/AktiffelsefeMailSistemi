package com.alptekintalan.service;

import java.util.List;

import com.alptekintalan.model.pojo.entity.Gorevli;

public interface GorevliService {

	public long createGorevli(Gorevli gorevli);

	public void deleteGorevli(long gorevliId);

	public Gorevli updateGorevli(Gorevli gorevli);

	public List<Gorevli> findAllGorevli();

	public Gorevli findGorevli(long gorevliId);

	public List<Gorevli> findGorevlilar(Object gorevliAdi, String gorevliTur);

}

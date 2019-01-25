package com.alptekintalan.service;

import java.util.List;

import com.alptekintalan.model.pojo.entity.Egitmen;

public interface EgitmenService {

	public long createEgitmen(Egitmen egitmen);

	public void deleteEgitmen(long egitmenId);

	public Egitmen updateEgitmen(Egitmen egitmen);

	public List<Egitmen> findAllEgitmen();

	public Egitmen findEgitmen(long egitmenId);

	public List<Egitmen> findEgitmenlar(Object egitmenAdi, String egitmenTur);

}

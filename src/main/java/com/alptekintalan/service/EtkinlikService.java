package com.alptekintalan.service;

import java.util.Date;
import java.util.List;

import com.alptekintalan.model.pojo.entity.Etkinlik;

public interface EtkinlikService {

	public long createEtkinlik(Etkinlik etkinlik);

	public void deleteEtkinlik(long etkinlikId);

	public Etkinlik updateEtkinlik(Etkinlik etkinlik);

	public List<Etkinlik> findAllEtkinlik();

	public Etkinlik findEtkinlik(long etkinlikId);

	public List<Etkinlik> findEtkinlikler(Object etkinlikAdi, String etkinlikTur, Date tarih1, Date tarih2);

	public List<Etkinlik> findEtkinliklerForSendMail();

	public List<Etkinlik> findEtkinliklerBySubeId(long subeId);

}

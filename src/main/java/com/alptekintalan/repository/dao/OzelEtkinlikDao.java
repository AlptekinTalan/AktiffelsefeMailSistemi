package com.alptekintalan.repository.dao;

import java.util.Date;
import java.util.List;

import com.alptekintalan.model.pojo.entity.OzelEtkinlik;

public interface OzelEtkinlikDao {

	public long createEtkinlik(OzelEtkinlik etkinlik);

	public void deleteEtkinlik(long etkinlikId);

	public OzelEtkinlik updateEtkinlik(OzelEtkinlik etkinlik);

	public List<OzelEtkinlik> findAllEtkinlik();

	public OzelEtkinlik findEtkinlik(long etkinlikId);

	public List<OzelEtkinlik> findEtkinlikler(Object etkinlikAdi, String etkinlikTur, Date tarih1, Date tarih2);

	public List<OzelEtkinlik> findEtkinliklerForSendMail();

	public List<OzelEtkinlik> findEtkinliklerBySubeId(long subeId);

}

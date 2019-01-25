package com.alptekintalan.service;

import java.util.List;

import com.alptekintalan.model.pojo.entity.Katilimci;
import com.alptekintalan.model.pojo.entity.Uye;

public interface KatilimciService {

	public long createKatilimci(Katilimci katilimci);

	public void deleteKatilimci(long katilimciId);

	public Katilimci updateKatilimci(Katilimci katilimci);

	public List<Katilimci> findAllKatilimci();

	public Katilimci findKatilimci(long katilimciId);
	
	public List<Katilimci> findKatilimcilar(Object katilimciAdi,String katilimciTur);


}

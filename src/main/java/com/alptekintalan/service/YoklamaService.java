package com.alptekintalan.service;

import java.util.List;

import com.alptekintalan.model.pojo.entity.Yoklama;

public interface YoklamaService {

	public long createYoklama(Yoklama yoklama);

	public void deleteYoklama(long yoklamaId);

	public Yoklama updateYoklama(Yoklama yoklama);

	public List<Yoklama> findAllYoklama();

	public Yoklama findYoklama(long yoklamaId);

	public List<Yoklama> findYoklamalar(long ozelEtkinlikId);

}

package com.alptekintalan.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alptekintalan.model.pojo.entity.Sube;
import com.alptekintalan.model.pojo.entity.Uye;
import com.alptekintalan.repository.dao.UyeDao;
import com.alptekintalan.service.UyeService;

@Service
@Transactional
public class UyeServiceImpl implements UyeService {

	@Inject // @Autowired
	private UyeDao uyeDao;

	// ----------------------------
	public UyeServiceImpl() {
		System.out.println("UyeServiceImpl");
	}
	// ----------------------------

	@Override
	public long createUye(Uye uye) {
		uye.setKayitTarihi(new Date());

		/// Herhangi bir dosyayý al resim olarak eklemeye çalýþ.

		// String photoFilePath = "C:/mytemp/uretim.jpg";
		// byte[] photoBytes = null;
		// try {
		// photoBytes = readBytesFromFile(photoFilePath);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// uye.setFoto(photoBytes);

		return uyeDao.createUye(uye);
	}

	private static byte[] readBytesFromFile(String filePath) throws IOException {
		File inputFile = new File(filePath);
		FileInputStream inputStream = new FileInputStream(inputFile);

		byte[] fileBytes = new byte[(int) inputFile.length()];
		inputStream.read(fileBytes);
		inputStream.close();

		return fileBytes;
	}

	@Override
	public Uye updateUye(Uye uye) {
		return uyeDao.updateUye(uye);
	}

	@Override
	public void deleteUye(long uyeId) {
		uyeDao.deleteUye(uyeId);
	}

	@Override
	public List<Uye> findAllUye() {
		return uyeDao.findAllUye();
	}

	@Override
	public Uye findUye(long uyeId) {
		return uyeDao.findUye(uyeId);
	}

	@Override
	public List<Uye> findUyeler(Object uyeAdi, String uyeTur, String statu) {
		return uyeDao.findUyeler(uyeAdi, uyeTur, statu);
	}

	@Override
	public List<Uye> findIcUyeler() {
		return uyeDao.findIcUyeler();
	}

	@Override
	public List<Uye> findUyelerBySubeId(Sube subeId) {
		// TODO Auto-generated method stub
		return uyeDao.findUyelerBySubeId(subeId);
	}

	@Override
	public List<Uye> findUyelerByNameAndLastName(String uyeAdi, String uyeSoyadi) {
		// TODO Auto-generated method stub
		return uyeDao.findUyelerByNameAndLastName(uyeAdi, uyeSoyadi);
	}
}

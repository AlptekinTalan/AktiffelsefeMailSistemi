package com.alptekintalan.repository.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alptekintalan.model.pojo.entity.Sube;
import com.alptekintalan.model.pojo.entity.Uye;
import com.alptekintalan.model.pojo.entity.Abone;
import com.alptekintalan.model.pojo.entity.AboneTemp;
import com.alptekintalan.model.pojo.entity.Etkinlik;
import com.alptekintalan.repository.dao.AboneTempDao;

@Repository
public class AboneTempDaoImpl implements AboneTempDao {

	@Inject // @Autowired
	private SessionFactory sessionFactory;

	// ---------------------------------------------
	public AboneTempDaoImpl() {
		System.out.println("AboneTempDaoImpl");
	}

	// ---------------------------------------------
	@Override
	public long createAboneTemp(AboneTemp aboneTemp) {
		return (long) sessionFactory.getCurrentSession().save(aboneTemp);
	}

	@Override
	public AboneTemp updateAboneTemp(AboneTemp aboneTemp) {
		sessionFactory.getCurrentSession().update(aboneTemp);
		return aboneTemp;
	}

	@Override
	public void deleteAboneTemp(long aboneTempId) {
		AboneTemp aboneTemp = new AboneTemp();
		aboneTemp.setAboneTempId(aboneTempId);
		sessionFactory.getCurrentSession().delete(aboneTemp);

	}

	@Override
	public List<AboneTemp> findAllAboneTemp() {
		return sessionFactory.getCurrentSession().createQuery("FROM " + AboneTemp.class.getName()).list();

	}

	@Override
	public AboneTemp findAboneTemp(long aboneTempId) {
		return sessionFactory.getCurrentSession().get(AboneTemp.class, aboneTempId);

	}

	@Override
	public List<AboneTemp> findAboneTempByKeyAndEposta(String aboneTempKey,String aboneTempEposta) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AboneTemp.class);
		List<AboneTemp> aboneTempler;

		aboneTempler=criteria.add(Restrictions.like("aboneTempEposta", aboneTempEposta)).add(Restrictions.like("aboneTempKey", aboneTempKey)).list();
		return aboneTempler;
	}

	@Override
	public List<AboneTemp> findAboneTempler(Object aboneTempAboneAdi, String aboneTempTur, Long AboneTmpSubeId) {

		String sorguNeyeGore = "aboneTempEposta";
		if (aboneTempTur.equals("ad")) {
			sorguNeyeGore = "aboneTempAdi";
		} else if (aboneTempTur.equals("soyad")) {
			sorguNeyeGore = "aboneTempSoyadi";
		} else if (aboneTempTur.equals("eposta")) {
			sorguNeyeGore = "aboneTempEposta";
		}

		List<AboneTemp> aboneTempler;

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AboneTemp.class);
		if (sorguNeyeGore.equals("aboneEposta")) {
			if (AboneTmpSubeId == null) {

				aboneTempler = criteria.add(Restrictions.like(sorguNeyeGore, aboneTempAboneAdi)).list();
			} else if (aboneTempAboneAdi.equals("")) {
				aboneTempler = criteria.createAlias("sube", "s").add(Restrictions.like("s.subeId", AboneTmpSubeId)).list();
			} else {
				aboneTempler = criteria.add(Restrictions.like(sorguNeyeGore, aboneTempAboneAdi)).createAlias("sube", "s")
						.add(Restrictions.like("s.subeId", AboneTmpSubeId)).list();
			}
		} else {
			if (AboneTmpSubeId == null) {
				aboneTempler = criteria.add(Restrictions.like(sorguNeyeGore, aboneTempAboneAdi + "%")).list();
			} else {
				aboneTempler = criteria.add(Restrictions.like(sorguNeyeGore, aboneTempAboneAdi + "%")).createAlias("sube", "s")
						.add(Restrictions.like("s.subeId", AboneTmpSubeId)).list();
			}

		}

		return aboneTempler;

	}

}
package bd2.Muber.repositories.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import bd2.Muber.model.Conductor;
import bd2.Muber.repositories.ConductoresRepositoryBI;

public class HibernateConductoresRepository implements ConductoresRepositoryBI {
	
	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Conductor> getConductores() {
		Query query = sessionFactory.getCurrentSession().createQuery("from Conductor");
        
        return (List<Conductor>) query.list();
	}
	
	@Override
	public Conductor getconductorById(Long id){
		Query query = sessionFactory.getCurrentSession().createQuery("from Conductor where idUsuario = :id");
		query.setParameter("id", id); 
		return (Conductor) query.list().get(0);
	}
	@Override
	public List<Conductor> getConductoresByViajesAbiertos() {
		Query query = sessionFactory.getCurrentSession().createQuery("select conductorViaje from Viaje where estado = 'Abierto'");
		return query.list();
	}
	@Override
	public void save(Conductor conductor) {
		sessionFactory.getCurrentSession().save(conductor);
	}
	@Override
	public void update(Conductor conductor) {
		sessionFactory.getCurrentSession().update(conductor);
	}

	
}

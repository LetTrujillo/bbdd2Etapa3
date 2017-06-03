package bd2.Muber.repositories.impl;

import org.hibernate.SessionFactory;

import bd2.Muber.model.Calificacion;
import bd2.Muber.repositories.CalificacionesRepositoryBI;

public class HibernateCalificacionesRepository implements CalificacionesRepositoryBI {
	
	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public void save(Calificacion calificacion) {
		sessionFactory.getCurrentSession().save(calificacion);
	}

}

package bd2.Muber.repositories.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import bd2.Muber.model.Pasajero;
import bd2.Muber.repositories.PasajerosRepositoryBI;

public class HibernatePasajerosRepository implements PasajerosRepositoryBI {
	
	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public List<Pasajero> getPasajeros() {
		
	    Query query = sessionFactory.getCurrentSession().createQuery("from Pasajero");
        return (List<Pasajero>) query.list();
	}
	@Override
	public Pasajero getPasajeroById(Long pasajeroId) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Pasajero where idUsuario = :id");
		query.setParameter("id", pasajeroId);
		return (Pasajero) query.list().get(0);
	}
	@Override
	public void save(Pasajero pasajero) {
		sessionFactory.getCurrentSession().save(pasajero);
	}
	@Override
	public void update(Pasajero pasajero) {
		sessionFactory.getCurrentSession().update(pasajero);
	}
	
	
}

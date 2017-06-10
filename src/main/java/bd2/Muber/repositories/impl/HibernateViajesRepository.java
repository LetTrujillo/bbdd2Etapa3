package bd2.Muber.repositories.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import bd2.Muber.model.Viaje;
import bd2.Muber.repositories.ViajesRepositoryBI;

public class HibernateViajesRepository implements ViajesRepositoryBI {
	
	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Viaje> getViajesAbiertos() {
		
		Query query = sessionFactory.getCurrentSession().createQuery("from Viaje where estado = 'Abierto'");
		return query.list();
	}
	@Override
	public void save(Viaje viaje) {
		sessionFactory.getCurrentSession().save(viaje);
	}
	@Override
	public Viaje getViajebyId(Long viajeId) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Viaje where idViaje = :id");
		query.setParameter("id", viajeId);
		return (Viaje) query.list().get(0);
	}
	@Override
	public void update(Viaje viaje) {
		sessionFactory.getCurrentSession().update(viaje);
	}
	
	

}

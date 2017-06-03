package bd2.Muber.repositories;

import bd2.Muber.repositories.impl.HibernateCalificacionesRepository;
import bd2.Muber.repositories.impl.HibernateConductoresRepository;
import bd2.Muber.repositories.impl.HibernatePasajerosRepository;
import bd2.Muber.repositories.impl.HibernateViajesRepository;

public class RepositoryLocator {
	
    private static final RepositoryLocator INSTANCE = new RepositoryLocator();
	private HibernatePasajerosRepository pasajerosRepository;
	private HibernateConductoresRepository conductoresRepository;
	private HibernateViajesRepository viajesRepository;
	private HibernateCalificacionesRepository calificacionesRepository;

    private RepositoryLocator() {
    }

    public static RepositoryLocator getInstance() {
        return INSTANCE;
    }
	
	public HibernatePasajerosRepository getPasajerosRepository() {
		return pasajerosRepository;
	}

	public void setPasajerosRepository(HibernatePasajerosRepository pasajerosRepository) {
		this.pasajerosRepository = pasajerosRepository;
	}

	public HibernateConductoresRepository getConductoresRepository() {
		return conductoresRepository;
	}

	public void setConductoresRepository(HibernateConductoresRepository conductoresRepository) {
		this.conductoresRepository = conductoresRepository;
	}
	

	public HibernateCalificacionesRepository getCalificacionesRepository() {
		return calificacionesRepository;
	}

	public void setCalificacionesRepository(HibernateCalificacionesRepository calificacionesRepository) {
		this.calificacionesRepository = calificacionesRepository;
	}

	public HibernateViajesRepository getViajesRepository() {
		return viajesRepository;
	}

	public void setViajesRepository(HibernateViajesRepository viajesRepository) {
		this.viajesRepository = viajesRepository;
	}
	
	


}

package bd2.Muber.services.impl;

import bd2.Muber.dto.DTOFactory;
import bd2.Muber.repositories.CalificacionesRepositoryBI;
import bd2.Muber.repositories.ConductoresRepositoryBI;
import bd2.Muber.repositories.PasajerosRepositoryBI;
import bd2.Muber.repositories.ViajesRepositoryBI;

public class BaseServiceImpl {
	
	private DTOFactory dtoFactory;
	private PasajerosRepositoryBI pasajerosRepository;
	private ConductoresRepositoryBI conductoresRepository;
	private ViajesRepositoryBI viajesRepository;
	private CalificacionesRepositoryBI calificacionesRepository;
	
	public DTOFactory getDtoFactory() {
		return dtoFactory;
	}
	public void setDtoFactory(DTOFactory dtoFactory) {
		this.dtoFactory = dtoFactory;
	}
	public PasajerosRepositoryBI getPasajerosRepository() {
		return pasajerosRepository;
	}
	public void setPasajerosRepository(PasajerosRepositoryBI pasajerosRepository) {
		this.pasajerosRepository = pasajerosRepository;
	}
	public ConductoresRepositoryBI getConductoresRepository() {
		return conductoresRepository;
	}
	public void setConductoresRepository(ConductoresRepositoryBI conductoresRepository) {
		this.conductoresRepository = conductoresRepository;
	}
	public ViajesRepositoryBI getViajesRepository() {
		return viajesRepository;
	}
	public void setViajesRepository(ViajesRepositoryBI viajesRepository) {
		this.viajesRepository = viajesRepository;
	}
	public CalificacionesRepositoryBI getCalificacionesRepository() {
		return calificacionesRepository;
	}
	public void setCalificacionesRepository(CalificacionesRepositoryBI calificacionesRepository) {
		this.calificacionesRepository = calificacionesRepository;
	}
	
	

}

package bd2.Muber.services.impl;

import bd2.Muber.model.Calificacion;
import bd2.Muber.services.CalificacionesServiceBI;

public class CalificacionesServiceImpl extends BaseServiceImpl implements CalificacionesServiceBI {

	@Override
	public void save(Calificacion calificacion) {
		this.getCalificacionesRepository().save(calificacion);
	}

	
}

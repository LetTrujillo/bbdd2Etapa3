package bd2.Muber.services.impl;

import java.util.List;

import bd2.Muber.model.Pasajero;
import bd2.Muber.services.PasajerosServiceBI;

public class PasajerosServiceImpl extends BaseServiceImpl implements PasajerosServiceBI {

	@Override
	public List<Pasajero> getPasajeros() {
		return this.getPasajerosRepository().getPasajeros();		
	}

	@Override
	public Pasajero getPasajeroById(Long pasajeroId) {
		return this.getPasajerosRepository().getPasajeroById(pasajeroId);
	}

	@Override
	public void save(Pasajero pasajero) {
		this.getPasajerosRepository().save(pasajero);
	}

	@Override
	public void update(Pasajero pasajero) {
		this.getPasajerosRepository().update(pasajero);
	}
	
	

}

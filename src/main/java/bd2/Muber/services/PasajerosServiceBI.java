package bd2.Muber.services;

import java.util.List;

import bd2.Muber.model.Pasajero;

public interface PasajerosServiceBI {

	public List<Pasajero> getPasajeros();

	public Pasajero getPasajeroById(Long pasajeroId);

	public void save(Pasajero pasajero);

	public void update(Pasajero pasajero);

}

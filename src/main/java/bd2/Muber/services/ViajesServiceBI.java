package bd2.Muber.services;

import java.util.List;

import bd2.Muber.model.Conductor;
import bd2.Muber.model.Viaje;

public interface ViajesServiceBI {
	
	public List<Viaje> getViajesAbiertos();

	public void save(Viaje viaje);

	public Viaje getViajeById(Long viajeId);

	public void update(Viaje viaje);

}

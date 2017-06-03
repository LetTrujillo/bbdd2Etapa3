package bd2.Muber.repositories;

import java.util.List;

import bd2.Muber.model.Viaje;

public interface ViajesRepositoryBI {
	
	public List<Viaje> getViajesAbiertos();

	public void save(Viaje viaje);

	public Viaje getViajebyId(Long viajeId);

	public void update(Viaje viaje);

}

package bd2.Muber.services.impl;

import java.util.List;

import bd2.Muber.model.Viaje;
import bd2.Muber.services.ViajesServiceBI;

public class ViajesServiceImpl extends BaseServiceImpl implements ViajesServiceBI {

	@Override
	public List<Viaje> getViajesAbiertos() {
		return this.getViajesRepository().getViajesAbiertos();
	}

	@Override
	public void save(Viaje viaje) {
		this.getViajesRepository().save(viaje);
	}

	@Override
	public Viaje getViajeById(Long viajeId) {
		return this.getViajesRepository().getViajebyId(viajeId);
	}

	@Override
	public void update(Viaje viaje) {
		this.getViajesRepository().update(viaje);
	}



}

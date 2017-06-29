package bd2.Muber.services.impl;

import java.util.List;

import bd2.Muber.model.Conductor;
import bd2.Muber.services.ConductoresServiceBI;

public class ConductoresServiceImpl extends BaseServiceImpl implements ConductoresServiceBI {

	@Override
	public List<Conductor> getConductores() {
		return this.getConductoresRepository().getConductores();
	}

	@Override
	public Conductor getconductorById(Long id) {
		return this.getConductoresRepository().getconductorById(id);
	}
	
	@Override
	public List<Conductor> getConductoresByViajesAbiertos() {
		return this.getConductoresRepository().getConductoresByViajesAbiertos();
	}

	@Override
	public void save(Conductor conductor) {
		this.getConductoresRepository().save(conductor);
	}

	@Override
	public void update(Conductor conductor) {
		this.getConductoresRepository().update(conductor);
	}

}

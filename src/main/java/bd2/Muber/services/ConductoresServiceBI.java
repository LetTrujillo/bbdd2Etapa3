package bd2.Muber.services;

import java.util.List;

import bd2.Muber.model.Conductor;

public interface ConductoresServiceBI {

	public List<Conductor> getConductores();
	public Conductor getconductorById(Long id);
	public List<Conductor> getConductoresByViajesAbiertos();
}

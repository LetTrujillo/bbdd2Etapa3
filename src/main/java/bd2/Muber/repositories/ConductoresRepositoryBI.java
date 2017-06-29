package bd2.Muber.repositories;

import java.util.List;

import bd2.Muber.model.Conductor;

public interface ConductoresRepositoryBI {

	public List<Conductor> getConductores();
	public Conductor getconductorById(Long id);
	public List<Conductor> getConductoresByViajesAbiertos();
	public void save(Conductor conductor);
	public void update(Conductor conductor);
}

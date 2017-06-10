package bd2.Muber.services;

public class ServiceLocator {
	
	private static final ServiceLocator INSTANCE = new ServiceLocator();
	private PasajerosServiceBI pasajerosService;
	private ConductoresServiceBI conductoresService;
	private ViajesServiceBI viajesService;
	private CalificacionesServiceBI calificacionesService;
	
	private ServiceLocator() {
    }

    public static ServiceLocator getInstance() {
        return INSTANCE;
    }

	public PasajerosServiceBI getPasajerosService() {
		return pasajerosService;
	}

	public void setPasajerosService(PasajerosServiceBI pasajerosService) {
		this.pasajerosService = pasajerosService;
	}

	public ConductoresServiceBI getConductoresService() {
		return conductoresService;
	}

	public void setConductoresService(ConductoresServiceBI conductoresService) {
		this.conductoresService = conductoresService;
	}

	public ViajesServiceBI getViajesService() {
		return viajesService;
	}

	public void setViajesService(ViajesServiceBI viajesService) {
		this.viajesService = viajesService;
	}

	public CalificacionesServiceBI getCalificacionesService() {
		return calificacionesService;
	}

	public void setCalificacionesService(CalificacionesServiceBI calificacionesService) {
		this.calificacionesService = calificacionesService;
	}
	
	

}

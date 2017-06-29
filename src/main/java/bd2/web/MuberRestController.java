package bd2.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import bd2.Muber.dto.CalificacionDTO;
import bd2.Muber.dto.ConductorDTO;
import bd2.Muber.dto.PasajeroDTO;
import bd2.Muber.dto.ViajeDTO;
import bd2.Muber.model.Calificacion;
import bd2.Muber.model.Conductor;
import bd2.Muber.model.Pasajero;
import bd2.Muber.model.Viaje;
import bd2.Muber.services.CalificacionesServiceBI;
import bd2.Muber.services.ConductoresServiceBI;
import bd2.Muber.services.PasajerosServiceBI;
import bd2.Muber.services.ViajesServiceBI;
import bd2.Muber.util.EstadoEnum;
import bd2.Muber.util.JsonUtil;

/***
 * Ver archivo LlamadosApiRest
 * @author Grupo 11
 *
 */
@ControllerAdvice
@RequestMapping("/services")
@ResponseBody
@EnableWebMvc
public class MuberRestController {
	
	@Autowired
	private PasajerosServiceBI pasajerosService;

	@Autowired
	private ConductoresServiceBI conductoresService;
	
	@Autowired
	private ViajesServiceBI viajesService;
	
	@Autowired
	private CalificacionesServiceBI calificacionesService;
	
	
	/**
	 * Crea objetos del escenario de la Etapa1
	 * curl http://localhost:8080/MuberRESTful/rest/services/iniciar
	 */
	@RequestMapping(value = "/iniciar", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
    public String instanciarEscenario(){
		/*
		 * Crear conductor
		 */
		Conductor conductor = new Conductor();
		conductor.setNombreUsuario("Roberto");
		conductor.setPassword("RobertoConductor");
		conductor.setFechaVencimientoLicencia(new Date());
		conductor.setFechaIngresoMuber(new Date());
		conductoresService.save(conductor);
		
		/*
		 * Crear viaje
		 */
		Viaje viaje = new Viaje();
		viaje.setOrigen("La Plata");
		viaje.setDestino("Tres Arroyos");
		viaje.setFechaViaje(new Date());
		viaje.setCantidadMaximaPasajeros(4);
		viaje.setCostoTotal(900);
		viaje.setEstado(EstadoEnum.ABIERTO.toString());
		viajesService.save(viaje);
		
		/*
		 * Crear Pasajeros
		 */
		Pasajero pasajeroGerman = new Pasajero();
		pasajeroGerman.setNombreUsuario("Germán");
		pasajeroGerman.setPassword("PasajeroGerman");
		pasajeroGerman.setCreditoDisponible(1500d);
		pasajeroGerman.setFechaIngresoMuber(new Date());
		pasajerosService.save(pasajeroGerman);
		
		Pasajero pasajeroAlicia = new Pasajero();
		pasajeroAlicia.setNombreUsuario("Alicia");
		pasajeroAlicia.setPassword("PasajeroAlicia");
		pasajeroAlicia.setCreditoDisponible(1500d);
		pasajeroAlicia.setFechaIngresoMuber(new Date());
		pasajerosService.save(pasajeroAlicia);
		
		Pasajero pasajeroMargarita = new Pasajero();
		pasajeroMargarita.setNombreUsuario("Margarita");
		pasajeroMargarita.setPassword("PasajeroMargarita");
		pasajeroMargarita.setCreditoDisponible(1500d);
		pasajeroMargarita.setFechaIngresoMuber(new Date());
		pasajerosService.save(pasajeroMargarita);
		
		/*
		 * Agregar pasajeros y conductor al viaje
		 */
		viaje.agregarPasajero(pasajeroGerman);
		viaje.agregarPasajero(pasajeroAlicia);
		viaje.agregarPasajero(pasajeroMargarita);
		viaje.setConductorViaje(conductor);
		viajesService.update(viaje);
		/*
		 * Crear calificaciones
		 */
		Calificacion calificacionGerman = new Calificacion();
		calificacionGerman.setPasajero(pasajeroGerman);
		calificacionGerman.setPuntaje(5);
		calificacionGerman.setComentario("Muy bueno el viaje");
		calificacionGerman.setViaje(viaje);
		calificacionesService.save(calificacionGerman);
		
		Calificacion calificacionAlicia = new Calificacion();
		calificacionAlicia.setPasajero(pasajeroAlicia);
		calificacionAlicia.setPuntaje(3);
		calificacionAlicia.setComentario("Viaje regular");
		calificacionAlicia.setViaje(viaje);
		calificacionesService.save(calificacionAlicia);
		
		Calificacion calificacionMargarita = new Calificacion();
		calificacionMargarita.setPasajero(pasajeroMargarita);
		calificacionMargarita.setPuntaje(4);
		calificacionMargarita.setComentario("Viaje bueno");
		calificacionMargarita.setViaje(viaje);
		calificacionesService.save(calificacionMargarita);
		
		conductor.agregarCalificacion(calificacionGerman);
		conductor.agregarCalificacion(calificacionAlicia);
		conductor.getCalificacionesConductor().add(calificacionMargarita);
		
		/*
		 * Registrar viaje del conductor
		 */
		conductor.registrarViajeRealizado(viaje);
		conductoresService.update(conductor);
		/*
		 * Finalizar el viaje
		 */
		viaje.finalizarViaje();
		viajesService.update(viaje);
		
		return JsonUtil.generateJson("OK", "Escenario creado"); 
		
	}

	/**
	 * Lista todos los pasajeros registrados en Muber
	 * curl http://localhost:8080/MuberRESTful/rest/services/pasajeros
	 * @return Json
	 */

	@RequestMapping(value = "/pasajeros", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String pasajeros() {
		
		List<Pasajero> pasajeros = this.getPasajerosService().getPasajeros();
		List<PasajeroDTO> pasajerosDTO = new ArrayList<PasajeroDTO>();
		if(pasajeros == null || pasajeros.isEmpty()){
	
			return JsonUtil.generateJson("OK", "No hay pasajeros registrados");		
		}else
			for(Pasajero pasajero : pasajeros){
				pasajerosDTO.add(new PasajeroDTO(pasajero));
			}
			return JsonUtil.generateJson("OK", pasajerosDTO);

	}
	
	/**
	 * Lista todos los conductores registrados en Muber
	 * curl http://localhost:8080/MuberRESTful/rest/services/conductores
	 * @return Json
	 */
	@RequestMapping(value = "/conductores", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String conductores() {

		List<Conductor> conductores = this.getConductoresService().getConductores();
		List<ConductorDTO> conductoresDTO = new ArrayList<ConductorDTO>();
		if(conductores != null && !conductores.isEmpty()){
			for(Conductor conductor : conductores){
				conductoresDTO.add(new ConductorDTO(conductor));
			}
			return JsonUtil.generateJson("OK", conductoresDTO);
		}
		else
			return JsonUtil.generateJson("OK", "No hay conductores registrados");
	}
	
	/**
	 * Lista todos los viajes abiertos en Muber
	 * curl http://localhost:8080/MuberRESTful/rest/services/viajes/abiertos
	 * @return Json
	 */
	@RequestMapping(value = "/viajes/abiertos", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String viajesAbiertos() {
		
		List<Viaje> viajes = this.getViajesService().getViajesAbiertos();
		List<ViajeDTO> viajesDTO = new ArrayList<ViajeDTO>();
		if(viajes != null && !viajes.isEmpty()){
			for(Viaje viaje : viajes){
				viajesDTO.add(new ViajeDTO(viaje));
			}
			return JsonUtil.generateJson("OK", viajesDTO);
		}	else
				return JsonUtil.generateJson("OK", "No hay viajes abiertos");
		
		
	}
	
	
	/**
	 * Obtener la información de un conductor (nombre de usuario, viajes realizados, puntaje promedio y fecha de licencia)
	 *  curl http://localhost:8080/MuberRESTful/rest/services/conductores/detalle/{id}
	 * @param id
	 * @return Json
	 */
	@RequestMapping(value = "/conductores/detalle/{id}", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String informacionConductor(@PathVariable (value = "id") Long id) {
		
		Conductor conductor = this.getConductoresService().getconductorById(id);
		if(conductor == null || "".equals(conductor))			
			return JsonUtil.generateJson("OK", "No se encontró el conductor");
		else{
			ConductorDTO conductorDTO = new ConductorDTO(conductor);
			conductorDTO.setPuntajePromedio(conductor.promedioCalificacion());
			return JsonUtil.generateJson("OK", conductorDTO);
		}
}
	
	/**
	 * Crea un viaje
	 * curl -d "origen={origen}&destino={destino}&conductorId={conductorId}&costoTotal={costo}&cantidadPasajeros={cantidad}" http://localhost:8080/MuberRESTful/rest/services/viajes/nuevo
	 * @param origen
	 * @param destino
	 * @param conductorId
	 * @param costoTotal
	 * @param cantidadPasajeros
	 * @return Json
	 */
	@RequestMapping(value = "/viajes/nuevo", method = RequestMethod.POST, produces = "application/json", headers = "Accept=application/json")
	public String crearViaje(String origen, String destino, Long conductorId, double costoTotal, int cantidadPasajeros) {
		
		Conductor conductor = this.getConductoresService().getconductorById(conductorId);

		if(conductor == null || "".equals(conductor))			
			return JsonUtil.generateJson("OK", "No se encontró el conductor");
		else{
			Viaje viaje = new Viaje();
			viaje.setCantidadMaximaPasajeros(cantidadPasajeros);
			viaje.setOrigen(origen);
			viaje.setDestino(destino);
			viaje.setCostoTotal(costoTotal);
			viaje.setFechaViaje(new Date());
			viaje.setEstado(EstadoEnum.ABIERTO.toString());
			viaje.setConductorViaje(conductor);
			this.getViajesService().save(viaje);
			ViajeDTO viajeDTO = new ViajeDTO(viaje);
			return JsonUtil.generateJson("OK",viajeDTO);

		}

	}
	/**
	 * Agrega un pasajero a un viaje ya creado
	 * curl -X PUT http://localhost:8080/MuberRESTful/rest/services/viajes/agregarPasajero/{viajeId}/{pasajeroId}
	 * @param viajeId
	 * @param pasajeroId
	 * @return Json
	 */
	@RequestMapping(value = "/viajes/agregarPasajero/{viajeId}/{pasajeroId}", method = RequestMethod.PUT, produces = "application/json", headers = "Accept=application/json")
	public String agregarPasajero(@PathVariable(value = "viajeId")Long viajeId, @PathVariable(value = "pasajeroId") Long pasajeroId) {
		
		Pasajero pasajero = this.getPasajerosService().getPasajeroById(pasajeroId);
		if(pasajero == null || "".equals(pasajero))			
			return JsonUtil.generateJson("OK", "No se encontró el pasajero");
		else{
			Viaje viaje = this.getViajesService().getViajeById(viajeId);
			if(viaje == null || "".equals(viaje))			
				return JsonUtil.generateJson("OK", "No se encontró el viaje");
			else{
				viaje.agregarPasajero(pasajero);
				this.getViajesService().update(viaje);
				return JsonUtil.generateJson("OK", "Se agregó el pasajero al viaje con éxito");
			}
			
		}

	}
	
	/**
	 * Crea una calificación de un pasajero para un viaje.
	 * curl -d "viajeId={viaje}&pasajeroId={pasajero}&puntaje={puntaje}&comentario={comentario}" http://localhost:8080/MuberRESTful/rest/services/viajes/calificar
	 * @param viajeId
	 * @param pasajeroId
	 * @param puntaje
	 * @param comentario
	 * @return Json
	 */
	@RequestMapping(value = "/viajes/calificar", method = RequestMethod.POST, produces = "application/json", headers = "Accept=application/json")
	public String calificarViaje(Long viajeId, Long pasajeroId, int puntaje, String comentario) {
		
		Pasajero pasajero = this.getPasajerosService().getPasajeroById(pasajeroId);
		if(pasajero == null || "".equals(pasajero))			
			return JsonUtil.generateJson("OK", "No se encontró el pasajero");
		else{
			Viaje viaje = this.getViajesService().getViajeById(viajeId);
			Calificacion calificacion;
			if(viaje == null || "".equals(viaje))			
				return JsonUtil.generateJson("OK", "No se encontró el viaje");
			else{
				calificacion = new Calificacion();
				calificacion.setPasajero(pasajero);
				calificacion.setViaje(viaje);
				calificacion.setPuntaje(puntaje);
				calificacion.setComentario(comentario);
				this.getCalificacionesService().save(calificacion);
				CalificacionDTO calificacionDTO = new CalificacionDTO(calificacion);
				return JsonUtil.generateJson("Calificación creada", calificacionDTO);
			}
		}
	}
	
	/**
	 * Finaliza un viaje, si está abierto.
	 * curl -X PUT http://localhost:8080/MuberRESTful/rest/services/viajes/finalizar?viajeId={idViaje}
	 * @param viajeId
	 * @return Json
	 */
	@RequestMapping(value = "/viajes/finalizar", method = RequestMethod.PUT, produces = "application/json", headers = "Accept=application/text")
	public String finalizarViaje(Long viajeId) {
		
		Viaje viaje = this.getViajesService().getViajeById(viajeId);
		if(viaje == null || "".equals(viaje))			
				return JsonUtil.generateJson("OK", "No se encontró el viaje");
		else{
			if(viaje.finalizarViaje()){
				this.getViajesService().update(viaje);
				ViajeDTO viajeDTO = new ViajeDTO(viaje);
				return JsonUtil.generateJson("Viaje Finalizado", viajeDTO);
			}	
			else return JsonUtil.generateJson("OK", "Verifique que el viaje no haya sido finalizado.");
		}
			
		}
	
	/**
	 * Cargar crédito a un pasajero
	 * curl -X PUT http://localhost:8080/MuberRESTful/rest/services/pasajeros/cargarCredito/{idPasajero}/{monto}
	 * @param pasajeroId
	 * @param monto
	 * @return Json
	 */

	@RequestMapping(value = "/pasajeros/cargarCredito/{pasajeroId}/{monto}", method = RequestMethod.PUT, produces = "application/json")
	public String cargarCredito(@PathVariable("pasajeroId") Long pasajeroId,@PathVariable("monto") String monto) {

		Pasajero pasajero =this.getPasajerosService().getPasajeroById(pasajeroId);
		if(pasajero == null || "".equals(pasajero))			
				return JsonUtil.generateJson("OK", "No se encontró el pasajero");
		else{
			double credito = Double.valueOf(monto);
			pasajero.agregarCredito(credito);
			this.getPasajerosService().update(pasajero);
			PasajeroDTO pasajeroDTO = new PasajeroDTO(pasajero);
			return JsonUtil.generateJson("Crédito cargado", pasajeroDTO);
			
		}
	}
	
	
	/**
	 * Lista los 10 conductores mejor calificados que no tengan viajes abiertos registrados
	 * curl http://localhost:8080/MuberRESTful/rest/services/conductores/top10
	 * @return Json
	 */
	@RequestMapping(value = "/conductores/top10", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String conductoresTop10() {
		
		List<Conductor> conductores = this.getConductoresService().getConductoresByViajesAbiertos();
		List<ConductorDTO> conductoresDTO = new ArrayList<ConductorDTO>();
		if(conductores != null && !conductores.isEmpty()){
			Collections.sort(conductores, Conductor.COMPARADO_POR_PROMEDIO);
			
			if(conductores.size() > 10)
				conductores = conductores.subList(conductores.size() - 10, conductores.size());
			
			for(Conductor conductor : conductores){
				conductoresDTO.add(new ConductorDTO(conductor));
			}
		
			return JsonUtil.generateJson("OK", conductoresDTO);

		}
		return JsonUtil.generateJson("OK", "No hay conductores con viajes abierto");
	}
	
	/**
	 * Crea un pasajero
	 * curl -d "nombre={nombre}&credito={credito}" http://localhost:8080/MuberRESTful/rest/services/pasajeros/nuevo
	 * @param nombre
	 * @param credito
	 * @return Json
	 */
	@RequestMapping(value = "/pasajeros/nuevo", method = RequestMethod.POST, produces = "application/json", headers = "Accept=application/json")
	public String crearPasajero(String nombre, double credito) {
			
		Pasajero pasajero = new Pasajero();
		pasajero.setNombreUsuario(nombre);
		pasajero.setCreditoDisponible(credito);
		this.getPasajerosService().save(pasajero);
		PasajeroDTO pasajeroDTO = new PasajeroDTO(pasajero);
		return JsonUtil.generateJson("Pasajero Creado", pasajeroDTO);
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

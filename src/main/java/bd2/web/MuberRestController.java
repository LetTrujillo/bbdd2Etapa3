package bd2.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

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
	 * Lista todos los pasajeros registrados en Muber
	 * curl http://localhost:8080/MuberRESTful/rest/services/pasajeros
	 * @return Json
	 */

	@RequestMapping(value = "/pasajeros", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String pasajeros() {
		
		List<Pasajero> pasajeros = this.getPasajerosService().getPasajeros();
		if(pasajeros == null || pasajeros.isEmpty())
			return JsonUtil.generateJson("OK", "No hay pasajeros registrados");		
		else
			return JsonUtil.generateJson("OK", pasajeros);

	}
	
	/**
	 * Lista todos los conductores registrados en Muber
	 * curl http://localhost:8080/MuberRESTful/rest/services/conductores
	 * @return Json
	 */
	@RequestMapping(value = "/conductores", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String conductores() {

		List<Conductor> conductores = this.getConductoresService().getConductores();
		if(conductores != null && !conductores.isEmpty()){
			return JsonUtil.generateJson("OK", conductores);
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
		if(viajes != null && !viajes.isEmpty()){
				return JsonUtil.generateJson("OK", viajes);
		}	else
				return JsonUtil.generateJson("OK", "No hay viajes abiertos");
		
		
	}
	
	
	/**
	 * Obtener la información de un conductor (nombre de usuario, viajes realizados, puntaje promedio y fecha de licencia)
	 *  curl http://localhost:8080/MuberRESTful/rest/services/conductores/detalle?id={id}
	 * @param id
	 * @return Json
	 */
	@RequestMapping(value = "/conductores/detalle", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public String informacionConductor(@PathParam(value = "id")Long id) {
		
		Conductor conductor = this.getConductoresService().getconductorById(id);
		if(conductor == null || "".equals(conductor))			
			return JsonUtil.generateJson("OK", "No se encontró el conductor");
		else{
			List<Object> dataList = new LinkedList<>();
			dataList.add(conductor);
			if(!conductor.getViajesRealizadosConductor().isEmpty()){
				for(Viaje viaje : conductor.getViajesRealizadosConductor()){
					dataList.add(viaje);
				}
			}
			else
				dataList.add("El conductor no tiene viajes realizados");
			
			dataList.add("Puntaje Promedio: " + conductor.promedioCalificacion());
			
			return JsonUtil.generateJson("OK", dataList);
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
			viaje.setEstado(EstadoEnum.ABIERTO.toString());
			viaje.setConductorViaje(conductor);
			this.getViajesService().save(viaje);
			return JsonUtil.generateJson("OK", "Se creo el viaje con éxito");

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
				return JsonUtil.generateJson("OK", "Se creo la calificación con éxito");
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
				return JsonUtil.generateJson("OK", "El viaje fue finalizado con éxito");
			}	
			else return JsonUtil.generateJson("OK", "Verifique que el viaje no haya sido finalizado.");
		}
			
		}
	
	/**
	 * Cargar crédito a un pasajero
	 * curl -X PUT http://localhost:8080/MuberRESTful/rest/services/pasajeros/cargarCredito/3/4000
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
			return JsonUtil.generateJson("OK", "Se agregó crédito al pasajero");
			
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
		if(conductores != null && !conductores.isEmpty()){
			Collections.sort(conductores, Conductor.COMPARADO_POR_PROMEDIO);
			
			ArrayList<String> datosConductores = new ArrayList<String>();
			if(conductores.size() > 10)
				conductores = conductores.subList(conductores.size() - 10, conductores.size());
			
			for(Conductor conductor : conductores){
				datosConductores.add(conductor.toString());
			}
			
			return JsonUtil.generateJson("OK", datosConductores);

		}
		return JsonUtil.generateJson("OK", "No hay conductores registrados");
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
		pasajero.setCreditoDisponible(credito);;
		this.getPasajerosService().save(pasajero);
		return JsonUtil.generateJson("OK", "Se creo el pasajero con éxito número: " + pasajero.getIdUsuario());
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

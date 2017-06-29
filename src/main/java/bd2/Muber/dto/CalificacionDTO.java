package bd2.Muber.dto;

import bd2.Muber.model.Calificacion;
import bd2.Muber.model.Pasajero;
import bd2.Muber.model.Viaje;

public class CalificacionDTO {
	
	private String comentario;
	private int puntaje;
	private PasajeroDTO pasajero;
	private ViajeDTO viaje;
	
	public CalificacionDTO(Calificacion calificacion){
		this.setComentario(calificacion.getComentario());
		this.setPuntaje(calificacion.getPuntaje());
		this.setPasajero(new PasajeroDTO(calificacion.getPasajero()));
		this.setViaje(new ViajeDTO(calificacion.getViaje()));
	}
	
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public int getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public PasajeroDTO getPasajero() {
		return pasajero;
	}

	public void setPasajero(PasajeroDTO pasajero) {
		this.pasajero = pasajero;
	}

	public ViajeDTO getViaje() {
		return viaje;
	}

	public void setViaje(ViajeDTO viaje) {
		this.viaje = viaje;
	}
	
	
	

}

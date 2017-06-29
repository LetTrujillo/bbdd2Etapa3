package bd2.Muber.dto;

import java.util.Date;
import bd2.Muber.model.Pasajero;

public class PasajeroDTO {
	
	private String nombreUsuario;
	private Date fechaIngresoMuber;
	private Double creditoDisponible;
	
	public PasajeroDTO(Pasajero pasajero){
		this.nombreUsuario = pasajero.getNombreUsuario();
		this.creditoDisponible = pasajero.getCreditoDisponible();
		this.fechaIngresoMuber = pasajero.getFechaIngresoMuber();
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public Double getCreditoDisponible() {
		return creditoDisponible;
	}
	public void setCreditoDisponible(Double creditoDisponible) {
		this.creditoDisponible = creditoDisponible;
	}

	public Date getFechaIngresoMuber() {
		return fechaIngresoMuber;
	}

	public void setFechaIngresoMuber(Date fechaIngresoMuber) {
		this.fechaIngresoMuber = fechaIngresoMuber;
	}
	

}

package bd2.Muber.dto;

import java.util.Date;
import bd2.Muber.model.Viaje;

public class ViajeDTO {
	
	private String destino;
	private String origen;
	private double costoTotal;
	private Date fechaViaje;
	private int cantidadMaximaPasajeros;
	private ConductorDTO conductorViaje;
	private String estado;	
	
	
	public ViajeDTO(Viaje viaje) {
		this.destino = viaje.getDestino();
		this.origen = viaje.getOrigen();
		this.costoTotal = viaje.getCostoTotal();
		this.fechaViaje = viaje.getFechaViaje();
		this.cantidadMaximaPasajeros = viaje.getCantidadMaximaPasajeros();
		this.estado = viaje.getEstado();
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public double getCostoTotal() {
		return costoTotal;
	}
	public void setCostoTotal(double costoTotal) {
		this.costoTotal = costoTotal;
	}
	public Date getFechaViaje() {
		return fechaViaje;
	}
	public void setFechaViaje(Date fechaViaje) {
		this.fechaViaje = fechaViaje;
	}
	public int getCantidadMaximaPasajeros() {
		return cantidadMaximaPasajeros;
	}
	public void setCantidadMaximaPasajeros(int cantidadMaximaPasajeros) {
		this.cantidadMaximaPasajeros = cantidadMaximaPasajeros;
	}
	public ConductorDTO getConductorViaje() {
		return conductorViaje;
	}
	public void setConductorViaje(ConductorDTO conductorViaje) {
		this.conductorViaje = conductorViaje;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

	
}

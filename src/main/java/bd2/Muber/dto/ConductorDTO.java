package bd2.Muber.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bd2.Muber.model.Conductor;
import bd2.Muber.model.Viaje;

public class ConductorDTO {

	private Date fechaVencimientoLicencia;
	private List<ViajeDTO> viajesRealizadosConductor;
	private String nombreUsuario;
	private double puntajePromedio;
	
	public ConductorDTO(Conductor conductor) {
		this.nombreUsuario = conductor.getNombreUsuario();
		this.fechaVencimientoLicencia = conductor.getFechaVencimientoLicencia();
		viajesRealizadosConductor = new ArrayList<ViajeDTO>();
		if(conductor.getViajesRealizadosConductor() != null ){
			for(Viaje viaje : conductor.getViajesRealizadosConductor()){
				viajesRealizadosConductor.add(new ViajeDTO(viaje));
			}
		}
	}
	public Date getFechaVencimientoLicencia() {
		return fechaVencimientoLicencia;
	}
	public void setFechaVencimientoLicencia(Date fechaVencimientoLicencia) {
		this.fechaVencimientoLicencia = fechaVencimientoLicencia;
	}
	public List<ViajeDTO> getViajesRealizadosConductor() {
		return viajesRealizadosConductor;
	}
	public void setViajesRealizadosConductor(List<ViajeDTO> viajesRealizadosConductor) {
		this.viajesRealizadosConductor = viajesRealizadosConductor;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public double getPuntajePromedio() {
		return puntajePromedio;
	}
	public void setPuntajePromedio(double puntajePromedio) {
		this.puntajePromedio = puntajePromedio;
	}
	
	
}

package com.aitia.demo.zwave_common.dto;

import java.io.Serializable;

public class ThermoResponseDTO implements Serializable {

	//=================================================================================================
	// members

	private static final long serialVersionUID = -8371510478751740542L;
	
	private double value;
	private String serviceType;
	private String deviceType;

	//=================================================================================================
	// methods

	//-------------------------------------------------------------------------------------------------
	public ThermoResponseDTO() {}
	
	//-------------------------------------------------------------------------------------------------
	public ThermoResponseDTO(final double value, final String serviceType, final String deviceType) {
		this.value = value;
		this.serviceType = serviceType;
		this.deviceType = deviceType;
	}

	//-------------------------------------------------------------------------------------------------
	public double getValue() { return value; }
	public String getServiceType() { return serviceType; }
	public String getDeviceType() { return deviceType; }

	//-------------------------------------------------------------------------------------------------
	public void setValue(final double value) {this.value = value; }
	public void setServiceType(final String serviceType) { this.serviceType = serviceType; }
	public void setDeviceType(final String deviceType) { this.deviceType = deviceType; }
}

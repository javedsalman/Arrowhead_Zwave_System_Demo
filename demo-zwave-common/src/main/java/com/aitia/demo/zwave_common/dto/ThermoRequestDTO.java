package com.aitia.demo.zwave_common.dto;

import java.io.Serializable;

public class ThermoRequestDTO implements Serializable {

	//=================================================================================================
	// members

	//private static final long serialVersionUID = -5363562707054976998L;

	private String serviceType;
	private String deviceType;

	//=================================================================================================
	// methods
	
	//-------------------------------------------------------------------------------------------------
	public ThermoRequestDTO(final String serviceType, final String deviceType) {
		this.serviceType = serviceType;
		this.deviceType = deviceType;
	}

	//-------------------------------------------------------------------------------------------------
	public String getServiceType() { return serviceType; }
	public String getDeviceType() { return deviceType; }

	//-------------------------------------------------------------------------------------------------
	public void setServiceType(final String serviceType) { this.serviceType = serviceType; }
	public void setDeviceType(final String deviceType) { this.deviceType = deviceType; }
}

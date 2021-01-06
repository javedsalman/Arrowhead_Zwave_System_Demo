package ai.aitia.demo.thermo_provider.database;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import ai.aitia.demo.thermo_provider.ZwaveDevices;
import org.springframework.stereotype.Component;

import ai.aitia.demo.thermo_provider.entity.Zwave;
import eu.arrowhead.common.exception.InvalidParameterException;

@Component
public class InMemoryZwaveDB extends ConcurrentHashMap<Double, Zwave> {

	//=================================================================================================
	// members
	
	//private static final long serialVersionUID = -2462387539362748691L;
	
	private double valueCounter = 0.00;
	
	//=================================================================================================
	// methods

	//-------------------------------------------------------------------------------------------------
	public Zwave create(final String serviceType, String deviceType) {
		if (serviceType == null || serviceType.isBlank()) {
			throw new InvalidParameterException("serviceType is null or empty");
		}		
		if (deviceType == null || deviceType.isBlank()) {
			throw new InvalidParameterException("deviceType is null or empty");
		}
		
		valueCounter++;
		deviceType = ZwaveDevices.zwaveDevices();
		this.put(valueCounter, new Zwave(valueCounter, serviceType.toLowerCase().trim(), deviceType.toLowerCase().trim()));
		return this.get(valueCounter);
	}
	
	//-------------------------------------------------------------------------------------------------
	public List<Zwave> getAll() {
		return List.copyOf(this.values());
	}
	
	//-------------------------------------------------------------------------------------------------
	public Zwave getById(final int id) {
		if (this.containsKey(id)) {
			return this.get(id);
		} else {
			throw new InvalidParameterException("id '" + id + "' not exists");
		}
	}
	
	//-------------------------------------------------------------------------------------------------
	public Zwave updateById(final double id, final String brand, final String color) {
		if (this.containsKey(id)) {
			final Zwave car = this.get(id);
			if (brand!= null && !brand.isBlank()) {
				car.setDeviceType(brand);
			}
			if (color != null && !color.isBlank()) {
				car.setServiceType(color);
			}
			this.put(id, car);
			return car;
		} else {
			throw new InvalidParameterException("id '" + id + "' not exists");
		}
	}
	
	//-------------------------------------------------------------------------------------------------
	public void removeById(final int id) {
		if (this.containsKey(id)) {
			this.remove(id);
		}
	}
}

package ai.aitia.demo.thermo_provider.database;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import ai.aitia.demo.thermo_provider.PlugSwitcher;
import org.springframework.stereotype.Component;

import ai.aitia.demo.thermo_provider.entity.Thermo;
import eu.arrowhead.common.exception.InvalidParameterException;

@Component
public class InMemoryPlugDB extends ConcurrentHashMap<Double, Thermo> {

	//=================================================================================================
	// members
	
	//private static final long serialVersionUID = -2462387539362748691L;
	
	private double valueCounter;
	
	//=================================================================================================
	// methods

	//-------------------------------------------------------------------------------------------------
	public Thermo create(final String switchPlugState, final String deviceType) {
		if (switchPlugState == null || switchPlugState.isBlank()) {
			throw new InvalidParameterException("serviceType is null or empty");
		}		
		if (deviceType == null || deviceType.isBlank()) {
			throw new InvalidParameterException("deviceType is null or empty");
		}
		
		valueCounter= Double.parseDouble(switchPlugState);
		PlugSwitcher.switchPlug (Integer.parseInt(switchPlugState));
		String switchPlugState1 = "switch-plug-state";

		this.put(valueCounter, new Thermo(valueCounter, deviceType.trim(), switchPlugState1.trim()));
		return this.get(valueCounter);
	}
	
	//-------------------------------------------------------------------------------------------------
	public List<Thermo> getAll() {
		return List.copyOf(this.values());
	}
	
	//-------------------------------------------------------------------------------------------------
	public Thermo getById(final int id) {
		if (this.containsKey(id)) {
			return this.get(id);
		} else {
			throw new InvalidParameterException("id '" + id + "' not exists");
		}
	}
	
	//-------------------------------------------------------------------------------------------------
	public Thermo updateById(final double id, final String brand, final String color) {
		if (this.containsKey(id)) {
			final Thermo car = this.get(id);
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

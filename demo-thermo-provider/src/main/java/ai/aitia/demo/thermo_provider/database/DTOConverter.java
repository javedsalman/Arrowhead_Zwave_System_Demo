package ai.aitia.demo.thermo_provider.database;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

import com.aitia.demo.zwave_common.dto.ThermoResponseDTO;

import ai.aitia.demo.thermo_provider.entity.Thermo;

public class DTOConverter {

	
	//=================================================================================================
	// methods
	
	//-------------------------------------------------------------------------------------------------
	public static ThermoResponseDTO convertCarToCarResponseDTO(final Thermo car) {
		Assert.notNull(car, "car is null");
		return new ThermoResponseDTO(car.getValue(), car.getDeviceType(), car.getServiceType());
	}
	
	//-------------------------------------------------------------------------------------------------
	public static List<ThermoResponseDTO> convertCarListToCarResponseDTOList(final List<Thermo> cars) {
		Assert.notNull(cars, "car list is null");
		final List<ThermoResponseDTO> carResponse = new ArrayList<>(cars.size());
		for (final Thermo car : cars) {
			carResponse.add(convertCarToCarResponseDTO(car));
		}
		return carResponse;
	}

	//=================================================================================================
	// assistant methods

	//-------------------------------------------------------------------------------------------------
	public DTOConverter() {
		throw new UnsupportedOperationException(); 
	}
}

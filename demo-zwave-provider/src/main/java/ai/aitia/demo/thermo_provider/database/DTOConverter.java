package ai.aitia.demo.thermo_provider.database;

import java.util.ArrayList;
import java.util.List;

import com.aitia.demo.zwave_common.dto.ZwaveResponseDTO;
import org.springframework.util.Assert;

import ai.aitia.demo.thermo_provider.entity.Zwave;

public class DTOConverter {

	
	//=================================================================================================
	// methods
	
	//-------------------------------------------------------------------------------------------------
	public static ZwaveResponseDTO convertCarToCarResponseDTO(final Zwave car) {
		Assert.notNull(car, "car is null");
		return new ZwaveResponseDTO(car.getValue(), car.getDeviceType(), car.getServiceType());
	}
	
	//-------------------------------------------------------------------------------------------------
	public static List<ZwaveResponseDTO> convertCarListToCarResponseDTOList(final List<Zwave> cars) {
		Assert.notNull(cars, "car list is null");
		final List<ZwaveResponseDTO> carResponse = new ArrayList<>(cars.size());
		for (final Zwave car : cars) {
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

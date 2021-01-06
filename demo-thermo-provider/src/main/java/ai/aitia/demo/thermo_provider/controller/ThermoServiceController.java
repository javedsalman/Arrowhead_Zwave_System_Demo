package ai.aitia.demo.thermo_provider.controller;

import java.util.ArrayList;
import java.util.List;

import com.aitia.demo.zwave_common.dto.ThermoRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.aitia.demo.zwave_common.dto.ThermoRequestDTO;

import com.aitia.demo.zwave_common.dto.ThermoResponseDTO;

import ai.aitia.demo.thermo_provider.ThermoProviderConstants;
import ai.aitia.demo.thermo_provider.database.DTOConverter;
import ai.aitia.demo.thermo_provider.database.InMemoryThermoDB;
import ai.aitia.demo.thermo_provider.entity.Thermo;
import eu.arrowhead.common.exception.BadPayloadException;

@RestController
@RequestMapping(ThermoProviderConstants.THERMO_URI)
public class ThermoServiceController {
	
	//=================================================================================================
	// members

	
	
	@Autowired
	private InMemoryThermoDB carDB;

	//=================================================================================================
	// methods

	//-------------------------------------------------------------------------------------------------
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public List<ThermoResponseDTO> getCars(@RequestParam(name = ThermoProviderConstants.REQUEST_PARAM_serviceType, required = false) final String serviceType,
														 @RequestParam(name = ThermoProviderConstants.REQUEST_PARAM_deviceType, required = false) final String deviceType) {
		final List<ThermoResponseDTO> response = new ArrayList<>();
		for (final Thermo car : carDB.getAll()) {
			boolean toAdd = true;
			if (serviceType != null && !serviceType.isBlank() && !car.getDeviceType().equalsIgnoreCase(serviceType)) {
				toAdd = false;
			}
			if (deviceType != null && !deviceType.isBlank() && !car.getServiceType().equalsIgnoreCase(deviceType)) {
				toAdd = false;
			}
			if (toAdd) {
				response.add(DTOConverter.convertCarToCarResponseDTO(car));
			}
		}
		return response;
	}

	//-------------------------------------------------------------------------------------------------
	@GetMapping(path = ThermoProviderConstants.BY_ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public ThermoResponseDTO getCarById(@PathVariable(value = ThermoProviderConstants.PATH_VARIABLE_ID) final int id) {
		return DTOConverter.convertCarToCarResponseDTO(carDB.getById(id));
	}
	
	//-------------------------------------------------------------------------------------------------
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public ThermoResponseDTO createCar(@RequestBody final ThermoRequestDTO dto) {
		if (dto.getServiceType() == null || dto.getServiceType().isBlank()) {
			throw new BadPayloadException("serviceType is null or blank");
		}
		if (dto.getDeviceType() == null || dto.getDeviceType().isBlank()) {
			throw new BadPayloadException("deviceType is null or blank");
		}
		final Thermo car = carDB.create(dto.getServiceType(), dto.getDeviceType());
		return DTOConverter.convertCarToCarResponseDTO(car);
	}
	
	//-------------------------------------------------------------------------------------------------
	@PutMapping(path = ThermoProviderConstants.BY_ID_PATH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public ThermoResponseDTO updateCar(@PathVariable(name = ThermoProviderConstants.PATH_VARIABLE_ID) final int id, @RequestBody final ThermoRequestDTO dto) {
		if (dto.getServiceType() == null || dto.getServiceType().isBlank()) {
			throw new BadPayloadException("brand is null or blank");
		}
		if (dto.getDeviceType() == null || dto.getDeviceType().isBlank()) {
			throw new BadPayloadException("color is null or blank");
		}
		final Thermo car = carDB.updateById(id, dto.getServiceType(), dto.getDeviceType());
		return DTOConverter.convertCarToCarResponseDTO(car);
	}
	
	
	//-------------------------------------------------------------------------------------------------
	@DeleteMapping(path = ThermoProviderConstants.BY_ID_PATH)
	public void removeCarById(@PathVariable(value = ThermoProviderConstants.PATH_VARIABLE_ID) final int id) {
		carDB.removeById(id);
	}
}

package ai.aitia.demo.thermo_provider.controller;

import java.util.ArrayList;
import java.util.List;

import ai.aitia.demo.thermo_provider.ZwaveProviderConstants;
import com.aitia.demo.zwave_common.dto.ZwaveRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aitia.demo.zwave_common.dto.ZwaveResponseDTO;

import ai.aitia.demo.thermo_provider.database.DTOConverter;
import ai.aitia.demo.thermo_provider.database.InMemoryZwaveDB;
import ai.aitia.demo.thermo_provider.entity.Zwave;
import eu.arrowhead.common.exception.BadPayloadException;

@RestController
@RequestMapping(ZwaveProviderConstants.ZWAVE_URI)
public class ZwaveServiceController {
	
	//=================================================================================================
	// members

	
	
	@Autowired
	private InMemoryZwaveDB carDB;

	//=================================================================================================
	// methods

	//-------------------------------------------------------------------------------------------------
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public List<ZwaveResponseDTO> getCars(@RequestParam(name = ZwaveProviderConstants.REQUEST_PARAM_serviceType, required = false) final String serviceType,
														@RequestParam(name = ZwaveProviderConstants.REQUEST_PARAM_deviceType, required = false) final String deviceType) {
		final List<ZwaveResponseDTO> response = new ArrayList<>();
		for (final Zwave car : carDB.getAll()) {
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
	@GetMapping(path = ZwaveProviderConstants.BY_ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public ZwaveResponseDTO getCarById(@PathVariable(value = ZwaveProviderConstants.PATH_VARIABLE_ID) final int id) {
		return DTOConverter.convertCarToCarResponseDTO(carDB.getById(id));
	}
	
	//-------------------------------------------------------------------------------------------------
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public ZwaveResponseDTO createCar(@RequestBody final ZwaveRequestDTO dto) {
		if (dto.getServiceType() == null || dto.getServiceType().isBlank()) {
			throw new BadPayloadException("serviceType is null or blank");
		}
		if (dto.getDeviceType() == null || dto.getDeviceType().isBlank()) {
			throw new BadPayloadException("deviceType is null or blank");
		}
		final Zwave car = carDB.create(dto.getServiceType(), dto.getDeviceType());
		return DTOConverter.convertCarToCarResponseDTO(car);
	}
	
	//-------------------------------------------------------------------------------------------------
	@PutMapping(path = ZwaveProviderConstants.BY_ID_PATH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody public ZwaveResponseDTO updateCar(@PathVariable(name = ZwaveProviderConstants.PATH_VARIABLE_ID) final int id, @RequestBody final ZwaveRequestDTO dto) {
		if (dto.getServiceType() == null || dto.getServiceType().isBlank()) {
			throw new BadPayloadException("brand is null or blank");
		}
		if (dto.getDeviceType() == null || dto.getDeviceType().isBlank()) {
			throw new BadPayloadException("color is null or blank");
		}
		final Zwave car = carDB.updateById(id, dto.getServiceType(), dto.getDeviceType());
		return DTOConverter.convertCarToCarResponseDTO(car);
	}
	
	
	//-------------------------------------------------------------------------------------------------
	@DeleteMapping(path = ZwaveProviderConstants.BY_ID_PATH)
	public void removeCarById(@PathVariable(value = ZwaveProviderConstants.PATH_VARIABLE_ID) final int id) {
		carDB.removeById(id);
	}
}

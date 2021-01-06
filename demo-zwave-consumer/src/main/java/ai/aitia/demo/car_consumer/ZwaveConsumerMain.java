package ai.aitia.demo.car_consumer;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.aitia.demo.zwave_common.dto.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;

import eu.arrowhead.client.library.ArrowheadService;
import eu.arrowhead.common.CommonConstants;
import eu.arrowhead.common.SSLProperties;
import eu.arrowhead.common.Utilities;
import eu.arrowhead.common.dto.shared.OrchestrationFlags.Flag;
import eu.arrowhead.common.dto.shared.OrchestrationFormRequestDTO;
import eu.arrowhead.common.dto.shared.OrchestrationFormRequestDTO.Builder;
import eu.arrowhead.common.dto.shared.OrchestrationResponseDTO;
import eu.arrowhead.common.dto.shared.OrchestrationResultDTO;
import eu.arrowhead.common.dto.shared.ServiceInterfaceResponseDTO;
import eu.arrowhead.common.dto.shared.ServiceQueryFormDTO;
import eu.arrowhead.common.exception.InvalidParameterException;

@SpringBootApplication
@ComponentScan(basePackages = {CommonConstants.BASE_PACKAGE, ZwaveConsumerConstants.BASE_PACKAGE})
public class ZwaveConsumerMain implements ApplicationRunner {
    
    //=================================================================================================
	// members
	
    @Autowired
	private ArrowheadService arrowheadService;
    
    @Autowired
	protected SSLProperties sslProperties;
    
    private final Logger logger = LogManager.getLogger(ZwaveConsumerMain.class);
    
    //=================================================================================================
	// methods

	//------------------------------------------------------------------------------------------------
    public static void main( final String[] args ) {
    	SpringApplication.run(ZwaveConsumerMain.class, args);
    }

    //-------------------------------------------------------------------------------------------------
    @Override
	public void run(final ApplicationArguments args) throws Exception {
    	//createCarServiceOrchestrationAndConsumption();
		getZwaveDecicesServiceOrchestrationAndConsumption();
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		TimeUnit.SECONDS.sleep(5);
/////////////////////////////////////////////////////////////////////////////////////////////////////////

		getSetPointThermoServiceOrchestrationAndConsumption();
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		TimeUnit.SECONDS.sleep(7);
/////////////////////////////////////////////////////////////////////////////////////////////////////////

		getCarServiceOrchestrationAndConsumption();

		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		TimeUnit.SECONDS.sleep(7);
/////////////////////////////////////////////////////////////////////////////////////////////////////////

		switchPlugServiceOrchestrationAndConsumption();

	}
    //-------------------------------------------------------------------------------------------------
	public void getZwaveDecicesServiceOrchestrationAndConsumption() {

		double setPointValue=0.00;

		////////////////////////////////////////////////////////////////

		logger.info("Orchestration request for " + ZwaveConsumerConstants.GET_ZWAVE_DEVICES_SERVICE_DEFINITION + " service:");
		final ServiceQueryFormDTO serviceQueryForm1 = new ServiceQueryFormDTO.Builder(ZwaveConsumerConstants.GET_ZWAVE_DEVICES_SERVICE_DEFINITION)
				.interfaces(getInterface())
				.build();

		final Builder orchestrationFormBuilder1 = arrowheadService.getOrchestrationFormBuilder();
		final OrchestrationFormRequestDTO orchestrationFormRequest1 = orchestrationFormBuilder1.requestedService(serviceQueryForm1)
				.flag(Flag.MATCHMAKING, true)
				.flag(Flag.OVERRIDE_STORE, true)
				.build();

		printOut(orchestrationFormRequest1);

		final OrchestrationResponseDTO orchestrationResponse1 = arrowheadService.proceedOrchestration(orchestrationFormRequest1);

		logger.info("Orchestration response for :"+ ZwaveConsumerConstants.GET_ZWAVE_DEVICES_SERVICE_DEFINITION + " service:");
		printOut(orchestrationResponse1);

		if (orchestrationResponse1 == null) {
			logger.info("No orchestration response received");
		} else if (orchestrationResponse1.getResponse().isEmpty()) {
			logger.info("No provider found during the orchestration");
		} else {

			System.out.println("provider Name: "+orchestrationResponse1.getResponse().get(0).getProvider().getSystemName()+
					"Provider address: "+orchestrationResponse1.getResponse().get(0).getProvider().getAddress()+":"+orchestrationResponse1.getResponse().get(0).getProvider().getPort());

			final OrchestrationResultDTO orchestrationResult1 = orchestrationResponse1.getResponse().get(0);
			validateOrchestrationResult(orchestrationResult1, ZwaveConsumerConstants.GET_ZWAVE_DEVICES_SERVICE_DEFINITION);

			//final List<ZwaveRequestDTO> carsToCreate = List.of(new ZwaveRequestDTO("nissan", "green"), new ZwaveRequestDTO("mazda", "blue"), new ZwaveRequestDTO("opel", "blue"), new ZwaveRequestDTO("nissan", "gray"));
			ZwaveRequestDTO setPointReq = new ZwaveRequestDTO("GetAllDevices", "Zwave");

			logger.info("Fetch All Zwave Devices Info Request:");
			printOut(setPointReq);

		/*	for (final ZwaveRequestDTO thermoRequestDTO : carsToCreate) {
				logger.info("Create a car request:");
				printOut(thermoRequestDTO);*/

			final String token = orchestrationResult1.getAuthorizationTokens() == null ? null : orchestrationResult1.getAuthorizationTokens().get(getInterface());
			final ZwaveResponseDTO setpointFetched7 = arrowheadService.consumeServiceHTTP(ZwaveResponseDTO.class, HttpMethod.valueOf(orchestrationResult1.getMetadata().get(ZwaveConsumerConstants.HTTP_METHOD)),
					orchestrationResult1.getProvider().getAddress(), orchestrationResult1.getProvider().getPort(), orchestrationResult1.getServiceUri(),
					getInterface(), token, setPointReq, new String[0]);
			logger.info("__________________________________________________________________________:");
			logger.info("__________________________________________________________________________:");
			logger.info("__________________________________________________________________________:");
			logger.info("Zwave Provider response");
			System.out.println("\n\nThe Following Zwave Devices are Connected with the Zwave Controller ");
			System.out.println("\n\n "+setpointFetched7.getServiceType() );

			System.out.println("\n\n");

			logger.info("__________________________________________________________________________:");
			logger.info("__________________________________________________________________________:");
			logger.info("__________________________________________________________________________:");
			//	printOut(carCreated);
			//	}
		}
	}

    //-------------------------------------------------------------------------------------------------
    public void getSetPointThermoServiceOrchestrationAndConsumption() {

		double setPointValue=0.00;

		////////////////////////////////////////////////////////////////

    	logger.info("Orchestration request for " + ZwaveConsumerConstants.GET_SETPOINT_SERVICE_DEFINITION + " service:");
    	final ServiceQueryFormDTO serviceQueryForm1 = new ServiceQueryFormDTO.Builder(ZwaveConsumerConstants.GET_SETPOINT_SERVICE_DEFINITION)
    																		.interfaces(getInterface())
    																		.build();
    	
		final Builder orchestrationFormBuilder1 = arrowheadService.getOrchestrationFormBuilder();
		final OrchestrationFormRequestDTO orchestrationFormRequest1 = orchestrationFormBuilder1.requestedService(serviceQueryForm1)
																					   .flag(Flag.MATCHMAKING, true)
																					   .flag(Flag.OVERRIDE_STORE, true)
																					   .build();
		
		printOut(orchestrationFormRequest1);
		
		final OrchestrationResponseDTO orchestrationResponse1 = arrowheadService.proceedOrchestration(orchestrationFormRequest1);
		
		logger.info("Orchestration response for :"+ ZwaveConsumerConstants.GET_SETPOINT_SERVICE_DEFINITION + " service:");
		printOut(orchestrationResponse1);
		
		if (orchestrationResponse1 == null) {
			logger.info("No orchestration response received");
		} else if (orchestrationResponse1.getResponse().isEmpty()) {
			logger.info("No provider found during the orchestration");
		} else {

			System.out.println("provider Name: "+orchestrationResponse1.getResponse().get(0).getProvider().getSystemName()+
					"Provider address: "+orchestrationResponse1.getResponse().get(0).getProvider().getAddress()+":"+orchestrationResponse1.getResponse().get(0).getProvider().getPort());

			final OrchestrationResultDTO orchestrationResult1 = orchestrationResponse1.getResponse().get(0);
			validateOrchestrationResult(orchestrationResult1, ZwaveConsumerConstants.GET_SETPOINT_SERVICE_DEFINITION);
			
			//final List<ThermoRequestDTO> carsToCreate = List.of(new ThermoRequestDTO("nissan", "green"), new ThermoRequestDTO("mazda", "blue"), new ThermoRequestDTO("opel", "blue"), new ThermoRequestDTO("nissan", "gray"));
			ThermoRequestDTO setPointReq = new ThermoRequestDTO("SetPoint", "Thermostat");

			logger.info("Fetch SetPoint from Thermostat Request:");
			printOut(setPointReq);

		/*	for (final ThermoRequestDTO thermoRequestDTO : carsToCreate) {
				logger.info("Create a car request:");
				printOut(thermoRequestDTO);*/

				final String token = orchestrationResult1.getAuthorizationTokens() == null ? null : orchestrationResult1.getAuthorizationTokens().get(getInterface());
				final ThermoResponseDTO setpointFetched = arrowheadService.consumeServiceHTTP(ThermoResponseDTO.class, HttpMethod.valueOf(orchestrationResult1.getMetadata().get(ZwaveConsumerConstants.HTTP_METHOD)),
						orchestrationResult1.getProvider().getAddress(), orchestrationResult1.getProvider().getPort(), orchestrationResult1.getServiceUri(),
						getInterface(), token, setPointReq, new String[0]);
			logger.info("__________________________________________________________________________:");
			logger.info("__________________________________________________________________________:");
			logger.info("__________________________________________________________________________:");
				logger.info("Thermostat Provider response");
			System.out.println("\n\nThermostat Current SetPoint is "+setpointFetched.getValue() +" degree celsius");
			System.out.println("\n\n");

			logger.info("__________________________________________________________________________:");
			logger.info("__________________________________________________________________________:");
			logger.info("__________________________________________________________________________:");
			//	printOut(carCreated);
		//	}
		}
    }

	public void switchPlugServiceOrchestrationAndConsumption() throws InterruptedException {

		double setPointValue=0.00;

		////////////////////////////////////////////////////////////////

		logger.info("Orchestration request for " + ZwaveConsumerConstants.SWITCH_PLUG_SERVICE_DEFINITION + " service:");
		final ServiceQueryFormDTO serviceQueryForm2 = new ServiceQueryFormDTO.Builder(ZwaveConsumerConstants.SWITCH_PLUG_SERVICE_DEFINITION)
				.interfaces(getInterface())
				.build();

		final Builder orchestrationFormBuilder2 = arrowheadService.getOrchestrationFormBuilder();
		final OrchestrationFormRequestDTO orchestrationFormRequest2 = orchestrationFormBuilder2.requestedService(serviceQueryForm2)
				.flag(Flag.MATCHMAKING, true)
				.flag(Flag.OVERRIDE_STORE, true)
				.build();

		printOut(orchestrationFormRequest2);

		final OrchestrationResponseDTO orchestrationResponse2 = arrowheadService.proceedOrchestration(orchestrationFormRequest2);

		logger.info("Orchestration response for :"+ ZwaveConsumerConstants.SWITCH_PLUG_SERVICE_DEFINITION + " service:");
		printOut(orchestrationResponse2);

		if (orchestrationResponse2 == null) {
			logger.info("No orchestration response received");
		} else if (orchestrationResponse2.getResponse().isEmpty()) {
			logger.info("No provider found during the orchestration");
		} else {

			System.out.println("provider Name: "+orchestrationResponse2.getResponse().get(0).getProvider().getSystemName()+
					"Provider address: "+orchestrationResponse2.getResponse().get(0).getProvider().getAddress()+":"+orchestrationResponse2.getResponse().get(0).getProvider().getPort());

			final OrchestrationResultDTO orchestrationResult2 = orchestrationResponse2.getResponse().get(0);
			validateOrchestrationResult(orchestrationResult2, ZwaveConsumerConstants.SWITCH_PLUG_SERVICE_DEFINITION);

			//final List<PlugRequestDTO> carsToCreate = List.of(new PlugRequestDTO("nissan", "green"), new PlugRequestDTO("mazda", "blue"), new PlugRequestDTO("opel", "blue"), new PlugRequestDTO("nissan", "gray"));
			PlugRequestDTO setPointReq = new PlugRequestDTO("0", "Plug");

			logger.info("Sending Switch Plug OFF Request:");
			//printOut(setPointReq);

		/*	for (final PlugRequestDTO thermoRequestDTO : carsToCreate) {
				logger.info("Create a car request:");
				printOut(thermoRequestDTO);*/

			final String token2 = orchestrationResult2.getAuthorizationTokens() == null ? null : orchestrationResult2.getAuthorizationTokens().get(getInterface());
			final PlugResponseDTO switchPlugSent = arrowheadService.consumeServiceHTTP(PlugResponseDTO.class, HttpMethod.valueOf(orchestrationResult2.getMetadata().get(ZwaveConsumerConstants.HTTP_METHOD)),
					orchestrationResult2.getProvider().getAddress(), orchestrationResult2.getProvider().getPort(), orchestrationResult2.getServiceUri(),
					getInterface(), token2, setPointReq, new String[0]);
			logger.info("Plug Provider response");
			printOut(switchPlugSent);
			System.out.println("_____________________________");
			System.out.println("_____________________________");
			System.out.println("\n\nBinary Plug is Now Turning OFF ");
			System.out.println("_____________________________");
			System.out.println("_____________________________");
			System.out.println("\n\n");
			//	printOut(carCreated);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
			TimeUnit.SECONDS.sleep(5);
/////////////////////////////////////////////////////////////////////////////////////////////////////////
			//	}
			PlugRequestDTO setPointReq1 = new PlugRequestDTO("255", "Plug");

			logger.info("Sending Switch Plug ON Request:");
			//printOut(setPointReq1);

		/*	for (final PlugRequestDTO thermoRequestDTO : carsToCreate) {
				logger.info("Create a car request:");
				printOut(thermoRequestDTO);*/

			final String token3 = orchestrationResult2.getAuthorizationTokens() == null ? null : orchestrationResult2.getAuthorizationTokens().get(getInterface());
			final PlugResponseDTO switchPlugSent1 = arrowheadService.consumeServiceHTTP(PlugResponseDTO.class, HttpMethod.valueOf(orchestrationResult2.getMetadata().get(ZwaveConsumerConstants.HTTP_METHOD)),
					orchestrationResult2.getProvider().getAddress(), orchestrationResult2.getProvider().getPort(), orchestrationResult2.getServiceUri(),
					getInterface(), token3, setPointReq1, new String[0]);
			logger.info("Plug Provider response");
			printOut(switchPlugSent1);

			System.out.println("_____________________________");
			System.out.println("_____________________________");
			System.out.println("\n\nBinary Plug is Now Turning ON ");
			System.out.println("_____________________________");
			System.out.println("_____________________________");
			System.out.println("\n\n");


		}
	}
    
    //-------------------------------------------------------------------------------------------------
    public void getCarServiceOrchestrationAndConsumption() {
    	logger.info("Orchestration request for " + ZwaveConsumerConstants.GET_CAR_SERVICE_DEFINITION + " service:");
    	final ServiceQueryFormDTO serviceQueryForm = new ServiceQueryFormDTO.Builder(ZwaveConsumerConstants.GET_CAR_SERVICE_DEFINITION)
    																		.interfaces(getInterface())
    																		.build();
    	
		final Builder orchestrationFormBuilder = arrowheadService.getOrchestrationFormBuilder();
		final OrchestrationFormRequestDTO orchestrationFormRequest = orchestrationFormBuilder.requestedService(serviceQueryForm)
																					   .flag(Flag.MATCHMAKING, true)
																					   .flag(Flag.OVERRIDE_STORE, true)
																					   .build();
		
		printOut(orchestrationFormRequest);		
		
		final OrchestrationResponseDTO orchestrationResponse = arrowheadService.proceedOrchestration(orchestrationFormRequest);
		
		logger.info("Orchestration response:");
		printOut(orchestrationResponse);		
		
		if (orchestrationResponse == null) {
			logger.info("No orchestration response received");
		} else if (orchestrationResponse.getResponse().isEmpty()) {
			logger.info("No provider found during the orchestration");
		} else {
			final OrchestrationResultDTO orchestrationResult = orchestrationResponse.getResponse().get(0);
			validateOrchestrationResult(orchestrationResult, ZwaveConsumerConstants.GET_CAR_SERVICE_DEFINITION);
			logger.info("__________________________________________________________________________:");
			logger.info("__________________________________________________________________________:");
			logger.info("__________________________________________________________________________:");
			logger.info("Get Thermostat SetPoint History:");
			System.out.println("\n\n");
			final String token = orchestrationResult.getAuthorizationTokens() == null ? null : orchestrationResult.getAuthorizationTokens().get(getInterface());
			@SuppressWarnings("unchecked")
			final List<ThermoResponseDTO> allCar = arrowheadService.consumeServiceHTTP(List.class, HttpMethod.valueOf(orchestrationResult.getMetadata().get(ZwaveConsumerConstants.HTTP_METHOD)),
																					orchestrationResult.getProvider().getAddress(), orchestrationResult.getProvider().getPort(), orchestrationResult.getServiceUri(),
																					getInterface(), token, null, new String[0]);
			printOut(allCar);
			System.out.println("\n\n");
			logger.info("__________________________________________________________________________:");
			logger.info("__________________________________________________________________________:");
			logger.info("__________________________________________________________________________:");
		/*	logger.info("Get only blue cars:");
			final String[] queryParamColor = {orchestrationResult.getMetadata().get(ZwaveConsumerConstants.REQUEST_PARAM_KEY_COLOR), "blue"};
			@SuppressWarnings("unchecked")
			final List<ThermoResponseDTO> blueCars = arrowheadService.consumeServiceHTTP(List.class, HttpMethod.valueOf(orchestrationResult.getMetadata().get(ZwaveConsumerConstants.HTTP_METHOD)),
																					  orchestrationResult.getProvider().getAddress(), orchestrationResult.getProvider().getPort(), orchestrationResult.getServiceUri(),
																					  getInterface(), token, null, queryParamColor);
			printOut(blueCars);*/
		}
    }
    
    //=================================================================================================
	// assistant methods
    
    //-------------------------------------------------------------------------------------------------
    private String getInterface() {
    	return sslProperties.isSslEnabled() ? ZwaveConsumerConstants.INTERFACE_SECURE : ZwaveConsumerConstants.INTERFACE_INSECURE;
    }
    
    //-------------------------------------------------------------------------------------------------
    private void validateOrchestrationResult(final OrchestrationResultDTO orchestrationResult, final String serviceDefinitin) {
    	if (!orchestrationResult.getService().getServiceDefinition().equalsIgnoreCase(serviceDefinitin)) {
			throw new InvalidParameterException("Requested and orchestrated service definition do not match");
		}
    	
    	boolean hasValidInterface = false;
    	for (final ServiceInterfaceResponseDTO serviceInterface : orchestrationResult.getInterfaces()) {
			if (serviceInterface.getInterfaceName().equalsIgnoreCase(getInterface())) {
				hasValidInterface = true;
				break;
			}
		}
    	if (!hasValidInterface) {
    		throw new InvalidParameterException("Requested and orchestrated interface do not match");
		}
    }
    
    //-------------------------------------------------------------------------------------------------
    private void printOut(final Object object) {
    	System.out.println(Utilities.toPrettyJson(Utilities.toJson(object)));
    }
}

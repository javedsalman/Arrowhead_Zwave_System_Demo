package ai.aitia.demo.car_consumer;

public class ZwaveConsumerConstants {
	
	//=================================================================================================
	// members
	
	public static final String BASE_PACKAGE = "ai.aitia";
	
	public static final String INTERFACE_SECURE = "HTTPS-SECURE-JSON";
	public static final String INTERFACE_INSECURE = "HTTP-INSECURE-JSON";
	public static final String HTTP_METHOD = "http-method";
	
	//public static final String CREATE_CAR_SERVICE_DEFINITION = "create-car";
	public static final String GET_CAR_SERVICE_DEFINITION = "get-setpoint-history";
	public static final String GET_SETPOINT_SERVICE_DEFINITION = "get-setpoint-thermo";
	public static final String SWITCH_PLUG_SERVICE_DEFINITION = "switch-plug-state";


	public static final String REQUEST_PARAM_KEY_BRAND = "request-param-brand";
	public static final String REQUEST_PARAM_KEY_COLOR = "request-param-color";
	
	//=================================================================================================
	// assistant methods

	//-------------------------------------------------------------------------------------------------
	private ZwaveConsumerConstants() {
		throw new UnsupportedOperationException();
	}

}

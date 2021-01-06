package ai.aitia.demo.thermo_provider;

public class PlugProviderConstants {
	
	//=================================================================================================
	// members
	
	public static final String BASE_PACKAGE = "ai.aitia";
	
	//public static final String CREATE_CAR_SERVICE_DEFINITION = "create-car";
	public static final String GET_CAR_SERVICE_DEFINITION = "get-car";

	public static final String SWITCH_PLUG_SERVICE_DEFINITION = "switch-plug-state";

	public static final String INTERFACE_SECURE = "HTTPS-SECURE-JSON";
	public static final String INTERFACE_INSECURE = "HTTP-INSECURE-JSON";
	public static final String HTTP_METHOD = "http-method";
	public static final String PLUG_URI = "/plugs";
	public static final String BY_ID_PATH = "/{id}";
	public static final String PATH_VARIABLE_ID = "id";
	public static final String REQUEST_PARAM_KEY_serviceType = "request-param-serviceType";
	public static final String REQUEST_PARAM_serviceType = "switchPlugState";
	public static final String REQUEST_PARAM_KEY_deviceType = "request-param-deviceType";
	public static final String REQUEST_PARAM_deviceType = "deviceType";
	
	//=================================================================================================
	// assistant methods

	//-------------------------------------------------------------------------------------------------
	private PlugProviderConstants() {
		throw new UnsupportedOperationException();
	}
}

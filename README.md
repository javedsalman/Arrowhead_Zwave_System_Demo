# Arrowhead Zwave System Demonstrator (Java Spring-Boot)
The Arrowhead Zwave System Demonstrator is a SOS with a Z-wave Controller, a Z-wave compliant Dasnfoss Thermostat Valve and a Z-wave compliant Fibaro Wall Plug which all behave as service providers in the arrowhead framework and the consumer connect to these devices and consumes their services. 


### Hardware used
- Raspberry Pi 3.0
- Z-Wave hat
- Fibaro Z-Wave electrical outlet  PLUG
- Danfoss Z-Wave radiator valve Thermostat


## Application Systems in the Arrowhead Cloud:
### demo-zwave-consumer
This is the Consumer in the SOS.
### demo-zwave-provider
This is the provider which fetches the information of all the zwave devices connected with the zwave controller and also maintains a inherent DataManager to store the devices
### demo-thermo-provider
This is the provider which fetches the setpoint values from Danfoss Z-Wave radiator valve Thermostat  and also maintains a inherent DataManager to store the history of setpoint service requests.
### demo-plug-provider
This is the provider which receive POST request from the consumer for switching Fibaro Z-Wave electrical outlet  PLUG ON or OFF and it also maintains DataManager to store the history of switch-plug-state service request.

##System Description Overview:

•	The Consumer will first send the orchestration request for getting the z-wave device list service to get all the available devices connected to z-wave controller.

•	After the list of devices with unique device ids and their types is received from zwave controller service provider, it will use the device ids and device types to request for different kind of services.

•	The consumer request get-setpoint-thermo from Thermostat provider after getting the orchestration response for get-setpoint-thermo service to receive the current setpoint value from Thermostat Valve while the Thermostat provider also stores the value with the timestamp into its inherent DataManager.

•	The consumer then requests get-set-point-history from Thermostat provider after getting the orchestration response for get-setpoint-history service to receive the records of setpoint value history stored in the Thermostat Provider inherent DataManager. 

•	Then the consumer requests switch-plug-state from Plug provider after getting the orchestration response for it to First Turn OFF the Switch then Turn it ON after 5 seconds.

##	Behavior Diagrams

![picture](doc/ArrowheadZwaveSystemDemonstrator.png)

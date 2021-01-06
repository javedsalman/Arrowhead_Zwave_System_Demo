# Arrowhead Zwave System Demonstrator (Java Spring-Boot)
The Arrowhead Zwave System Demonstrator is a SOS with Zwave Devices; One Thermostat and one Plug whích behave as service providers in the arrowhead framework and the consumer consumes the services to get the Thermostat SetPoint Current and Previous Values and Switch the Plug ON and OFF at RUN Time.

## Setting up the hardware

### Hardware used
- Raspberry Pi 4.0
- Z-Wave hat
- Fibaro Z-Wave electrical outlet  PLUG
-  Danfoss Z-Wave radiator valve Thermostat


## Service Descriptions
**create-car:**

Creates a new car instance.
* ***input:*** CarRequestDTO.json
```
{
   "brand":"string",
   "color":"string"
}
```
* ***output:*** CarResponseDTO.json
```
{
   "id":"integer",
   "brand":"string",
   "color":"string"
}
```

**get-car:**

Returns a car list based on the given parameters.
* ***input:*** Query parameters: 

  `brand`={brand} [*not mandatory*]
  
  `color`={color} [*not mandatory*]

* ***output:*** List of CarResponseDTO.json
```
[{
   "id":"integer",
   "brand":"string",
   "color":"string"
}]
```

## How to run?
1. Clone this repo to your local machine.
2. Go to the root directory and execute `mvn install` command, then wait until the build succeeds.
3. Start the [Arrowhead Framework v4.1.3](https://github.com/arrowhead-f/core-java-spring), before you would start the demo.
   Required core systems:
   * Service Registry
   * Authorization
   * Orchestration
4. Start the provider (it will registrate automatically to the Service Registry Core System).
5. At the very first time, register the consumer manually and create the intra cloud authorization rules.
6. Start the Consumer.

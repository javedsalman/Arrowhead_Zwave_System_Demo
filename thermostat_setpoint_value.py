import requests
import json


thermostat_setpoint_value = requests.get('http://130.240.234.243:8083/JS/Run/zway.devices[22].instances[0].commandClasses[0x43].valueOf()', auth=('admin','11KahnaNau11'),stream=True)


print ("print thermostat_setpoint_value() :")
print (thermostat_setpoint_value.json()['data']['1']['val']['value'])


with open("thermosat-setpoint-value.csv", "w") as outfile: 
    json.dump(thermostat_setpoint_value.json()['data']['1']['val']['value'], outfile)     

    
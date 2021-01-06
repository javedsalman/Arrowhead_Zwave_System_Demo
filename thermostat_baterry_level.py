import requests
import json


thermostat_battery_level = requests.get('http://130.240.234.243:8083/JS/Run/zway.devices[22].instances[0].commandClasses[0x80].valueOf()', auth=('admin','11KahnaNau11'),stream=True)


print ("print thermostat_battery_level() :")
print (thermostat_battery_level.json()['data']['last']['value'])


with open("thermostat_battery_level.csv", "w") as outfile: 
    json.dump(thermostat_battery_level.json()['data']['last']['value'], outfile)     

    
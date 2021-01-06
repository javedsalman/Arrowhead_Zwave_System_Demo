import requests
import json


plug_data_energy = requests.get('http://130.240.234.243:8083/JS/Run/zway.devices[23].instances[0].commandClasses[0x32].valueOf()',auth=('admin','11KahnaNau11'),stream=True)


print ("print plug_data_energy() :")
print (plug_data_energy.json()['data']['0']['val']['value'])


with open("plug_data_power.csv", "w") as outfile: 
    json.dump(plug_data_energy.json()['data']['0']['val']['value'], outfile)     

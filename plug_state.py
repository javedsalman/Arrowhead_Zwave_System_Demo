import requests
import json


plug_state = requests.get('http://130.240.234.243:8083/JS/Run/zway.devices[23].instances[0].commandClasses[0x25].data.level', auth=('admin','11KahnaNau11'),stream=True)


print ("print plug_state() :")
print (plug_state.json()['value'])


with open("plug_state.csv", "w") as outfile: 
    json.dump(plug_state.json()['value'], outfile)     

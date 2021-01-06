import requests
import json


set_plug_on = requests.get('http://130.240.234.243:8083/JS/Run/zway.devices[23].instances[0].commandClasses[0x25].Set(255)', auth=('admin','11KahnaNau11'), stream=True)
print (set_plug_on)


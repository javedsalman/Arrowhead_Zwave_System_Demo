import requests
import json
import sys
import os
import glob


device_id = sys.argv[1]
switch_state = sys.argv[2]


switch_plug = requests.get('http://130.240.234.243:8083/JS/Run/zway.devices['+str(device_id)+'].instances[0].commandClasses[0x25].Set('+str(switch_state)+')', auth=('admin','11KahnaNau11'), stream=True)

print (switch_plug)
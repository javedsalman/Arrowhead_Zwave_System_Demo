import requests
import json

reset_energy_counter = requests.get('http://130.240.234.243:8083/JS/Run/zway.devices[22].instances[0].commandClasses[0x32].Reset()',auth=('admin','11KahnaNau11'),stream=True)

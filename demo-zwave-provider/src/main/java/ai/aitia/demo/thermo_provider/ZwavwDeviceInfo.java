package ai.aitia.demo.thermo_provider;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ZwavwDeviceInfo {
    static String[] deviceInfo = new String[4];
    static String[] deviceInfoFinal = new String[4];


    public static String [] getZwaveDeviceInfo(String device_id){


        try{

            int deviceNumber = 1;

            ProcessBuilder pbb = new ProcessBuilder("py ","C:\\Users\\javsal\\Documents\\GitHub\\arrowhead\\RPi\\device_info.py",""+device_id);
            Process pn = pbb.start();
            BufferedReader in = new BufferedReader(new InputStreamReader(pn.getInputStream()));
            String deviceID = in.readLine();
            deviceID = "  || " + "Zwave Device ID:  " + deviceID +" --> ";
            deviceInfo[0] = deviceID;
            //System.out.println(deviceID);


            String deviceName = in.readLine();
           // deviceInfo[1] = deviceName;
            //System.out.println(deviceName);

            String deviceType = in.readLine();
            deviceType = "Type:  " + deviceType + "  || ";
            deviceInfo[1] = deviceType;
            //System.out.println(deviceType);

            String deviceVendor = in.readLine();
          //  deviceInfo[3] = deviceVendor;
           // System.out.println(deviceVendor);

            if (deviceVendor.equals("None")) {
                return null;

            }
            else {return deviceInfo;}


        }catch(Exception e){System.out.println(e);}
        return deviceInfo;

    }


    public static void main(String a[]){



        deviceInfoFinal = getZwaveDeviceInfo ("16");

        if (deviceInfoFinal != null) {
            for (int i = 0; i < 4; i++) {


                System.out.println(deviceInfoFinal[i].trim());

            }
        }

        }





    }


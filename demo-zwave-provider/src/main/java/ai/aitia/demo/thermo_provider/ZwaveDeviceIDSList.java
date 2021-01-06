package ai.aitia.demo.thermo_provider;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ZwaveDeviceIDSList {

    static String zwaveDeviceListIDS;

        public static String getZwaveDeviceListIDS(){
            try{



                ProcessBuilder pb = new ProcessBuilder("py ","C:\\Users\\javsal\\Documents\\GitHub\\arrowhead\\RPi\\zwave_devices_list.py");
                Process p = pb.start();

                BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
                zwaveDeviceListIDS = in.readLine();
                return zwaveDeviceListIDS;

            }catch(Exception e){System.out.println(e);}

            return null;
        }

    public static void getZwaveDeviceInfo(String deviceId){
        try{



            ProcessBuilder pbb = new ProcessBuilder("py ","C:\\Users\\javsal\\Documents\\GitHub\\arrowhead\\RPi\\device.info.py" ,""+"16");
            Process pn = pbb.start();
            String[] deviceInfo = new String[0];
            BufferedReader in = new BufferedReader(new InputStreamReader(pn.getInputStream()));
            String deviceID = in.readLine();
           // deviceInfo[0] = deviceID.trim();
            System.out.println(deviceID);


            String deviceName = in.readLine();
           // deviceInfo[1] = deviceName.trim();
            System.out.println(deviceName);

            String deviceType = in.readLine();
           // deviceInfo[2] = deviceType.trim();
            System.out.println(deviceType);

            String deviceVendor = in.readLine();
          //  deviceInfo[3] = deviceVendor.trim();
            System.out.println(deviceVendor);


        }catch(Exception e){System.out.println(e);}

    }


        public static void main(String a[]){
            zwaveDeviceListIDS = getZwaveDeviceListIDS();
            String[] zwaveDeviceInfo = new String[0];
            String finalList = zwaveDeviceListIDS.replaceAll("\\[", "").replaceAll("\\]","");

            System.out.println("value is : "+finalList);

            String[] deviceIDS = finalList.split(",");
            for(int i=0; i < deviceIDS.length; i++) {
                System.out.println(deviceIDS[i].trim());
                getZwaveDeviceInfo ("16");
            }

           /* for(int i=0; i < 3; i++) {
                System.out.println(zwaveDeviceInfo[i].trim());
            }*/




        }

    }



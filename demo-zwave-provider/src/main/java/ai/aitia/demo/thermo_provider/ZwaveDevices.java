package ai.aitia.demo.thermo_provider;

public class ZwaveDevices {


    public static String zwaveDevices() {
        String zwaveDeviceListIDS = ai.aitia.demo.thermo_provider.ZwaveDeviceIDSList.getZwaveDeviceListIDS();
        String[] zwaveDevicesInfo = new String[5];
        String zwaveDevices = "";
        String finalList = zwaveDeviceListIDS.replaceAll("\\[", "").replaceAll("\\]", "");

        // System.out.println("value is : "+finalList);

        String[] deviceIDS = finalList.split(",");
        for (int i = 0; i < deviceIDS.length; i++) {
            //   System.out.println(deviceIDS[i].trim());
            zwaveDevicesInfo = ai.aitia.demo.thermo_provider.ZwavwDeviceInfo.getZwaveDeviceInfo(deviceIDS[i].trim());

            if (zwaveDevicesInfo != null) {
                for (int j = 0; j < 2; j++) {
                    // System.out.println(deviceIDS[i].trim());
                    //  zwaveDevicesInfo = ZwavwDeviceInfo.getZwaveDeviceInfo ("23"/*deviceIDS[i].trim()*/);
                   // System.out.println(zwaveDevicesInfo[j]);
                    zwaveDevices = zwaveDevices + " " + zwaveDevicesInfo[j];




                }
                //return zwaveDevicesInfo;
               // System.out.println("///////////////////////");
            }


        }

        return zwaveDevices;
    }


    public static void main(String a[]){

       // String [] zzwaveDeviceInfoList = ZwaveDevices.zwaveDevices();
        System.out.println(ZwaveDevices.zwaveDevices());
    }
}

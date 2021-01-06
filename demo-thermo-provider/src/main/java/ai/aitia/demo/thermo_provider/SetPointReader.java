package ai.aitia.demo.thermo_provider;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Random;
import org.springframework.stereotype.Component;

@Component
public
class SetPointReader {
    public static double readSetPoint(){
        try{


            String deviceId = "22";
            ProcessBuilder pb = new ProcessBuilder("py ","C:\\Users\\javsal\\Documents\\GitHub\\\\zwave\\demo-zwave\\demo-thermo-provider\\src\\main\\java\\ai\\aitia\\demo\\thermo_provider\\receive_thermostat_setpoint.py",""+deviceId/*,""+number2*/);
            Process p = pb.start();

            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String setPoint = in.readLine();
            double setPointValue= Double.parseDouble(setPoint);
            System.out.println("SetPoint Value is : "+setPointValue);
            return setPointValue;

        }catch(Exception e){System.out.println(e);}


        return 0;
    }


}
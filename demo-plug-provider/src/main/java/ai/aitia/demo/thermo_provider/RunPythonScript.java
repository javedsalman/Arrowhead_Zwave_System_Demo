package ai.aitia.demo.thermo_provider;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class RunPythonScript {


        public static void main(String a[]){
            try{


                String deviceId = "23";
                String switchPlug = "255";
                ProcessBuilder pb = new ProcessBuilder("py ","C:\\Users\\javsal\\Documents\\GitHub\\zwave\\backup-212021\\demo-plug\\demo-plug-provider\\src\\main\\java\\ai\\aitia\\demo\\thermo_provider\\SwitchPlug.py",""+deviceId,""+switchPlug);
                Process p = pb.start();

                BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String setPoint = in.readLine();
                double setPointValue= Double.parseDouble(setPoint);
                System.out.println("SetPoint Value is : "+setPointValue);

            }catch(Exception e){System.out.println(e);}


        }
    }


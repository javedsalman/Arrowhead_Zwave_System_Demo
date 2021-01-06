package ai.aitia.demo.thermo_provider;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class RunPythonScript {


        public static void main(String a[]){
            try{

                String prg = "import sys\nprint int(sys.argv[1])+int(sys.argv[2])\n";
                BufferedWriter out = new BufferedWriter(new FileWriter("test1.py"));
                out.write(prg);
                out.close();
                int number1 = 10;
                int number2 = 32;

                ProcessBuilder pb = new ProcessBuilder("py ","C:\\Users\\javsal\\Documents\\GitHub\\arrowhead\\RPi\\set_thermostat_setpoint.py",""+number1/*,""+number2*/);
                Process p = pb.start();

                BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String ret = in.readLine();
                System.out.println("value is : "+ret);
            }catch(Exception e){System.out.println(e);}
        }
    }


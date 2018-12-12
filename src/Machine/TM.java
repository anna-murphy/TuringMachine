package Machine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TM
{
    public static void main ( String args [] )
    {
        BufferedReader inputReader = new BufferedReader(
                new InputStreamReader(System.in));
        System.out.print("Turing Machine Specification File:  ");
        String filename = "";
        try
        {
            filename = inputReader.readLine();
        }
        catch (IOException ioe)
        {
            System.out.println(ioe);
            System.exit(0);
        }
        TuringMachine tm = new TuringMachine(filename);
        while ( true )
        {
            //  Read input
            System.out.println("--> ");
            String inputString = "";
            try
            {
                inputString = inputReader.readLine();
            }
            catch (IOException ioe)
            {
                System.out.println(ioe);
                System.exit(0);
            }
            if (inputString.length() == 0)
            {
                System.out.println("Goodbye");
                System.exit(1);
            }
            //  Process string
            tm.run(inputString);
        }
    }
}
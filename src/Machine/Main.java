package Machine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main
{
    public static void main ( String args [] )
    {
        BufferedReader inputReader = new BufferedReader(
                new InputStreamReader(System.in));
        System.out.println("Turing Machine Specification File:  ");
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
            //  Process string

        }
    }
}
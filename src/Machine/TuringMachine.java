package Machine;

import Machine.State;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TuringMachine
{
    /// States
    private ArrayList<State> states;
    private String start;
    private String accept;
    private String reject;

    /// Alphabet
    private ArrayList<Character> tapeAlphabet;
    private ArrayList<Character> inputAlphabet;

    /// Tape
    private int machineHead;
    private ArrayList<Character> tape;
    private State currentState;

    /**
     * Constructs the turing machine
     * from the specification file.
     * @param filename is a *.tm file
     *                 that should be formatted
     *                 properly.
     */
    public TuringMachine ( String filename )
    {
        // Initialize variables
        this.states = new ArrayList<>();
        this.tapeAlphabet = new ArrayList<>();
        this.inputAlphabet = new ArrayList<>();
        this.tape = new ArrayList<>();
        this.machineHead = 0;
        // Open File
        try
        {
            BufferedReader fileIn = new BufferedReader
                    ( new FileReader ( filename ) );
            String line = fileIn.readLine();
            int counter = 0;
            while ( line != null )
            {
                //System.out.println("Line: " + line);
                // Ignore Comments
                if ( line.startsWith("#") ||
                        line.length() == 0)
                {
                    line = fileIn.readLine();
                    continue;
                }
                // Analyze the other lines
                switch ( counter )
                {
                    case ( 0 ):
                        // States
                        String split [] = line.split( " " );
                        for ( String token : split )
                        {
                            states.add( new State (this, token ));
                            System.out.println("Added state: " + token);
                        }
                        break;
                    case ( 1 ):
                        //  Input Alphabet
                        String inSymbols [] = line.split ( " " );
                        for ( String symbol : inSymbols )
                        {
                            inputAlphabet.add( symbol.charAt( 0 ));
                        }
                        break;
                    case ( 2 ):
                        // Tape Alphabet
                        String tSymbols [] = line.split ( " " );
                        for ( String symbol : tSymbols )
                        {
                            tapeAlphabet.add( symbol.charAt( 0 ));
                        }
                        break;
                    case ( 3 ):
                        // Start Machine.State
                        this.start = line;
                        break;
                    case ( 4 ):
                        //  Accept Machine.State
                        this.accept = line;
                        break;
                    case ( 5 ):
                        // Reject Machine.State
                        this.reject = line;
                        break;
                    default:
                        //  Transitions
                        //  Transition Order:
                        //      Current State
                        //      Tape Character
                        //      Next State
                        //      Character to write to tape
                        //      Movement Direction
                        String instructions [] = line.split(" ");
                        System.out.println("Adding Transition to state: " + instructions[0]);
                        State currentState = getState( instructions[0]);
                        if ( currentState == null )
                        {
                            //  Invalid file format. Abort
                            System.out.println("State not found.");
                            System.exit(0);
                        }
                        currentState.addTransition(instructions);
                        break;
                }
                counter += 1;
                line = fileIn.readLine();
            }
        }
        catch (IOException ioe)
        {
            System.out.println(ioe);
        }
    }

    /**
     * Generic function that searches for
     * a state by its name, and the returns it.
     * @param stateName Name of the state being
     *                  searched for.
     * @return Correct state, or null if it doesn't
     * exist.
     */
    public State getState (String stateName )
    {
        for ( State s : this.states )
        {
            System.out.println("\t" + s);
            if ( s.getName().equals( stateName ))
            {
                return ( s );
            }
        }
        return (null);
    }

    /**
     * The following three sections are
     * methods that get three specific
     * important states: the start, accept,
     * and reject states:
     * @return start, accept, or reject state.
     */
    public State getStart ()
    {
        return getState( this.start );
    }

    public State getAccept ()
    {
        return ( getState( this.accept ));
    }

    public  State getReject ()
    {
        return ( getState ( this.accept ));
    }

    public boolean isState ( String name )
    {
        return ( getState(name) == null );
    }

    /**
     * Dumps the contents of the
     * turing machine into the terminal.
     */
    public void printStates ()
    {
        for (State s : this.states)
        {
            System.out.println(s);
        }
    }

    public String printConfig ()
    {
        String config = "";
        for (int i = 0; i < this.tape.size(); i++)
        {
            if (i == this.machineHead)
            {
                config += this.currentState;
            }
            config += tape.get(i);
        }
        return (config);
    }

    /**
     * Function that takes a string and
     * determines if it is in the language.
     * @param input from user.
     */
    public void run (String input)
    {
        //  Load the input into the tape.
        for ( Character c : input.toCharArray())
        {
            this.tape.add(c);
        }
        this.currentState = getState(this.start);
        while ( true )
        {
            printConfig();
            if (this.currentState.getName().equals(this.accept))
            {
                break;
            }
            //  
        }
    }
}
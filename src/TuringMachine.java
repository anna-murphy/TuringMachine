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

    private String input;
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
        // Open File
        try
        {
            BufferedReader fileIn = new BufferedReader
                    ( new FileReader ( filename ) );
            String line = fileIn.readLine();
            int counter = 0;
            while ( line != null )
            {
                // Ignore Comments
                if ( line.startsWith("#") )
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
                            states.add( new State ( token ));
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
                        // Start State
                        this.start = line;
                        break;
                    case ( 4 ):
                        //  Accept State
                        this.accept = line;
                        break;
                    case ( 5 ):
                        // Reject State
                        this.reject = line;
                        break;
                    default:
                        // Transitions
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
    public State getState ( String stateName )
    {
        for ( State s : this.states )
        {
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

    public boolean isAccept (State test)
    {
        return ( test.getName().equals(getAccept()));
    }

}
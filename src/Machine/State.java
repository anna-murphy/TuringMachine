package Machine;

import java.util.Hashtable;

public class State
{
    private String name;
    private Hashtable<Character, Transition> transitionTable;
    private TuringMachine machine;

    /**
     * Initializes variables
     * @param machine this state is apart of
     * @param name of the state
     */
    public State ( TuringMachine machine, String name )
    {
        this.machine = machine;
        this.name = name;
        this.transitionTable = new Hashtable<>();
    }

    /**
     * Just return the name of the state.
     * @return this.name.
     */
    @Override
    public String toString ()
    {
        String tostring = "";
        tostring += this.name;
        for (Transition t: this.transitionTable.values())
        {
            tostring += t.toString();
        }
        return (tostring);
    }

    /**
     * Return the name of the state.
     * @return this.name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Adds a transition object to the hash
     * table of transition the object has
     * @param instructions array received from
     *                     the user input.
     */
    public void addTransition ( String instructions [] )
    {
        this.transitionTable.put(
                instructions[1].charAt(0),
                new Transition(this.machine, instructions));
    }
}

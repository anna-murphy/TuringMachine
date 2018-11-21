package Machine;

import java.util.Hashtable;

public class State
{
    private String name;
    private Hashtable<Character, Transition> transitionTable;

    public State ( String name )
    {
        this.name = name;
    }

    @Override
    public String toString ()
    {
        return ( this.name );
    }

    public void addTransition ( String instructions [] )
    {

    }
}

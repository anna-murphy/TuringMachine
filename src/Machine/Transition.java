package Machine;

public class Transition
{
    /**
     * Note: direction is +1 or -1,
     * denoting the direction that the
     * tape head needs to go.
     */
    private State startingState;
    private State returnState;

    private Character tapeCharacter;
    private  Character writeCharacter;
    private Integer direction;

    /**
     * Creates a transition object.
     * The order is:
     *      Starting State
     *      Tape Character
     *      Return State
     *      Write Character
     *      Movement
     * @param tm Turning machine we're working with
     * @param instructions string array read from file
     */
    public Transition (TuringMachine tm, String instructions [])
    {
        //System.out.println("Adding a transition: ");
        int counter = 0;
        for (String token : instructions)
        {
            if (token.length() == 0)
            {
                continue;
            }
            switch (counter)
            {
                case (0):
                    //  Starting state
                    this.startingState = tm.getState(token);
                    break;
                case (1):
                    //  Tape Character
                    this.tapeCharacter = token.charAt(0);
                    break;
                case (2):
                    //  Return State
                    this.returnState = tm.getState(token);
                    break;
                case (3):
                    //  Written character
                    this.writeCharacter = token.charAt(0);
                    break;
                case (4):
                    //  Direction
                    if (token.equals("R"))
                    {
                        this.direction = 1;
                    }
                    else if (token.equals("L"))
                    {
                        this.direction = -1;
                    }
                    else
                    {
                        // Invalid file format.
                        System.out.println(
                                "Invalid direction in creating a transition.");
                        System.exit(0);
                    }
                    break;
                default:
                    break;
            }
            counter += 1;
        }
        //  Check for null states.
        if (this.startingState == null ||
                this.returnState ==null)
        {
            //  Invalid file format.
            System.out.println(
                    "State not found in creating a transition.");
            System.exit(0);
        }
    }

    /**
     * The following 5 methods are
     * getters for the class variables.
     * @return
     */
    public State getStartingState()
    {
        return startingState;
    }

    public State getReturnState()
    {
        return returnState;
    }

    public Character getWriteCharacter()
    {
        return writeCharacter;
    }

    public Integer getDirection()
    {
        return direction;
    }

    /**
     * Overridden toString method
     * @return string form of the transition
     */
    @Override
    public String toString()
    {
        return "( " +
                returnState.getName() +  ", " +
                this.tapeCharacter +  " ) -> ( " +
                this.returnState.getName() + ", " +
                this.writeCharacter + ", " +
                direction + " )";
    }

    /**
     * Overridden hash code method
     * @return integer hashcode
     */
    @Override
    public int hashCode()
    {
        return toString().hashCode();
    }
}
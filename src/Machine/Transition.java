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
        this.startingState = tm.getState(instructions[0]);
        this.tapeCharacter = instructions[1].charAt(0);
        this.returnState = tm.getState(instructions[2]);
        this.writeCharacter = instructions[3].charAt(0);
        if (instructions[4] == "R")
        {
            this.direction = 1;
        }
        else if (instructions[4] =="L")
        {
            this.direction = -1;
        }
        else
        {
            // Invalid file format.
            System.exit(0);
        }
        if (this.startingState == null ||
                this.returnState ==null)
        {
            //  Invalid file format.
            System.exit(0);
        }
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
                this.returnState + ", " +
                this.writeCharacter + ", " +
                direction.toString() + " )";
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

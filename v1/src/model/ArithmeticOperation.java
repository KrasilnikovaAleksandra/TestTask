package model;

/**
 * It is class of model arithmetic Operation
 */
public class ArithmeticOperation {

    private String first;
    private String second;
    private String operation;

    /**
     *It is the empty constructor.
     */
    public ArithmeticOperation() {
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}

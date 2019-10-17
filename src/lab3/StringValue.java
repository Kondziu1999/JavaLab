package lab3;

public class StringValue extends Value{

    private String value;

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public Value add(Value value) {
        StringBuilder builder= new StringBuilder();

    }

    @Override
    public Value sub(Value value) {
        throw new IllegalArgumentException("cannot compare String");    }

    @Override
    public Value mul(Value value) {
        throw new IllegalArgumentException("cannot compare String");    }

    @Override
    public Value div(Value value) {
        throw new IllegalArgumentException("cannot compare String");    }

    @Override
    public Value pow(Value value) {
        return null;
    }

    @Override
    public boolean eq(Value value) {
        return equals(value);
    }

    @Override
    public boolean lte(Value value) {
        throw new IllegalArgumentException("cannot compare String");
    }

    @Override
    public boolean gte(Value value) {
        throw new IllegalArgumentException("cannot compare String");    }

    @Override
    public boolean neq(Value value) {
        throw new IllegalArgumentException("cannot compare String");    }

    @Override
    public boolean equals(Object other) {
        if(checkTypeEq(other)){

            String val=((StringValue)other).getValue();
            return this.value.equals(val);
        }
        else{
            throw new IllegalArgumentException("wrong type of argument");
        }

    }

    @Override
    public int hashCode() {
        return this.hashCode();

    }

    private <T> boolean checkTypeEq(T object){
        if(object instanceof StringValue){
            return true;
        }
        return false;
    }
}

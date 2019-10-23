package lab3;

public class StringValue extends Value{

    private String value;

    @Override
    public Value clone() {
       return StringValue.create(value.toString());
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public Value add(Value value) {
       if(checkTypeEq(value)) {
           this.value = this.value + value.toString();
           return this;
       }
       else {
           throw new IllegalArgumentException("wrong type of argument");
       }
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

    public static Value create(String s) {
        String val= String.valueOf(s);
        Value integerValue=new StringValue(val);

        return integerValue;
    }

    private StringValue(String value){
        this.value=value;
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

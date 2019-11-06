package lab4;

public class StringValue extends Value {

    private String value;

    public StringValue(){};

    @Override
    public Value clone() {
       return this.create(value.toString());
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
        int ifTrue=this.value.compareTo(((StringValue)value).getValue());
        if(ifTrue<=0){
         return true;
        }
        //throw new IllegalArgumentException("cannot compare String");
        return false;
    }

    @Override
    public boolean gte(Value value) {
        int ifTrue=this.value.compareTo(((StringValue)value).getValue());
        if(ifTrue>=0){
            return true;
        }
        //throw new IllegalArgumentException("cannot compare String");
        return false;
    }

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
    public  Value create(String s) {
        String val= String.valueOf(s);
        Value integerValue=new StringValue(val);

        return integerValue;
    }
    //refer to particular Value
    public static Value build(String s) {
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

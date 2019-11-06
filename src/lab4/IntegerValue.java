package lab4;

public class IntegerValue extends Value {

    private Integer value;
    @Override
    public  Value create(String s) {
        Integer val= Integer.valueOf(s);
        Value integerValue=new IntegerValue(val);

        return integerValue;
    }
    public static Value build(String s) {
        Integer val= Integer.valueOf(s);
        Value integerValue=new IntegerValue(val);

        return integerValue;
    }


    public IntegerValue(){};
    @Override
    public Value clone() { return this.create(value.toString());
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    @Override
    public Value add(Value value) {
        if(checkTypeEq(value)){

            Integer val1=this.getValue();
            Integer val2=((IntegerValue)value).getValue();

            int result= (int)val1+val2;

            return new IntegerValue(result);
        }
        else {
            throw new IllegalArgumentException("wrong type of argument");
        }

    }

    @Override
    public Value sub(Value value) {
        if(checkTypeEq(value)){

            Integer val1=this.getValue();
            Integer val2=((IntegerValue)value).getValue();

            int result= (int)val1-val2;

            return new IntegerValue(result);
        }
        else {
            throw new IllegalArgumentException("wrong type of argument");
        }

    }

    @Override
    public Value mul(Value value) {
        if(checkTypeEq(value)){

            Integer val1=this.getValue();
            Integer val2=((IntegerValue)value).getValue();

            int result= (int) val1*val2;

            return new IntegerValue(result);
        }
        else {
            throw new IllegalArgumentException("wrong type of argument");
        }

    }

    @Override
    public Value div(Value value) {
        if(checkTypeEq(value)){

            Integer val1=this.getValue();
            Integer val2=((IntegerValue)value).getValue();

            int result= (int)val1/val2;

            return new IntegerValue(result);
        }
        else {
            throw new IllegalArgumentException("wrong type of argument");
        }

    }

    @Override
    public Value pow(Value value) {
        if(checkTypeEq(value)){

            Integer val1=this.getValue();
            Integer val2=((IntegerValue)value).getValue();

            int result= (int) Math.pow(val1,val2);

            return new IntegerValue(result);
        }
        else {
            throw new IllegalArgumentException("wrong type of argument");
        }

    }



    @Override
    public boolean eq(Value value) {
        return equals(value);
    }

    @Override
    public boolean lte(Value value) {
        if(checkTypeEq(value)){

            return (((IntegerValue)value).getValue())>(this.getValue());
        }
        else {
            throw new IllegalArgumentException("wrong type of argument");
        }
    }

    @Override
    public boolean gte(Value value) {
        if(checkTypeEq(value)){

            return (((IntegerValue)value).getValue())<(this.getValue());
        }
        else {
            throw new IllegalArgumentException("wrong type of argument");
        }
    }

    @Override
    public boolean neq(Value value) {
        return  !equals(value);
    }

    @Override
    public boolean equals(Object value) {

        if(checkTypeEq(value)) {


            return ((IntegerValue)value).getValue().equals(this.getValue());
        }
        else {
            throw new IllegalArgumentException("wrong type of argument");
        }
    }

    @Override
    public int hashCode() {
        return this.hashCode();
    }



    private IntegerValue(Integer value){
        this.value=value;
    }

    public Integer getValue() {
        return value;
    }

    private <T> boolean checkTypeEq(T object){
        if(object instanceof IntegerValue){
            return true;
        }
        return false;
    }

}

package lab3;

public class FloatValue extends Value {
    private Float value;

    @Override
    public Value clone() {
        return FloatValue.create(value.toString());
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
    public Float getValue() {
        return this.value;
    }

    @Override
    public Value add(Value value) {
        if(checkTypeEq(value)){

            Float val1=this.getValue();
            Float val2=((FloatValue)value).getValue();

            float result= (float)val1+val2;

            return new FloatValue(result);
        }
        else {
            throw new IllegalArgumentException("wrong type of argument");
        }

    }

    @Override
    public Value sub(Value value) {
        if(checkTypeEq(value)){

            Float val1=this.getValue();
            Float val2=((FloatValue)value).getValue();

            float result= (float)val1-val2;

            return new FloatValue(result);
        }
        else {
            throw new IllegalArgumentException("wrong type of argument");
        }

    }

    @Override
    public Value mul(Value value) {
        if(checkTypeEq(value)){

            Float val1=this.getValue();
            Float val2=((FloatValue)value).getValue();

            float result= (float) val1*val2;

            return new FloatValue(result);
        }
        else {
            throw new IllegalArgumentException("wrong type of argument");
        }

    }

    @Override
    public Value div(Value value) {
        if(checkTypeEq(value)){

            Float val1=this.getValue();
            Float val2=((FloatValue)value).getValue();

            float result= (float)val1/val2;

            return new FloatValue(result);
        }
        else {
            throw new IllegalArgumentException("wrong type of argument");
        }

    }

    @Override
    public Value pow(Value value) {
        if(checkTypeEq(value)){

            Float val1=this.getValue();
            Float val2=((FloatValue)value).getValue();

            float result= (float) Math.pow(val1,val2);

            return new FloatValue(result);
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

            return (((FloatValue)value).getValue())<(this.getValue());
        }
        else {
            throw new IllegalArgumentException("wrong type of argument");
        }
    }

    @Override
    public boolean gte(Value value) {
        if(checkTypeEq(value)){

            return (((FloatValue)value).getValue())>(this.getValue());
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


            return ((FloatValue)value).getValue().equals(this.getValue());
        }
        else {
            throw new IllegalArgumentException("wrong type of argument");
        }
    }

    @Override
    public int hashCode() {
        return this.hashCode();
    }

    public static Value create(String s) {
        Float val= Float.valueOf(s);
        Value floatValue=new FloatValue(val);

        return floatValue;
    }

    private FloatValue(Float value){
        this.value=value;
    }



    private <T> boolean checkTypeEq(T object){
        if(object instanceof FloatValue){
            return true;
        }
        return false;
    }
}

package lab4;


public class DoubleValue extends Value {
    private Double value;

    public DoubleValue(){};
    @Override
    public Value clone() {
        return this.create(value.toString());
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    @Override
    public Value add(Value value) {
        if(checkTypeEq(value)){

            Double val1=this.getValue();
            Double val2=((DoubleValue)value).getValue();

            double result= (double)val1+val2;

            return new DoubleValue(result);
        }
        else {
            throw new IllegalArgumentException("wrong type of argument");
        }

    }

    @Override
    public Value sub(Value value) {
        if(checkTypeEq(value)){

            Double val1=this.getValue();
            Double val2=((DoubleValue)value).getValue();

            double result= (double)val1-val2;

            return new DoubleValue(result);
        }
        else {
            throw new IllegalArgumentException("wrong type of argument");
        }

    }

    @Override
    public Value mul(Value value) {
        if(checkTypeEq(value)){

            Double val1=this.getValue();
            Double val2=((DoubleValue)value).getValue();

            double result= (double) val1*val2;

            return new DoubleValue(result);
        }
        else {
            throw new IllegalArgumentException("wrong type of argument");
        }

    }

    @Override
    public Value div(Value value) {
        if(checkTypeEq(value)){

            Double val1=this.getValue();
            Double val2=((DoubleValue)value).getValue();

            double result= (double)val1/val2;

            return new DoubleValue(result);
        }
        else {
            throw new IllegalArgumentException("wrong type of argument");
        }

    }

    @Override
    public Value pow(Value value) {
        if(checkTypeEq(value)){

            Double val1=this.getValue();
            Double val2=((DoubleValue)value).getValue();

            double result= (double) Math.pow(val1,val2);

            return new DoubleValue(result);
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

            return (((DoubleValue)value).getValue())>(this.getValue());
        }
        else {
            throw new IllegalArgumentException("wrong type of argument");
        }
    }

    @Override
    public boolean gte(Value value) {
        if(checkTypeEq(value)){

            return (((DoubleValue)value).getValue())<(this.getValue());
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


            return ((DoubleValue)value).getValue().equals(this.getValue());
        }
        else {
            throw new IllegalArgumentException("wrong type of argument");
        }
    }

    @Override
    public int hashCode() {
        return this.hashCode();
    }

    @Override
    public Value create(String s) {
        Double val= Double.valueOf(s);
        Value doubleValue=new DoubleValue(val);

        return doubleValue;
    }
    ///to refer to particular Value
    public static Value build(String s){
        Double val= Double.valueOf(s);
        Value doubleValue=new DoubleValue(val);

        return doubleValue;
    }

    private DoubleValue(Double value){
        this.value=value;
    }

    public Double getValue() {
        return value;
    }

    private <T> boolean checkTypeEq(T object){
        if(object instanceof DoubleValue){
            return true;
        }
        return false;
    }

}

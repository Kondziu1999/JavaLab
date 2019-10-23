package lab3;



public class DoubleValue extends Value {
    private Double value;


    @Override
    public Value clone() {
        return DoubleValue.create(value.toString());
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
    public lab3.Value sub(lab3.Value value) {
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
    public lab3.Value mul(lab3.Value value) {
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
    public lab3.Value div(lab3.Value value) {
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
    public lab3.Value pow(lab3.Value value) {
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
    public boolean eq(lab3.Value value) {
        return equals(value);
    }

    @Override
    public boolean lte(lab3.Value value) {
        if(checkTypeEq(value)){

            return (((DoubleValue)value).getValue())<(this.getValue());
        }
        else {
            throw new IllegalArgumentException("wrong type of argument");
        }
    }

    @Override
    public boolean gte(lab3.Value value) {
        if(checkTypeEq(value)){

            return (((DoubleValue)value).getValue())>(this.getValue());
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

    public static Value create(String s) {
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

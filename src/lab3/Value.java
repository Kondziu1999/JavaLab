package lab3;

public abstract class Value  {



    public abstract String toString();
    public abstract Value add(Value value);
    public abstract Value sub(Value value);
    public abstract Value mul(Value value);
    public abstract Value div(Value value);
    public abstract Value pow(Value value);
    public abstract boolean eq(Value value);
    public abstract boolean lte(Value value);
    public abstract boolean gte(Value value);
    public abstract boolean neq(Value value);
    public abstract boolean equals(Object other);
    public abstract int hashCode();

    public static Value create(String s) {
        return null;
    }


}

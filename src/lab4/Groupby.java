package lab4;

public interface Groupby {

    DataFrameCol max();
    DataFrameCol min();
    DataFrameCol mean();
    DataFrameCol std();
    DataFrameCol sum();
    DataFrameCol var();
    DataFrameCol apply();
}

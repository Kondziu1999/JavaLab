package lab4;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class GroupbyImpl implements Groupby {

    private DataFrameCol df;
    private String colToGroup;
    private String[] mColumnNames;
    private List<ArrayList<Value>> mColumns;
    private Value[] mColumnsTypes;
    private List<DataFrameCol> groups;
    private int index;

    //przekazuje dataFrame
    public GroupbyImpl(DataFrameCol df, String colToGroup, String[] mColumnNames,Value[] mColumnsTypes, List<ArrayList<Value>> mColumns){
        this .df=df;
        this.colToGroup=colToGroup;
        this.mColumnNames=mColumnNames;
        this.mColumnsTypes=mColumnsTypes;
        this.mColumns=mColumns;
        group(colToGroup);
    }

    //grupuje dataFrame
    private void group(String colToGroup){
        //lista mini dataFrame wynikowa


        //hasz mapa do szukania uniklanych kluczy i wskazywania na ramke ktora je przechowuje
        Map<String, DataFrameCol> idMap = new HashMap<>();
        this.groups=new LinkedList<>();

        //index kolumny wzgledej ktorej grupujemy
        Integer groupByIndex=null;
        for(int i=0; i<mColumnNames.length; i++){
            if(mColumnNames[i].equals(colToGroup)){
                groupByIndex=i;
                index=groupByIndex;
                break;
            }
        }
        if(groupByIndex==null){
            throw new RuntimeException("Column not found" + colToGroup);
        }

        //dodawanie grup
        //licznik wierszy(nie chce zamieniac fora zakresowego)
        int rowNumber=0;
        for(Value val:mColumns.get(groupByIndex.intValue())){
            //DataFrameCol tempDf=new DataFrameCol(new String[]{val.toString()},new Value[]{val});


            //n
            String [] miniDataFrameColumnsNames;
            miniDataFrameColumnsNames=mColumnNames;
            //miniDataFrameColumnsNames[groupByIndex.intValue()]=val.toString();

            //jesli element istnieje zwraca ten element(istniejacy) a jak NIE istnieje to null
            String valString=val.toString();
            //nie ma vara w jvm 8 :((((
            Object exist=idMap.putIfAbsent(valString,new DataFrameCol(this.mColumnNames,this.mColumnsTypes));

                //wiersz z danymi z przetwarzanej ramki
            DataFrameCol row = df.iloc(rowNumber);

                //dodajemy wiersz z danymi

                var x = idMap.get(valString);
                x.addRow(row);

            rowNumber++;
        }
        //przerzucenie wartosci mapy do listy data framow
        for(var x:idMap.values()){
            this.groups.add(x);
        }
        //this.groups= (List<DataFrameCol>) idMap.values();
        //TO DO posortuj kompratorem ale uwazaj bo pierwszy wiersz w kolumnie to nazwa kolumny wiec wypada go od
    }
    @Override
    public DataFrameCol max() {
        DataFrameCol finalDf=new DataFrameCol(mColumnNames,mColumnsTypes);
        List<ArrayList<Value>> mColumnsFinal=finalDf.getmColumns();
        //przechodzimy po data frames
        for(DataFrameCol df:groups){
            int colNumber=0;
            //przechodzimy po kolumnach
            for(List<Value> col:df.getmColumns()){
                //przechodzimy po wierszach
                Value max=col.get(1);
                for(Value val:col){
                    if(val.gte(max)){
                        max=val;
                    }
                }
                mColumnsFinal.get(colNumber).add(max);
                colNumber++;
            }
        }
        return finalDf;
    }
    @Override
    public DataFrameCol min() {
        DataFrameCol finalDf=new DataFrameCol(mColumnNames,mColumnsTypes);
        List<ArrayList<Value>> mColumnsFinal=finalDf.getmColumns();
        //przechodzimy po data frames
        for(DataFrameCol df:groups) {
            int colNumber = 0;
            //przechodzimy po kolumnach
            for (List<Value> col : df.getmColumns()) {
                //przechodzimy po wierszach
                try {
                    if(col.size()==1){
                        throw new RuntimeException("Byku po tym nie grupuj");
                    }
                    Value min = col.get(1);


                    for (Value val : col) {
                        if (val.lte(min)) {
                            min = val;
                        }
                    }
                    mColumnsFinal.get(colNumber).add(min);
                    colNumber++;

                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return finalDf;
    }
    @Override
    public DataFrameCol mean() {
        DataFrameCol finalDf=new DataFrameCol(mColumnNames,mColumnsTypes);
        List<ArrayList<Value>> mColumnsFinal=finalDf.getmColumns();
        //przechodzimy po data frames
        for(DataFrameCol df:groups){
            int colNumber=0;
            //przechodzimy po kolumnach
            for(List<Value> col:df.getmColumns()){

                //jesli nie mamy do czynienia z Stringiem to dzialamy dalej
                if(col.get(0) instanceof StringValue){
                    mColumnsFinal.get(colNumber).add(StringValue.build("cannot create mean from Strings"));


                }
                else {
                    //przechodzimy po wierszach
                    Double sum = 0.0;
                    for (Value val : col) {
                        sum = sum + Double.parseDouble(val.toString());
                    }

                    Double mean = (double) (sum / col.size());

                    mColumnsFinal.get(colNumber).add(DoubleValue.build(mean.toString()));

                }
                colNumber++;
            }
        }
        return finalDf;
    }
    @Override
    public DataFrameCol std() {
        DataFrameCol finalDf=new DataFrameCol(mColumnNames,mColumnsTypes);
        List<ArrayList<Value>> mColumnsFinal=finalDf.getmColumns();
        //przechodzimy po data frames
        for(DataFrameCol df:groups){
            int colNumber=0;
            //przechodzimy po kolumnach
            for(List<Value> col:df.getmColumns()){

                //jesli nie mamy do czynienia z Stringiem to dzialamy dalej
                if(col.get(0) instanceof StringValue ) {
                    mColumnsFinal.get(colNumber).add(StringValue.build("cannot create std from Strings"));
                    colNumber++;

                }
                else {
                    //przechodzimy po wierszach
                    Double sum = 0.0;
                    for (Value val : col) {
                        sum = sum + Double.parseDouble(val.toString());
                    }

                    Double mean = (double) (sum / col.size());

                    //odchylenie standardowe
                    Double varSum=0.0;
                    for (Value val : col) {
                        varSum = varSum + Math.pow((Double.parseDouble(val.toString())-mean),2);
                    }
                    Double std=Math.sqrt(varSum/col.size());

                    mColumnsFinal.get(colNumber).add(DoubleValue.build(std.toString()));
                    colNumber++;
                }

            }
        }
        return finalDf;

    }

    @Override
    public DataFrameCol sum() {
        DataFrameCol finalDf=new DataFrameCol(mColumnNames,mColumnsTypes);
        List<ArrayList<Value>> mColumnsFinal=finalDf.getmColumns();
        //przechodzimy po data frames
        for(DataFrameCol df:groups){
            int colNumber=0;
            //przechodzimy po kolumnach
            for(List<Value> col:df.getmColumns()){

                //jesli nie mamy do czynienia z Stringiem to dzialamy dalej
                if(col.get(0) instanceof StringValue ) {
                    mColumnsFinal.get(colNumber).add(StringValue.build("cannot create mean from Strings"));
                    colNumber++;

                }
                else {
                    //przechodzimy po wierszach
                    Double sum = 0.0;
                    for (Value val : col) {
                        sum = sum + Double.parseDouble(val.toString());
                    }



                    mColumnsFinal.get(colNumber).add(DoubleValue.build(sum.toString()));
                    colNumber++;

                }

            }
        }
        return finalDf;
    }

    @Override
    public DataFrameCol var() {
        DataFrameCol finalDf=new DataFrameCol(mColumnNames,mColumnsTypes);
        List<ArrayList<Value>> mColumnsFinal=finalDf.getmColumns();
        //przechodzimy po data frames
        for(DataFrameCol df:groups){
            int colNumber=0;
            //przechodzimy po kolumnach
            for(List<Value> col:df.getmColumns()){

                //jesli nie mamy do czynienia z Stringiem to dzialamy dalej
                if(col.get(0) instanceof StringValue ) {
                    mColumnsFinal.get(colNumber).add(StringValue.build("cannot create var from Strings"));
                    colNumber++;
                }
                else {
                    //przechodzimy po wierszach
                    Double sum = 0.0;
                    for (Value val : col) {
                        sum = sum + Double.parseDouble(val.toString());
                    }

                    Double mean = (double) (sum / col.size());

                    //wariancja
                    Double varSum=0.0;
                    for (Value val : col) {
                        varSum = varSum + Math.pow((Double.parseDouble(val.toString())-mean),2);
                    }
                    Double variance=varSum/col.size();

                    mColumnsFinal.get(colNumber).add(DoubleValue.build(variance.toString()));
                    colNumber++;

                }

            }
        }
        return finalDf;
    }

    @Override
    public DataFrameCol apply(Applyable apply) {
        //oddelegowanie wykonania do Applyable
        return apply.apply(df);
    }
}

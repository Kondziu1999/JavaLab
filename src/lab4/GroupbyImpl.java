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
    private List<DataFrameCol> groups=new LinkedList<>();
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
        HashMap<String, DataFrameCol> idMap = new HashMap<>();

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
            miniDataFrameColumnsNames[groupByIndex.intValue()]=val.toString();



            //jesli element istnieje zwraca ten element(istniejacy) a jak NIE istnieje to null

            //nie ma vara w jvm 8 :((((
            Object exist=idMap.putIfAbsent(val.toString(),new DataFrameCol(mColumnNames,mColumnsTypes));
            //istnieje
            if(exist==null){
                //wiersz z danymi z przetwarzanej ramki
                DataFrameCol row=df.iloc(rowNumber);

                //dodajemy wiersz z danymi
                idMap.get(val.toString()).addRow(row);
            }
            //nie istnieje (NULL)
            else{
                //nic nie robimy DataFrame sie stworzyl juz
            }
            rowNumber++;
        }
        //przerzucenie wartosci mapy do listy data framow
        this.groups= (List<DataFrameCol>) idMap.values();
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
        for(DataFrameCol df:groups){
            int colNumber=0;
            //przechodzimy po kolumnach
            for(List<Value> col:df.getmColumns()){
                //przechodzimy po wierszach
                Value min=col.get(1);
                for(Value val:col){
                    if(val.lte(min)){
                        min=val;
                    }
                }
                mColumnsFinal.get(colNumber).add(min);
                colNumber++;
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
                if(col.get(colNumber) instanceof StringValue ) {
                    //przechodzimy po wierszach
                    Integer sum = 0;
                    for (Value val : col) {
                        sum = sum + Integer.parseInt(val.toString());
                    }

                    Double mean = (double) (sum / col.size());

                    mColumnsFinal.get(colNumber).add(DoubleValue.create(mean.toString()));
                    colNumber++;

                }
                else {
                    mColumnsFinal.get(colNumber).add(StringValue.create("cannot create mean from Strings"));
                    colNumber++;
                }

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
                if(col.get(colNumber) instanceof StringValue ) {
                    //przechodzimy po wierszach
                    Double sum = 0.0;
                    for (Value val : col) {
                        sum = sum + Double.parseDouble(val.toString());
                    }

                    Double mean = (double) (sum / col.size());

                    //odchylenie standardowe
                    Double varSum=0.0;
                    for (Value val : col) {
                        varSum = varSum + (Integer.parseInt(val.toString())-mean);
                    }
                    Double std=Math.sqrt(varSum/col.size());

                    mColumnsFinal.get(colNumber).add(DoubleValue.create(std.toString()));
                    colNumber++;

                }
                else {
                    mColumnsFinal.get(colNumber).add(StringValue.create("cannot create std from Strings"));
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
                if(col.get(colNumber) instanceof StringValue ) {
                    //przechodzimy po wierszach
                    Double sum = 0.0;
                    for (Value val : col) {
                        sum = sum + Double.parseDouble(val.toString());
                    }



                    mColumnsFinal.get(colNumber).add(DoubleValue.create(sum.toString()));
                    colNumber++;

                }
                else {
                    mColumnsFinal.get(colNumber).add(StringValue.create("cannot create mean from Strings"));
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
                if(col.get(colNumber) instanceof StringValue ) {
                    //przechodzimy po wierszach
                    Double sum = 0.0;
                    for (Value val : col) {
                        sum = sum + Double.parseDouble(val.toString());
                    }

                    Double mean = (double) (sum / col.size());

                    //wariancja
                    Double varSum=0.0;
                    for (Value val : col) {
                        varSum = varSum + (Integer.parseInt(val.toString())-mean);
                    }
                   Double variance=varSum/col.size();

                    mColumnsFinal.get(colNumber).add(DoubleValue.create(variance.toString()));
                    colNumber++;

                }
                else {
                    mColumnsFinal.get(colNumber).add(StringValue.create("cannot create var from Strings"));
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

//package com.javalab.demo.oldDf;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//
//public class DataFrameCol{
//    private List<ArrayList<Value>> mColumns;
//
//    protected String[] mColumnNames;
//    protected Value[] mColumnTypes;
//
//    public DataFrameCol(String[] columnNames, Value[] columnTypes) {
//        mColumns=new ArrayList<>();
//        mColumnNames = columnNames;
//        mColumnTypes=columnTypes;
//        for(int i=0; i<columnNames.length;i++){
//            mColumns.add(new ArrayList<>());
//        }
//    }
//
//    public DataFrameCol(String file, Value[] colTypes, String[] ColumnNames) {
//
//        BufferedReader br;
//        String strLine;
//        try {
//            FileInputStream inputStream = new FileInputStream(file);
//            br = new BufferedReader(new InputStreamReader(inputStream));
//
//            //przypisanie nazw do kolumn
//            if(ColumnNames==null){
//                mColumnNames= new String[colTypes.length];
//
//                String str=br.readLine();
//                String[] data=str.split(",");
//                mColumnNames=data;
//
//            }
//
//            List<ArrayList<Value>> df=new ArrayList<>();
//
//            //stworzenie kolumn(nazwa i typ jest w osobnych tablicach)
//            for(int i=0; i<mColumnNames.length; i++){
//                ArrayList<Value> temp=new ArrayList<>();
//                df.add(temp);
//            }
//
//
//            while ((strLine = br.readLine()) != null)   {
//                String[]data=strLine.split(",");
//
//                for(int i=0; i<df.size(); i++){
//                    var col=df.get(i);
//                    //wyluskac kolumne sprawdzic jaka Value odpowiada i stworzyc obiekt z stringa
//                    String str=data[i];
//                    Value newValue=colTypes[i].create(str);
//                    col.add(newValue);
//                }
//
//            }
//            //przypisanie referencji do ramki danych klasy
//            mColumns=df;
//
//            br.close();
//
//        } catch (FileNotFoundException e) {
//            e.getStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    /*
//    – zwracającą ilość wierszy w całej DF (uwaga – DF nie może mieć jednej z kolumn dłuższej niż pozostałe
//     */
//    public int size()
//    {
//        ArrayList<Value> firstColumn = mColumns.get(0);
//
//        if (firstColumn == null) {
//            return 0;
//        }
//
//        return firstColumn.size();
//    }
//
//    /*
//     – zwracającą kolumnę o podanej nazwie
//     */
//    public ArrayList<Value> get(String columnName)
//    {
//        int columnIndex = -1;
//
//        for (int i = 0; i < mColumnNames.length; i++) {
//            if (mColumnNames[i].equals(columnName)) {
//                columnIndex = i;
//                break;
//            }
//        }
//
//        if (columnIndex == -1) {
//            return null;
//        }
//
//        return mColumns.get(columnIndex);
//    }
//
//    /*
//    – zwracającą nową DataFrame z kolumnami podanymi jako parametry. W zależności od wartości parametru copy albo tworzona jest głęboka kopia, albo płytka.
//     */
//    public DataFrameCol get(String[] colNames, boolean copy)
//    {
//        List<ArrayList<Value>> columns = new ArrayList<>();
//        Value[] colTypes= new Value[colNames.length];
//
//        for (int i = 0; i < colNames.length; i++) {
//            String currentColName = colNames[i];
//            ArrayList<Value> currentColumn = get(currentColName);
//
//            for (int j = 0; j < mColumnNames.length; j++)
//            {
//                if (mColumnNames[j].equals(currentColName))
//                {
//                    colTypes[i] = mColumnTypes[j];
//                    break;
//                }
//            }
//
//            if(copy)
//            {
//                ArrayList<Value> copiedColumn = new ArrayList<>();
//
//                for (Value x: currentColumn){
//                    copiedColumn.add(x.clone());
//                }
//
//                columns.add(copiedColumn);
//            }
//            else
//            {
//                columns.add(currentColumn);
//            }
//        }
//
//        DataFrameCol dataFrame = new DataFrameCol(colNames, colTypes);
//
//        dataFrame.set(columns);
//
//        return dataFrame;
//    }
//
//    /*
//     – zwracającą wiersz o podanym indeksie (jako nową DataFrame)
//     */
//    public DataFrameCol iloc(int i)
//    {
//        DataFrameCol rowDataFrame = new DataFrameCol(mColumnNames, mColumnTypes);
//
//        List<ArrayList<Value>> columnsForRowDataFrame = getRowsFromTo(i, i);
//
//        rowDataFrame.set(columnsForRowDataFrame);
//
//        return rowDataFrame;
//    }
//
//    /*
//     – zwracającą nową DataFrame z wierszami z podanego zakresu
//     */
//    public DataFrameCol iloc(int from, int to)
//    {
//        DataFrameCol rowDataFrame = new DataFrameCol(mColumnNames, mColumnTypes);
//
//        List<ArrayList<Value>> columnsForRowDataFrame = getRowsFromTo(from, to);
//
//        rowDataFrame.set(columnsForRowDataFrame);
//
//        return rowDataFrame;
//    }
//
//    public void set(List<ArrayList<Value>> columns)
//    {
//        mColumns = columns;
//    }
//
//    private List<ArrayList<Value>> getRowsFromTo(int from, int to)
//    {
//        List<ArrayList<Value>> columns = new ArrayList<ArrayList<Value>>();
//
//        for (ArrayList<Value> column: mColumns)
//        {
//            ArrayList<Value> newColumn = new ArrayList<>();
//
//            for (int i = from; i <= to; i++)
//            {
//                Value valueAtIndex = column.get(i);
//                newColumn.add(valueAtIndex);
//            }
//
//            columns.add(newColumn);
//        }
//
//        return columns;
//    }
//    public void addRow(DataFrameCol dfRow){
//
//        for(int i=0; i<mColumnNames.length; i++){
//            var a=dfRow.get(mColumnNames[i]);
//            Value x=a.get(0);
//            var col=mColumns.get(i);
//            col.add(x);
//        }
//    }
//
//
//    public GroupbyImpl groupBy(String colToGroup){
//
//        //builder zwracamy tak naprawde GroupByImpl i na nim wywołujemy interesujace nas funkcje
//        return new GroupbyImpl(this,colToGroup,mColumnNames,mColumnTypes,mColumns);
//
//    }
//
//    public List<ArrayList<Value>> getmColumns() {
//        return mColumns;
//    }
//
//
//
//    public String print(){
//        StringBuilder builder= new StringBuilder();
//        for(String name:mColumnNames){
//            builder.append(name);
//            builder.append("                     |");
//        }
//        System.out.println(builder.toString());
//        System.out.println("\n");
//
//        for(int row=0; row<mColumns.get(0).size(); row++){
//
//            builder.setLength(0);
//
//            for(List<Value> col:mColumns){
//                builder.append(col.get(row).toString());
//                builder.append("                     |");
//            }
//            System.out.println(builder.toString());
//            System.out.println("\n");
//        }
//
//        return "xd";
//    }
//}
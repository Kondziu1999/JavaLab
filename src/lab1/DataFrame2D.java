package lab1;

import java.io.*;
import java.nio.BufferOverflowException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DataFrame2D {

    protected List<List<Object>> dataFrame;
    protected List<Object> colNames;
    protected   List<Object> colTypes;

    // kolejnosc nazw adekwatna do kolejnosci list w ramce
    public DataFrame2D(String[] colNames, String[]colTypes) {
        this.colNames=new ArrayList<>(colNames.length);
        this.colTypes=new ArrayList<>(colTypes.length);

        this.colNames.addAll(Arrays.asList(colNames));
        this.colTypes.addAll(Arrays.asList(colTypes));

        this.dataFrame=new ArrayList<>(colNames.length);
    }

    public DataFrame2D(){}

    public DataFrame2D(String file, String[] colTypes, String[] colNames) {

        BufferedReader br;
        String strLine;
        try {
            FileInputStream inputStream = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(inputStream));

            if(colNames==null){
                colNames= new String[colTypes.length];


                    String str=br.readLine();
                    String[] data=str.split(",");
                    colNames=data;



            }
            DataFrame2D dataFrame2D=new DataFrame2D(colNames,colTypes);
            List<List<Object>> df=new LinkedList<>();
            while ((strLine = br.readLine()) != null)   {
                    String[]data=strLine.split(",");
                    int count=0;
                    List<Object> list= new LinkedList<>();
                    while(count<3){
                        list.add(data[count]);
                        count++;
                    }
                df.add(list);
            }

            br.close();
            dataFrame=df;
        } catch (FileNotFoundException e) {
            e.getStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


//Close the input stream

    }

    public List<Object> get(String name){
        System.out.println("xdd");
        boolean contains=colNames.contains(name);
        if(contains){
            int index=colNames.indexOf(name);
            return dataFrame.get(index);
        }
        return null;
    }

    public DataFrame2D get(String[] cols, boolean copy){
        int[] exising=new int[colNames.size()];
        String[] newNames=new String[cols.length];

        //zerowanie indeksów
        for(int i=0; i<exising.length;i++){
            exising[i]=0;
        }

        for(int i=0; i<cols.length; i++){
            if(colNames.contains(cols[i])){
                //index typu
                int index=colNames.indexOf(cols[i]);
                newNames[i]=colTypes.get(index).toString();
                exising[index]=1;
            }
        }
        //tworzenie listy list z danymi;
        List<List<Object>> data=new ArrayList<>(cols.length);

        if(copy) {
            for (int i = 0; i < exising.length; i++) {
                if (exising[i] == 1) {
                    List<Object> temp=new ArrayList<>(cols.length);
                    //nowe stringi nie referencje
                    for (var x:dataFrame.get(i)){
                        temp.add(new StringBuilder(x.toString()).toString());
                    }
                }
            }
        }
        else {
            for (int i = 0; i < exising.length; i++) {
                if (exising[i] == 1) {
                    data.add(dataFrame.get(i));
                }
            }
        }
        DataFrame2D  newDf = new DataFrame2D (cols, newNames);
        newDf.setDataFrame(data);

        return newDf;
    }

    public DataFrame2D  iloc(int index){
        String name=colNames.get(index).toString();
        String type=colTypes.get(index).toString();
        DataFrame2D  newDf=new DataFrame2D (new String[]{name},new String[]{type});

        List<List<Object>> data=new ArrayList<>();
        for(var x:dataFrame) {
           //wyłuskanie wartosci z danego wiersza
            ArrayList<Object> arry=new ArrayList<>();
            arry.add(x.get(index));

            data.add(arry);
        }
        newDf.setDataFrame(data);
        return newDf;
    }

    public DataFrame2D  iloc(int from, int to) {
        String [] newColNames=new String[to-from];
        String [] newTypeNames=new String[to-from];
//        for(int i=from; i<=to; i++){
//            int iterator=0;
//            newColNames[iterator]=colNames.get(i);
//            newTypeNames[iterator]=colTypes.get(i);
//            iterator++;
//
//        }
        List<List<Object>> data=new ArrayList<>();
        for(var x:dataFrame){

            List<Object> arry=new ArrayList<>();
            for(int i=from; i<to+1; i++){

                arry.add(x.get(i));
            }

            data.add(arry);
        }

        DataFrame2D  newDf=new DataFrame2D (newColNames,newTypeNames);

        //List<List<String>> data=new ArrayList<>();
//        for(int i=from; i<=to; i++) {
//            data.add(dataFrame.get(i));
//        }

        newDf.setDataFrame(data);
        return newDf;

    }

    @Override
    public String toString() {
        List<Object> content=new LinkedList<>();
        StringBuilder sb=new StringBuilder();
        for(int i=0; i<dataFrame.size(); i++){
            for(var x:dataFrame.get(i)){
                sb.append(x);
                sb.append(" |");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    public void print(){

        for(int i=0; i<dataFrame.size(); i++){
            StringBuilder str= new StringBuilder();
            for(var x:dataFrame.get(i)){
                str.append(x);
                str.append(" |");

            }
            System.out.println(str.toString());
            System.out.println("\n");
        }


    }
    public List<List<Object>> getDataFrame() {
        return dataFrame;
    }

    public void setDataFrame(List<List<Object>> dataFrame) {
        this.dataFrame = dataFrame;
    }

    public List<Object> getColNames() {
        return colNames;
    }

    public void setColNames(List<Object> colNames) {
        this.colNames = colNames;
    }

    public List<Object> getColTypes() {
        return colTypes;
    }

    public void setColTypes(List<Object> colTypes) {
        this.colTypes = colTypes;
    }
}

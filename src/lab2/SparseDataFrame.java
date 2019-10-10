package lab2;


import lab1.DataFrame2D;

import javax.swing.*;
import java.util.*;

public class SparseDataFrame extends DataFrame2D {
    private List<List<Object>> SparseData;
    private String [] Names;
    private String[] Types;

    public SparseDataFrame(String[] colNames, String[] colTypes, String hide) {
        super(colNames, colTypes);
        SparseData=new ArrayList<>();
        Names=colNames;
        Types=colTypes;
        int hideInt= new Integer(hide);
        parseToSparse(getDataFrame(),SparseData,hideInt);



       // Class<?> cls = Class.forName(colTypes[0]);
    }

    public DataFrame2D toDense(){
        DataFrame2D denseDf=new DataFrame2D(Names,Types);
        for(var WSize=0; WSize<SparseData.size(); WSize++){

            //Map<Integer,Integer> maxEntry=Collections.max(SparseData.get(WSize),(HashMap<Integer,Integer> el, Map<Integer,Integer> e2)
//            Map<Integer,Integer> maxEntry = Collections.max(map.entrySet(), (Map<Integer,Integer> e1, Map<Integer,Integer> e2 )-> e1.
//                    .compareTo(e2));
        }



    }












    private void parseToSparse(List<List<Object>> dataFrame,List<List<Object>> SparseData,int hideInt) {
        for(var listIndex=0; listIndex<dataFrame.size();listIndex++){
            List<Object> temp=new ArrayList<>();

            for(var objectIndex=0;objectIndex<dataFrame.get(listIndex).size();objectIndex++){
                if(!dataFrame.get(listIndex).get(objectIndex).equals(hideInt)){

                    Map<Integer,Integer> hm =
                            new HashMap<>();
                    //add existing values top hash map
                    hm.put(objectIndex,(Integer)dataFrame.get(listIndex).get(objectIndex));
                    //add hash map to temporary list
                    temp.add(hm);
                }
            }
            SparseData.add(temp);
        }

    }
}

//package lab2;
//
//
//import lab1.DataFrame2D;
//
//import javax.swing.*;
//import java.lang.reflect.Array;
//import java.util.*;
//
//public class SparseDataFrame extends DataFrame2D {
//    private List<List<Object>> SparseData;
//    private List<String> SparseNames;
//    private List<String> SparseTypes;
//    private int XSize=0;
//    private int YSize=0;
//
//    public SparseDataFrame(String[] colNames, String[] colTypes, String hide) throws ClassNotFoundException {
//        SparseData=new ArrayList<>();
//        SparseNames= Arrays.asList(colNames);
//        SparseTypes=Arrays.asList(colTypes);
//
//        //checking for a type of cols (colTypes should be fully-qualified
//        Class<?> cls = Class.forName(colTypes[0]);
//        }
//    public SparseDataFrame(DataFrame2D dataFrame2D) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
//        //wyłuskanie stringu typu
//        String hide=dataFrame2D.getColNames().get(0).toString();
//        String [] names= (String[]) dataFrame2D.getColNames().toArray();
//        String [] types= (String[]) dataFrame2D.getColTypes().toArray();
//        //jak to nie bd string[] to program leży xDDD
//
//        SparseDataFrame sparseDataFrame=new SparseDataFrame(names,types,hide);
//        parseData(dataFrame2D,hide);
//    }
//
//    private List<List<Object>> parseData(DataFrame2D dataFrame2D,String hide) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//        List<List<Object>> parseData=new ArrayList<>();
//        for(int col=0; col<dataFrame2D.getColNames().size(); col++){
//            List<Object> list= new ArrayList<>();
//            //row -> rozmiar kolumny
//            for(int row=0; row<dataFrame2D.getDataFrame().get(col).size(); row++){
//
//                String type=dataFrame2D.getDataFrame().get(col).get(row).getClass().toString();
//                Class<?> cls=Class.forName(type);
//
//
//                if(type!=hide){
//                    COOValue cooValue=new COOValue();
//                    cooValue.setKey(row);
//                    cooValue.setValue(dataFrame2D.getDataFrame().get(col).get(row));
//
//                    list.add(cooValue);
//                }
//
//            }
//            parseData.add(list);
//        }
//
//        return parseData;
//    }
//
//    @Override
//    public List<Object> get(String name) {
//        boolean contains=SparseNames.contains(name);
//        if(contains){
//            int index=SparseNames.indexOf(name);
//            return SparseData.get(index);
//        }
//        return null;
//
//    }
//
//    @Override
//    public DataFrame2D get(String[] cols, boolean copy) {
//        return super.get(cols, copy);
//    }
//
//    @Override
//    public DataFrame2D iloc(int index) {
//        return super.iloc(index);
//    }
//
//    @Override
//    public DataFrame2D iloc(int from, int to) {
//        return super.iloc(from, to);
//    }
//
//    @Override
//    public String toString() {
//        return super.toString();
//    }
//
//    @Override
//    public List<List<Object>> getDataFrame() {
//        return super.getDataFrame();
//    }
//
//    @Override
//    public void setDataFrame(List<List<Object>> dataFrame) {
//        super.setDataFrame(dataFrame);
//    }
//
//    private void parseToSparse(List<List<Object>> dataFrame, List<List<Object>> SparseData, int hideInt) {
//        for(var listIndex=0; listIndex<dataFrame.size();listIndex++){
//            List<Object> temp=new ArrayList<>();
//
//            for(var objectIndex=0;objectIndex<dataFrame.get(listIndex).size();objectIndex++){
//                if(!dataFrame.get(listIndex).get(objectIndex).equals(hideInt)){
//
//                    Map<Integer,Integer> hm =
//                            new HashMap<>();
//                    //add existing values top hash map
//                    hm.put(objectIndex,(Integer)dataFrame.get(listIndex).get(objectIndex));
//                    //add hash map to temporary list
//                    temp.add(hm);
//                }
//            }
//            SparseData.add(temp);
//        }
//
//    }
//}

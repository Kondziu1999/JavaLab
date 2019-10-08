//package lab1;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class DataFrame {
//    private List<String> dataFrame;
//    private List<String> colNames;
//    private  List<String> colTypes;
//
//
//    public DataFrame(String[] colNames, String[]colTypes) {
//        this.colNames=new ArrayList<>();
//        this.colTypes=new ArrayList<>();
//
//        this.colNames.addAll(Arrays.asList(colNames));
//        this.colTypes.addAll(Arrays.asList(colTypes));
//
//        this.dataFrame=new ArrayList<>();
//    }
//
//    public int size(){
//        return  dataFrame.size();
//    }
//
//    public List<String> get(String colName){
//        boolean contains=colNames.contains(colName);
//
//        List<String> data=new ArrayList<>();
//        if(contains){
//            int index=colNames.lastIndexOf(colName);
//
//            for(int i=0; i<dataFrame.size(); i++){
//
//                if(i==index){
//                    data.add(dataFrame.get(i));
//                    index+=index;
//                }
//            }
//            return data;
//        }
//        else {
//            return null;
//        }
//
//    }
//    public void get(String [] cols, boolean copy){
//        if(copy){
//            deepCopy(cols);
//        }
//        else{
//            shallowCopy(cols);
//
//        }
//    }
//
//    public DataFrame deepCopy(String[] cols){
//        ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
//
//        for(int i=0; i<cols.length;i++) {
//            ArrayList<String> temp=new ArrayList<>();
//
//            int index = colNames.lastIndexOf(cols[i]);
//
//            for(int var=0; var<dataFrame.size(); var++){
//                if(var==index){
//                    temp.add(dataFrame.get(index));
//                    index+=index;
//                }
//            }
//            data.add(temp);
//        }
//        //transform into one dimensional array
//        ArrayList<String> finalArr=new ArrayList<>();
//        for(int i=0; i<data.get(0).size(); i++){
//            for(int var=0; var<data.size(); var++){
//                finalArr.add(data.get(var).get(i));
//            }
//        }
//        DataFrame finalDf=new DataFrame(,cols);
//        return finalDf;
//    }
//
//
//
//    public DataFrame shallowCopy(String[] cols){}
//
//
//
//    public List<String> getDataFrame() {
//        return dataFrame;
//    }
//
//    public void setDataFrame(List<List<String>> dataFrame) {
//        this.dataFrame = dataFrame;
//    }
//}

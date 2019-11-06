package lab4;

import java.util.HashMap;

public class test {

    public static void main(String[]args) {
        Value strVal=new StringValue().create("xd");
        Value dblVal= new DoubleValue().create("1.0");
        Value[] xd=new Value[]{strVal,strVal,dblVal,dblVal};


        DataFrameCol df = new DataFrameCol("C:\\Users\\priva\\Desktop\\groupby2.csv",xd
                ,null);


        String []mColumnNames=new String[]{"id","data","total","val"};
        Value[] mColumnTypes=null;

        //df.toString();
        HashMap<String, Object> map= new HashMap<>();
        map.put("a",new DataFrameCol(mColumnNames,mColumnTypes));
        var x=map.get("a");

        df.groupBy("id").sum().print();
    }


}

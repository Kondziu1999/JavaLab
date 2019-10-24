package lab4;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class GroupbyImpl implements Groupby {

    private DataFrameCol df;
    private String colToGroup;
    private String[] mColumnNames;
    private List<List<Value>> mColumns;

    public GroupbyImpl(DataFrameCol df, String colToGroup,String[] mColumnNames, List<List<Value>> mColumns){
        this .df=df;
        this.colToGroup=colToGroup;
        this.mColumnNames=mColumnNames;
        this.mColumns=mColumns;
    }

    private void group(String colToGroup){
        List<DataFrameCol> groups=new LinkedList<>();
        HashMap<String, DataFrameCol> idMap = new HashMap<>();

        Integer groupByIndex=null;
        for(int i=0; i<mColumnNames.length; i++){
            if(mColumnNames[i].equals(colToGroup)){
                groupByIndex=i;
                break;
            }
        }
        if(groupByIndex==null){
            throw new RuntimeException("Column not found" + colToGroup);
        }
        //adding groups
        for(Value val:mColumns.get(groupByIndex.intValue())){
            //DataFrameCol tempDf=new DataFrameCol(new String[]{val.toString()},new Value[]{val});
            idMap.putIfAbsent(val.toString(),new DataFrameCol(new String[]{val.toString()},new Value[]{val}));
        }

        for(Value val:mColumns.get(groupByIndex.intValue())){
            var map=
                    idMap.get(val.toString());

            map.addRow();

        }


    }



    @Override
    public DataFrameCol max() {
        return null;
    }

    @Override
    public DataFrameCol min() {
        return null;
    }

    @Override
    public DataFrameCol mean() {
        return null;
    }

    @Override
    public DataFrameCol std() {
        return null;
    }

    @Override
    public DataFrameCol sum() {
        return null;
    }

    @Override
    public DataFrameCol var() {
        return null;
    }

    @Override
    public DataFrameCol apply() {
        return null;
    }
}

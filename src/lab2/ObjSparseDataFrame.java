package lab2;

import lab1.DataFrame2D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ObjSparseDataFrame extends DataFrame2D {


    private List<String> sparseNames;
    private List<String> sparseTypes;
    private List<List<Object>> sparseData;
    private String hide;
    private int countCols;
    private int countRows;

    public ObjSparseDataFrame(String[] colNames, String[] colTypes, String hide) {
        sparseData = new ArrayList<>();
        sparseNames = Arrays.asList(colNames);
        sparseTypes = Arrays.asList(colTypes);
        this.hide = hide;
        this.countCols =colNames.length;
    }

    public  void addRow(){


    }
    @Override
    public List<Object> get(String name) {
        return super.get(name);
    }

    @Override
    public ObjSparseDataFrame get(String[] cols, boolean copy) {
        int[] exising = new int[colNames.size()];
        String[] newNames = new String[cols.length];

        //zerowanie indeksów
        for (int i = 0; i < exising.length; i++) {
            exising[i] = 0;
        }

        for (int i = 0; i < cols.length; i++) {
            if (colNames.contains(cols[i])) {
                //index typu
                int index = colNames.indexOf(cols[i]);
                newNames[i] = colTypes.get(index).toString();
                exising[index] = 1;
            }
        }
        //tworzenie listy list z danymi;
        List<List<Object>> data = new ArrayList<>(cols.length);

        if (copy) {
            for (int i = 0; i < exising.length; i++) {
                List<Object> temp = new ArrayList<>(cols.length);
                if (exising[i] == 1) {

                    //nowe stringi nie referencje

                    for (int x = 0; x < sparseData.get(i).size(); x++) {
                        COOValue<Object> dataInCell = new COOValue<>();

                        int key = ((COOValue<Object>) sparseData.get(i).get(x)).getKey();
                        Object val = ((COOValue<Object>) sparseData.get(i).get(x)).getValue();

                        dataInCell.setKey(key);
                        dataInCell.setValue(val);

                        temp.add(dataInCell);
                    }
                }
                data.add(temp);
            }
        }
        ObjSparseDataFrame newDf = new ObjSparseDataFrame(cols, newNames, this.hide);
        newDf.setSparseData(data, newDf.getCountCols());

        return newDf;
    }

    @Override
    public ObjSparseDataFrame iloc(int index) {
        String name = sparseNames.get(index);
        String type = sparseTypes.get(index);
        ObjSparseDataFrame newDf = new ObjSparseDataFrame(new String[]{name}, new String[]{type}, hide);

        List<List<Object>> data = new ArrayList<>();
        for (var x : sparseData) {
            //wyłuskanie wartosci z danego wiersza
            ArrayList<Object> arry = new ArrayList<>();
            arry.add(x.get(index));

            data.add(arry);
        }
        newDf.setDataFrame(data);
        return newDf;
    }

    @Override
    public DataFrame2D iloc(int from, int to) {
        String[] newColNames = new String[to - from];
        String[] newTypeNames = new String[to - from];

        List<List<Object>> data = new ArrayList<>();
        for (var x : sparseData) {

            List<Object> arry = new ArrayList<>();
            for (int i = from; i < to + 1; i++) {

                arry.add(x.get(i));
            }

            data.add(arry);
        }

        ObjSparseDataFrame newDf = new ObjSparseDataFrame(newColNames, newTypeNames, hide);

        newDf.setDataFrame(data);
        return newDf;
    }

    public List<List<Object>> getSparseData() {
        return sparseData;
    }

    public void setSparseData(List<List<Object>> sparseData, int rows) {
        this.sparseData = sparseData;
        this.countRows =rows;
    }

    @Override
    public String toString() {
        List<Object> content = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sparseData.size(); i++) {
            for (var x : sparseData.get(i)) {
                sb.append(x);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public int getCountCols() {
        return countCols;
    }

    public int getCountRows() {
        return countRows;
    }

    public static DataFrame2D toDense(ObjSparseDataFrame sparseDataFrame){

        String[] newNames= sparseDataFrame.getSparseNames().toArray(new String[0]);
        String[] newCols=sparseDataFrame.getSparseTypes().toArray(new String[0]);
        DataFrame2D dataFrame2D=new DataFrame2D(newNames,newCols);

        List<List<Object>> denseData = new ArrayList<>();
        //przepisuje referencje dla czytelności
        List<List<Object>> sparseData=sparseDataFrame.getSparseData();

        for(int i=0; i<sparseData.size(); i++){
            List<Object> cells=new ArrayList<>();
            //niepuste miejsca w denseData
            int [][] indexes=xd(sparseDataFrame, sparseDataFrame.getCountCols(), sparseDataFrame.getCountRows());

            for(int x=0; x<sparseData.get(i).size() ;x++){
                //jesli w docelowej komorce w denseFrame znajduje sie warotsc!=0
                if(indexes[i][x]==1){
                    COOValue obj= (COOValue) sparseDataFrame.getSparseData().get(i).get(x);
                    cells.add(obj.getValue());
                }
                else {
                    //jesli tam nic nie ma to dodaje ukrywana wartosc
                    cells.add(sparseDataFrame.hide);
                }
            }
            denseData.add(cells);
        }
        //dodanie pozostałych wierszy dataFrame (moga byc puste wszakze)
        if(sparseData.size()<sparseDataFrame.getCountCols()){
            for(int i=sparseData.size(); i<sparseDataFrame.getCountCols();i++){
                List<Object> restOfCells=new ArrayList<>();
                for(int x=0; x<sparseData.get(0).size(); x++){
                    restOfCells.add(sparseDataFrame.hide);
                }
                denseData.add(restOfCells);
            }
        }
        dataFrame2D.setDataFrame(denseData);
        return dataFrame2D;
    }

    private static int[] nonEmptyIndexes(List<List<Object>>objects, int count,int colNumber) {
        //ustawiam 1 na indeksach w których znjaduje sie warotsc!=0
        int[] indexes=new int[count];

        for(int i=0; i<count; i++){
            indexes[i]=0;
        }
        for(var list:objects) {

                //kastuje do typu komórki
                COOValue obj = (COOValue) list.get(colNumber);
                indexes[obj.getKey()] = 1;

        }
        var list=objects.get(colNumber);
        return  indexes;
    }

    public static int [][] xd(ObjSparseDataFrame dataFrame, int vertical,int horizontal){

        List<List<Integer>> list=new ArrayList<>();
        int [][] indexes=new int[horizontal][vertical];

        for(int i=0; i<horizontal; i++){
            for(int x=0; x<vertical; x++){
                indexes[i][x]=0;
            }
        }
        for(var i=0; i<dataFrame.getSparseData().size(); i++){
            for(var o=0; o<dataFrame.getSparseData().get(0).size(); o++){
                indexes[i][o]=1;
            }
        }
        return indexes;
    }

    public List<String> getSparseNames() {
        return sparseNames;
    }

    public void setSparseNames(List<String> sparseNames) {
        this.sparseNames = sparseNames;
    }

    public List<String> getSparseTypes() {
        return sparseTypes;
    }

    public void setSparseTypes(List<String> sparseTypes) {
        this.sparseTypes = sparseTypes;
    }
}

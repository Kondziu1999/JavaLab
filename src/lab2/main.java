package lab2;

import lab1.DataFrame2D;

import java.util.ArrayList;
import java.util.List;

public class main {

    public static void main(String [] args){

        // uzywam wrapperów zamiast prostych typów
        ObjSparseDataFrame df= new ObjSparseDataFrame(new String[]{"kol1", "kol2", "kol3"},
                new String[]{"int", "int","int"},"0");

        List<List<Object>> sparseData=new ArrayList<>();

        // 1 wiersz
        List<Object> temp1= new ArrayList<>();
        COOValue<Double> tempCell=new COOValue<>();
        tempCell.setKey(0);
        tempCell.setValue(4.0);

        COOValue<Double> tempCell2=new COOValue<>();
        tempCell2.setKey(0);
        tempCell2.setValue(2.0);

        COOValue<Double> tempCell3=new COOValue<>();
        tempCell3.setKey(0);
        tempCell3.setValue(4.4);

        temp1.add(tempCell);
        temp1.add(tempCell2);
        temp1.add(tempCell3);

        sparseData.add(temp1);
        //2 wiersz
        tempCell=new COOValue<>();
        tempCell2=new COOValue<>();
        tempCell3=new COOValue<>();

        tempCell.setKey(1);
        tempCell.setValue(3.0);

        tempCell2.setKey(1);
        tempCell2.setValue(3.0);

        tempCell3.setKey(1);
        tempCell3.setValue(2.0);

        temp1= new ArrayList<>();

        temp1.add(tempCell);
        temp1.add(tempCell2);
        temp1.add(tempCell3);

        sparseData.add(temp1);

        df.setSparseData(sparseData,3);

        System.out.println(df);

        DataFrame2D df2=ObjSparseDataFrame.toDense(df);

        System.out.println(df2);



    }


}

package lab1;


import java.util.ArrayList;
import java.util.List;

public class app {

    public static void main(String[] args) {
        DataFrame2D df = new DataFrame2D(new String[]{"kol1", "kol2", "kol3"},
                new String[]{"int", "double", "MyCustomType"});
        //df.setDataFrame(Arrays.asList(new String[]{"int", "double", "MyCustomType"}));
        List<List<String>> list = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        temp.add("xd");
        temp.add("xd2");
        temp.add("xd3");
        list.add(temp);
        list.add(temp);

        List<String> temp2 = new ArrayList<>();
        temp2.add("zz");
        temp2.add("xx");
        temp2.add("vv");
        list.add(temp2);

        df.setDataFrame(list);
        System.out.println(df.get("kol1"));
        System.out.println(df.iloc(1, 2).toString());

    }
}

package lab1;


import java.util.ArrayList;
import java.util.List;

public class app {

    public static void main(String[] args) {
        DataFrame2D df = new DataFrame2D(new String[]{"kol1", "kol2", "kol3"},
                new String[]{"int", "double", "MyCustomType"});
        //df.setDataFrame(Arrays.asList(new String[]{"int", "double", "MyCustomType"}));
        List<List<Object>> list = new ArrayList<>();
        List<Object> temp = new ArrayList<>();
        temp.add("xd");
        temp.add("xd2");
        temp.add("xd3");
        list.add(temp);
        list.add(temp);

        List<Object> temp2 = new ArrayList<>();
        temp2.add("zz");
        temp2.add("xx");
        temp2.add("vv");
        list.add(temp2);

        List<Object> temp3=new ArrayList<>();
        Integer var=112;

        temp3.add(new Xd("test",997, var,true));
        temp3.add(new Xd("test2",998, var,false));
        temp3.add(new Xd("test3",999, var,true));
        list.add(temp3);

        df.setDataFrame(list);
        System.out.println(df.get("kol1"));
        System.out.println(df.iloc(1, 2).toString());

    }
}
class Xd{

    String x;
    int a;
    Integer c;
    boolean v;

    public Xd(String x, int a, Integer c, boolean v) {
        this.x = x;
        this.a = a;
        this.c = c;
        this.v = v;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public Integer getC() {
        return c;
    }

    public void setC(Integer c) {
        this.c = c;
    }

    public boolean isV() {
        return v;
    }

    public void setV(boolean v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return "xd{" +
                "x='" + x + '\'' +
                ", a=" + a +
                ", c=" + c +
                ", v=" + v +
                '}';
    }
}


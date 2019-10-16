package lab1;

import java.io.*;

public class test {

    public static void main(String [] args) {

        try {
            BufferedReader br;
            String strLine;
            FileInputStream inputStream = new FileInputStream("C:\\Users\\priva\\Desktop\\data.csv");
            br = new BufferedReader(new InputStreamReader(inputStream));


            while ((strLine = br.readLine()) != null) {
                //String[] data = strLine.split(",");
                System.out.println(strLine);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getStackTrace());
        } catch (IOException e) {
            e.printStackTrace();
        }


        DataFrame2D df3 = new DataFrame2D("C:\\Users\\priva\\Desktop\\data.csv", new String[]{"int", "int", "int"}, null);
       // System.out.println(df3);
        df3.print();
    }
}

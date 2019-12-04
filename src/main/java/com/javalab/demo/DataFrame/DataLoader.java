//package com.javalab.demo.DataFrame;
//
//import com.javalab.demo.DAO.DFCrudRepo;
//
//import com.javalab.demo.Entity.DataRow;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//
//import java.io.*;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//
//import java.util.logging.Logger;
//
//@Component
//public class DataLoader {
//    private Logger logger=Logger.getLogger("logger",DataLoader.class.getName());
//
//
//    private  DFCrudRepo repo;
//
//
//    @Autowired
//    public DataLoader(DFCrudRepo repo){
//        this.repo=repo;
//    };
//    public DataLoader(){}
//
//    public void load(String path){
//        BufferedReader bf;
//        String line;
//
//        try {
//            FileInputStream inputStream=new FileInputStream(path);
//            bf=new BufferedReader(new InputStreamReader(inputStream));
//            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
//
//            line=bf.readLine(); //czytam tytul kolum w powietrze
//            while ((line=bf.readLine())!= null){
//                String[]data=line.split(",");
//
//                repo.save(new DataRow(data[0],formatter.parse(data[1]),Double.parseDouble(data[2]),Double.parseDouble(data[3])));
//
//            }
//
//        } catch (FileNotFoundException e) {
//            logger.warning(e.getMessage());
//        } catch (IOException e) {
//            logger.warning(e.getMessage());
//        } catch (ParseException e) {
//            logger.warning(e.getMessage());
//        }
//
//    }
//
//
//}

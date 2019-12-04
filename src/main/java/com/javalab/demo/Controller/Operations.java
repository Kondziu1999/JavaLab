package com.javalab.demo.Controller;


import com.javalab.demo.DAO.DFCrudRepo;

import com.javalab.demo.Entity.DataRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.*;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/dataframe")
public class Operations {


    @Autowired
    DFCrudRepo repo;

    @PostConstruct
    public void load(){
        BufferedReader bf;
        String line;

        try {
            FileInputStream inputStream=new FileInputStream("C:\\Users\\priva\\Desktop\\data2.csv");
            bf=new BufferedReader(new InputStreamReader(inputStream));
            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");

            line=bf.readLine(); //czytam tytul kolum w powietrze
            while ((line=bf.readLine())!= null){
                String[]data=line.split(",");

                repo.save(new DataRow(data[0],formatter.parse(data[1]),Double.parseDouble(data[2]),Double.parseDouble(data[3])));

            }


        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    @GetMapping("/rows")
    public List<DataRow> getRows(){
        return repo.findAll();
    }

    @GetMapping("/rows/min/{col}")
    public DataRow getMaxRows(@PathVariable String col ){
        if(col.equals("total")) {
            return repo.findTopByOrderByTotalAsc();
        }
        if(col.equals("val")){
            return repo.findTopByOrderByValAsc();
        }
        else
            return null;
    }
    @GetMapping("/rows/max/{col}")
    public DataRow getMinRow(@PathVariable String col ){
        if(col.equals("total")) {
            return repo.findTopByOrderByTotalDesc();
        }
        if(col.equals("val")){
            return repo.findTopByOrderByValDesc();
        }
        else
            return null;
    }

    @GetMapping("/rows/groupby/{col}/{type}")
    public Collection getDataRowsGroupBy(@PathVariable String col,@PathVariable String type){
        if(type.equals("max")) {
            if (col.equals("id")) return repo.getGroupByIdMax();
            else if (col.equals("date")) return repo.getGroupByDateMax();
            else return null;
        }
        else {
            if (col.equals("id")) return repo.getGroupByIdMin();
            else if (col.equals("date")) return repo.getGroupByDateMin();
            else return null;

        }
    }
    

}

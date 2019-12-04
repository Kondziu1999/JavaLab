package com.javalab.demo.Entity;

import java.util.LinkedList;
import java.util.List;

public class DataFrame {
    private List<DataRow> rows;
    public DataFrame(){
        this.rows=new LinkedList<>();
    }

    public List<DataRow> getRows() {
        return rows;
    }

    public void setRows(List<DataRow> rows) {
        this.rows = rows;
    }

    public void addRow(DataRow dataRow){
        rows.add(dataRow);
    }
}

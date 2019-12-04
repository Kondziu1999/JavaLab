package com.javalab.demo.DAO;

import com.javalab.demo.Entity.DataRow;

import java.util.List;

public interface Dfdao {

    public DataRow getRow(int theId);
    public List<DataRow> getRows();
    public void addRow(DataRow dataRow);
    public void deleteRow(int theId);
}

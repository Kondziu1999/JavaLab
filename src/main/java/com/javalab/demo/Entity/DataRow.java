package com.javalab.demo.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "data_frame")
public class DataRow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "id2")
    private String id2;

    @Column(name = "date")
    private Date date;

    @Column(name = "total")
    private double total;

    @Column(name = "val")
    private double val;

    public DataRow() {
    }

    public DataRow(String id2, Date date, double total, double val) {
        this.id2 = id2;
        this.date = date;
        this.total = total;
        this.val = val;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getVal() {
        return val;
    }

    public void setVal(double val) {
        this.val = val;
    }
}

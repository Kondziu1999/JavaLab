package com.javalab.demo.DAO;

import com.javalab.demo.Entity.DataRow;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface DFCrudRepo extends JpaRepository<DataRow,Long> {



    DataRow findTopByOrderByTotalAsc();
    DataRow findTopByOrderByValAsc();

    DataRow findTopByOrderByTotalDesc();
    DataRow findTopByOrderByValDesc();


//    List<DataRow> findOrderByTotal();
//    List<DataRow> findOrderByVal();
//    List<DataRow> findOrderByDate();
//    List<DataRow> findOrderById2();

    @Query(value = "Select id2, max(date),max(val),max(total) from data_frame  group by id2",nativeQuery = true)
    Collection getGroupByIdMax();

    @Query(value = "Select id2,date, max(val),max(total) from data_frame  group by id2,date ",nativeQuery = true)
    Collection getGroupByDateMax();

    @Query(value = "Select id2, min(date),min(val),min(total) from data_frame  group by id2",nativeQuery = true)
    Collection getGroupByIdMin();

    @Query(value = "Select id2,date, min(val),min(total) from data_frame  group by id2,date ",nativeQuery = true)
    Collection getGroupByDateMin();
    //List<DataRow> findByTotalOrderByTotal();
}

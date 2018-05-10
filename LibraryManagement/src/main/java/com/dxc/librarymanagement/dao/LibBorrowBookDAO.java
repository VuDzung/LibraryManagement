package com.dxc.librarymanagement.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dxc.librarymanagement.entities.LibBorrowBook;
import com.dxc.librarymanagement.entities.LibUser;
@Repository
public interface LibBorrowBookDAO extends JpaRepository<LibBorrowBook, Integer> {
	List<LibBorrowBook> findByUser(LibUser user);
	
	@Query("select MONTH(DateBorrow) as _month,COUNT(IdBorrow) as borrowed from LibBorrowBook where YEAR(DateBorrow)=:year group by MONTH(DateBorrow)")
	public List<Map<Integer,Integer>> getMonthly(@Param("year") Integer year);
	
	@Query("select year(DateBorrow) as _year,COUNT(IdBorrow) as borrowed from LibBorrowBook group by year(DateBorrow)")
	public List<Map<Integer,Integer>> getYearly();
	
	@Query(value="select datepart(dw, DateBorrow) as _day,COUNT(IdBorrow) as borrowed \r\n" + 
			"from LibBorrowBook where  DATEPART(WW,DateBorrow)=:week and YEAR(DateBorrow)=:year \r\n" + 
			"group by datepart(dw, DateBorrow)", nativeQuery = true)
	public List<Map<Integer,Integer>> getWeekly(@Param("week") Integer week, @Param("year") Integer year);

}

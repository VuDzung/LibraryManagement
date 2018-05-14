package com.dxc.librarymanagement.dao;

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
	@Query("from LibBorrowBook b where b.dateReturn is null and b.user=:user")
	List<LibBorrowBook> findByUser(@Param("user") LibUser user);
	
	@Query("select MONTH(DateBorrow) as _month,COUNT(IdBorrow) as borrowed from LibBorrowBook where YEAR(DateBorrow)=:year group by MONTH(DateBorrow)")
	public List<Map<Integer,Integer>> getMonthly(@Param("year") Integer year);
	
	@Query("select year(DateBorrow) as _year,COUNT(IdBorrow) as borrowed from LibBorrowBook group by year(DateBorrow)")
	public List<Map<Integer,Integer>> getYearly();
	
	@Query(value="select datepart(dw, DateBorrow) as _day,COUNT(IdBorrow) as borrowed \r\n" + 
			"from LibBorrowBook where  DATEPART(WW,DateBorrow)=:week and YEAR(DateBorrow)=:year \r\n" + 
			"group by datepart(dw, DateBorrow)", nativeQuery = true)
	public List<Map<Integer,Integer>> getWeekly(@Param("week") Integer week, @Param("year") Integer year);
	
	@Query(value="select top 5 COUNT(bb.ISBN) as total , b.TitleOfBook as Title from LibBorrowBook bb, LibISBN i, LibBook b  \r\n" + 
			"where \r\n" + 
			"	bb.ISBN = i.ISBN and i.IdBook = b.IdBook and\r\n" + 
			"	YEAR(DateBorrow)=:year\r\n" + 
			"group by b.TitleOfBook order by total desc", nativeQuery=true)
	public List<Map<Integer,String>> getTopBookOfDaYear(@Param("year") Integer year);
	
	@Query(value="select top 5 COUNT(bb.ISBN) as total , b.TitleOfBook as Title from LibBorrowBook bb, LibISBN i, LibBook b  \r\n" + 
			"	where \r\n" + 
			"		bb.ISBN = i.ISBN and i.IdBook = b.IdBook and\r\n" + 
			"		month(DateBorrow)=:month and YEAR(DateBorrow)=:year\r\n" + 
			"	group by b.TitleOfBook\r\n" + 
			"	order by total desc", nativeQuery=true)
	public List<Map<Integer,String>> getTopBookOfDaMonth(@Param("year") Integer year,@Param("month") Integer month);
	
	
	@Query(value="select top 5 COUNT(bb.ISBN) as total , b.TitleOfBook as Title from LibBorrowBook bb, LibISBN i, LibBook b \r\n" + 
			"where  \r\n" + 
			"bb.ISBN = i.ISBN and i.IdBook = b.IdBook and\r\n" + 
			"				datepart(week,DateBorrow)=:week and YEAR(DateBorrow)=:year\r\n" + 
			"group by b.TitleOfBook\r\n" + 
			"order by total desc\r\n" + 
			"", nativeQuery=true)
	public List<Map<Integer,Integer>> getTopBookOfDaWeek(@Param("week") Integer week, @Param("year") Integer year);
	
	public LibBorrowBook findByIdBorrow(int idborrow);
	
	
	
}

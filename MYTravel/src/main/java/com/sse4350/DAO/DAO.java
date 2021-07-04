package com.sse4350.DAO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import com.sse4350.model.Admin;
import com.sse4350.model.place;
import com.sse4350.model.review;
import com.sse4350.model.user;




public class DAO {
	Connection connection=null;
	Statement statement=null;
	ResultSet resultSet=null;
	PreparedStatement preparedStatement =null;
	
	
public boolean login(String username,String pass) {
		Boolean found = false;
		try {
			String sql = "select * from MYTravel_admin where username='"+username+"'";
			connection = DBConnectionUtil.openConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				System.out.println("receive "+username+" "+pass);
				String DBuser = resultSet.getString("username");
				String DBpass = resultSet.getString("pass");
				System.out.println("receive DB "+DBuser+" "+DBpass);
				if(username.equals(DBuser) && pass.equals(DBpass)) {
					found = true;
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return found;
	}

public List<user> getUser() {
	List<user> list = null;
	user user = new user();
	System.out.print("value retived 1");

	try {
		list = new ArrayList<user>();
		String sql = "SELECT * FROM MYTravel_user order by userid asc";
		connection = DBConnectionUtil.openConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		
	
		while(resultSet.next()) {
		
		user = new user();
			
		user.setUserid(resultSet.getString("userid"));
		user.setUsername(resultSet.getString("username"));
		user.setUsermail(resultSet.getString("useremail"));
		user.setUserage(resultSet.getString("userage"));
		user.setUserpnum(resultSet.getString("userpnum"));		
		list.add(user);
		System.out.println(list);
	
		}
	} catch (Exception e) {
	}

	return list;
}

public boolean save(place pl) {
	boolean flag = false;

	try {
		String sql = "INSERT INTO MYTravel_places(placeid,placename,placeaddress,placenum,placemail,placedesc)VALUES"
				+ "('" + pl.getPlaceid() + "','" + pl.getPlacename() + "', '" + pl.getPlaceaddress() + "', '"
				+ pl.getPlacenum() + "', '" + pl.getPlacemail()+ "', '" + pl.getPlacedesc()+ "')";

		System.out.println(sql);
		connection = DBConnectionUtil.openConnection();
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.executeUpdate();
		flag = true;
	} catch (SQLException ex) {
		ex.printStackTrace();
	}
	return flag;
}

public boolean checkPlace(String placeid) {
	Boolean found = false;
	try {
		String sql = "SELECT * FROM MYTravel_places where placeid='" + placeid + "'";
		connection = DBConnectionUtil.openConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		if (resultSet.next()) {
			found = true;
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return found;
}
	
public place get(String placeid) {
	place pl = null;
	try {
		pl = new place();
		String sql = "SELECT * FROM MYTravel_places where placeid='" + placeid + "'";
		connection = DBConnectionUtil.openConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		if (resultSet.next()) {
			pl.setPlaceid(resultSet.getString("placeid"));
			pl.setPlacename(resultSet.getString("placename"));
			pl.setPlaceaddress(resultSet.getString("placeaddress"));
			pl.setPlacenum(resultSet.getString("placenum"));
			pl.setPlacemail(resultSet.getString("placemail"));
			pl.setPlacedesc(resultSet.getString("placedesc"));
			System.out.print("DAO get"+pl);
			
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return pl;
}

public boolean update(place place) {
	boolean flag = false;
	try {
		String sql = "UPDATE MYTravel_places SET placeid ='" + place.getPlaceid() + "', placename = '" + place.getPlacename()
				+ "', " + "placeaddress = '" + place.getPlaceaddress() + "', placenum = '" + place.getPlacenum() + "', placemail = '"
				+ place.getPlacemail() + "', placedesc = '" + place.getPlacedesc() + "' where placeid='" + place.getPlaceid() + "'";

		connection = DBConnectionUtil.openConnection();
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.executeUpdate();
		flag = true;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return flag;
}

public List<place> getPlace() {
	List<place> list = null;
	place place = new place();

	try {
		list = new ArrayList<place>();
		String sql = "SELECT * FROM MYTravel_places order by placeid asc";
		connection = DBConnectionUtil.openConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			place = new place();
			place.setPlaceid(resultSet.getString("placeid"));
			place.setPlacename(resultSet.getString("placename"));
			place.setPlaceaddress(resultSet.getString("placeaddress"));
			place.setPlacenum(resultSet.getString("placenum"));
			place.setPlacemail(resultSet.getString("placemail"));
			place.setPlacedesc(resultSet.getString("placedesc"));
			list.add(place);
			
		}
	} catch (Exception e) {
	}

	return list;
}

public boolean delete(String place) {
	boolean flag = false;
	try {
		String sql = "DELETE FROM MYTravel_places where placeid='" + place + "'";
		System.out.println(sql);
		connection = DBConnectionUtil.openConnection();
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.executeUpdate();
		flag = true;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return flag;
}

public List<review> getReview() {
	List<review> list = null;
	review review = new review();

	try {
		list = new ArrayList<review>();
		String sql = "SELECT * FROM MYTravel_review order by reviewid asc";
		connection = DBConnectionUtil.openConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			review = new review();
			review.setReviewid(resultSet.getString("reviewid"));
			review.setPlacename(resultSet.getString("placename"));
			review.setRating(resultSet.getString("rating"));
			review.setReview(resultSet.getString("review"));
			
			list.add(review);
			
		}
	} catch (Exception e) {
	}

	return list;
}

public boolean addAdmin(Admin admin) {
	boolean flag = false;

	try {
		String sql = "INSERT INTO MYTravel_admin VALUES"
				+ "('" + admin.getAdminUsername() + "','" + admin.getAdminPassword() + "')";

		System.out.println(sql);
		connection = DBConnectionUtil.openConnection();
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.executeUpdate();
		flag = true;
	} catch (SQLException ex) {
		ex.printStackTrace();
	}
	return flag;
}

public boolean updateadmin(Admin admin) {
	boolean flag = false;
	try {
		String sql = "UPDATE MYTravel_admin SET pass ='" + admin.getAdminPassword() + "' where username='" + admin.getAdminUsername() + "'";

		connection = DBConnectionUtil.openConnection();
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.executeUpdate();
		flag = true;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return flag;
}

public Admin getAdmin(String adminusername) {
	Admin admin = null;
	try {
		admin = new Admin();
		String sql = "SELECT * FROM MYTravel_admin where username='" + adminusername + "'";
		connection = DBConnectionUtil.openConnection();
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		if (resultSet.next()) {
			admin.setAdminUsername(resultSet.getString("username"));
			admin.setAdminPassword(resultSet.getString("pass"));
			
	
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return admin;
}

//Export the table for the User
public void exportU() {
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd");  
	LocalDateTime now = LocalDateTime.now(); 
	String name = dtf.format(now);
	
    String excelFilePath = "D:\\DB_Backup\\user\\"+name+".csv";
    Connection connection = DBConnectionUtil.openConnection();
    try {
        String sql = "SELECT * FROM MYTravel_user";

        Statement statement = connection.createStatement();

        ResultSet result = statement.executeQuery(sql);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("User");

        writeHeaderLineU(sheet);

        writeDataLinesU(result, workbook, sheet);

        FileOutputStream outputStream = new FileOutputStream(excelFilePath);
        workbook.write(outputStream);
      // workbook.close();

      //  statement.close();

    } catch (SQLException e) {
        System.out.println("Datababse error:");
        e.printStackTrace();
    } catch (IOException e) {
        System.out.println("File IO error:");
        e.printStackTrace();
    }
}

private void writeHeaderLineU(XSSFSheet sheet) {

    Row headerRow = sheet.createRow(0);

    Cell headerCell = headerRow.createCell(0);
    headerCell.setCellValue("userid");

    headerCell = headerRow.createCell(1);
    headerCell.setCellValue("username");

    headerCell = headerRow.createCell(2);
    headerCell.setCellValue("useremail");

    headerCell = headerRow.createCell(3);
    headerCell.setCellValue("userage");
    
    headerCell = headerRow.createCell(4);
    headerCell.setCellValue("userpnum");

}

private void writeDataLinesU(ResultSet result, XSSFWorkbook workbook,
        XSSFSheet sheet) throws SQLException {
  
	int rowCount = 1;
 
    while (result.next()) {
 
        String userid = result.getString("userid");
        String username = result.getString("username");
        String useremail = result.getString("useremail");
        String userage = result.getString("userage");
        String userpnum = result.getString("userpnum");

        Row row = sheet.createRow(rowCount++);

        int columnCount = 0;
        Cell cell = row.createCell(columnCount++);
        cell.setCellValue(userid);

        cell = row.createCell(columnCount++);
        cell.setCellValue(username);

        cell = row.createCell(columnCount++);
        cell.setCellValue(useremail);

        cell = row.createCell(columnCount++);
        cell.setCellValue(userage);
        
        cell = row.createCell(columnCount);
        cell.setCellValue(userpnum);

    }
}

//Export the table for the Table Place
public void exportP() {
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd");  
	LocalDateTime now = LocalDateTime.now(); 
	String name = dtf.format(now);
	
    String excelFilePath = "D:\\DB_Backup\\place\\"+name+".csv";
    Connection connection = DBConnectionUtil.openConnection();
    try  {
        String sql = "SELECT * FROM MYTravel_places";

        Statement statement = connection.createStatement();

        ResultSet result = statement.executeQuery(sql);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Place");

        writeHeaderLineP(sheet);

        writeDataLinesP(result, workbook, sheet);

        FileOutputStream outputStream = new FileOutputStream(excelFilePath);
        workbook.write(outputStream);
       // workbook.close();

       // statement.close();

    } catch (SQLException e) {
        System.out.println("Datababse error:");
        e.printStackTrace();
    } catch (IOException e) {
        System.out.println("File IO error:");
        e.printStackTrace();
    }
}

private void writeHeaderLineP(XSSFSheet sheet) {

    Row headerRow = sheet.createRow(0);

    Cell headerCell = headerRow.createCell(0);
    headerCell.setCellValue("placeid");

    headerCell = headerRow.createCell(1);
    headerCell.setCellValue("placename");

    headerCell = headerRow.createCell(2);
    headerCell.setCellValue("placeaddress");

    headerCell = headerRow.createCell(3);
    headerCell.setCellValue("placenum");
    
    headerCell = headerRow.createCell(4);
    headerCell.setCellValue("placemail");
    
    headerCell = headerRow.createCell(5);
    headerCell.setCellValue("placedesc");

}

private void writeDataLinesP(ResultSet result, XSSFWorkbook workbook,
        XSSFSheet sheet) throws SQLException {
   
	int rowCount = 1;
    while (result.next()) {
    	
        String placeid = result.getString("placeid");
        String placename = result.getString("placename");
        String placeaddress = result.getString("placeaddress");
        String placenum = result.getString("placenum");
        String placemail = result.getString("placemail");
        String placedesc = result.getString("placedesc");

        Row row = sheet.createRow(rowCount++);

        int columnCount = 0;
        Cell cell = row.createCell(columnCount++);
        cell.setCellValue(placeid);

        cell = row.createCell(columnCount++);
        cell.setCellValue(placename);

        cell = row.createCell(columnCount++);
        cell.setCellValue(placeaddress);
        
        cell = row.createCell(columnCount++);
        cell.setCellValue(placenum);
        
        cell = row.createCell(columnCount++);
        cell.setCellValue(placemail);

        cell = row.createCell(columnCount);
        cell.setCellValue(placedesc);

    }
}

//Export the table for the Review
public void exportR() {
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd");  
	LocalDateTime now = LocalDateTime.now(); 
	String name = dtf.format(now);
	
    String excelFilePath = "D:\\DB_Backup\\review\\"+name+".csv";
    Connection connection = DBConnectionUtil.openConnection();
    try {
        String sql = "SELECT * FROM MYTravel_review";

        Statement statement = connection.createStatement();

        ResultSet result = statement.executeQuery(sql);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Reviews");

        writeHeaderLineR(sheet);

        writeDataLinesR(result, workbook, sheet);

        FileOutputStream outputStream = new FileOutputStream(excelFilePath);
        workbook.write(outputStream);
      //  workbook.close();

        //statement.close();

    } catch (SQLException e) {
        System.out.println("Datababse error:");
        e.printStackTrace();
    } catch (IOException e) {
        System.out.println("File IO error:");
        e.printStackTrace();
    }
}

private void writeHeaderLineR(XSSFSheet sheet) {

    Row headerRow = sheet.createRow(0);

    Cell headerCell = headerRow.createCell(0);
    headerCell.setCellValue("reviewid");

    headerCell = headerRow.createCell(1);
    headerCell.setCellValue("placename");

    headerCell = headerRow.createCell(2);
    headerCell.setCellValue("rating");

    headerCell = headerRow.createCell(3);
    headerCell.setCellValue("review");

}

private void writeDataLinesR(ResultSet result, XSSFWorkbook workbook,
        XSSFSheet sheet) throws SQLException {
 
	int rowCount = 1;  
    while (result.next()) {

        String reviewid = result.getString("reviewid");
        String placename = result.getString("placename");
        String rating = result.getString("rating");
        String reviews = result.getString("review");


        Row row = sheet.createRow(rowCount++);

        int columnCount = 0;
        Cell cell = row.createCell(columnCount++);
        cell.setCellValue(reviewid);

        cell = row.createCell(columnCount++);
        cell.setCellValue(placename);

        cell = row.createCell(columnCount++);
        cell.setCellValue(rating);

        cell = row.createCell(columnCount);
        cell.setCellValue(reviews);

    }
}

	
}

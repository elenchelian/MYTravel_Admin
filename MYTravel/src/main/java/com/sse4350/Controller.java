package com.sse4350;

import java.io.FileOutputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import  org.apache.poi.hssf.usermodel.HSSFSheet;  
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import  org.apache.poi.hssf.usermodel.HSSFRow;  


import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sse4350.DAO.DAO;
import com.sse4350.model.*;


/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO DAO = null;
	RequestDispatcher rd = null;
	String clickDelete = "";
	String clickUpdate = "";
	String clickLogin = "";

	
    public Controller() {
        DAO = new DAO();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getParameter("action");
		switch (action) {
		
		case "UPDATE":
			rd = request.getRequestDispatcher("/editsingle.jsp");
			rd.forward(request, response);
			break;
		case "BackUp_User":
			user_dtl(request, response);
			break;
		case "BackUp_Place":
			place_dtl(request, response);
			break;
		case "BackUp_Review":
			review_dtl(request, response);
			break;
		default:
			UserList(request, response);
			break;
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		switch(action) {
		case "login":
			login(request, response);
			break;
		case "UserList":
			UserList(request, response);
			break;
		case "ADDPLC":
			rd = request.getRequestDispatcher("/addplaces.jsp");
			rd.forward(request, response);
			break;
		case "EDTPLC":
			rd = request.getRequestDispatcher("/editsingle.jsp");
			rd.forward(request, response);
			break;
		case "DLTPLC":
			rd = request.getRequestDispatcher("/deletesingle.jsp");
			rd.forward(request, response);
			break;
		case "REVIEW":
			ReviewList(request, response);
			break;
		case "ADD":
			savePlace(request, response);
			break;
		case "UPDATE":
			getSinglePlaceUpdate(request, response);
			break;
		case "PLC":
			PlaceList(request, response);
			break;
		case "DLT":
			getSinglePlaceDelete(request, response);
			break;
		case "ADMINPLC":
			rd = request.getRequestDispatcher("/addadmin.jsp");
			rd.forward(request, response);
			break;
		case "ADDADMIN":
			saveAdmin(request, response);
			break;
		case "ADMINRCVPLC":
			rd = request.getRequestDispatcher("/EditAdmin.jsp");
			rd.forward(request, response);
			break;
		case "ADMINRCV":
			RecoverAdmin(request, response);
			break;
		case "SAVEDB":
//			saveDB(request, response);
//			break;
		case "GODB":
			rd = request.getRequestDispatcher("/backup.jsp");
			rd.forward(request, response);
			break;
		}

	}

	int check=0;
	private void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		
		if (clickLogin == "") {
			String username = request.getParameter("Username");
			String pass = request.getParameter("pass");
			System.out.println("username n pass word send "+username+" "+pass);
			boolean loginCheck = DAO.login(username,pass);
			
			if (loginCheck) {
				System.out.println("valid");
				check=0;
				UserList(request, response);
			} else {
				check++;
				System.out.println("invalid "+check);
				request.setAttribute("NOTIFICATION", " "+check+" invalid attempt !!!");
				
					if(check>=3) {
						adminLogin adminlog = new adminLogin();
						request.setAttribute("adminlog", adminlog);
						request.setAttribute("NOTIFICATION", "Login Disabled");
						rd = request.getRequestDispatcher("/index.jsp");
						rd.forward(request, response);
					}
					
				rd = request.getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
			}

		} else {
			//updatePlace(request, response);
		}
	}
	
	private void UserList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		DAO dao = new DAO();
		List<user> theList = dao.getUser();
		request.setAttribute("user", theList);
		System.out.println("list on the user"+theList);
		RequestDispatcher rd = request.getRequestDispatcher("/userlist.jsp");
		rd.forward(request, response);	

	}
	
	protected void savePlace(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		place pl = new place();
		pl.setPlaceid(request.getParameter("placeID"));
		pl.setPlacename(request.getParameter("placeName"));
		pl.setPlaceaddress(request.getParameter("placeAddress"));
		pl.setPlacenum(request.getParameter("placeContactNum"));
		pl.setPlacemail(request.getParameter("placeEmail"));
		pl.setPlacedesc(request.getParameter("placeDesc"));
		
		if (DAO.save(pl)) {
			request.setAttribute("NOTIFICATION", "Place Registered Successfully!");
		}

		request.setAttribute("place", pl);
		RequestDispatcher rd = request.getRequestDispatcher("/addplaces.jsp");
		rd.forward(request, response);
	}
	
	private void getSinglePlaceUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		if (clickUpdate == "") {
			String placeID = request.getParameter("placeID");
			boolean placeFound = DAO.checkPlace(placeID);

			if (placeFound) {
				place thePlace = DAO.get(placeID);
				System.out.print("singleget"+thePlace);
				request.setAttribute("place", thePlace);
				clickUpdate = "Display";
				rd = request.getRequestDispatcher("/placeupdate.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("NOTIFICATION", "Place ID Not Found!");
				rd = request.getRequestDispatcher("/editsingle.jsp");
				rd.forward(request, response);
			}

		} else {
			updatePlace(request, response);
		}
	}
	
	
	
	private void updatePlace(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		place pl = new place();
		
		pl.setPlaceid(request.getParameter("placeID"));
		pl.setPlacename(request.getParameter("placeName"));
		pl.setPlaceaddress(request.getParameter("placeAddress"));
		pl.setPlacenum(request.getParameter("placeContactNum"));
		pl.setPlacemail(request.getParameter("placeEmail"));
		pl.setPlacedesc(request.getParameter("placeDesc"));
		System.out.print("update place :"+pl);

		if (DAO.update(pl)) {
			request.setAttribute("NOTIFICATION", "Place Updated Successfully!");
			clickUpdate = "";
		}

		place theBook = DAO.get(request.getParameter("placeID"));
		request.setAttribute("place", theBook);
		RequestDispatcher rd = request.getRequestDispatcher("/placeupdate.jsp");
		rd.forward(request, response);

	}
	
	private void PlaceList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		DAO dao = new DAO();
		List<place> theList = dao.getPlace();
		request.setAttribute("place", theList);
		System.out.println("new"+theList);
		RequestDispatcher rd = request.getRequestDispatcher("/tableplaces.jsp");
		rd.forward(request, response);	

	}
	
	private void getSinglePlaceDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		if (clickDelete == "") {
			String pl = request.getParameter("placeID");
			boolean placeFound = DAO.checkPlace(pl);

			if (placeFound) {
				place thePlace = DAO.get(pl);
				request.setAttribute("place", thePlace);
				clickDelete = "Display";
				rd = request.getRequestDispatcher("/placedelete.jsp");
				rd.forward(request, response);
				
			} else {
				request.setAttribute("NOTIFICATION", "Place ID Not Found!");
				rd = request.getRequestDispatcher("/deletesingle.jsp");
				rd.forward(request, response);
			}

			} else {
				DeletePlace(request, response);
			}

			}
	
	private void DeletePlace(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			String pl = request.getParameter("placeID");
			if (DAO.delete(pl)) {
			request.setAttribute("NOTIFICATION", "Place Deleted Successfully!");
			clickDelete = "";
			}

			RequestDispatcher rd = request.getRequestDispatcher("/deletesingle.jsp");
			rd.forward(request, response);

			}
	
	private void ReviewList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		DAO dao = new DAO();
		List<review> theList = dao.getReview();
		request.setAttribute("review", theList);
		System.out.println("review "+theList);
		RequestDispatcher rd = request.getRequestDispatcher("/tablereview.jsp");
		rd.forward(request, response);	

	}
	
	protected void saveAdmin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Admin admin = new Admin();
		admin.setAdminUsername(request.getParameter("adminUsername"));
		admin.setAdminPassword(request.getParameter("adminPassword"));
	
	
		if (DAO.addAdmin(admin)) {
			request.setAttribute("NOTIFICATION", "Admin Registered Successfully!");
		}

		request.setAttribute("Admin", admin);
		RequestDispatcher rd = request.getRequestDispatcher("/addadmin.jsp");
		rd.forward(request, response);
	}

	private void RecoverAdmin (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Admin admin = new Admin();
		
		admin.setAdminUsername(request.getParameter("adminUsername"));
		admin.setAdminPassword(request.getParameter("adminPassword"));

		if (DAO.updateadmin(admin)) {
			request.setAttribute("NOTIFICATION", "Admin Password Reset Successfully!");
			clickUpdate = "";
		}

		Admin theAdmin = DAO.getAdmin(request.getParameter("adminUsername"));
		request.setAttribute("Admin", theAdmin);
		RequestDispatcher rd = request.getRequestDispatcher("/EditAdmin.jsp");
		rd.forward(request, response);

	}
	
	private void user_dtl(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		DAO dao = new DAO();
		dao.exportU();
		
		request.setAttribute("NOTIFICATION", "User Table BackUp is Success!");

		RequestDispatcher rd = request.getRequestDispatcher("/backup.jsp");
		rd.forward(request, response);

		}
	
	private void place_dtl(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		DAO dao = new DAO();
		dao.exportP();
		request.setAttribute("NOTIFICATION", "Place Table BackUp is Success!");

		RequestDispatcher rd = request.getRequestDispatcher("/backup.jsp");
		rd.forward(request, response);
	
		}
	
	private void review_dtl(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		DAO dao = new DAO();
		dao.exportR();
		request.setAttribute("NOTIFICATION", "Review Table BackUp is Success!");

		RequestDispatcher rd = request.getRequestDispatcher("/backup.jsp");
		rd.forward(request, response);
	
		}
}

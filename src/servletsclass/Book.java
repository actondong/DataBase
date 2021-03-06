package servletsclass;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Date;

/**
 * Servlet implementation class Book
 */
@WebServlet("/Book")
public class Book extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException cnfe) {
		}
	}
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Book() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("in post now");
		String TripidStr = request.getParameter("Tripid");
		int Tripid=Integer.parseInt(TripidStr);
		String Name = request.getParameter("Name");
		String Phone = request.getParameter("Phone");
		String Passport = request.getParameter("Passport");
		String Email = request.getParameter("Email");
		String Seattype = request.getParameter("Seattype");
		String Flightnumber = request.getParameter("Flightnumber");
		String Airline = request.getParameter("Airline");
		String Depttime = request.getParameter("Depttime");
		Date Date=new java.util.Date();
		java.sql.Date sqlDate;
		sqlDate = new java.sql.Date(Date.getTime());

		System.out.println("Name:"+Name);
		System.out.println("Seat:"+Seattype);
		PrintWriter pw = response.getWriter();
		System.out.println("load form variables");
		try {
			if (conn == null) {
				// Edit the following to use your endpoint, database name,
				// username, and password
				conn = DriverManager
						.getConnection(
								"jdbc:mysql://cs4111.cf7twhrk80xs.us-west-2.rds.amazonaws.com:3306/cs4111?allowMultiQueries=true",
								"sd2810", "26842810");
				System.out.println("connecting to db");
			}
		 String sqlstmt;
		 
		 sqlstmt="select passport_number from Book_trip where passport_number= "+Passport+" and tripid= "+Tripid + " ";
		 Statement stmt0=conn.createStatement();
		 ResultSet rset0 = stmt0.executeQuery(sqlstmt);
		 sqlstmt="select name from Passenger where passport_number= "+Passport +" ";
		 Statement stmt1=conn.createStatement();
		 ResultSet rset1 = stmt1.executeQuery(sqlstmt);
		 if(!rset0.next()){
			 PreparedStatement stmt;
			 if(!rset1.next()){
				 
		 System.out.println(Seattype);
		 if(Seattype.equals("1")){
			  sqlstmt=" insert into Passenger values (?, ?, ?, ?); insert into Book_trip values(?, ?, ?); update Flight set economy_available_number=economy_available_number-1 where flight_number=? and airline=? and depart_time=? ";
		 }else if(Seattype.equals("2")){
			  sqlstmt=" insert into Passenger values (?, ?, ?, ?); insert into Book_trip values(?, ?, ?); update Flight set business_available_number=business_available_number-1 where flight_number=? and airline=? and depart_time=? ";
		 }else{
			  sqlstmt=" insert into Passenger values (?, ?, ?, ?); insert into Book_trip values(?, ?, ?); update Flight set first_class_available_number=first_class_available_number-1 where flight_number=? and airline=? and depart_time=? ";
		 }	 

		 	stmt = conn.prepareStatement(sqlstmt);

			System.out.println("prequery done"); 
			stmt.setString(1, Passport);
			stmt.setString(2, Name);
			stmt.setString(3, Phone);
			stmt.setString(4, Email);
			
			stmt.setString(5, Passport);
			stmt.setInt(6, Tripid);
			stmt.setDate(7, sqlDate);
			stmt.setString(8, Flightnumber);
			stmt.setString(9, Airline);
			stmt.setString(10, Depttime);
			}else{
				 if(Seattype.equals("1")){
					  sqlstmt="  insert into Book_trip values(?, ?, ?); update Flight set economy_available_number=economy_available_number-1 where flight_number=? and airline=? and depart_time=? ";
				 }else if(Seattype.equals("2")){
					  sqlstmt="  insert into Book_trip values(?, ?, ?); update Flight set business_available_number=business_available_number-1 where flight_number=? and airline=? and depart_time=? ";
				 }else{
					  sqlstmt="  insert into Book_trip values(?, ?, ?); update Flight set first_class_available_number=first_class_available_number-1 where flight_number=? and airline=? and depart_time=? ";
				 }	 
				 	stmt = conn.prepareStatement(sqlstmt);
					System.out.println("prequery done"); 
					stmt.setString(1, Passport);
					stmt.setInt(2, Tripid);
					stmt.setDate(3, sqlDate);
					stmt.setString(4, Flightnumber);
					stmt.setString(5, Airline);
					stmt.setString(6, Depttime);
				
			}
			System.out.println("set vars done"); 
			stmt.executeUpdate();
			System.out.println("query done"); 
			pw.write(" <p class='text-primary'>Congrat!!!Your ticket has been reserved, Thank you for using our booking system! </p>");
			}else{
				 System.out.println("duplicated");
				pw.write("<p class='text-primary'>Sorry!!! You can only book one ticket for yourself in A trip </p>");
			}
		 
		}catch (SQLException e) {
			pw.println(e.getMessage());
		}
		pw.close();
	}

}

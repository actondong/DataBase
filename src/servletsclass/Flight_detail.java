package servletsclass;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.*;
/**
 * Servlet implementation class Flight_detail
 */
@WebServlet("/Flight_detail")
public class Flight_detail extends HttpServlet {
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
    public Flight_detail() {
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
		String Airline = request.getParameter("Airline");
		String Flight_number = request.getParameter("Flightnumber");
		String Dept_time = request.getParameter("Depttime");
		System.out.println("depttime:"+Dept_time);
		PrintWriter pw = response.getWriter();
		System.out.println("load form variables");
		try {
			if (conn == null) {
				// Edit the following to use your endpoint, database name,
				// username, and password
				conn = DriverManager
						.getConnection(
								"jdbc:mysql://cs4111.cf7twhrk80xs.us-west-2.rds.amazonaws.com:3306/cs4111",
								"sd2810", "26842810");
				System.out.println("connecting to db");
			}
			
			
			
			String sqlstmt="select T.economy_price, T.business_price,T.first_class_price, "
					+ "F.economy_available_number,F.business_available_number, F.first_class_available_number "+
					"from Trip T, Flight F "+ 
					"where T.tripid = "+
					"(select tripid "
					+"from Trip_consists "
					+"where airline=? and flight_number=? and depart_time=?) and "
					+" F.airline=? and F.flight_number=? and F.depart_time=? ";
			
			PreparedStatement stmt = conn.prepareStatement(sqlstmt);
			stmt.setString(1, Airline);
			stmt.setString(2, Flight_number);
			stmt.setString(3, Dept_time);
			stmt.setString(4, Airline);
			stmt.setString(5, Flight_number);
			stmt.setString(6, Dept_time);
			ResultSet rset = stmt.executeQuery();
			String Result="";
			String Econ_price="";
			String Busi_price="";
			String First_price="";
			String Econ_available_number="";
			String Busi_available_number="";
			String First_available_number="";
			
			while(rset.next()){
			 Econ_price=rset.getString(1);
			 Busi_price=rset.getString(2);
			 First_price=rset.getString(3);
			 Econ_available_number=rset.getString(4);
			 Busi_available_number=rset.getString(5);
			 First_available_number=rset.getString(6);

			}
			
			Result="<table class='table table-bordered'>"
					+ "<tr><td class='text-primary'> Econ_price</td>"+ "<td class='text-primary'>Econ_available_number</td>"
					+ "<td class='text-primary'>"+ "Busi_price</td>"	+ "<td class='text-primary'>Busi_available_number</td>"
					+ "<td class='text-primary'>First_price</td>"   +	"<td class='text-primary'> First_available_number</td></tr>";
				
				
			Result=Result+"<tr><td class='text-primary'>"+Econ_price+"</td><td class='text-primary'>"+Econ_available_number+"</td><td class='text-primary'>"+Busi_price+"</td>"
					+"<td class='text-primary'>"+Busi_available_number+"</td><td class='text-primary'>"+First_price+"</td>"
					+"<td class='text-primary'>"+ First_available_number+"</td></tr></table>";
			
		
			pw.print(Result);
			System.out.println("writeback");
	}catch (SQLException e) {
		pw.println(e.getMessage());
	}
	pw.close();

}
	
}
	
	

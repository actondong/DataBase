<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="java.sql.*"%>
<%
Connection conn = null;
ResultSet rset = null;
String error_msg = "";
String Dept_city = request.getParameter("Dept_city");
String Arr_city = request.getParameter("Arr_city");
String Transfer = request.getParameter("Transfer_or_not");
System.out.println(Transfer);	
System.out.println(Transfer.equals("0"));

if(Transfer.equals("0")) {
try {

	if (conn == null) {
	
		// Edit the following to use your endpoint, database name,
		// username, and password
		Class.forName("com.mysql.jdbc.Driver");

		conn = DriverManager
				.getConnection(
						"jdbc:mysql://cs4111.cf7twhrk80xs.us-west-2.rds.amazonaws.com:3306/cs4111",
						"sd2810", "26842810");
		System.out.println("connecting");		
	}

String sqlstmt = "SELECT T.tripid,F.airline,F.flight_number,F.depart_airport,F.depart_time,F.arrive_airport,F.arrive_time "
		+ "FROM Trip T,Airport A1,Airport A2,Trip_consists Tc, Flight F "
		+ "WHERE F.depart_airport=A1.airport AND F.arrive_airport=A2.airport AND A1.city=? AND A2.city=? AND "
		+ "T.tripid=Tc.tripid AND F.flight_number=Tc.flight_number AND F.airline=Tc.airline AND F.depart_time=Tc.depart_time AND "
		+ "T.depart_airport=F.depart_airport and T.arrive_airport=F.arrive_airport ";

PreparedStatement stmt = conn.prepareStatement(sqlstmt);
stmt.setString(1, Dept_city);
stmt.setString(2, Arr_city);
rset = stmt.executeQuery();
System.out.println("getting result from db");		
} catch (SQLException e) {
error_msg = e.getMessage();
if( conn != null ) {
conn.close();
}
}

}else{
	
	
	
	
}
%>
  

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Page2</title>
<style type="text/css">
@import
"bootstrap.css";

 body{
        margin:0px;
        padding:0px;
        text-align:center;
        background:#e9fbff;
    }
    #container{
        position:relative;
        margin:0 auto;
        padding:0px;
        width:1200px;

        background:url(container_bg.jpg)  repeat-y;
    }
    #banner{
        margin:0px;
        padding:0px;
        width:1200px;
    }
    #banner img{
        height:200px;
        width:300px;
    }
    #contentup{
    	width:1200px;
        position:absolute;
        top:530px;
       
        background-color:#afdcff;
    
    }
    
    #contentdown{
    width:1200px;
        position:absolute;
        top:900px;
    
        background-color:#afdcff;
    
    }

</style>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="http://www.jquery4u.com/scripts/function-demos-script.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("button[name=Detail]").click(function() {
		var sendinfo= {"Tripid":$(this).parent().siblings("td").eq(0).text(),"Airline":$(this).parent().siblings("td").eq(1).text(),"Flightnumber":$(this).parent().siblings("td").eq(2).text(),"Depttime":$(this).parent().siblings("td").eq(4).text()};
		//alert($(this).parent().siblings("#testtr").attr("value"));
		$.ajax({

			url : "${pageContext.request.contextPath}/Flight_detail",

			type : "post",

			data : sendinfo,

			dataType : "html",

			success : function(data) {

				$("#content_rightup").html(data);
				//$(this).parent().parent().siblings("#Detail").html(data);	
			
			},

			error : function() {
				alert("into error");
				$(this).parent().parent().siblings("#Detail").html("XXX");


			},

		});

return false;
		
		
	});
});


</script>
</head>
<body>

<div id="container">

<div id="banner">



</div>

<div id="content_left">
</div>

<div id="content_rightup">

</div>
<div id="content_right">
	
    <p class="text-info"> Flights For You  </p>
	<table class='table table-bordered'>
	
	<tr><td class='text-info' >Trip Id</td><td class='text-info' >Airline</td><td class='text-info' >Flight_number</td><td class='text-info'>Depart Airport</td><td class='text-info'>Depart Time</td><td class='text-info'>Arrive Airport</td><td class='text-info'>Arrive Time</td>
    </tr>
<%
	if(Transfer.equals("0")){
	if(rset != null){
	
		System.out.println("rset not null");		
		while(rset.next()){
			out.print("<tr><td class='text-primary' name='Tripid'>"+rset.getString(1)+"</td> <td class='text-primary' name='Airline' >"
					+ rset.getString(2) + "</td>"
					+ "<td class='text-primary' name='Flight_number)'>" + rset.getString(3)
					+ "</td>" + " <td class='text-primary'>"
					+ rset.getString(4) + "</td>"
					+ " <td class='text-primary' name='Dept_time'>" + rset.getString(5)
					+ "</td>" + " <td class='text-primary'>"
					+ rset.getString(6) + "</td>"
					+ " <td class='text-primary'>" + rset.getString(7)+ "</td>"+
				    "<td><button name='Detail' type='button' class='btn btn-primary'>Detail</button></td>"
				    +"<td><a name = 'Book' type='button' class='btn btn-primary' method='post' href='Page3.jsp?Tripid="+rset.getString(1)+"&Airline="+rset.getString(2)+"&Flight_number="+rset.getString(3)+"&Dept_time="+rset.getString(5)+"'>Book</a></td></tr>");
				
		}
	}

	} else {
		
		
		
		
		
		
		
		
	}
%>
	</table>
	



</div>
</div>
</body>
</html>

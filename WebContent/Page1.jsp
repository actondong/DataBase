<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Page1</title>
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
        width:900px;
        text-align:left;
        background:url(container_bg.jpg)  repeat-y;
    }
    #banner{
        margin:0px;
        padding:0px;
        width:900px;
    }
    #banner img{
        height:50px;
        width:900px;
    }
    #links{
        font-size:20px;
        margin:-15px 0px 0px 0px;
        padding:0px;
        position:relative;
        background-color:#afdcff;
    }
    #links li{
        list-style:none;/*无列表符号*/
        float:left;/*向右伸展*/
        margin:0px 60px 0px 0px;/*相邻列表之间的距离*/
    }
    /*左侧部分之超链接*/
    #links a:link, #nav a:visited{
        text-decoration:none;
        color:#2a4f6f;
        background-color:transparent;
    }
    #content{
        margin:50px 0px 0px 0px;
        width:900px;
    }
    #content_left{
        width:150px;
        position:absolute;
        top:80px;
        left:0px;

    }
    #content_left img{
        width:100px;
        height:100px;
         
        border:1px solid #0073cc;
        margin-bottom:5px;
        text-align:center;
    }
    #content_left h3{
        text-align:center;
        margin-top:0px;
    }
    #content_left p{
        text-align:justify;
        padding:0px 10px 0px 10px;
    }
    #content_right{
        left-padding:20px;
        position:absolute;
        top:80px;
        right:300px;
        width:300px;

    }
    #content_right h3{
        margin-top:10px;
        text-align:left;
    }
    #footer{
 
        width:900px;
        text-align:center;
    }
    
    
</style>
<script>
	
</script>

</head>
<body>

	<div id="container">
		<div id="banner"></div>
		<div id="links"></div>
		<div id="content">
			<div id="content_left">
			   
				<form id="Passenger_login"
					class="form-group has-success has-feedback" action="./Booklog.jsp" method="POST">
             
					Passport_number: <input name="Passport_number" type="text" class="form-control"> 
					Passenger_email: <input name="Passenger_email" type="text" class="form-control">
					<br/>
					<button type="submit" class="btn btn-primary" id="User_login">Login</button>
				</form>
				<p>
					<font size=1 class="text-info">Login to check for your
						booking information</font>
				</p>
				 <img src="EZpic.jpg"/>
			</div>

			<div id="content_right">
				<form id="Flight_search" class="form-group has-success has-feedback"
					action="./Page2.jsp" method="POST">

					DepartCity: <input id="dept_city_for_search" type="text"
						name="Dept_city" class="form-control"> ArriveCity: <input
						id="Arr_city_for_search" type="text" name="Arr_city"
						class="form-control"> DepartTime:<input type="text"
						name="dept_time_for_search" class="form-control" name="Dept_time">

					<div class="checkbox">

						<div>
							<input type="radio" id="Nonestop" value="0"
								name="Transfer_or_not">
							<p class="text-info">None-Stop only</p>
						</div>
						<div>
							<input type="radio" id="transfer" value="1"
								name="Transfer_or_not">
							<p class="text-info">Allow Transfer</p>
						</div>

					</div>
					<button id="Flight_search_submit" type="submit"
						class="btn btn-primary">Search</button>
				</form>
			</div>

		</div>
	</div>
	<div id="footer"></div>



</body>
</html>
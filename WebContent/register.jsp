<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.util.HashMap"%>
<%@ page import= "java.util.Map"%>
<%@ page import="com.signify.internshipproject.controller.UserServlet" %>
<%@ page import="com.signify.internshipproject.DTO.UserDTO" %>
<% Map<Integer,String> results=(HashMap<Integer,String>)request.getAttribute("results");%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<title>LogIn Page</title>
<link rel="icon" href="Signify_2.png" type="image/png">
<link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">

<script>
function validate()
{ 
var username = document.form.username.value; 
var password = document.form.password.value;
var email = document.form.email.value;
var mobileno = document.form.mobileno.value;
var timezone_id=document.form.timezone_id.value;
if (username==null || username=="")
{ 
alert("username can't be blank"); 
return false; 
}
else if(password.length<6)
{ 
alert("Password must be at least 6 characters long."); 
return false; 
}
else if (email==null || email=="")
{ 
alert("Email can't be blank"); 
return false; 
}
else if (mobileno.length!=10)
{ 
alert("Invalid mobileno"); 
return false; 
}
else if(timezone_id==null||timezone_id=="")
{
	alert("enter timezone_id");
}


document.forms["form"].submit();

}

function handleEnter(e) {
	  if(e.keyCode === 13){
	      compare()
	  }
	}

function cancel(){
	
}
</script>
<style>
html { 
	background: url(login_bg.jpg) no-repeat center fixed; 
	background-size: cover;
}


body {
    font: 17px Montserrat, sans-serif;
    line-height: 1;
    color: #f5f6f7;
}

.text_input {
	width: 63%;
	height: 25px;
	font: 17px Montserrat, sans-serif;
}

.extra_text_input {
	width: 70%;
}

.checkbox_input {
	width: 5%;
	height: 15px;
	
}

.font_style_form {
	font: 20px Montserrat, sans-serif;
}
.timezone_id .select{
  width: 120%;
}

.timezone_id .select select{
  background: transparent;
  line-height: 1;
  border: 0;
  padding: 0;
  border-radius: 0;
  width: 120%;
  position: relative;
  z-index: 10;
  font-size: 1em;
}


.rounded_corner {
	border-radius: 6px;
}

.forgot_password_container,
.signup_container,
.login_container {
	background-color:hsla(156, 100%, 50%, 0.5);
	position: absolute;
	text-align: center;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	width: 20%;
	display: block;

}

.signup_container {
	display: block;
}

.forgot_password_container {
	display: block;
}

.error_div_container {
	width: 100%;
	font: 20px Montserrat, sans-serif;
	text-align: center;
	color: Crimson;
	display: block;
	


}
a:link {
  color: red;
}

/* visited link */
a:visited {
  color: green;
}

/* mouse over link */
a:hover {
  color: hotpink;
}

/* selected link */
a:active {
  color: blue;
}
</style>
</head>
 
<body>
  <div class="signup_container" id="signup_div_id"> 
  
			
	<form name="form" action='loginRegister?actionType=register2' method="post">
	
	  <img src="Signify_3.png" alt="Signify_logo" style="width: 100%; height: 145px; padding-bottom: 10px;"/> <br/>
	<h3> New User Registration: </h3>
	
	<div class="error_div_container">
	<h3 style="color:red;">${message}</h3>
    <h3 style="color:yellow;">${successMessage}</h3></div>
    
		<input type="text" name="username" class="text_input extra_text_input" placeholder="UserName"/> <br/> <br/>
		<input type="password" name="password" class="text_input extra_text_input" placeholder="Password"/> <br/> <br/>
		<input type="password" name="password1" class="text_input extra_text_input" placeholder="Confirm Password"/> <br/> <br/>
		<input type="email" name="email" class="text_input extra_text_input" placeholder="email"/><br/><br />
		<input type="text" name="mobileno" class="text_input extra_text_input" placeholder=" Mobile number "/> <br/> <br/>
		<div style="height: 40px;">
		<select id="timezone_id" name="timezone_id" style="width:70%" >
         <% for(Map.Entry<Integer, String> entry: results.entrySet()) {
         Integer timezone_id=entry.getKey();
          String name=entry.getValue();%>
        <option value="<%=timezone_id %>"><%=name%></option>
          <%}%>   
</select></div> <br/> <br/>
<input type="button" value="Submit" onclick="return validate()" class="font_style_form rounded_corner" onkeydown="handleEnter(event)"> &nbsp; | &nbsp;<input type="button" value="Cancel" class="font_style_form rounded_corner" onclick="window.location.href='login.jsp ';">
</form>
</div>	
</body>
</html>
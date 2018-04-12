<%@page import="model.post.PostBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.UserBean"%>
<%@page import="controller.manager.CollectionsManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<title>W3.CSS Template</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">



<style>

.myButton {
	background-color:#44c767;
	-moz-border-radius:28px;
	-webkit-border-radius:28px;
	border-radius:28px;
	border:1px solid #18ab29;
	display:inline-block;
	cursor:pointer;
	color:#ffffff;
	font-family:Verdana;
	font-size:17px;
	padding:16px 31px;
	text-decoration:none;
	text-shadow:0px 1px 0px #2f6627;
}
.myButton:hover {
	background-color:#5cbf2a;
}
.myButton:active {
	position:relative;
	top:1px;
}

<!-- search bar-->

* {box-sizing: border-box;}

body {
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
}

.topnav {
  overflow: hidden;
  background-color: #e9e9e9;
}

.topnav a {
  float: left;
  display: block;
  color: black;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}

.topnav a:hover {
  background-color: #ddd;
  color: black;
}

.topnav a.active {
  background-color: #2196F3;
  color: white;
}

.topnav input[type=text] {
  float: right;
  padding: 6px;
  margin-top: 8px;
  margin-right: 16px;
  border: none;
  font-size: 17px;
}

@media screen and (max-width: 600px) {
  .topnav a, .topnav input[type=text] {
    float: none;
    display: block;
    text-align: left;
    width: 100%;
    margin: 0;
    padding: 14px;
  }
  .topnav input[type=text] {
    border: 1px solid #ccc;  
  }
}


/*Resize the wrap to see the search bar change!*/
.wrap{
  width: 30%;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}



body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
.w3-third img{margin-bottom: -6px; opacity: 0.8; cursor: pointer}
.w3-third img:hover{opacity: 1}




</style>
<body class="w3-light-grey w3-content" style="max-width:1600px">


<div class="topnav">
	<form name="searchBox" method="post" action="searchUsers">
    <input type="text" placeholder="Search users.." name="username">
    </form>
</div>


<!-- Sidebar/menu -->
<nav class="w3-sidebar w3-bar-block w3-white w3-animate-left w3-text-grey w3-collapse w3-top w3-center" style="z-index:3;width:300px;font-weight:bold" id="mySidebar"><br>
  
  
  <h3><b><a href="userfeed" class="w3-padding-64 w3-center"><%= ((UserBean)session.getAttribute("user")).getUsername() %></a></b></h3>
  
  <img src="https://www.w3schools.com/w3images/natureboy.jpg" style="width:70%"></img>
  <a href="javascript:void(0)" onclick="w3_close()" class="w3-bar-item w3-button w3-padding w3-hide-large">CLOSE</a>
  <a href="friendsfeed" onclick="w3_close()" class="w3-bar-item w3-button">Friends Feed</a>
  <a href="trendingfeed" onclick="w3_close()" class="w3-bar-item w3-button">Trending Feed</a>
  <a href="imageupload.jsp" onclick="w3_close()" class="w3-bar-item w3-button">Image upload</a> 
  <a href="profileedit.jsp" onclick="w3_close()" class="w3-bar-item w3-button">Profile</a>
  <a href="logout" onclick="w3_close()" class="w3-bar-item w3-button">Logout</a>
</nav>

<!-- Top menu on small screens -->
<header class="w3-container w3-top w3-hide-large w3-white w3-xlarge w3-padding-16">
  <span class="w3-left w3-padding">SOME NAME</span>
  <a href="javascript:void(0)" class="w3-right w3-button w3-white" onclick="w3_open()">x</a>
</header>

<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:300px">

  <!-- Push down content on small screens --> 
  <div class="w3-hide-large" style="margin-top:83px"></div>
  
  <!-- Photo grid -->
  
    	<%
    	ArrayList<PostBean> posts = (ArrayList<PostBean>)request.getAttribute("posts");   	
    	for(int i = 0; i<posts.size(); i++){
    		//	if(i==posts.size()-1) break;
    		String url = posts.get(i).getUrl();
    		%>
    		 <img src="<%=posts.get(i).getUrl()%>" style="width:42%"  onclick="onClick(this)" >
    		
    	<%} %>
      
      <!-- OLD PART 
      <img src="https://www.w3schools.com/w3images/girl_mountain.jpg" style="width:100%" onclick="onClick(this)" alt="What a beautiful scenery this sunset">
      <img src="https://www.w3schools.com/w3images/girl.jpg" style="width:100%" onclick="onClick(this)" alt="The Beach. Me. Alone. Beautiful">
    </div>

    <div class="w3-third">
      <img src="https://www.w3schools.com/w3images/boy.jpg" style="width:100%" onclick="onClick(this)" alt="Quiet day at the beach. Cold, but beautiful">
      <img src="https://www.w3schools.com/w3images/man_bench.jpg" style="width:100%" onclick="onClick(this)" alt="Waiting for the bus in the desert">
      <img src="https://www.w3schools.com/w3images/natureboy.jpg" style="width:100%" onclick="onClick(this)" alt="Nature again.. At its finest!">
    </div>
    
    <div class="w3-third">
      <img src="https://www.w3schools.com/w3images/girl.jpg" style="width:100%" onclick="onClick(this)" alt="Canoeing again">
      <img src="https://www.w3schools.com/w3images/girl_train.jpg" style="width:100%" onclick="onClick(this)" alt="A girl, and a train passing">
      <img src="https://www.w3schools.com/w3images/closegirl.jpg" style="width:100%" onclick="onClick(this)" alt="What a beautiful day!">
    </div>
  </div>
-->
 

  
  <!-- Modal for full size images on click-->
  <div id="modal01" class="w3-modal w3-black" style="padding-top:0" onclick="this.style.display='none'">
    <span class="w3-button w3-black w3-xlarge w3-display-topright">x—</span>
    <div class="w3-modal-content w3-animate-zoom w3-center w3-transparent w3-padding-64">
      <img id="img01" class="w3-image">
      <p id="caption"></p>
    </div>
  </div>

  <!-- About section -->
  
  <div class="w3-container w3-dark-grey w3-center w3-text-light-grey w3-padding-32" id="about">



  	<br><hr><br>

<% UserBean user = (UserBean)request.getAttribute("user");
   String firstName = user.getFirstName() == null ? "" : user.getFirstName();
   String lastName = user.getLastName() == null ? "" : user.getLastName();
   String email = user.getEmail();
   
   %>
    <h4><b><%= user.getUsername()  %></b></h4>
    <% UserBean currentUser = (UserBean)request.getSession().getAttribute("user");
   
    
    if(!user.getUsername().equals(currentUser.getUsername())) {
     request.setAttribute("subscriber", currentUser.getId());
    request.setAttribute("subscribedToUser", user.getId());
    %>
    <form action="subscription" method="post">
	<input type="hidden" name="subscriberUserId" value=<%=currentUser.getId()%> >
	<input type="hidden" name="subscribedUserId" value=<%=user.getId()%> >
	<button type="submit" class="myButton">Subscribe</button>
	</form>
    <br>
    <%} %>
    <img src="https://www.w3schools.com/w3images/avatar_hat.jpg" alt="Me" class="w3-image w3-padding-32" width="600" height="650">
    <div class="w3-content w3-justify" style="max-width:600px">
   
      <h4><%= firstName + " " + lastName %></h4>
      <p>
      
      </p>
      <p>mail: <%= email %></p>
      <hr class="w3-opacity">
  
<!-- End page content -->
</div>

<script>

//Using jQuery for pressing enter in the search box

$(function() {
    $('form').each(function() {
        $(this).find('input').keypress(function(e) {
            // Enter pressed?
            if(e.which == 10 || e.which == 13) {
                this.form.submit();
            }
        });

        $(this).find('input[type=submit]').hide();
    });
});


// Script to open and close sidebar
function w3_open() {
    document.getElementById("mySidebar").style.display = "block";
    document.getElementById("myOverlay").style.display = "block";
}
 
function w3_close() {
    document.getElementById("mySidebar").style.display = "none";
    document.getElementById("myOverlay").style.display = "none";
}

// Modal Image Gallery
function onClick(element) {
  document.getElementById("img01").src = element.src;
  document.getElementById("modal01").style.display = "block";
  var captionText = document.getElementById("caption");
  //captionText.innerHTML = element.alt;
  captionText.innerHTML = "amatrixable said: asdasd <br> turo said: aasd"
  
}

</script>


</body>
</html>

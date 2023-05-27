<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
</style>
</head>
<body>
<link href='//html/css/font-awesome.min.css' rel='stylesheet'/>
<link href='css/client/home.css' rel='stylesheet'/>
<div class="profile-card-1">
        <!--image-->
        <div class="img">
            <img src="img/img_avatar.png"/>
        </div>
        <a class="view-more" href="">
            <i class="fa fa-plus-circle"></i>
        </a>
        <!--text-->
        <div class="mid-section">
            <div class="name">${loggedInUser.fullName}</div>  
            <div class="description"> Developer - Supper Vip -
        <a href="#">Palestine</a></div>

       <div class="line"></div>
       <div class="stats">
           <div class="stat">53.4M
               <div class="subtext">Followers</div>
           </div>
           <div class="stat">324K
            <div class="subtext">Liks</div>
        </div>
        <div class="stat">236K
            <div class="subtext">Shares</div>
        </div>
       </div>
        </div>
        <!--social icons-->
        <div class="links">
            <a class="fb" href="">
                <i class="fa fa-facebook-f" aria-hidden="true"></i>
            </a>
            <a class="twitter" href="">
                <i class="fa fa-twitter" aria-hidden="true"></i>
            </a>

            <a class="follow" href="">
                <i class="fa fa-rocket" aria-hidden="true"></i>
            </a>
        </div>

    </div>
</body>
</html>
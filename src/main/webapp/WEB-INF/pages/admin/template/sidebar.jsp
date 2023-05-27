<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<div class="container-fluid">
    <div class="col-md-3">

      <div id="sidebar">
        <div class="container-fluid tmargin">
          <div class="input-group">
<!--             <input type="text" class="form-control" placeholder="Search..." />
            <span class="input-group-btn">
              <button class="btn btn-default">
                <span class="glyphicon glyphicon-search"></span>
              </button>
            </span> -->
          </div>
        </div>

        <ul class="nav navbar-nav side-bar">       
         
          <li class="side-bar tmargin">
            <a href="<c:url value='/admin/order'/>">
              <span class="glyphicon glyphicon-shopping-cart">&nbsp;</span>Manage Order</a>
          </li>
        
          <li class="side-bar">
            <a href='<c:url value="/admin/product"/>' >
              <span class="glyphicon glyphicon-folder-open">&nbsp;</span>Manage Product</a>
          </li>
        
          <li class="side-bar">
            <a href='<c:url value="/admin/category"/>'>
              <span class="glyphicon glyphicon-tasks">&nbsp;</span>Manage Category</a>
          </li>
          
          <li class="side-bar">
            <a href='<c:url value="/admin/manufacturer"/>' >
              <span class="glyphicon glyphicon-flag">&nbsp;</span>Manage Manufacturer</a>
          </li>

          <li class="side-bar main-menu">
            <a href="<c:url value='/admin/account' />">
              <span class="glyphicon glyphicon-th-list">&nbsp;</span>Manage Account</a>
          </li>
          
          <li class="side-bar main-menu">
            <a href="<c:url value='/admin/contact' />">
              <span class="glyphicon glyphicon-envelope">&nbsp;</span>Manage Contact</a>
          </li>
          
          <li class="side-bar main-menu">
            <a href="<c:url value='/admin/statistic' />">
              <span class="glyphicon glyphicon-signal">&nbsp;</span>Statistic</a>
          </li>
          
          <li class="side-bar">
            <a href="<c:url value='/admin/profile' />">
              <span class="glyphicon glyphicon-user">&nbsp;</span>Personal Info</a>
          </li>

        </ul>
      </div>
    </div>
    
    <script src="<c:url value='/js/admin.js'/>" ></script>

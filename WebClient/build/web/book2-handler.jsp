<%@ page language="java" import="java.util.*, java.io.*" %>
  
<%   
int iduser = 1;
int idvilla = 1;
String checkIn = request.getParameter("checkIn");
String checkOut = request.getParameter("checkOut");
String totalGuest = request.getParameter("totalGuest");
String notes = request.getParameter("notes");

response.sendRedirect("book3.jsp");
%>
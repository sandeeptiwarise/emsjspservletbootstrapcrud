package com.wipro.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.wipro.dao.DatabaseOps;
import com.wipro.model.Employee;

@WebServlet("/")
public class EmsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseOps databaseOps;

	public EmsController() {
		super();

	}

	public void init(ServletConfig config) throws ServletException {

		databaseOps = new DatabaseOps();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();

		switch (action) {

		case "/new":
			showNewForm(request, response);
			break;
		case "/insert":
			insertEmployee(request, response);
			break;
		case "/delete":
			deleteEmployee(request, response);
			break;
		case "/edit":
			showEditForm(request, response);
			break;
		case "/update":
			updateEmployee(request, response);
			break;
		default:
			listEmployees(request, response);
			break;

		}

	}

	protected void listEmployees(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<Employee> listEmployee = databaseOps.rcrAllEmployee();
		request.setAttribute("listemployee", listEmployee);
		
		RequestDispatcher redirect = request.getRequestDispatcher("user-list.jsp");
		redirect.forward(request, response);
	}

	protected void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher redirect = request.getRequestDispatcher("user-form.jsp");
		redirect.forward(request, response);
	}

	protected void insertEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ememail = request.getParameter("txtemail");
		String emaddress = request.getParameter("txtaddress");
		String emdesig = request.getParameter("txtdesig");
		double emsalary = Double.parseDouble(request.getParameter("txtsalary"));

		Employee newempobj = new Employee(ememail, emaddress, emdesig, emsalary);
		databaseOps.rcrInsert(newempobj);
		response.sendRedirect("list");
	}

	protected void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ememail = request.getParameter("urlemail");
		Employee existingEmp = databaseOps.rcrGetEmployee(ememail);

		RequestDispatcher redirect = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("employee", existingEmp);
		redirect.forward(request, response);
	}

	protected void updateEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String ememail = request.getParameter("txtmail");
		String emaddress = request.getParameter("txtaddress");
		String emdesig = request.getParameter("txtdesig");
		double emsalary = Double.parseDouble(request.getParameter("txtsalary"));

		Employee editempobj = new Employee(ememail, emaddress, emdesig, emsalary);
		databaseOps.rcrUpdate(editempobj);
		response.sendRedirect("list");

	}
	
	protected void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String ememail = request.getParameter("urlemail");
		System.out.println(ememail);
		databaseOps.rcrDelete(ememail);
		response.sendRedirect("list");

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		service(request, response);
	}

}

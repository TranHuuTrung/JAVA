package controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.User;
import model.dao.UserDAO;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserDAO userDAO;

	public UserServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		userDAO = new UserDAO();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println(email);
		System.out.println(password);
		String command = request.getParameter("command");
		if (command.equalsIgnoreCase("register")) {
			String password2 = request.getParameter("password2");
			if (password.equalsIgnoreCase("") || email.equalsIgnoreCase("")) {
				response.getWriter().print("Not empty field");
			} else if (!password.equalsIgnoreCase(password2)) {
				response.getWriter().print("Password not match");
			} else if (!userDAO.getItemByKey(email).isEmpty()) {
				response.getWriter().print("Email đã tồn tại, Login: <a href ='login.jsp'> here</a>");
			} else {
				// register
				User item = new User(new Date().getTime(), password, email);
				if (userDAO.addItem(item) > 0) {
					response.getWriter().print("Login Success, Login: <a href ='login.jsp'> here</a>");
				}
			}
		} else if (command.equalsIgnoreCase("login")) {
			// check validate
			if (password.equalsIgnoreCase("") || email.equalsIgnoreCase("")) {
				request.setAttribute("message", "Not empty field");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			} else {
				// set cookie
				if (request.getParameter("remember") != null) {
					Cookie c = new Cookie("cookieEmail", email);
					c.setMaxAge(24 * 60 * 60);
					response.addCookie(c);
					c = new Cookie("cookiePassword", password);
					c.setMaxAge(24 * 60 * 60);
					response.addCookie(c);
				} else {
					Cookie cookieEmail = new Cookie("cookieEmail", null);
					cookieEmail.setMaxAge(0);
					Cookie cookiePass = new Cookie("cookiePassword", null);
					cookiePass.setMaxAge(0);
					response.addCookie(cookieEmail);
					response.addCookie(cookiePass);
				}
				// check login
				System.out.println("UserServlet"+ email);
				System.out.println("UserServlet"+ password);
				User user = (userDAO.checkUser(email, password));
				if (user == null) {
					request.setAttribute("message", "Login failed, or Register: <a href ='register.jsp'> here</a>");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				} else {
					request.getSession().setAttribute("user", user);
					response.sendRedirect("home.jsp");
				}
			}
			return;
		}
		if (command.equalsIgnoreCase("changePassword")) {
			String oldPass = request.getParameter("oldPassword");
			String newPass = request.getParameter("newPassword");
			String newPass2 = request.getParameter("newPassword2");
			String message = "";
			if (oldPass.equalsIgnoreCase("") || newPass.equalsIgnoreCase("") || newPass2.equalsIgnoreCase("")) {
				message = "Not empty field.";
			} else if (!newPass.equals(newPass2)) {
				message = "New Password not match.";
			} else {
				User user = (User) request.getSession().getAttribute("user");
				if (!user.getPass().equals(oldPass)) {
					message = "Old Password not match.";
				} else {
					user.setPass(newPass);
					if (userDAO.editItem(user) > 0) {
						message = "Update Success.";
					} else {
						message = "Update failed, try again.";
					}
				}
			}
			request.setAttribute("message", message);
			request.getRequestDispatcher("profile.jsp").forward(request, response);

		}

	}

}

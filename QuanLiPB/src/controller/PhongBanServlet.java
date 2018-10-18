package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.PhongBan;
import model.dao.PhongBanDAO;

@WebServlet("/PhongBanServlet")
public class PhongBanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PhongBanDAO _DAO = new PhongBanDAO();

	public PhongBanServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// view
		String key = (request.getParameter("key") == null) ? "" : request.getParameter("key");
		int page = 0;
		int totals = _DAO.countTotal("key");
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {		}

		ArrayList<PhongBan> items = _DAO.getList(key, page * 5, 5);
		request.setAttribute("totals", String.valueOf(totals));
		request.setAttribute("items", items);
		request.getRequestDispatcher("xemphongban.jsp").forward(request, response);
		return;

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// edit

		// delete
	}

}

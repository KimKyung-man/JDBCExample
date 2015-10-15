package kr.pe.gracefulife.jdbc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.pe.gracefulife.jdbc.dao.UserDao;
import kr.pe.gracefulife.jdbc.dto.User;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserController() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		User user = UserDao.findUser(userId);
		// .......... something
	}

}

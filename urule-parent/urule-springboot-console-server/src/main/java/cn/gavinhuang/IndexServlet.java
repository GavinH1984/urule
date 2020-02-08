package cn.gavinhuang;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author gavin huang
 * @since 2020/2/6
 */
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 9155627652423910928L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(req.getContextPath()+"/urule/frame");
	}
}

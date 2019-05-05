package xiuxiuxiu.servelt;

import xiuxiuxiu.dao.ArticleDAO;

import xiuxiuxiu.dao.ArticleDAOImpl;
import xiuxiuxiu.pojo.Article;
import xiuxiuxiu.util.Page;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import com.sun.nio.sctp.MessageInfo;


import java.io.IOException;
import java.util.List;

@WebServlet(name = "ArticleServlet", urlPatterns = { "/ArticleServlet" })
public class ArticleServlet extends HttpServlet {
	private ArticleDAO articleDAO = new ArticleDAOImpl();
	private Article article = new Article();
	int start = 0;
	int count = 5;

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");

			/* 获取分页信息 */
			if (request.getParameter("page.start") != null) {
				start = Integer.parseInt(request.getParameter("page.start"));
			}
			if (request.getParameter("page.count") != null) {
				count = Integer.parseInt(request.getParameter("page.count"));
			}
			Page page = new Page(start, count);
			page.setTotal(3);

			if (request.getParameter("page.start") != null) {
				list(request, response, page);
			}
			if (request.getParameter("type") != null) {	
    			if (request.getParameter("type").equals("list")) {
    					list(request, response, page);
    			}
    			if(request.getParameter("type").equals("single")) {
    				single(request,response,page);
    			}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}


	public void list(HttpServletRequest request, HttpServletResponse response, Page page)
			throws ServletException, IOException {
		List<Article> articles = articleDAO.list(page.getStart(), page.getCount());
		int total = articleDAO.getTotalArticle();
		page.setTotal(total);
		request.setAttribute("articles", articles);
		request.setAttribute("page", page);
		// Servlet本身并不输出响应到客户端，因此必须将请求转发到视图页面
		RequestDispatcher rd;
		// 获取转发对象
		rd = request.getRequestDispatcher("文章&通知.jsp");
		// 转发请求
		rd.forward(request, response);
	}
	
	//用户点击进入详细文章页面
	public void single(HttpServletRequest request, HttpServletResponse response, Page page)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.print(id);
		article = articleDAO.getArticle(id);
		request.setAttribute("article", article);
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("文章详情页.jsp");
		rd.forward(request, response);
	}


}

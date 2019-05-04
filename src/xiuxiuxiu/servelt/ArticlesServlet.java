package xiuxiuxiu.servelt;

import java.io.Console;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.sun.javafx.scene.layout.region.Margins.Converter;

import xiuxiuxiu.dao.ArticleDAOImpl;
import xiuxiuxiu.dao.ComponentDAOImpl;
import xiuxiuxiu.pojo.Article;
import xiuxiuxiu.pojo.Component;
import xiuxiuxiu.util.Page;

@WebServlet("/ArticlesServlet")
public class ArticlesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    ArticleDAOImpl articleDao = new ArticleDAOImpl();
    Date day=new Date();    

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticlesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");
        String authorName = request.getParameter("authorName");
        String text = request.getParameter("text");
        
       
        String time=df.format(day);
       
        Article article = new Article();
        article.setTitle(title);
        article.setAuthorName(authorName);
        int id=article.getid(authorName);
        System.out.println(id);
        article.setAuthorId(id);
        article.setTime(time);
        article.setText(text);
       
        articleDao.addArticle(article);
        // 服务端跳转
        request.getRequestDispatcher("articles-list.jsp").forward(request, response);

    }
    void list(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	
        List<Article> articles = articleDao.getList();
        request.setAttribute("articles", articles);
        request.getRequestDispatcher("articles-list.jsp").forward(request, response);
      
    }
    
    void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	
        int id = Integer.parseInt(request.getParameter("id"));
        /*if (!articleDao.isArticleExist(id)) {
            request.setAttribute("err", "错误：该零件已被删除");
            list(request, response); // 客户端跳转
        } else { */
            articleDao.deleteArticle(id);
            list(request, response); // 客户端跳转
        //}
    }

 void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
	 int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String authorName = request.getParameter("authorName");
        String text = request.getParameter("text");
        
      
        String time=df.format(day);
       
        Article article = new Article();
        
        int authorId=article.getid(authorName);
        System.out.println(authorId);
        article.setAuthorId(id);
        article.setId(id);
        article.setTitle(title);
        article.setAuthorName(authorName);
        article.setAuthorId(authorId);
        article.setTime(time);
        article.setText(text);
        
        articleDao.updateArticle(article);
        list(request, response); // 客户端跳转
        
    }
    void search(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
     
	    int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String authorName = request.getParameter("authorName");
        String text = request.getParameter("text");
        
      
        String time=df.format(day);
       
        Article article = new Article();
        article.setId(id);
        article.setTitle(title);
        article.setAuthorName(authorName);
        article.setTime(time);
        article.setText(text);
        
        articleDao.updateArticle(article);
        list(request, response); // 客户端跳转
        
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) {
        try {
        	request.setCharacterEncoding("UTF-8");
            String method = request.getParameter("method");
          
            if (method.equals("add")) {
            	
                add(request, response);
            } else if (method.equals("update")) {
               update(request, response);
            } else if (method.equals("delete")) {
            	
               delete(request, response);
            }/*else if (method.equals("search")) {
               	System.out.println("search");
                search(request, response);
            } */else {
            	
               list(request, response);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
}


package xiuxiuxiu.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import xiuxiuxiu.pojo.Article;
import xiuxiuxiu.pojo.*;
import xiuxiuxiu.util.DBUtil;

public class ArticleDAOImpl implements ArticleDAO{

	public void addArticle(Article article) {
		// TODO Auto-generated method stub
				String sql = "insert into article(author_id,author_name,title,text,time) values(? ,? ,? ,? ,? )";
				try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
					ps.setInt(1, article.getAuthorId()); 
					ps.setString(2, article.getAuthorName());
					ps.setString(3, article.getTitle());
					ps.setString(4, article.getText());
					ps.setString(5, article.getTime());
					ps.execute();
					ResultSet rs = ps.getGeneratedKeys();
					if (rs.next()) {
						int id = rs.getInt(1);
						//article.setID(id);
					}
				} catch (SQLException e) {

					e.printStackTrace();
				}
	}

	public void deleteArticle(int id) {
		// TODO Auto-generated method stub
				String sql = "delete from article where id = ?";
				try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
					ps.setLong(1, id);
					ps.execute();

				} catch (SQLException e) {

					e.printStackTrace();
				}
	}

	public void updateArticle(Article article) {
		// TODO Auto-generated method stub
		String sql = "update article set author_id=?,author_name=?,title=?,text=?,time=? where id=?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			
			ps.setInt(1, article.getAuthorId());
			ps.setString(2, article.getAuthorName());
			ps.setString(3, article.getTitle());
			ps.setString(4, article.getText());
			ps.setString(5, article.getTime());
			ps.setInt(6, article.getId());
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	public Article getArticle(int id) {
		// TODO Auto-generated method stub
		String sql = "select id,author_id,author_name,title,text,time from article where id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setLong(1, id);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			if (rs.next()) {
				Article article=new Article();
				article.setId(rs.getInt("id"));
				article.setAuthorName(rs.getString("author_name"));
				article.setText(rs.getString("text"));
				article.setTitle(rs.getString("title"));
				article.setAuthorId(rs.getInt("author_id"));
				article.setTime(rs.getString("time"));
				return article;
			} else {
				System.out.println("该id不存在！！");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}


	public List<Article> searchArticle(String str) {
		// TODO Auto-generated method stub
		String sql = "select id,author_id,author_name,title,text,time from article from article ORDER BY id";
		List<Article> articleList = new ArrayList<Article>();
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.execute();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				Article article = new Article();
				//reservation.setID(rs.getString("user_id"));
				articleList.add(article);
			}
			return articleList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getTotalArticle() {
		// TODO Auto-generated method stub
		int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            String sql = "select count(*) from article";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
	}

	@Override
	public List<Article> list(int start, int count) {
		// TODO Auto-generated method stub
		String sql = "select * from article";
		List<Article> articleList = new ArrayList<Article>();
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.execute();
            ResultSet rs = ps.getResultSet();
            int n=0;
            while(rs.next()) 
            {
            	Article bean = new Article();
            	if( n>=start && n<(start+count) )
            	{
            		bean.setId(rs.getInt(1));
            		bean.setTime(rs.getString(6));
               		bean.setTitle(rs.getString(4));
            		bean.setAuthorName(rs.getString(3));
            		bean.setText(rs.getString(5));
            		articleList.add(bean);
            	}          
            	n++;
            }
            return articleList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return null;
	}

	@Override
	public List<Article> list() {
		// TODO Auto-generated method stub
		String sql = "select * from article";
		List<Article> articleList = new ArrayList<Article>();
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) 
            {
            	Article bean = new Article();
            	bean.setTime(rs.getString(6));
           		bean.setTitle(rs.getString(4));
        		bean.setAuthorName(rs.getString(3));
        		articleList.add(bean);
            }
            return articleList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}
	public List<Article> getList() {
		// TODO Auto-generated method stub
				String sql = "select id,author_id,author_name,title,text,time from article ORDER BY id";
				List<Article> articleList = new ArrayList<Article>();
				try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
					ps.execute();
					ResultSet rs = ps.getResultSet();
					while (rs.next()) {
						Article article = new Article();
						//reservation.setID(rs.getString("user_id"));
						article.setId(rs.getInt("id"));
						article.setAuthorId(rs.getInt("author_id"));
						article.setAuthorName(rs.getString("author_name"));
						article.setTitle(rs.getString("title"));
						article.setText(rs.getString("text"));
						article.setTime(rs.getString("time"));
						articleList.add(article);
					}
					return articleList;
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
		}
}


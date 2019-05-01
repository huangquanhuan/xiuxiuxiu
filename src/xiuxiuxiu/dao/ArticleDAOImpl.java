package xiuxiuxiu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xiuxiuxiu.pojo.*;
import xiuxiuxiu.util.DBUtil;

public class ArticleDAOImpl implements ArticleDAO{

    @Override
	public void addArticle(Article article) {
		// TODO Auto-generated method stub
				String sql = "insert into article(author_id,author_name,title,text,time) values(? ,? ,? ,? ,? )";
				try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
					//ps.setString(1, article.getID()); 
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

    @Override
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
    
    @Override
	public void updateArticle(Article article) {
		// TODO Auto-generated method stub
		String sql = "update article set author_id=?,author_name=?,title=?,text=?,time=? where id=?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			//ps.setString(1, article.getName());
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    @Override
	public Article getArtice(int id) {
		// TODO Auto-generated method stub
		String sql = "select id,author_id,author_name,title,text,time from article where id = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setLong(1, id);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			if (rs.next()) {
				Article article=new Article();
				//article.setID(rs.getString("id"));
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

    @Override
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
		return 0;
	}
}

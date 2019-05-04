package xiuxiuxiu.pojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import xiuxiuxiu.util.DBUtil;
public class Article {
    //文章id
    private int id;
    //作者id 外键为管理员id
    private int authorId;
    //作者名
    private String authorName;
    //标题
    private String title;
    //内容
    private String text;
    ////格式2019-04-25(13:30~17:30)
    private String time;
    
    public Article() {
        this.id = 0;
        this.authorId = 0;
        this.authorName = "未知";
        this.text = "";
        this.title = "未定";
        this.time = "未知";
    }
    
    public int getAuthorId() {
        return authorId;
    }
    public String getAuthorName() {
        return authorName;
    }
    public int getId() {
        return id;
    }
    public String getText() {
        return text;
    }
    public String getTime() {
        return time;
    }
    public String getTitle() {
        return title;
    }
    
    
    public void setId(int id) {
        if(id >= 0)
            this.id = id;
    }
    public void setAuthorId(int authorId) {
        if(authorId >= 0 )
            this.authorId = authorId;
    }
    public void setAuthorName(String authorName) {
        if(!authorName.equals(""))
            this.authorName = authorName;
    }
    public void setText(String text) {
        if(!text.equals(""))
            this.text = text;
    }
    public void setTitle(String title) {
        if(!title.equals(""))
            this.title = title;
    }
    public void setTime(String time) {
        if(!time.equals(""))
            this.time = time;
    }
    
    public Article getArticle() {
        return this;
    }
    
    public Article setArticle(int id,int authorId,String authorName,String title,String text,String time)
    {
        if (id >= 0 && authorId>=0 && !authorName.equals("") && !title.equals("") && !text.equals("") && !time.equals("")) 
        {
            this.id = id;
            this.authorId = authorId;
            this.authorName = authorName;
            this.title = title;
            this.text = text;
            this.time = time;
        }
        return this;
    }
    
    public Article setArticle(final Article a) {
        this.id = a.id;
        this.authorId = a.authorId;
        this.authorName = a.authorName;
        this.title = a.title;
        this.text = a.text;
        this.time = a.time;
        return this;
    }
    public int getid(String authorName) {
		String sql ="select id from manger where name = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, authorName);
			ps.execute();
			ResultSet rs = ps.getResultSet();
			if (rs.next()) {
				int id=rs.getInt("id");
				//System.out.println("cao"+id);
				return id;
			} else {
				System.out.println("该id不存在！！");
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		
	}
}

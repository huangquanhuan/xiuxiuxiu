package xiuxiuxiu.dao;

import xiuxiuxiu.pojo.*;

import java.util.List;

import xiuxiuxiu.pojo.Article;


/**
 * ArticleDAO - 封装有关Article 表的操作
 * 对 Article表的所有操作应通过实现该接口实现
 *
 * @author masgak
 * @date 2019-04-29
 */
public interface ArticleDAO {
	/**
     * add() - 添加一个 article 对象到表中
     *
     * @param article 实例化后的 Article 对象
     */
	void addArticle(Article article);
	/**
     * delete() - 从文章表中删除某一对象，以 ID 为依据
     *
     * @param id 文章的 ID 号
     */
	void deleteArticle(int id);
	/**
     * update() - 把对 Article 对象的修改写回数据库中
     *
     * @param article 要保存的 Article 对象
     */
	void updateArticle(Article article);
	/**
     * getTotal() - 返回 Article 表的总记录数
     *
     * @return 一个整数，表示 article 表的记录数，也就是用户总数
     * TODO: 斟酌该方法的返回值应该设为 int 还是 long
     */
	public int getTotalArticle();
	/**
     * getArticle() - 返回 Article 表详细信息
     *
     * @return 相应id的文章详细信息
     */
	public Article getArtice(int id);
	/**
     *@return 一个包含文章对象的List
     * 
     */

	public List<Article> searchArticle(String str);//返回id的List
	
	public List<Article> getList(); //获取文章列表

	List<Article> list(int start,int count);

}

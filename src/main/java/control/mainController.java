package control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.GuestbookArticleDao;
import vo.GuestbookArticle;

public class mainController implements Controller {
	GuestbookArticleDao guestbookArticleDao;
	
	public mainController(GuestbookArticleDao guestbookArticleDao) {
		this.guestbookArticleDao = guestbookArticleDao;
	}
	
	public void setGuestbookArticleDao(GuestbookArticleDao guestbookArticleDao) {
		this.guestbookArticleDao = guestbookArticleDao;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		if(model.get("email") != null) {
			GuestbookArticle guestbookArticle = new GuestbookArticle();
			guestbookArticle.setEmail((String) model.get("email"));
			guestbookArticle.setPwd((String) model.get("pwd"));
			guestbookArticle.setArticle((String) model.get("article"));
			guestbookArticleDao.insertArticle(guestbookArticle);
		}
		
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		if(model.get("orderCond") != null)
			paramMap.put("orderCond", (String) model.get("orderCond"));
		else
			paramMap.put("orderCond", "MODIFIED_TIME");
		
		List<GuestbookArticle> articles = guestbookArticleDao.getList(paramMap);
		model.put("articles" , articles);
		
		return "/WEB-INF/views/main.jsp";
	}

}

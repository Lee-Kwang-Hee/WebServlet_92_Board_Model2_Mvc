package kr.or.bit.service;


import java.util.Enumeration;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Board;

public class BoardWriteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
	
    	ActionForward forward = null;

    	String uploadpath = request.getSession().getServletContext().getRealPath("upload");
		int size = 1024*1024*10;
		try {
			MultipartRequest multi = new MultipartRequest(
					request,
					uploadpath,
					size,
					"UTF-8",
					new DefaultFileRenamePolicy()		
					);	
			String subject = multi.getParameter("subject");
	    	String writer = multi.getParameter("writer");
	    	String email = multi.getParameter("email");
	    	String homepage = multi.getParameter("homepage");
	    	String content = multi.getParameter("content");
	    	String pwd = multi.getParameter("pwd");
	    	String filename = multi.getFilesystemName("filename");
	    	//Enumeration filenames = multi.getFileNames();
			
	    	Board board = new Board(0, writer, pwd, subject, content, null, 0, filename, 0, homepage, email, 0, 0, 0);
			try {
				BoardDao dao = new BoardDao();
		    	int result = dao.writeok(board);

		    	String msg="";
		        String url="";
		        if(result > 0){
		        	msg ="insert success";
		        	url ="list.board";
		        }else{
		        	msg="insert fail";
		        	url="write.board";
		        }
		        
		        request.setAttribute("board_msg", msg);
		        request.setAttribute("board_url", url);
		        
		        forward = new ActionForward();
		        forward.setRedirect(false);
		        forward.setPath("/WEB-INF/board/redirect.jsp");
		        
			} catch (NamingException e) {
				e.printStackTrace();
				
			}
			
			
		} catch (Exception e) {
			
		}
    	
		
		
		return forward;
	}

}

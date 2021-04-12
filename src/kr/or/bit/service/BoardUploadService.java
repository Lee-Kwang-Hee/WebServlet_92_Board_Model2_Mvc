package kr.or.bit.service;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;

public class BoardUploadService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
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
		} catch (Exception e) {
			
		}
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("");
		return forward;
	}

}

package kr.or.bit.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;

public class BoardDownloadService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String filename = request.getParameter("filename");

		String savepath = "upload";
		String downloadpath = request.getSession().getServletContext().getRealPath(savepath);
		String filepath = downloadpath+"\\"+filename;
		
		byte[] b = new byte[4096];
		
		try {
			FileInputStream in = new FileInputStream(filepath);
			String sMimeType = request.getSession().getServletContext().getMimeType(filepath); 
			if(sMimeType == null)
			{
				sMimeType = "application/octet-stream";
			}
			response.setContentType(sMimeType);
			try {
				   response.setHeader("Content-Disposition", "attachment;filename="+new String(filename.getBytes(),"ISO8859_1"));
			} catch (Exception e) {
				
			}
			try {
				 ServletOutputStream out2 = response.getOutputStream();
				    int numread;
				    while((numread = in.read(b,0,b.length)) != -1){
				       out2.write(b,0,numread);
				    }
			} catch (Exception e) {
				
			}

			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		return null;
	}

}

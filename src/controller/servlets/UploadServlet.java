package controller.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import controller.manager.PostManager;
import model.UserBean;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/upload")
@MultipartConfig(fileSizeThreshold = 6291456, // 6 MB
		maxFileSize = 10485760L, // 10 MB
		maxRequestSize = 20971520L) // 20 MB
public class UploadServlet extends HttpServlet {

	private static final String UPLOAD_DIR = "photos";

		
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			try {
			
			// constructs path of the directory to save uploaded file
			String uploadFilePath = "/home/maxim/Programming/Eclipse/PicssshareProject/WebContent/" + UPLOAD_DIR;
			// creates upload folder if it does not exists
			File uploadFolder = new File(uploadFilePath);
			if (!uploadFolder.exists()) {
				uploadFolder.mkdirs();
			}
			PrintWriter writer = response.getWriter();
			// write all files in upload folder
			UserBean user = (UserBean) request.getSession().getAttribute("user");

			Part part = request.getPart("file");
			InputStream input = part.getInputStream();
			
				if (part != null && part.getSize() > 0) {
					String fileName = user.getUsername() + "img" + user.getPosts().size() + ".jpg";
					
					// allows only JPEG files to be uploaded
//					if (!contentType.equalsIgnoreCase("image/jpeg") && !contentType.equalsIgnoreCase("image/png")) {
//						continue;
//					}
					File f = new File(uploadFilePath + File.separator + fileName);
					f.createNewFile();
					FileOutputStream out = new FileOutputStream(f);
					
					IOUtils.copy(input,out);
					input.close();
					out.close();
					
					//part.write(uploadFilePath + File.separator + fileName);
					
					PostManager.getInstance().addPost(user, (UPLOAD_DIR + "\\" + fileName));
					
					writer.append("File successfully uploaded to " + uploadFolder.getAbsolutePath() + File.separator
							+ fileName);
				
			}
			request.getRequestDispatcher("successfullupload.jsp").forward(request, response);
			} catch(Exception e) {
				request.setAttribute("error", "Sorry, something went totally wrong!");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
	}

}

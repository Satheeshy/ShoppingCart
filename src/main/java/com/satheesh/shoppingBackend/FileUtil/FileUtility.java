package com.satheesh.shoppingBackend.FileUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUtility {
	private static Logger logger = LoggerFactory.getLogger(FileUtility.class);
	private static String Real_path = null;
	private static final String ABSOLUTE_PATH = "/Users/satheesh/Applications/eclipse-workspace/Ecommerce/src/main/webapp";

	public static boolean uploadFile(HttpServletRequest req,MultipartFile file,String code) {
		Real_path = req.getSession().getServletContext().getRealPath("/assests/images");
		logger.info(Real_path);
		if(!new File(Real_path).exists()) {
			new File(Real_path).mkdirs();
			//created a new directory if directory doesn't exist
		}
		if(!new File(ABSOLUTE_PATH).exists()) {
			new File(ABSOLUTE_PATH).mkdirs();
		}
		//now keep the file at both locations
		
		try {
			file.transferTo(new File(Real_path+code+".jpg"));
			file.transferTo(new File(ABSOLUTE_PATH+code+".jpg"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	public static void uploadNoImage(HttpServletRequest request, String code) {
		// get the real server path
		Real_path = request.getSession().getServletContext().getRealPath("/assets/images/");
	
		String imageURL = "http://placehold.it/640X480?text=No Image";
		String destinationServerFile = Real_path + code + ".jpg";
		String destinationProjectFile = Real_path + code + ".jpg";
				
		try {
			URL url = new URL(imageURL);				
			try (	
					InputStream inputstream = url.openStream();
					OutputStream osREAL_PATH = new FileOutputStream(destinationServerFile);
					OutputStream osABS_PATH = new FileOutputStream(destinationProjectFile);
				){
			
				byte[] b = new byte[2048];
				int length;
				while((length = inputstream.read(b))!= -1) {
					osREAL_PATH.write(b, 0, length);
					osABS_PATH.write(b, 0, length);
				}
			}			
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}

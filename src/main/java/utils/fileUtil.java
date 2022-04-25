package utils;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class fileUtil {
	
	public static File saveFileUploadExcel(Part part) {
		File folderUpload = new File("C:/Users/TranSang/Desktop/excel/");
		if (!folderUpload.exists()) {
			folderUpload.mkdirs();
		}
		File file = new File("C:/Users/TranSang/Desktop/excel/", part.getSubmittedFileName());
		if (!file.exists()) {
			try {
				part.write(file.getAbsolutePath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}

	public static File saveFileUpload(File nameFolder, Part part) {
		File file = new File(nameFolder, part.getSubmittedFileName());
		if (!file.exists()) {
			try {
				part.write(file.getAbsolutePath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}
	
	public static File createForder(String nameFolder) {
		File folderUpload = new File("E:/esclipe/SANGTM_PH17730_ASM/src/main/webapp/img/" + nameFolder);
		if (!folderUpload.exists()) {
			folderUpload.mkdirs();
		}
		return folderUpload;
	}
}
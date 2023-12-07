package handler;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileRemoveHandler {

	private static final Logger log = LoggerFactory.getLogger(FileRemoveHandler.class);
	
	//파일 이름과 경로를 받아 파일을 삭제하는 메서드드드드드드드드드드드드드드드드드듣
	//리턴타입 int / 되면 1, 안 되면 0
	public int deleteFile(String imageFileName, String savePath) {
		
		boolean isDel = false; //삭제가 되었는지 체크하는 변수
		log.info("deleteFile method check" + imageFileName);
		
		File fileDir = new File(savePath);
		File removeFile = new File(fileDir+fileDir.separator+imageFileName);
		File removeThumbFile = new File(fileDir+fileDir.separator+"thum_"+imageFileName);
		
		//파일이 존재해야 삭제
		if(removeFile.exists() || removeThumbFile.exists()) {
			isDel = removeFile.delete();
			log.info(">>> fileRemove : "+(isDel ? "ok":"fail"));
			if(isDel) {
				isDel = removeThumbFile.delete();
				log.info(">>> ThumbFileRemove : "+(isDel ? "ok":"fail"));
			}
		}
		
		log.info(">>> remove Ok");
		
		return isDel? 1:0;
	}
	
}

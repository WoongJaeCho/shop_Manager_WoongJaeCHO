package Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import dao.CartDAO;
import dao.ItemDAO;
import dao.UserDAO;

public class FileManager {
	
	private final String CUR_PATH = System.getProperty("user.dir")+"/src/"+this.getClass().getPackageName()+"/";
	
	private void saveToFileData(String fileName, String data) {
		try(FileWriter fw = new FileWriter(CUR_PATH+fileName)){
			fw.write(data);
			System.out.println(fileName+" 파일 저장 성공");
		} catch (IOException e) {
			System.out.println(fileName+" 파일 저장 실패");
		}
	}
	
	public void saveToFile(UserDAO uDAO, ItemDAO iDAO, CartDAO cDAO) {
		String uData = uDAO.saveToData();
		String iData = iDAO.saveToData();
		String cData = cDAO.saveToData();
		saveToFileData("user.txt", uData);
		saveToFileData("item.txt", iData);
		saveToFileData("cart.txt", cData);
	}
	
	
	private String loadToFileData(String fileName) {
		try(FileReader fr = new FileReader(CUR_PATH+fileName);
				BufferedReader br = new BufferedReader(fr);){
			String data = "";
			while(true) {
				int read = br.read();
				if(read==-1) break;
				
				data += (char)read;
			}
			System.out.println(fileName+" 파일 로드 성공");
			return data;
		} catch (IOException e) {
			System.out.println(fileName+" 파일 로드 실패");
			return null;
		}
	}
	
	public void loadToFile(UserDAO uDAO, ItemDAO iDAO, CartDAO cDAO) {
		String uData = loadToFileData("user.txt");
		String iData = loadToFileData("item.txt");
		String cData = loadToFileData("cart.txt");
		uDAO.loadToFile(uData);
		iDAO.loadToFile(iData);
		cDAO.loadToFile(cData);
	}
	// cart.txt
	// user.txt
	// item.txt
}
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;

public class Multiplayer {

	public static void main(String[] args) throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException, FTPDataTransferException, FTPAbortedException {
		FTPClient ftpC = new FTPClient();
		String server = "7Sidorkin.netau.net";
		int port = 21;
		ftpC.connect(server, port); // Establish Connection
		ftpC.login("a1169379", "Sidorkin"); // Login
		//Upload the file
		try {
			ftpC.upload(new java.io.File("testFile.txt"));
			System.out.println("File uploaded. (:");
		} catch (IllegalStateException e) {
			System.out.println("Snagged an error: ");
			e.printStackTrace();
		} catch (FTPDataTransferException e) {
			System.out.println("Snagged an error: ");
			e.printStackTrace();
		} catch (FTPAbortedException e) {
			System.out.println("Snagged an error: ");
			e.printStackTrace();
		}
		
		//Download the file
		ftpC.download("testFile.txt", new java.io.File("testFile.txt"));
		System.out.println("Downloaded?");
		
	}

}

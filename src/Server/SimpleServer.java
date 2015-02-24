package Server;

import java.net.*;
import java.util.Arrays;
import java.util.Calendar;
import java.io.*;

public class SimpleServer {
	ServerSocket ss;
	DatagramSocket ds;
	SimpleServer server;
	Thread thread;
	Thread threadBrocast;

	// 建構函式
	public SimpleServer() { }

	public void setServerPort(int port){
		server = new SimpleServer(); 
		System.out.println("監聽PORT："+port);
		server.startServer(port);  //port 8888
	}

	private void startServer(int port) {
		try {
			// 執行緒-客戶端操作
			ss = new ServerSocket(port); // port由GUI_Main.class指定
			thread = new Thread(new ClientThread(ss));
			thread.start();

			// 執行緒-廣播 
			ds = new DatagramSocket(8899); // 廣播部分固定port
			threadBrocast = new Thread(new brocastThread(ds,ss));
			threadBrocast.start();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}


//執行緒-廣播 
class brocastThread implements Runnable {
	private ServerSocket ss;
	private Socket cs;
	private DatagramSocket ds;
	private DatagramPacket receive ;
	private DataOutputStream out;
	private byte[] recvBytes;

	// 建構函式
	public brocastThread(DatagramSocket ds, ServerSocket ss) throws Exception {
		this.ds = ds;
		this.ss = ss;
	}

	public void run() {
		try {
			while(true) {
				// 接收廣播
				receive = new DatagramPacket(new byte[1024], 1024);  
				ds.receive(receive);
				recvBytes = Arrays.copyOfRange(
						receive.getData(),
						0,  
						receive.getLength());
				String string=new String(recvBytes);
				// 顯示console
				System.out.println("Server receive a brocast msg:" +string ); 

				// 輸出Server IP
				if(!string.isEmpty()) {
					cs = new Socket(string, 3578);
					// 建立用戶端的輸出串流
					out = new DataOutputStream(cs.getOutputStream());
					out.writeUTF("192.168.1.35");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();//列印異常資訊
		} finally {//用finally語句塊確保動作執行
			try{
				if(cs != null){
					cs.close();
				}
				if(ds != null){
					ds.close();//關閉輸入串流
				}
			}
			catch(Exception e){
				e.printStackTrace();//列印異常資訊
			}
		}
	}
}


//執行緒-客戶端操作
class ClientThread implements Runnable {
	private ServerSocket ss;
	private Socket cs;
	private DataInputStream  in;
	private DataOutputStream out;

	// 建構函式
	public ClientThread(ServerSocket ss) throws Exception {
		this.ss = ss;
	}

	public void run() {
		try {
			while(true) {
				// 接受用戶端的連線請求
				cs = ss.accept();
				// 建立用戶端的輸入串流				
				in  = new DataInputStream (cs.getInputStream());
				// 建立用戶端的輸出串流
				out = new DataOutputStream(cs.getOutputStream());

				String now=Calendar.HOUR+":"+Calendar.MINUTE+":"+Calendar.SECOND+"";
				// 至客戶端接收的資料				
				String FromClient = in.readUTF();
				System.out.println("客戶端送來的訊息：\""+FromClient+"\"@"+now);
				ControlWMP wmp = new ControlWMP();
				ControlPPT ppt = new ControlPPT();
				ControlComputer cc = new ControlComputer();
				Folder f = new Folder();

				//電腦控制部分
				if(FromClient.equalsIgnoreCase("TESTTEST123123")) {
					//System.out.println("!!!!!!!!!!!!!!!!!!!");
					out.writeUTF("Conected");
				}
				else if(FromClient.equalsIgnoreCase("MRCode_CC_00")) cc.sleep(); //休眠
				else if(FromClient.equalsIgnoreCase("MRCode_CC_01")) cc.reset(); //重新開機
				else if(FromClient.equalsIgnoreCase("MRCode_CC_02")) cc.powerOff(); //關機

				//WMP控制部分
				else if(FromClient.equalsIgnoreCase("MRCode_WMP_00")) wmp.Close(); //關閉播放器
				else if(FromClient.equalsIgnoreCase("MRCode_WMP_01")) wmp.Random(); //隨機播放
				else if(FromClient.equalsIgnoreCase("MRCode_WMP_02")) wmp.Repick(); //重複播放
				else if(FromClient.equalsIgnoreCase("MRCode_WMP_10")) wmp.Pause(); //繼續或暫停
				else if(FromClient.equalsIgnoreCase("MRCode_WMP_11")) wmp.fullScreen(); //全畫面-取消全畫面
				else if(FromClient.equalsIgnoreCase("MRCode_WMP_12")) wmp.PageUP(); //上一首
				else if(FromClient.equalsIgnoreCase("MRCode_WMP_13")) wmp.PageDown(); //下一首
				else if(FromClient.equalsIgnoreCase("MRCode_WMP_14")) wmp.Mute(); //靜音-取消靜音
				else if(FromClient.equalsIgnoreCase("MRCode_WMP_15")) wmp.VolumeIncrease(); //增加音量
				else if(FromClient.equalsIgnoreCase("MRCode_WMP_16")) wmp.VolumeReduce(); //降低音量
				else if(FromClient.equalsIgnoreCase("MRCode_WMP_17")) wmp.FastPlay(); //加速播放
				else if(FromClient.equalsIgnoreCase("MRCode_WMP_18")) wmp.SlowPlay(); //減速播放
				else if(FromClient.equalsIgnoreCase("MRCode_WMP_19")) wmp.StopPlay(); //停止播放

				//PPT控制部分
				else if(FromClient.equalsIgnoreCase("MRCode_PPT_00")) ppt.Close(); //關閉簡報
				else if(FromClient.equalsIgnoreCase("MRCode_PPT_10")) ppt.Slide(); //進入投影片模式
				else if(FromClient.equalsIgnoreCase("MRCode_PPT_11")) ppt.Esc(); //結束簡報 ESC
				else if(FromClient.equalsIgnoreCase("MRCode_PPT_12")) ppt.PageUP(); //上一頁
				else if(FromClient.equalsIgnoreCase("MRCode_PPT_13")) ppt.PageDown(); //下一頁
				else if(FromClient.equalsIgnoreCase("MRCode_PPT_14")) ppt.VolumeIncrease(); //增加音量
				else if(FromClient.equalsIgnoreCase("MRCode_PPT_15")) ppt.VolumeReduce(); //降低音量

				//接收到客戶端廣播欲取得伺服端IP資料
				else if(FromClient.equalsIgnoreCase("MRCode_Return")) out.writeUTF(ss.getInetAddress().getLocalHost().getHostAddress());

				//客戶端要求資料夾內含的檔案資訊傳輸
				else if(FromClient.equalsIgnoreCase("MRCode_Show_Music")) out.writeUTF(f.FolderSelect(1));  //傳回音樂資料夾檔案
				else if(FromClient.equalsIgnoreCase("MRCode_Show_Videos")) out.writeUTF(f.FolderSelect(2));  //傳回影片資料夾檔案
				else if(FromClient.equalsIgnoreCase("MRCode_Show_Documents")) out.writeUTF(f.FolderSelect(3));  //傳回簡報資料夾檔案

				//客戶端要求開啟檔案
				else
				{
					int Run_ok = f.Strat_File(FromClient.trim());
					if(Run_ok==1){
						out.writeUTF("執行檔案成功！");
					}else if(Run_ok==-1){
						out.writeUTF("執行檔案失敗！");
					}else
						out.writeUTF("錯誤的指令！");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();//列印異常資訊
		} finally {//用finally語句塊確保動作執行
			try{
				if(in != null){
					in.close();//關閉輸入串流
				}
				if(out != null){
					out.close();//關閉輸入串流
				}
				if(cs != null){
					cs.close();//關閉Socket連接
				}
			}
			catch(Exception e){
				e.printStackTrace();//列印異常資訊
			}
		}
	}

}

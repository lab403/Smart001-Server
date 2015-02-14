package Server;

import java.net.*;
import java.util.Arrays;
import java.io.*;

public class SimpleServer {
	ServerSocket ss;
	ServerSocket ss2;
	SimpleServer server;
	Thread thread;
	Thread threadBrocast;

	// �غc�禡
	public SimpleServer() { }

	public void setServerPort(int port){
		server = new SimpleServer(); 
		System.out.println("��ťPORT�G"+port);
		server.startServer(port);  //port 8888
	}

	private void startServer(int port) {
		try {
			ss = new ServerSocket(port);
			// �����
			thread = new Thread(new ClientThread(ss));
			thread.start();

			threadBrocast = new Thread(new brocastThread(ss2));
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

//
//�����
class brocastThread implements Runnable {
	private ServerSocket ss2;
	private Socket cs2;

	DatagramSocket ds;
	DatagramPacket receive ;
	private byte[] recvBytes;

	// �غc�禡
	public brocastThread(ServerSocket ss2) throws Exception {
		this.ss2 = ss2;
	}

	public void run() {
		try {
			while(true) {
				receive = new DatagramPacket(new byte[1024], 1024);  
				ds = new DatagramSocket(8888);
				ds.receive(receive);
				recvBytes = Arrays.copyOfRange(
						receive.getData(),
						0,  
						receive.getLength());
				System.out.println("Server receive msg:" + new String(recvBytes)); 
			}
		} catch (Exception e) {
			e.printStackTrace();//�C�L���`��T
		} finally {//��finally�y�y���T�O�ʧ@����
			try{
				if(ds != null){
					ds.close();//������J��y
				}
				if(cs2 != null){
					cs2.close();//����Socket�s��
				}
			}
			catch(Exception e){
				e.printStackTrace();//�C�L���`��T
			}
		}
	}

}



// �����
class ClientThread implements Runnable {
	private ServerSocket ss;
	private Socket cs;

	DataInputStream  in;
	DataOutputStream out;

	// �غc�禡
	public ClientThread(ServerSocket ss) throws Exception {
		this.ss = ss;

	}

	public void run() {
		try {
			while(true) {
				// �����Τ�ݪ��s�u�ШD
				cs = ss.accept();
				// �إߥΤ�ݪ���J��y				
				in  = new DataInputStream (cs.getInputStream());
				// �إߥΤ�ݪ���X��y
				out = new DataOutputStream(cs.getOutputStream());

				// �ܫȤ�ݱ��������				
				String FromClient = in.readUTF();
				System.out.println("�Ȥ�ݰe�Ӫ��T���G "+FromClient);
				ControlWMP wmp = new ControlWMP();
				ControlPPT ppt = new ControlPPT();
				ControlComputer cc = new ControlComputer();
				Folder f = new Folder();

				//�q�������
				if(FromClient.equalsIgnoreCase("TESTTEST123123")) {
					System.out.print("!!!!!!!!!!!!!!!!!!!");
					out.writeUTF("Conected");
				}
				else if(FromClient.equalsIgnoreCase("MRCode_CC_00")) cc.sleep(); //��v
				else if(FromClient.equalsIgnoreCase("MRCode_CC_01")) cc.reset(); //���s�}��
				else if(FromClient.equalsIgnoreCase("MRCode_CC_02")) cc.powerOff(); //����

				//WMP�����
				else if(FromClient.equalsIgnoreCase("MRCode_WMP_00")) wmp.Close(); //��������
				else if(FromClient.equalsIgnoreCase("MRCode_WMP_01")) wmp.Random(); //�H������
				else if(FromClient.equalsIgnoreCase("MRCode_WMP_02")) wmp.Repick(); //���Ƽ���
				else if(FromClient.equalsIgnoreCase("MRCode_WMP_10")) wmp.Pause(); //�~��μȰ�
				else if(FromClient.equalsIgnoreCase("MRCode_WMP_11")) wmp.fullScreen(); //���e��-�������e��
				else if(FromClient.equalsIgnoreCase("MRCode_WMP_12")) wmp.PageUP(); //�W�@��
				else if(FromClient.equalsIgnoreCase("MRCode_WMP_13")) wmp.PageDown(); //�U�@��
				else if(FromClient.equalsIgnoreCase("MRCode_WMP_14")) wmp.Mute(); //�R��-�����R��
				else if(FromClient.equalsIgnoreCase("MRCode_WMP_15")) wmp.VolumeIncrease(); //�W�[���q
				else if(FromClient.equalsIgnoreCase("MRCode_WMP_16")) wmp.VolumeReduce(); //���C���q
				else if(FromClient.equalsIgnoreCase("MRCode_WMP_17")) wmp.FastPlay(); //�[�t����
				else if(FromClient.equalsIgnoreCase("MRCode_WMP_18")) wmp.SlowPlay(); //��t����
				else if(FromClient.equalsIgnoreCase("MRCode_WMP_19")) wmp.StopPlay(); //�����

				//PPT�����
				else if(FromClient.equalsIgnoreCase("MRCode_PPT_00")) ppt.Close(); //����²��
				else if(FromClient.equalsIgnoreCase("MRCode_PPT_10")) ppt.Slide(); //�i�J��v���Ҧ�
				else if(FromClient.equalsIgnoreCase("MRCode_PPT_11")) ppt.Esc(); //����²�� ESC
				else if(FromClient.equalsIgnoreCase("MRCode_PPT_12")) ppt.PageUP(); //�W�@��
				else if(FromClient.equalsIgnoreCase("MRCode_PPT_13")) ppt.PageDown(); //�U�@��
				else if(FromClient.equalsIgnoreCase("MRCode_PPT_14")) ppt.VolumeIncrease(); //�W�[���q
				else if(FromClient.equalsIgnoreCase("MRCode_PPT_15")) ppt.VolumeReduce(); //���C���q

				//������Ȥ�ݼs�������o���A��IP���
				else if(FromClient.equalsIgnoreCase("MRCode_Return")) out.writeUTF(ss.getInetAddress().getLocalHost().getHostAddress());

				//�Ȥ�ݭn�D��Ƨ����t���ɮ׸�T�ǿ�
				else if(FromClient.equalsIgnoreCase("MRCode_Show_Music")) out.writeUTF(f.FolderSelect(1));  //�Ǧ^���ָ�Ƨ��ɮ�
				else if(FromClient.equalsIgnoreCase("MRCode_Show_Videos")) out.writeUTF(f.FolderSelect(2));  //�Ǧ^�v����Ƨ��ɮ�
				else if(FromClient.equalsIgnoreCase("MRCode_Show_Documents")) out.writeUTF(f.FolderSelect(3));  //�Ǧ^²����Ƨ��ɮ�

				//�Ȥ�ݭn�D�}���ɮ�
				else
				{
					int Run_ok = f.Strat_File(FromClient.trim());
					if(Run_ok==1){
						out.writeUTF("�����ɮצ��\�I");
					}else if(Run_ok==-1){
						out.writeUTF("�����ɮץ��ѡI");
					}else
						out.writeUTF("���~�����O�I");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();//�C�L���`��T
		} finally {//��finally�y�y���T�O�ʧ@����
			try{
				if(in != null){
					in.close();//������J��y
				}
				if(out != null){
					out.close();//������J��y
				}
				if(cs != null){
					cs.close();//����Socket�s��
				}
			}
			catch(Exception e){
				e.printStackTrace();//�C�L���`��T
			}
		}
	}

}

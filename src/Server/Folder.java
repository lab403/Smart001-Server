package Server;

import java.io.File;

public class Folder { //�Ω���o��Ƨ������ɮ�
	private File root; //File���A����root(��)
	GUI_Main GUI = new GUI_Main();;
	public String Video_File;
	public String Music_File;
	public String Point_File;
	public Folder(){ } //�غc�l
	
	/*------------------------------------------------------------
	 * ����k�Ω�^�ǫ��w��Ƨ����U�Ҧ��D��Ƨ��Φ����ɮ�
	 * sel 
	 * 1 : ���ָ�Ƨ�
	 * 2 : �v����Ƨ�
	 * 3 : ²����Ƨ�
	 * �^�Ǥ@�Ӧr��Φ�����ơA�C���ɮץH//s�i�����
	 *------------------------------------------------------------*/
	public String FolderSelect(int sel) {
		String File = null;
		switch(sel){ //���o��J
			case 1: //���w���ָ�Ƨ�
				root = new File(GUI.getMusicPath());
				File = printFolder(root,1);
				//File = Music_File;
				break;
			case 2: //���w�v����Ƨ�
				root = new File(GUI.getVideoPath());
				File = printFolder(root,2); 
				//File = Video_File;
				break;
			case 3: //���w²����Ƨ�
				root = new File(GUI.getPointPath());
				File = printFolder(root,3); 
				//File = Point_File;
				break;
		}
		if(File==null)
			return "NoFile";
		else
			return File;
	}
	/*------------------------------------------------------------
	 * ����k�Ω�H�S�w�{��������w��Ƨ����U�����w�ɮ�
	 * �����r�ꫬ�A��Run_Files 
	 * �H//s�i�����
	 * �Ĥ@�Ӥ��Φs��o���ɮ׭n�έ��ؤ覡����
	 * MRCode_Run_Music : ���歵����
	 * MRCode_Run_Videos : ����v����
	 * MRCode_Run_Documents : ����²����
	 * �ĤG�ӥH�᪺�h���n���檺�ɮצW�١C
	 * �^�Ǥ@�Ӿ�ƧΦ�����ơA0�N����~�����O 1�N����榨�\ -1�N����楢��
	 *------------------------------------------------------------*/
	public int Strat_File(String Run_Files){
		String winPath =  System.getenv("WINDIR");
		String ppt_exe[] = {
				"\\Program Files\\Microsoft Office\\OFFICE11\\POWERPNT.EXE ",
				"\\Program Files\\Microsoft Office\\OFFICE12\\POWERPNT.EXE ",
				"\\Program Files\\Microsoft Office\\OFFICE14\\POWERPNT.EXE ",
				"\\Program Files (x86)\\Microsoft Office\\OFFICE11\\POWERPNT.EXE ",
				"\\Program Files (x86)\\Microsoft Office\\OFFICE12\\POWERPNT.EXE ",
				"\\Program Files (x86)\\Microsoft Office\\OFFICE14\\POWERPNT.EXE ",
		};
		File myPnt;
		String Full_File;
		String Full_File_WMP="\\Program Files\\Windows Media Player\\wmplayer.exe ";
		String Full_File_WMP2="\\Program Files (x86)\\Windows Media Player\\wmplayer.exe ";
		String Full_File_PPT="\\Program Files\\Microsoft Office\\OFFICE14\\POWERPNT.EXE ";
		String Run_File[] = Run_Files.split("//s");
		myPnt = new File(winPath.substring(0, 2)+Full_File_WMP);
		if(myPnt.exists()){ //���񾹦s�b
			Full_File_WMP = winPath.substring(0, 2)+Full_File_WMP;
		}else{
			myPnt = new File(winPath.substring(0, 2)+Full_File_WMP2);
			if(myPnt.exists()){ //���񾹦s�b
				Full_File_WMP = winPath.substring(0, 2)+Full_File_WMP2;
			}else{
				Full_File_WMP = "";
			}
		}
		boolean Have_PPT = false;
		for(int num=0; num<ppt_exe.length; num++ ){
			myPnt = new File(winPath.substring(0, 2)+ppt_exe[num]);
			if(myPnt.exists()){ //PPT�s�b
				Have_PPT = true;
				Full_File_PPT = winPath.substring(0, 2)+ppt_exe[num];
				break;
			}
		}
		if(!Have_PPT){ //PPT���s�b
			Full_File_PPT = "";
		}
		if(Run_File[0].equalsIgnoreCase("MRCode_Run_Music")){ //���歵�ָ�Ƨ��ɮ�
			if(Full_File_WMP.isEmpty()) return 0;
			for(int i=1; i < Run_File.length; i++)
				Full_File_WMP += "\""+GUI.getMusicPath()+Run_File[i]+"\" ";
				//�[�W""�O���F�קK�ťթΨ�L�]���ɭP�}���ɮץ���
			Full_File = Full_File_WMP;
		}else if(Run_File[0].equalsIgnoreCase("MRCode_Run_Videos")){//����v����Ƨ��ɮ�
			if(Full_File_WMP.isEmpty()) return 0;
			Full_File_WMP += "\""+GUI.getVideoPath()+Run_File[1]+"\"";
			Full_File = Full_File_WMP;
		}else if(Run_File[0].equalsIgnoreCase("MRCode_Run_Documents")){ //����PPT��Ƨ��ɮ�
			if(Full_File_PPT.isEmpty()) return 0;
			Full_File_PPT += "\""+GUI.getPointPath()+Run_File[1]+"\"";
			Full_File = Full_File_PPT;
			//Program.findProgram(".ppt").execute(GUI.getPointPath()+Run_File[1]);
			//return 1;
		}else{ //���~�����O
			return 0;
		}
		
		try{
			System.out.println("���檺���O�G"+Full_File);
			Runtime.getRuntime().exec(Full_File);  //�Ω����DOS���O
		}catch(Exception e){
			return -1;
		}
		return 1;
	}
	/*------------------------------------------------------------
	 * Ū�����w��Ƨ����U���]�t��Ƨ����Ҧ��ɮ�
	 * �èϥ�//s�Ÿ����X��쪺�ɮ�
	 * �^�Ǥ@�Ӧr�ꪺ��ƫ��A
	 *------------------------------------------------------------*/
	private String printFolder(File root , int sel) {
		String fileExtension[] = null;
		
		 //���ְ��ɦW�L�o(.mp3 .wma .wav)
		String fileExtension1[] = {"mp3", "wma","wav"};
		
		//�v�����ɦW�L�o(.avi .wmv .mpg .mp4)
		String fileExtension2[] = {"avi", "wmv","mpg","mp4"};
		
		//²�����ɦW�L�o(.ppt .pptx .pptm .ppsx .pps .ppsm)
		String fileExtension3[] = {"ppt", "pptx","pptm","ppsx","pps","ppsm"};
		
		switch(sel){
			case 1: fileExtension = fileExtension1.clone(); break;
			case 2: fileExtension = fileExtension2.clone(); break;
			case 3: fileExtension = fileExtension3.clone(); break;
		}
		
		//�Nroot.listFiles()��Ӷ]�@�M�A�ñN�C�@������Ū��controlFolder
		//�D�n�Ω������w�ؿ����U�Ҧ�����Ƨ��B�ɮ�
		String File = null;
		for (File controlFolder : root.listFiles(new ExtensionFileFilter(fileExtension)) ){ 
			// ���X���ɦW��k
			if(controlFolder.isFile()){ //�p�GcontrolFolder�O�@���ɮ�
				//��X�G���o���ɮצW��
				if(File==null)
					File = controlFolder.getName() + "//s";
				else
					File += controlFolder.getName() + "//s"; // //s�O���X�Ÿ��A���Զǰe��Ȥ�ݥi�H�i����
			}
		}
		return File;
	}
}

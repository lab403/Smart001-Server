package Server;

public class ControlComputer {
	
	public ControlComputer(){ //�غc�l
	} 
	/*------------------------------------------------------------
	 * ������L��ALT+DOWN
	 *------------------------------------------------------------*/
	public void sleep(){ // ��v
		try{
			Runtime.getRuntime().exec("rundll32.exe powrprof.dll SetSuspendState");  //�Ω����DOS���O
		}catch(Exception e){}
	}
	
	/*------------------------------------------------------------
	 * ������L��ALT+UP
	 *------------------------------------------------------------*/
	public void reset(){ // ���s�}��
		try{
			Runtime.getRuntime().exec("shutdown.exe -f -r -t 0");  //�Ω����DOS���O
		}catch(Exception e){}
	}
	
	/*------------------------------------------------------------
	 * ������L��ALT+F4
	 *------------------------------------------------------------*/
	public void powerOff(){ // ����
		try{
			Runtime.getRuntime().exec("shutdown.exe -f -s -t 0");  //�Ω����DOS���O
		}catch(Exception e){}
	}

}

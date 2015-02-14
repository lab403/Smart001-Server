package Server;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class ControlWMP {
	public static boolean isSlow,isFast;
	
	public ControlWMP(){ //�غc�l
	} 
	
	/*------------------------------------------------------------
	 * ������L��Ctrl+T
	 *------------------------------------------------------------*/
	public void Repick(){ // ���Ƽ���
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_T);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_T);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	/*------------------------------------------------------------
	 * ������L��Ctrl+H
	 *------------------------------------------------------------*/
	public void Random(){ // �H������
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_H);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_H);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	/*------------------------------------------------------------
	 * ������L��Ctrl+S
	 *------------------------------------------------------------*/
	public void StopPlay(){ // �����
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_S);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_S);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	/*------------------------------------------------------------
	 * ������L��Ctrl+Shift+S
	 *------------------------------------------------------------*/
	public void SlowPlay(){ // �C�t����
		try {
			if(isFast){ //�ثe�O�ֳt����
				isFast = false;
				NormalPlay();
			}else{
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_S);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				robot.keyRelease(KeyEvent.VK_S);
				isSlow = true;
			}
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	/*------------------------------------------------------------
	 * ������L��Ctrl+Shift+G
	 *------------------------------------------------------------*/
	public void FastPlay(){ // �ֳt����
		try {
			if(isSlow){ //�ثe�O�C�t����
				isSlow = false;
				NormalPlay();
			}else{
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_G);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				robot.keyRelease(KeyEvent.VK_G);
				isFast = true;
			}
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	/*------------------------------------------------------------
	 * ������L��Ctrl+Shift+N
	 *------------------------------------------------------------*/
	public void NormalPlay(){ // ���`����t��
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_N);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			robot.keyRelease(KeyEvent.VK_N);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	/*------------------------------------------------------------
	 * ������L��ALT+F4
	 *------------------------------------------------------------*/
	public void Close(){ //
		try {
			Runtime.getRuntime().exec("taskkill.exe /F /IM wmplayer.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*------------------------------------------------------------
	 * ������L��F7
	 *------------------------------------------------------------*/
	public void Mute(){ // �R��/�����R��
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_F7);
			robot.keyRelease(KeyEvent.VK_F7);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	/*------------------------------------------------------------
	 * ������L��F9
	 *------------------------------------------------------------*/
	public void VolumeIncrease(){ // �W�[���q
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_F9);
			robot.keyRelease(KeyEvent.VK_F9);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	/*------------------------------------------------------------
	 * ������L��F8
	 *------------------------------------------------------------*/
	public void VolumeReduce(){ // ���C���q
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_F8);
			robot.keyRelease(KeyEvent.VK_F8);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	/*------------------------------------------------------------
	 * ������L��CTRL+P
	 *------------------------------------------------------------*/
	public void Pause(){ // �~��μȰ�
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_P);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_P);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	/*------------------------------------------------------------
	 * ������L��ALT+ENTER
	 *------------------------------------------------------------*/
	public void fullScreen(){ //���e��-�������e��
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	

	/*------------------------------------------------------------
	 * ������L��CTRL+B
	 *------------------------------------------------------------*/
	public void PageUP(){ // �W�@��
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_B);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_B);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	/*------------------------------------------------------------
	 * ������L��CTRL+F
	 *------------------------------------------------------------*/
	public void PageDown(){ // �U�@��
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_F);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_F);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
}

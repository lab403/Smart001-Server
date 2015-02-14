package Server;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class ControlPPT {
	
	public ControlPPT(){ //�غc�l
	} 
	/*------------------------------------------------------------
	 * ������L��ALT+DOWN
	 *------------------------------------------------------------*/
	public void VolumeReduce(){ // ���C���q
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.keyRelease(KeyEvent.VK_DOWN);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	/*------------------------------------------------------------
	 * ������L��ALT+UP
	 *------------------------------------------------------------*/
	public void VolumeIncrease(){ // �W�[���q
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_UP);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.keyRelease(KeyEvent.VK_UP);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	/*------------------------------------------------------------
	 * ������L��ALT+F4
	 *------------------------------------------------------------*/
	public void Close(){ // ����²��
		try {
			Runtime.getRuntime().exec("taskkill.exe /F /IM POWERPNT.EXE");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*------------------------------------------------------------
	 * ������L��F5
	 *------------------------------------------------------------*/
	public void Slide(){ // �i�J��v���Ҧ�
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_F5);
			robot.keyRelease(KeyEvent.VK_F5);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	/*------------------------------------------------------------
	 * ������L��ESC
	 *------------------------------------------------------------*/
	public void Esc(){ // ����²�� ESC
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ESCAPE);
			robot.keyRelease(KeyEvent.VK_ESCAPE);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	/*------------------------------------------------------------
	 * ������L��PageUP
	 *------------------------------------------------------------*/
	public void PageUP(){ // �W�@��
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_PAGE_UP);
			robot.keyRelease(KeyEvent.VK_PAGE_UP);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	/*------------------------------------------------------------
	 * ������L��PageDown
	 *------------------------------------------------------------*/
	public void PageDown(){ // �U�@��
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_PAGE_DOWN);
			robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
}

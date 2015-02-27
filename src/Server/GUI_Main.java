package Server;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;

public class GUI_Main extends JFrame {
    private JButton Btn_Music_Source;
    private JButton Btn_NO;
    private JButton Btn_OK;
    private JButton Btn_Point_Source;
    private JButton Btn_Video_Source;
    private JTextField Music_Source;
    private JTextField Point_Source;
    private JTextField Video_Source;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
	private static String Music_File, Point_File, Video_File; //�Ω�s���Ƨ������|
	private static final int PORT = 3579; //���A���ϥΪ�port
	public static ArrayList<String> allIP = new ArrayList<String>();
	
	
	public static void main(String args[]) {
    	GUI_Main g = new GUI_Main();
    	g.initComponents();
    	g.setVisible(true);
    }
    
    public GUI_Main() { } //�غc�l

     private void initComponents() { //����
        Btn_Music_Source = new JButton();
        Btn_Video_Source = new JButton();
        Btn_Point_Source = new JButton();
        Music_Source = new JTextField();
        Video_Source = new JTextField();
        Point_Source = new JTextField();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        Btn_OK = new JButton();
        Btn_NO = new JButton();
    	
    	this.setTitle("�h�C�黻����");
    	
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Btn_Music_Source.setFont(new java.awt.Font("�з���", 0, 24)); // NOI18N
        Btn_Music_Source.setIcon(new javax.swing.ImageIcon(getClass().getResource("folder_empty.png"))); // NOI18N
        Btn_Music_Source.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                Btn_Music_SourceMouseClicked(evt);
            }
        });
        /*Btn_Music_Source.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Btn_Music_SourceActionPerformed(evt);
            }
        });*/

        Btn_Video_Source.setFont(new java.awt.Font("�з���", 0, 24)); // NOI18N
        Btn_Video_Source.setIcon(new javax.swing.ImageIcon(getClass().getResource("folder_empty.png"))); // NOI18N
        Btn_Video_Source.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                Btn_Video_SourceMouseClicked(evt);
            }
        });

        Btn_Point_Source.setFont(new java.awt.Font("�з���", 0, 24)); // NOI18N
        Btn_Point_Source.setIcon(new javax.swing.ImageIcon(getClass().getResource("folder_empty.png"))); // NOI18N
        Btn_Point_Source.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                Btn_Point_SourceMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("�з���", 0, 24)); // NOI18N
        jLabel1.setText("���ָ�Ƨ�");

        jLabel2.setFont(new java.awt.Font("�з���", 0, 24)); // NOI18N
        jLabel2.setText("�v����Ƨ�");

        jLabel3.setFont(new java.awt.Font("�з���", 0, 24)); // NOI18N
        jLabel3.setText("²����Ƨ�");

        String Music = "C:\\Users\\SirMarine\\Music\\\r\n";
        String Video = "C:\\Users\\SirMarine\\Videos\\\r\n";
        String PowerPoing = "C:\\Users\\SirMarine\\Documents\\\r\n";

        try {
        	FileReader AllFile = new FileReader("./config.cfg");
            char AllFileName[] = new char[256];
        	int num = AllFile.read(AllFileName);
        	String AllFileName_temp = new String(AllFileName,0,num);
        	String AllFileName_temp2[] = AllFileName_temp.split("\r\n");
        	Music = AllFileName_temp2[0];
        	Video = AllFileName_temp2[1];
        	PowerPoing = AllFileName_temp2[2];
        	System.out.println(Music);
        	System.out.println(Video);
        	System.out.println(PowerPoing);
		} catch (FileNotFoundException e1) { //�䤣���ɮ�
			try {
				FileWriter AllFileW = new FileWriter("./config.cfg");
				AllFileW.write("C:\\Users\\SirMarine\\Music\\\r\n");
				AllFileW.write("C:\\Users\\SirMarine\\Videos\\\r\n");
				AllFileW.write("C:\\Users\\SirMarine\\Documents\\\r\n");
				AllFileW.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        if(Music_Source.getText().isEmpty()){
        	Music_Source.setFont(new java.awt.Font("�з���", 0, 18)); // NOI18N
        	Music_Source.setText(Music);
        	Music_File = Music_Source.getText();
        }
        Music_Source.setEditable(false); //�T��ϥΪ̪����ק�
        
        if(Video_Source.getText().isEmpty()){
        	Video_Source.setFont(new java.awt.Font("�з���", 0, 18)); // NOI18N
            Video_Source.setText(Video);
        	Video_File = Video_Source.getText();
        }
        Video_Source.setEditable(false); //�T��ϥΪ̪����ק�
        
        if(Point_Source.getText().isEmpty()){
        	Point_Source.setFont(new java.awt.Font("�з���", 0, 18)); // NOI18N
        	Point_Source.setText(PowerPoing);
        	Point_File = Point_Source.getText();
        }
        Point_Source.setEditable(false); //�T��ϥΪ̪����ק�
        
        Btn_OK.setFont(new java.awt.Font("�з���", 0, 24)); // NOI18N
        Btn_OK.setText("�T�w�ñҰʦ��A��");
        Btn_OK.setActionCommand("BT_Start");
        Btn_OK.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                Btn_OKMouseClicked(evt);
            }
        });
        
        Btn_NO.setFont(new java.awt.Font("�з���", 0, 24)); // NOI18N
        Btn_NO.setText("���}���������A��");
        Btn_NO.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                Btn_NOMouseClicked(evt);
            }
        });
        
        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("�з���", 0, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("�Х���ܬ����ɮשҹ�������Ƨ��A�H�K�t�ί�T����z�ݭn���ɮסC");
        jLabel4.setOpaque(true);

        jLabel5.setFont(new java.awt.Font("�з���", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 204));
        
        try{
        	InetAddress myComputer = InetAddress.getLocalHost() ;
//            jLabel5.setText("����IP��}�G"+myComputer.getHostAddress());
        	String IP ="";
//          jLabel5.setText("����IP��}�G"+myComputer.getHostAddress());
          Enumeration e = NetworkInterface.getNetworkInterfaces();
          while(e.hasMoreElements())
          {
              NetworkInterface n = (NetworkInterface) e.nextElement();
              Enumeration ee = n.getInetAddresses();
              while (ee.hasMoreElements())
              {
                  InetAddress i = (InetAddress) ee.nextElement();
                  if(i.getHostAddress().indexOf(":")==-1 && !i.getHostAddress().equals("127.0.0.1")){
//                    System.out.println(i.getHostAddress())
                	  if(n.getDisplayName().toLowerCase().indexOf("virtualbox")==-1 && n.getDisplayName().toLowerCase().indexOf("microsoft")==-1 && n.getDisplayName().toLowerCase().indexOf("hyper-v")==-1&& n.getDisplayName().toLowerCase().indexOf("vmware")==-1){
                		IP+=i.getHostAddress()+"�B";
                		allIP.add(i.getHostAddress());
                		System.out.println(n.getDisplayName());  
                	  }
                  }
              }
          }
          jLabel5.setText("����IP��}�G"+IP.substring(0, IP.length()-1));
        }catch(Exception e){}



        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(60, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(Video_Source, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Music_Source, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Point_Source, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Btn_Video_Source, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Btn_Point_Source, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Btn_Music_Source, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Btn_OK)
                        .addGap(18, 18, 18)
                        .addComponent(Btn_NO))
                    .addComponent(jLabel5))
                .addGap(60, 60, 60))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Music_Source, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_Music_Source, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Video_Source, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_Video_Source, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Point_Source, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_Point_Source, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn_OK)
                    .addComponent(Btn_NO))
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void Btn_Music_SourceMouseClicked(MouseEvent evt) {//GEN-FIRST:event_Btn_Music_SourceMouseClicked
        JFileChooser chooser =new JFileChooser();
        String path;
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogTitle("�п�ܸ��|");  
        if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(null)) {
            path = chooser.getSelectedFile().getAbsolutePath(); 
            Music_Source.setText(path.replace("/", "\\")+"\\");
        	Music_File = Music_Source.getText();
        	UpdataAllFileName();
        	System.out.println(Music_File);
        }  
    }//GEN-LAST:event_Btn_Music_SourceMouseClicked

    /*private void Btn_Music_SourceActionPerformed(ActionEvent evt) {//GEN-FIRST:event_Btn_Music_SourceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Btn_Music_SourceActionPerformed*/

    private void Btn_Video_SourceMouseClicked(MouseEvent evt) {//GEN-FIRST:event_Btn_Video_SourceMouseClicked
       JFileChooser chooser =new JFileChooser();
        String path;
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogTitle("�п�ܸ��|");  
        if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(null)) {
            path = chooser.getSelectedFile().getAbsolutePath(); 
            Video_Source.setText(path.replace("/", "\\")+"\\");
        	Video_File = Video_Source.getText();
        	UpdataAllFileName();
        	System.out.println(Video_File);
        }
    }//GEN-LAST:event_Btn_Video_SourceMouseClicked

    private void Btn_Point_SourceMouseClicked(MouseEvent evt) {//GEN-FIRST:event_Btn_Point_SourceMouseClicked
        JFileChooser chooser =new JFileChooser();
        String path;
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogTitle("�п�ܸ��|");  
        if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(null)) {
            path = chooser.getSelectedFile().getAbsolutePath(); 
            Point_Source.setText(path.replace("/", "\\")+"\\");
        	Point_File = Point_Source.getText();
        	UpdataAllFileName();
        	System.out.println(Point_File);
        }
    }//GEN-LAST:event_Btn_Point_SourceMouseClicked

    private void Btn_NOMouseClicked(MouseEvent evt) {//GEN-FIRST:event_Btn_NOMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_Btn_NOMouseClicked

	private void Btn_OKMouseClicked(MouseEvent evt) {//GEN-FIRST:event_Btn_OKMouseClicked
        // TODO add your handling code here:
		if(Btn_OK.isEnabled()){
			SimpleServer Start = new SimpleServer();
			Start.setServerPort(PORT);
			Btn_OK.setEnabled(false);
		}
    }//GEN-LAST:event_Btn_OKMouseClicked

    public String getMusicPath(){ //�Ǧ^���ָ�Ƨ����|
    	System.out.println(Music_File);
    	return Music_File;
    }
    public String getPointPath(){
    	System.out.println(Point_File);
    	return Point_File;
    }
    public String getVideoPath(){
    	System.out.println(Video_File);
    	return Video_File;
    }
    
    public void UpdataAllFileName(){
		try {
			FileWriter AllFileW = new FileWriter("./config.cfg");
			AllFileW.write(Music_Source.getText()+"\r\n");
			AllFileW.write(Video_Source.getText()+"\r\n");
			AllFileW.write(Point_Source.getText()+"\r\n");
			AllFileW.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}

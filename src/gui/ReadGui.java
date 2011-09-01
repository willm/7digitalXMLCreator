package gui;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;

import excel.ExcelReader;


public class ReadGui extends javax.swing.JFrame {


    private javax.swing.JFileChooser jFileChooser1;
    private FileListener filelis;
    private ExcelReader excelReader = new ExcelReader();


    public ReadGui() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Import From Excel - 7digital");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
                
        jFileChooser1.addChoosableFileFilter(new ExcelFilter());
        
        filelis = new FileListener();
        jFileChooser1.addActionListener(filelis);

        pack();
    }// </editor-fold>


	public String getExtension(File file) {
		String theName = file.getName();
		String ext = theName.substring(theName.lastIndexOf(".")+1, theName.length());
		return ext;
	}

	private class FileListener implements ActionListener {
	
	    public void actionPerformed(ActionEvent e) {
	
	        if (JFileChooser.APPROVE_SELECTION.equals(e.getActionCommand())){    
	            File file = jFileChooser1.getSelectedFile();
	            try{
	            	excelReader.exceltoxml(file.getAbsolutePath());
	            	JOptionPane.showMessageDialog(null, "Success");
	            }
	            catch(Exception ex){
	            	JOptionPane.showMessageDialog(null, "Fail : " + ex.getMessage());
	            }
	            
	            //This line creates all the xmls and returns the result as a message box
	            excelReader.reset();
	        }
	        else if (JFileChooser.CANCEL_SELECTION.equals(e.getActionCommand())) {
	            System.out.println("Open command cancelled by user.");
	            ReadGui.this.setVisible(false);
	        }
	    }
	}
    

    public class ExcelFilter extends javax.swing.filechooser.FileFilter {
    
        //Accept all directories and all xls or xlsx files.
        public boolean accept(File f) {
            if (f.isDirectory()) {
                return true;
            }
    
            String extension = getExtension(f);
            if (extension != null) {
                if (extension.equals("xls") || extension.equals("xlsx")) {
                        return true;
                } else {
                    return false;
                }
            }
    
            return false;
        }

        public String getDescription() {
            return "Just Excel Files";
        }
    }
}

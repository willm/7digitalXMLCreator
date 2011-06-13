import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
/**
 *
 * @author William Munn
 */
public class TrackGui extends javax.swing.JFrame {


    // Variables declaration - do not modify
    private javax.swing.JButton AddPtcptBut;
    private javax.swing.JButton EdtPrtcpntBut;
    private javax.swing.JButton addTrkBut;
    private javax.swing.JCheckBox explctBox;
    private javax.swing.JCheckBox hiddenBox;
    private javax.swing.JTextField isrcText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton okBut;
    private javax.swing.JList prtcpntLst;
    private javax.swing.JButton remPrtcpntBut;
    private javax.swing.JTextField trkGnrText;
    private javax.swing.JTextField trkIdText;
    private javax.swing.JTextField trkArtText;
    private javax.swing.JTextField trkLblText;
    private javax.swing.JTextField trkPubLnText;
    private javax.swing.JTextField trkLthText;
    private javax.swing.JTextField trkNrText;
    private javax.swing.JTextField trkTitText;
    private javax.swing.JComboBox trkTypBox;
    private javax.swing.JTextField trkVTitText;
    private javax.swing.JTextField volText;
    private Xml theXml;
    private ProductGui2 product;
    
    private ButHandler butlis;
    private OkHandler oklis;
    private RemHandler remlis;
    private AddPartHandler partlis;
    private EdtPartHandler edtparlis;
    private boolean isEdit = false;
    
    private PartGui part = null;
    private String[] partlist = { };
    private DefaultListModel sampleModel;
    private int pos;
    // End of variables declaration

    /** Creates new form TrackGui */
    public TrackGui() {
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

        jPanel1 = new javax.swing.JPanel();
        isrcText = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        hiddenBox = new javax.swing.JCheckBox();
        trkIdText = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        trkArtText = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        explctBox = new javax.swing.JCheckBox();
        volText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        trkNrText = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        trkTypBox = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        trkTitText = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        trkVTitText = new javax.swing.JTextField();
        trkLthText = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        trkLblText = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        trkPubLnText = new javax.swing.JTextField();
        trkGnrText = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        okBut = new javax.swing.JButton();
        addTrkBut = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        prtcpntLst = new javax.swing.JList();
        jLabel13 = new javax.swing.JLabel();
        AddPtcptBut = new javax.swing.JButton();
        EdtPrtcpntBut = new javax.swing.JButton();
        remPrtcpntBut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add Track - 7digital");

        jLabel1.setText("ISRC (no hypens or spaces)");

        hiddenBox.setText("Is Hidden (Bundle Only)");

        jLabel2.setText("Track Identifier");

        jLabel3.setText("Track Artist");

        explctBox.setText("Is Explicit");

        volText.setText("1");
        
        trkNrText.setEditable(false);

        jLabel4.setText("Track Volume");

        jLabel5.setText("Track number");

        jLabel6.setText("Track Type");

        trkTypBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Audio", "Video" }));

        jLabel7.setText("Track Title");

        jLabel8.setText("Track Version Title");

        trkLthText.setText("HH:MM:SS");

        jLabel9.setText("Track Length");

        jLabel10.setText("Track Label");

        jLabel11.setText("Track Publisher Line");

        jLabel12.setText("Track Genre");

        okBut.setText("OK");
        okBut.setEnabled(false);

        addTrkBut.setText("Add / Update Track");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(addTrkBut)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(okBut))
                    .addComponent(trkGnrText, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .addComponent(trkPubLnText, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .addComponent(trkLblText, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .addComponent(trkLthText, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .addComponent(trkVTitText, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .addComponent(trkTitText, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .addComponent(trkNrText, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(explctBox)
                    .addComponent(trkArtText, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .addComponent(isrcText, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hiddenBox)
                    .addComponent(trkIdText, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .addComponent(volText, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(trkTypBox, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(isrcText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(hiddenBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(trkIdText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(trkArtText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(explctBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(volText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(trkNrText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(trkTypBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(trkTitText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(trkVTitText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(trkLthText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(trkLblText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(trkPubLnText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(trkGnrText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addTrkBut)
                    .addComponent(okBut))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        sampleModel = new DefaultListModel();
        for(int i = 0; i<partlist.length; i++){
            sampleModel.addElement(partlist[i]);
        }
        prtcpntLst.setModel(sampleModel);
        prtcpntLst.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(prtcpntLst);

        jLabel13.setText("Track Participants");

        AddPtcptBut.setText("Add Participant");
        AddPtcptBut.setEnabled(false);

        EdtPrtcpntBut.setText("Edit Participant");
        EdtPrtcpntBut.setEnabled(false);

        remPrtcpntBut.setText("Remove Participant");
        remPrtcpntBut.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(remPrtcpntBut, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(AddPtcptBut, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(EdtPrtcpntBut)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddPtcptBut, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EdtPrtcpntBut))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(remPrtcpntBut)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        butlis=new ButHandler();
        addTrkBut.addActionListener(butlis);
        
        oklis=new OkHandler();
        okBut.addActionListener(oklis);
        
        partlis= new AddPartHandler();
        AddPtcptBut.addActionListener(partlis);
        
        remlis = new RemHandler();
        remPrtcpntBut.addActionListener(remlis);
        
        edtparlis = new EdtPartHandler();
        EdtPrtcpntBut.addActionListener(edtparlis);
               
        pack();
    }// </editor-fold>

    /**
    * @param args the command line arguments
    */
    public static void doit() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TrackGui().setVisible(true);
            }
        });
    }
    
    public void addXml(Xml anXml){
        //this method MUST be called when a new track gui is created. It attaches the Xml classs to it
        theXml = anXml;
    }
    
    public void addPro(ProductGui2 pro){
        //this method MUST be called when a new track gui is created. It attaches the ProductGui2 classs to it
        product = pro;
    }
    
    public void updatePList(String thePrtcpt){
        //this adds items to the tracklist
            int index = sampleModel.getSize();
            sampleModel.addElement(thePrtcpt);
            getContentPane().invalidate();
            getContentPane().validate();
            prtcpntLst.setSelectedIndex(index);
            prtcpntLst.ensureIndexIsVisible(index);
            if(EdtPrtcpntBut.isEnabled()==false){
                EdtPrtcpntBut.setEnabled(true);                
            }
            if(remPrtcpntBut.isEnabled()==false){
                remPrtcpntBut.setEnabled(true);
            }
        }
        
    public void updateParTInList(int pos, String newTitle){
        int index = sampleModel.getSize();
        sampleModel.set(pos, newTitle);
        getContentPane().invalidate();
        getContentPane().validate();
        prtcpntLst.setSelectedIndex(index);
        prtcpntLst.ensureIndexIsVisible(index);
    }
    
    public void remPfrmList(){      
        int index = sampleModel.getSize();
        sampleModel.remove(prtcpntLst.getSelectedIndex());
        getContentPane().invalidate();
        getContentPane().validate();
        prtcpntLst.setSelectedIndex(index);
        prtcpntLst.ensureIndexIsVisible(index);
    }
    
    public void setEdit(){
        //this method is called when the edit track button is pressed in a ProductGui2
        isEdit = true;
        isrcText.setText(theXml.getTrackAtrbtValue(pos, "isrc"));
        
        String hidden = theXml.getTrackAtrbtValue(pos, "hidden");
        if(hidden == "true"){
            hiddenBox.setSelected(true);
        }
            
        trkIdText.setText(theXml.getTrackTagValue(pos,"track_identifier"));
        trkArtText.setText(theXml.getTrackArtistValue(pos));
        if(theXml.getTrackTagValue(pos,"explicit_content")=="true"){
            explctBox.setSelected(true);
        }
        volText.setText(theXml.getTrackTagValue(pos,"track_volume"));
        trkTitText.setText(theXml.getTrackTagValue(pos,"track_title"));
        trkVTitText.setText(theXml.getTrackTagValue(pos,"track_version_title"));
        if(theXml.getTrackTagValue(pos,"track_type")=="Audio"){
            trkTypBox.setSelectedItem("Audio");
        }
        else if(theXml.getTrackTagValue(pos,"track_type")=="Video"){
            trkTypBox.setSelectedItem("Video");
        }
        trkLthText.setText(theXml.getTrackTagValue(pos,"track_length"));
        trkLblText.setText(theXml.getTrackTagValue(pos,"track_label"));
        trkPubLnText.setText(theXml.getTrackTagValue(pos,"track_p_line"));
        //need to add other stuff
        trkGnrText.setText(theXml.getTrackGenreValue(pos));
        String[] existingParticipents = theXml.getTrackParticipents(pos);
        for(int i =0; i<existingParticipents.length; i++){
            updatePList(existingParticipents[i]);
        }
        AddPtcptBut.setEnabled(true);
        //okBut.setEnabled(true);
    }        
    
    private TrackGui returnThis(){
        return this;
    }
    
    public void setTrkNo(int theNum){
        trkNrText.setText(Integer.toString(theNum));
        pos = (Integer.parseInt(trkNrText.getText())) -1;
    }
    
    public void printpos(){
        System.out.println(pos);
    }
    
    private class ButHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            
            String hidden =null;
            String rude =null;
            String theType =null;
            
            if(hiddenBox.isSelected() == true){
                System.out.println("trk hidden? -- Yes");
                hidden = "true";
            }
            else if(hiddenBox.isSelected() == false){
                System.out.println("trk hidden? -- NO");
                hidden = "false";
            }
            
            if(explctBox.isSelected() == true){
                System.out.println("trk explicit? -- Yes");
                rude = "true";
            }
            else if(explctBox.isSelected() == false){
                System.out.println("trk explicit? -- NO");
                rude = "false";
            }
            
            if(trkTypBox.getSelectedItem() == "Audio"){
                System.out.println("trk typ audio");
                theType = "Audio";
            }
            else if(trkTypBox.getSelectedItem() == "Video"){
                System.out.println("trk typ video");
                theType = "Video";
            }
            
            //this is called when add track is clicked
            if(isEdit == false){
                if(trkTitText.getText().equals("")){
                       JOptionPane.showMessageDialog(null, "Please enter a track title");;
                    }
                else{
                   theXml.addTrack(isrcText.getText(),
                                   hidden,
                                   trkIdText.getText(),
                                   trkArtText.getText(),
                                   rude,
                                   volText.getText(),
                                   trkNrText.getText(),
                                   theType,
                                   trkTitText.getText(),
                                   trkVTitText.getText(),
                                   trkLthText.getText(),
                                   trkLblText.getText(),
                                   trkPubLnText.getText(),
                                   trkGnrText.getText()
                                   );

                   product.updateTList(trkTitText.getText() + " (" + trkVTitText.getText() + ")");
                   
                   product.setTracks();
                   product.productReady();
                   AddPtcptBut.setEnabled(true);
                   okBut.setEnabled(true);
                   addTrkBut.setEnabled(false);
                        }
                    }
           //this is called when edit track is clicked             
           else if(isEdit ==true){
               //pos = (Integer.parseInt(trkNrText.getText())) -1;
               System.out.println(pos);
               theXml.editTrackTagAttribute(pos,"isrc",isrcText.getText());
               theXml.editTrackTagAttribute(pos,"hidden",hidden);
               theXml.editTrackTagValue(pos,"track_identifier",trkIdText.getText());
               theXml.editTrackArtistValue(pos,trkArtText.getText());
               theXml.editTrackTagValue(pos,"explicit_content",rude);
               theXml.editTrackTagValue(pos,"track_volume",volText.getText());
               theXml.editTrackTagValue(pos,"track_type",theType);
               theXml.editTrackTagValue(pos,"track_title",trkTitText.getText());
               theXml.editTrackTagValue(pos,"track_version_title",trkVTitText.getText());
               theXml.editTrackTagValue(pos,"track_length",trkLthText.getText());
               theXml.editTrackTagValue(pos,"track_label",trkLblText.getText());
               theXml.editTrackTagValue(pos,"track_p_line",trkPubLnText.getText());
               theXml.editTrackTagValue(pos,"genre",trkGnrText.getText());
               
               //product.updateTtitleInList(pos, trkNrText.getText() + ". " + trkTitText.getText());
               product.updateTtitleInList(pos, trkTitText.getText() + " (" + trkVTitText.getText() + ")");
               
               AddPtcptBut.setEnabled(true);
               okBut.setEnabled(true);
            }
                                   
          
           
           
           System.out.println("----------------THE TRACK-------------");
           System.out.println(isrcText.getText() +
                           hidden+
                           trkIdText.getText()+
                           trkArtText.getText()+
                           rude+
                           volText.getText()+
                           trkNrText.getText()+
                           theType+
                           trkTitText.getText()+
                           trkVTitText.getText()+
                           trkLthText.getText()+
                           trkLblText.getText()+
                           trkPubLnText.getText()+
                           trkGnrText.getText()
                           );
           System.out.println("--------------------------------------");
            //setVisible(false);
           //make track frame close once done
           
           
        }
    }
    
    private class AddPartHandler implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            //int pos = (Integer.parseInt(trkNrText.getText())) -1;
            part = new PartGui();
            part.setVisible(true);
            part.connect(theXml,pos,true,returnThis());
        }
    }
    
    private class OkHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            dispose();
        }
    }
    
    private class RemHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.out.println(prtcpntLst.getSelectedIndex());
            theXml.removeTrackParticipent(pos,prtcpntLst.getSelectedIndex());
            remPfrmList();
            //needs to be removed from xml!!!
        }
    }
    
    private class EdtPartHandler implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            //int pos = (Integer.parseInt(trkNrText.getText())) -1;
            part = new PartGui();
            part.setVisible(true);
            part.connect(theXml,pos,true,returnThis());
            part.setEdit(pos,prtcpntLst.getSelectedIndex());
        }
    }
    
}

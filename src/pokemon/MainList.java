
package pokemon;

import java.io.*;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JOptionPane;

public class MainList extends javax.swing.JFrame {

    String folderPath;
    Vector<Pokemon> pkm = new Vector<>();
    Vector<Pokemon> pkmTmp = new Vector<>();
    Vector<Pokemon> iv = new Vector<>();
    Vector<Pokemon> AlternateForm = new Vector<>();
    Vector<EvolutionLine> EvolveList = new Vector<>();
    TableModel<Pokemon> model;
    boolean selected = false;

    public MainList() {
        initComponents();
        folderPath = (new FilePath()).getFilePath() + "Text Files/";
        String[] header = {"STATUS", "NAME", "HP", "ATK", "DEF", "S.ATK", "S.DEF", "SPD"};
        int[] ind = {0, 1, 2, 3, 4, 5, 6, 7};
        model = new TableModel<>(header, ind);
        loadData(folderPath + "List Files/List.txt");
        loadExisting();
        ivHp.setEditable(false);
        ivAtk.setEditable(false);
        ivDef.setEditable(false);
        ivSatk.setEditable(false);
        ivSdef.setEditable(false);
        ivSpd.setEditable(false);
        baseHp.setEditable(false);
        baseAtk.setEditable(false);
        baseDef.setEditable(false);
        baseSatk.setEditable(false);
        baseSdef.setEditable(false);
        baseSpd.setEditable(false);
        btnEvolveFrom.setVisible(false);
        btnEvolveTo.setVisible(false);
        btnForm.setVisible(false);
        this.tblPkm.setModel(model);
        this.tblPkm.getColumnModel().getColumn(0).setPreferredWidth(30);
        this.tblPkm.getColumnModel().getColumn(1).setPreferredWidth(100);
        this.tblPkm.getColumnModel().getColumn(2).setPreferredWidth(15);
        this.tblPkm.getColumnModel().getColumn(3).setPreferredWidth(15);
        this.tblPkm.getColumnModel().getColumn(4).setPreferredWidth(15);
        this.tblPkm.getColumnModel().getColumn(5).setPreferredWidth(15);
        this.tblPkm.getColumnModel().getColumn(6).setPreferredWidth(15);
        this.tblPkm.getColumnModel().getColumn(7).setPreferredWidth(15);
    }
    
    private String loadCompleted(String name) {
        String status = "";
        String filePath = folderPath + "Completed/" + name + "_Stat.txt";
        File file = new File(filePath);
        if (file.exists()) {
            status = "CLEAR";
        }
        return status;
    }

    private void loadExisting() {
        for (int i = 0; i < pkm.size(); i++) {
            String pokemon = pkm.get(i).getName();
            String filePath = folderPath + pokemon + "_Stat.txt";
            File file = new File(filePath);
            if (file.exists() && !checkExisting(pokemon)) {
                crPkm.addItem(pokemon);
            }
        }
    }

    private boolean checkExisting(String pokemon) {
        boolean check = false;
        for (int i = 0; i < crPkm.getItemCount(); i++) {
            if (pokemon.equalsIgnoreCase(crPkm.getItemAt(i))) {
                check = true;
            }
        }
        return check;
    }

    private void loadData(String path) {
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String S;
            while ((S = br.readLine()) != null) {
                S = S.trim();
                if (S.length() > 0) {
                    StringTokenizer st = new StringTokenizer(S, ",");
                    String name = st.nextToken();
                    int hp = Integer.parseInt(st.nextToken());
                    int atk = Integer.parseInt(st.nextToken());
                    int def = Integer.parseInt(st.nextToken());
                    int satk = Integer.parseInt(st.nextToken());
                    int sdef = Integer.parseInt(st.nextToken());
                    int spd = Integer.parseInt(st.nextToken());
                    int ivhp = Integer.parseInt(st.nextToken());
                    int ivatk = Integer.parseInt(st.nextToken());
                    int ivdef = Integer.parseInt(st.nextToken());
                    int ivsatk = Integer.parseInt(st.nextToken());
                    int ivsdef = Integer.parseInt(st.nextToken());
                    int ivspd = Integer.parseInt(st.nextToken());
                    int stage = Integer.parseInt(st.nextToken());
                    String preEvolve = st.nextToken();
                    StringTokenizer sk = new StringTokenizer(name, " ");
                    if (sk.countTokens() > 1 && !name.equalsIgnoreCase("Nidoran Male")
                            && !name.equalsIgnoreCase("Nidoran Female")
                            && !name.equalsIgnoreCase("Mr. Mime")
                            && !name.equalsIgnoreCase("Mime Jr.")) {
                        String subName = sk.nextToken();
                        String subForm = sk.nextToken();
                        AlternateForm.add(new Pokemon(subName, subForm, hp, atk, def, satk, sdef, spd));
                    }
                    String status = loadCompleted(name);
                    if (sk.countTokens() == 1 || name.equalsIgnoreCase("Nidoran Male")
                            || name.equalsIgnoreCase("Nidoran Female")
                            || name.equalsIgnoreCase("Mr. Mime")
                            || name.equalsIgnoreCase("Mime Jr.")) {
                        pkm.add(new Pokemon(name, hp, atk, def, satk, sdef, spd, status));
                        EvolveList.add(new EvolutionLine(name, stage, preEvolve));
                        model.getData().add(new Pokemon(name, hp, atk, def, satk, sdef, spd, status));
                    }
                    iv.add(new Pokemon(name, ivhp, ivatk, ivdef, ivsatk, ivsdef, ivspd, status));
                }
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPkm = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ivName = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        baseHp = new javax.swing.JTextField();
        ivHp = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        baseAtk = new javax.swing.JTextField();
        ivAtk = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        baseDef = new javax.swing.JTextField();
        ivDef = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        baseSatk = new javax.swing.JTextField();
        ivSatk = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        baseSdef = new javax.swing.JTextField();
        ivSdef = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        baseSpd = new javax.swing.JTextField();
        ivSpd = new javax.swing.JTextField();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        btnEvolveFrom = new javax.swing.JComboBox<>();
        btnEvolveTo = new javax.swing.JComboBox<>();
        btnForm = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        pkmSearch = new javax.swing.JTextField();
        btnReset = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        crPkm = new javax.swing.JComboBox<>();
        listComplete = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        listReset = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnSet = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));

        jPanel5.setPreferredSize(new java.awt.Dimension(450, 370));
        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        tblPkm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblPkm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPkmMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblPkmMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblPkm);

        jPanel5.add(jScrollPane1);

        getContentPane().add(jPanel5);

        jPanel3.setPreferredSize(new java.awt.Dimension(190, 370));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel12.setPreferredSize(new java.awt.Dimension(128, 40));
        jPanel12.setLayout(new java.awt.GridLayout(2, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("EV yield:");
        jPanel12.add(jLabel1);

        ivName.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        ivName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ivName.setAlignmentY(2.0F);
        jPanel12.add(ivName);

        jPanel3.add(jPanel12, java.awt.BorderLayout.PAGE_START);

        jPanel13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("HP :  ");
        jLabel7.setPreferredSize(new java.awt.Dimension(80, 30));
        jPanel13.add(jLabel7);

        baseHp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        baseHp.setPreferredSize(new java.awt.Dimension(40, 30));
        jPanel13.add(baseHp);

        ivHp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ivHp.setPreferredSize(new java.awt.Dimension(30, 30));
        jPanel13.add(ivHp);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("ATK :  ");
        jLabel2.setPreferredSize(new java.awt.Dimension(80, 30));
        jPanel13.add(jLabel2);

        baseAtk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        baseAtk.setPreferredSize(new java.awt.Dimension(40, 30));
        jPanel13.add(baseAtk);

        ivAtk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ivAtk.setPreferredSize(new java.awt.Dimension(30, 30));
        jPanel13.add(ivAtk);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("DEF :  ");
        jLabel3.setPreferredSize(new java.awt.Dimension(80, 30));
        jPanel13.add(jLabel3);

        baseDef.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        baseDef.setPreferredSize(new java.awt.Dimension(40, 30));
        jPanel13.add(baseDef);

        ivDef.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ivDef.setPreferredSize(new java.awt.Dimension(30, 30));
        jPanel13.add(ivDef);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("S.ATK :  ");
        jLabel4.setPreferredSize(new java.awt.Dimension(80, 30));
        jPanel13.add(jLabel4);

        baseSatk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        baseSatk.setPreferredSize(new java.awt.Dimension(40, 30));
        jPanel13.add(baseSatk);

        ivSatk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ivSatk.setPreferredSize(new java.awt.Dimension(30, 30));
        jPanel13.add(ivSatk);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("S.DEF :  ");
        jLabel5.setPreferredSize(new java.awt.Dimension(80, 30));
        jPanel13.add(jLabel5);

        baseSdef.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        baseSdef.setPreferredSize(new java.awt.Dimension(40, 30));
        jPanel13.add(baseSdef);

        ivSdef.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ivSdef.setPreferredSize(new java.awt.Dimension(30, 30));
        jPanel13.add(ivSdef);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("SPD :  ");
        jLabel6.setPreferredSize(new java.awt.Dimension(80, 30));
        jPanel13.add(jLabel6);

        baseSpd.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        baseSpd.setPreferredSize(new java.awt.Dimension(40, 30));
        jPanel13.add(baseSpd);

        ivSpd.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ivSpd.setPreferredSize(new java.awt.Dimension(30, 30));
        jPanel13.add(ivSpd);

        jPanel3.add(jPanel13, java.awt.BorderLayout.CENTER);

        jLayeredPane1.setPreferredSize(new java.awt.Dimension(128, 70));

        btnEvolveFrom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Evolve From--" }));
        btnEvolveFrom.setPreferredSize(new java.awt.Dimension(120, 25));
        btnEvolveFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEvolveFromActionPerformed(evt);
            }
        });
        jLayeredPane1.add(btnEvolveFrom);
        btnEvolveFrom.setBounds(30, 40, 120, 30);

        btnEvolveTo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Evolve Into--" }));
        btnEvolveTo.setPreferredSize(new java.awt.Dimension(120, 25));
        btnEvolveTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEvolveToActionPerformed(evt);
            }
        });
        jLayeredPane1.add(btnEvolveTo);
        btnEvolveTo.setBounds(30, 0, 120, 30);

        btnForm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Form--" }));
        btnForm.setPreferredSize(new java.awt.Dimension(120, 25));
        btnForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFormActionPerformed(evt);
            }
        });
        jLayeredPane1.add(btnForm);
        btnForm.setBounds(30, 0, 120, 30);

        jPanel3.add(jLayeredPane1, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(jPanel3);

        jPanel1.setPreferredSize(new java.awt.Dimension(450, 80));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 5));

        pkmSearch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pkmSearch.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pkmSearch.setPreferredSize(new java.awt.Dimension(100, 30));
        pkmSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pkmSearchActionPerformed(evt);
            }
        });
        jPanel1.add(pkmSearch);

        btnReset.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnReset.setText("RESET");
        btnReset.setPreferredSize(new java.awt.Dimension(75, 30));
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        jPanel1.add(btnReset);

        jPanel4.setMinimumSize(new java.awt.Dimension(50, 50));
        jPanel4.setPreferredSize(new java.awt.Dimension(80, 30));
        jPanel1.add(jPanel4);

        crPkm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Current Pokemon--" }));
        crPkm.setPreferredSize(new java.awt.Dimension(150, 30));
        crPkm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crPkmActionPerformed(evt);
            }
        });
        jPanel1.add(crPkm);

        listComplete.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        listComplete.setText("SHOW COMPLETED");
        listComplete.setMargin(new java.awt.Insets(2, 2, 2, 2));
        listComplete.setPreferredSize(new java.awt.Dimension(185, 30));
        listComplete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listCompleteActionPerformed(evt);
            }
        });
        jPanel1.add(listComplete);

        jPanel6.setMinimumSize(new java.awt.Dimension(50, 50));
        jPanel6.setPreferredSize(new java.awt.Dimension(80, 30));
        jPanel1.add(jPanel6);

        listReset.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        listReset.setText("UPDATE LIST");
        listReset.setMargin(new java.awt.Insets(2, 2, 2, 2));
        listReset.setPreferredSize(new java.awt.Dimension(150, 30));
        listReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listResetActionPerformed(evt);
            }
        });
        jPanel1.add(listReset);

        getContentPane().add(jPanel1);

        jPanel2.setPreferredSize(new java.awt.Dimension(190, 80));

        btnSet.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSet.setText("SET");
        btnSet.setPreferredSize(new java.awt.Dimension(100, 50));
        btnSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetActionPerformed(evt);
            }
        });
        jPanel2.add(btnSet);

        getContentPane().add(jPanel2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblPkmMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPkmMouseReleased
        int r = tblPkm.getSelectedRow();
        int c = tblPkm.getSelectedColumn();
        tblPkm.getCellEditor(r, c).cancelCellEditing();
    }//GEN-LAST:event_tblPkmMouseReleased

    private void tblPkmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPkmMouseClicked
        int sel = tblPkm.getSelectedRow();
        String name = tblPkm.getValueAt(sel, 1).toString();
        loadIV(name);
        setupBox(name);
    }//GEN-LAST:event_tblPkmMouseClicked
    void loadIV(String name) {
        ivName.setText(name);
        String form = "";
        StringTokenizer sk = new StringTokenizer(name, " ");
        name = sk.nextToken();
        if (sk.hasMoreTokens() && !name.equalsIgnoreCase("Nidoran Male")
                && !name.equalsIgnoreCase("Nidoran Female")
                && !name.equalsIgnoreCase("Mr. Mime")
                && !name.equalsIgnoreCase("Mime Jr.")) {
            form = sk.nextToken();
        }
        int selectIV = -1;
        for (int i = 0; i < iv.size(); i++) {
            if (iv.get(i).getName().equals(name)) {
                selectIV = i;
            }
        }
        String hp = "" + iv.get(selectIV).getHp();
        ivHp.setText(hp);
        String atk = "" + iv.get(selectIV).getAtk();
        ivAtk.setText(atk);
        String def = "" + iv.get(selectIV).getDef();
        ivDef.setText(def);
        String satk = "" + iv.get(selectIV).getSatk();
        ivSatk.setText(satk);
        String sdef = "" + iv.get(selectIV).getSdef();
        ivSdef.setText(sdef);
        String spd = "" + iv.get(selectIV).getSpd();
        ivSpd.setText(spd);
        if (form.equals("")) {
            for (int i = 0; i < pkm.size(); i++) {
                if (pkm.get(i).getName().equals(name)) {
                    hp = "" + pkm.get(i).getHp();
                    baseHp.setText(hp);
                    atk = "" + pkm.get(i).getAtk();
                    baseAtk.setText(atk);
                    def = "" + pkm.get(i).getDef();
                    baseDef.setText(def);
                    satk = "" + pkm.get(i).getSatk();
                    baseSatk.setText(satk);
                    sdef = "" + pkm.get(i).getSdef();
                    baseSdef.setText(sdef);
                    spd = "" + pkm.get(i).getSpd();
                    baseSpd.setText(spd);
                }
            }
        } else {
            for (int i = 0; i < AlternateForm.size(); i++) {
                if (AlternateForm.get(i).getForm().equals(form)) {
                    hp = "" + AlternateForm.get(i).getHp();
                    baseHp.setText(hp);
                    atk = "" + AlternateForm.get(i).getAtk();
                    baseAtk.setText(atk);
                    def = "" + AlternateForm.get(i).getDef();
                    baseDef.setText(def);
                    satk = "" + AlternateForm.get(i).getSatk();
                    baseSatk.setText(satk);
                    sdef = "" + AlternateForm.get(i).getSdef();
                    baseSdef.setText(sdef);
                    spd = "" + AlternateForm.get(i).getSpd();
                    baseSpd.setText(spd);
                }
            }
        }
        selected = true;
    }

    void setupBox(String name) {
        if (btnEvolveFrom.getItemCount() > 1) {
            for (int i = btnEvolveFrom.getItemCount() - 1; i > 0; i--) {
                btnEvolveFrom.removeItemAt(i);
            }
        }
        if (btnEvolveTo.getItemCount() > 1) {
            for (int i = btnEvolveTo.getItemCount() - 1; i > 0; i--) {
                btnEvolveTo.removeItemAt(i);
            }
        }
        if (btnForm.getItemCount() > 1) {
            for (int i = btnForm.getItemCount() - 1; i > 0; i--) {
                btnForm.removeItemAt(i);
            }
        }
        int evolveTo, evolveFrom, form;
        evolveTo = setupEvolveTo(name);
        evolveFrom = setupEvolveFrom(name);
        form = setupForm(name);
        if (evolveTo > 0) {
            btnEvolveTo.setVisible(true);
        } else {
            btnEvolveTo.setVisible(false);
        }
        if (evolveFrom > 0) {
            btnEvolveFrom.setVisible(true);
        } else {
            btnEvolveFrom.setVisible(false);
        }
        if (form > 0) {
            btnForm.setVisible(true);
        } else {
            btnForm.setVisible(false);
        }
    }

    int setupEvolveTo(String name) {
        int evolveTo = 0;
        for (int i = 0; i < EvolveList.size(); i++) {
            if (EvolveList.get(i).getPrior().equalsIgnoreCase(name)) {
                btnEvolveTo.addItem(EvolveList.get(i).getName());
                evolveTo++;
            }
        }
        return evolveTo;
    }

    int setupEvolveFrom(String name) {
        int evolveFrom = 0;
        for (int i = 0; i < EvolveList.size(); i++) {
            if (EvolveList.get(i).getName().equalsIgnoreCase(name)
                    && !EvolveList.get(i).getPrior().equalsIgnoreCase("none")) {
                btnEvolveFrom.addItem(EvolveList.get(i).getPrior());
                evolveFrom++;
            }
        }
        return evolveFrom;
    }

    int setupForm(String name) {
        int form = 0;
        for (int i = 0; i < AlternateForm.size(); i++) {
            if (AlternateForm.get(i).getName().equalsIgnoreCase(name)) {
                btnForm.addItem(AlternateForm.get(i).getForm());
                form++;
            }
        }
        return form;
    }

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        int index = tblPkm.getSelectedRow();
        int select = -1;
        if (index >= 0 && index < model.getData().size()) {
            String name = tblPkm.getValueAt(index, 1).toString();
            for (int i = 0; i < pkm.size(); i++) {
                if (iv.get(i).getName().equals(name)) {
                    select = i;
                }
            }
        }
        if (model.getData().size() > 0) {
            model.getData().clear();
        }
        if (pkmTmp.size() > 0) {
            pkmTmp.clear();
        }
        pkm.clear();
        EvolveList.clear();
        iv.clear();
        AlternateForm.clear();
        loadData(folderPath + "List Files/List.txt");
        tblPkm.updateUI();
        tblPkm.setRowSelectionInterval(select, select);
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetActionPerformed
        if (!selected) {
            JOptionPane.showMessageDialog(this, "Select a Pokemon.");
        } else {
            int index = 0;
            for (int i = 0; i < pkm.size(); i++) {
                if (pkm.get(i).getName().toLowerCase().equals(ivName.getText().toLowerCase())) {
                    index = i;
                }
            }
            Stat st = new Stat(pkm.get(index));
            st.setVisible(true);
            st.setTitle(pkm.get(index).getName() + "'s Stats Tracking");
            st.setSize(400, 500);
        }
    }//GEN-LAST:event_btnSetActionPerformed

    private void pkmSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pkmSearchActionPerformed
        if (pkmSearch.getText().equals("")) {
            btnResetActionPerformed(evt);
        } else {
            if (pkmTmp.size() > 0) {
                pkmTmp.clear();
            }
            String s = pkmSearch.getText().toLowerCase();
            for (int i = 0; i < pkm.size(); i++) {
                if (pkm.get(i).getName().toLowerCase().contains(s)) {
                    pkmTmp.add(pkm.get(i));
                }
            }
            model.getData().clear();
            for (int i = 0; i < pkmTmp.size(); i++) {
                model.getData().add(pkmTmp.get(i));
            }
            tblPkm.updateUI();
        }
        if (pkmTmp.size() == 1) {
            String name = pkmTmp.get(0).getName();
            loadIV(name);
            setupBox(name);
            tblPkm.setRowSelectionInterval(0, 0);
        }
    }//GEN-LAST:event_pkmSearchActionPerformed

    private void crPkmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crPkmActionPerformed
        if (crPkm.getSelectedIndex() != 0) {
            int index = 0;
            for (int i = 0; i < pkm.size(); i++) {
                if (pkm.get(i).getName().equals(crPkm.getSelectedItem().toString())) {
                    index = i;
                }
            }
            Stat st = new Stat(pkm.get(index));
            st.setVisible(true);
            st.setTitle(pkm.get(index).getName() + "'s Stats");
            st.setSize(400, 500);
        }
    }//GEN-LAST:event_crPkmActionPerformed

    private void listResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listResetActionPerformed
        loadExisting();
        crPkm.setSelectedIndex(0);
        File file;
        String filePath;
        for (int i = 1; i < crPkm.getItemCount(); i++) {
            String pokemon = crPkm.getItemAt(i);
            filePath = folderPath + pokemon + "_Stat.txt";
            file = new File(filePath);
            if (!file.exists()) {
                crPkm.removeItemAt(i);
            }
        }
    }//GEN-LAST:event_listResetActionPerformed

    private void listCompleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listCompleteActionPerformed
        if (pkmTmp.size() > 0) {
            pkmTmp.clear();
        }
        for (int i = 0; i < pkm.size(); i++) {
            String pokemon = pkm.get(i).getName();
            String filePath = folderPath + "Completed/" + pokemon + "_Stat.txt";
            File file = new File(filePath);
            if (file.exists() && !checkExisting(pokemon)) {
                pkmTmp.add(pkm.get(i));
            }
        }
        model.getData().clear();
        for (int i = 0; i < pkmTmp.size(); i++) {
            model.getData().add(pkmTmp.get(i));
        }
        tblPkm.updateUI();
    }//GEN-LAST:event_listCompleteActionPerformed

    private void btnFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFormActionPerformed
        if (btnForm.getSelectedIndex() != 0) {
            String form = btnForm.getSelectedItem().toString();
            String name = ivName.getText();
            StringTokenizer sk = new StringTokenizer(name, " ");
            if (sk.countTokens() > 1 && !name.equalsIgnoreCase("Nidoran Male")
                    && !name.equalsIgnoreCase("Nidoran Female")
                    && !name.equalsIgnoreCase("Mr. Mime")
                    && !name.equalsIgnoreCase("Mime Jr.")) {
                name = sk.nextToken();
            }
            String full = name + " " + form;
            loadIV(full);
            setupBox(name);
        }
    }//GEN-LAST:event_btnFormActionPerformed

    private void btnEvolveToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEvolveToActionPerformed
        if (btnEvolveTo.getSelectedIndex() != 0) {
            String name = btnEvolveTo.getSelectedItem().toString();
            loadIV(name);
            setupBox(name);
            for (int i = 0; i < tblPkm.getRowCount(); i++) {
                if (tblPkm.getValueAt(i, 1).toString().equals(name)) {
                    tblPkm.setRowSelectionInterval(i, i);
                }
            }
        }
    }//GEN-LAST:event_btnEvolveToActionPerformed

    private void btnEvolveFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEvolveFromActionPerformed
        if (btnEvolveFrom.getSelectedIndex() != 0) {
            String name = btnEvolveFrom.getSelectedItem().toString();
            loadIV(name);
            setupBox(name);
            for (int i = 0; i < tblPkm.getRowCount(); i++) {
                if (tblPkm.getValueAt(i, 1).toString().equals(name)) {
                    tblPkm.setRowSelectionInterval(i, i);
                }
            }
        }
    }//GEN-LAST:event_btnEvolveFromActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainList ml = new MainList();
                ml.setVisible(true);
                ml.setTitle("Stats Calculation");
                ml.setSize(700, 520);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField baseAtk;
    private javax.swing.JTextField baseDef;
    private javax.swing.JTextField baseHp;
    private javax.swing.JTextField baseSatk;
    private javax.swing.JTextField baseSdef;
    private javax.swing.JTextField baseSpd;
    private javax.swing.JComboBox<String> btnEvolveFrom;
    private javax.swing.JComboBox<String> btnEvolveTo;
    private javax.swing.JComboBox<String> btnForm;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSet;
    private javax.swing.JComboBox<String> crPkm;
    private javax.swing.JTextField ivAtk;
    private javax.swing.JTextField ivDef;
    private javax.swing.JTextField ivHp;
    private javax.swing.JLabel ivName;
    private javax.swing.JTextField ivSatk;
    private javax.swing.JTextField ivSdef;
    private javax.swing.JTextField ivSpd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton listComplete;
    private javax.swing.JButton listReset;
    private javax.swing.JTextField pkmSearch;
    private javax.swing.JTable tblPkm;
    // End of variables declaration//GEN-END:variables
}

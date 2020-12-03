package pokemon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Stat extends javax.swing.JFrame {

    String folderPath;
    static Pokemon pokemon;
    String name;
    int hp, atk, def, satk, sdef, spd;
    String list = "D:/MyWorks/DS Emulator/Pokemon/Text Files/List Files/List.txt";
    String listA = "E:/Donald/Nald Games/NDS/Pokemon/Text Files/List Files/List.txt";
    boolean Rus = false;
    int RusCount;
    File i = new File(list);
    File iA = new File(listA);
    double[] natureList = {0, 0, 0, 0, 0};
    Vector<Pokemon> pkm = new Vector<>();
    Vector<EvolutionLine> EvolveList = new Vector<>();

    public Stat(Pokemon pkm) {
        initComponents();
        folderPath = (new FilePath()).getFilePath() + "Text Files/";
        this.pokemon = pkm;
        loadSpecificData(pokemon.getName());
        if (i.exists()) {
            loadDataIV(list);
        } else if (iA.exists()) {
            loadDataIV(listA);
        }
        EvolveText.setVisible(false);
        loadExisting();
        loadData();
        if (Rus) {
            RusCount = 1;
            RusState.setText("Rus ON");
        } else {
            RusCount = 0;
            RusState.setText("Rus OFF");
        }
    }

    private void loadDataIV(String s) {
        try {
            FileReader fr = new FileReader(s);
            BufferedReader br = new BufferedReader(fr);
            String S;
            while ((S = br.readLine()) != null) {
                S = S.trim();
                if (S.length() > 0) {
                    StringTokenizer st = new StringTokenizer(S, ",");
                    String Name = st.nextToken();
                    int basehp = Integer.parseInt(st.nextToken());
                    int baseatk = Integer.parseInt(st.nextToken());
                    int basedef = Integer.parseInt(st.nextToken());
                    int basesatk = Integer.parseInt(st.nextToken());
                    int basesdef = Integer.parseInt(st.nextToken());
                    int basespd = Integer.parseInt(st.nextToken());
                    int ivhp = Integer.parseInt(st.nextToken());
                    int ivatk = Integer.parseInt(st.nextToken());
                    int ivdef = Integer.parseInt(st.nextToken());
                    int ivsatk = Integer.parseInt(st.nextToken());
                    int ivsdef = Integer.parseInt(st.nextToken());
                    int ivspd = Integer.parseInt(st.nextToken());
                    int stage = Integer.parseInt(st.nextToken());
                    String preEvolve = st.nextToken();
                    pkm.add(new Pokemon(Name, "", basehp, baseatk, basedef, basesatk, basesdef, basespd));
                    EvolveList.add(new EvolutionLine(Name, stage, preEvolve));
                }
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadExisting() {
        for (int i = 0; i < pkm.size(); i++) {
            String pokemonName = pkm.get(i).getName();
            String filePath = folderPath + pokemonName + "_Stat.txt";
            File file = new File(filePath);
            if (file.exists() && !pokemonName.equalsIgnoreCase(pkmName.getText())) {
                crPkm.addItem(pokemonName);
            }
        }
    }

    private void loadData() {
        name = pokemon.getName().toUpperCase();
        hp = pokemon.getHp();
        atk = pokemon.getAtk();
        def = pokemon.getDef();
        satk = pokemon.getSatk();
        sdef = pokemon.getSdef();
        spd = pokemon.getSpd();
        pkmName.setText(name);
        BaseHp.setText(hp + "");
        BaseHp.setEditable(false);
        BaseAtk.setText(atk + "");
        BaseAtk.setEditable(false);
        BaseDef.setText(def + "");
        BaseDef.setEditable(false);
        BaseSatk.setText(satk + "");
        BaseSatk.setEditable(false);
        BaseSdef.setText(sdef + "");
        BaseSdef.setEditable(false);
        BaseSpd.setText(spd + "");
        BaseSpd.setEditable(false);
        for (int x = 0; x < EvolveList.size(); x++) {
            if (EvolveList.get(x).getPrior().equalsIgnoreCase(name)) {
                btnEvolve.addItem(EvolveList.get(x).getName());
                btnEvolve.setSelectedIndex(0);
            }
        }
        if (btnEvolve.getItemCount() == 1) {
            btnEvolve.setVisible(false);
            EvolveText.setVisible(true);
            EvolveText.setText("Final Stage");
        }
    }

    private void loadSpecificData(String pokemon) {
        String filePath = pokemon + "_Stat.txt";
        File file = null;
        if ((file = new File(folderPath + "Completed/" + filePath)).exists()) {
            JOptionPane.showMessageDialog(this, "Completed Stats Found!");
            loadSpecificPokemon(folderPath + "Completed/" + filePath);
        } else if ((file = new File(folderPath + filePath)).exists()) {
            loadSpecificPokemon(folderPath + filePath);
        }
    }

    private void loadSpecificPokemon(String in) {
        try {
            FileReader fr = new FileReader(in);
            BufferedReader br = new BufferedReader(fr);
            String S;
            while ((S = br.readLine()) != null) {
                S = S.trim();
                if (S.length() > 0) {
                    StringTokenizer st = new StringTokenizer(S, ",");
                    btnLvl.setText(st.nextToken());
                    IVHp.setText(st.nextToken());
                    IVAtk.setText(st.nextToken());
                    IVDef.setText(st.nextToken());
                    IVSatk.setText(st.nextToken());
                    IVSdef.setText(st.nextToken());
                    IVSpd.setText(st.nextToken());
                    setEvHP.setText(st.nextToken());
                    setEvAtk.setText(st.nextToken());
                    setEvDef.setText(st.nextToken());
                    setEvSatk.setText(st.nextToken());
                    setEvSdef.setText(st.nextToken());
                    setEvSpd.setText(st.nextToken());
                    crtEvHP.setText(st.nextToken());
                    crtEvAtk.setText(st.nextToken());
                    crtEvDef.setText(st.nextToken());
                    crtEvSatk.setText(st.nextToken());
                    crtEvSdef.setText(st.nextToken());
                    crtEvSpd.setText(st.nextToken());
                    btnNature.setSelectedItem(st.nextToken());
                    Rus = st.nextToken().equalsIgnoreCase("true");
                }
                tally();
                CountMaxActionPerformed(null);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        crPkm = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        btnNature = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        EvolveLayer = new javax.swing.JLayeredPane();
        EvolveText = new javax.swing.JLabel();
        btnEvolve = new javax.swing.JComboBox<>();
        jPanel11 = new javax.swing.JPanel();
        RusState = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btnLvl = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        BaseHp = new javax.swing.JTextField();
        BaseAtk = new javax.swing.JTextField();
        BaseDef = new javax.swing.JTextField();
        BaseSatk = new javax.swing.JTextField();
        BaseSdef = new javax.swing.JTextField();
        BaseSpd = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        IVHp = new javax.swing.JTextField();
        IVAtk = new javax.swing.JTextField();
        IVDef = new javax.swing.JTextField();
        IVSatk = new javax.swing.JTextField();
        IVSdef = new javax.swing.JTextField();
        IVSpd = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        setEvHP = new javax.swing.JTextField();
        setEvAtk = new javax.swing.JTextField();
        setEvDef = new javax.swing.JTextField();
        setEvSatk = new javax.swing.JTextField();
        setEvSdef = new javax.swing.JTextField();
        setEvSpd = new javax.swing.JTextField();
        lblEV = new javax.swing.JLabel();
        crtEvHP = new javax.swing.JTextField();
        crtEvAtk = new javax.swing.JTextField();
        crtEvDef = new javax.swing.JTextField();
        crtEvSatk = new javax.swing.JTextField();
        crtEvSdef = new javax.swing.JTextField();
        crtEvSpd = new javax.swing.JTextField();
        evCount = new javax.swing.JLabel();
        btnEvHP = new javax.swing.JButton();
        btnEvAtk = new javax.swing.JButton();
        btnEvDef = new javax.swing.JButton();
        btnEvSatk = new javax.swing.JButton();
        btnEvSdef = new javax.swing.JButton();
        btnEvSpd = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        totalHP = new javax.swing.JLabel();
        totalAtk = new javax.swing.JLabel();
        totalDef = new javax.swing.JLabel();
        totalSatk = new javax.swing.JLabel();
        totalSdef = new javax.swing.JLabel();
        totalSpd = new javax.swing.JLabel();
        CountMax = new javax.swing.JButton();
        totalHP1 = new javax.swing.JLabel();
        totalAtk1 = new javax.swing.JLabel();
        totalDef1 = new javax.swing.JLabel();
        totalSatk1 = new javax.swing.JLabel();
        totalSdef1 = new javax.swing.JLabel();
        totalSpd1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        pkmName = new javax.swing.JLabel();
        Status = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(400, 440));
        getContentPane().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        jPanel1.setPreferredSize(new java.awt.Dimension(350, 100));

        jPanel7.setPreferredSize(new java.awt.Dimension(170, 40));

        crPkm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Current Pokemon--" }));
        crPkm.setPreferredSize(new java.awt.Dimension(150, 30));
        crPkm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crPkmActionPerformed(evt);
            }
        });
        jPanel7.add(crPkm);

        jPanel1.add(jPanel7);

        jPanel3.setPreferredSize(new java.awt.Dimension(130, 40));

        btnNature.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Nature--", "Hardy", "Lonely", "Adamant", "Naughty", "Brave", "Bold", "Docile", "Impish", "Lax", "Relaxed", "Modest", "Mild", "Bashful", "Rash", "Quiet", "Calm", "Gentle", "Careful", "Quirky", "Sassy", "Timid", "Hasty", "Jolly", "Naive", "Serious" }));
        btnNature.setPreferredSize(new java.awt.Dimension(100, 30));
        btnNature.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNatureActionPerformed(evt);
            }
        });
        jPanel3.add(btnNature);

        jPanel1.add(jPanel3);

        jPanel10.setPreferredSize(new java.awt.Dimension(170, 40));

        EvolveLayer.setPreferredSize(new java.awt.Dimension(150, 40));

        EvolveText.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        EvolveText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EvolveText.setPreferredSize(new java.awt.Dimension(150, 30));
        EvolveLayer.add(EvolveText);
        EvolveText.setBounds(0, 0, 150, 30);

        btnEvolve.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Evolve Into--" }));
        btnEvolve.setPreferredSize(new java.awt.Dimension(150, 30));
        btnEvolve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEvolveActionPerformed(evt);
            }
        });
        EvolveLayer.add(btnEvolve);
        btnEvolve.setBounds(0, 0, 150, 30);

        jPanel10.add(EvolveLayer);

        jPanel1.add(jPanel10);

        jPanel11.setPreferredSize(new java.awt.Dimension(130, 40));

        RusState.setText("Rus OFF");
        RusState.setPreferredSize(new java.awt.Dimension(100, 30));
        RusState.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RusStateActionPerformed(evt);
            }
        });
        jPanel11.add(RusState);

        jPanel1.add(jPanel11);

        getContentPane().add(jPanel1);

        jPanel4.setPreferredSize(new java.awt.Dimension(350, 250));
        jPanel4.setLayout(new java.awt.GridLayout(8, 7));

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        btnLvl.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        btnLvl.setText("1");
        btnLvl.setMargin(new java.awt.Insets(1, 1, 1, 1));
        btnLvl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLvlActionPerformed(evt);
            }
        });
        jPanel5.add(btnLvl);

        jPanel4.add(jPanel5);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("HP");
        jLabel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel17.setMinimumSize(null);
        jPanel4.add(jLabel17);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("ATK");
        jLabel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel13.setMinimumSize(null);
        jPanel4.add(jLabel13);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("DEF");
        jLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel9.setMinimumSize(null);
        jPanel4.add(jLabel9);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("S.ATK");
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel6.setMinimumSize(null);
        jPanel4.add(jLabel6);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("S.DEF");
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel5.setMinimumSize(null);
        jPanel4.add(jLabel5);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SPD");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel1.setMinimumSize(null);
        jPanel4.add(jLabel1);

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("BASE");
        jLabel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel11.setMinimumSize(null);
        jPanel4.add(jLabel11);

        BaseHp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        BaseHp.setText("0");
        BaseHp.setMinimumSize(null);
        jPanel4.add(BaseHp);

        BaseAtk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        BaseAtk.setText("0");
        BaseAtk.setMinimumSize(null);
        jPanel4.add(BaseAtk);

        BaseDef.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        BaseDef.setText("0");
        BaseDef.setMinimumSize(null);
        jPanel4.add(BaseDef);

        BaseSatk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        BaseSatk.setText("0");
        BaseSatk.setMinimumSize(null);
        jPanel4.add(BaseSatk);

        BaseSdef.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        BaseSdef.setText("0");
        BaseSdef.setMinimumSize(null);
        jPanel4.add(BaseSdef);

        BaseSpd.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        BaseSpd.setText("0");
        BaseSpd.setMinimumSize(null);
        jPanel4.add(BaseSpd);

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("IVs");
        jLabel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel18.setMinimumSize(null);
        jPanel4.add(jLabel18);

        IVHp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        IVHp.setText("0");
        IVHp.setMinimumSize(null);
        IVHp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IVHpActionPerformed(evt);
            }
        });
        jPanel4.add(IVHp);

        IVAtk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        IVAtk.setText("0");
        IVAtk.setMinimumSize(null);
        IVAtk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IVAtkActionPerformed(evt);
            }
        });
        jPanel4.add(IVAtk);

        IVDef.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        IVDef.setText("0");
        IVDef.setMinimumSize(null);
        IVDef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IVDefActionPerformed(evt);
            }
        });
        jPanel4.add(IVDef);

        IVSatk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        IVSatk.setText("0");
        IVSatk.setMinimumSize(null);
        IVSatk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IVSatkActionPerformed(evt);
            }
        });
        jPanel4.add(IVSatk);

        IVSdef.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        IVSdef.setText("0");
        IVSdef.setMinimumSize(null);
        IVSdef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IVSdefActionPerformed(evt);
            }
        });
        jPanel4.add(IVSdef);

        IVSpd.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        IVSpd.setText("0");
        IVSpd.setMinimumSize(null);
        IVSpd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IVSpdActionPerformed(evt);
            }
        });
        jPanel4.add(IVSpd);

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Set EVs");
        jLabel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel16.setMinimumSize(null);
        jPanel4.add(jLabel16);

        setEvHP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        setEvHP.setText("0");
        setEvHP.setMinimumSize(null);
        setEvHP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setEvHPActionPerformed(evt);
            }
        });
        jPanel4.add(setEvHP);

        setEvAtk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        setEvAtk.setText("0");
        setEvAtk.setMinimumSize(null);
        setEvAtk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setEvAtkActionPerformed(evt);
            }
        });
        jPanel4.add(setEvAtk);

        setEvDef.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        setEvDef.setText("0");
        setEvDef.setMinimumSize(null);
        setEvDef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setEvDefActionPerformed(evt);
            }
        });
        jPanel4.add(setEvDef);

        setEvSatk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        setEvSatk.setText("0");
        setEvSatk.setMinimumSize(null);
        setEvSatk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setEvSatkActionPerformed(evt);
            }
        });
        jPanel4.add(setEvSatk);

        setEvSdef.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        setEvSdef.setText("0");
        setEvSdef.setMinimumSize(null);
        setEvSdef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setEvSdefActionPerformed(evt);
            }
        });
        jPanel4.add(setEvSdef);

        setEvSpd.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        setEvSpd.setText("0");
        setEvSpd.setMinimumSize(null);
        setEvSpd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setEvSpdActionPerformed(evt);
            }
        });
        jPanel4.add(setEvSpd);

        lblEV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEV.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        lblEV.setMinimumSize(null);
        jPanel4.add(lblEV);

        crtEvHP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        crtEvHP.setText("0");
        crtEvHP.setMinimumSize(null);
        crtEvHP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crtEvHPActionPerformed(evt);
            }
        });
        jPanel4.add(crtEvHP);

        crtEvAtk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        crtEvAtk.setText("0");
        crtEvAtk.setMinimumSize(null);
        crtEvAtk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crtEvAtkActionPerformed(evt);
            }
        });
        jPanel4.add(crtEvAtk);

        crtEvDef.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        crtEvDef.setText("0");
        crtEvDef.setMinimumSize(null);
        crtEvDef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crtEvDefActionPerformed(evt);
            }
        });
        jPanel4.add(crtEvDef);

        crtEvSatk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        crtEvSatk.setText("0");
        crtEvSatk.setMinimumSize(null);
        crtEvSatk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crtEvSatkActionPerformed(evt);
            }
        });
        jPanel4.add(crtEvSatk);

        crtEvSdef.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        crtEvSdef.setText("0");
        crtEvSdef.setMinimumSize(null);
        crtEvSdef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crtEvSdefActionPerformed(evt);
            }
        });
        jPanel4.add(crtEvSdef);

        crtEvSpd.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        crtEvSpd.setText("0");
        crtEvSpd.setMinimumSize(null);
        crtEvSpd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crtEvSpdActionPerformed(evt);
            }
        });
        jPanel4.add(crtEvSpd);

        evCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        evCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        evCount.setMinimumSize(null);
        jPanel4.add(evCount);

        btnEvHP.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnEvHP.setText("UP");
        btnEvHP.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnEvHP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEvHPActionPerformed(evt);
            }
        });
        jPanel4.add(btnEvHP);

        btnEvAtk.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnEvAtk.setText("UP");
        btnEvAtk.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnEvAtk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEvAtkActionPerformed(evt);
            }
        });
        jPanel4.add(btnEvAtk);

        btnEvDef.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnEvDef.setText("UP");
        btnEvDef.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnEvDef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEvDefActionPerformed(evt);
            }
        });
        jPanel4.add(btnEvDef);

        btnEvSatk.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnEvSatk.setText("UP");
        btnEvSatk.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnEvSatk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEvSatkActionPerformed(evt);
            }
        });
        jPanel4.add(btnEvSatk);

        btnEvSdef.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnEvSdef.setText("UP");
        btnEvSdef.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnEvSdef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEvSdefActionPerformed(evt);
            }
        });
        jPanel4.add(btnEvSdef);

        btnEvSpd.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnEvSpd.setText("UP");
        btnEvSpd.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnEvSpd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEvSpdActionPerformed(evt);
            }
        });
        jPanel4.add(btnEvSpd);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Total");
        jLabel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jLabel14.setMinimumSize(null);
        jPanel4.add(jLabel14);

        totalHP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalHP.setText("0");
        totalHP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        totalHP.setMinimumSize(null);
        jPanel4.add(totalHP);

        totalAtk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalAtk.setText("0");
        totalAtk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        totalAtk.setMinimumSize(null);
        jPanel4.add(totalAtk);

        totalDef.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalDef.setText("0");
        totalDef.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        totalDef.setMinimumSize(null);
        jPanel4.add(totalDef);

        totalSatk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalSatk.setText("0");
        totalSatk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        totalSatk.setMinimumSize(null);
        jPanel4.add(totalSatk);

        totalSdef.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalSdef.setText("0");
        totalSdef.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        totalSdef.setMinimumSize(null);
        jPanel4.add(totalSdef);

        totalSpd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalSpd.setText("0");
        totalSpd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        totalSpd.setMinimumSize(null);
        jPanel4.add(totalSpd);

        CountMax.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        CountMax.setText("MAX");
        CountMax.setMargin(new java.awt.Insets(1, 1, 1, 1));
        CountMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CountMaxActionPerformed(evt);
            }
        });
        jPanel4.add(CountMax);

        totalHP1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalHP1.setText("0");
        totalHP1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        totalHP1.setMinimumSize(null);
        jPanel4.add(totalHP1);

        totalAtk1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalAtk1.setText("0");
        totalAtk1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        totalAtk1.setMinimumSize(null);
        jPanel4.add(totalAtk1);

        totalDef1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalDef1.setText("0");
        totalDef1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        totalDef1.setMinimumSize(null);
        jPanel4.add(totalDef1);

        totalSatk1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalSatk1.setText("0");
        totalSatk1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        totalSatk1.setMinimumSize(null);
        jPanel4.add(totalSatk1);

        totalSdef1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalSdef1.setText("0");
        totalSdef1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        totalSdef1.setMinimumSize(null);
        jPanel4.add(totalSdef1);

        totalSpd1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalSpd1.setText("0");
        totalSpd1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        totalSpd1.setMinimumSize(null);
        jPanel4.add(totalSpd1);

        getContentPane().add(jPanel4);

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        jPanel6.setPreferredSize(new java.awt.Dimension(350, 60));

        jPanel8.setPreferredSize(new java.awt.Dimension(220, 45));
        jPanel8.setLayout(new java.awt.GridLayout(2, 0));

        pkmName.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        pkmName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pkmName.setPreferredSize(new java.awt.Dimension(80, 20));
        jPanel8.add(pkmName);

        Status.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel8.add(Status);

        jPanel6.add(jPanel8);

        jPanel9.setPreferredSize(new java.awt.Dimension(100, 40));

        btnSave.setText("SAVE");
        btnSave.setPreferredSize(new java.awt.Dimension(80, 30));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel9.add(btnSave);

        jPanel6.add(jPanel9);

        getContentPane().add(jPanel6);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNatureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNatureActionPerformed
        if (btnNature.getSelectedIndex() != 0) {
            natureList = natureTally(btnNature.getSelectedItem().toString());
            tally();
        }
    }//GEN-LAST:event_btnNatureActionPerformed

    private void btnEvHPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEvHPActionPerformed
        int check = checkEvLower();
        if (check < 510) {
            int real = convert(crtEvHP.getText());
            if (!Rus) {
                real += 1;
            } else {
                real += 2;
            }
            crtEvHP.setText(real + "");
            tally();
        }
    }//GEN-LAST:event_btnEvHPActionPerformed

    private void btnEvAtkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEvAtkActionPerformed
        int check = checkEvLower();
        if (check < 510) {
            int real = convert(crtEvAtk.getText());
            if (!Rus) {
                real += 1;
            } else {
                real += 2;
            }
            crtEvAtk.setText(real + "");
            tally();
        }
    }//GEN-LAST:event_btnEvAtkActionPerformed

    private void btnEvDefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEvDefActionPerformed
        int check = checkEvLower();
        if (check < 510) {
            int real = convert(crtEvDef.getText());
            if (!Rus) {
                real += 1;
            } else {
                real += 2;
            }
            crtEvDef.setText(real + "");
            tally();
        }
    }//GEN-LAST:event_btnEvDefActionPerformed

    private void btnEvSatkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEvSatkActionPerformed
        int check = checkEvLower();
        if (check < 510) {
            int real = convert(crtEvSatk.getText());
            if (!Rus) {
                real += 1;
            } else {
                real += 2;
            }
            crtEvSatk.setText(real + "");
            tally();
        }
    }//GEN-LAST:event_btnEvSatkActionPerformed

    private void btnEvSdefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEvSdefActionPerformed
        int check = checkEvLower();
        if (check < 510) {
            int real = convert(crtEvSdef.getText());
            if (!Rus) {
                real += 1;
            } else {
                real += 2;
            }
            crtEvSdef.setText(real + "");
            tally();
        }
    }//GEN-LAST:event_btnEvSdefActionPerformed

    private void btnEvSpdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEvSpdActionPerformed
        int check = checkEvLower();
        if (check < 510) {
            int real = convert(crtEvSpd.getText());
            if (!Rus) {
                real += 1;
            } else {
                real += 2;
            }
            crtEvSpd.setText(real + "");
            tally();
        }
    }//GEN-LAST:event_btnEvSpdActionPerformed

    private void crtEvHPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crtEvHPActionPerformed
        tally();
    }//GEN-LAST:event_crtEvHPActionPerformed

    private void crtEvAtkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crtEvAtkActionPerformed
        tally();
    }//GEN-LAST:event_crtEvAtkActionPerformed

    private void crtEvDefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crtEvDefActionPerformed
        tally();
    }//GEN-LAST:event_crtEvDefActionPerformed

    private void crtEvSatkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crtEvSatkActionPerformed
        tally();
    }//GEN-LAST:event_crtEvSatkActionPerformed

    private void crtEvSdefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crtEvSdefActionPerformed
        tally();
    }//GEN-LAST:event_crtEvSdefActionPerformed

    private void crtEvSpdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crtEvSpdActionPerformed
        tally();
    }//GEN-LAST:event_crtEvSpdActionPerformed

    private void setEvHPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setEvHPActionPerformed
        int set = convert(setEvHP.getText()), real = convert(crtEvHP.getText());
        int compare = real - set;
        if (compare > 0) {
            btnEvHP.setText("+" + (compare));
        } else {
            btnEvHP.setText("UP");
        }
        calculateMax();
    }//GEN-LAST:event_setEvHPActionPerformed

    private void setEvAtkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setEvAtkActionPerformed
        int set = convert(setEvAtk.getText()), real = convert(crtEvAtk.getText());
        int compare = real - set;
        if (compare > 0) {
            btnEvAtk.setText("+" + (compare));
        } else {
            btnEvAtk.setText("UP");
        }
        calculateMax();
    }//GEN-LAST:event_setEvAtkActionPerformed

    private void setEvDefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setEvDefActionPerformed
        int set = convert(setEvDef.getText()), real = convert(crtEvDef.getText());
        int compare = real - set;
        if (compare > 0) {
            btnEvDef.setText("+" + (compare));
        } else {
            btnEvDef.setText("UP");
        }
        calculateMax();
    }//GEN-LAST:event_setEvDefActionPerformed

    private void setEvSatkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setEvSatkActionPerformed
        int set = convert(setEvSatk.getText()), real = convert(crtEvSatk.getText());
        int compare = real - set;
        if (compare > 0) {
            btnEvSatk.setText("+" + (compare));
        } else {
            btnEvSatk.setText("UP");
        }
        calculateMax();
    }//GEN-LAST:event_setEvSatkActionPerformed

    private void setEvSdefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setEvSdefActionPerformed
        int set = convert(setEvSdef.getText()), real = convert(crtEvSdef.getText());
        int compare = real - set;
        if (compare > 0) {
            btnEvSdef.setText("+" + (compare));
        } else {
            btnEvSdef.setText("UP");
        }
        calculateMax();
    }//GEN-LAST:event_setEvSdefActionPerformed

    private void setEvSpdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setEvSpdActionPerformed
        int set = convert(setEvSpd.getText()), real = convert(crtEvSpd.getText());
        int compare = real - set;
        if (compare > 0) {
            btnEvSpd.setText("+" + (compare));
        } else {
            btnEvSpd.setText("UP");
        }
        calculateMax();
    }//GEN-LAST:event_setEvSpdActionPerformed

    private void IVHpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IVHpActionPerformed
        tally();
    }//GEN-LAST:event_IVHpActionPerformed

    private void IVAtkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IVAtkActionPerformed
        tally();
    }//GEN-LAST:event_IVAtkActionPerformed

    private void IVDefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IVDefActionPerformed
        tally();
    }//GEN-LAST:event_IVDefActionPerformed

    private void IVSatkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IVSatkActionPerformed
        tally();
    }//GEN-LAST:event_IVSatkActionPerformed

    private void IVSdefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IVSdefActionPerformed
        tally();
    }//GEN-LAST:event_IVSdefActionPerformed

    private void IVSpdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IVSpdActionPerformed
        tally();
    }//GEN-LAST:event_IVSpdActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        tally();
        String name = pkmName.getText().toLowerCase();
        String filePath = folderPath + name + "_Stat.txt";
        saveCompletedFile(folderPath, filePath, name);

    }//GEN-LAST:event_btnSaveActionPerformed
    void saveCompletedFile(String folder, String filePath, String name) {
        File file = new File(filePath);
        if (checkCompletion()) {
            if (JOptionPane.showConfirmDialog(this, "Pokemon's Stats Maximized, move file to Completed Folder?",
                    "Move Completed File?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (file.exists()) {
                    file.delete();
                }
                btnLvl.setText("100");
                filePath = folder + "Completed/" + name + "_Stat.txt";
                save(filePath);
            } else {
                save(filePath);
            }
        } else {
            save(filePath);
        }
    }

    private void save(String s) {
        String lv = btnLvl.getText();
        String ivHp = IVHp.getText(), ivAtk = IVAtk.getText(),
                ivDef = IVDef.getText(), ivSa = IVSatk.getText(),
                ivSd = IVSdef.getText(), ivSp = IVSpd.getText();
        String SevHp = setEvHP.getText(), SevAtk = setEvAtk.getText(),
                SevDef = setEvDef.getText(), SevSa = setEvSatk.getText(),
                SevSd = setEvSdef.getText(), SevSp = setEvSpd.getText();
        String evHp = crtEvHP.getText(), evAtk = crtEvAtk.getText(),
                evDef = crtEvDef.getText(), evSa = crtEvSatk.getText(),
                evSd = crtEvSdef.getText(), evSp = crtEvSpd.getText();
        String na = btnNature.getSelectedItem().toString();

        try {
            PrintWriter pw = new PrintWriter(s);
            String line = lv + "," + ivHp + "," + ivAtk + "," + ivDef + "," + ivSa + "," + ivSd + "," + ivSp
                    + "," + SevHp + "," + SevAtk + "," + SevDef + "," + SevSa + "," + SevSd + "," + SevSp
                    + "," + evHp + "," + evAtk + "," + evDef + "," + evSa + "," + evSd + "," + evSp + "," + na + "," + Rus;
            pw.println(line);
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void CountMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CountMaxActionPerformed
        calculateMax();
    }//GEN-LAST:event_CountMaxActionPerformed
    void calculateMax() {
        if (btnNature.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Select a nature.");
        } else {
            int ivHp = convert(IVHp.getText()), ivAtk = convert(IVAtk.getText()), ivDef = convert(IVDef.getText()), ivSatk = convert(IVSatk.getText()), ivSdef = convert(IVSdef.getText()), ivSpd = convert(IVSpd.getText());
            int sevHp = convert(setEvHP.getText()), sevAtk = convert(setEvAtk.getText()), sevDef = convert(setEvDef.getText()), sevSatk = convert(setEvSatk.getText()), sevSdef = convert(setEvSdef.getText()), sevSpd = convert(setEvSpd.getText());
            int baHp = convert(BaseHp.getText()), baAtk = convert(BaseAtk.getText()), baDef = convert(BaseDef.getText()), baSatk = convert(BaseSatk.getText()), baSdef = convert(BaseSdef.getText()), baSpd = convert(BaseSpd.getText());
            int hfHp, hfAtk, hfDef, hfSatk, hfSdef, hfSpd;
            hfHp = tallyHP(baHp, ivHp, sevHp, 100);
            hfAtk = (int) (tallyStats(baAtk, ivAtk, sevAtk, 100) * natureList[0]);
            hfDef = (int) (tallyStats(baDef, ivDef, sevDef, 100) * natureList[1]);
            hfSatk = (int) (tallyStats(baSatk, ivSatk, sevSatk, 100) * natureList[2]);
            hfSdef = (int) (tallyStats(baSdef, ivSdef, sevSdef, 100) * natureList[3]);
            hfSpd = (int) (tallyStats(baSpd, ivSpd, sevSpd, 100) * natureList[4]);
            int evC = sevHp + sevAtk + sevDef + sevSatk + sevSdef + sevSpd;
            if (evC <= 510) {
                evCount.setText((510 - evC) + "");
            } else {
                evCount.setText("-" + (evC - 510) + "");
            }
            if (pkmName.getText().equalsIgnoreCase("Shedinja")) {
                totalHP1.setText("1");
            } else {
                totalHP1.setText(hfHp + "");
            }
            totalAtk1.setText(hfAtk + "");
            totalDef1.setText(hfDef + "");
            totalSatk1.setText(hfSatk + "");
            totalSdef1.setText(hfSdef + "");
            totalSpd1.setText(hfSpd + "");
        }
    }
    private void crPkmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crPkmActionPerformed
        if (crPkm.getSelectedIndex() != 0) {
            String pokemonName = crPkm.getSelectedItem().toString();
            String filePath = folderPath + pokemonName.toLowerCase() + "_Stat.txt";
            File file = new File(filePath);
            deleteFile(pokemonName, file);
        }
    }//GEN-LAST:event_crPkmActionPerformed
    private void deleteFile(String pokemonName, File file) {
        if (JOptionPane.showConfirmDialog(this, "Load data of " + pokemonName + "?",
                "Load Data?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            loadSpecificData(pokemonName.toLowerCase());
        }
        if (JOptionPane.showConfirmDialog(this, "Delete the old Data File?",
                "Delete Data?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            crPkm.setSelectedIndex(0);
            for (int i = 0; i < crPkm.getItemCount(); i++) {
                if (crPkm.getItemAt(i).equals(pokemonName)) {
                    crPkm.removeItemAt(i);
                }
            }
            file.delete();
        }
    }
    private void btnLvlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLvlActionPerformed
        int lv = convert(btnLvl.getText()) + 1;
        if (lv > 100) {
            btnLvl.setText((lv - 1) + "");
        } else {
            btnLvl.setText(lv + "");
        }
        tally();
    }//GEN-LAST:event_btnLvlActionPerformed

    private void btnEvolveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEvolveActionPerformed
        if (btnEvolve.getSelectedIndex() != 0) {
            String pokemonName = btnEvolve.getSelectedItem().toString();
            String filePath = folderPath + pokemon.getName().toLowerCase() + "_Stat.txt";
            File file = new File(filePath);
            file.delete();
            for (int i = 0; i < pkm.size(); i++) {
                if (pkm.get(i).getName().equalsIgnoreCase(pokemonName)) {
                    pokemon = pkm.get(i);
                }
            }
            filePath = folderPath + pokemon.getName().toLowerCase() + "_Stat.txt";
            saveCompletedFile(folderPath, filePath, pokemon.getName().toLowerCase());
            Stat newStat = new Stat(pokemon);
            newStat.setVisible(true);
            newStat.setTitle(pokemon.getName() + "'s Stats Tracking");
            newStat.setSize(400, 490);
            this.dispose();
        }
    }//GEN-LAST:event_btnEvolveActionPerformed

    private void RusStateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RusStateActionPerformed
        RusCount++;
        if (RusCount % 2 == 0) {
            Rus = false;
            RusState.setText("Rus OFF");
        } else {
            Rus = true;
            RusState.setText("Rus ON");
        }
    }//GEN-LAST:event_RusStateActionPerformed
    private int checkEvLower() {
        int evHp = convert(crtEvHP.getText()), evAtk = convert(crtEvAtk.getText()), evDef = convert(crtEvDef.getText()), evSatk = convert(crtEvSatk.getText()), evSdef = convert(crtEvSdef.getText()), evSpd = convert(crtEvSpd.getText());
        int sumEV = evHp + evAtk + evDef + evSatk + evSdef + evSpd;
        return sumEV;
    }

    private void tally() {
        if (btnNature.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Select a nature.");
        } else {
            int lv = convert(btnLvl.getText());
            int ivHp = convert(IVHp.getText()), ivAtk = convert(IVAtk.getText()), ivDef = convert(IVDef.getText()), ivSatk = convert(IVSatk.getText()), ivSdef = convert(IVSdef.getText()), ivSpd = convert(IVSpd.getText());
            int evHp = convert(crtEvHP.getText()), evAtk = convert(crtEvAtk.getText()), evDef = convert(crtEvDef.getText()), evSatk = convert(crtEvSatk.getText()), evSdef = convert(crtEvSdef.getText()), evSpd = convert(crtEvSpd.getText());
            int sevHp = convert(setEvHP.getText()), sevAtk = convert(setEvAtk.getText()), sevDef = convert(setEvDef.getText()), sevSatk = convert(setEvSatk.getText()), sevSdef = convert(setEvSdef.getText()), sevSpd = convert(setEvSpd.getText());
            int baHp = convert(BaseHp.getText()), baAtk = convert(BaseAtk.getText()), baDef = convert(BaseDef.getText()), baSatk = convert(BaseSatk.getText()), baSdef = convert(BaseSdef.getText()), baSpd = convert(BaseSpd.getText());
            int fnHp, fnAtk, fnDef, fnSatk, fnSdef, fnSpd;
            int crEv = evHp + evDef + evAtk + evSatk + evSdef + evSpd;
            lblEV.setText(crEv + "");
            int hfHp, hfAtk, hfDef, hfSatk, hfSdef, hfSpd;
            setBtn(sevHp, evHp, btnEvHP);
            setBtn(sevSpd, evSpd, btnEvSpd);
            setBtn(sevAtk, evAtk, btnEvAtk);
            setBtn(sevDef, evDef, btnEvDef);
            setBtn(sevSdef, evSdef, btnEvSdef);
            setBtn(sevSatk, evSatk, btnEvSatk);
            hfHp = tallyHP(baHp, ivHp, evHp, lv);
            hfAtk = tallyStats(baAtk, ivAtk, evAtk, lv);
            hfDef = tallyStats(baDef, ivDef, evDef, lv);
            hfSatk = tallyStats(baSatk, ivSatk, evSatk, lv);
            hfSdef = tallyStats(baSdef, ivSdef, evSdef, lv);
            hfSpd = tallyStats(baSpd, ivSpd, evSpd, lv);
            fnHp = hfHp;
            fnAtk = (int) (hfAtk * natureList[0]);
            fnDef = (int) (hfDef * natureList[1]);
            fnSatk = (int) (hfSatk * natureList[2]);
            fnSdef = (int) (hfSdef * natureList[3]);
            fnSpd = (int) (hfSpd * natureList[4]);
            if (pkmName.getText().equalsIgnoreCase("Shedinja")) {
                totalHP.setText("1");
            } else {
                totalHP.setText(fnHp + "");
            }
            totalAtk.setText(fnAtk + "");
            totalDef.setText(fnDef + "");
            totalSatk.setText(fnSatk + "");
            totalSdef.setText(fnSdef + "");
            totalSpd.setText(fnSpd + "");
            if (checkCompletion()) {
                Status.setText("(Completed)");
            } else {
                Status.setText("");
            }
        }
    }

    boolean checkCompletion() {
        boolean check = false;
        int evHp = convert(crtEvHP.getText()), evAtk = convert(crtEvAtk.getText()), evDef = convert(crtEvDef.getText()), evSatk = convert(crtEvSatk.getText()), evSdef = convert(crtEvSdef.getText()), evSpd = convert(crtEvSpd.getText());
        int sevHp = convert(setEvHP.getText()), sevAtk = convert(setEvAtk.getText()), sevDef = convert(setEvDef.getText()), sevSatk = convert(setEvSatk.getText()), sevSdef = convert(setEvSdef.getText()), sevSpd = convert(setEvSpd.getText());
        int curEV = evHp + evAtk + evDef + evSatk + evSdef + evSpd;
        if (evHp == sevHp && evAtk == sevAtk && evDef == sevDef && evSatk == sevSatk
                && evSdef == sevSdef && evSpd == sevSpd && curEV == 510) {
            check = true;
        }
        return check;
    }

    void setBtn(int s, int r, JButton b) {
        int compare = r - s;
        if (compare > 0) {
            b.setText("+" + (compare));
        } else {
            b.setText("UP");
        }

    }

    private double[] natureTally(String na) {
        double a = 1, d = 1, sa = 1, sd = 1, s = 1;
        if (na.equals("Lonely") || na.equals("Adamant") || na.equals("Naughty") || na.equals("Brave")) {
            a = 1.1;
        }
        if (na.equals("Bold") || na.equals("Impish") || na.equals("Lax") || na.equals("Relaxed")) {
            d = 1.1;
        }
        if (na.equals("Modest") || na.equals("Mild") || na.equals("Rash") || na.equals("Quiet")) {
            sa = 1.1;
        }
        if (na.equals("Calm") || na.equals("Gentle") || na.equals("Careful") || na.equals("Sassy")) {
            sd = 1.1;
        }
        if (na.equals("Timid") || na.equals("Hasty") || na.equals("Jolly") || na.equals("Naive")) {
            s = 1.1;
        }
        if (na.equals("Bold") || na.equals("Modest") || na.equals("Calm") || na.equals("Timid")) {
            a = 0.9;
        }
        if (na.equals("Lonely") || na.equals("Mild") || na.equals("Gentle") || na.equals("Hasty")) {
            d = 0.9;
        }
        if (na.equals("Adamant") || na.equals("Impish") || na.equals("Careful") || na.equals("Jolly")) {
            sa = 0.9;
        }
        if (na.equals("Naughty") || na.equals("Lax") || na.equals("Rash") || na.equals("Naive")) {
            sd = 0.9;
        }
        if (na.equals("Brave") || na.equals("Relaxed") || na.equals("Quiet") || na.equals("Sassy")) {
            s = 0.9;
        }
        double[] tmp = {a, d, sa, sd, s};
        return tmp;
    }

    private int convert(String s) {
        return Integer.parseInt(s);
    }

    private int tallyHP(int b, int i, int e, int l) {
        int rEV = e / 4;
        int br = b * 2 + i + rEV;
        double T = (br * l) / 100;
        return (int) (T + l + 10);
    }

    private int tallyStats(int b, int i, int e, int l) {
        int rEV = e / 4;
        int br = b * 2 + i + rEV;
        double T = (br * l) / 100;
        return (int) (T + 5);
    }

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
            java.util.logging.Logger.getLogger(Stat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Stat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Stat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Stat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Stat st = new Stat(pokemon);
                st.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BaseAtk;
    private javax.swing.JTextField BaseDef;
    private javax.swing.JTextField BaseHp;
    private javax.swing.JTextField BaseSatk;
    private javax.swing.JTextField BaseSdef;
    private javax.swing.JTextField BaseSpd;
    private javax.swing.JButton CountMax;
    private javax.swing.JLayeredPane EvolveLayer;
    private javax.swing.JLabel EvolveText;
    private javax.swing.JTextField IVAtk;
    private javax.swing.JTextField IVDef;
    private javax.swing.JTextField IVHp;
    private javax.swing.JTextField IVSatk;
    private javax.swing.JTextField IVSdef;
    private javax.swing.JTextField IVSpd;
    private javax.swing.JButton RusState;
    private javax.swing.JLabel Status;
    private javax.swing.JButton btnEvAtk;
    private javax.swing.JButton btnEvDef;
    private javax.swing.JButton btnEvHP;
    private javax.swing.JButton btnEvSatk;
    private javax.swing.JButton btnEvSdef;
    private javax.swing.JButton btnEvSpd;
    private javax.swing.JComboBox<String> btnEvolve;
    private javax.swing.JButton btnLvl;
    private javax.swing.JComboBox<String> btnNature;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> crPkm;
    private javax.swing.JTextField crtEvAtk;
    private javax.swing.JTextField crtEvDef;
    private javax.swing.JTextField crtEvHP;
    private javax.swing.JTextField crtEvSatk;
    private javax.swing.JTextField crtEvSdef;
    private javax.swing.JTextField crtEvSpd;
    private javax.swing.JLabel evCount;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lblEV;
    private javax.swing.JLabel pkmName;
    private javax.swing.JTextField setEvAtk;
    private javax.swing.JTextField setEvDef;
    private javax.swing.JTextField setEvHP;
    private javax.swing.JTextField setEvSatk;
    private javax.swing.JTextField setEvSdef;
    private javax.swing.JTextField setEvSpd;
    private javax.swing.JLabel totalAtk;
    private javax.swing.JLabel totalAtk1;
    private javax.swing.JLabel totalDef;
    private javax.swing.JLabel totalDef1;
    private javax.swing.JLabel totalHP;
    private javax.swing.JLabel totalHP1;
    private javax.swing.JLabel totalSatk;
    private javax.swing.JLabel totalSatk1;
    private javax.swing.JLabel totalSdef;
    private javax.swing.JLabel totalSdef1;
    private javax.swing.JLabel totalSpd;
    private javax.swing.JLabel totalSpd1;
    // End of variables declaration//GEN-END:variables
}

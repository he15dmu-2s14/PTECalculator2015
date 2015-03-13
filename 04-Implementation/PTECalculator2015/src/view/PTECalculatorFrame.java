package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.Vinkel;
import exceptions.UgyldigHalvhojdeException;
import exceptions.UgyldigInertiMomentException;
import logic.PTECalculatorController;
import logic.PTECalculatorControllerImpl;
import logic.PTEObserver;
import domain.ArealEnhed;
import domain.Enhed;
import domain.Laengde;
import exceptions.UgyldigArealException;
import exceptions.UgyldigBelastningException;
import exceptions.UgyldigLaengdeException;
import exceptions.UgyldigVinkelException;

public class PTECalculatorFrame extends JFrame implements PTEObserver,
		FocusListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private static final String VANDRET = "Vandret", LODRET = "Lodret";

	private JTextField belastning, belastningFormel, fDim, vinkel;
	private JComboBox<Laengde> laengdeEnhed;
	private JComboBox<Enhed> enhed;
	private JComboBox<ArealEnhed> arealEnhed;
	private JComboBox<String> vandretLodret;
	private JTextField arealIndskrivning, armensLaengdeIndskrivning,
			eIndskrivning, iIndskrivning, tilladeligSpaendingIndskrivning;
	private JTextField fnResultat, ftResultat, arealResultat, tauResultat,
			laengdeResultat, sigmaNResultat, sigmaBojResultat,
			sigmaRefResultat, sikkerhedsfaktorResultat;
	private JTextField fnFormel, ftFormel, tauFormel;
   private Visualizer visualizer;
	private PTECalculatorController pteCalc;

	private DecimalFormat decimalFormatter, kraftFormatter, momentFormatter, spaendingFormatter, laengdeFormatter;

	public PTECalculatorFrame() {
		pteCalc = new PTECalculatorControllerImpl();
		pteCalc.tilmeldObserver(this);

      decimalFormatter = new DecimalFormat("#.##");
		kraftFormatter = new DecimalFormat("#.## N");
      momentFormatter = new DecimalFormat("#.## Nmm");
      spaendingFormatter = new DecimalFormat("#.## N/mm2");
      laengdeFormatter = new DecimalFormat("#.## mm");

		initComponents();
		layoutComponents();

		// settings på framen
		setTitle("PTECalculator");
		setSize(900, 600);
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		update();
	}

	private void initComponents() {
		// Belastning har ActionListener og FoculListener saa det er mulig aa
		// bruke baade tab og enter for aa beregne resultat
		belastning = new JTextField(8);
		belastning.addFocusListener(this);
		belastning.addActionListener(this);

		enhed = new JComboBox<Enhed>();
		enhed.addItem(Enhed.kg);
		enhed.addItem(Enhed.ton);
		enhed.addItem(Enhed.Newton);
		enhed.addActionListener(this);

		laengdeEnhed = new JComboBox<Laengde>();
		laengdeEnhed.addItem(Laengde.mm);
		laengdeEnhed.addItem(Laengde.cm);
		laengdeEnhed.addItem(Laengde.m);
		laengdeEnhed.setPreferredSize(new Dimension(73, 22));
		laengdeEnhed.addActionListener(this);

		arealEnhed = new JComboBox<ArealEnhed>();
		arealEnhed.addItem(ArealEnhed.mm2);
		arealEnhed.addItem(ArealEnhed.cm2);
		arealEnhed.addItem(ArealEnhed.m2);
		arealEnhed.setPreferredSize(new Dimension(70, 22));
		arealEnhed.addActionListener(this);

		belastningFormel = new JTextField(8);
		belastningFormel.setEditable(false);

		fDim = new JTextField(6);
		fDim.setEditable(false);

		// vinkel har ActionListener og FocusListener saa det er mulig aa bruke
		// baade tab og enter for aa beregne resultat
		vinkel = new JTextField(8);
		vinkel.addFocusListener(this);
		vinkel.addActionListener(this);

		vandretLodret = new JComboBox<>();
		vandretLodret.addItem(VANDRET);
		vandretLodret.addItem(LODRET);
		vandretLodret.addActionListener(this);

		fnFormel = new JTextField(8);
		fnFormel.setEditable(false);

		fnResultat = new JTextField(6);
		fnResultat.setEditable(false);

		ftFormel = new JTextField(8);
		ftFormel.setEditable(false);

		ftResultat = new JTextField(6);
		ftResultat.setEditable(false);

		sigmaNResultat = new JTextField(8);
		sigmaNResultat.setEditable(false);

		sigmaBojResultat = new JTextField(8);
		sigmaBojResultat.setEditable(false);

		sigmaRefResultat = new JTextField(8);
		sigmaRefResultat.setEditable(false);

		sikkerhedsfaktorResultat = new JTextField(8);
		sikkerhedsfaktorResultat.setEditable(false);

		arealIndskrivning = new JTextField(8);
		arealIndskrivning.setEditable(true);
		arealIndskrivning.addFocusListener(this);

		armensLaengdeIndskrivning = new JTextField(8);
		armensLaengdeIndskrivning.setEditable(true);
		armensLaengdeIndskrivning.addFocusListener(this);

		tilladeligSpaendingIndskrivning = new JTextField(8);
		tilladeligSpaendingIndskrivning.setEditable(true);
		tilladeligSpaendingIndskrivning.addFocusListener(this);

		eIndskrivning = new JTextField(8);
		eIndskrivning.setEditable(true);
		eIndskrivning.addFocusListener(this);

		iIndskrivning = new JTextField(8);
		iIndskrivning.setEditable(true);
		iIndskrivning.addFocusListener(this);

		arealResultat = new JTextField(10);
		arealResultat.setEditable(false);

		tauFormel = new JTextField(8);
		tauFormel.setEditable(false);

		tauResultat = new JTextField(6);
		tauResultat.setEditable(false);

		laengdeResultat = new JTextField(8);
		laengdeResultat.setEditable(false);

      visualizer = new Visualizer();
      visualizer.setPreferredSize(new Dimension(300, 450));
      visualizer.setOrigin(new Point(25, 275));
      visualizer.setDraggable(true);
	}

	private void layoutComponents() {
		JPanel inputPanel = new JPanel(new GridBagLayout());
      add(inputPanel);

		GridBagConstraints con;
		Insets ins = new Insets(5, 5, 5, 5);

		// Linie 0
		con = createGBC(3, 0, 1, 1);
		con.insets = new Insets(5, 15, 0, 5);
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(new JLabel("Formel"), con);

		con = createGBC(4, 0, 1, 1);
		con.insets = new Insets(5, 5, 0, 5);
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(new JLabel("Fdim"), con);

		// Linie 1
		con = createGBC(0, 1, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(new JLabel("Belastning (Fdim):  "), con);

		con = createGBC(1, 1, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(belastning, con);

		con = createGBC(2, 1, 1, 1);
		con.insets = ins;
      inputPanel.add(enhed, con);

		con = createGBC(3, 1, 1, 1);
		con.insets = new Insets(0, 5, 5, 5);
      inputPanel.add(belastningFormel, con);

		con = createGBC(4, 1, 1, 1);
		con.insets = new Insets(0, 5, 5, 5);
      inputPanel.add(fDim, con);

		// Linie 2
		con = createGBC(0, 2, 1, 1);
		con.insets = new Insets(5, 5, 20, 5);
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(new JLabel("Vinkel: "), con);

		con = createGBC(1, 2, 1, 1);
		con.insets = new Insets(5, 5, 20, 5);
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(vinkel, con);

		con = createGBC(2, 2, 1, 1);
		con.insets = new Insets(5, 5, 20, 5);
      inputPanel.add(vandretLodret, con);

		// Linie 3
		con = createGBC(1, 3, 1, 1);
		con.insets = new Insets(5, 5, 0, 5);
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(new JLabel("Formel"), con);

		con = createGBC(2, 3, 1, 1);
		con.insets = new Insets(5, 5, 0, 5);
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(new JLabel("Resultat"), con);

		// Linie 4
		con = createGBC(0, 4, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(new JLabel("Fn: "), con);

		con = createGBC(1, 4, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(fnFormel, con);

		con = createGBC(2, 4, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(fnResultat, con);

		// Linie 5
		con = createGBC(0, 5, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(new JLabel("Ft: "), con);

		con = createGBC(1, 5, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(ftFormel, con);

		con = createGBC(2, 5, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(ftResultat, con);

		// Linje 6 (ny)
		con = createGBC(0, 6, 5, 1);
		con.insets = new Insets(5, 5, 15, 5);
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(new JLabel(
				"_______________________________________________________________________"),
				con);

		// Linje 7 (ny)
		con = createGBC(0, 7, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(new JLabel("Areal: "), con);

		con = createGBC(1, 7, 1, 1);
		con.insets = ins;
      inputPanel.add(arealIndskrivning, con);

		con = createGBC(2, 7, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(arealEnhed, con);

		con = createGBC(3, 7, 1, 1);
		con.insets = ins;
      inputPanel.add(arealResultat, con);

		con = createGBC(4, 7, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(new JLabel("mm^2"), con);

		// Linje 8 (ny)
		con = createGBC(0, 8, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(new JLabel("tau: "), con);

		con = createGBC(1, 8, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(tauFormel, con);

		con = createGBC(2, 8, 1, 1);
		con.insets = ins;
      inputPanel.add(tauResultat, con);

		// Linie 9 BøjningsMoment
		con = createGBC(0, 9, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(new JLabel("Armens laengde:"), con);

		con = createGBC(1, 9, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(armensLaengdeIndskrivning, con);

		con = createGBC(2, 9, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(laengdeEnhed, con);

		con = createGBC(3, 9, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(laengdeResultat, con);

		// linje 10
		con = createGBC(0, 11, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(new JLabel("SigmaN:"), con);

		// linie 11 IKKE IMPLEMENTERET LOGIK
		con = createGBC(0, 11, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(new JLabel("SigmaN:"), con);

		con = createGBC(1, 11, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(sigmaNResultat, con);

		// linie 12 IKKE IMPLEMENTERET LOGIK
		con = createGBC(0, 12, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(new JLabel("E :"), con);

		con = createGBC(1, 12, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(eIndskrivning, con);

		// linie 13 IKKE IMPLEMENTERET LOGIK
		con = createGBC(0, 13, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(new JLabel("I :"), con);

		con = createGBC(1, 13, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(iIndskrivning, con);

		// Linje 14 IKKE IMPLEMENTERET LOGIK
		con = createGBC(0, 14, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(new JLabel("SigmaBøj :"), con);

		con = createGBC(1, 14, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(sigmaBojResultat, con);

		// linje 15 IKKE IMPLEMENTERET ENDNU!!!!!!
		con = createGBC(0, 15, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(new JLabel("SigmaRef :"), con);

		con = createGBC(1, 15, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(sigmaRefResultat, con);

		// Linje 16 IKKE IMPLEMENTERET ENDNU!!!!!!
		con = createGBC(0, 16, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(new JLabel("Tilladelig Spænding :"), con);

		con = createGBC(1, 16, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(tilladeligSpaendingIndskrivning, con);

		// Linje 17 IKKE IMPLEMENTERET ENDNU!!!!!!
		con = createGBC(0, 17, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(new JLabel("Sikkerhedsfaktor :"), con);

		con = createGBC(1, 17, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
      inputPanel.add(sikkerhedsfaktorResultat, con);

      JPanel visPanel = new JPanel(new BorderLayout());
      add(visPanel, BorderLayout.EAST);
      visPanel.setBorder(BorderFactory.createLoweredBevelBorder());
      visPanel.add(visualizer);
	}

	public GridBagConstraints createGBC(int x, int y, int width, int height) {
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = x;
		gbc.gridy = y;

		gbc.gridwidth = width;
		gbc.gridheight = height;

		return gbc;
	}

	@Override
	public void update() {
		String belastningText = "";

		Enhed enh = (Enhed) enhed.getSelectedItem();
		switch (enh) {
		case kg:
			if (pteCalc.getBelastning() != null)
				belastningText += pteCalc.getBelastning().getBelastningIKg();

			belastning.setText(belastningText);
			break;
		case ton:
			if (pteCalc.getBelastning() != null)
				belastningText += pteCalc.getBelastning().getBelastningITon();

			belastning.setText(belastningText);
			break;
		default:
			if (pteCalc.getBelastning() != null)
				belastningText += pteCalc.getBelastning().getBelastning();

			belastning.setText(belastningText);
			break;
		}

		if (pteCalc.getBelastning() != null) {
			double f = pteCalc.getBelastning().getBelastning();
			fDim.setText(kraftFormatter.format(f));
		}

      Vinkel v = pteCalc.getVinkel();
      if (v != null) {
         vandretLodret.setSelectedItem(v.tilVandret() ? VANDRET : LODRET);
         visualizer.setVinkel(v);
      }

		if (pteCalc.getTvaerkraft() != null) {
			double f = pteCalc.getTvaerkraft().getTvaerkraft();
			ftResultat.setText(kraftFormatter.format(f));
		}

		if (pteCalc.getNormalkraft() != null) {
			double n = pteCalc.getNormalkraft().getNormalkraft();
			fnResultat.setText(kraftFormatter.format(n));
		}

		if (pteCalc.getAreal() != null) {
			double n = pteCalc.getAreal().getAreal();
			arealResultat.setText("" + n);
			double angivetAreal;
			ArealEnhed aEnhed = (ArealEnhed) arealEnhed.getSelectedItem();
			switch (aEnhed) {
			case cm2:
				angivetAreal = pteCalc.getAreal().getArealIcm2();
				break;
			case m2:
				angivetAreal = pteCalc.getAreal().getArealIm2();
				break;
			default:
				angivetAreal = pteCalc.getAreal().getAreal();
				break;
			}
			arealIndskrivning.setText("" + angivetAreal);
		}

		if (pteCalc.getForskydningsspaending() != null) {
			double n = pteCalc.getForskydningsspaending()
					.getForskydningsspaending();
			tauResultat.setText(spaendingFormatter.format(n));
		}

		if (pteCalc.getBojningsmoment() != null) {
			double angivetArmLaengde;
			Laengde lEnhed = (Laengde) laengdeEnhed.getSelectedItem();
			switch (lEnhed) {
			case cm:
				angivetArmLaengde = pteCalc.getBojningsmoment()
						.getArmlaengdeIcm();
				break;
			case m:
				angivetArmLaengde = pteCalc.getBojningsmoment()
						.getArmlaengdeIm();
				break;
			default:
				angivetArmLaengde = pteCalc.getBojningsmoment()
						.getArmlaengdeImm();
				break;
			}
			armensLaengdeIndskrivning.setText("" + angivetArmLaengde);
		}

		if (pteCalc.getBojningsmoment() != null) {
			double n = pteCalc.getBojningsmoment().getBojningsmoment();
			laengdeResultat.setText(laengdeFormatter.format(n));

		}

		if (pteCalc.getNormalspaending() != null) {
			double n = pteCalc.getNormalspaending().getNormalspaending();
			sigmaNResultat.setText(spaendingFormatter.format(n));
		}

      if (pteCalc.getBojningsspaending() != null) {
         double n = pteCalc.getBojningsspaending().getBojningsspending();
         sigmaBojResultat.setText(spaendingFormatter.format(n));
      }

      if (pteCalc.getReferencespaending() != null) {
         double n = pteCalc.getReferencespaending().getReferencespaending();
         sigmaRefResultat.setText(spaendingFormatter.format(n));
      }

      if (pteCalc.getSikkerhedsfaktor() != null) {
         double n = pteCalc.getSikkerhedsfaktor().getSikkerhedsfaktor();
         sikkerhedsfaktorResultat.setText(decimalFormatter.format(n));
      }
	}

	@Override
	public void focusGained(FocusEvent e) {
		// No-op
	}

	@Override
	public void focusLost(FocusEvent e) {
		updateFields(e.getSource());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		updateFields(e.getSource());

	}

	private void updateFields(Object e) {
		if (e == belastning || e == enhed) {
			try {
				if (!belastning.getText().isEmpty()) { // check om belastning er
														// tom. Gir Ugyldig
														// input melding om det
														// ikke checkes
					double b = Double.parseDouble(belastning.getText());
					Enhed enh = (Enhed) enhed.getSelectedItem();
					pteCalc.angivBelastning(b, enh);
				}
			} catch (NumberFormatException | UgyldigBelastningException ex) {
				JOptionPane.showMessageDialog(null, "Ugyldigt input");
			}
		} else if (e == vinkel || e == vandretLodret) {
			try {
				if (!vinkel.getText().isEmpty()) { // check om vinkel er tom.
													// Gir Ugyldig nummer
													// melding om det ikke
													// checkes
					double v = Double.parseDouble(vinkel.getText());
					boolean vandret = vandretLodret.getSelectedItem().equals(
							VANDRET);
					pteCalc.beregnTvaerkraft(v, vandret);
					pteCalc.beregnNormalkraft(v, vandret);
				}
			} catch (UgyldigVinkelException ex) {
				JOptionPane.showMessageDialog(null,
						"Vinkel skal vaere mellem 0 og 90 grader");
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Ugyldig nummer");
			}
		} else if (e == arealIndskrivning || e == arealEnhed) {
			if (!vinkel.getText().isEmpty()) {
            if (!arealIndskrivning.getText().isEmpty()
                    && pteCalc.getNormalkraft() != null) {
               try {
                  double areal = Double.parseDouble(arealIndskrivning
                          .getText());
                  pteCalc.beregnNormalspaending(areal,
                          (ArealEnhed) arealEnhed.getSelectedItem());
                  pteCalc.beregnForskydningsspaending(areal,
                          (ArealEnhed) arealEnhed.getSelectedItem());
                  sigmaNResultat.setText(pteCalc.getNormalspaending().getNormalspaending() + "");
               } catch (UgyldigArealException | NumberFormatException e3) {
                  JOptionPane.showMessageDialog(null,
                          "Ugyldigt Areal. Areal skal være skarpt positivt.");
               }
            }
			}
		} else if (e == armensLaengdeIndskrivning) {
			if (!armensLaengdeIndskrivning.getText().isEmpty()) {
				try {
					double laengde = Double
							.parseDouble(armensLaengdeIndskrivning.getText());
					pteCalc.beregnBojningsmoment(laengde,
							(Laengde) laengdeEnhed.getSelectedItem());
				} catch (UgyldigLaengdeException | NumberFormatException e2) {
					JOptionPane
							.showMessageDialog(null,
									"Ugyldig laengde. Laengde skal være skarpt positivt");
				}
			}
		} else if (e == eIndskrivning || e == iIndskrivning) {
         if (!eIndskrivning.getText().isEmpty() &&
                 !iIndskrivning.getText().isEmpty() &&
                 pteCalc.getBojningsmoment() != null) {
            Double eVal = null, iVal = null;
            try {
               eVal = Double.parseDouble(eIndskrivning.getText());
            } catch (NumberFormatException ex) {
               JOptionPane.showMessageDialog(null, "Ugyldig vaerdi for halvhoejde");
            }
            try {
               iVal = Double.parseDouble(iIndskrivning.getText());
            } catch (NumberFormatException ex) {
               JOptionPane.showMessageDialog(null, "Ugyldig vaerdi for inertimoment");
            }

            if (eVal != null && iVal != null) {
               try {
                  pteCalc.beregnBojningspaending(iVal, eVal);
               } catch (UgyldigHalvhojdeException ex) {
                  JOptionPane.showMessageDialog(null, "Halvhoejde maa ikke vaere negativ");
               } catch (UgyldigInertiMomentException ex) {
                  JOptionPane.showMessageDialog(null, "Inertimoment maa ikke vaere negativ");
               }
            }
         }
      } else if (e == tilladeligSpaendingIndskrivning &&
              !tilladeligSpaendingIndskrivning.getText().isEmpty()) {
         try {
            double n = Double.parseDouble(tilladeligSpaendingIndskrivning.getText());
            pteCalc.beregnSikkerhedsfaktor(n);
         } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Ugyldig vaerdi for tilladelig spaending");
         }
      }
	}

}

package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import domain.ArealEnhed;
import domain.Enhed;
import exceptions.UgyldigArealException;
import exceptions.UgyldigBelastningException;
import exceptions.UgyldigVinkelException;
import logic.PTECalculatorController;
import logic.PTECalculatorControllerImpl;
import logic.PTEObserver;

public class PTECalculatorFrame extends JFrame implements PTEObserver, FocusListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private static final String VANDRET = "Vandret", LODRET = "Lodret";

	private JTextField belastning, belastningFormel, fDim, vinkel;
	private JComboBox<Enhed> enhed;
	private JComboBox<ArealEnhed> arealEnhed;
	private JComboBox<String> vandretLodret;
	private JTextField fnFormel, ftFormel, fnResultat, ftResultat, arealIndskrivning, arealResultat, tauFormel, tauResultat;
	private PTECalculatorController pteCalc;

	private DecimalFormat forceFormatter;

	public PTECalculatorFrame() {
		pteCalc = new PTECalculatorControllerImpl();
		pteCalc.tilmeldObserver(this);

		forceFormatter = new DecimalFormat("#.## N");

		initComponents();
		layoutComponents();

		// settings på framen
		setTitle("PTECalculator");
		setSize(600, 400);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		update();
	}

	private void initComponents() {
		// Belastning har ActionListener og FoculListener saa det er mulig aa
		// bruke baade tab og enter for aa beregne resultat
		belastning = new JTextField(5);
		belastning.addFocusListener(this);
		belastning.addActionListener(this);

		enhed = new JComboBox<Enhed>();
		enhed.addItem(Enhed.kg);
		enhed.addItem(Enhed.ton);
		enhed.addItem(Enhed.Newton);
		enhed.addActionListener(this);

		arealEnhed = new JComboBox<ArealEnhed>();
		arealEnhed.addItem(ArealEnhed.mm2);
		arealEnhed.addItem(ArealEnhed.cm2);
		arealEnhed.addItem(ArealEnhed.m2);
		arealEnhed.addActionListener(this);

		belastningFormel = new JTextField(8);
		belastningFormel.setEditable(false);

		fDim = new JTextField(6);
		fDim.setEditable(false);

		// vinkel har ActionListener og FocusListener saa det er mulig aa bruke
		// baade tab og enter for aa beregne resultat
		vinkel = new JTextField(5);
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

		arealIndskrivning = new JTextField(10);
		arealIndskrivning.setEditable(true);
		arealIndskrivning.addFocusListener(this);

		arealResultat = new JTextField(10);
		arealResultat.setEditable(false);
		
		tauFormel = new JTextField(8);
		tauFormel.setEditable(false);
		
		tauResultat = new JTextField(6);
		tauResultat.setEditable(false);
	}

	private void layoutComponents() {
		getContentPane().setLayout(new GridBagLayout());

		GridBagConstraints con;
		Insets ins = new Insets(5, 5, 5, 5);

		// Linie 0
		con = createGBC(3, 0, 1, 1);
		con.insets = new Insets(5, 5, 0, 5);
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("Formel"), con);

		con = createGBC(4, 0, 1, 1);
		con.insets = new Insets(5, 5, 0, 5);
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("Fdim"), con);

		// Linie 1
		con = createGBC(0, 1, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("Belastning (Fdim):  "), con);

		con = createGBC(1, 1, 1, 1);
		con.insets = ins;
		add(belastning, con);

		con = createGBC(2, 1, 1, 1);
		con.insets = ins;
		add(enhed, con);

		con = createGBC(3, 1, 1, 1);
		con.insets = new Insets(0, 5, 5, 5);
		add(belastningFormel, con);

		con = createGBC(4, 1, 1, 1);
		con.insets = new Insets(0, 5, 5, 5);
		add(fDim, con);

		// Linie 2
		con = createGBC(0, 2, 1, 1);
		con.insets = new Insets(5, 5, 20, 5);
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("Vinkel: "), con);

		con = createGBC(1, 2, 1, 1);
		con.insets = new Insets(5, 5, 20, 5);
		add(vinkel, con);

		con = createGBC(2, 2, 1, 1);
		con.insets = new Insets(5, 5, 20, 5);
		add(vandretLodret, con);

		// Linie 3
		con = createGBC(1, 3, 1, 1);
		con.insets = new Insets(5, 5, 0, 5);
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("Formel"), con);

		// Linie 4
		con = createGBC(0, 4, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("Fn: "), con);

		con = createGBC(1, 4, 1, 1);
		con.insets = ins;
		add(fnFormel, con);

		con = createGBC(2, 4, 1, 1);
		con.insets = ins;
		add(fnResultat, con);

		// Linie 5
		con = createGBC(0, 5, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("Ft: "), con);

		con = createGBC(1, 5, 1, 1);
		con.insets = ins;
		add(ftFormel, con);

		con = createGBC(2, 5, 1, 1);
		con.insets = ins;
		add(ftResultat, con);

		// Linje 6 (ny)
		con = createGBC(1, 6, 1, 1);
		con.insets = new Insets(5, 5, 0, 5);
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("_________"), con);

		// Linje 7 (ny)
		con = createGBC(0, 7, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("Areal: "), con);

		con = createGBC(1, 7, 1, 1);
		con.insets = ins;
		add(arealIndskrivning, con);

		con = createGBC(2, 7, 1, 1);
		con.insets = ins;
		add(arealEnhed, con);

		con = createGBC(3, 7, 1, 1);
		con.insets = ins;
		add(arealResultat, con);

		con = createGBC(4, 7, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("mm^2"), con);
		
		// Linje 8 (ny)
		con = createGBC(0, 8, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("tau: "), con);

		con = createGBC(1, 8, 1, 1);
		con.insets = ins;
		add(tauFormel, con);

		con = createGBC(2, 8, 1, 1);
		con.insets = ins;
		add(tauResultat, con);
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
			fDim.setText(forceFormatter.format(f));
		}

		if (pteCalc.getVinkel() != null)
			vandretLodret.setSelectedItem(pteCalc.getVinkel().tilVandret() ? VANDRET : LODRET);

		if (pteCalc.getTvaerkraft() != null) {
			double f = pteCalc.getTvaerkraft().getTvaerkraft();
			ftResultat.setText(forceFormatter.format(f));
		}

		if (pteCalc.getNormalkraft() != null) {
			double n = pteCalc.getNormalkraft().getNormalkraft();
			fnResultat.setText(forceFormatter.format(n));
		}

		if (pteCalc.getForskydningsspaending() != null) {
			double n = pteCalc.getForskydningsspaending().getForskydningsspaending();
			fnResultat.setText(forceFormatter.format(n));
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
		
		if(pteCalc.getForskydningsspaending() != null) {
			double n = pteCalc.getForskydningsspaending().getForskydningsspaending();
			tauResultat.setText(forceFormatter.format(n));
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
					boolean vandret = vandretLodret.getSelectedItem().equals(VANDRET);
					pteCalc.beregnTvaerkraft(v, vandret);
					pteCalc.beregnNormalkraft(v, vandret);
				}
			} catch (UgyldigVinkelException ex) {
				JOptionPane.showMessageDialog(null, "Vinkel skal være mellem 0 og 90 grader");
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Ugyldig nummer");
			}
		} else if (e == arealIndskrivning || e == arealEnhed) {
			if (!vinkel.getText().isEmpty()) {
				try {
					double areal = Double.parseDouble(arealIndskrivning.getText());
					pteCalc.beregnForskydningsspaending(areal, (ArealEnhed) arealEnhed.getSelectedItem());
				} catch (UgyldigArealException | NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Ugyldigt Areal. Areal skal være skarpt positivt.");
				}
			}
		}
	}

}

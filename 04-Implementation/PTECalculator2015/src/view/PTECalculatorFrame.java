package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import logic.PTECalculatorController;
import logic.PTECalculatorControllerStub;
import logic.PTEObserver;
import logic.UgyldigBelastningException;

public class PTECalculatorFrame extends JFrame implements PTEObserver,
		FocusListener {
	private JTextField belastning, belastningFormel, fDim, vinkel;
	private JComboBox enhed, vandretLodret;
	private JTextField fnFormel, ftFormel, fnResultat, ftResultat;
	private GridBagLayout layout;
	private PTECalculatorController pteCalc;

	public PTECalculatorFrame() {
		setTitle("PTECalculator");

		pteCalc = new PTECalculatorControllerImpl();
		pteCalc.tilmeldObserver(this);

		layout = new GridBagLayout();
		getContentPane().setLayout(layout);

		GridBagConstraints con = new GridBagConstraints();
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
		belastning = new JTextField(4);
		belastning.addFocusListener(this);
		add(belastning, con);

		con = createGBC(2, 1, 1, 1);
		con.insets = ins;
		enhed = new JComboBox();
		enhed.addItem(logic.Enhed.kg);
		enhed.addItem(logic.Enhed.ton);
		enhed.addItem(logic.Enhed.Newton);
		add(enhed, con);

		con = createGBC(3, 1, 1, 1);
		con.insets = new Insets(0, 5, 5, 5);
		belastningFormel = new JTextField(8);
		belastningFormel.setEditable(false);
		add(belastningFormel, con);

		con = createGBC(4, 1, 1, 1);
		con.insets = new Insets(0, 5, 5, 5);
		fDim = new JTextField(5);
		fDim.setEditable(false);
		add(fDim, con);

		// Linie 2
		con = createGBC(0, 2, 1, 1);
		con.insets = new Insets(5, 5, 20, 5);
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("Vinkel: "), con);

		con = createGBC(1, 2, 1, 1);
		con.insets = new Insets(5, 5, 20, 5);
		vinkel = new JTextField(4);
		add(vinkel, con);

		con = createGBC(2, 2, 1, 1);
		con.insets = new Insets(5, 5, 20, 5);
		vandretLodret = new JComboBox();
		vandretLodret.addItem("Vandret");
		vandretLodret.addItem("Lodret");
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
		fnFormel = new JTextField(8);
		fnFormel.setEditable(false);
		add(fnFormel, con);

		con = createGBC(2, 4, 1, 1);
		con.insets = ins;
		fnResultat = new JTextField(4);
		fnResultat.setEditable(false);
		add(fnResultat, con);

		// Linie 5
		con = createGBC(0, 5, 1, 1);
		con.insets = ins;
		con.anchor = GridBagConstraints.WEST;
		add(new JLabel("Ft: "), con);

		con = createGBC(1, 5, 1, 1);
		con.insets = ins;
		ftFormel = new JTextField(8);
		ftFormel.setEditable(false);
		add(ftFormel, con);

		con = createGBC(2, 5, 1, 1);
		con.insets = ins;
		ftResultat = new JTextField(4);
		ftResultat.setEditable(false);
		add(ftResultat, con);

		// settings på framen
		setSize(500, 300);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		update();
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
		switch ((logic.Enhed) enhed.getSelectedItem()) {
		case kg:
			belastning.setText("" + pteCalc.getBelastning().getBelastningIKg());
			break;

		case ton:
			belastning
					.setText("" + pteCalc.getBelastning().getBelastningITon());
			break;

		default:
			belastning.setText("" + pteCalc.getBelastning().getBelastning());
			break;
		}

		fDim.setText("" + pteCalc.getBelastning().getBelastning() + " N");

		if (pteCalc.getVinkel().tilVandret()) {
			vandretLodret.setSelectedItem("Vandret");
		} else
			vandretLodret.setSelectedItem("Lodret");

	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == belastning) {

			try {
				double temp;
				temp = Double.parseDouble(belastning.getText());
				if (temp == Double.NaN || temp == Double.POSITIVE_INFINITY)
					throw new NumberFormatException();

				logic.Enhed unit = (logic.Enhed) enhed.getSelectedItem();

				pteCalc.angivBelastning(temp, unit);

			} catch (NumberFormatException | UgyldigBelastningException e2) {
				JOptionPane.showMessageDialog(null, "Ugyldigt indput");
			}
		}

		if (e.getSource() == vinkel) {
			try {
				double temp;
				temp = Double.parseDouble(vinkel.getText());
				
				if(vandretLodret.getSelectedItem().toString() == "Vandret")
					pteCalc.beregnTvaerkraft(temp, true);
				else
					pteCalc.beregnTvaerkraft(temp, false);
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Ugyldigt indput");
			}
		}
	}
}

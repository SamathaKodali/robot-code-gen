import javax.swing.*;
import java.util.ArrayList;

public class MovementConfig {
	
	JLabel movementNumInstructions;
	JLabel enterMovementNumLabel;
	JTextField numMovementsField;
	JButton movementApplyButton;
	ArrayList<JLabel> movementTypeLabels;
	ArrayList<JComboBox> movementTypeDropdownList;
	int numMovements;
	
	public MovementConfig() {
		movementNumInstructions = new JLabel("Enter the number of routine movements the robot should "
				+ "have and then click Apply. Note that the maximum number of movements "
				+ "available is 10");
		enterMovementNumLabel = new JLabel("Number of movements: ");
		numMovementsField = new JTextField("0");
		movementApplyButton = new JButton("Apply");
		movementTypeLabels = new ArrayList<JLabel>();
		movementTypeDropdownList = new ArrayList<JComboBox>();
		numMovements = 0;
	}
	
	private void reset() {
		
		for (int i = 0; i < numMovements; i++) {
			movementTypeLabels.get(i).setVisible(false);
			movementTypeDropdownList.get(i).setVisible(false);
		}
		
		numMovements = 0;
		movementTypeLabels.clear();
		movementTypeDropdownList.clear();
	}
	
	public void generateMovementSelectionItems() {
		reset();
		numMovements = Integer.parseInt(numMovementsField.getText());
		
		//create the correct number of sensor number labels
		for (int i = 0; i < numMovements; i++) {
			movementTypeLabels.add(new JLabel("Movement " + (i+1) + " Type: "));
		}
		
		//create the correct number of sensor drop downs
		for (int i = 0; i < numMovements; i++) {
			MovementTypeComboBox mtcb = new MovementTypeComboBox();
			movementTypeDropdownList.add(mtcb.getSensorTypeComboBox());
		}
	}
	
	
	//getters for all the components that will go on the panel
	public JLabel getMovementNumInstructions() {
		return movementNumInstructions;
	}
		
	public JLabel getEnterMovementNumLabel() {
		return enterMovementNumLabel;
	}
		
	public JTextField getNumMovementsField() {
		return numMovementsField;
	}
		
	public JButton getMovementsApplyButton() {
		return movementApplyButton;
	}
		
	public JLabel getMovementTypeLabelNumberX(int x) {
		return movementTypeLabels.get(x);
	}
	
	public JComboBox getMovementTypeComboBoxX(int x) {
		return movementTypeDropdownList.get(x);
	}
		
	public int getNumMovements() {
		return numMovements;
	}

}









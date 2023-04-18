import javax.swing.*;
import java.util.ArrayList;

public class MotorConfig {
	
	JLabel motorNumInstructions;
	JLabel enterMotorNumLabel;
	JTextField numMotorsField;
	JButton motorApplyButton;
	ArrayList<JLabel> motorTypeLabels;
	ArrayList<JRadioButton> servoMotorTypeRadioButtons;
	ArrayList<JRadioButton> dcMotorTypeRadioButtons;
	int numMotors;
	
	public MotorConfig() {
		motorNumInstructions = new JLabel("Enter the number of motors the robot should "
				+ "have and then click Apply. Note that the maximum number of motors "
				+ "available is 20");
		enterMotorNumLabel = new JLabel("Number of motors: ");
		numMotorsField = new JTextField("0");
		motorApplyButton = new JButton("Apply");
		motorTypeLabels = new ArrayList<JLabel>();
		servoMotorTypeRadioButtons = new ArrayList<JRadioButton>();
		dcMotorTypeRadioButtons = new ArrayList<JRadioButton>();
		numMotors = 0;
	}
	
	private void reset() {
		
		for (int i = 0; i < numMotors; i++) {
			motorTypeLabels.get(i).setVisible(false);
			servoMotorTypeRadioButtons.get(i).setVisible(false);
			dcMotorTypeRadioButtons.get(i).setVisible(false);
		}
		
		numMotors = 0;
		motorTypeLabels.clear();
		servoMotorTypeRadioButtons.clear();
		dcMotorTypeRadioButtons.clear();
	}
	
	public void generateMotorSelectionItems() {
		reset();
		numMotors = Integer.parseInt(numMotorsField.getText());
		
		//create the correct number of motor number labels
		for (int i = 0; i < numMotors; i++) {
			motorTypeLabels.add(new JLabel("Motor " + (i+1) + " Type: "));
		}
		
		//create the correct number of motor type radio buttons
		for (int i = 0; i < numMotors; i++) {
			servoMotorTypeRadioButtons.add(new JRadioButton("Servo"));
			dcMotorTypeRadioButtons.add(new JRadioButton("DC"));
		}
	}
	
	//getters for all the components that will go on the panel
	
	public JLabel getMotorNumInstructions() {
		return motorNumInstructions;
	}
	
	public JLabel getEnterMotorNumLabel() {
		return enterMotorNumLabel;
	}
	
	public JTextField getNumMotorsField() {
		return numMotorsField;
	}
	
	public JButton getMotorApplyButton() {
		return motorApplyButton;
	}
	
	public JLabel getMotorTypeLabelNumberX(int x) {
		return motorTypeLabels.get(x);
	}
	
	public JRadioButton getServoMotorTypeRadioButtonX(int x) {
		return servoMotorTypeRadioButtons.get(x);
	}
	
	public JRadioButton getDCMotorTypeRadioButtonX(int x) {
		return dcMotorTypeRadioButtons.get(x);
	}
	
	public int getNumMotors() {
		return numMotors;
	}
}












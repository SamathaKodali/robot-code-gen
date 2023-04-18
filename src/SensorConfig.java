import javax.swing.*;
import java.util.ArrayList;

public class SensorConfig {
	
	JLabel sensorNumInstructions;
	JLabel enterSensorNumLabel;
	JTextField numSensorsField;
	JButton sensorApplyButton;
	ArrayList<JLabel> sensorTypeLabels;
	ArrayList<JComboBox> sensorTypeDropdownList;
	int numSensors;
	
	public SensorConfig() {
		sensorNumInstructions = new JLabel("Enter the number of sensors the robot should "
				+ "have and then click Apply. Note that the maximum number of sensors "
				+ "available is 20");
		enterSensorNumLabel = new JLabel("Number of sensors: ");
		numSensorsField = new JTextField("0");
		sensorApplyButton = new JButton("Apply");
		sensorTypeLabels = new ArrayList<JLabel>();
		sensorTypeDropdownList = new ArrayList<JComboBox>();
		numSensors = 0;
	}
	
	private void reset() {
		
		for (int i = 0; i < numSensors; i++) {
			sensorTypeLabels.get(i).setVisible(false);
			sensorTypeDropdownList.get(i).setVisible(false);
		}
		
		numSensors = 0;
		sensorTypeLabels.clear();
		sensorTypeDropdownList.clear();
	}
	
	public void generateSensorSelectionItems() {
		reset();
		numSensors = Integer.parseInt(numSensorsField.getText());
		
		//create the correct number of sensor number labels
		for (int i = 0; i < numSensors; i++) {
			sensorTypeLabels.add(new JLabel("Sensor " + (i+1) + " Type: "));
		}
		
		//create the correct number of sensor drop downs
		for (int i = 0; i < numSensors; i++) {
			SensorTypeComboBox stcb = new SensorTypeComboBox();
			sensorTypeDropdownList.add(stcb.getSensorTypeComboBox());
		}
	}
	
	//getters for all the components that will go on the panel
	public JLabel getSensorNumInstructions() {
		return sensorNumInstructions;
	}
		
	public JLabel getEnterSensorNumLabel() {
		return enterSensorNumLabel;
	}
		
	public JTextField getNumSensorsField() {
		return numSensorsField;
	}
		
	public JButton getSensorApplyButton() {
		return sensorApplyButton;
	}
		
	public JLabel getSensorTypeLabelNumberX(int x) {
		return sensorTypeLabels.get(x);
	}
	
	public JComboBox getSensorTypeComboBoxX(int x) {
		return sensorTypeDropdownList.get(x);
	}
		
	public int getNumSensors() {
		return numSensors;
	}

}











import javax.swing.JComboBox;

public class SensorTypeComboBox {
	
	String[] sensorTypes = {"Ultrasonic", "PIR", "Generic", "Infrared"};
	JComboBox<String> sensorTypeComboBox;
	
	public SensorTypeComboBox() {
		sensorTypeComboBox = new JComboBox<>(sensorTypes);
	}
	
	public JComboBox getSensorTypeComboBox() {
		return sensorTypeComboBox;
	}
}
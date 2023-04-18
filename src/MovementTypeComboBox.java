import javax.swing.JComboBox;

public class MovementTypeComboBox {
	
	String[] movementTypes = {"Motion1", "Motion2", "Motion3"};
	JComboBox<String> movementTypeComboBox;
	
	public MovementTypeComboBox() {
		movementTypeComboBox = new JComboBox<>(movementTypes);
	}
	
	public JComboBox getSensorTypeComboBox() {
		return movementTypeComboBox;
	}
}

import java.awt.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class RobotCodeCreator {
	
	JFrame frame;
	JPanel panel;
	MotorConfig mc;
	SensorConfig sc;
	MovementConfig mov_c;
	JButton generate;
    GenStart gs;
	
	public RobotCodeCreator(GenStart the_gen_start) {

        //set the GenStart member variable
        this.gs = the_gen_start;
		
		//create the frame
		frame = new JFrame();
		
		//create the parts
		mc = new MotorConfig();
		mc.getMotorApplyButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mc.generateMotorSelectionItems();
				
				int numberOfMotors = mc.getNumMotors();
				for (int i = 0; i < numberOfMotors; i++) {
					panel.add(mc.getMotorTypeLabelNumberX(i));
					panel.add(mc.getDCMotorTypeRadioButtonX(i));
					panel.add(mc.getServoMotorTypeRadioButtonX(i));
				}
			}
		});
		
		sc = new SensorConfig();
		sc.getSensorApplyButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sc.generateSensorSelectionItems();
				
				int numberOfSensors = sc.getNumSensors();
				for (int i = 0; i < numberOfSensors; i++) {
					panel.add(sc.getSensorTypeLabelNumberX(i));
					panel.add(sc.getSensorTypeComboBoxX(i));
				}
			}
		});
		
		mov_c = new MovementConfig();
		mov_c.getMovementsApplyButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mov_c.generateMovementSelectionItems();
				
				int numberOfMovements = mov_c.getNumMovements();
				for (int i = 0; i < numberOfMovements; i++) {
					panel.add(mov_c.getMovementTypeLabelNumberX(i));
					panel.add(mov_c.getMovementTypeComboBoxX(i));
				}
			}
		});
		
		generate = new JButton("Generate");
		generate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//close the frame
				frame.dispose();
				createAssembleRobotFunctionDefinitions();
				createRoutineMovementsFunctionDefinitions();
			}
		});
		
		//create the panel
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
		panel.setLayout(new GridLayout(0,1));
		
		panel.add(mc.getMotorNumInstructions());
		panel.add(mc.getEnterMotorNumLabel());
		panel.add(mc.getNumMotorsField());
		panel.add(mc.getMotorApplyButton());
		
		panel.add(sc.getSensorNumInstructions());
		panel.add(sc.getEnterSensorNumLabel());
		panel.add(sc.getNumSensorsField());
		panel.add(sc.getSensorApplyButton());
		
		panel.add(mov_c.getMovementNumInstructions());
		panel.add(mov_c.getEnterMovementNumLabel());
		panel.add(mov_c.getNumMovementsField());
		panel.add(mov_c.getMovementsApplyButton());
		
		panel.add(generate);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Configure Robot");
		frame.pack();
		frame.setVisible(true);
		
	}

	private void createAssembleRobotFunctionDefinitions() {
		
		System.out.println("void SpecificIndusrialRobot::assembleRobot() {");
		System.out.println();
		createAddMotorFunctionCalls();
		createAddSensorFunctionCalls();
		System.out.println("\n}");
	}
	
	
	private void createRoutineMovementsFunctionDefinitions() {
		
		System.out.println("void SpecificIndusrialRobot::routineMovements() {");
		System.out.println();
		createRoutineMovementsFunctionCalls();;
		System.out.println("\n}");
	}

	
	private void createAddMotorFunctionCalls() {
		int numOfMotors = mc.getNumMotors();
		String motorType;
		
		for (int i = 0; i < numOfMotors; i++) {
			if (mc.getDCMotorTypeRadioButtonX(i).isSelected())
			{
				motorType = "SERVO";
			}
			else if (mc.getServoMotorTypeRadioButtonX(i).isSelected())
			{
				motorType = "DC";
			}
			else
			{
				motorType = "ERROR"; 
			}
			
			System.out.println("this->addMotor(\"" + motorType + "\");");
		}
	}
	
	private void createAddSensorFunctionCalls() {
		int numOfSensors = sc.getNumSensors();
		String sensorType;
		
		for (int i = 0; i < numOfSensors; i++) {
			sensorType = String.valueOf(sc.getSensorTypeComboBoxX(i).getSelectedItem());
			System.out.println("this->addSensor(\"" + sensorType + "\");");
		}
	}
	
	private void createRoutineMovementsFunctionCalls() {
		int numOfMovements = mov_c.getNumMovements();
		String movementType;
		
		for (int i = 0; i < numOfMovements; i++) {
			movementType = String.valueOf(mov_c.getMovementTypeComboBoxX(i).getSelectedItem());
			System.out.println("this->addMovement(\"" + movementType + "\");");
		}
	}
}

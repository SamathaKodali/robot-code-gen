import java.awt.*;
import javax.swing.*;
import java.io.File; // Import the File class
import java.io.IOException;


//this class creates a GUI that allows us to take user-input, parses that user-input, and then creates a setup file from it
public class GenStart {

    private MyFileWriter mfw;
    private RobotCodeCreator rcc;
    private JFrame frame;
    private JPanel panel;
    private MotorConfig mc;
    private SensorConfig sc;
    private MovementConfig mov_c;
    private JButton generate;


    //the GenStart() constructor will get the user-input data from a GUI and ... 
    public GenStart()
    {
        //create the frame
        frame = new JFrame();

        //after getting the number of motors, create selection options for each of the motors' types
        motor_type_setup();

        //after getting the number of sensors, create selection options for each of the sensors' types
        sensor_type_setup();
        
        //after getting the number of movements, create movement options for each
        movement_type_setup();
       
        //when the generate button is hit, close the jframe and write the results to the file 
        generate = new JButton("Generate");
        generate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //close the frame
                frame.dispose();
                write_to_robot_industrial_arduino_code();
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



    private void motor_type_setup()
    {
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
    }



    private void sensor_type_setup()
    {
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
    }



    private void movement_type_setup()
    {
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
    }



    private void create_assemble_robot_function_definitions() 
    {

        System.out.println("void SpecificIndusrialRobot::assembleRobot() {");
        System.out.println();
        createAddMotorFunctionCalls();
        createAddSensorFunctionCalls();
        System.out.println("\n}");
    }



    private void create_routine_movements_function_definitions() 
    {

        System.out.println("void SpecificIndusrialRobot::routineMovements() {");
        System.out.println();
        createRoutineMovementsFunctionCalls();
        System.out.println("\n}");
    }



    private void create_add_motor_function_calls() 
    {
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

            mfw.writeLine("R.addMotor(\"" + motorType + "\");");
        }
    }



    private void create_add_sensor_function_calls() 
    {
        int numOfSensors = sc.getNumSensors();
        String sensorType;

        for (int i = 0; i < numOfSensors; i++) {
            sensorType = String.valueOf(sc.getSensorTypeComboBoxX(i).getSelectedItem());
            mfw.writeLine("R.addSensor(\"" + sensorType + "\");");
        }
    }



    private void create_routine_movements_function_calls() 
    {
        int numOfMovements = mov_c.getNumMovements();
        String movementType;

        for (int i = 0; i < numOfMovements; i++) {
            movementType = String.valueOf(mov_c.getMovementTypeComboBoxX(i).getSelectedItem());
            //System.out.println("R.addMovement(\"" + movementType + "\");");
            mfw.writeLine("R.addMovement(\"" + movementType + "\");");
        }
    }



    private void create_includes()
    {
        System.out.println("top of create_includes()");
        try {
            mfw.writeLine("#include \"RobotIndustrial.h\"");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("bottom of create_includes()");
    }



    private void create_member_variables()
    {
        System.out.println("top of create_member_variables()");
        try {
            mfw.writeLine("RobotIndustrial R;");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("top of create_member_variables()");
    }



    private void create_setup()
    {
        System.out.println("top of create_setup()");
        try {
            mfw.writeLine("void setup() {\n");
            create_add_motor_function_calls();
            create_add_routine_movements_function_calls();
            create_add_sensor_function_calls();
            mfw.writeLine("}\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("bottom of create_setup()");
    }



    private void write_to_robot_industrial_arduino_code()
    {
        System.out.println("top of write_to_robot_industrial_arduino_code()");
        try {
            mfw = new MyFileWriter("/Users/samathakodali/Documents/ProfessionalStuff/freelancing/arduino_industrial_robotics_system/write_to/robot_industrial_arduino_code.ino");
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.create_includes();
        this.create_member_variables(); 
        this.create_setup();

        try {
            mfw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("bottom of write_to_robot_industrial_arduino_code()");
    }


    
    //MAIN -- where the program execution is starting
    public static void main(String[] args) {
        System.out.println("top of main()");
        // TODO Auto-generated method stub
        GenStart gen_start = new GenStart();
        System.out.println("bottom of main()");

    }

}

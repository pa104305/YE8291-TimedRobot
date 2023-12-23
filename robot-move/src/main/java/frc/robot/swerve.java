package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class swerve {
    private CANSparkMax driveEngine;
    private CANSparkMax turnEngine;

    private RelativeEncoder driveEncoder;
    private RelativeEncoder turnEncoder;
    // TODO: Agregar aqui el encoder absoluto
    

    public void set_engines(int drive, int turn, MotorType type){
        driveEngine = new CANSparkMax(drive, type);
        turnEngine = new CANSparkMax(turn, type);
        
    }
}
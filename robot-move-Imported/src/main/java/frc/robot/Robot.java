// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
  // Crear la clase personalizada transmision para el movimiento de la transmision
  Transmision drive = new Transmision();
  // Crear el control de xbox, para usar un mando de xbox como mando
  XboxController joy = new XboxController(0);
  // Crear un temporizador, para poder ejecutar acciones basadas en el paso del tiempo
  Timer tmr = new Timer();
  Sensors sensors = new Sensors();
  double[] val;
  @Override
  public void robotInit() {
    drive.set_motor_pos(3, 1, 4, 2, MotorType.kBrushed);
  }

  @Override
  public void robotPeriodic() {
    val = sensors.ll_values();
    SmartDashboard.putNumber("limelight x", val[0]);
    SmartDashboard.putNumber("limelight y", val[1]);
    SmartDashboard.putNumber("limelight a", val[2]);
    SmartDashboard.putNumber("limelight id", val[3]);
  }

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {
    if(val[2] >= 4 & val[2] < 7){
      if(val[2] < 5){
        drive.turn_time(1, 1.8);
      }else{
        drive.move(0.3, 0);
      }
    }else if(val[2] < 4 & val[2] > 0){
      drive.move(-0.3, 0);
    }
  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    if(joy.getAButton()){// Comprobar si se esta presionando el boton A del mando
      drive.turn_time(-1, 1.8); // vuelta direccion y tiempo
    }else if(joy.getBButton()){ // Comprobar si se esta presionando el boton B en el mando
      drive.turn_time(1, 1.8); // vuelta direccion y tiempo
    }else if(joy.getXButton()){ // Comprobar si se esta presionando el boton X en el mando
      drive.set_vel(0.7); // cambiar velocidad
    }else{ // ejecutar si no se esta presionando ninguno de los botones anteriores
      drive.set_vel(0.5); // cambiar velocidad
    }
    drive.move(joy.getLeftY(), joy.getRightX()); // Mover al robot con la velocidad obtenida del joystick
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {
    // Agregar la calibracion para el giroscopio al final del match
  }

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Robot extends TimedRobot {
  // Crear los objetos de controlador spark max e indicar el id del controlador
  // Motor delantero izquierdo (ID 4)
  private final CANSparkMax left_front = new CANSparkMax(4, MotorType.kBrushed);
  // Motor trasero izquierdo (ID 2)
  private final CANSparkMax left_back = new CANSparkMax(2, MotorType.kBrushed);
  // Motor delantero derecho (ID 3)
  private final CANSparkMax right_front = new CANSparkMax(3, MotorType.kBrushed);
  // Motor trasero derecho(ID 1)
  private final CANSparkMax right_back = new CANSparkMax(1, MotorType.kBrushed);
  
  // Crear objeto de grupo de motores izquierdos
  MotorControllerGroup izquierda = new MotorControllerGroup(left_front, left_back);
  // Crear objeto de grupo de motores derechos
  MotorControllerGroup derecha = new MotorControllerGroup(right_front, right_back);

  // Crear el objeto de la transmision con lo que se controlaran todos los
  // motores en un solo objeto
  DifferentialDrive transmission = new DifferentialDrive(izquierda, derecha);
  
  // Crear el control de xbox, para usar un mando de xbox como mando
  XboxController joy = new XboxController(0);

  @Override
  // Pedro sabe
  public void robotInit() {    
    izquierda.setInverted(true);
    left_back.follow(left_front);
    right_back.follow(right_front);
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    /*if(joy.getAButton()){
      left_front.set(0.5);
    }else if(joy.getYButton()){
      left_back.set(0.5);
    }else if(joy.getBButton()){
      right_front.set(0.5);
    }else if(joy.getXButton()){
      right_back.set(0.5);
    }else{
      right_front.set(0);
      left_front.set(0);
    }*/

    transmission.arcadeDrive(joy.getLeftY()*-0.6, joy.getRightX()*-0.6);
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}

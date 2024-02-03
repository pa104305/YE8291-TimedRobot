// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;

public class Robot extends TimedRobot {
  // Crear la clase personalizada transmision para el movimiento de la transmision
  Transmision drive = new Transmision();
  // Crear el control de xbox, para usar un mando de xbox como mando
  XboxController joy = new XboxController(0);
  // Crear un temporizador, para poder ejecutar acciones basadas en el paso del tiempo
  Timer tmr = new Timer();

  @Override
  public void robotInit() {
    // Establecer posiciones de los controladores de cada motor
    // Se establece la posicion indicada para cada motor y encoder en la transmision
    drive.set_modules(MotorType.kBrushless, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);    
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {
    // Si se requiere mayot velocidad durante el autonomo cambiarla con el metodo 'set_vel()'
    //drive.acel_time(1, 5); // Avanzar el robot hacia la direccion y tiempo indicados
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    // Metodo para el movimiento, recibe el giro que tendran las llantas y la velocidad total que se dara
    drive.move(joy.getLeftX(), joy.getLeftY(), joy.getRightTriggerAxis());
    // Girar el robot sobre su eje, recibiendo el valor en x del joystick derecho
    drive.turn(joy.getRightX());
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
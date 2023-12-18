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
    drive.set_motor_pos(4, 2, 3, 1, MotorType.kBrushed);
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {
    // Si se requiere mayot velocidad durante el autonomo cambiarla con el metodo 'set_vel()'
    drive.acel_time(1, 5); // Avanzar el robot hacia la direccion y tiempo indicados
  }

  @Override
  public void autonomousPeriodic() {}

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
    drive.move(joy.getLeftY(), joy.getLeftX()); // Mover al robot con la velocidad obtenida del joystick
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
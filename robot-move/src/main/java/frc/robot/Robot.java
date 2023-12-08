// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
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

  // Crear un temporizador, para poder ejecutar acciones basadas en el paso del tiempo
  Timer tmr = new Timer();

  @Override
  public void robotInit() {
    // Se establecen los motores del lado izquierdo invertidos, para que giren en la misma
    // direccion que los motores derechos al recibir la misma señal
    izquierda.setInverted(true);
    // Se declara que el motor trasero izquierdo sigue al motor delantero izquierdo
    left_back.follow(left_front);
    // Se declara que el motor trasero derecho sigue al motor delantero derecho
    right_back.follow(right_front);
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {
    tmr.stop();
    tmr.reset();
    tmr.start();
    while(tmr.get() < 5){
      transmission.arcadeDrive(0.3, 0);
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    // Variable para la velocidad predeterminada
    double vel = 0.5;
    // Comprobar si se esta presionando el boton A del mando
    if(joy.getAButton()){
      // Detener, reiniciar e iniciar un temporizador
      tmr.stop();
      tmr.reset();
      tmr.start();
      // Bucle que se ejecuta cuando el tiempo transcurrido es menor o igual a 1.8s
      while(tmr.get() <= 1.8){
        // girar la transmision a la izquierda
        transmission.arcadeDrive(0, 0.5);
      }
    }else if(joy.getBButton()){ // Comprobar si se esta presionando el boton B en el mando
      // Detener, reiniciar e iniciar un temporizador
      tmr.stop();
      tmr.reset();
      tmr.start();
      // Bucle que se ejecuta mientras el tiempo transcurrido sea menor o igual a 1.8s
      while(tmr.get() <= 1.8){
        // girar la transmision a la derecha
        transmission.arcadeDrive(0, -0.5);
      }
    }else if(joy.getXButton()){ // Comprobar si se esta presionando el boton X en el mando
      // Cambiar el valor de la variable de velocidad
      vel = 0.7;
    }else{ // ejecutar si no se esta presionando ninguno de los botones anteriores
      // Cambiar al valor predeterminado de la velocidad
      vel = 0.5;
    }

    // Orden para hacer que la transmicion avance, se requiere multiplicar el valor del joystick por
    // un decimal (este decimal definira el limite de velocidad) para que el motor nunca tenga una velocidad de 1
    transmission.arcadeDrive(joy.getLeftY()*-vel, joy.getRightX()*-vel);
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

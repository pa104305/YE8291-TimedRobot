package frc.robot;
// https://docs.revrobotics.com/brushless/spark-max/revlib
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.Timer;

public class Transmision {
  // Declaracion de los objetos de controlador sparkmax
  private CANSparkMax left_front; // controlador izquierdo delantero
  private CANSparkMax left_back; // controlador izquierdo trasero
  private CANSparkMax right_front; // controlador derecho delantero
  private CANSparkMax right_back; // controlador derecho trasero
  // Diferencial
  // DifferentialDrive controller = new DifferentialDrive(left_front, right_front); // transmision robot
  private double vel = -0.5; // Velocidad predeterminada del robot
  // Temporizador
  Timer tmr = new Timer();

  // Metodo para declarar la posicion de los motores
  public void set_motor_pos(int l_f, int l_b, int r_f, int r_b, MotorType type){
    left_front = new CANSparkMax(l_f, type); // posicion del motor y tipo de motor
    left_back = new CANSparkMax(l_b, type); // posicion del motor y tipo de motor
    right_front = new CANSparkMax(r_f, type); // posicion del motor y tipo de motor
    right_back = new CANSparkMax(r_b, type); // posicion del motor y tipo de motor 
    left_front.setInverted(true); // Motores izquierdos invertidos
    left_back.follow(left_front); // Seguir al motor delantero
    right_back.follow(right_front); // Seguir al motor delantero
  }

  // Metodo para cambiar la velocidad del robot
  public void set_vel(double in_vel){
    if(in_vel < 1 && in_vel > -1){ // Velocidad nueva en el rango admitido
      vel = -in_vel; // establecer la nueva velocidad y cambiar el signo
    }
  }

  // Metodo para el movimiento del robot
  public void move(double dir, double turn){
    //controller.arcadeDrive((dir * vel), (turn * vel)); // Ejecutar el movimiento del robot con una multiplicacion
    //group(dir * vel, turn * vel);
    vel = -0.4;
    if(turn < -0.1 | turn > 0.1){
      right_front.set(vel*turn);
      left_front.set((vel*turn)*-1);
    }else{
      right_front.set(vel*dir);
      left_front.set(vel*dir);
    }
  }

  // Metodo para dar una vuelta al robot por tiempo
  public void turn_time(int turn, double time){
    // detener, reiniciar e iniciar un temporizador
    tmr.stop();
    tmr.reset();
    tmr.start();
    // Ejecutar una vuelta por el tiempo indicado
    while(tmr.get() <= time){
      move(0, (vel*turn));
      //controller.arcadeDrive(0, (vel * turn)); // rotar el robot y cambiar la direccion segun una operacion
    }
  }

  // Metodo para avanzar al robot por tiempo indicado
  public void acel_time(int dir, double time){
    // detener, reiniciar e iniciar un temporizador
    tmr.stop();
    tmr.reset();
    tmr.start();
    // Ejecutar un avance por el tiempo asignado
    while(tmr.get() <= time){
      move((vel*dir), 0);
      //controller.arcadeDrive((vel * dir), 0); // avanzar el robot y cambiar la direccion segun una operacion
    }
  }

  private void group(double vel_trs, double turn_trs){
    double side = 0.2;
    double oper;
    if(vel_trs > 0 & turn_trs > 0){
      oper = vel_trs - turn_trs;
      if(oper != 0){
        if(oper < 1 & oper > -1){
          side = vel_trs - turn_trs;
        }
      }
    }else if(vel_trs < 0 & turn_trs < 0){
      oper = vel_trs + (turn_trs * -1); 
      if(oper != 0){
        if(oper < 1 & oper > -1){
          side = vel_trs + (turn_trs * -1);
        }
      }
    }else{
      oper = vel_trs + turn_trs;
      if(oper != 0){
        if(oper < 1 & oper > -1){
          side = vel_trs + turn_trs;
        }
      }
    }
    if(turn_trs > 0){
      right_front.set(side);
      left_front.set(vel_trs);
    }else if(turn_trs < 0){
      right_front.set(vel_trs);
      left_front.set(side);
    }else{
      right_front.set(vel_trs);
      left_front.set(vel_trs);
    }
  }
}
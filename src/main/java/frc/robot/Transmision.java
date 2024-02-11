package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Transmision{
  // Declaracion de los objetos de controlador sparkmax
  private CANSparkMax left_front; // controlador izquierdo delantero
  private CANSparkMax left_back; // controlador izquierdo trasero
  private CANSparkMax right_front; // controlador derecho delantero
  private CANSparkMax right_back; // controlador derecho trasero
  // Diferencial
  DifferentialDrive controller; // transmision robot
  private double vel = -0.8; // Velocidad predeterminada del robot
  // Temporizador
  Timer tmr = new Timer();

  // Metodo para declarar la posicion de los motores
  public void set_motor_pos(int l_f, int l_b, int r_f, int r_b, MotorType type){
    left_front = new CANSparkMax(l_f, type); // posicion del motor y tipo de motor
    left_back = new CANSparkMax(l_b, type); // posicion del motor y tipo de motor
    right_front = new CANSparkMax(r_f, type); // posicion del motor y tipo de motor
    right_back = new CANSparkMax(r_b, type); // posicion del motor y tipo de motor 
    left_front.setInverted(true); // Motores izquierdos invertidos
    // Indicar los motores superiores de la transmision
    controller = new DifferentialDrive(left_front, right_front);
  }

  public void follows(){
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
    controller.arcadeDrive(dir*vel, turn*vel); // Mover el robot en relacion al joystick
  }

  // Metodo para dar una vuelta al robot por tiempo
  public void turn_time(int turn, double time){
    // detener, reiniciar e iniciar un temporizador
    tmr.stop();
    tmr.reset();
    tmr.start();
    // Ejecutar una vuelta por el tiempo indicado
    while(tmr.get() <= time){
      controller.arcadeDrive(0, (vel * turn)); // rotar el robot y cambiar la direccion segun una operacion
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
      controller.arcadeDrive((vel * dir), 0); // avanzar el robot y cambiar la direccion segun una operacion
    }
  }
}
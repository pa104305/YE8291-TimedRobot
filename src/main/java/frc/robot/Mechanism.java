package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

//import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

public class Mechanism {
    // Climber

    // Motores 1 y 2 del shooter
    private CANSparkMax eng1_sho;
    private CANSparkMax eng2_sho;
    // Motor del intake
    private VictorSP eng1_int;
    // Velocidad del giro de los motores
    private double shot_vel = 0.7;
    
    // Cambiar la velocidad de los motores
    public void set_shot_vel(double in_vel){
        if(in_vel > 0 & in_vel <= 1){ // Comprobar que la velocidad esta en el rango admitido
            shot_vel = in_vel; // Establecer la nueva velocidad
        }
    }

    // Establecer la posicion de los motores
    public void set_shooter_eng(int eng1, int eng2, MotorType type){
        eng1_sho = new CANSparkMax(eng1, type); // Establecer la posicion de los motores
        eng2_sho = new CANSparkMax(eng2, type); // del shooter
    }

    // Disparar el aro desde el shooter
    public void shot(){
        eng1_sho.set(shot_vel); // Establecer la velocidad en los motores
        eng2_sho.set(shot_vel); // en sentido positivo (+)
    }

    // Meter el aro en el shooter
    public void collect(){
        eng1_sho.set(-shot_vel); // Establecer la velocidad en los motores
        eng2_sho.set(-shot_vel); // en sentido negativo (-)
    }

    // Establecer la posicion del intake
    public void set_intake_eng(int eng){
        eng1_int = new VictorSP(eng); // Establecer la posicion del motor del intake
    }

    // Soltar el aro del intake
    public void let(){
        eng1_int.set(shot_vel*0.25); // Establecer velocidad en el intake positiva
    }

    // Tomar el aro con el intake
    public void take(){
        eng1_int.set(-shot_vel*0.25); // Establecer velocidad en el intake negativa
    }

    // Establecer la velocidad de los motores en 0
    public void stop(){
        eng1_int.set(0);
        eng1_sho.set(0);
        eng2_sho.set(0);
    }
}
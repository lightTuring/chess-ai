#include <Stepper.h>

const int stepsPerRevolution = 100; 

Stepper motors[2] = {
  Stepper(stepsPerRevolution, 4,6,5,7),
  Stepper(stepsPerRevolution, 8,10,9,11),
};

void setup(){

  for(int i = 0; i < 2; i++)
    motors[i].setSpeed(300);
  
  Serial.begin(9600);

}

void loop(){
  void rotate(int motor, char sense);
  void axis_xy(int step_x, int step_y);
}

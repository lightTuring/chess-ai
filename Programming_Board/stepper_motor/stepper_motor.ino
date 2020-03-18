#include <Stepper.h>

const int stepsPerRevolution = 100; 
int lastHouse_x = 0;
int lastHouse_y = 0;
int lengthOfHouse = 3;// valor em CM

Stepper motors[2] = {
  Stepper(stepsPerRevolution, 4,6,5,7),
  Stepper(stepsPerRevolution, 8,10,9,11)
};

void setup(){

  for(int i = 0; i < 2; i++)
    motors[i].setSpeed(300);
  
  Serial.begin(9600);

}

void loop(){
  void rotate(int motor, char sense);
  void axis_xy(int step_x, int step_y);
  int metricToRotation(int distance_cm);

  if(Serial.available() > 0){
    String input_movement = Serial.readString();
   
    axis_xy(metricToRotation(lengthOfHouse * (((int)input_movement[0] - (int)'0') - lastHouse_x)), metricToRotation(lengthOfHouse * (((int)input_movement[1] - (int)'0') - lastHouse_y)));

    lastHouse_x = ((int)input_movement[0] - (int)'0');
    lastHouse_y = ((int)input_movement[1] - (int)'0');   
  }
  Serial.write(0);//this value is like a finalize...
}

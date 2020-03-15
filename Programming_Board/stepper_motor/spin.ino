#define CM 600

//distância fixa.
void rotate(int motor, char sense){
  //frente
  if(sense == 'F' || sense == 'f'){
    motors[motor].step(-600);
  }
  //trás
  else if(sense == 'B' || sense == 'b'){
    motors[motor].step(600);
  }
  delay(500);
}
//movimento no eixo x y cartesiano. Defina um valor positivo ou negativo
void axis_xy(int step_x, int step_y){
  motors[0].step(step_x);
  motors[1].step(step_y);
}

int metricToRotation(int distance_cm){
  return distance_cm * CM;
}

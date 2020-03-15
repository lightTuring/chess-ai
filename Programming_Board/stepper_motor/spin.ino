void spinh(int motor, char sense){
  //frente
  if(sense == 'F' || sense == 'f'){
    motores[motor].step(-600);
  }
  //trás
  else if(sense == 'B' || sense == 'b'){
    motores[motor].step(600);
  }
  delay(500);
}
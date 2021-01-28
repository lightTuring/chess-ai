/*
	Código da leitura dos pinos estrutura robótica
    @authors: Felipe Santos, Lucas Absalão, Pedro Barros e Pedro Victor.
    
    * Componentes:
	- Arduino Mega
    - Reed switch
 
*/

#include <Stepper.h>
#include <stdint.h>
#define u64 int64_t

int casas[8][8];//pinos das casas
char letras[8] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
int x = 0;

const int stepsPerRevolution = 100; 
int lastHouse_x = 0;
int lastHouse_y = 0;
int lengthOfHouse = 3;// valor em CM

Stepper motors[2] = {
  Stepper(stepsPerRevolution, 4,6,5,7),
  Stepper(stepsPerRevolution, 8,10,9,11)
};

void setup(){

  for(int i=0;i<8;i++)
    for(int j=0;j<8;j++)
        pinMode(casas[i][j], OUTPUT);
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

u64 createBoard() {
  for (int i = 0; i<64; i++)
      for (int j = 0; j<64; j++)
        digitalRead(casas[i][j]);
}

//Imprime a imagem do estado do tabuleiro
void printImage(){
  for(int i=0;i<=8;i++) {
    for(int j=0;j<=8;j++){
      if(i==8) Serial.println(letras[x]);
      else{
        Serial.print(digitalRead(casas[i][j]));
          Serial.print(" ");
      }
    }
    x++;
  }
  for(int i=1;i<=8;i++) {
    Serial.print(i);
  }
  Serial.println();
}

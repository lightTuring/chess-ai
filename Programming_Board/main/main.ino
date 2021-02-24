/*
  Código da leitura dos pinos estrutura robótica
    @authors: Felipe Santos, Lucas Absalão, Pedro Barros e Pedro Victor.
    
    * Componentes:
  - Arduino Mega
    - Reed switch
 
*/

//declarar o servo na porta 6
//atualizar a biblioteca do motor de passo

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

#define len 8
#define GREEN_LED 12
#define RED_LED 13

typedef struct Pair{
   int x, y;
}pair;

typedef struct Translator{
  char letters[8] = {'A','B','C','D','E', 'F', 'G', 'H'};
  int numbers[8] = {8,7,6,5,4,3,2,1};

  int binary_search(char element){
      int r = sizeof(letters)/sizeof(char) - 1;
      int l = 0;
      while(l<=r){
        int mid = (l+r)/2;
        if(element==letters[mid]) return mid;
        else if((int)letters[mid]<(int)element) l = mid + 1;
        else if((int)letters[mid]>(int)element) r = mid - 1;
      }
      return -1;
  }
  
  pair NotationChessToComputer(char pos_w, int pos_h) {
    
    if((int)pos_w > 90)
      pos_w = (char)((int)pos_w - ((int)'a' - (int)'A'));
    
    pair OrderedPair;

    for (int i = 0; i < sizeof(numbers)/sizeof(int); i++) {
      if(pos_h == numbers[i])
        OrderedPair.x = i;
        break;
    }
    OrderedPair.y = binary_search(pos_w);
    
    return OrderedPair;
    
  }

  String NotationComputerToChess(int i, int j) {
    
    String OrderedPair = letters[j] + String(binary_search(numbers[i]));
    
    return OrderedPair;
    
  }
  
}translator;


typedef struct Board{
  const int board[len][len] ={};//Matriz com os pinos do tabuleiro
                             
  int stateBoard[len][len]; //Matriz com o estado de cada pino 
  int lastState[len][len]; 
  
  pair lastMovement;//pair com o último movimento
  pair nowMovement;//pair com a jogada realizada
 
  bool turn = true;
  
  void updateBoard(){
    for(int i=0;i<len;i++)
      for(int j=0;j<len;j++)
        stateBoard[i][j] = digitalRead(board[i][j]);
  }
  
  void init(){
    for(int i=0;i<len;i++)
      for(int j=0;j<len;j++)
        pinMode(board[i][j], INPUT);
    updateBoard();
    Serial.begin(9600);
    pinMode(GREEN_LED, OUTPUT);
    pinMode(RED_LED, OUTPUT);
  }
  
  bool capture(){   
    for(int i=0;i<len;i++)
      for(int j=0;j<len;j++)
        lastState[i][j] = stateBoard[i][j];
      
    updateBoard();
    int countLast = 0;
    int countState = 0;
    for (int i = 0; i<len; i++) for(int j=0;j<len;j++){
        countLast+=(int)lastState[i][j];
        countState+=(int)stateBoard[i][j];
    }
    if (countState<countLast) return true;
    return false;
  }

  bool movement(){
    for(int i=0;i<len;i++)
      for(int j=0;j<len;j++)
        lastState[i][j] = stateBoard[i][j];
      
    updateBoard();
    bool cond = false;
    int i = 0;
    int j = 0;  
    for(i=0;i<len;i++) {
      for(j=0;j<len;j++){
        if(lastState[i][j] == HIGH && lastState[i][j] != stateBoard[i][j]){
          cond = true;
          break;
        }
      }
      if(cond) break;
    }
      while(cond){
        updateBoard();
        for (int m=0;m<len;m++) {
          for(int n=0;n<len;n++){
            if(m==i&&n==j) continue;
            if (lastState[m][n]==LOW && lastState[m][n] != stateBoard[m][n]){
              lastMovement.x = i;
              lastMovement.y = j;
              nowMovement.x = m;
              nowMovement.y = n;
              turn = !turn;
              return true;
            }
            if (lastState[m][n]==HIGH && lastState[m][n] != stateBoard[m][n]){
              nowMovement.x = i;
              nowMovement.y = j;
              lastMovement.x = m;
              lastMovement.y = n;
              turn = !turn;
              return true;
            }
          }
        }
      }  
     
    return false;
  }
  
  void printStateBoard(){
    for(int i=0;i<len;i++){ 
       for(int j=0;j<len;j++){
        Serial.print(stateBoard[i][j]);
            Serial.print(" ");
      }
      Serial.println();
    }
    Serial.println();    
  }
  void clearState(){
    lastMovement.x = -1;
    lastMovement.y = -1;
    nowMovement.x = -1;
    nowMovement.y = -1;
  }
  bool backGame(){
    for(int i=0;i<len;i++) for(int j=0;j<len;j++){
      if(lastState[i][j]!=stateBoard[i][j]) return false; 
    }        
    return true;
  }
  
}tab;

tab chess;

void setup(){

  for(int i=0;i<8;i++)
    for(int j=0;j<8;j++)
        pinMode(casas[i][j], OUTPUT);
  for(int i = 0; i < 2; i++)
    motors[i].setSpeed(300);
  
  Serial.begin(9600);
  chess.init();
}

void loop(){
  void rotate(int motor, char sense);
  void axis_xy(int step_x, int step_y);
  int metricToRotation(int distance_cm);
  chess.updateBoard(); 
  chess.printStateBoard();

  //movimento das pretas
  if(!chess.turn){
    while(!chess.turn){
      //mover
        if(Serial.available() > 0){
          String input_movement = Serial.readString();
         
          axis_xy(metricToRotation(lengthOfHouse * (((int)input_movement[0] - (int)'0') - lastHouse_x)), 
                  metricToRotation(lengthOfHouse * (((int)input_movement[1] - (int)'0') - lastHouse_y)));
      
          lastHouse_x = ((int)input_movement[0] - (int)'0');
          lastHouse_y = ((int)input_movement[1] - (int)'0');   
        }
      chess.movement(); 
      chess.turn = true;
    }
  }
  //movimento das brancas
  else if(chess.turn) {   
    while(!chess.movement()){
      Serial.println("JOGUE CONDENADOOOOOOO");
    }
   chess.turn = false;//segurança
  }
}
u64 createBoard() {
  u64 board = 0LL;
  for (int i = 0; i<64; i++)
    board += digitalRead(casas[i/8][i%8]) * (1LL << i);
  
  return board;
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

/*
  Código da leitura dos pinos estrutura robótica
    @authors: Felipe Santos, Lucas Absalão, Pedro Barros e Pedro Victor.
    
    * Componentes:
    - Arduino Mega
    - Reed switch
 
*/

#include <Stepper.h>
#define u64 long long

int casas[8][8];//pinos das casas
char letras[8] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
int x = 0;

const int stepsPerRevolution = 100;
int lastHouse_x = 0;
int lastHouse_y = 0;
int lengthOfHouse = 3;// valor em CM

int positionOfBit(u64);

Stepper motors[2] = {
  Stepper(stepsPerRevolution, 4,6,5,7),
  Stepper(stepsPerRevolution, 8,10,9,11)
};

#define len 8
#define GREEN_LED 12
#define RED_LED 13

int positionOfBit(u64 x) {
  int sq = 0;
  while (x != 0LL) {
    x>>=1;
    sq++;
  }
  return (sq - 1);
}


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
  const int board[len][len] = {};//Matriz com os pinos do arduino para representar o tabuleiro
  
  // peão 0 e 1; cavalo: 2 e 3; bispo: 4 e 5; torre: 6 e 7; rainha: 8 e 9; rei: 10 e 11; brancas: 12; pretas: 13; tabuleiro: 14.
  u64 stateBoard; //Matriz com o estado de cada pino 
  u64 lastState;
  char boardChar[8][8];
  char lastBoardChar[8][8];

  pair lastMovement;//pair com o último movimento
  pair nowMovement;//pair com a jogada realizada
 
  bool turn = true;
  
  void updateBoard(){
    for (int i = 0; i<64; i++)
      stateBoard |= digitalRead(board[i/len][i%len]) << i;
  }
  
  void init(){
    for(int i=0; i<len; i++)
      for(int j=0; j<len; j++)
        pinMode(board[i][j], INPUT);
    updateBoard();
    Serial.begin(9600);
    pinMode(GREEN_LED, OUTPUT);
    pinMode(RED_LED, OUTPUT);
  }
  
  bool capture(){   
    lastState = stateBoard;
      
    updateBoard();
    int countLast = 0;
    int countState = 0;
    u64 a = stateBoard;
    u64 b = lastState;

    while (a) {
      a = a & (a-1);
      countState++;
    }
    while (b) {
      b = b & (b-1);
      countLast++;
    }
    
    if (countState<countLast) return true;
    return false;
  }

  bool movement(){
    lastState = stateBoard;
    int sqin;
    int sqout;
    updateBoard();
    bool cond = false;
    //verificando se houve movimento
    //retorna qual o quadrado inicial e final (caso não haja captura)
    if (lastState != stateBoard) {
      cond = true;
      u64 k = lastState ^ stateBoard;
      u64 ini = k & lastState;
      u64 fin = k & stateBoard;

      sqin = positionOfBit(ini);
    }

    while(cond){
      updateBoard();
      for (int m=0;m<len;m++) {
        for(int n=0;n<len;n++) {
          if(m==i && n==j) continue;
          if (lastState[m][n]==LOW && lastState[m][n] != stateBoard[m][n]) {
            lastMovement.x = i;
            lastMovement.y = j;
            nowMovement.x = m;
            nowMovement.y = n;
            turn = !turn;
            return true;
          }
          if (lastState[m][n]==HIGH && lastState[m][n] != stateBoard[m][n]) {
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
    turn = !turn;
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

  //movimento das brancas
  if(chess.turn){
    while(chess.turn){
      //mover
        if(Serial.available() > 0){
          String input_movement = Serial.readString();
         
          axis_xy(metricToRotation(lengthOfHouse * (((int)input_movement[0] - (int)'0') - lastHouse_x)), metricToRotation(lengthOfHouse * (((int)input_movement[1] - (int)'0') - lastHouse_y)));
      
          lastHouse_x = ((int)input_movement[0] - (int)'0');
          lastHouse_y = ((int)input_movement[1] - (int)'0');   
        }
      chess.movement(); 
    }
  }
  //movimento das pretas
  else if(!chess.turn) {   

    /*
     * MI -> MOVIMENTO ILEGAL
     * CP -> CAPTURA DE PEÇA
     * MS -> MOVIMENTO SIMPLES(TROCA DE CASA)
     * 
     */
   
    String in = Serial.readString();//Trocar pra comunicação Socket
    
    if(in == "MI"){
      while(!chess.backGame()){
        digitalWrite(GREEN_LED, LOW);
        digitalWrite(RED_LED, HIGH);
      }
      digitalWrite(GREEN_LED, HIGH);
      digitalWrite(RED_LED, LOW);     
    }else if(in == "CP"){
      
    }else if(in == "MS"){
      
    }
    chess.turn = true;
  }
  
  delay(5);


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

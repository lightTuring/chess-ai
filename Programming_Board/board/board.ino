#define len 8

struct Board{
  const int board[len][len] ={};//Matriz com os pinos do tabuleiro
                              
  int stateBoard[len][len]; //Matriz com o estado de cada pino 
  int lastMovement[2];//pair com o último movimento
  int nowMovement[2];//pair com a jogada realizada
  
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
  }
    
  bool movement(){
    int lastState[len][len];
    
    for(int i=0;i<len;i++)
      for(int j=0;j<len;j++)
        lastState[i][j] = stateBoard[i][j];
      
    updateBoard();
    bool cond = false;
      
    for(int i=0;i<len;i++) for(int j=0;j<len;j++){
      if(lastState == HIGH && lastState[i][j] != stateBoard[i][j]){
        lastMovement[0] = i;
        lastMovement[1] = j;
        cond = true;
      }
    }
    if(cond){
      for(int i=0;i<len;i++) for(int j=0;j<len;j++){
        if(lastState == LOW && lastState[i][j] != stateBoard[i][j]){
          nowMovement[0] = i;
          nowMovement[1] = j;
          return true;
        }
      }  
    }
   
    return false;
  }
  
  int getLMAxis_X(){
    return lastMovement[0];
  }
  int getLMAxis_Y(){
    return lastMovement[1];
  }
  int getNMAxis_X(){
    return nowMovement[0];
  }
  int getNMAxis_Y(){
    return nowMovement[1];
  }
  
  void printBoard(){
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
    lastMovement[0] = -1;
    lastMovement[1] = -1;
    nowMovement[0] = -1;
    nowMovement[1] = -1;
  }
  
};

#define tab struct Board

tab chess;

void setup(){
  chess.init();
}
void loop(){
  chess.updateBoard(); 
  chess.printBoard();
  delay(5);
}

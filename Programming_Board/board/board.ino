#define len 8

struct Board{
  const int board[len][len] ={0,0,0,0,
                              0,0,0,0,
                              0,0,0,0,
                              0,0,0,0};//Matriz com os pinos do tabuleiro
                              
  int stateBoard[len][len]; //Matriz com o estado de cada pino 
  int lastMovement[2];//pair com o último movimento

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
    
  void movement(){
    int lastState[len][len];
    
    for(int i=0;i<len;i++)
      for(int j=0;j<len;j++)
        lastState[i][j] = stateBoard[i][j];
      
    updateBoard();
    
    for(int i=0;i<len;i++) for(int j=0;j<len;j++){
      if(lastState[i][j] != stateBoard[i][j]){
        lastMovement[0] = i;
        lastMovement[1] = j;
        goto end_t;
      }
    }
    end_t:
    return;
  }
  
  int getAxis_X(){
    return lastMovement[0];
  }
  int getAxis_Y(){
    return lastMovement[1];
  }
  void printBoard(){
    for(int i=0;i<len;i++){ 
       for(int j=0;j<len;j++){
        Serial.print(board[i][j]);
            Serial.print(" ");
      }
      Serial.println();
    }
        
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
}

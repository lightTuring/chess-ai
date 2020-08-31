struct Board{
  int board[8][8] ={0,0,0,0,
                    0,0,0,0,
                    0,0,0,0,
                    0,0,0,0};//Matriz com os pinos do tabuleiro
                    
  int stateBoard[8][8]; //Matriz com o estado de cada pino 
  int lastMovement[2];//pair com o último movimento
  
  void updateBoard(){
    for(int i=0;i<8;i++)
      for(int j=0;j<8;j++)
        stateBoard[i][j] = digitalRead(board[i][j]);
  }
  
  void movement(){
    int lastState[8][8];
    
    for(int i=0;i<8;i++)
      for(int j=0;j<8;j++)
        lastState[i][j] = stateBoard[i][j];
      
    updateBoard();
    
    for(int i=0;i<8;i++) for(int j=0;j<8;j++){
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
  
} tab;

void setup(){
  
}
void loop(){
  
}

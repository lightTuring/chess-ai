#define len 8

typedef struct Pair{
   int x, y;
}pair;

typedef struct Board{
  const int board[len][len] ={};//Matriz com os pinos do tabuleiro
                             
  int stateBoard[len][len]; //Matriz com o estado de cada pino 
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
  }
  
  bool capture(){
    int lastState[len][len];
    
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
  int lastState[len][len];
  
  for(int i=0;i<len;i++)
    for(int j=0;j<len;j++)
      lastState[i][j] = stateBoard[i][j];
    
  updateBoard();
  bool cond = false;
  int i = 0;
  int j = 0;  
  for(int i=0;i<len;i++) for(int j=0;j<len;j++){
    if(lastState[i][j] == HIGH && lastState[i][j] != stateBoard[i][j]){
      cond = true;
      break;
    }
  }
    while(cond){
      updateBoard();
      for (int m=0;m<len && m!=i;m++) for(int n=0;n<len && n!=j;n++){
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
  turn = !turn;
  return false;
}

  /*bool movement(){
    int lastState[len][len];
    
    for(int i=0;i<len;i++)
      for(int j=0;j<len;j++)
        lastState[i][j] = stateBoard[i][j];
      
    updateBoard();
    bool cond = false;
      
    for(int i=0;i<len;i++) for(int j=0;j<len;j++){
      if(lastState[i][j] == HIGH && lastState[i][j] != stateBoard[i][j]){
        lastMovement[0] = i;
        lastMovement[1] = j;
        cond = true;
      }
    }
    if(cond){
      for(int i=0;i<len;i++) for(int j=0;j<len;j++){
        if(lastState[i][j] == LOW && lastState[i][j] != stateBoard[i][j]){
          nowMovement[0] = i;
          nowMovement[1] = j;
          turn = !turn;
          return true;
        }
      }  
    }
    turn = !turn;
    return false;
  }
  */
  
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
  
}tab;

tab chess;

void setup(){
  chess.init();
}
void loop(){
  chess.updateBoard(); 
  chess.printStateBoard();

  if(chess.turn){
    //movimento das brancas
    while(chess.turn){
      
    }
  }else {
    //movimento das pretas
  }
  
  delay(5);
}

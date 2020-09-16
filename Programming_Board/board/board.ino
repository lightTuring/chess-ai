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
  chess.init();
}
void loop(){
  chess.updateBoard(); 
  chess.printStateBoard();

  //movimento das brancas
  if(chess.turn){
    while(chess.turn){
      //mover
      chess.movement(); 
    }
  }
  //movimento das pretas
  if(!chess.turn) {   

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

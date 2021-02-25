String lastMsg = "";

int buttons[8][7]; 

void setup() {
  Serial.begin(11250);
  for(int i=0;i<8;i++){
    for(int j=0;j<7;j++) pinMode(buttons[i][j], INPUT);
  }
}

void loop() {
  Serial.print(make_msg());
  delay(100);
}

String make_msg(){
  String ans = "";
  for(int i=0;i<8;i++){
    for(int j=0;j<7;j++) ans+=String(digitalRead(buttons[i][j]));
  }
  return ans;
}

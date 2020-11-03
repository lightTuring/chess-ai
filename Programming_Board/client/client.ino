#include <Ethernet.h>
#include <SPI.h>

#define PORTAO 5005

byte mac[] = {0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED};//Esta informação depende do shield
byte ip[] = {127, 0, 0, 1};//IP do meu PC

//EthernetClient client;

EthernetServer server(PORTAO);
boolean myTurn = true;


void setup(){
  Ethernet.begin(mac, ip);
  server.begin();
  Serial.begin(9600);
  delay(1000);
}

void loop(){
  ConnectedAI();
}
void ConnectedAI(){
  EthernetClient client = server.available();
  if (client) {
    Serial.println("new client");
    while (client.connected()) {
       if(!myTurn){
        if (client.available()) {
          char c = client.read();
          if(c=='E'){//End
            break;
          }
          if(c == 'n'){
            //MOVIMENTO DA MÁQUINA        
          }
          
        }
      }else{
        //LER O MOVIMENTO DO JOGADOR

        String movement = "";

        client.print(movement);
      }
    }
    
    delay(1);
    // close the connection:
    client.stop();
  }
}

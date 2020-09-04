#include <Ethernet.h>
#include <SPI.h>

#define PORT 5005

byte mac[] = {0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED};//Esta informação depende do shield
byte ip[] = {127, 0, 0, 1};//IP do meu PC

EthernetClient client;

void setup(){
  Ethernet.begin(mac, ip);
  Serial.begin(9600);
  delay(1000);
  
  if (client.connect(ip, PORT)) {
    Serial.println("connected");
    client.println("GET /search?q=arduino HTTP/1.0");
    client.println();
  } else {
    Serial.println("connection failed");
  }
}

void loop()
{
  if(client.available()) {
    String c = client.readString();//Talvez deixe lento
    Serial.print(c);
  }

  if(!client.connected()) {
    Serial.println();
    Serial.println("disconnecting.");
    client.stop();
    return;
  }
}

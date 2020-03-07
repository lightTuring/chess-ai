# AI

Nesta pasta encontramos os códigos referentes a inteligência artificial do projeto. Contêm os seguintes pacotes.:

## Rules

## Server

Neste pacote está contido o arquivo 'Server.java', que faz o Server da nossa comunicação Socket com a placa que faz a ponte com o hardware. Em Server.java está disposto os seguintes métodos:

```java
public Server(int channel);
```
- Método construtor da classe. Recebe como argumento o canal da comunicação.

```java
public void setMoviment(int xo, int yo, int xf, int yf);
```
- Método que define a mensagem que deseja ser enviada. Os parâmetros são as coordenadas inicias e as finais.

```java
 public void sendMoviment();
```
- Método que envia a mensagem para o Client.

```java
public void finalizeCommunication();
```
- Finaliza a comunicação.


## Teste

Este pacote apresenta código apenas para fins de teste.
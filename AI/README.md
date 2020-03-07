# AI

Nesta pasta encontramos os códigos referentes a inteligência artificial do projeto. Contêm os seguintes pacotes.:

## Rules

O pacote apresenta classes de criação e manipulação do tabuleiro e das peças de xadrez.

### Board

Classe que cria o tabuleiro e realiza algumas operações analogas aos métodos presentes nela.

```java
public Board();
```
- Método construtor da classe.

```java
public boolean isWhite(int pos_i, int pos_j);
```
- Retorna um boolean dizendo se a peça na posição _pos_i_ e _pos_j_ é branca ou não.

```java
public boolean isBlack(int pos_i, int pos_j);
```
- Retorna um boolean dizendo se a peça na posição _pos_i_ e _pos_j_ é preta ou não.

```java
public boolean ItsMyColor(int me_i, int me_j, int that_piece_i, int that_piece_j);
```
- Retorna se duas peças apresentam a mesma cor.

```java
public void changePos(int begin_x, int begin_y, int final_x, int final_y);
```
- Chamada pública do método de troca.

```java
private void setChange(int begin_x, int begin_y, int final_x, int final_y);
```
- Realiza a operação de troca de posição entre duas peças.

```java
public char getPiece(int pos_x, int pos_y);
```
- Retorna a peça presente na posição passada pelo argumento.

```java
public void printImage();
```
- Imprime uma imagem do tabuleiro no terminal.

```java
public boolean getHasWhiteKingMoved();
```
- Retorna se o rei branco deu o seu primeiro movimento.

```java
public boolean setHasWhiteKingMovedAsTrue();
```
- Declara que o rei branco deu o seu primeiro movimento.

```java
public boolean getHasWhiteTowerMoved();
```
- Retorna se a torre branca deu o seu primeiro movimento.

```java
public boolean setHasWhiteTowerMovedAsTrue();
```
- Declara que a torre branca deu o seu primeiro movimento.

```java
public boolean getHasBlackKingMoved();
```
- Retorna se o rei preto deu o seu primeiro movimento.

```java
public boolean setHasBlackKingMovedAsTrue();
```
- Declara que o rei preto deu o seu primeiro movimento.

```java
public boolean getHasBlackTowerMoved();
```
- Retorna se a torre preta deu o seu primeiro movimento.

```java
public boolean setHasBlackTowerMovedAsTrue();
```
- Declara que a torre preta deu o seu primeiro movimento.


```java
public boolean hasPawnMoved(int pos_i, int pos_j);
```
- Retorna se determinado(por suas coordenadas) peão deu o seu primeiro movimento.

```java
public ArrayList<Coordinate> indexOfPiece(char b);
```
- Retorna um _ArrayList_ das coordenadas das peças passada no argumento.

### Controller

Contêm métodos que retorna um _ArrayList_ de _Coordinate_ apresentando as possiveis posições finais que cada peça pode jogar. São exemplos dessa classe os métodos:

```java
public ArrayList<Coordinate> getPawnMoves(byte i, byte j);
```
- Para a peça: Peão.

```java
public ArrayList<Coordinate> getTowerMoves(byte i, byte j);
```
- Para a peça: Torre.

```java
public ArrayList<Coordinate> getKnightMoves(byte i, byte j);
```
- Para a peça: Cavalo.

```java
public ArrayList<Coordinate> getBishopMoves(byte i, byte j);
```
- Para a peça: Bispo.

```java
public ArrayList<Coordinate> getKingMoves(byte i, byte j);
```
- Para a peça: Rei.

### Coordinate

Objeto que apresenta a posição inicial e final de determinada peça. 

```java
public Coordinate(int initial_i, int initial_j, int final_i, int final_j);
```
- Construtor.

```java
public void printCoordinate();
```
- Imprime no terminal as coordenadas inicias e finais.

### BoardOutOfBoundsException

### UnexpectedPieceException


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
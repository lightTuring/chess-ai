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
public char[][] getBoard();
```
- Retorna o array char da posição atual

```java
public boolean isWhite(int pos_i, int pos_j);
```
- Retorna um boolean dizendo se a peça na posição _pos_i_ e _pos_j_ é branca ou não.

```java
public boolean isBlack(int pos_i, int pos_j);
```
- Retorna um boolean dizendo se a peça na posição _pos_i_ e _pos_j_ é preta ou não.

```java
public boolean hasSameColor(int me_i, int me_j, int that_piece_i, int that_piece_j);
```
- Retorna se duas peças apresentam a mesma cor.

```java
public void changePos(int begin_x, int begin_y, int final_x, int final_y);
```
- Chamada pública do método de troca.

```java
private void setChange(int begin_x, int begin_y, int final_x, int final_y);
```
- Realiza a operação de troca genérica de posição entre duas peças.

```java
public void doBlacksCastling(int y);
```
- Método de roque para as pretas. Pede um y para definir qual das duas possiblidades de roque vai ser feita.

```java
public void doWhitesCastling(int y);
```
- Método de roque para as brancas. Pede um y para definir qual das duas possiblidades de roque vai ser feita.

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
public boolean getHasRightWhiteRookMoved();
```
- Retorna se a torre branca da direita deu o seu primeiro movimento.

```java
public boolean getHasLeftWhiteRookMoved();
```
- Retorna se a torre branca da esquerda deu o seu primeiro movimento.

```java
public boolean setHasRightWhiteRookMovedAsTrue();
```
- Declara que a torre branca da direita deu o seu primeiro movimento.

```java
public boolean setHasLeftWhiteRookMovedAsTrue();
```
- Declara que a torre branca da esquerda deu o seu primeiro movimento.

```java
public boolean getHasBlackKingMoved();
```
- Retorna se o rei preto deu o seu primeiro movimento.

```java
public boolean setHasBlackKingMovedAsTrue();
```
- Declara que o rei preto deu o seu primeiro movimento.

```java
public boolean getHasRightBlackRookMoved();
```
- Retorna se a torre preta da direita deu o seu primeiro movimento.

```java
public boolean getHasLeftBlackRookMoved();
```
- Retorna se a torre preta da esquerda deu o seu primeiro movimento.

```java
public boolean setHasRightBlackRookMovedAsTrue();
```
- Declara que a torre preta da direita deu o seu primeiro movimento.

```java
public boolean setHasLeftBlackRookMovedAsTrue();
```
- Declara que a torre preta da esquerda deu o seu primeiro movimento.

```java
public boolean hasPawnMoved(int pos_i, int pos_j);
```
- Retorna se determinado(por suas coordenadas) peão deu o seu primeiro movimento.

```java
public ArrayList<Coordinate> indexOfPiece(char b);
```
- Retorna um _ArrayList_ das coordenadas das peças passada no argumento.

```java
public boolean isAPiece(int pos_i, int  pos_j);
```
- Retorna um _boolean_ informando se nas posições passadas no argumento, há ou não uma peça.

```java
public byte[] HowManyKindOfAttacks(ArrayList<Coordinate> movements);
```
- Retorna um array de _byte_ informando em qual jogada pode ocorrer algum tipo de ataque. É passado como argumento um _ArrayList_ de _Coordinate_ dos possíveis movimentos que uma peça pode realizar. Cada elemento do array pode apresentar os seguintes valores:

|Valor do Retorno|   Significado  | 
|------|--------|
|  0   |Movimentação normal, troca de casa|
|  1   |Captura de uma peça|
|  2   |Xeque|


```java
public boolean WillOccurCapture(ArrayList<Coordinate> movements);
```
- Retorna um _boolean_ informando se pode ocorrer uma captura de peça ou não. É passado como argumento um _ArrayList_ de _Coordinate_ dos possíveis movimentos que uma peça pode realizar.

```java
public boolean WillOccurCheck(ArrayList<Coordinate> movements);
```
- Retorna um _boolean_ informando se pode ocorrer xeque ou não. É passado como argumento um _ArrayList_ de _Coordinate_ dos possíveis movimentos que uma peça atacante pode realizar.

```java
public boolean WillOccurCheckMate(ArrayList<Coordinate> ... movements);
```
- Retorna um _boolean_ informando se pode ocorrer um xeque mate ou não. É passado como argumento um _var args_ de _ArrayList_ de _Coordinate_ dos possíveis movimentos que um conjunto de peças atacantes podem realizar.

```java
public Coordinate WherePlayToCheck(ArrayList<Coordinate> movements);
```
- Retorna um objeto _Coordinate_ da jogada do xeque.

```java
public boolean IsCheckMateInBlackKing(ArrayList<Coordinate> movements);
```
- Retorna se está se está ocorrendo um xeque-mate no Rei preto. É passado como argumento dois _ArrayList_ de _Coordinate_ das posições dos cavalos(de preferência o que está na posição de ataque), ou um valor _null_ caso não há ataque dos cavalos.

```java
public boolean IsCheckMateInWhiteKing(ArrayList<Coordinate> movements);
```
- Retorna se está se está ocorrendo um xeque-mate no Rei branco. É passado como argumento dois _ArrayList_ de _Coordinate_ das posições de dos cavalos(de preferência o que está na posição de ataque), ou um valor _null_ caso não há ataque dos cavalos.

```java
private boolean IsCheckMate(ArrayList<Coordinate> movements, char b);
```
- Algoritmo geral para a verificação se há xeque-mate no Rei. É passado como argumento os mesmos _ArrayList_ de _Coordinate_ das posições dos cavalos, ou um valor _null_ caso não há ataque dos cavalos e um _char_ representando o rei. 

#### obs: os últimos 9 métodos são chamados nos métodos públicos do xeque-mate

### Position

- Por enquanto não faz nada de mais;

### Controller

Contêm métodos que retorna um _ArrayList_ de _Coordinate_ apresentando as possiveis posições finais que cada peça pode jogar. São exemplos dessa classe os métodos:

```java
public ArrayList<Coordinate> getPawnMoves(int i, int j);
```
- Para a peça: Peão.

```java
public ArrayList<Coordinate> getRookMoves(int i, int j);
```
- Para a peça: Torre.

```java
public ArrayList<Coordinate> getKnightMoves(int i, int j);
```
- Para a peça: Cavalo.

```java
public ArrayList<Coordinate> getBishopMoves(int i, int j);
```
- Para a peça: Bispo.

```java
public ArrayList<Coordinate> getKingMoves(int i, int j);
```
- Para a peça: Rei.

```java
public ArrayList<Coordinate> getQueenMoves(int i, int j);
```
- Para a peça: Rainha.

```java
private static ArrayList<Coordinate> rookGen (int pos_i, int pos_j, Board b);
```
- Implementa o algoritmo da Torre.

```java
private static ArrayList<Coordinate> bishopGen (int pos_i, int pos_j, Board b);
```
- Implementa o algoritmo do Bispo.

#### OBS.: Os dois últimos métodos são estáticos que implementam os algoritmos para a movimentação do bispo e da torre (e consequentemente da rainha).

### Coordinate

Objeto que apresenta a posição inicial e final de determinada peça. 

```java
public Coordinate(int final_i, int final_j);
```
- Construtor.

```java
public void printCoordinate();
```
- Imprime no terminal as coordenadas inicias e finais.

### BoardOutOfBoundsException

Exceçao a ser implementada onde ArrayOutOfBounds seria lançada para Board.chessBoard.

```java
public BoardOutOfBoundsException(String message);
```
- Construtor.

### UnexpectedPieceException

Exceção a ser lançada quando os argumentos dados a um método controle se referem a uma peça diferente à esperada pelo código do método.

```java
public UnexpectedPieceException(String message);
```
- Construtor.

### IllegalCastlingException

Exceção a ser lançada quando doWhitesCastling ou doBlacksCastling são chamadas mas as condições para o roque de uma dada cor não são atendidas.

```java
public IllegalCastlingException(String message);
```
- Construtor.

## Algorithm
Neste pacote está disposto a implementação de alguns algoritmos que ajudam na construção da I.A(Inteligência Artificial).

### Graph
Esta classe cria o [Grafo](https://pt.wikipedia.org/wiki/Teoria_dos_grafos) das implementações dos algoritmos.

```java
public Graph(int nodes);
```
- Método construtor, é passado como argumento a quantidade de nós presentes no grafo.

```java
public void addElements(int node, int ... elements);
```
- Método de adição de elementos em determinado nó. O primeiro parâmetro é o nó a ser atribuido, o segundo é um _VarArgs_ que correspondem aos elementos que estão em conexão ao nó.

```java
public ArrayList<Integer>[] getGraphConnections();
```
- Retorna o grafo.

```java
public void putWeight(int node, int weight);
```
- Coloca os pesos no Grafo. É passado como argumento o nó e os pesos.

```java
public int getWeight(int node);
```
- Retorna o peso do nó passado pelo argumento.

```java
public int[] getAllWeights();
```
- Retorna todos os pesos apresentado em cada nó.

```java
public void putMoviment(int node, Coordinate c);
```
- Atribui um objeto _Coordinate_ no nó passado como argumento.

```java
public Coordinate getMoviment(int node);
```
- Retorna um objeto _Coordinate_ presente no nó passado como argumento.

```java
public int getAllDepth();
```
- Retorna a profundidade máxima do Grafo.

```java
public int getDepth(int node);
```
- Retorna a profundidade do nó passado no argumento.

### DFS

Está classe está a implementação do [algoritmo de busca por profundidade](https://pt.wikipedia.org/wiki/Busca_em_profundidade)(DFS) e para a construção/manipulação do Grafo encontramos os seguintes métodos:

```java
public DFS(ArrayList<Integer>[] graph, int nodes, boolean[] visited);
```
- Método construtor. Recebe três argumentos; um array de _ArrayList_ representando o Grafo, a primeira dimensão refere-se ao nó e a segunda à um elemento adjacente(nó conectivo); a quantidade de nós totais; uma lista dos nós visitados.

```java
public void print();
```
- Imprime o Grafo no terminal.

```java
public Integer Search(int node, int elementForSearch);
```
- Realiza a busca em profundidade. É passado como argumento o nó e o elemento de busca e retornado o próprio nó de busca, entretando, o retorno corresponde ao elemento do _ArrayList_ bi-dimensional.

```java
public Set<Integer> getPath();
```
- Retorna uma lista do tipo _Set_ representando o caminho liquido do início até o fim do nó de busca.

```java
public ArrayList<Integer> getPathProcess();
```
- Retorna uma lista do tipo _ArrayList_ representando o caminho bruto do início até o fim do nó de busca, ou seja, este método retorna com precisão por todos os nós que o algoritmo de busca passou e por quantas vezes.

## Server

Neste pacote está contido o arquivo _'Server.java'_, que faz o Server da nossa comunicação Socket com a placa que faz a ponte com o hardware. Em Server.java está disposto os seguintes métodos:

### Server

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

## Notation
Pacote de notações do Xadrez. Contém uma classe que realiza a tradução de notações.

### Translator
Realiza a tradução da notação do Xadrez para a do computador, e a recíproca é válida.

```java
public static int[] NotationChessToComputer(char pos_w, int pos_h);
```
- Realiza a tradução da notação do Xadrez para a do computador, passando dois argumentos referentes à casa e retorna um _array_ de _int_ com dois elementos correspondentes às dimensões dos elementos da matriz análoga ao tabuleiro.

```java
public static String NotationComputerToChess(int i, int j);
```
- Realiza a tradução da notação do computador para a do Xadrez, passando dois _int_ referentes às dimensões da matriz e retorna uma _String_ análoga a casa do tabuleiro.

## Teste

Este pacote apresenta código apenas para fins de teste.

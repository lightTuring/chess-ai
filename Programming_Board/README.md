# Programming_Board

Pacote referente à manipulação do Arduino, o microcontrolador deste projeto.

## stepper_motor

Arquivo _main_ do pacote. Aqui há a declaração do _array_ dos motores de passo e  a abertura e manipulação da _Serial_.

## spin

Apresenta métodos de manipulação referentes ao giro dos motores de passos.

```cpp
void rotate(int motor, char sense);
```

- Método de rotação fixa de 90º graus.

```cpp
void axis_xy(int step_x, int step_y);
```

- Método de movimentação nos eixos _x_ e _y_. É passado como argumentos os passos do motor.

```cpp
int metricToRotation(int distance_cm);
```

- Método que retorna um _int_ referente ao valor convertido do sistema métrico para o valor de rotações do motor.

## board

A ideia deste arquivo é armazenar/descrever o estado das casas(pinos) do tabuleiro. Isto é representado numa _struct_ e dentro dela podemos encontrar os seguintes métodos e atributos:

```cpp
typedef struct Board{
  //code
}board;
```

- Estrutura do tabuleiro

### Atributos:

```cpp
const int board[len][len] = {};
```

- Matriz com os pinos do tabuleiro

 ```cpp
int stateBoard[len][len];
```

- Matriz com o estado de cada pino

```cpp
  int lastState[len][len];
```

- Matriz com o último estado de cada pino. É importande para a movimentação das peças.

```cpp
pair lastMovement;
```

- Par com a coordenada inicial da jogada.

```cpp
pair nowMovement;
```

- Par com a coordenada da jogada.

```cpp
bool turn = true;
```

- Turno de cada jogador. _True_ para as brancas, _false_ para as pretas.

#### OBs: Este objeto pair não é padrão do Arduino. Ele foi implementando no código.

```cpp
typedef struct Pair{
   int x, y;
}pair;
```

### Métodos:

```cpp
void updateBoard();
```

- Atualiza o estado do tabuleiro.

```cpp
void init();
```

- Inicia a nossa estrutura.


```cpp
bool capture();
```

- Verifica se ocorreu uma captura(método depreciado).

```cpp
bool movement();
```

- Verifica se o usuário movimentou uma peça.

```cpp
void printStateBoard();
```

- Imprime no monitor serial o estado do tabuleiro.

```cpp
void clearState();
```

- Limpa o estado dos pares de movimentação da estrutura, setando como -1 os seus valores.

```cpp
bool backGame();
```

- Retorna se o jogo voltou ao estado anterior.
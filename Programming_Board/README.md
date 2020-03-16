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
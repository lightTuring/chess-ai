# Bridge

Esta pasta contêm todos os arquivos de comunicação entre a inteligência artificial e a microcontroladora.

## Client 
Realiza uma comunicação _Socket_ com a inteligência artificial, sendo o _Client_ desse canal. Ao importar o arquivo, automaticamente é feita a comunicação, assim sobra apenas dois métodos para a manipulação do servidor _Socket_.

```py
def read_socket()
```
- Faz a leitura do _byte_ enviado.

```py
def finalize()
```
- Finaliza a comunicação.

## communication_serial
O arquivo remete a comunicação _Serial_ entre o Python e a microcontroladora. Aqui há uma classe chamada Board, apresentando métodos para a manipulação do objeto.

```py
class Board:
    def __init__(self, port_arduino, baud_rate)
```
- Método construtor da classe. É passado como argumento o nome da porta e o _baud rate_ da comunicação _Serial_.

```py
def begin(self)
```
- Inicia a comunicação.

```py
def read(self)
```
- Faz a leitura de um _byte_, enviado pela microcontroladora.

```py
def send(self, command)
```
- Envia o _byte_ passado como argumento.

```py
def finalize(self)
```
- Finaliza a comunicação.

## main
Realiza a comunicação entre o servidor _Socket_ e a _Serial_.
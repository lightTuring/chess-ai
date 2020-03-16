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

## main
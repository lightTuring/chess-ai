import Client as client
import communication_serial as little_board

#it's just an example. Change please the name mainly for job with SO Windown and Linux
name_board = '/dev/ttyUSB0'
value_of_baudRate= 9600

b = little_board.Board(name_board, value_of_baudRate)

b.begin()

while True:
    #receiving the command
    movement = client.read_socket()

    #After change the value of comparation

    #byte from send. It's a form to say 'you can send... '
    if b.read() == b'\x01\r\n':
        b.send(movement)
    #another kind of byte from send
    elif b.read() == b'\x01\r\n':
        break

client.finalize()
b.finalize()
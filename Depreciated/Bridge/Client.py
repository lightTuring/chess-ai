import socket

host_ip = "127.0.0.1"
port = 5006

s = socket.socket() 
s.connect((host_ip, port))

def read_socket():
    return s.recv(1024)

def finalize():
    s.close()


return_socket = read_socket()

print(str(return_socket))
print(type(return_socket)) 

finalize()

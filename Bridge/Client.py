import socket

host_ip = "127.0.0.1"
port = 5005

def read_socket():
    s = socket.socket() 
    s.connect((host_ip, port))

    return s.recv(1024)

    s.close()

'''
return_socket = read_socket()

print(return_socket)
print(type(return_socket)) 

'''
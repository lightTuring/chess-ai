import socket

s = socket.socket() 

host_ip = "127.0.0.1"
port = 5005

s.connect((host_ip, port))

return_socket = s.recv(1024)

print(return_socket)
print(type(return_socket)) 

s.close() 
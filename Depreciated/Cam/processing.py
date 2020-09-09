import cv2
import numpy as np 

def ThereIsAnObject(img):
    sum = 0
    for i in range(img.shape[0]):
        for j in range(img.shape[1]):
            sum+=img[i][j]
    return (sum != 0)

img = cv2.imread("board.png")

gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)

ret, corners = cv2.findChessboardCorners(gray, (7, 7), None)
edges = cv2.Canny(gray, 50, 100)
ret_c, corners_c = cv2.findChessboardCorners(edges, (7, 7), None)

r = cv2.findContours(edges,cv2.RETR_TREE,cv2.CHAIN_APPROX_SIMPLE)

cv2.drawChessboardCorners(img, (7, 7), corners, ret)
cv2.drawChessboardCorners(edges, (7, 7), corners_c, ret_c)

print(ret)
#print(r)
print(corners)
print(int(corners[8][0][0]))
house = edges[int(corners[0][0][0])+3:int(corners[8][0][0])-3, int(corners[0][0][1])+3:int(corners[8][0][1])-3]
print(ThereIsAnObject(house))

cv2.imshow("House", house)
cv2.imshow("Board", img)
cv2.imshow("canny", edges)

cv2.waitKey(0)
cv2.destroyAllWindows()
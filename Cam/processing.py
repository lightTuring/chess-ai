import cv2
import numpy as np 

img = cv2.imread("tab.png")

gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)

ret, corners = cv2.findChessboardCorners(gray, (7, 7), None)
edges = cv2.Canny(gray, 50, 100)
ret_c, corners_c = cv2.findChessboardCorners(edges, (7, 7), None)

cv2.drawChessboardCorners(img, (7, 7), corners, ret)
cv2.drawChessboardCorners(edges, (7, 7), corners_c, ret_c)

print(ret)
#print(float(corners[0][0][0]))

cv2.imshow("Board", img)
cv2.imshow("canny", edges)

cv2.waitKey(0)
cv2.destroyAllWindows()
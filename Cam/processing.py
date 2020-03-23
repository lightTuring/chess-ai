import cv2
import numpy as np 

img = cv2.imread("tab_pecas.jpeg")

gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)

ret, corners = cv2.findChessboardCorners(gray, (7, 7), None)
edges = cv2.Canny(gray, 50, 100)

cv2.drawChessboardCorners(img, (7, 7), corners, ret)

print(ret)

cv2.imshow("Board", img)
cv2.imshow("canny", edges)

cv2.waitKey(0)
cv2.destroyAllWindows()
import cv2

img = cv2.imread("tab.jpeg")

cv2.imshow("Board", img)


cv2.waitKey(0)
cv2.destroyAllWindows()
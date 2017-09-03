
CSG mysphere = new Sphere(40).toCSG()
CSG mycube = new Cube(40,60,80).toCSG()

CSG movedCube = mycube.movex(50).movey(50)
mysphere = mysphere.movez(50)

return [ mysphere, mycube, movedCube ]
//Your code here
import com.neuronrobotics.bowlerstudio.vitamins.Vitamins;

// Set a specific repository for the vitamins
Vitamins.setGitRepoDatabase("https://github.com/madhephaestus/Hardware-Dimensions.git")

ArrayList<String> types = Vitamins.listVitaminTypes()
println "\n\nAll availible types: "+types

chassisLen = 300
chassisWidth = 200
chassisHeight = 20
CSG chassis = new Cube(chassisLen, chassisWidth, chassisHeight).toCSG()
chassis = chassis.rot(0,0,90)
chassis = chassis.move(0,0,221*0.89138)

CSG rocker1 = new Cube(221, 10, 5).toCSG()
CSG rocker2 = rocker1.rot(0,0,90)

rocker1 = rocker1.move(221/2,10/2,0)
rocker2 = rocker2.move(5/2,221/2,0)

rocker = rocker1.union(rocker2)
rocker = rocker.rot(90,-26.95,0)
rocker = rocker.move(0,0, 221*0.89138) // 221*cos(26.95)
rocker = rocker.move(68.4761,0,0)

CSG bogie1 = new Cube(134, 10, 5).toCSG()
CSG bogie2 = bogie1.rot(0,0,90)

bogie1 = bogie1.move(134/2,10/2,0)
bogie2 = bogie2.move(5/2,134/2,0)

bogie = bogie1.union(bogie2)
bogie = bogie.rot(90,-45,0)
bogie = bogie.move(0,0,(134/2.power(1/2)))
bogie = bogie.move((196.9999 + 68.4761),0,0)

CSG mechanism = rocker.union(bogie)
mechanism1 = mechanism.rot(0,0,90).move(100,68.4761,0)
mechanism2 = mechanism.rot(0,0,90).move(-100,68.4761,0)

CSG wheel = new Cylinder(94.7532/4,94.7532/4,5,(int)30).toCSG()
wheel = wheel.rot(0,90,0)

wheel1 = wheel.move(100+5+2.5,68.4761+94.7532/4,0)
wheel2 = wheel.move(-(100+2.5),68.4761+94.7532/4,0)
wheel3 = wheel.move(100+5+2.5, -(94.7523+196.9999),0)
wheel4 = wheel.move(-(100+2.5), -(94.7523+196.9999),0)
wheel5 = wheel.move(100+5+2.5, -(196.9999-94.7532),0)
wheel6 = wheel.move(-(100+2.5), -(196.9999-94.7532),0)

robot = chassis.union(mechanism1, mechanism2, wheel1, wheel2, wheel3, wheel4, wheel5, wheel6)


return [robot]
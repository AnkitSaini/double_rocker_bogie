//File linkFile = ScriptingEngine.fileFromGit("https://github.com/AnkitSaini/double_rocker_bogie.git","part.stl")
//CSG link = Vitamins.get(linkFile)

ArrayList<Object> servoMeasurments = new ArrayList<Object>();

servoMeasurments.add(11.7) //servoThinDimentionThickness
servoMeasurments.add(23.6) // servoThickDimentionThickness
servoMeasurments.add(50) // servoShaftSideHeight
servoMeasurments.add(10) // outputShaftDiameter
servoMeasurments.add(5.13) //shaftToShortSideDistance
servoMeasurments.add(9.8) // shaftToShortSideFlandgeEdge
servoMeasurments.add(12.9) // tipOfShaftToBottomOfFlange
servoMeasurments.add(3.0 ) //flangeThickness
servoMeasurments.add(32.3) // flangeLongDimention
servoMeasurments.add(10.16) //bottomOfFlangeToTopOfBody

CSG originalServo = (CSG)ScriptingEngine
	                    .gitScriptRun(
                                "https://gist.github.com/3f9fef17b23acfadf3f7.git", // git location of the library
	                              "servo.groovy", // file to load
	                              null 
                        )

CSG servoReference =  (CSG)ScriptingEngine
	                    .gitScriptRun(
                                "https://gist.github.com/3f9fef17b23acfadf3f7.git", // git location of the library
	                              "servo.groovy" , // file to load
	                              servoMeasurments
                        )
// Create copy for additional links                      
CSG copyOfServo =   servoReference
			.clone()  
			.movex(40)
copyOfServo.setColor(javafx.scene.paint.Color.CYAN);

return [servoReference,
	copyOfServo,
	originalServo.movex(80)
	]
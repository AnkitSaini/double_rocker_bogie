//Your code here
/*
CSG rockerBogie(){
	rockerLength = new LengthParameter("Rocker Link Length",100,[500.0, 10.0])
	rockerWidth = new LengthParameter("Rocker Link Width",10,[100.0, 1.0])
	rockerHeight = new LengthParameter("Rocker Link Height",5,[100.0, 1.0])
	bogieLength = new LengthParameter("Bogie Link Length", 50, [500.0, 10.0])
	wheelBase = new LengthParameter("Rocker Bogie Wheel Base",100, [1000.0, 10.0])
	
	// Calculating angle between links of rocker and bogie
	if((wheelBase.getMM()/(2*rockerLength.getMM())) < -1 || (wheelBase.getMM()/(2*rockerLength.getMM())) > 1) {
		println "Please enter different values of rocker link length and wheel base"
		return null
	}else{
		angle = 2*(90 - Math.toDegrees(Math.acos(wheelBase.getMM()/(2*rockerLength.getMM())))) // degrees
	}

	//Constructing rocker
	def rockerLink1 = new Cube(rockerLength, rockerWidth, rockerHeight).toCSG()
	def rockerLink2 = new Cube((rockerLength.getMM() - bogieLength.getMM()), rockerWidth.getMM(), rockerHeight.getMM()).toCSG()
	rockerLink2 = rockerLink2.rot(0,0,angle)
	// We move the center of the object in Bowler Studio
	// Thus to align the corners of the link, calculate proper position of the center of the objectdef rockerLink2 = new Cube((rockerLength.getMM() - bogieLength.getMM()), rockerWidth.getMM(), rockerHeight.getMM()).toCSG()
	rockerLink2 = rockerLink2.move( ((rockerLength.getMM()/2)-((rockerLength.getMM() - bogieLength.getMM())/2)*Math.cos(Math.toRadians(angle))), ((rockerLength.getMM() - bogieLength.getMM())/2)*Math.sin(Math.toRadians(angle)), 0 )
	// Combining rocker links
	rocker = rockerLink1.union(rockerLink2)

	// Constructing bogie
	if(rockerLength.getMM() < bogieLength.getMM()){
		println "Rocker link length cannot be less than bogie link length"
		return null
	}else{
		def bogieLink1 = new Cube(bogieLength, rockerWidth, rockerHeight).toCSG()
		def bogieLink2 = bogieLink1.rot(0,0,angle)
		bogieLink2 = bogieLink2.move( ((bogieLength.getMM()/2)-(bogieLength.getMM()/2)*Math.cos(Math.toRadians(angle))), (bogieLength.getMM()/2)*Math.sin(Math.toRadians(angle)), 0 )
		//Combining bogie links
		bogie = bogieLink1.union(bogieLink2)
	}

	// Moving rocker and bogie to proper location to merge them together
	rocker = rocker.move(rockerLength.getMM()/2,0,0)
	bogie = bogie.move((wheelBase.getMM() - 2*bogieLength.getMM()*Math.cos(Math.toRadians((180-angle)/2)))*Math.sin(Math.toRadians(angle/2)) + bogieLength.getMM()/2,
				    (wheelBase.getMM() - 2*bogieLength.getMM()*Math.cos(Math.toRadians((180-angle)/2)))*Math.cos(Math.toRadians(angle/2)),
				    0)
	
	rockerBogie = rocker.union(bogie)

	return rockerBogie
			.setParameter(wheelBase)
			.setRegenerate({rockerBogie()})
	
}

CSGDatabase.clear()
return rockerBogie()
*/

//Testing some features to understand
import com.neuronrobotics.bowlerstudio.creature.ICadGenerator;
import com.neuronrobotics.bowlerstudio.creature.CreatureLab;
import org.apache.commons.io.IOUtils;
//Create the kinematics model from the xml file describing the D-H compliant parameters. 
def file=["https://github.com/madhephaestus/carl-the-hexapod.git","CarlTheRobot.xml"]as String[]
String xmlContent = ScriptingEngine.codeFromGit(file[0],file[1])[0]

println "Loading the robot"
MobileBase base=null;
if(DeviceManager.getSpecificDevice(MobileBase.class, "CarlTheWalkingRobot")==null){
	BowlerStudio.speak("Connecting CarlTheWalkingRobot.");
	base = new MobileBase(IOUtils.toInputStream(xmlContent, "UTF-8"));
	DeviceManager.addConnection(base,"CarlTheWalkingRobot");
}else{
	base = (MobileBase)DeviceManager.getSpecificDevice(MobileBase.class, "CarlTheWalkingRobot");
}


def script = ["https://gist.github.com/e54cfebe4f55fb0549dd.git","ExampleCadGenerator.groovy"]as String[]
base.setGitCadEngine(script)
for(DHParameterKinematics appendge: base.getAllDHChains()){
	appendge.setGitCadEngine(script)
}

return null;
println "***********************"
println "***Last line reached***"
println "***********************"

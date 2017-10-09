import com.neuronrobotics.bowlerstudio.creature.ICadGenerator;
import com.neuronrobotics.bowlerstudio.creature.CreatureLab;
import org.apache.commons.io.IOUtils;
import com.neuronrobotics.bowlerstudio.vitamins.*;
import java.nio.file.Paths;
import eu.mihosoft.vrl.v3d.FileUtil;
import com.neuronrobotics.bowlerstudio.vitamins.*;
import com.neuronrobotics.bowlerstudio.physics.*;
println "Loading STL file"
// Load an STL file from a git repo
// Loading a local file also works here

return new ICadGenerator(){
	@Override 
	public ArrayList<CSG> generateCad(DHParameterKinematics d, int linkIndex) {return new ArrayList<>()}
	@Override 
	public ArrayList<CSG> generateBody(MobileBase b ) {
		ArrayList<CSG> allCad=new ArrayList<>();
		double size =40;
		CSG body = new Cube(size).toCSG();
		ArrayList<CSG> attach = [];
		for(DHParameterKinematics l:b.getAppendages()){
			TransformNR position = l.getRobotToFiducialTransform();
			Transform csgTrans = TransformFactory.nrToCSG(position)
			CSG attachment = body// get attachment for root
				.transformed(csgTrans)
				.setColor(javafx.scene.paint.Color.RED)
			attach.add(attachment);
		}
	 	for(DHParameterKinematics l:b.getSteerable()){
			TransformNR position = l.getRobotToFiducialTransform();
			Transform csgTrans = TransformFactory.nrToCSG(position)
			CSG attachment = body// get attachment for root
				.transformed(csgTrans)
				.setColor(javafx.scene.paint.Color.WHITE)
			attach.add(attachment);
		}
		for(DHParameterKinematics l:b.getDrivable()){
			TransformNR position = l.getRobotToFiducialTransform();
			Transform csgTrans = TransformFactory.nrToCSG(position)
			CSG attachment = body// get attachment for root
				.transformed(csgTrans)
				.setColor(javafx.scene.paint.Color.BLUE)
			attach.add(attachment);
		}
		
		body.setManipulator(b.getRootListener());
		body.setColor(javafx.scene.paint.Color.WHITE)
		def parts = [body]		
		parts.addAll(attach);
		return parts;
	}
};
//Your code here

// Set a specific repository for the vitamins
Vitamins.setGitRepoDatabase("https://github.com/madhephaestus/Hardware-Dimensions.git")

ArrayList<String> types = Vitamins.listVitaminTypes()
println "\n\nAll availible types: "+types

def transformationMatrix(float[] dhParameters){
	// Pass DH parameters as [link_length, link_twist, link_offset, joint_angle]
	a = dhParameters[0]
	alpha = dhParameters[1]
	d = dhParameters[2]
	th = dhParameters[3]

	// Converting angles to radians
	th = Math.toRadians(th)
	alpha = Math.toRadians(alpha)
	
	cos_th = Math.cos(th)
	sin_th = Math.sin(th)
	cos_alpha = Math.cos(alpha)
	sin_alpha = Math.sin(alpha)

	float[][] matrix = [[cos_th, -sin_th*cos_alpha,  sin_th*sin_alpha, a*cos_th],
				     [sin_th,  cos_th*cos_alpha, -cos_alpha*sin_th, a*sin_th],
				     [     0,         sin_alpha,         cos_alpha,        d],
				     [     0,                 0,                 0,        1]]
	return matrix
}

def moveToOrigin(CSG component,float[][] trans_matrix){
	// Takes in transformation matrix and moves the desired location on component to (0,0,0)
	return component.move(trans_matrix[0][3], trans_matrix[1][3], trans_matrix[2][3])	
}

// Generating rocker based on DH parameters
link_length = 100
link_width = 5
link_height = 10

rocker_angle = 90

//CSG rocker1 = new Cube(link_length, link_width, link_height).toCSG()
//rocker1 = rocker1.move(link_length/2,link_width/2,link_height/2)
//CSG rocker2 = new Cube(link_length, link_width, link_height).toCSG()
//rocker2 = rocker2.rot(0,0,90).move(link_width/2,link_length/2, link_height/2)

//CSG rocker = rocker1.union(rocker2)

CSG bar = new Cube(link_length, link_width, link_height).toCSG()

// Pass DH parameters as [link_length, link_twist, link_offset, joint_angle]
//float[] dh = [ ( (link_length/2).power(2) + (link_width/2).power(2) ).power(1/2), 0, 0, Math.toDegrees(Math.atan(link_width/link_length))  ]
float[] dh = [ 50, 0, 0, 0 ]
float[][] matrix = transformationMatrix(dh)
print matrix

bar = moveToOrigin(bar, matrix)

// Moving z0 to it's location and assuming that z1 will be at world origin
//bar = bar.move(link_length*Math.sin(Math.toRadians((180-90)/2)), 0, link_length*Math.cos(Math.toRadians((180-90)/2)))


return [bar]
CSG vitaminFromScript = Vitamins.get("hobbyServo","hv5932mg");

CSG myVitamin = Vitamins.get("vexWheels", "4inchStandard");
CSG newObject = myVitamin.difference(vitaminFromScript)

return [newObject]
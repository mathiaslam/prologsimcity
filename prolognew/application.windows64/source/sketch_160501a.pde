JSONArray values;
PImage police_station;
String type;

float scaler = 1;

int d = 40;
 
float xo;
float yo;
float zoom = 1;
float angle = 0;

ArrayList<Road> roads;
ArrayList<Tree> trees;
ArrayList<River> rivers;
ArrayList<Police> polices;
ArrayList<Residential> residentials;
ArrayList<Commercial> commercials;
ArrayList<Industrial> industrials;

int scaling = 10;

void setup() {
  size(1880, 1280);

  smooth();
  noStroke();
  rectMode(CORNER);
  
  
  
  police_station = loadImage("sprites/police_station.png"); 
  commercials = new ArrayList<Commercial>();
  residentials = new ArrayList<Residential>();
  industrials = new ArrayList<Industrial>();
  polices = new ArrayList<Police>();
  roads = new ArrayList<Road>();
  trees = new ArrayList<Tree>();
  rivers = new ArrayList<River>();
  selectInput("Select a file to process:", "fileSelected");
}

void draw() {
  background(0);

  stroke(135, 115, 45);
    translate (xo, yo);

  scale(scaler);
  translate(width/2 -960,height/2- 160);
scale(1,0.5);
rotate(radians(-45));
  fill(200, 170, 10);
  rect(0,0,1280,1280);




  for (Road road : roads) {
    road.display();
  }
    for (Residential residential : residentials) {
    residential.display();
  }
     for (Industrial industrial : industrials) {
    industrial.display();
  }
    for (Commercial commercial : commercials) {
    commercial.display();
  }
  for (Tree tree : trees) {
    tree.display();
  }
  for (River river : rivers) {
    river.display();
  }
  for (Police police : polices) {
    police.display();
  }
  
    for (int i = 0; i < 128; i++) {
    line (0, 10*i, 1280, 10*i);
    line (10*i, 0, 10*i, 1280);
  }

  noStroke();
}

void fileSelected(File selection) {
  if (selection == null) {
    println("Window was closed or the user hit cancel.");
  } else {

    values = loadJSONArray(selection.getAbsolutePath());

    for (int i = 0; i < values.size(); i++) {

      JSONObject tile = values.getJSONObject(i);

      type = tile.getString("type");
      int posX = tile.getInt("posX");
      int posY = tile.getInt("posY");
      int size = tile.getInt("size");

      switch(type) {
      case "road":
        roads.add(new Road(posX*scaling, posY*scaling, size*scaling));   
        break;
      case "tree":
        trees.add(new Tree(posX*scaling, posY*scaling, size*scaling));   
        break;
      case "river":
        rivers.add(new River(posX*scaling, posY*scaling, size*scaling));   
        break;
      case "residential_area":
        residentials.add(new Residential(posX*scaling, posY*scaling, size*scaling));   
        break;  
        case "industrial_area":
        industrials.add(new Industrial(posX*scaling, posY*scaling, size*scaling));   
        break;  
      case "commercial_area":
        commercials.add(new Commercial(posX*scaling, posY*scaling, size*scaling));   
        break;   
      case "police_station":
        polices.add(new Police(posX*scaling, posY*scaling, size*scaling));   
        break; 
      }
    }
  }
}


void mouseDragged(){
  xo= xo + (mouseX - pmouseX);
  yo = yo + (mouseY - pmouseY);
}

void keyPressed() {
  if (key == 'y') {scaler -= 0.1;} // smaller
  if (key == 'x') {scaler += 0.1;} // bigger
  if (key == 'c') {scaler = 1;} // reset scale
}
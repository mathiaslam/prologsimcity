// tiles are actually 32x17
// 32x16 for processing


JSONArray values;
PImage police_station;
String type;

ArrayList<Road> roads;
ArrayList<Tree> trees;
ArrayList<River> rivers;
ArrayList<Police> polices;

int scaling = 16;
int size = 3;

void setup() {
  size(128, 64);
  noStroke();
  rectMode(CORNER);
  
  
  
  police_station = loadImage("sprites/police_station.png"); 
  
  polices = new ArrayList<Police>();
  roads = new ArrayList<Road>();
  trees = new ArrayList<Tree>();
  rivers = new ArrayList<River>();
  selectInput("Select a file to process:", "fileSelected");
}

void draw() {
  background(0);
  fill(200, 170, 10);
  quad(64,0,128,32,64,64,0,32);
  stroke(135, 115, 45);

  //grid
  for (int i = 0; i < 8; i++) {
    line(128-i*32,64, 128,64-i*16); 
    line(96-i*32,0, 128,16+i*16); 
  }
  noStroke();
  
  


  
   // quad(0+posY*scaling+posX*scaling, 32+posY*scaling/2-posX*scaling/2, (16+posY*scaling+posX*scaling)+size*16, (24+posY*scaling/2-posX*scaling/2)-size*8, (32+posY*scaling+posX*scaling)+size*32, 32+posY*scaling/2-posX*scaling/2, (16+posY*scaling+posX*scaling)+size*16, (40+posY*scaling/2-posX*scaling/2)+size*8);
  /*
  //tile 0x0 red
  fill(255,0,0);
  quad(0,32,16,24,32,32,16,40);
  
  //tile SIZE
  
    fill(255,0,0);
    
                16         8                    32       16                   8
  quad(0,32, 16+scaling,24-scaling/2, 32+scaling*2, 32, 16+scaling,40+scaling/2);
  
  //tile 0x1 blue
  fill(0,0,255);
  quad(0+16,32+8,16+16,24+8,32+16,32+8,16+16,40+8);
    
  //tile 1x0 green
  fill(0,255,0);
  quad(0+16,32-8,16+16,24-8,32+16,32-8,16+16,40-8);
  
  
    //tile 0x2 cyan
  fill(0,255,255);
  quad(0+32,32+16,16+32,24+16,32+32,32+16,16+32,40+16);
  
  
  //tile 0xposY yellow
  fill(255,255,0);
  int posY = 1;
  int posX = 3;
  quad(0+posY*scaling+posX*scaling, 32+posY*scaling/2-posX*scaling/2, 16+posY*scaling+posX*scaling, 24+posY*scaling/2-posX*scaling/2, 32+posY*scaling+posX*scaling, 32+posY*scaling/2-posX*scaling/2, 16+posY*scaling+posX*scaling, 40+posY*scaling/2-posX*scaling/2);
  
*/

  


  
  
  

  for (Road road : roads) {
    road.display();
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
      int size = tile.getInt("size")-1;



      switch(type) {
      case "road":
        roads.add(new Road(posX, posY, size));   
        break;
      case "tree":
        trees.add(new Tree(posX*scaling, posY*scaling, size*scaling));   
        break;
      case "river":
        rivers.add(new River(posX, posY, size));   
        break;
        case "police_station":
        polices.add(new Police(posX*scaling, posY*scaling, size*scaling));   
        break; 
      }
    }
  }
}
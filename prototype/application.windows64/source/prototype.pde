// tiles are actually 32x17
// 32x16 for processing


JSONArray values;

String type;

ArrayList<Road> roads;
ArrayList<Tree> trees;
ArrayList<River> rivers;


int scaling = 16;
int size = 3;

void setup() {
  size(128, 64);
  noStroke();
  rectMode(CORNER);
  
  
  

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
 
  for (Road road : roads) {
    road.display();
  }
  for (Tree tree : trees) {
    tree.display();
  }
  for (River river : rivers) {
    river.display();
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
        trees.add(new Tree(posX, posY, size));   
        break;
      case "river":
        rivers.add(new River(posX, posY, size));   
        break;
      }
    }
  }
}
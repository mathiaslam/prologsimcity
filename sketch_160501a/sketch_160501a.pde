JSONArray values;

String type;

ArrayList<Road> roads;
ArrayList<Tree> trees;
ArrayList<River> rivers;

int scaling = 10;

void setup() {
  size(1280, 1280);
  noStroke();
  rectMode(CORNER);

  roads = new ArrayList<Road>();
  trees = new ArrayList<Tree>();
  rivers = new ArrayList<River>();

  values = loadJSONArray("data.json");

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
    }
  }
}

void draw() {
  background(200, 170, 100);
  stroke(135, 115, 45);
  
  for (int i = 0; i < 128; i++) {
    line (0, 10*i, 1280, 10*i);
    line (10*i, 0, 10*i, 1280);
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
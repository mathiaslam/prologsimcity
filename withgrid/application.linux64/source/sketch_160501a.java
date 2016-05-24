import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class sketch_160501a extends PApplet {

JSONArray values;

String type;

ArrayList<Road> roads;
ArrayList<Tree> trees;
ArrayList<River> rivers;

int scaling = 10;

public void setup() {
  
  noStroke();
  rectMode(CORNER);

  roads = new ArrayList<Road>();
  trees = new ArrayList<Tree>();
  rivers = new ArrayList<River>();
  selectInput("Select a file to process:", "fileSelected");
}

public void draw() {
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

public void fileSelected(File selection) {
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
      }
    }
  }
}

class River { 
  int river_x, river_y, river_size;

  River(int x, int y, int size) {
    river_x = x;
    river_y = y;
    river_size = size;
  }

  public void display() {
    fill(0,0,255);
    rect(river_x, river_y, river_size, river_size);
  }
} 
class Road { 
  int road_x, road_y, road_size;

  Road(int x, int y, int size) {
    road_x = x;
    road_y = y;
    road_size = size;
  }

  public void display() {
    fill(120);
    rect(road_x, road_y, road_size, road_size);
  }
} 
class Tree { 

  int tree_x, tree_y, tree_size;
  Tree(int x, int y, int size) {
    tree_x = x;
    tree_y = y;
    tree_size = size;
  }

  public void display() {
    fill(0, 150, 56);
    rect(tree_x, tree_y, tree_size, tree_size);
  }
} 
  public void settings() {  size(1280, 1280); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_160501a" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}

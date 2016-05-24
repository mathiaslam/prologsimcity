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

public class prototype extends PApplet {

// tiles are actually 32x17
// 32x16 for processing


JSONArray values;

String type;

ArrayList<Road> roads;
ArrayList<Tree> trees;
ArrayList<River> rivers;


int scaling = 16;
int size = 3;

public void setup() {
  
  noStroke();
  rectMode(CORNER);
  
  
  

  roads = new ArrayList<Road>();
  trees = new ArrayList<Tree>();
  rivers = new ArrayList<River>();
  selectInput("Select a file to process:", "fileSelected");
}

public void draw() {
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
class River { 
  int river_x, river_y, river_size;

  River(int x, int y, int size) {
    river_x = x;
    river_y = y;
    river_size = size;
  }

  public void display() {
    fill(0,0,255);
    quad(0+river_y*scaling+river_x*scaling, 32+river_y*scaling/2-river_x*scaling/2, (16+river_y*scaling+river_x*scaling)+river_size*16, (24+river_y*scaling/2-river_x*scaling/2)-river_size*8, (32+river_y*scaling+river_x*scaling)+river_size*32, 32+river_y*scaling/2-river_x*scaling/2, (16+river_y*scaling+river_x*scaling)+river_size*16, (40+river_y*scaling/2-river_x*scaling/2)+river_size*8);
 
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
    quad(0+road_y*scaling+road_x*scaling, 32+road_y*scaling/2-road_x*scaling/2, (16+road_y*scaling+road_x*scaling)+road_size*16, (24+road_y*scaling/2-road_x*scaling/2)-road_size*8, (32+road_y*scaling+road_x*scaling)+road_size*32, 32+road_y*scaling/2-road_x*scaling/2, (16+road_y*scaling+road_x*scaling)+road_size*16, (40+road_y*scaling/2-road_x*scaling/2)+road_size*8);
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
    quad(0+tree_y*scaling+tree_x*scaling, 32+tree_y*scaling/2-tree_x*scaling/2, (16+tree_y*scaling+tree_x*scaling)+tree_size*16, (24+tree_y*scaling/2-tree_x*scaling/2)-tree_size*8, (32+tree_y*scaling+tree_x*scaling)+tree_size*32, 32+tree_y*scaling/2-tree_x*scaling/2, (16+tree_y*scaling+tree_x*scaling)+tree_size*16, (40+tree_y*scaling/2-tree_x*scaling/2)+tree_size*8);
  }
} 
  public void settings() {  size(128, 64); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "prototype" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}

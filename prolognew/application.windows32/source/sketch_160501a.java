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

public void setup() {
  

  
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

public void draw() {
  background(0);

  stroke(135, 115, 45);
    translate (xo, yo);

  scale(scaler);
  translate(width/2 -960,height/2- 160);
scale(1,0.5f);
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


public void mouseDragged(){
  xo= xo + (mouseX - pmouseX);
  yo = yo + (mouseY - pmouseY);
}

public void keyPressed() {
  if (key == 'y') {scaler -= 0.1f;} // smaller
  if (key == 'x') {scaler += 0.1f;} // bigger
  if (key == 'c') {scaler = 1;} // reset scale
}
class Commercial { 
  int commercial_x, commercial_y, commercial_size;

  Commercial (int x, int y, int size) {
    commercial_x = x;
    commercial_y = y;
    commercial_size = size;
  }

  public void display() {
    fill(100,140,180);
    rect(commercial_x, commercial_y, commercial_size, commercial_size);
  }
} 
class Industrial { 
  int industrial_x, industrial_y, industrial_size;

  Industrial (int x, int y, int size) {
    industrial_x = x;
    industrial_y = y;
    industrial_size = size;
  }

  public void display() {
    fill(230,225,129);
    rect(industrial_x, industrial_y, industrial_size, industrial_size);
  }
} 
class Police { 
  int police_x, police_y, police_size;

  Police(int x, int y, int size) {
    police_x = x;
    police_y = y;
    police_size = size;
  }

  public void display() {
    fill(75, 145, 225);
    image(police_station, police_x, police_y);
    rect(police_x, police_y, police_size, police_size);
  }
} 
class Residential { 
  int residential_x, residential_y, residential_size;

  Residential(int x, int y, int size) {
    residential_x = x;
    residential_y = y;
    residential_size = size;
  }

  public void display() {
    fill(125,180,54);
    rect(residential_x, residential_y, residential_size, residential_size);
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
  public void settings() {  size(1880, 1280);  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_160501a" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}

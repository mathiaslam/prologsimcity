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

float scaler = 1;

int d = 40;

float xo;
float yo;
float zoom = 1;
float angle = 0;

ArrayList<Powerplant> powerplants;
ArrayList<Waterpump> waterpumps;
ArrayList<Smallpark> smallparks;
ArrayList<Library> libraries;
ArrayList<Road> roads;
ArrayList<College> colleges;
ArrayList<Tree> trees;
ArrayList<River> rivers;
ArrayList<Police> polices;
ArrayList<Hospital> hospitals;
ArrayList<Fire> fires;
ArrayList<School> schools;
ArrayList<Residential> residentials;
ArrayList<Commercial> commercials;
ArrayList<Industrial> industrials;

int scaling = 10;

public void setup() {
  

  
  noStroke();
  rectMode(CORNER);
  waterpumps = new ArrayList<Waterpump>();
  smallparks = new ArrayList<Smallpark>();
  libraries = new ArrayList<Library>();
  colleges = new ArrayList<College>();
  commercials = new ArrayList<Commercial>();
  residentials = new ArrayList<Residential>();
  industrials = new ArrayList<Industrial>();
  polices = new ArrayList<Police>();
  fires = new ArrayList<Fire>();
  schools = new ArrayList<School>();
  hospitals = new ArrayList<Hospital>();
  roads = new ArrayList<Road>();
  trees = new ArrayList<Tree>();
  rivers = new ArrayList<River>();
  powerplants = new ArrayList<Powerplant>();
  selectInput("Select a file to process:", "fileSelected");
}

public void draw() {
  background(0);
  stroke(135, 115, 45);
  translate (xo, yo);

  scale(scaler);
  translate(width/2 -960, height/2- 160);
  scale(1, 0.5f);
  rotate(radians(-45));
  fill(200, 170, 10);
  rect(0, 0, 1280, 1280);

  for (Road road : roads) {
    road.display();
  }

  for (Residential residential : residentials) {
    residential.display();
  }
  for (Library library : libraries) {
    library.display();
  }

  for (Industrial industrial : industrials) {
    industrial.display();
  }
  for (Waterpump waterpump : waterpumps) {
    waterpump.display();
  }

  for (Smallpark smallpark : smallparks) {
    smallpark.display();
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

  for (Hospital hospital : hospitals) {
    hospital.display();
  }
  for (School school : schools) {
    school.display();
  }
  for (College college : colleges) {
    college.display();
  }

  for (Fire fire : fires) {
    fire.display();
  }

  for (Powerplant powerplant : powerplants) {
    powerplant.display();
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

      if (type.equals("residential_area") == true || type.equals("industrial_area") == true || type.equals("commercial_area") == true) {
        int posX = tile.getInt("posX");
        int posY = tile.getInt("posY");
        int aWidth = tile.getInt("width");
        int aHeight = tile.getInt("height");

        println(type);

        switch(type) {

        case "residential_area":
          residentials.add(new Residential(posX*scaling, posY*scaling, aWidth*scaling, aHeight*scaling));   
          break;  
        case "industrial_area":
          industrials.add(new Industrial(posX*scaling, posY*scaling, aWidth*scaling, aHeight*scaling));   
          break;  
        case "commercial_area":
          commercials.add(new Commercial(posX*scaling, posY*scaling, aWidth*scaling, aHeight*scaling));   
          break;
        }
      } else {
        int posX = tile.getInt("posX");
        int posY = tile.getInt("posY");
        int size = tile.getInt("size");

        switch(type) {
        case "road":
          roads.add(new Road(posX*scaling, posY*scaling, size*scaling));   
          break;
        case "park_small":
          smallparks.add(new Smallpark(posX*scaling, posY*scaling, size*scaling));   
          break;
        case "library":
          libraries.add(new Library(posX*scaling, posY*scaling, size*scaling));   
          break;
        case "college":
          colleges.add(new College(posX*scaling, posY*scaling, size*scaling));   
          break;
        case "tree":
          trees.add(new Tree(posX*scaling, posY*scaling, size*scaling));   
          break;
        case "water_pump":
          waterpumps.add(new Waterpump(posX*scaling, posY*scaling, size*scaling));   
          break;
        case "river":
          rivers.add(new River(posX*scaling, posY*scaling, size*scaling));   
          break;  
        case "police_station":
          polices.add(new Police(posX*scaling, posY*scaling, size*scaling));   
          break;
        case "school":
          schools.add(new School(posX*scaling, posY*scaling, size*scaling));   
          break;
        case "fire_station":
          fires.add(new Fire(posX*scaling, posY*scaling, size*scaling));   
          break;
        case "hospital":
          hospitals.add(new Hospital(posX*scaling, posY*scaling, size*scaling));   
          break;
        case "power_plant_coal":
          powerplants.add(new Powerplant(posX*scaling, posY*scaling, size*scaling));   
          break;
        }
      }
    }
  }
}


public void mouseDragged() {
  xo= xo + (mouseX - pmouseX);
  yo = yo + (mouseY - pmouseY);
}

public void keyPressed() {
  if (key == 'y') {
    scaler -= 0.1f;
  } // smaller
  if (key == 'x') {
    scaler += 0.1f;
  } // bigger
  if (key == 'c') {
    scaler = 1;
  } // reset scale
}
class College { 
  int college_x, college_y, college_size;

  College(int x, int y, int size) {
    college_x = x;
    college_y = y;
    college_size = size;
  }

  public void display() {
    fill(164, 242, 255);
    rect(college_x, college_y, college_size, college_size);
    pushMatrix();
    translate(college_x, college_y);
    textSize(24);
    fill(0);
    text("C", 8, 24); 

    popMatrix();
  }
} 
class Commercial { 
  int commercial_x, commercial_y, commercial_width, commercial_height;

  Commercial (int x, int y, int width, int height) {
    commercial_x = x;
    commercial_y = y;
    commercial_width = width;
    commercial_height = height;
  }

  public void display() {
    fill(100, 140, 180);
    rect(commercial_x, commercial_y, commercial_width, commercial_height);
  }
} 
class Fire { 
  int fire_x, fire_y, fire_size;

  Fire(int x, int y, int size) {
    fire_x = x;
    fire_y = y;
    fire_size = size;
  }

  public void display() {
    fill(218, 90, 34);
    rect(fire_x, fire_y, fire_size, fire_size);
    pushMatrix();
    translate(fire_x, fire_y);
    textSize(24);
    fill(0);
    text("F", 8, 24); 
    popMatrix();
  }
} 
class Hospital { 
  int hospital_x, hospital_y, hospital_size;

  Hospital(int x, int y, int size) {
    hospital_x = x;
    hospital_y = y;
    hospital_size = size;
  }

  public void display() {
    fill(25, 200, 200);
    rect(hospital_x, hospital_y, hospital_size, hospital_size);
    pushMatrix();
    translate(hospital_x, hospital_y);
    textSize(24);
    fill(0);
    text("H", 8, 24); 
    popMatrix();
  }
} 
class Industrial { 
  int industrial_x, industrial_y, industrial_width, industrial_height;

  Industrial (int x, int y, int width, int height) {
    industrial_x = x;
    industrial_y = y;
    industrial_width = width;
    industrial_height = height;
  }

  public void display() {
    fill(230, 225, 129);
    rect(industrial_x, industrial_y, industrial_width, industrial_height);
  }
} 
class Library { 
  int library_x, library_y, library_size;

  Library(int x, int y, int size) {
    library_x = x;
    library_y = y;
    library_size = size;
  }

  public void display() {
    fill(164, 242, 255);
    rect(library_x, library_y, library_size, library_size);
    pushMatrix();
    translate(library_x, library_y);
    textSize(20);
    fill(0);
    text("L", 8, 24); 

    popMatrix();
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
    fill(164, 242, 255);
    rect(police_x, police_y, police_size, police_size);
    pushMatrix();
    translate(police_x, police_y);
    textSize(24);
    fill(0);
    text("P", 8, 24); 

    popMatrix();
  }
} 
class Powerplant { 
  int powerplant_x, powerplant_y, powerplant_size;

  Powerplant(int x, int y, int size) {
    powerplant_x = x;
    powerplant_y = y;
    powerplant_size = size;
  }

  public void display() {
    fill(25, 200, 200);
    rect(powerplant_x, powerplant_y, powerplant_size, powerplant_size);
    pushMatrix();
    translate(powerplant_x, powerplant_y);
    textSize(24);
    fill(0);
    text("P", 8, 24); 
    popMatrix();
  }
} 
class Residential { 
  int residential_x, residential_y, residential_width, residential_height;

  Residential(int x, int y, int width, int height) {
    residential_x = x;
    residential_y = y;
    residential_height = height;
    residential_width = width;
  }

  public void display() {
    fill(125, 180, 54);
    rect(residential_x, residential_y, residential_width, residential_height);
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
    fill(0, 0, 255);
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
class School { 
  int school_x, school_y, school_size;

  School(int x, int y, int size) {
    school_x = x;
    school_y = y;
    school_size = size;
  }

  public void display() {
    fill(25, 200, 200);
    rect(school_x, school_y, school_size, school_size);
    pushMatrix();
    translate(school_x, school_y);
    textSize(24);
    fill(0);
    text("S", 8, 24); 
    popMatrix();
  }
} 
class Smallpark { 
  int smallpark_x, smallpark_y, smallpark_size;

  Smallpark(int x, int y, int size) {
    smallpark_x = x;
    smallpark_y = y;
    smallpark_size = size;
  }

  public void display() {
    fill(25, 200, 200);
    rect(smallpark_x, smallpark_y, smallpark_size, smallpark_size);
    pushMatrix();
    translate(smallpark_x, smallpark_y);
    textSize(10);
    fill(0);
    text("P", 4, 14); 
    popMatrix();
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
class Waterpump { 
  int waterpump_x, waterpump_y, waterpump_size;

  Waterpump(int x, int y, int size) {
    waterpump_x = x;
    waterpump_y = y;
    waterpump_size = size;
  }

  public void display() {
    fill(25, 200, 200);
    rect(waterpump_x, waterpump_y, waterpump_size, waterpump_size);
    pushMatrix();
    translate(waterpump_x, waterpump_y);
    textSize(10);
    fill(0);
    text("W", 4, 14); 
    popMatrix();
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


JSONArray values;
PImage tile;
PImage police_station;
String type;
float tileSize = 40;


int scaling = 10;

void setup() {
  size(1140, 600);
  noStroke();
  rectMode(CORNER);
  
   
  police_station = loadImage("sprites/police_station.png"); 
  tile = loadImage("single_tile.png"); 
  
 
}

void draw() {
  background(200, 170, 100);
  fill(255);

  //quad(608,40,)
  stroke(135, 115, 45);

  for (int x = 0; x< 15; x++) {
    line(988-x*152,0, 1140,80+x*80);
  }
  
  for (int x = 0; x<15; x++) {
  line(0, 40+x*80, 76+x*152,0);
  }

  //image(tile, 0 , 0);



quad(0, tileSize, tileSize*1.9, 0, tileSize*3.8, tileSize, tileSize*1.9, tileSize*2);
/*
  for (int i = 0; i < 128; i++) {
    line (0, 10*i, 1280, 10*i);
    line (10*i, 0, 10*i, 1280);
  }
*/
  noStroke();

 
}
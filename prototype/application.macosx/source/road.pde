class Road { 
  int road_x, road_y, road_size;

  Road(int x, int y, int size) {
    road_x = x;
    road_y = y;
    road_size = size;
  }

  void display() {
    fill(120);
    quad(0+road_y*scaling+road_x*scaling, 32+road_y*scaling/2-road_x*scaling/2, (16+road_y*scaling+road_x*scaling)+road_size*16, (24+road_y*scaling/2-road_x*scaling/2)-road_size*8, (32+road_y*scaling+road_x*scaling)+road_size*32, 32+road_y*scaling/2-road_x*scaling/2, (16+road_y*scaling+road_x*scaling)+road_size*16, (40+road_y*scaling/2-road_x*scaling/2)+road_size*8);
  }
} 
class River { 
  int river_x, river_y, river_size;

  River(int x, int y, int size) {
    river_x = x;
    river_y = y;
    river_size = size;
  }

  void display() {
    fill(0,0,255);
     quad(0+river_y*scaling+river_x*scaling, 32+river_y*scaling/2-river_x*scaling/2, (16+river_y*scaling+river_x*scaling)+river_size*16, (24+river_y*scaling/2-river_x*scaling/2)-river_size*8, (32+river_y*scaling+river_x*scaling)+river_size*32, 32+river_y*scaling/2-river_x*scaling/2, (16+river_y*scaling+river_x*scaling)+river_size*16, (40+river_y*scaling/2-river_x*scaling/2)+river_size*8);
 
  }
} 
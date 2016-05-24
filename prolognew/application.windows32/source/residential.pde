class Residential { 
  int residential_x, residential_y, residential_size;

  Residential(int x, int y, int size) {
    residential_x = x;
    residential_y = y;
    residential_size = size;
  }

  void display() {
    fill(125,180,54);
    rect(residential_x, residential_y, residential_size, residential_size);
  }
} 
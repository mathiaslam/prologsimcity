class Commercial { 
  int commercial_x, commercial_y, commercial_size;

  Commercial (int x, int y, int size) {
    commercial_x = x;
    commercial_y = y;
    commercial_size = size;
  }

  void display() {
    fill(100,140,180);
    rect(commercial_x, commercial_y, commercial_size, commercial_size);
  }
} 
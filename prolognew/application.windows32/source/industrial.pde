class Industrial { 
  int industrial_x, industrial_y, industrial_size;

  Industrial (int x, int y, int size) {
    industrial_x = x;
    industrial_y = y;
    industrial_size = size;
  }

  void display() {
    fill(230,225,129);
    rect(industrial_x, industrial_y, industrial_size, industrial_size);
  }
} 
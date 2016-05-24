class Police { 
  int police_x, police_y, police_size;

  Police(int x, int y, int size) {
    police_x = x;
    police_y = y;
    police_size = size;
  }

  void display() {
    fill(75, 145, 225);
    rect(police_x, police_y, police_size, police_size);
  }
} 
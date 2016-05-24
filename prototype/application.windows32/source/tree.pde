class Tree { 

  int tree_x, tree_y, tree_size;
  Tree(int x, int y, int size) {
    tree_x = x;
    tree_y = y;
    tree_size = size;
  }

  void display() {
    fill(0, 150, 56);
    quad(0+tree_y*scaling+tree_x*scaling, 32+tree_y*scaling/2-tree_x*scaling/2, (16+tree_y*scaling+tree_x*scaling)+tree_size*16, (24+tree_y*scaling/2-tree_x*scaling/2)-tree_size*8, (32+tree_y*scaling+tree_x*scaling)+tree_size*32, 32+tree_y*scaling/2-tree_x*scaling/2, (16+tree_y*scaling+tree_x*scaling)+tree_size*16, (40+tree_y*scaling/2-tree_x*scaling/2)+tree_size*8);
  }
} 
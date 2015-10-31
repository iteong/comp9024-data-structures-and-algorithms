package net.datastructures;

public class Main {

    public static void main(String[] args) { 
      String values1[] = 
              {"Sydney", "Beijing", "Shanghai", "New York", "Tokyo", "Berlin", "Athens", "Paris", "London", "Cairo"};
      int keys1[] = {20, 8, 5, 30, 22, 40, 12, 10, 3, 5};
      String values2[] = {"Fox", "Lion", "Dog", "Sheep", "Rabbit", "Fish"};
      int keys2[] = {40, 7, 5, 32, 20, 30};
    
      String values3[] = {"Fox", "Fish"};
      int keys3[] = {40, 7};
         
      /* Create the first AVL tree with an external node as the root and the
     default comparator */ 
             
      AVLTree<Integer, String> tree1 = new AVLTree<Integer, String>();
  
      // Insert 10 nodes into the first tree
         
      for (int i = 0; i < 10; i++) {
          tree1.insert(keys1[i], values1[i]);
      }
       
      /* Create the second AVL tree with an external node as the root and the
     default comparator */
         
      AVLTree<Integer, String> tree2 = new AVLTree<Integer, String>();
       
      // Insert 6 nodes into the tree
         
      for (int i = 0; i < 6; i++) {
          tree2.insert(keys2[i], values2[i]);
      }

      // Create the third AVL tree
      AVLTree<Integer, String> tree3 = new AVLTree<Integer, String>();

      // Insert 6 nodes into the tree
      for (int i = 0; i < 2; i++) {
          tree3.insert(keys3[i], values3[i]);
      }
         
      //ExtendedAVLTree.print(tree1);
      //ExtendedAVLTree.print(tree2);
      //ExtendedAVLTree.print(ExtendedAVLTree.clone(tree1));
      //ExtendedAVLTree.print(ExtendedAVLTree.clone(tree2));
    
      ExtendedAVLTree.print(ExtendedAVLTree.merge(ExtendedAVLTree.clone(tree1), ExtendedAVLTree.clone(tree2)));

      }
}



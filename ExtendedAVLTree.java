/** COMP9024 Assignment Two
 *  Shu Hao Ivan Teong (z3386180) */

package net.datastructures;

import javax.swing.*;
import java.awt.*;

public class ExtendedAVLTree<K,V> extends AVLTree<K,V> {

/**
 * Static classes for objects to be drawn by extending JComponent
 */

    // Drawing circles for internal nodes
    protected static class Circle extends JComponent {
        /**
         * 
         */
        private static final long serialVersionUID = -285245281689614327L;
        int x = 0;
        int y = 0;
        int w = 0;
        int h = 0;
        
        Circle(int x, int y, int d) {
            this.x = x-d/2; // change (x, y) to the top of the circle
            this.y = y;
            this.w = d;
            this.h = d;
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            g.drawOval(x, y, w, h);
        }
    }

    // Drawing rectangles for external/leaf nodes
    protected static class Rectangle extends JComponent {
        /**
         * 
         */
        private static final long serialVersionUID = 5313538215998587004L;
        int x = 0;
        int y = 0;
        int w = 0;
        int h = 0;
        
        Rectangle(int x, int y, int w, int h) {
            this.x = x-w/2; // change (x, y) to the top of the rectangle
            this.y = y;
            this.w = w;
            this.h = h;
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            g.drawRect(x, y, w, h);
        }
    }
    
    // Drawing connecting lines
    protected static class Line extends JComponent {
        /**
         * 
         */
        private static final long serialVersionUID = 557134623293999792L;
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;
        
        Line(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            g.drawLine(x1, y1, x2, y2);
        }
    }

    // Drawing text for numbers within nodes
    protected static class Text extends JComponent {
        /**
         * 
         */
        private static final long serialVersionUID = 8667260237684528313L;
        String s;
        int x = 0;
        int y = 0;
        
        Text(String s, int x, int y) {
            this.s = s;
            this.x = x;
            this.y = y;
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            g.drawString(s, x, y);
        }
    }

/**
 * Q1. This class method creates an identical copy of the AVL tree specified by the parameter 
 * and returns a reference to the new AVL tree.
 */

    public static <K, V> AVLTree<K, V> clone(AVLTree<K,V> tree) {
        AVLTree<K, V> clonedTree = new AVLTree<K, V>(); // declare new AVLTree object as clonedTree
        clonedTree.insert(tree.root.element().getKey(), tree.root.element().getValue()); // access key and value of tree root and insert into clonedTree
        cloneSubtrees(clonedTree.root, tree.root); // clone subtrees of tree to clonedTree from tree, starting from the root node as reference for both
        clonedTree.numEntries = tree.size(); // number of entries in clonedTree equals number of elements in tree
        return clonedTree;
    }
    
    private static <K, V> void cloneSubtrees(BTPosition<Entry<K,V>> cloneNode, BTPosition<Entry<K,V>> originalNode) { // perform subtrees cloning on the BTree node interface
        AVLNode<K,V> leftChild = new AVLNode<K,V>(); // declare new AVLNode object as leftChild
        AVLNode<K,V> rightChild = new AVLNode<K,V>(); // declare new AVLNode object as rightChild
        
        // linking parent node to its left child node
        cloneNode.setLeft(leftChild); // set left of node to be cloned to be the new AVLNode object, leftChild
        leftChild.setParent(cloneNode); // set leftChild's parent as node to be cloned
        // linking parent node to its right child node
        cloneNode.setRight(rightChild); // set right of node to be cloned to be the new AVLNode object, rightChild
        rightChild.setParent(cloneNode); // set rightChild's parent as node to be cloned
        
        // check to see if there is a left child node for original node
        if (originalNode.getLeft().element() != null) { // if there is a left child node for the original node
            leftChild.setElement(originalNode.getLeft().element()); // get left child element of original node and set it as that of leftChild
            cloneSubtrees(leftChild, originalNode.getLeft()); // clone left child from original node to leftChild
        }
        // check to see if there is a right child node for original node
        if (originalNode.getRight().element() != null) { // if there is a right child node for the original node
            rightChild.setElement(originalNode.getRight().element()); // get right child element of original node and set it as that of rightChild
            cloneSubtrees(rightChild, originalNode.getRight()); // clone right child from original node to leftChild
        }   
    }

    
    
/**
 * Q2. This class method merges two AVL trees, tree1 and tree2, into a new tree. After the
 * merge, this method reclaims the unused original AVL trees and returns the new AVL
 * tree. You need to make this method as fast as possible and analyze its running time in
 * big O notation. Put your running time analysis as comments after the code.
 * 
 * Bonus marks: If the time complexity of your merge method is O(m+n), where m and n are 
 * the numbers of nodes of the two input AVL trees, you will get 2 bonus marks.
 */

    /*  Assume that m = number of nodes in tree1 and n = number of nodes in tree2.
     * 
     *  1) This merging method begins by flattening the trees into sorted lists where its
     *  time complexity is O(m+n).
     *  
     *  2) This is followed by merging the 2 sorted lists where its time complexity is O(m+n).
     *  
     *  3) Finally, the merged list is converted back into a tree where its time complexity is O(m+n).
     *
     *  Overall time complexity of this operation is O(m+n).
     *
     */

    public static <K, V> AVLTree<K, V> merge(AVLTree<K,V> tree1, AVLTree<K,V> tree2) {
        AVLTree<K, V> mergedTree = new AVLTree<K, V>();
        
        
        // 1) Flatten tree1 and tree2 into 2 sorted lists, list1 and list2
        PositionList<Position<Entry<K,V>>> mergedList = new NodePositionList<Position<Entry<K,V>>>();
        PositionList<Position<Entry<K,V>>> list1 = new NodePositionList<Position<Entry<K,V>>>();
        PositionList<Position<Entry<K,V>>> list2 = new NodePositionList<Position<Entry<K,V>>>();
        // Creates a list storing the the nodes in the subtree of a node, ordered according to the in-order traversal of the subtree (LinkedBinaryTree.java)
        tree1.inorderPositions(tree1.root(), list1); // create sorted list1 from tree1
        tree2.inorderPositions(tree2.root(), list2); // create sorted list2 from tree2
        
        
        // 2) Merge the 2 sorted lists into 1 list, mergedList
        while (!list1.isEmpty() && !list2.isEmpty()) { // logical AND: as long as list1 is not empty, and as long as list2 is not empty
            
            // skipping external/leaf nodes which is null when merging into 1 list
            if (list1.first().element().element() == null) { // if element of list1 is null, remove from list1
                list1.remove(list1.first());
            }
            if (list2.first().element().element() == null) { // if element of list2 is null, remove from list1
                list2.remove(list2.first());
            }
            
            if (list1.isEmpty() || list2.isEmpty()) { // logical OR: if list1 is empty, and if list2 is also empty
                continue; // jumps back to condition, !list1.isEmpty() && !list2.isEmpty(), executing next iteration of the while-loop
            }
            
            // Compare keys of 2 elements in the 2 lists and insert the node with smaller key into the merged list
            if (Integer.parseInt(list1.first().element().element().getKey().toString()) <= Integer.parseInt(list2.first().element().element().getKey().toString())) {
                mergedList.addLast(list1.remove(list1.first())); // remove from list1 and add it to end of mergedList
            } else {
                mergedList.addLast(list2.remove(list2.first())); // remove from list2 and add it to end of mergedList
            }
        }
        
        // List with smaller size should be empty, so add remaining elements in the larger list to mergedList
        while (!list1.isEmpty()) { // as long as list1 is not empty
            // skipping external/leaf nodes which is null
            if (list1.first().element().element() == null) { // if element of list1 is null, remove from list1
                list1.remove(list1.first());
            } else { // if it is not external/leaf node, remove from list1 and add it to end of mergedList
                mergedList.addLast(list1.remove(list1.first()));
            }
        }
        while (!list2.isEmpty()) { // as long as list2 is not empty
            // skipping external/leaf nodes which is null
            if (list2.first().element().element() == null) { // if element of list2 is null, remove from list2
                list2.remove(list2.first());
            } else { // if it is not external/leaf node, remove from list2 and add it to end of mergedList
                mergedList.addLast(list2.remove(list2.first()));
            }
        }
        
        
        // 3) Convert merged list back to a sorted tree and store entries in tree1
        mergedTree.root = sortedListToTree(mergedList, 0, mergedList.size() - 1); // call private static function to convert list to tree
        mergedTree.numEntries = tree1.size() + tree2.size(); // number of elements in mergedTree = sum of elements in tree1 and tree2
        tree1 = null; // get rid of tree1 object
        tree2 = null; // get rid of tree2 object
        
        return mergedTree;
    }
    
    private static <K, V> BTPosition<Entry<K,V>> sortedListToTree(PositionList<Position<Entry<K,V>>> mergedList, int starting, int ending) {
        
        // if reach the end of the mergedList, do not return any node
        if (starting > ending) {
            return null;
        }
        
        // determine midpoint of the starting and ending points
        int midpoint = starting + (ending - starting) / 2;
        
        BTPosition<Entry<K,V>> parentNode = new AVLNode<K, V>(); // declare new AVLNode object as parentNode of AVL tree
        
        // call private function, sortedListToTree, recursively to start from left of midpoint for tree
        BTPosition<Entry<K,V>> leftChild = sortedListToTree(mergedList, starting, midpoint - 1);
        // linking parent to left child
        if (leftChild != null) { // link if leftChild is not null by setting parent of leftChild as parentNode
            leftChild.setParent(parentNode);
        }
        parentNode.setLeft(leftChild); // set left of parentNode as leftChild
        parentNode.setElement(mergedList.remove(mergedList.first()).element()); // remove that element from beginning of mergedList and set it as element of parent

        // call private function, sortedListToTree, recursively to start from right of midpoint for tree
        BTPosition<Entry<K,V>> rightChild = sortedListToTree(mergedList, midpoint + 1, ending);
        // linking parent to right child
        if (rightChild != null) { // link if rightChild is not null by setting parent of rightChild as parentNode
            rightChild.setParent(parentNode);
        }
        parentNode.setRight(rightChild); // set right of parentNode as rightChild
        
        return parentNode;
    }



/**
 * Q3. This class method creates a new window and prints the AVL tree specified by the
 * parameter on the new window. Each internal node is displayed by a circle containing
 * its key and each external node is displayed by a rectangle. You need to choose a
 * proper size for all the circles and a proper size for all the rectangles and make sure
 * that this method never prints a tree with crossing edges.
 */
    
    public static <K, V> void print(AVLTree<K, V> tree) {
        // New window properties using JFrame to contain sub-components of drawn AVLTree
        JFrame frame = new JFrame();
        int gridWidth = 1200;
        int gridHeight = 800;
        frame.setSize(gridWidth, gridHeight);
        frame.setResizable(false); // user cannot resize window since tree will be adapted to fit in window
        frame.setTitle("COMP9024 Assignment Two: ExtendedAVLTree.java, Shu Hao Ivan Teong z3386180");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Draw AVLTree by calling private function, printSubTree
        printSubTree(tree.root, frame, gridWidth/2, 0, gridWidth/2, 0);
    }
    
    private static <K, V> void printSubTree(BTPosition<Entry<K,V>> position, JFrame frame, int x, int y, int separation, int height) {
        int circle_depth = 50 - height*3;
        int rectangle_width = 30 - height*2;
        int rectangle_height = 30 - height*2;
        
        // Draw left subtree, where if its left child is an external/leaf node, then draw a rectangle
        if (position.getLeft() != null && position.getLeft().element() != null) { // logical AND: if position's left child is not null, and if that left child's element is also not null
            printSubTree(position.getLeft(), frame, x - separation/2, y + 100, separation/2, height + 1);  // recurse on left child
        } else { // else, add rectangle
            frame.getContentPane().add(new Rectangle(x - separation/2, y + 100, rectangle_width, rectangle_height));
            frame.setVisible(true);
        }
        
        // Draw right subtree, where if its right child is an external/leaf node, then draw a rectangle
        if (position.getRight() != null && position.getRight().element() != null) { // logical AND: if position's right child is not null, and if that right child's element is also not null
            printSubTree(position.getRight(), frame, x + separation/2, y + 100, separation/2, height + 1); // recurse on right child
        } else { // else, add rectangle
            frame.getContentPane().add(new Rectangle(x + separation/2, y + 100, rectangle_width, rectangle_height));
            frame.setVisible(true);
        }
        
        // Drawing current nodes
        
        // Draw internal node as a circle
        frame.getContentPane().add(new Circle(x, y, circle_depth));
        frame.setVisible(true);
        // Put key in circle
        frame.getContentPane().add(new Text(position.element().getKey().toString(), x - height, y + circle_depth/2));
        frame.setVisible(true);
        // Draw left leg
        frame.getContentPane().add(new Line(x, y + circle_depth, x - separation/2, y + 100));
        frame.setVisible(true);
        // Draw right leg
        frame.getContentPane().add(new Line(x, y + circle_depth, x + separation/2, y + 100));
        frame.setVisible(true);
        
    }

}


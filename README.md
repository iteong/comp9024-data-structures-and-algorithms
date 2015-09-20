# comp9024-data-structures-and-algorithms
Java Assignments for Data Structures and Algorithms (UNSW Semester 2 2015)

ASSIGNMENT ONE:
In this assignment, you extend the doubly linked list class DList given in the textbook. The subclass is named MyDlist.

You need to implement the following constructors and methods of MyDlist:
1. public MyDlist(). This constructor creates an empty doubly linked list.

2. public MyDlist(String f). This constructor creates a doubly linked list by reading all strings from a text file
named f. Assume that adjacent strings in the file f are separated by one or more white space characters. If f is “stdin”, MyDlist(“stdin”) creates a doubly linked list by reading all strings from the standard input. Assume that each input line is a string and an empty line denotes end of input.

3. public void printList(). This instance method prints all elements of a list on the standard output, one element per line.

4. public static MyDlist cloneList(MyDlist u). This class method creates an identical copy of a doubly linked list u and returns the resulting doubly linked list.

5. public static MyDlist union(MyDlist u, MyDlist v). This class method computes the union of the two sets that are stored in the doubly linked lists u and v, respectively, and returns a doubly linked list that stores the union. 
Each element of a set is stored in a node of the corresponding doubly linked list. Given two sets A and B, the union of A and B is a set that contains all the distinct element of A and B. 
Include the detailed time complexity analysis of this method in big O notation immediately above the source code of this method as comments.

6. public static MyDlist intersection(MyDlist u, MyDlist v). This class method computes the intersection of the two sets that are stored in the doubly linked lists u and v, respectively, and returns a doubly linked list that stores the intersection. Each element of a set is stored in a node of the corresponding doubly linked list. 
Given two sets A and B, the intersection of A and B is a set that contains all the elements of A that are also in B. Include the detailed time complexity analysis of this method in big O notation immediately above the source code of this method as comments.
We assume that all the elements of a set are distinct.

ASSIGNMENT TWO:
In this assignment, you will implement a class named ExtendedAVLTree. ExtendedAVLTree extends the AVLTree class to include the following methods:

- Public static <K, V> AVLTree<K, V> clone(AVLTree<K,V> tree)
This class method creates an identical copy of the AVL tree specified by the parameter and returns a reference to the new AVL tree.

- public static <K, V> AVLTree<K, V> merge(AVLTree<K,V> tree1, AVLTree<K,V> tree2 )
This class method merges two AVL trees, tree1 and tree2, into a new tree. After the merge, this method reclaims the unused original AVL trees and returns the new AVL tree. You need to make this method as fast as possible and analyze its running time in big O notation. 
Put your running time analysis as comments after the code.
Bonus marks: If the time complexity of your merge method is O(m+n), where m and n are the numbers of nodes of the two input AVL trees, you will get 2 bonus marks.

- public static <K, V> void print(AVLTree<K, V> tree)
This class method creates a new window and prints the AVL tree specified by the parameter on the new window. Each internal node is displayed by a circle containing its key and each external node is displayed by a rectangle. 
You need to choose a proper size for all the circles and a proper size for all the rectangles and make sure that this method never prints a tree with crossing edges.

For simplicity, we assume that K is int and V is String.

All the related classes are in the package net-datastructures-4-0. Please download net- datastructures-4-0, install it on your own computer and create the new class ExtendedAVLTree in the same package.
You need to read the code of all the related classes in order to understand how the AVLTree class is implemented.

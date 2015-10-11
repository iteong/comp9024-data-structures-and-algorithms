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

ASSIGNMENT THREE:
This is an individual assignment. In this assignment, you will implement a task scheduler in Java.

Background:
An embedded system is a computer system performing dedicated functions within a larger mechanical or electrical system. Embedded systems range from portable devices such as Google Glasses, to large stationary installations like traffic lights, factory controllers, and complex systems like hybrid vehicles, and avionic. Typically, the software of an embedded system consists of a set of tasks (threads) with timing constraints. Typical timing constraints are release times and deadlines. A release time specifies the earliest time a task can start, and a deadline is the latest time by which a task needs to finish. One major goal of embedded system design is to find a feasible schedule for the task set such that all the timing constraints are satisfied.

Task scheduler:
We assume that the hardware platform of the target embedded systems is a single processor with m identical cores. The task set V={v1, v2, ..., vn} consists of n independent tasks. The execution time of each task is one time unit. Each task vi (i=1, 2, ... n) has a release time ri and a deadline di (di>ri). All the release times and deadlines are non-negative integers. You need to design an algorithm for the task scheduler and implement it in Java. Your task scheduler uses EDF (Earliest Deadline First) strategy to find a feasible schedule for a task set. A schedule of a task set specifies when each task starts and on which core it is executed. A feasible schedule is a schedule satisfying all the release time and deadline constraints.

The EDF strategy works as follows:
- At any time t, among all the ready tasks, find a task with the smallest deadline, and schedule it on an idle core. Ties are broken arbitrarily. A task vi (i=1, 2, ... n) is ready at a time t if t􏰁ri holds.

It can be shown that the EDF strategy is guaranteed to find a feasible schedule whenever one exists for a set of independent tasks with unit execution time.

You can define any fields, constructors and methods within the TaskScheduler class. You can also define additional classes. You must put all the additional classes in the file Taskscheduler.java without class any class modifiers.

The main method scheduler(String file1, String file2, Integer m) gets a task set from file1, constructs a feasible schedule for the task set on a processor with m identical cores by using the EDF strategy, and write the feasible schedule to file2. If no feasible schedule exists, it displays “ No feasible schedule exists” on the screen. This method needs to handle all the possible cases properly when reading from file1 and writing to file2. All the possible cases are as follows:
1. file1 does not exist.
2. file2 already exists.
3. The task attributes (task name, release time and deadline) of file1 do not follow the format as shown next.

Both file1 and file2 are text files. files1 contains a set of independent tasks each of which has a name, a release time and a deadline. A task name is a string of letters and numbers. All the release times are non-negative integers, and all the deadlines are natural numbers.

In file2, all the tasks must be sorted in non-decreasing start times. Notice that you do not need to include the core on which each task is executed.

Time complexity requirement:
You need to include your time complexity analysis as comments in your program. The time complexity of your scheduler is required to be no higher than O(n log n), where n is the number of tasks, and m is the number of cores (Hints: use heap-based priority queues). You need to include the time complexity analysis of your task scheduler in the TaskScheduler class file as comments. There is no specific requirement on space complexity. However, try your best to make your program space efficient.

Restrictions:
All the data structures and algorithms must be implemented in the TaskScheduler class. You are NOT allowed to use any sorting algorithms and priority queues provided by Java.

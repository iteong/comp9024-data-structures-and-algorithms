/** COMP9024 Assignment One
 *  Shu Hao Ivan Teong (z3386180) */

package assignmentone.ivan.www;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class MyDList extends DList implements Cloneable {
  private Scanner _scanner;
  private List<String> _nodeList = new ArrayList<String>();

  
  /** Q1. Default constructor creating empty DList by calling parent constructor */
  public MyDList() { 
    super();
  }

  /** Q2. Constructor creating DList based on input type */
  public MyDList(String f) {
	  super(); // data structure initialized
	  
	  if (f.equals("stdin")) {
		  readStringsFromStdInput(); // read strings from standard input
	  } else {
		  try {
			readStringsFromFile(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // read strings from file f otherwise
	  }
	  
	  for (String name : _nodeList) { // creating doubly linked list
		  DNode currentNode = new DNode(name, null, null); // initial new node
		  if (super.header.next == super.trailer) { // if only 2 nodes (header & trailer)
			  super.addAfter(super.header, currentNode); // add new node after header
		  } else {
			  super.addLast(currentNode); // add new node at tail of list otherwise
		  }
	  }
  }

  /** Private function to read strings from standard input */
  private void readStringsFromStdInput() {
	  String input = null;
	  while (true) { // execute following block of code as long as there is no initial string input
		  _scanner = new Scanner(System.in); // declare Scanner object 
		  input = _scanner.nextLine(); // assigns input to the whole String line that user has entered
		  
		  if (input.isEmpty()) {
			  break; // if user did not enter anything and hence the input is empty, break out of this loop
		  } else {
			  _nodeList.add(input); // if user entered something, add that input into _nodeList
		  }
	  }
  }

  /** Private function to read strings from file in directory */
  private void readStringsFromFile(String f) throws FileNotFoundException {
	  File file = new File(f); // declare file object  
	  Scanner s = new Scanner(file); // create new Scanner scanning file and references variable s to it
	  while (s.hasNext()) { // when there is the next string separated by default whitespace
		  String scan = s.next(); // scanning the next string within scanner 
		  _nodeList.add(scan); // adds the scanned string to _nodeList
	  }
	  s.close(); // close Scanner variable, s
  }

  /** Q3. Instance method printing elements of a list on standard input */
  public void printList() {
		DNode current = this.header.next; // current is this class's header pointing to next node
		
		while (current != super.trailer) { // if current is not at end of superclass
			System.out.println(current.element); // print out the element at current
			current = current.next; // then move on to the next node
		}
	}
 

  /** Q4. Class method cloning identical copy of doubly linked list */
  public static MyDList cloneList(MyDList u) {
	  MyDList clonedList = new MyDList(); // declare new DList object as clonedList
	  DNode currentNode = u.header.next; // currentNode is u's header pointing to next node
	  
	  while (true) {
		  if (currentNode == u.trailer) { // when currentNode is at last node of DList
			  break; // stop cloning nodes to clonedList (reached end of DList)
		  } else {
			  DNode newNode = new DNode(null, null, null); // assigns newNode to new DNode object created			  
			  newNode.element = currentNode.element; // element of currentNode assigned to element of newNode
			  clonedList.addLast(newNode); // add new node at tail of clonedList		  
			  currentNode = currentNode.next; // currentNode becomes node after it
		  }
		  
	  }
	  return clonedList; // return resulting cloned doubly linked list
   }

  /** Q5. Class method computing union of 2 sets stored in MyDLists u and v, 
   *  returning MyDList storing that union: its time complexity is O(n) as
   *  it calls the private functions, readStringsFromList O(n) and union O(1), 
   *  and adding both functions' time complexities to calculate its complexity
   *  results in O(n) + O(1) = O(n)  */
  public static MyDList union(MyDList u, MyDList v) {
	  List<String> ulist = readStringsFromList(u);
	  List<String> vlist = readStringsFromList(v);
	  List<String> unionList = union(ulist, vlist);
	  
	  MyDList unionMyDList = new MyDList(); // declare new doubly linked list called unionMyDList
	  for (String tmp : unionList) { // extract elements from unionList which is a list into tmp as a String
	 // transfer elements into a new doubly linked list called unionMyDList
		  DNode currentNode = new DNode(tmp, null, null); // initial new node
		  if (unionMyDList.header.next == unionMyDList.trailer) { // if only 2 nodes (header & trailer)
			  unionMyDList.addAfter(unionMyDList.header, currentNode); // add new node after header
		  } else {
			  unionMyDList.addLast(currentNode); // add new node at tail of list otherwise
		  }
	  }
	  return unionMyDList;
  }
  
  /** Q6. Class method computing intersection of 2 sets stored in MyDLists u and v, 
   *  returning MyDList storing that intersection: its time complexity is O(n) as
   *  it calls the private functions, readStringsFromList O(n) and union O(n), 
   *  and adding both functions' time complexities to calculate its complexity
   *  results in O(n) + O(n) = O(n)  */
  public static MyDList intersection(MyDList u, MyDList v) {
	  List<String> ulist = readStringsFromList(u);
	  List<String> vlist = readStringsFromList(v);
	  List<String> intersectionList = intersection(ulist, vlist);
	  
	  MyDList intersectionMyDList = new MyDList(); // declare new doubly linked list called intersectionMyDList
	  for (String tmp : intersectionList) { // extract elements from intersectionList which is a list into tmp as a String
	 // transfer elements into a new doubly linked list called intersectionMyDList
		  DNode currentNode = new DNode(tmp, null, null); // initial new node
		  if (intersectionMyDList.header.next == intersectionMyDList.trailer) { // if only 2 nodes (header & trailer)
			  intersectionMyDList.addAfter(intersectionMyDList.header, currentNode); // add new node after header
		  } else {
			  intersectionMyDList.addLast(currentNode); // add new node at tail of list otherwise
		  }
	  }
	  return intersectionMyDList;
  }
  
  /** Private function readStringsFromList for u and v, where the time
   *  complexity is O(n), as it looping through a single loop linearly
   *  and seeing if the list already contains the current elements, if not,
   *  it will add that element to the list */
  private static List<String> readStringsFromList(MyDList newList) {
	  List<String> strings = new ArrayList<String>(); // declare new ArrayList as strings
	  DNode current = newList.header.next; // current is header pointing to next node
	  
	  while (current != newList.trailer) { // as long as current is not at the end of newList
		  if (strings.contains(current.element)) continue; // if strings already contains current
		  // elements, then continue searching until it does not find current elements, and then add
		  strings.add(current.element); // the current element to strings
		  
		  current = current.next; // move on to the next node at continue above
	  }
	  
	  return strings;
  }

  /** Time complexity is O(1), as it uses a hash set to add all elements of u list and 
   *  v list into the merged union list. For a hash set, complexity is O(1) for insertion,
   *  traversal/search and removal as it offers constant time performance */
  private static <T> List<T> union(List<T> ulist, List<T> vlist) {
	  Set<T> set = new HashSet<T>(); // declare new HashSet object as set
	  set.addAll(ulist); // add all elements from u list to set
	  set.addAll(vlist); // add all elements from v list to set
	  
	  return new ArrayList<T>(set);
  }
 
  /** Time complexity is O(n), as it needs to find an item in an unsorted array 
   *  list through linear search, where n increases without bound */
  private static <T> List<T> intersection(List<T> ulist, List<T> vlist) {
	  List<T> result = new ArrayList<T>(); // declare new ArrayList object as result
	  
	  for (T obju : ulist) { // for object from u list
		  if (vlist.contains(obju)) { // check if v list contains that object
			  result.add(obju); // if it does, add it to resulting intersection list
		  }
	  }
	  return result;
  }

}

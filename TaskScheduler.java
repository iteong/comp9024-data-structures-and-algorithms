/** COMP9024 Assignment Three
 *  Shu Hao Ivan Teong (z3386180)
 *  
 *  We assume that the hardware platform of the target embedded systems is a single processor with m identical cores. 
 *  The task set V={v1, v2, ..., vn} consists of n independent tasks. The execution time of each task is one time unit. 
 *  Each task vi (i=1, 2, ,,, n) has a release time ri and a deadline di (di>ri). All the release times and deadlines 
 *  are non-negative integers. You need to design an algorithm for the task scheduler and implement it in Java. 
 *  
 *  Your task scheduler uses EDF (Earliest Deadline First) strategy to find a feasible schedule for a task set.
 *  A schedule of a task set specifies when each task starts and on which core it is executed. A feasible schedule is a
 *  schedule satisfying all the release time and deadline constraints.
 *  
 *  The EDF strategy works as follows:
 *  At any time t, among all the ready tasks, find a task with the smallest deadline, and schedule
 *  it on an idle core. Ties are broken arbitrarily. A task vi (i=1, 2, ,,, n) is ready at a time t if t􏰁 >= ri holds.
 *  
 *  It can be shown that the EDF strategy is guaranteed to find a feasible schedule whenever one exists for a set of 
 *  independent tasks with unit execution time.*/

package net.datastructures;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TaskScheduler {

	/**
	 * Private function to read tasks from file1 and write feasible schedule to
	 * file2 in directory with available cores of m
	 * 
	 * @throws FileNotFoundException
	 */
	static void scheduler(String file1, String file2, Integer m) {
		HeapPriorityQueue<Integer, Task> pqueue1 = new HeapPriorityQueue<Integer, Task>();
		HeapPriorityQueue<Integer, Task> pqueue2 = new HeapPriorityQueue<Integer, Task>();

		/**
		 * check whether file1 exists to be read and whether it contains the
		 * right format of task attributes when reading
		 */
		File readFile = new File(file1); // declare file object to be read from file1 as readFile
		Scanner s;
		try {
			s = new Scanner(readFile);
			// checking group of 3 elements
			while (s.hasNext()) { // as long as when there is an element next separated by white spaces
				Task task = new Task(); // declare new Task object with empty parameters

				if (!s.hasNextInt()) {          // if 1st/next element is not an integer (task name)
					
					// set the task name for Task object and move forward to that 1st/next element
					task.setTaskname(s.next()); 

					if (s.hasNextInt()) {       // if 2nd/next element is an integer (release time)
						
						// set the release time for Task object and move forward to that 2nd/next element
						task.setReleasetime(s.nextInt());
						 
						// if 3rd/next element is not an integer (deadline time), the format is incorrect
						if (!s.hasNextInt()) { 

							s.close(); // close the scanner
							System.out.println("The task attributes (task name, release time and deadline) of " + readFile + " do not follow the right format.");
							return;
						} else {   
							// else if 3rd/next element is an integer (deadline time), set the deadline time for
							// Task object and move forward to that 3rd element and then jump down to end of loop at ***
							task.setDeadlinetime(s.nextInt());
						}
					} else {       // else if 2nd element is not an integer and thus incorrect (release time),
								   // the pattern is not good and thus the format is incorrect
						s.close(); // close the scanner
						System.out.println("The task attributes (task name, release time and deadline) of " + readFile + " do not follow the right format.");
						return;
					}
				} else {           // else if 1st element is an integer and thus incorrect (task name),
							       // the pattern is not good and thus the format is incorrect (task name)
					s.close();     // close the scanner
					System.out.println("The task attributes (task name, release time and deadline) of " + readFile + " do not follow the right format.");
					return;
				}
				/**
				 * Time Complexity:
				 * 
				 * Inserting all the tasks into any of the priority queue will take O(n log n) time,
				 * where n is the number of tasks. Therefore, time complexity of insertion into pqueue1 is
				 * O(n log n) time.
				 * 
				 */
				
				// insert release time as key and Task object as value for each group of 3 elements into pqueue1
				pqueue1.insert(task.getReleasetime(), task);
				
			} // *** go back to while loop to check the 1st element in next group of 3 elements
			s.close(); // close Scanner
			
			// if it went through all elements through while loop without issues and thus the pattern in the whole text file is good,
			// then the format of all elements in the text file is correct, continue forward to next section where tasks appended into taskList
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(file1 + " does not exist.");
		}


		
		/**
		 * Time Complexity:
		 * 
		 * The total number of removeMin() operations is O(n) as there are at most n tasks in each queue (n tasks in the first queue with release 
		 * times as the keys, and at most n tasks in the queue with deadlines as the keys). So all the removeMin() operations take O(n log n) time.
		 * Insertion's time complexity will be the same as above, where time complexity of inserting into pqueue2 is O(n log n).
		 */
		// create string where tasks will be appended into
		String taskList = "";
		
		// initialize start time = 0 and enter for loop when pqueue1 is not empty, 
		// where time increases by 1 per loop
		for (int time = 0; !pqueue1.isEmpty(); time++) {
			
			// 1st while loop to insert task from pqueue1 to pqueue2: as long as pqueue1 is not 
			// empty and if task's release time = current time
			while (!pqueue1.isEmpty() && pqueue1.min().getKey() == time) { 

				// assign wrapped Task object which is value of minimum (key is  release time, 
				// value is wrapped Task object) in pqueue1 to t
				Task t = pqueue1.min().getValue();

				// remove task from pqueue1 after assigning to t
				pqueue1.removeMin();

				// insert into pqueue2 (tasks will be auto-sorted by earliest deadline time)
				pqueue2.insert(t.getDeadlinetime(), t);
			}

			// initialize counter to check if the number of machine cores is enough
			// to run tasks at certain time
			int counter = 1;

			// 2nd while loop to append tasks to tasklist
			
			// as long as pqueue2 is not empty, and if counter <= number of machine cores
			while (!pqueue2.isEmpty() && counter <= m) { 
				counter += 1; // increase counter by 1 for every iteration

				/**
				 * if task deadline time is less than or equal to start time,
				 * it is a task leftover from previous start time when there are
				 * not enough machine cores, therefore no feasible schedule exists.
				 */
				if (pqueue2.min().getKey() <= time) {
					System.out.println("No feasible schedule exists.");
					return;
				} else { // if a feasible schedule exists

					try {
						
						// assign task name from task to taskName
						String taskName = pqueue2.min().getValue().getTaskname(); 

						// append taskName and time into taskList with white-spaces
						taskList = taskList + taskName + " " + time + " ";

						// remove task(s) from pqueue2 after writing to file, so
						// can sort tasks with same start time for next round
						pqueue2.removeMin();

					} catch (Exception e) {
						// if I/O error occurs
						System.out.print("Cannot write, buffered writer is closed.");
					}

				} // close else loop where a feasible schedule exists

			} // close while loop when there are enough machine cores to handle task processing

		} // close for loop to insert tasks from pqueue1 to pqueue2
		
		/** check whether file2 exists to be written to */
		File writeFile = new File(file2 + ".txt");
		File printFile = new File(file2); // for printing without the ".txt" file extension

		try { 
			if (!writeFile.exists()) { // if file2 does not exist
				try {
					// create file2 to be written to if it does not exist
					writeFile.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				// if file2 already exists
				System.out.println(printFile + " already exists.");
				return;
			}
			

			FileWriter fw = null;
			BufferedWriter bw = null;
			// create FileWriter getting name of File from writeFile object
			fw = new FileWriter(writeFile.getName(), true); 
			// create BufferedWriter to write through
			bw = new BufferedWriter(fw);

			/** write the String of taskList containing appended tasks with white-spaces into file2 all at once,
			 * this is done only when there is a feasible schedule and file2 does not exist */
			bw.write(taskList);
			bw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} // close scheduler

} // close TaskScheduler class

// additional Task class for wrapped Task object to be inserted into heap-based priority queues
class Task {

	// fields of Task class
	private String taskname;
	private Integer releasetime;
	private Integer deadlinetime;

	// empty constructor without parameters, getters and setters to be used to
	// assign parameters
	public Task() {

	}

	// constructor with parameters
	public Task(String taskname, Integer releasetime, Integer deadlinetime) {
		super();
		this.taskname = taskname;
		this.releasetime = releasetime;
		this.deadlinetime = deadlinetime;
	}

	// getters and setters to set fields for Task objects
	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public Integer getReleasetime() {
		return releasetime;
	}

	public void setReleasetime(Integer releasetime) {
		this.releasetime = releasetime;
	}

	public Integer getDeadlinetime() {
		return deadlinetime;
	}

	public void setDeadlinetime(Integer deadlinetime) {
		this.deadlinetime = deadlinetime;
	}
}


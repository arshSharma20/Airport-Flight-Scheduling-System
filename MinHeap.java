/**
 * Implements a min-heap to schedule flights by departure time and priority.
 * Supports insertion, removal, and sorted output of flights.
 * @author Arshdeep Singh Sharma
 */

import java.util.Arrays;

public class MinHeap {

    private Flight[] heap;
    private int size;
    private int capacity;

    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new Flight[capacity];
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int left(int i) { return 2 * i + 1; }
    private int right(int i) { return 2 * i + 2; }

    public void insert(Flight f) {
        if (size == capacity) {
            System.out.println("Heap is full! Cannot add more flights.");
            return;
        }
        heap[size] = f;
        heapifyUp(size);
        size++;
    }

    public Flight peek() {
        // check if size = 0
        if(size==0){
        	System.out.println("No flights scheduled");
        }
        return heap[0];
        
    }

    public Flight remove() {
       if (size == 0) {
            System.out.println("No flights to remove.");
            return null;
        }
        Flight root = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);
        return root;
    }

    /**
     Reorder elements upward to maintain min-heap property.
     Used after inserting a new element at the end.
     */
    private void heapifyUp(int i) {
        int position = i;
        
        while(position >0){
        	// checking on base of priority and swapping if needed.
        	if(heap[parent(position)].compareTo(heap[position]) > 0){
        		swap(position,parent(position));
        		position = parent(position);
        	}else{
        		break;
        	}
        }
    }

    /**
      Reorder elements downward to maintain min-heap property.
     */
    private void heapifyDown(int i) {
        int position =i;
        int nextChild = i;
        
        while(position <size){
        	Flight min = heap[position];
        	int left = left(i);
        	int right= right(i);
        	
        	// Check if left child exists and is smaller than current smallest
		if (left < size && heap[left].compareTo(min) < 0) {
			min = heap[left];
			nextChild = left;
		}

		// Check if right child exists and is smaller than current smallest
		if (right < size && heap[right].compareTo(min) < 0) {
			min = heap[right];
			nextChild = right;
		}

		// If smallest is not the current node, swap and continue heapifying
		if (nextChild != position) {
			swap(position, nextChild);
			position = nextChild;
		}else{
			break;
		}
    
        }
    }

    private void swap(int i, int j) {
        Flight temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public void printHeap() {
        System.out.println("\nFlights currently in heap:");
        for (int i = 0; i < size; i++) {
            System.out.println(heap[i]);
        }
    }


   /**
     Return a sorted array of all flights in ascending order of departure.
     */
    public Flight[] heapSort() {
 
        // Create a copy of the current heap data
        Flight[] sortedArray = Arrays.copyOf(heap, size);
        
        // Apply merge sort
        mergeSort(sortedArray);
        
        return sortedArray;
    }

    /**
     Merge-sort contents of Flight array S.
     */
    private void mergeSort(Flight[] S) {
        int n = S.length;
        if (n < 2) return; // array is trivially sorted
        
        // divide
        int mid = n / 2;
        Flight[] S1 = Arrays.copyOfRange(S, 0, mid);     // copy of first half
        Flight[] S2 = Arrays.copyOfRange(S, mid, n);     // copy of second half
        
        // conquer (recursively sort each half)
        mergeSort(S1);
        mergeSort(S2);
        
        // merge results
        merge(S1, S2, S);
    }

    /**
     Merge contents of arrays S1 and S2 into properly sized array S.
     */
    private void merge(Flight[] S1, Flight[] S2, Flight[] S) {
        int i = 0, j = 0;
        while (i + j < S.length) {
            if (j == S2.length || (i < S1.length && S1[i].compareTo(S2[j]) < 0))
                S[i + j] = S1[i++];  // copy ith element of S1
            else
                S[i + j] = S2[j++];  // copy jth element of S2
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linklist;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author ITLAB2-PC01-STUDENT
 */
public class queuecode {
    public static void main(String[] args) {
        // Creating a queue using a LinkedList
        Queue<String> fruits = new LinkedList<>();

        // Adding elements to the queue
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");

        // Accessing and removing elements from the queue (FIFO)
        String firstElement = fruits.poll(); // Removes and returns "Apple"
        String secondElement = fruits.poll(); // Removes and returns "Banana"

        // Printing the remaining elements in the queue
        System.out.println("Remaining elements in the queue: " + fruits);

        // Adding more elements to the queue
        fruits.add("Date");
        fruits.add("Eggplant");

        // Accessing and removing elements from the queue (FIFO)
        String thirdElement = fruits.poll(); // Removes and returns "Cherry"

        // Printing the remaining elements in the queue
        System.out.println("Remaining elements in the queue: " + fruits);
    }
}
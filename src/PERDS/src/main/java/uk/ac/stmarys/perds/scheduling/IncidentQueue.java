package PERDS.src.main.java.uk.ac.stmarys.perds.scheduling;

import PERDS.src.main.java.uk.ac.stmarys.perds.core.Incident;

import java.util.Comparator;
import java.util.PriorityQueue;

public class IncidentQueue {


    PriorityQueue<Incident> queue;


    public IncidentQueue() {
        this.queue = new PriorityQueue<>(new Comparator<Incident>() {
            @Override
            //creating priority queue that sorts by severity
            public int compare(Incident i1, Incident i2){
                return i1.getSeverity().getPriority() - i2.getSeverity().getPriority();
            }});}



    //Time complexity O(log n)
    public void addIncident(Incident incdident) {
        queue.add(incdident);}



    public void removeIncident(Incident incdident) {
        queue.remove(incdident);}

    //finds the highest priority incident
    //Time complexity of O(1)
    public Incident peekIncident() {
        return queue.peek();}


    public Incident getNext(){
        Incident next = queue.poll();
        if(next != null){
            System.out.println("Processing next incident " + next.getDescription()
            + " with severity " + next.getSeverity().getPriority());
        }
        return next;}



    public boolean isEmpty(){
        return queue.isEmpty();//Time complexity O(1)
    }
    public boolean hasIncidents(){
        return !queue.isEmpty(); //Time complexity O(1)
    }
    public int Size(){
        return queue.size();//Time complexity O(1)
    }
    public void clear(){
        queue.clear();//Time complexity O(1)
    }

}
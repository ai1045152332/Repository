package com.honghe.recordweb.util.base.util;


import java.util.concurrent.ConcurrentLinkedQueue;

public class ListQueue {
	
	private ConcurrentLinkedQueue<Object> linkedqueue;
    
	private Object[] objs;


	 public ListQueue() {
		 
		 linkedqueue = new ConcurrentLinkedQueue<Object>();
	 }
	 
	 public int size(){
	 	return linkedqueue.size();
	 }
	 
	 public void enqueue(Object obj) {
		 linkedqueue.add(obj);
	
	 }
	 
	 public Object dequeue() {
	 	
		 return linkedqueue.poll();
	 }
	 
	 public Object front(){
		return linkedqueue.peek();
	 }
	 
	 public boolean isEmpty() {
	 	return linkedqueue.isEmpty();
	 }
	 
	 public boolean remove(Object obj){
		 
		 return linkedqueue.remove(obj);
	 }
	 public Object next(Object obj){
		 
		objs =  linkedqueue.toArray();
		
		for (int i = 0; i<objs.length;i++) {
			
			if (obj.equals(objs[i])) {
				
				if (i<objs.length-1) {
					 
					return objs[i+1];
				}
				
			}
			
		}
		
		return null;
	 }
}

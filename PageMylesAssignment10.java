import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
Myles Page
Cs 1450 002
Monday - Wednesday
Due 04-28-2021
Assignment 10
Binarry Tree
*/

public class PageMylesAssignment10 {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		//makes the binarry tree
		BinaryTree tree = new BinaryTree();
		
		//read in 
		File parrotFile = new File("parrots.txt");
		Scanner parrotRead = new Scanner(parrotFile);
		
		//reading and adding by line 
		System.out.print("IF ADDED \n______________________________________________________________________________________________________________________________________\n");
		while(parrotRead.hasNext()) {
			int id = parrotRead.nextInt();
			String name = parrotRead.next();
			String songWord = parrotRead.next();
			Parrot parrot = new Parrot(id, name, songWord);
			boolean added = tree.insert(parrot);
			System.out.print(added + " ");
		}
		//calls functions 
		System.out.print("\n\nPARROTS SONG \n______________________________________________________________________________________________________________________________________\n");
		tree.levelOrder();
		System.out.print("\n\nPARROTS NAMES \n______________________________________________________________________________________________________________________________________\n");
		tree.visitLeaves();
		
	}
}

//parrot class
class Parrot {
	
	//variabls
	private int id;
	private String name;
	private String songWord;
	
	//constrictor
	public Parrot(int id, String name, String songWord) {
		this.id = id;
		this.name = name;
		this.songWord = songWord;
	}
	
	//getters
	public  String getName() {
		return this.name;
	}
	public String sing() {
		return this.songWord;
	}
	
	//compare to 
	public int compareTo(Parrot otherParrot) {
		if (this.id < otherParrot.id) {
			return -1;
		}
		else if (this.id > otherParrot.id) {
			return 1;
		}
		else {
			return 0;
		}
	}
}

//binary tree class
class BinaryTree{
	
	//variables
	private TreeNode root;
	
	//constructor
	public BinaryTree() {
		root = null;
	}
	//insert
	public boolean insert(Parrot parrotToAdd) {
		
		//if empty 
		if(root == null) {
			//add node
			root = new TreeNode(parrotToAdd);
		}
		
		//else
		else {
			//nodes
			TreeNode parent = null;
			TreeNode current = root;
			
			//gospot by spot finding the correct location 
			while(current != null) {
				if(parrotToAdd.compareTo(current.parrot) < 0) {
					parent = current;
					current = current.left;
				}
				else if(parrotToAdd.compareTo(current.parrot) > 0) {
					parent = current;
					current = current.right;
				}
				else {
					return false;
				}
			}
			
			//checks if should add left or right 
			if(parrotToAdd.compareTo(parent.parrot) < 0) {
				parent.left = new TreeNode(parrotToAdd);
			}
			
			else {
				parent.right = new TreeNode(parrotToAdd);
			}
			
		}
		
		return true;
	}
	
	//read spot by spot 
	public void levelOrder() {
		
		//if not empty 
		if(root != null) {
			//makes the queue
			Queue<TreeNode> queue = new LinkedList<>();
			//adds the root 
			queue.add(root);
			
			//print queue and adds the new nodes
			while(queue.isEmpty() == false){
				TreeNode tempNode = queue.remove();
				
				System.out.print(tempNode.parrot.sing() + " ");
				
				if(tempNode.left != null) {
					queue.add(tempNode.left);
				}
				
				if(tempNode.right != null) {
					queue.add(tempNode.right);
				}
			}
		}
		
	}
	
	//public visit leaves
	public void visitLeaves() {
		visitLeaves(root);
	}
	//private visit leaves
	private void visitLeaves(TreeNode aNode){
		
		//if root is empty 
	    if (aNode == null) {
	        return;
	    }
	    
	    if (aNode.left == null && aNode.right == null){
	        System.out.print(aNode.parrot.getName() + " ");
	        return;
	    }

	    if (aNode.left != null) {
	    	visitLeaves(aNode.left);
	    }
	    
	    if (aNode.right != null) {
	    	visitLeaves(aNode.right);
	    }
	}
	
	//node function 
	class TreeNode{
		
		//variables
		private Parrot parrot;
		private TreeNode left;
		private TreeNode right;
		
		//constructor
		public TreeNode(Parrot parrot) {
			this.parrot = parrot;
			this.left = null;
			this.right = null;
		}
	}
	
	
}

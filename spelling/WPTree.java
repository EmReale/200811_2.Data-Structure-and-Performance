/**
 * 
 */
package spelling;

//import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Creates a path between two words using a tree and breadth first search
 */

public class WPTree implements WordPath {

	// this is the root node of the WPTree
	private WPTreeNode root;
	// used to search for nearby Words
	private NearbyWords nw; 
	
	public WPTree () {
		this.root = null;
		// TODO initialize a NearbyWords object
		Dictionary d = new DictionaryHashSet();
		DictionaryLoader.loadDictionary(d, "data/dict.txt");
		this.nw = new NearbyWords(d);
	}
	
	public WPTree (NearbyWords nw) {
		this.root = null;
		this.nw = nw;
	}
	
	// Creates a list describing the path of mutations between word1 and word2
	public List<String> findPath(String word1, String word2) {
		
		List<String> path = new LinkedList<String>();
		//Create a queue of WPTreeNodes to hold words to explore
		Queue<WPTreeNode> queue = new LinkedList<WPTreeNode>();
		//Create a visited set to avoid looking at the same word repeatedly
		List<String> visited= new LinkedList<String>();
		//Set the root to be a WPTreeNode containing word1
		this.root = new WPTreeNode(word1,null);
		WPTreeNode currNode = root;
		//Add the initial word to visited
		visited.add(word1);
		//Add root to the queue
		queue.add(root);
		
		//while the queue has elements and we have not yet found word2
		while (!queue.isEmpty()) {
			//remove the node from the start of the queue and assign to currNode
			currNode = queue.remove();
			//get a list of real word neighbours (one mutation from curr's word)
			List<String> neighbours = nw.distanceOne(currNode.getWord(),true);
			  //for each n in the list of neighbours
				for (String n : neighbours) {
					//if n is not visited
					if (!visited.contains(n)) {
				       //add n as a child of currNode
						WPTreeNode child = currNode.addChild(n);
				       //add n to the visited set
						visited.add(n);
				       //add the node for n to the back of the queue
						queue.add(child);
				       //if n is word2
						if (n.equals(word2)) {
							//return the path from child to root
							WPTreeNode lastNode = currNode.addChild(n);
							path = lastNode.buildPathToRoot();
							System.out.println("Path: " + path);
							return path;
						}
					}  
				}
		}
		
		//return null as no path exists
	    return null;
	}
	
	// Method to print a list of WPTreeNodes (for debugging)
	private String printQueue(List<WPTreeNode> list) {
		String ret = "[ ";
		
		for (WPTreeNode w : list) {
			ret+= w.getWord()+", ";
		}
		ret+= "]";
		return ret;
	}
	
	public static void main (String[] args) {
		WPTree tree = new WPTree();
		tree.findPath("spell","mine");
	}
	
}

/* Tree Node in a WordPath Tree. This is a standard tree with each
 * node having any number of possible children.  Each node should only
 * contain a word in the dictionary and the relationship between nodes is
 * that a child is one character mutation (deletion, insertion, or
 * substitution) away from its parent
*/
class WPTreeNode {
    
    private String word;
    private List<WPTreeNode> children;
    private WPTreeNode parent;
    
    //Creates a node with the word w and the parent p
    public WPTreeNode(String w, WPTreeNode p) {
        this.word = w;
        this.parent = p;
        this.children = new LinkedList<WPTreeNode>();
    }
    
    //Add a child node with word s
    public WPTreeNode addChild(String s){
        WPTreeNode child = new WPTreeNode(s, this);
        this.children.add(child);
        return child;
    }
    
    //Produce a list of this children
    public List<WPTreeNode> getChildren() {
        return this.children;
    }
   
    //Creates a path between word2 and the root
    public List<String> buildPathToRoot() {
        WPTreeNode curr = this;
        List<String> path = new LinkedList<String>();
        while(curr != null) {
            path.add(0,curr.getWord());
            curr = curr.parent; 
        }
        return path;
    }
    
    //Get word for the calling object
    public String getWord() {
        return this.word;
    }
    

    public String toString() {
        String ret = "Word: "+word+", parent = ";
        if(this.parent == null) {
           ret+="null.\n";
        }
        else {
           ret += this.parent.getWord()+"\n";
        }
        ret+="[ ";
        for(WPTreeNode curr: children) {
            ret+=curr.getWord() + ", ";
        }
        ret+=(" ]\n");
        return ret;
    }

}


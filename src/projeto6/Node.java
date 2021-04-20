package projeto6;



public class Node {

	public int value;
	public Character symbol;
	public Node left;
	public Node right;
	public Node parent;
	
	public Node(int value,Node parent) {
		this.value = value;
		this.parent= parent;
	}
	public Node(Character c, int value,Node parent) {
		symbol = c;
		this.value = value;
		this.parent= parent;
	}
	public Node(Node l, Node r, int v, Node p) {
		if(l != null)
			left = new Node(l.symbol, l.value, this);
		if(r!= null)
			right = new Node(r.symbol, r.value, this);
		value = v;
		parent = p;
		
	}
	public boolean isLeaf() {
		return left == null && right == null;
	}
	
	public boolean hasOnlyChild() {
		return hasLeft() ^ hasRight();
	}
	
//	public boolean isTheLastOrPenultimate(int treeHeight) {
//		return this.height == treeHeight || this.height == treeHeight-1;
//	}

	public boolean hasLeft() {
		return left != null;
	}
	public int countNode(Node n) {
		if(n == null)
			return 0;
		return 1+countNode(n.left)+countNode(n.right);
	}
	//Pegue o filho que tem a menor altura 
	public Node getChild() {
		return countNode(this.left) > countNode(this.right) ? this.right : this.left;
 	}

	public boolean hasRight() {
		return right != null;
	}

	public void setChilds(Node left, Node right) {
		this.left = left;
		this.right = right;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + value;
		return result;
	}

	public String toString() {
		return Integer.toString(value);
	}

	public boolean equals(Node obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if(obj.value != value)
			return false;
//		Tree other = (Tree) obj;
//		if (value != other.value)
//			return false;
		return true;
	}
}

package projeto6;



public class Node {

	public int frequency;
	public Character symbol;
	public Node left;
	public Node right;
	public Node parent;

	public Node(int value,Node parent) {
		this.frequency = value;
		this.parent= parent;
	}
	public Node(Character c, int frequency,Node parent) {
		symbol = c;
		this.frequency = frequency;
		this.parent= parent;
	}
	public Node(Node left, Node right, int frequency, Node p) {
		this.left = left;
		this.right = right;
		this.frequency = frequency;
		parent = p;
		
	}
	public boolean isLeaf() {
		return left == null && right == null;
	}
	
	
	public boolean hasLeft() {
		return left != null;
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
		result = prime * result + frequency;
		return result;
	}

	public String toString() {
		return Integer.toString(frequency);
	}

	public boolean equals(Node obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if(obj.frequency != frequency)
			return false;
		return true;
	}
}

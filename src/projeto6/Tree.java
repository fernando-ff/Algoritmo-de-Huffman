package projeto6;

import java.util.LinkedList;

public class Tree {

	protected Node root;
	protected int size;

	public boolean insert(Character c, int e) {
		return insert(e, c);
	}

	private boolean insert(int value, Character c) {
		// Se a raiz for nula
		if (root == null) {
			root = new Node(c, value, null);
			size = 1;
			return true;
		} else {
			LinkedList<Node> queue = new LinkedList<>();
			queue.addLast(root);
			boolean inserted = false;
			while (!queue.isEmpty() && !inserted) {
				Node current = queue.removeFirst();
				if (!current.hasLeft()) {
					current.left = new Node(c, value, current);
					inserted = true;
					up(current.left);
					size++;
				} else if (!current.hasRight()) {
					current.right = new Node(c, value, current);
					inserted = true;
					up(current.right);
					size++;
				} else {
					if (current.hasLeft())
						queue.add(current.left);
					if (current.hasRight())
						queue.add(current.right);
				}
			}
			return inserted;
		}
	}

	public void insert(Node left, Node right, int value) {
		// Se a raiz for nula
		if (root == null) {
			root = new Node(left, right, value, null);
			size = 1;
		} else {
			Node r = new Node(root.left, root.right, root.value, root);
			Node k = new Node(left, right, value, root);
			root = new Node(r.left, k.right, value + r.value, null);

		}
	}

	public void insert(Node newNode) {
		// Se a raiz for nula
		if (root == null) {
			root = newNode;
			size = 1;
		} else {
			LinkedList<Node> queue = new LinkedList<>();
			queue.addLast(root);
			boolean inserted = false;
			while (!queue.isEmpty() && !inserted) {
				Node current = queue.removeFirst();
				if (!current.hasLeft()) {
					current.left = new Node(newNode.symbol, newNode.value, current);
					inserted = true;
					up(current.left);
					size++;
				} else if (!current.hasRight()) {
					current.right = new Node(newNode.symbol, newNode.value, current);
					inserted = true;
					up(current.right);
					size++;
				} else {
					if (current.hasLeft())
						queue.add(current.left);
					if (current.hasRight())
						queue.add(current.right);
				}
			}
		}
	}

	private void up(Node n) {
		// Se p valor do nó filho for maior do que o valor do nó pai
		if (n.parent != null && n.value > n.parent.value) {
			// Sustitua os valores deles
			int k = n.value;
			n.value = n.parent.value;
			n.parent.value = k;
			// E compare novamente chamando agr o nó pai com valor att
			up(n.parent);
		}
	}

	public Node remove() {
		if (root == null) {
			return null;
		} else if (root.isLeaf()) {
			Node k = new Node(root.symbol, root.value, null);
			root = null;
			return k;
		}
		Node k = new Node(root.symbol, root.value, null);
		Node lastNode = null;
		LinkedList<Node> queue = new LinkedList<>();
		queue.addLast(root);
		while (!queue.isEmpty()) {
			Node current = queue.removeFirst();
			lastNode = current;

			if (current.hasLeft())
				queue.addLast(current.left);
			if (current.hasRight())
				queue.addLast(current.right);
		}

		root.value = lastNode.value;
		if (lastNode.parent.left != null && lastNode.parent.left.equals(lastNode)) {
			lastNode.parent.left = null;
		} else {
			lastNode.parent.right = null;
		}
		down(root);
		size--;
		return k;

	}

	private void swap(Node n, Node n1) {
		int k = n.value;
		n.value = n1.value;
		n1.value = k;
	}

	private void down(Node n) {
		if (n != null) {
			if (n.right != null && n.left != null && n.value > n.right.value && n.value > n.left.value) {
				if (n.left.value < n.right.value) {
					swap(n.left, n);
					down(n.left);
				} else {
					swap(n.right, n);
					down(n.right);
				}
			} else if (n.left != null && n.value > n.left.value) {
				swap(n.left, n);
				down(n.left);
			} else if (n.right != null && n.value > n.right.value) {
				swap(n.right, n);
				down(n.right);
			}
		}
	}

	public int get() {
		if (root != null)
			return root.value;
		throw new IndexOutOfBoundsException("Heap is empty!");
	}

	public int size() {
		return size;
	}

	public void update(int current, int newValue) {
		if (root != null) {
			LinkedList<Node> queue = new LinkedList<>();
			queue.addLast(root);
			while (!queue.isEmpty()) {
				Node aux = queue.removeFirst();
				if (current == aux.value) {
					int k = aux.value;
					aux.value = newValue;
					if (newValue > k) {
						up(aux);
					} else {
						down(aux);
					}
					return;
				}
				if (aux.hasLeft())
					queue.addLast(aux.left);
				if (aux.hasRight())
					queue.addLast(aux.right);
			}
			throw new IndexOutOfBoundsException("Value not present!");
		} else {
			throw new IndexOutOfBoundsException("Heap is empty!");
		}
	}

	public String toString() {
		return toString(root, "", 0);
	}

	public String toString(Node current, String tabs, int level) {
		if (current == null) {
			return "";
		}
		String str = toString(current.right, tabs + "\t", level + 1);
		str += String.format("%s%s %s [Level:%d]\n", tabs, current,current.symbol, level + 1);
		str += toString(current.left, tabs + "\t", level + 1);
		return str;
	}
}

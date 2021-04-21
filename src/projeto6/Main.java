package projeto6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(new File("arquivo")));
		
		Map<Character, Integer> frequency = new LinkedHashMap<>();
		
		int readed = in.read();
		while(readed != -1) {
			char c = (char) readed;
			if(frequency.containsKey(c)) {
				int value = frequency.get(c);
				value ++;
				frequency.put(c, value);
			} else {
					frequency.put(c, 1);
			}
			readed = in.read();
		}
		
		
		List<Node> list = new ArrayList<Node>();
		
		for(Map.Entry<Character, Integer> m: frequency.entrySet()) {
		        list.add(new Node(m.getKey(), m.getValue(), null));
		}
		
		Comparator<Node> cmp = new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				return o1.value - o2.value;
			}		
		};
		list.sort(cmp);
		
		List<Tree> listTree= new ArrayList<Tree>();
		while(!list.isEmpty()) {
			Tree tree = new Tree();
			if(list.size() > 1 && list.get(0).value == list.get(1).value) {
				tree.root = new Node(list.get(0), list.get(1), list.get(0).value + list.get(1).value, null);
				listTree.add(tree);
				list.remove(0);
				list.remove(0);
			} else {
				tree.root = list.get(0);
				listTree.add(tree);
				list.remove(0);
			}
		}
		
		listTree.forEach(el -> {
			System.out.println(el);
		});
		
		//teste construtor
		Tree tree1 = new Tree();
		
		tree1.root = new Node(listTree.get(0).root, listTree.get(1).root, listTree.get(0).root.value + listTree.get(1).root.value, null);
		
		System.out.println(tree1);
		
	}
}

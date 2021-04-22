package projeto6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class Main {
	public static void main(String[] args) throws IOException {
		Main algoritmo = new Main();
		algoritmo.loading("file2");
		algoritmo.processing();	
		algoritmo.charToBit("file2");
	}
	Map<Character, Integer> listFrequency = new LinkedHashMap<>();
	Map<Character, String> charBits = new LinkedHashMap<>();
	List<Node> list = new ArrayList<Node>();
	Tree tree = new Tree();

	void loading(String FileName) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(new File(FileName)));

		int readed = in.read();
		while(readed != -1) {
			char c = (char) readed;
			if(listFrequency.containsKey(c)) {
				int value = listFrequency.get(c);
				value ++;
				listFrequency.put(c, value);
			} else {
				listFrequency.put(c, 1);
			}
			readed = in.read();
		}

		for(Map.Entry<Character, Integer> m: listFrequency.entrySet()) {
		        list.add(new Node(m.getKey(), m.getValue(), null));
		}
	}
	
	void processing() {
		Comparator<Node> cmp = new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				return o1.frequency - o2.frequency;
			}		
		};
		list.sort(cmp);
		int i = 0;
		while(!list.isEmpty()) {
			if(list.size() == 1) {
				tree.root = list.remove(0);
			}
			else if(list.size() > 1 && list.get(0).frequency+i == list.get(1).frequency) {
				list.add(new Node(list.get(0), list.get(1), list.get(0).frequency + list.get(1).frequency, null));
				list.remove(0);
				list.remove(0);
				list.sort(cmp);
				i = 0;
			} else {
				list.add(list.remove(0));
				list.sort(cmp);
				i++;
			}
		}
		
		System.out.println(tree);
	}
	
	void charToBit(String FileName) throws IOException {
		preOrder(tree.root, "");
		System.out.println(charBits);
		
		BufferedReader in = new BufferedReader(new FileReader(new File(FileName)));
		
		File fileObj = new File(FileName + "-zipado");
		fileObj.createNewFile();
		
		FileWriter out = new FileWriter(FileName + "-zipado");
		int readed = in.read();
		while(readed != -1) {
			char c = (char) readed;
			if(charBits.containsKey(c)) {
				String bits = charBits.get(c);
				out.write(bits);
			}
			readed = in.read();
		}
		out.close();
	}
	

	private void preOrder(Node root, String bits) {
		String aux = bits;
		if (root != null) {
			if(root.symbol != null) {
				charBits.put(root.symbol, bits);
			}
			bits += "0";
			preOrder(root.left, bits);
			bits = aux;
			bits += "1";
			preOrder(root.right, bits);
		}
	}
}

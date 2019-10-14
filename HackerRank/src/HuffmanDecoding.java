abstract class Node implements Comparable<Node> {
    public  int frequency; // the frequency of this tree
    public  char data;
    public  Node left, right;
    public Node(int freq) {
      frequency = freq;
    }

    // compares on the frequency
    public int compareTo(Node tree) {
        return frequency - tree.frequency;
    }
}

class HuffmanLeaf extends Node {


    public HuffmanLeaf(int freq, char val) {
        super(freq);
        data = val;
    }
}

class HuffmanNode extends Node {

    public HuffmanNode(Node l, Node r) {
        super(l.frequency + r.frequency);
        left = l;
        right = r;
    }

}


class Decoding {

	private boolean isLeaf(Node node) {
		return node.left == null && node.right == null;
	}

	void decode(String s, Node root) {
		StringBuilder builder = new StringBuilder();
		Node current = root;
		for (char c : s.toCharArray()) {
			current = c == '0' ? current.left : current.right;
			if (isLeaf(current)) {
				builder.append(current.data);
				current = root;
			}
		}
		System.out.println(builder);
    }

}
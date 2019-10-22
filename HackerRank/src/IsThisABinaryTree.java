/* Hidden stub code will pass a root argument to the function below. Complete the function to solve the challenge. Hint: you may want to write one or more helper functions.

The Node class is defined as follows:
    class Node {
    int data;
    Node left;
    Node right;
     }
*/

class Solution {
	class Node {
	    int data;
	    Node left;
	    Node right;
    }

    boolean checkBST(Node root) {
        return check(root, -1, 10001);
    }

    boolean check(Node node, int mn, int mx) {
        if (node == null) return true;
        if (node.data <= mn || mx <= node.data) return false;
        return check(node.left, mn, Math.min(mx, node.data))
                && check(node.right, Math.max(mn, node.data), mx);
    }



}
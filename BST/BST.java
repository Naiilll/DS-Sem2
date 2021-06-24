package Notes.BST;

public class BST <E extends Comparable<E>> {
// extends AbstractList<E>
    protected TreeNode<E> root;
    protected int size = 0;

    /** Create a default binary tree */
    public BST(){}

    /** Create a binary tree from an array of objects */
    public BST(E[] objects) {
        for (int i = 0; i < objects.length; i++)
            insert(objects[i]);
    }

    /** Searches an element in BST
     * Returns true if the element is in the tree */
    public boolean search (E element){
        TreeNode<E> current = root;
        while (current!=null){
            if (element.compareTo(current.element)<0)
                current = current.left;                    // traverse to the left
            else if (element.compareTo(current.element)>0)
                current = current.right;                   // traverse to the right
            else    // element matches current.element
                return true; // element is found
        }
        return false; // element is not in the tree
    }

    /** Inserts element o into the binary tree
     * Return true if the element is inserted successfully */
    public boolean insert (E element){
        if(root==null)
            root = new TreeNode<>(element);
        else {
            // Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current!=null){
                if(element.compareTo(current.element)<0){
                    parent = current;
                    current = current.left;
                } else if(element.compareTo(current.element)>0){
                    parent = current;
                    current = current.right;
                } else
                    return false; // duplicate node not inserted
            }

            // Create the new node and attach to parent node
            if(element.compareTo(parent.element)<0)
                parent.left = new TreeNode<>(element);
            else parent.right = new TreeNode<>(element);
        }
        size++;
        return true; // element is inserted
    }

    /** Inorder traversal from the root*/
    public void inorder(){
        inorder(root);
    }

    /** Inorder traversal from a subtree
     * Visits left subtree, its node and its right subtree*/
    protected void inorder(TreeNode<E> root){
        if(root==null) return;
        inorder(root.left);
        System.out.print(root.element+" ");
        inorder(root.right);
    }

    /** Postorder traversal from the root */
    public void postorder() {
        postorder(root);
    }

    /** Postorder traversal from a subtree
     * Visit left subtree, right subtree and only its node*/
    protected void postorder(TreeNode<E> root){
        if(root==null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element+" ");
    }

    /** Preorder traversal from the root */
    public void preorder() {
        preorder(root);
    }

    /** Preorder traversal from a subtree
     * Visits the node, its left subtree and its right subtree*/
    protected void preorder(TreeNode<E> root) {
        if (root == null) return;
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }

    /** Get the number of nodes in the tree */
    public int getSize() {
        return size;
    }

    /** Returns the height of the BST */
    public int height() {
        return height(root);
    }

    private int height(TreeNode<E> node) {
        if (node == null) {
            return -1;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    /** Returns the root of the tree */
    public TreeNode<E> getRoot() {
        return root;
    }

    public E minValue() {
        return minValue(root);
    }

    /** Function to return least value recursively */
    private E minValue(TreeNode<E> node) {
        if (node.left == null) {
            return node.element;
        }
        return minValue(node.left);
    }

    public E maxValue() {
        return maxValue(root);
    }

    /** Function to return least value recursively */
    private E maxValue(TreeNode<E> node) {
        if (node.right == null) {
            return node.element;
        }
        return maxValue(node.right);
    }

    /** Returns a path from the root leading to the specified element */
    public java.util.ArrayList<TreeNode<E>> path(E e) {
        java.util.ArrayList<TreeNode<E>> list = new java.util.ArrayList<TreeNode<E>>();
        TreeNode<E> current = root; // Start from the root

        while (current != null) {
            list.add(current); // Add the node to the list
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0) {
                current = current.right;
            }
            else
                break;
        }

        return list; // Return an array list of nodes
    }

    /** Delete an element from the binary tree.
     * Return true if the element is deleted successfully
     * Return false if the element is not in the tree */
    public boolean delete(E e) {
        // Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            }
            else
                break; // Element is in the tree pointed at by current
        }

        if (current == null)
            return false; // Element is not in the tree

        // Case 1: current has no left child
        if (current.left == null) {
            // Connect the parent with the right child of the current node
            if (parent == null) {
                root = current.right;
            }
            else {
                // link the parent node to the right subtree of deleted node
                if (e.compareTo(parent.element) < 0)
                    parent.left = current.right;
                else
                    parent.right = current.right;
            }
        }
        else {
            // Case 2: The current node has a left child
            // Locate the rightmost node in the left subtree of
            // the current node and also its parent
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left; // left subtree

            while (rightMost.right != null) { // traversing the right subtree to find max
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }

            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost)
                parentOfRightMost.right = rightMost.left;
            else
                // Special case: parentOfRightMost == current
                parentOfRightMost.left = rightMost.left;
        }

        size--;
        return true; // Element deleted successfully
    }

    /** Obtain an iterator. Use inorder. */
    public java.util.Iterator<E> iterator() {
        return new InorderIterator();
    }

    // Inner class InorderIterator
    private class InorderIterator implements java.util.Iterator<E> {
        // Store the elements in a list
        private java.util.ArrayList<E> list =
                new java.util.ArrayList<E>();
        private int current = 0; // Point to the current element in list

        public InorderIterator() {
            inorder(); // Traverse binary tree and store elements in list
        }

        /** Inorder traversal from the root*/
        private void inorder() {
            inorder(root);
        }

        /** Inorder traversal from a subtree */
        private void inorder(TreeNode<E> root) {
            if (root == null)return;
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        @Override /** More elements for traversing? */
        public boolean hasNext() {
            if (current < list.size())
                return true;

            return false;
        }

        @Override /** Get the current element and move to the next */
        public E next() {
            return list.get(current++);
        }

        @Override /** Remove the current element */
        public void remove() {
            delete(list.get(current)); // Delete the current element
            list.clear(); // Clear the list
            inorder(); // Rebuild the list
        }
    }

    /** Remove all elements from the tree */
    public void clear() {
        root = null;
        size = 0;
    }
}


/** This inner class is static, because it does not access
 any instance members defined in its outer class */
class TreeNode <E extends Comparable <E>>{
    protected E element;              // element stored in the node
    protected TreeNode<E> left;       // reference to left child
    protected TreeNode<E> right;      // reference to right child

    // Constructor of TreeNode
    public TreeNode (E o){
        element = o;
    }
}

/*
abstract class AbstractTree<E> implements Tree<E>{
    public void inorder(){}
    public void postorder(){}
    public void preorder(){}
    public boolean isEmpty(){return getSize()==0;}

}
*/

// interface Tree<E> extends Iterable<E> {
//    /** Return true if the element is in the tree */
//    public boolean search(E e);
//
//    /** Insert element o into the binary tree
//     * Return true if the element is inserted successfully */
//    public boolean insert(E e);
//
//    /** Delete the specified element from the tree
//     * Return true if the element is deleted successfully */
//    public boolean delete(E e);
//
//    /** Inorder traversal from the root*/
//    public void inorder();
//
//    /** Postorder traversal from the root */
//    public void postorder();
//
//    /** Preorder traversal from the root */
//    public void preorder();
//
//    /** Get the number of nodes in the tree */
//    public int getSize();
//
//    /** Return true if the tree is empty */
//    public boolean isEmpty();
//}


public class BinTree<G extends Comparable<G>>{
  protected Node<G> root;    //Node pointer

  //default constructor
  public BinTree(){
    this.root = null;
  }

  //overloaded constructor
  public BinTree(Node<G> r){
    this.root = r;
  }

  //accessor
  public Node<G> getRoot(){
    return root;
  }

  //mutator
  public void setRoot(Node<G> r){
    this.root = r;
  }

  //inserts a node into the tree
  public void insert(Node<G> add){
    if(this.root == null){
      //if the tree doesn't have a root, then the node is made the root
      this.setRoot(add);
      return;
    }

    else{
      rInsert(this.root, this.root, add);
      //recursive function helper for the parent node, current node, and adding node
      return;
    }
  }

  public void rInsert(Node<G> p, Node<G> c, Node<G> a){
    if(c == null){ //current node is at the end of the branch
      if(a.compareTo(p) < 0){
        p.setLeft(a);
        //node is added to the left of the the parent if it's less
        return;
      }
      else{
        p.setRight(a);
        //node is added to the right of the parent if it's greater
        return;
      }
    }
    else{ //if not at the leaf, must continue traversing
      p = c;
      if(a.compareTo(c) < 0){
        //smaller node means traversing to the left with recursive call
        rInsert(p, c.getLeft(), a);
      }
      else{
        //smaller node means traversing to the right with recursive call
        rInsert(p, c.getRight(), a);
      }
    }
  }

  //returns the node in the tree if found or else returns null
  public Node<G> Search(Node<G> find){
    //call to recursive function helper
    return this.rSearch(this.root, find);
  }

  public Node<G> rSearch(Node<G> current, Node<G> find){
    //if all nodes have been checked and still not found, return null
    if(current == null){
      return null;
    }

    //if nodes matches the one we're looking for, the node is returned
    if(current.compareTo(find) == 0){
        return current;
    }

    else if(current.compareTo(find) < 0){
      //recursive traversal to the right if current node is less than the target node
      return rSearch(current.getRight(), find);
    }

    else{
      //recursive traversal to the left if current node is greater than the target node
      return rSearch(current.getLeft(), find);
    }
  }

  //generic inOrder traversal of the BST to print out it's nodes
  public void print(Node<G> current){
    if(current == null){
      return;
    }
    print(current.getLeft());
    System.out.println(current.getPayload());
    print(current.getRight());
  }

  //deletes the node connected to the BST
  public void delete(G remove){
    this.root = rdelete(this.root, remove);
    //new root of BST has the recursive call to rdelete
  }

  public Node<G> rdelete(Node<G> current, G remove){
    //if the BST is empty, there is nothing to delete
    if(this.root == null){
        return current;
      }
    
    if(current.getPayload().compareTo(remove) < 0){
      current.setRight(rdelete(current.getRight(), remove));
    }
    else if(current.getPayload().compareTo(remove) > 0){
      current.setLeft(rdelete(current.getLeft(), remove));
    }
    else{
      if(current.getLeft() == null){
        return current.getRight();
      }
      else if(current.getRight() == null){
        return current.getLeft();
      }
      current.setPayload(highest(current.getLeft()));
      //deletes the lowest value from the right subtree
      current.setLeft(rdelete(current.getLeft(), current.getPayload()));
    }
    return current;
  }

  G highest(Node<G> curr){
    G low = curr.getPayload();
    while(curr.getRight() != null){
      low = curr.getRight().getPayload();
      curr = curr.getRight();
    }
    return low;
  }
}
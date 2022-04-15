public class Node<G extends Comparable<G>> implements Comparable <Node<G>>{
  //generic class of type 'G'
  //Implements a comparable interface to allow us to compare two node objects
  protected Node<G> left;      //Node Pointers
  protected Node<G> right;
  protected G payload;         //Generic Payload
  
  //default constructor
  public Node(){
    this.left = null;
    this.right = null;
    this.payload = null;
  }

  //overloaded constructor
  public Node(Node<G> l, Node<G> r, G p){
    this.left = l;
    this.right = r;
    this.payload = p;
  }

  //constructor for a node just given a generic payload object
  public Node(G p){
    this.left = null;
    this.right = null;
    this.payload = p;
  }

  //constructor for a node just given a generic Node object
  public Node(Node<G> n){
    this.left = n.getLeft();
    this.right = n.getRight();
    this.payload = n.getPayload();
  }
  //accessors
  public Node<G> getLeft(){
    return left;
  }
  public Node<G> getRight(){
    return right;
  }
  public G getPayload(){
    return payload;
  }
  //mutators
  public void setLeft(Node<G> l){
    this.left = l;
  }
  public void setRight(Node<G> r){
    this.right = r;
  }
  public void setPayload(G p){
    this.payload = p;
  }

  //Prints the node by accessing the payload object and printing the payload
  @Override 
  public String toString(){
    String str1 = (this.getPayload()).toString();
    return str1;
  }

  //compares generic node objects by comparing the payloads of the nodes
  public int compareTo(Node<G> n){
    return this.getPayload().compareTo(n.getPayload());
  }
}
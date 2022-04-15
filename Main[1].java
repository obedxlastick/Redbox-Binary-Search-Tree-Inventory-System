import java.util.*; //File scanner object
import java.io.FileInputStream; //To read from a file
import java.io.IOException; //If file cannot be opened

class Main {

  //to recursively display the binary search specifically for DVD objects
  public static void display(Node<DVD> current){
    if(current == null){
      return;
    }
    display(current.getLeft());   //recursive call to left branch
    System.out.println(current); //Prints the node
    display(current.getRight()); //recursive call to right branch 
    
  }

  public static void main(String[] args) throws IOException{
    BinTree<DVD> tree = new BinTree<>(); //creates an empty binary search tree
    DVD create; //instantiates a DVD object
    Node<DVD> add;  //instantiates a Node object
    Node<DVD> find;  //instantiates a Node object
    String fields[]; //arrays perform string input validation by splitting the line based on spaces
    String Fields[];
    String storage; //holds each line of the file for processing
    System.out.println("Please enter the inventory file name: ");
    Scanner input = new Scanner(System.in);
    String file = input.next();
    //Processes the inventory file
    try{
      FileInputStream dataFile = null;
      Scanner inFS = null;
      dataFile = new FileInputStream(file);
      inFS = new Scanner(dataFile);

      //For the inventory file, all titles needed to be seeded in BST 
      //so new DVD object and Node object is created and inserted into the tree
      while(inFS.hasNextLine()){
        storage = inFS.nextLine();
        fields = storage.split(",");
        //DVD object is based on information parsed from the spaces in the line
        create = new DVD(fields[0], Integer.parseInt(fields[1]), Integer.parseInt(fields[2]));
        add = new Node<>(create);
        //insert function adds the node to the tree
        tree.insert(add);
      }
      dataFile.close();
      inFS.close();
    }

    catch(IOException ex){
      System.out.println("File cannot be opened");
    }
  
    System.out.println("Please enter the transaction file name: ");
    file = input.next();
    //Processes the transaction log file
    try{
      FileInputStream dataFile2 = null;
      Scanner inFS2 = null;
      dataFile2 = new FileInputStream(file);
      inFS2 = new Scanner(dataFile2);

      while(inFS2.hasNextLine()){
        storage = inFS2.nextLine();
        Fields = storage.split(" ", 2);
        if(Fields[0].compareTo("add") == 0){
          //if the action is to add a title...
          create = new DVD(Fields[1].substring(0, Fields[1].indexOf(',')), Integer.parseInt(Fields[1].substring(Fields[1].indexOf(',') + 1, Fields[1].length())), 0);
          find = new Node<>(create);
          add = tree.Search(find);
          if(add != null){
            //if the title already exists, it's available amount is updated with the number of additions
            add.getPayload().setAvailable(add.getPayload().getAvailable() + find.getPayload().getAvailable());
          }
          else{
            //if the title doesn't exist, it's inserted as a new title into the tree
            tree.insert(find);
          }
          
          
        }
        if(Fields[0].compareTo("remove") == 0){
          //if the action is to remove a title..
          create = new DVD(Fields[1].substring(0, Fields[1].indexOf(',')), Integer.parseInt(Fields[1].substring(Fields[1].indexOf(',') + 1, Fields[1].length())), 0);
          find = new Node<>(create);
          add = tree.Search(find);
          if(add != null){
            //if the title exists in the BST, then the amount of available is updated with the number of deductions
            add.getPayload().setAvailable(add.getPayload().getAvailable() - find.getPayload().getAvailable());
            if(add.getPayload().getAvailable() == 0 && add.getPayload().getRented() == 0){
              //if there are no copies of the title available and no copies are rented out
              //then the node is deleted from the BST
              System.out.println("1Deleting: " + add);
              tree.delete(add.getPayload());
            }
          }
          
        }
        if(Fields[0].compareTo("rent") == 0){
          //if the action is to rent a title...
          create = new DVD(Fields[1], 0, 0);
          find = new Node<>(create);
          add = tree.Search(find);
          if(add != null){
            //the number of copies of available of that title is decremented by 1 and copies rented is incremented by 1
            add.getPayload().setAvailable(add.getPayload().getAvailable() - 1);
            add.getPayload().setRented(add.getPayload().getRented() + 1);
            if(add.getPayload().getAvailable() == 0 && add.getPayload().getRented() == 0){
            //if available copies and number rented are both zero, then the title is removed
            System.out.println("2Deleting: " + add);
              tree.delete(add.getPayload());
            }
          }
        }
        if(Fields[0].compareTo("return") == 0){
          //if the action is to return a title..
          create = new DVD(Fields[1], 0, 0);
          find = new Node<>(create);
          add = tree.Search(find);
          if(add != null){
            //the number of coies of available of that title is incremented by 1 and the copies rented is decremented by 1
            add.getPayload().setAvailable(add.getPayload().getAvailable() + 1);
            add.getPayload().setRented(add.getPayload().getRented() - 1);
            if(add.getPayload().getAvailable() == 0 && add.getPayload().getRented() == 0){
              //if available copies and number rented are both zero, then the title is removed
              System.out.println("3Deleting: " + add);
              tree.delete(add.getPayload());
            }
          }
        }
      }
      dataFile2.close();
      inFS2.close();
    }

    catch(IOException ex){
      System.out.println("Second file could not be opened");
    }
    display(tree.getRoot());
    //the binary search tree with the DVD titles is displayed in formatted columns to the console
    input.close();
  }
}
public class DVD implements Comparable<DVD>{ //allows us to compare DVD objects
  protected String title;
  protected int available;
  protected int rented;

  //default constructor
  public DVD(){
    this.title = " ";
    this.available = 0;
    this.rented = 0;
  }

  //overloaded constructor
  public DVD(String t, int a, int r){
    this.title = t;
    this.available = a;
    this.rented = r;
  }

  //accessors
  public String getTitle(){return title;}
  public int getAvailable(){return available;}
  public int getRented(){return rented;}

  //mutators
  public void setTitle(String t){this.title = t;}
  public void setAvailable(int a){this.available = a;}
  public void setRented(int r){this.rented = r;}

  //toString method
  @Override
  public String toString(){
    //removes the first and last quotations from the movie title
    String str = (this.title).substring(1, (this.title).length() - 1); 
    //formats the movie title, copies available, and copies rented into 3 evenly spaced columns
    return String.format("%-30.30s" + " " + "%-2.2s" + " " + "%-2.2s", str, Integer.toString(this.available), Integer.toString(this.rented));
  }

  //compares DVD objects by comparing the titles of each object
  public int compareTo(DVD d){
    return this.getTitle().compareTo(d.getTitle());
  }
  
}
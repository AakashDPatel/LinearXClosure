/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linearxclosure;
import java.io.*;
import java.util.*;
public class linearxclosure{
public static void main(String[] args)
{
try {
// Read the text file
    FileReader fr = new FileReader("data.txt"); // read file
    BufferedReader br = new BufferedReader(fr); // store contains of file in br variable
    Scanner file = new Scanner(br); // scan or read contains of file line by line
    // read the attributes
    String str = file.nextLine(); // read first line and store the value into string
    System.out.println("Attribute List:"+str);
    Set<Object> att = new TreeSet<Object>(); // take a set attribute att
    for (int i = 0 ; i<str.length(); i++) {
        // repeat loop until end of first line
        att.add(str.charAt(i)); // add every attribute into set.
    }
    System.out.println("Stored attributes as a set:"+att);
    // read the functional dependencies
    FDs fd = new FDs(); // all functional dependencies are stored into fd variable which is an object of FDs() method
    // call FDs() method from FDs.java
    while (true)
    {
    str = file.nextLine(); // read next line. now str read functional dependencies and store into it.
    if (str.compareTo("#")== 0) // read upto # . if # comes then break it.
    break;
    // otherwise split into two string. Left side of functional dependency stores into one string array
    // and right side of functional dependency stores into another string array.
    else
    {
    String[] str1 = str.split("->"); // split string when -> comes
    fd.addFD(str1[0], str1[1]); // call addFD()
    }
    }//end of while loop
    System.out.println("Functional dependency:"+fd);
    // Compute closures
    while (true)
    {
    str = file.nextLine(); // read next line
    if (str.compareTo("#")==0) {
        break;
    } else
    {
    Set<Character> X = new TreeSet<Character>(); // take a new set object X
    for (int i=0; i<str.length(); i++) {
        X.add(str.charAt(i)); // add those characters whose closure we have to find ex: BHE
    }
    // call XClosure()
    // final answer should be in [,] format so i removed [] and , between characters
    // for that call toString()
    System.out.println("\n"+ str +" Closure: " + XClosure (fd, X).toString().replaceAll("\\s+","")
    .replace(",", "")
    .replace("[", "")
    .replace("]", ""));
    }
    }// end of while loop
    }// end of try
    catch (FileNotFoundException e)
    {
    System.err.println(e.getMessage());
    }
    }// end of main() method
    // Calculating XClosure Method
    public static Set<Character> XClosure(FDs fd, Set<Character> x)
    {
    Set <Character> closure = new TreeSet<Character>(x); // take a new set object
    int size0 = 0;
    while(size0 < closure.size()) // it trues because closure has value of X
    // which contains intial characters whose closure we have to find ex: BHE
    {
    size0 = closure.size();
    // left object contains left side of FDs which are keys
    // so add those attributes which are right side of keys from closure.
    // call getFDMap()
    // call keySet() which is in-built method of java
    // ex: find BHE closure. First it gets keys(which are at left side of FDs) of B, H, E individually or together
    // Now it adds all attribute from right side. And again check left side of all attributes we get at a first time from a loop
    // if we get another attributes which is a part of keys then it adds otherwise stops a loop and gives a final answer
    // Like, BHE closure: first check B into left side and if it is a part of a key then add right side of attributes in closure
    // B is part of key AB and from it we derives E but E is already in closure so it checks next
    // then check BE together and add I into closure
    // then check E and add G into closure
    // then check GI together and add H into closure
    // now there are not any other attribute from left side is same as closure so it stops and gives the final answer BHEIG.
    for( Set<Character> left : fd.getFDMap().keySet())
    {
    if(closure.containsAll(left)) {
        closure.addAll(fd.getFDMap().get(left));
    }
    }
    } // end of loop
    return closure; // return closure
    } // end of XClosure method
    }// end of program
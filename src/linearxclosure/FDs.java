/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linearxclosure;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
public class FDs
{
private Map<Set<Character>,Set<Character>> FDMap; // declare Map
// Constructor
public FDs()
{
FDMap = new LinkedHashMap<Set<Character>,Set<Character>>(); // create an object of linkedhashmap
}
//Getter and Setter
public Map<Set<Character>, Set<Character>> getFDMap() // get method
{
return FDMap;
}
public void setFDMap(Map<Set<Character>, Set<Character>> fDMap) // set method
{
FDMap = fDMap;
}
// Add new dependency into Functional Dependencies
// call function addFD()
public void addFD (String left0, String right0)
{
// take two new set objects
Set<Character> left = new TreeSet<Character>();
Set<Character> right = new TreeSet<Character>();
// Convert left and right Strings to set of Characters
for(int i = 0; i < left0.length(); i++)
left.add(left0.charAt(i)); // left side of functional dependency now stores into left object as a set
for(int i = 0; i < right0.length(); i++)
right.add(right0.charAt(i)); // right side of functional dependency now stores into right object as a set
if(FDMap.containsKey(left))
{
right.addAll(FDMap.get(left)); // add every right side of FDs which have a key.
//ex : AB -> E then put E into right side
}
FDMap.put(left, right); // all FDs store in left adn right set object as a set
}
// Override toString() method to return FDSet as string
// final answer should be in [,] format so i removed [] and , between characters
public String toString()
{
String s = "";
boolean firstEntry = true;
for(Map.Entry<Set<Character>,Set<Character>> entry :
FDMap.entrySet())
{
if( firstEntry == false )
s = s + " , ";
else
firstEntry = false;
s = s + entry.getKey().toString() + "->" +
entry.getValue().toString();
} return s; } }

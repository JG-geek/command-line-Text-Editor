import java.io.*;
import java.util.*;
class Editor1
{
    public static void main(String[] args)
    throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); // to read input
        InsertList1 lines = new InsertList1(); // creating an object of Doublylinkedlist
        char ch;
        String command,line;
        do {
            System.out.print(": ");
            command = in.readLine(); //reading command
            ch = command.charAt(0);
            switch(ch)  // switch case to identify command mode
            {
                case 'i' : // insert mode
                    do {
                        line = in.readLine(); //read input string
                        if(line.length()==1&&line.charAt(0)=='.')
                            break;
                        lines.insert(line); //inserts string to linkedlist
                    }
                    while(true);
                    break;
                case 'd' :  // delete mode
                    if(lines.isEmpty())
                        System.out.println("?");
                    else
                    {
                        lines.delete(); // deletes a node i.e a line from linkedlist
                        if(!lines.isEmpty())
                            System.out.println(lines.current());
                    }
                    break;
                case '+' :  //change cursor mode
                    if(lines.isEmpty()||lines.atEnd())
                        System.out.println("?");
                    else
                    {
                        lines.forward();
                        System.out.println(lines.current());
                    }
                    break;
                case '-' :  // change cursor mode
                    if(lines.isEmpty()||lines.atStart())
                        System.out.println("?");
                    else
                    {
                        lines.backward();
                        System.out.println(lines.current());
                    }
                    break;
                case 'p': // current line mode
                    if(lines.isEmpty())
                        System.out.println("?");
                    else
                        System.out.println(lines.current());
                    break;
                case 'l': //show content mode
                    System.out.print(lines);
                    break;
                case 's':  // save mode:saves the input by making a file
                    String s=lines.toString();
                    System.out.println(s);
                    String s1=in.readLine();
                    FileWriter fw=new FileWriter(s1);
                    BufferedWriter bfw=new BufferedWriter(fw);
                    
                    bfw.write(s);
                    bfw.close();
                case 'q': break; //quit mode
                default:
                    System.out.println("?");
            }
        }
        while(ch!='q');
    }
}
class InsertList1
{
    private Cell ptr;
    
    public InsertList1()
    {
        ptr=null;
    }
    
    public boolean isEmpty() //to check whether the list is empty or not
    {
        return ptr==null;
    }
    
    public boolean atEnd() // to check the end of linked list
    {
        return ptr.next==null;
    }
    
    public boolean atStart() // to check the start of linked list
    {
        return ptr.prev==null;
    }
    
    public void insert(Object obj) // to insert a node
    {
        if(ptr==null)
            ptr = new Cell(null,obj,null);  // creating first node
        else if(ptr.next==null)
        {
            ptr.next = new Cell(ptr,obj,null); // creating second node
            ptr = ptr.next;
        }
        else
        {
            ptr.next.prev = new Cell(ptr,obj,ptr.next); // creating a new node
            ptr.next = ptr.next.prev;
            ptr=ptr.next;
        }
    }
    
    public void delete() // to delete
    {
        if(ptr.next==null)
        {
            ptr = ptr.prev;
            if(ptr!=null)
                ptr.next=null;
        }
        else
        {
            ptr.next.prev = ptr.prev;
            if(ptr.prev!=null)
                ptr.prev.next = ptr.next;
            ptr=ptr.next;
        }
    }
    
    public void forward() // to change the current line cursor to its next line
    {
        ptr = ptr.next;
    }
    
    public void backward() // to change current line cursor to its previous line
    {
        ptr = ptr.prev;
    }
    
    public Object current() // to get the current cursor line
    {
        return ptr.content;
    }
    
    public String toString() // to store all lines as one string
    {
        Cell temp;
        String str="";
        if(ptr!=null)
        {
            for(temp=ptr; temp.prev!=null; temp=temp.prev);
            for(;temp!=null;temp=temp.next)
            {
                if(temp==ptr)
                    str+="> ";
                else
                    str+="  ";
                str+=temp.content+"\n";
            }
        }
        return str;
    }
// nested class to create node of linked list
    private static class Cell
    {
        Cell prev,next;
        Object content;
        
        Cell(Cell p,Object obj,Cell n)
        {
            prev=p;
            content=obj;
            next=n;
        }
    }
}

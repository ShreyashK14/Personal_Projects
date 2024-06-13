package projectsp;

import java.util.*;

class Student
{
    public int Rno;
    public String Name;
    public int Age;
    public int Marks;

    public static int Generator;

    static {                    //initialization of static varaibles
        Generator = 0;
    }
    public Student(String str,int X,int Y)
    {
        this.Rno = ++Generator;
        this.Name = str;
        this.Age = X;
        this.Marks = Y;
    }
    public void Display()
    {
        System.out.println(this.Rno+" "+this.Name+" "+this.Age+" "+this.Marks);
    }
}

class DBMS
{
    public LinkedList <Student> lobj; //Reference of LinkedLast Class

    public DBMS()
    {
        lobj = new LinkedList<Student>();
    }
    public void StartDBMS()
    {
        System.out.println("Marvellous DBMS started successfully...");
        System.out.println("Table Schema created successfully...");

        String Query;
        String Tokens[];

        Scanner sobj = new Scanner(System.in);
        int TokensCount = 0;

        while(true)
        {
            System.out.println("Marvellous DBMS : ");
            Query = sobj.nextLine();

            Tokens = Query.split(" ");
            
            TokensCount = Tokens.length;

            if(TokensCount == 1)
            {
                if("exit".equals(Tokens[0]))
                {
                    System.out.println("Thank you for using Marvellous DBMS");
                    break;
                }
            }
            else if(TokensCount == 2)
            {}
            else if(TokensCount == 3)
            {}
            else if(TokensCount == 4)
            {
                if("select".equals(Tokens[0]))
                {
                    SelectFrom();
                }
            }
            else if(TokensCount == 5)
            {
                int iRet = 0;
                double dRet = 0;

                if("select".equals(Tokens[0]) && "MAX".equals(Tokens[1]) && "(marks)".equals(Tokens[2]))
                {
                    iRet= Aggregate_Max();
                    System.out.println("Maximum : "+iRet);
                }
                if("select".equals(Tokens[0]) && "MIN".equals(Tokens[1]) && "(marks)".equals(Tokens[2]))
                {
                    iRet= Aggregate_Min();
                    System.out.println("Minimum : "+iRet);
                }
                if("select".equals(Tokens[0]) && "SUM".equals(Tokens[1]) && "(marks)".equals(Tokens[2]))
                {
                    iRet= Aggregate_Sum();
                    System.out.println("Sum : "+iRet);
                }
                if("select".equals(Tokens[0]) && "AVG".equals(Tokens[1]) && "(marks)".equals(Tokens[2]))
                {
                    dRet= Aggregate_Avg();
                    System.out.println("Average : "+dRet);
                }
            }
            else if(TokensCount == 6)
            {
                
            }
            else if(TokensCount == 7)
            {
                if("insert".equals(Tokens[0]))
                {
                    InsertIntoTable(Tokens[4],Integer.parseInt(Tokens[5]),Integer.parseInt(Tokens[6]) );
                }
                if("delete".equals(Tokens[0]))
                {
                    DeleteFrom(Integer.parseInt(Tokens[6]));
                }
            }
            else if(TokensCount == 8)
            {
                if("select".equals(Tokens[0]))
                {
                    if("Rno".equals(Tokens[5]))
                    {
                        SelectFrom(Integer.parseInt(Tokens[7]));
                    }
                    if("Name".equals(Tokens[5]))
                    {
                        SelectFrom(Tokens[7]);
                    }
                }
            }
        }
    }

    //insert into table
    public void InsertIntoTable(String name,int age,int marks)
    {
        Student sobj = new Student(name,age,marks);
        lobj.add(sobj);
    }

    //Select * from student
    public void SelectFrom()
    {
        System.out.println("Records from the student database are : ");

        for(Student sref : lobj)
        {
            sref.Display();
        }
    }

    //select * from student where Rno = 2
    public void SelectFrom(int no)
    {
        System.out.println("Records from the student database are : ");

        for(Student sref : lobj)
        {
            if(sref.Rno == no)
            {
                sref.Display();
            }    
        }
    }

    //select * from student where Name = 'Rahul'
    public void SelectFrom(String str)
    {

        for(Student sref : lobj)
        {
            if(str.equals(sref.Name))
            {
                sref.Display();
            }    
        }
    }

    //delete from student where RNo = 2;
    public void DeleteFrom(int no)
    {
        int i = 0;

        for(Student sref : lobj)
        {
            if(sref.Rno == no)
            {
                lobj.remove(i);
                break;
            }
            i++;
        }
    }

    //select MAX(marks) from student
    public int Aggregate_Max()
    {
        int iMax = 0;
        for(Student sref : lobj)
        {
            if(sref.Marks > iMax)
            {
                iMax = sref.Marks;
            }
        }
        return iMax;
    }

    //select MIN(marks) from student
    public int Aggregate_Min()
    {
        Student temp = lobj.get(0);
        int iMin = temp.Marks;
        for(Student sref : lobj)
        {
            if(sref.Marks < iMin)
            {
                iMin = sref.Marks;
            }
        }
        return iMin;
    }

    //select SUM(marks) from student
    public int Aggregate_Sum()
    {
        int iSum = 0;

        for(Student sref : lobj)
        {
            iSum = iSum+sref.Marks;
        }
        return iSum;
    }
    //select AVG(marks) from student
    public double Aggregate_Avg()
    {
        int iSum = 0;

        for(Student sref : lobj)
        {
            iSum = iSum+sref.Marks;
        }
        return (iSum/(lobj.size()));
    }

} 

class Program12
{
    public static void main(String args[])
    {
        
        DBMS obj = new DBMS();
        obj.StartDBMS();

    }
}

// Insert Query 
// Insert into student values Rahul 23 67
//    0     1      2       3    4   5   6    
//No of tokens : 7

//Select query 
//select * from student
//   0  1   2    3      
//No of tokens : 4

//Select query 
//delete from student where Rno = 2
//   0   1   2    3        4   5  6      
//No of tokens : 8

//Select query 
//select * from student where Rno = 2
//   0   1   2    3        4   5  6  7     
//No of tokens : 8

//Select query 
//select * from student where Name = Rahul
//   0   1   2    3        4   5   6   7     
//No of tokens : 8

//select query
//delete from student where RNo = 2
//  0       1   2       3     4 5 6
//No of tokens : 7

//select query
//select MAX (marks) from student
//  0      1   2       3      4
//No of tokens : 5
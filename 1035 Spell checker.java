import java.io.*; 
import java.util.*; 

class Item implements Comparable< Item> 
{ 
    int index; 
    String str; 
    Item(int i,String s) 
    { 
        index = i; 
        str = s; 
    } 
    public int compareTo(Item it) 
    { 
        if(index>it.index) 
            return 1; 
        else if(index< it.index) 
            return -1; 
        else  
            return 0; 
    } 
}

public class Main 
{ 
    private static List< Item> dicts = new ArrayList< Item>(); 
    public static void main(String[] args) throws Exception 
    { 
        readFile(); 
    } 

    private static void readFile() throws Exception 
    { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        StringTokenizer st = null; 
        String temp = null; 
        int iCount = 1; 
        while(!(temp = br.readLine()).equals("#")) 
            dicts.add(new Item(iCount++,temp)); 
        int flag = 0; 
        while(!(temp = br.readLine()).equals("#")) 
        { 
            if(flag != 0) 
                System.out.println(); 
            process(temp); 
            flag++; 
        } 
    } 

    private static void process(String str) 
    { 
        List< Item> result = new ArrayList< Item>(); 
        List< Item> temp = null; 
        if(isHave(str)) 
        { 
            System.out.print(str + " is correct"); 
            return; 
        } 
        temp = getArrayList(str.length() + 1); 
        for(Item it : temp) 
            if(judge(str,it.str)) 
                result.add(it);
        temp = getArrayList(str.length() - 1); 
        for(Item it : temp) 
            if(judge(str,it.str)) 
                result.add(it);
        temp = getArrayList(str.length()); 
        for(Item it : temp) 
            if(judge1(str,it.str)) 
                result.add(it);
        Collections.sort(result);
        
        System.out.print(str + ":"); 
        if(result.size() != 0) 
            System.out.print(" "); 
        int flag = 0; 
        for(Item it : result) 
        { 
            if(flag!=0) 
                System.out.print(" "); 
            System.out.print(it.str); 
            flag++; 
        } 
    } 

    private static boolean judge1(String src,String dest) 
    { 
        int i = 0; 
        int count = 0; 
        while(i< src.length()) 
        { 
            if(src.charAt(i)!=dest.charAt(i)) 
            { 
                count++; 
            } 
            if(count>1) 
                return false; 
            i++; 
        } 
        return true; 
    } 

    private static boolean judge(String src,String dest) 
    { 
        String min = dest; 
        String max = src; 
        if(src.length() < dest.length()) 
        { 
            min = src; 
            max = dest; 
        } 
        int count = 0; 
        int i = 0; 
        int j = 0; 
        while(i < max.length()&&j < min.length()) 
        { 
            if(max.charAt(i) != min.charAt(j)) 
            { 
                count++; 
                i++; 
            } 
            else 
            { 
                i++; 
                j++; 
            } 
            if(count > 1) 
                return false; 
        } 
        return true; 
    } 

    private static boolean isHave(String str) 
    { 
        for(Item it : dicts) 
        { 
            if(it.str.equals(str)) 
                return true; 
        } 
        return false; 
    } 

    private static ArrayList< Item> getArrayList(int n) 
    { 
        ArrayList< Item> result = new ArrayList< Item>(); 
        for(Item it : dicts) 
        { 
            if(it.str.length()==n) 
                result.add(it); 
        } 
        return result; 
    } 
}
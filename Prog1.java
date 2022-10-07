import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;


public class Prog1 {
    public static void main(String[] args)
    {
        Scanner myScanner = new Scanner(System.in);
        System.out.print("Please enter file path/name : ");
        String fileName = myScanner.nextLine();
        HashMap<Character, Integer> map = new HashMap<>();
        HashMap<Character, String> HuffmanCode = new HashMap<>();
        String code = "";

        try
        {
            File file = new File(fileName);
            FileReader fReader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(fReader);

            String line;
            int count = -1;
            while((line = bReader.readLine()) != null)
            {
                char[] nLine = line.toCharArray();

                for (char c : nLine) {
                    if (!map.containsKey(c))
                    {
                        map.put(c, 1);
                    }
                    else
                    {
                        map.put(c, map.get(c) + 1);
                    }
                }
                count++;

            }
            if(count > 0)
            {
                map.put(Character.valueOf((char)0), count);
            }

            fReader.close();
            Heap heap = new Heap(map);
            while(heap.getSize() > 1)
            {
                HeapNode left = heap.deleteMin();
                HeapNode right = heap.deleteMin();
                HeapNode newNode = new HeapNode(null, (left.freq+ right.freq), left, right);
                heap.insert(newNode);
            }

            System.out.println("Frequency counts are: " + map + "\n");
            encode(heap.peek(), code, HuffmanCode);
            System.out.println("Huffman Codes are: " + HuffmanCode + "\n");

            FileReader fReader2 = new FileReader(file);
            BufferedReader bReader2 = new BufferedReader(fReader2);
            StringBuilder sBuffer = new StringBuilder();

            System.out.print("The original file is: ");

            String line2;
            int count2 = 0;
            while((line2 = bReader2.readLine()) != null)
            {
                System.out.println(line2);
                char[] nLine2 = line2.toCharArray();
                if(count2 > 0)
                {
                    sBuffer.append(HuffmanCode.get(Character.valueOf((char)0)));
                }
                for (char c : nLine2)
                {
                    if (HuffmanCode.containsKey(c))
                    {
                        sBuffer.append(HuffmanCode.get(c));
                    }
                }
                count2++;

            }
            fReader2.close();

            System.out.println("\nThe encoded string is:\n" + sBuffer + "\n");


        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }

    }

    public static void encode(HeapNode node, String str, HashMap<Character, String> HuffmanCode)
    {
        if(node == null)
        {
            return;
        }

        if(node.left == null && node.right == null)
        {
            if(!str.isEmpty())
            {
                HuffmanCode.put(node.ch, str);
            }
            else
            {
                HuffmanCode.put(node.ch, "1");
            }
        }
        encode(node.left, str + '0', HuffmanCode);
        encode(node.right, str + '1', HuffmanCode);
    }
}

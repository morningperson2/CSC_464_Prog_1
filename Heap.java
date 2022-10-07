import java.util.HashMap;
import java.util.Map;


public class Heap {

    HeapNode[] array;

    int numElements;

    int capacity;

    public Heap(HashMap<Character, Integer> map)
    {
        capacity = map.size();

        array = new HeapNode[capacity+1];
        numElements = 0;

        for(Map.Entry<Character, Integer> entry : map.entrySet())
        {
            Character k = entry.getKey();
            Integer v = entry.getValue();
            HeapNode temp = new HeapNode(k,v);
            insert(temp);
        }

    }

    public HeapNode peek()
    {
        return array[0];
    }

    public HeapNode deleteMin()
    {
        if(numElements == 0)
        {
            System.out.println("The heap is empty.");
            return null;
        }
        else
        {
            HeapNode temp = array[0];
            array[0] = array[numElements-1];
            numElements--;
            array[numElements] = null;
            if(numElements > 0)
            {
                siftDown(0);
            }
            return temp;
        }
    }

    public void insert(HeapNode node)
    {

        if(numElements == capacity)
        {
            System.out.println("The heap is full.");
        }
        else
        {
            numElements++;
            array[numElements-1] = node;
            siftUp(numElements-1);
        }
    }

    public int getSize()
    {
        return numElements;
    }

    private void siftUp(int nodeIndex)
    {
        int parentIndex;
        if(nodeIndex != 0)
        {
            parentIndex = (nodeIndex - 1)/2;
            if(array[parentIndex].freq > array[nodeIndex].freq)
            {
                swap(parentIndex, nodeIndex);
                siftUp(parentIndex);
            }
        }
    }

    private void siftDown(int node)
    {
        int leftChild, rightChild;
        int min;
        leftChild = (node * 2) + 1;
        rightChild = (node * 2) + 2;
        if(rightChild >= numElements)
        {
            if(leftChild >= numElements)
            {
                return;
            }
            else
            {
                min = leftChild;
            }
        }
        else
        {
            if(array[leftChild].freq <= array[rightChild].freq)
            {
                min = leftChild;
            }
            else
            {
                min = rightChild;
            }
        }
        if(array[node].freq > array[min].freq)
        {
            swap(node, min);
            siftDown(min);
        }
    }


    private void swap(int pos1, int pos2)
    {
        HeapNode temp;
        temp = array[pos1];

        array[pos1] = array[pos2];
        array[pos2] = temp;
    }
}

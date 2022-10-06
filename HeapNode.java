public class HeapNode {
    Character ch;
    Integer freq;
    HeapNode left = null;
    HeapNode right = null;

    public HeapNode(Character ch, Integer freq)
    {
        this.ch = ch;
        this.freq = freq;
    }

    public HeapNode(Character ch, Integer freq, HeapNode left, HeapNode right)
    {
        this.ch = ch;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }


}

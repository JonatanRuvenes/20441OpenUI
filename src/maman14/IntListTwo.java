package maman14;

public class IntListTwo {
    IntNodeTwo _head, _tail;

    public IntListTwo() {
        _head = null;
        _tail = null;
    }

    /**
     * this method add new element to the end of the list
     *
     * @param num the value of the new element that will be added
     */
    public void addToEnd(int num) {
        IntNodeTwo node = new IntNodeTwo(num);
        if (_head == null) { //the list is empty
            _head = node;
            _tail = node;
        } else {
            _tail.setNext(node);
            node.setPrev(_tail);
            _tail = node;
        }
    }

    /**
     * this method convert the list to string in "{a, b, ...}" format
     *
     * @return the list in String format
     */
    public String toString() {
        if (_head == null) {
            return "{}";
        }
        String val = "{" + _head.getNum();
        IntNodeTwo current = _head.getNext();
        while (current != null) {
            val = val + ", " + current.getNum();
            current = current.getNext();
        }
        val += "}";
        return val;
    }

    /**
     * this method convert the list to string in "{a, b, ...}" format that a-is the last num b-is the num before the last and so on...
     *
     * @return the reverse list in String format
     */
    public String toStringReverse() {
        if (_head == null) {
            return "{}";
        }
        return "{" + toStringReverse(_head) + "}";
    }

    /**
     * this method convert the list to string in "a, b, ..." format that a-is the last num b-is the num before the last and so on...
     *
     * @return the reverse list in String format
     */
    private String toStringReverse(IntNodeTwo node) {
        if (node == _tail) {
            return node.getNum() + "";
        }
        return toStringReverse(node.getNext()) + ", " + node.getNum();
    }

    /**
     * this method check if there is a path from the head to tail that from every element the path can move only x steps right or left determined by the element value
     * @return if there is a path from the head to the tail of the list
     */
    public boolean isWay() {
        if (_head == null) // the empty path
            return true;

        return isWay(_head);
    }

    /**
     * this method check if there is a path from the start to tail that from every element the path can move only x steps right or left determined by the element value
     * @param node the starting position to search from
     * @return if there is a path from the head to the tail of the list
     */
    private boolean isWay(IntNodeTwo node) {
        if (node == null || node.getNum() == -1)
            return false;
        if (node == _tail)
            return true;
        int steps = node.getNum();
        node.setNum(-1);
        return isWay(moveBack(node,steps)) || isWay(moveForward(node,steps));
    }

    /**
     * this method move x step back in a list
     * @param node the list
     * @param steps the amount of steps to move
     * @return the element in the x steps back in the list
     */
    private IntNodeTwo moveBack(IntNodeTwo node, int steps){
        if(steps == 0)
            return node;
        if(node == null)
            return null;
        return moveBack(node.getPrev(), steps-1);
    }

    /**
     * this method move x step forward in a list
     * @param node the list
     * @param steps the amount of steps to move
     * @return the element in the x steps forward in the list
     */
    private IntNodeTwo moveForward(IntNodeTwo node, int steps){
        if(steps == 0)
            return node;
        if(node == null)
            return null;
        return moveForward(node.getNext(), steps-1);
    }
}


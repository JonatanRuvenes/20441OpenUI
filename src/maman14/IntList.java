package maman14;

public class IntList {
    private IntNode _head;

    public IntList( ) {
        _head = null;
    }

    public void addToEnd(int num) {
        // adds num at the end of the list
        IntNode node = new IntNode(num);
        if (_head == null)
            _head = node;
        else {
            IntNode ptr = _head;
            while (ptr.getNext( ) != null)
                ptr = ptr.getNext( );
            ptr.setNext(node);
        }
    }

    public String toString()
    {
        String s = "";
        IntNode temp = _head;
        while (temp != null)
        {
            s = s+ temp.getValue() + " --> ";
            temp = temp.getNext();
        }
        s+= " null";
        return s;
    }

    /**
     * this method gets a full whole number and returns true or false if there is a sublist that her sum is the given number
     *
     * Time complexity: O(n^2)
     *      the method look for any element int the list as the head of the potential sublist
     *      and then move for any other element to see if the sublist sum is num
     *
     * Space complexity: O(1)
     *      the methode declare only one new element (int sum)
     *      after that she use only pre declared elements
     *
     * @param num - full whole number
     * @return if there is a sublist that her sum is the given number
     */
    public boolean subListSum(int num){
        IntNode current = _head;
        int sum;
        while (current != null){
            IntNode next = current;
            sum = num;
            while (next != null && sum > 0){
                sum -= next.getValue();
                next = next.getNext();
            }
            if (sum == 0)
                return true;
            current = current.getNext();
        }
        return false;
    }

    /**
     *  this method finds and return the element that cuts the list to the two sublist with the max difference in the two average
     *
     *  Time complexity: O(n)
     *      the method look for every element twice
     *      one when it calculates the sum of the list and find it length
     *      again when it checks what are the averages of the two sublist
     *      O(n+n) = O(2n) ~ O(n)
     *  Space complexity: O(1)
     *      the method creates only 7 new elements
     *      (sum1, sum2, length, place, max)
     *      the others are using existing memory (current, maxAvNode)
     *      O(7) ~ O(1)
     *
     * @return the element that cuts the list to the two sublist with the max difference in the two average
     */
    public IntNode averageNode(){
        if(_head == null || _head.getNext() == null) // if the list length is 0 or 1
            return null;

        //sum all the elements in the list and find the list length
        IntNode current = _head;
        // sum1 = the sum of all elements right to "center" (included)
        // sum2 = the sum of all elements left to "center"
        int sum1=0, sum2=0;
        double length = 0;

        while(current != null){
            length++;
            sum2 += current.getValue();
            current = current.getNext();
        }


        current = _head; //init current again
        double place = 1; // the amount of elements in sum1
        double max = -1; // maximum abs different between sum1 to sum2
        IntNode maxAvNode = null; // save the element in the
        while (current.getNext() != null){
            sum1 += current.getValue(); //adding current value to sum1
            sum2 -= current.getValue(); //remove current value to sum2

            double av1 = sum1/place, av2 = sum2/(length-place);
            if(Math.abs(av1-av2) >= max){ //this is the max abs difference
                max = Math.abs(av1-av2);
                maxAvNode = current;
            }

            current = current.getNext();
            place ++;
        }

        return maxAvNode;
    }
}

//Team 2/Peglegs- Andie Ma, Denis Duman
//APCS1 pd10
//HW42- Array of Titanium
//2015-12-6

public class SuperArray {
 
    //~~~~~INSTANCE VARS~~~~~
    //underlying container, or "core" of this data structure:
    private Comparable[] _data;

    //position of last meaningful value
    private int _lastPos;

    //size of this instance of SuperArray
    private int _size;

		
    //~~~~~METHODS~~~~~
    //default constructor – initializes 10-item array
    public SuperArray() 
    { 
	_data = new Comparable[10];
	_lastPos = -1; //flag to indicate no lastpos yet
	_size = 0;	
    }

		
    //output array in [a,b,c] format, eg
    // {1,2,3}.toString() -> "[1,2,3]"
    public String toString() 
    { 
	String foo = "[";
	for( int i = 0; i < _size; i++ ) {
	    foo += _data[i] + ",";
	}
	//shave off trailing comma
	if ( foo.length() > 1 )
	    foo = foo.substring( 0, foo.length()-1 );
	foo += "]";
	return foo;
    }

		
    //double capacity of this SuperArray
    private void expand() 
    { 
	Comparable[] temp = new Comparable[ _data.length * 2 ];
	for( int i = 0; i < _data.length; i++ )
	    temp[i] = _data[i];
	_data = temp;
    }

		
    //accessor -- return value at specified index
    public Comparable get( int index ) { return _data[index]; }

		
    //mutator -- set value at index to newVal, 
    //           return old value at index
    public Comparable set( int index, Comparable newVal ) 
    { 
 	Comparable temp = _data[index];
	_data[index] = newVal;
	return temp;
    }


    // ~~~~~~~~~~~~~~ PHASE II ~~~~~~~~~~~~~~
    //adds an item after the last item
    public void add( Comparable newVal ) {    	
	if (_size == _data.length) expand();	
	_lastPos ++;
	_size ++;
	set(_lastPos, newVal);
    }


    //inserts an item at index
    //shifts existing elements to the right
    public void add( int index, Comparable newVal ) {
    	if (_size == _data.length) expand();
	for (int i = _size; i > index; i--) {
	    _data[i] = _data[i - 1];
	}
	_data[index] = newVal;
	_size += 1;
	_lastPos += 1;
    }

    //removes the item at index
    //shifts elements left to fill in newly-empted slot
    public void remove( int index ) {
    	for (int i = index; i < _data.length - 1; i++){
	    Comparable temp = _data[i + 1];
	    set(i, temp);
    	}
	_size--;
	_lastPos--;
    }

    //return number of meaningful items in _data
    public int size() {
	return _size;
    }

    public int linSearch(Comparable C){
	int index = -1;
	for (int i=0; i<_size; i++){
	    if (_data[i].compareTo(C)==0) {
		index = i;
	    }
	}
	return index;
    }

    public boolean isSorted(){
	boolean val = true;
	for (int i=0; i<_size-1; i++){
	    if (_data[i].compareTo(_data[i+1])==1) {
		return false;
	    }
	}
	return val;
    }

    //main method for testing
    public static void main( String[] args ) {

	SuperArray mayfield = new SuperArray();
	System.out.println("Printing empty SuperArray mayfield...");
	System.out.println(mayfield);

	mayfield.add(new Binary(1));
	mayfield.add(new Binary(5));
	mayfield.add(new Hexadecimal(7));
	mayfield.add(new Rational(10,1));

	System.out.println("Printing populated SuperArray mayfield...");
	System.out.println(mayfield);
	 
	mayfield.remove(0);
	System.out.println("Printing SuperArray mayfield post-remove...");
	System.out.println(mayfield);

	mayfield.add(1,new Binary(8));
	System.out.println("Printing SuperArray mayfield post-insert...");
	System.out.println(mayfield);
	mayfield.add(3,new Hexadecimal(11));
	System.out.println("Printing SuperArray mayfield post-insert...");
	System.out.println(mayfield);
	mayfield.add(5,new Rational(20,4));
	System.out.println("Printing SuperArray mayfield post-insert...");
	System.out.println(mayfield);

	System.out.println(mayfield.linSearch(new Binary(8)));
	System.out.println(mayfield.linSearch(new Hexadecimal(7)));
	System.out.println(mayfield.linSearch(new Rational(10,1)));

	System.out.println(mayfield.isSorted());

    }//end main
		
}//end class

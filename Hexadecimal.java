//Team 1031/1225- Kate Johnston and Andrea Ma
//APCS1 pd10
//HW44- This or That or Fourteen Other Things
//2015-12-08

public class Hexadecimal implements Comparable{

    // instance variables
    
    private int _decNum;
    private String _hexNum;
    private final static String HEXDIGITS = "0123456789ABCDEF";

    // constructors

    /*=====================================
      default constructor
      pre:  n/a
      post: initializes _decNum to 0, _hexNum to "0"
      =====================================*/
    public Hexadecimal() { 
        _decNum = 0;
	_hexNum = "0";
    }

    /*=====================================
      overloaded constructor
      pre:  n >= 0
      post: sets _decNum to n, _hexNum to equiv string of bits
      =====================================*/
    public Hexadecimal( int n ) {
        _decNum = n;
	_hexNum = decToHex(n);
    }

    /*=====================================
      overloaded constructor
      pre:  s is String representing non-negative hexadecimal number
      post: sets _hexNum to input, _decNum to decimal equiv
      =====================================*/
    public Hexadecimal( String s ) {
        _decNum = hexToDec(s);
	_hexNum = s;
    }

    // methods
    public int getDecNum(){
	return _decNum;
    }
    
    /*=====================================
      String toString() -- returns String representation of this Object
      pre:  n/a
      post: returns String of hexadecimal representing value of this Object
      =====================================*/
    public String toString() { 
        return _hexNum;
    }

    /*=====================================
      String decToHex(int) -- converts base-10 input to base-16
      pre:  n >= 0
      post: returns String of hexadecimal
      =====================================*/
    public static String decToHex( int n ) {
	int r = 0;
	String h = "";
	while ( n != 0 ) {
	    r = n % 16;
	    n /= 16;
	    h = HEXDIGITS.substring(r,r+1) + h;
	}
	return h;
    }

    /*=====================================
      String decToHexR(int) -- converts base-10 input to hexadecimal, recursively
      pre:  n >= 0
      post: returns String of hexadecimal
      =====================================*/
    public static String decToHexR(int n){
	return decToHexR(n,""); // helper method below
    }
    
    public static String decToHexR(int n, String h) { 
        if ( n == 0 ) {
	    return h;
	}
        else {
	    String b1 = ( Integer.parseInt(HEXDIGITS.substring(n,n+1)) % 16 ) + h;
	    return decToHexR(n/16,b1);
	}
    }

    /*=====================================
      String hexToDec(String) -- converts base-10 input to hexadecimal
      pre:  s represents non-negative hexadecimal number
      post: returns decimal equivalent as int
      =====================================*/
    public static int hexToDec( String s ) {
	String dec = "";
	for (int i = 0 ; i < s.length() ; i++) {
	    dec += HEXDIGITS.indexOf(s.substring(i, i + 1))
		* Math.pow(16, s.length() - i - 1);
	}
	return Integer.valueOf(dec); 
    }

    /*=====================================
      String hexToDecR(String) -- converts base-10 input to hexadecimal, recursively
      pre:  s represents non-negative hexadecimal
      post: returns decimal equivalent as int
      =====================================*/
    public static int hexToDecR( String s ) { 
	if( s.length() == 1 ) {
	    return HEXDIGITS.indexOf(s);
	}
	else {
	    return hexToDecR( s.substring(1) )
		+ HEXDIGITS.indexOf(s.substring(0,1))
		* (int)Math.pow(16, s.length() - 1);
	}
    }

    /*=============================================
      boolean equals(Object) -- tells whether 2 Objs are equivalent
      pre:  other is an instance of class Hexadecimal
      post: Returns true if this and other are aliases (pointers to same 
      Object), or if this and other represent equal values
      =============================================*/
    public boolean equals( Object other ) { 
	Hexadecimal otherHex = (Hexadecimal)(other);
	return this.compareTo(otherHex) == 0;
    }

    /*=============================================
      int compareTo(Object) -- tells which of two Hexadecimal objects is greater
      pre:  other is instance of class Hexadecimal
      post: Returns 0 if this Object is equal to the input Object,
      negative integer if this<input, positive integer otherwise
      =============================================*/
    public int compareTo( Object other ) {
        if (other instanceof Comparable) {
            if (other instanceof Binary) {
                if (this._decNum==((Binary)other).getDecNum()) {
                    return 0;
                }
                else if (this._decNum<((Binary)other).getDecNum()) {
                    return -1;
                }
                else {
                    return 1;
                }
            }
            if (other instanceof Hexadecimal) {
                if (this._decNum==((Hexadecimal)other).getDecNum()) {
                    return 0;
                }
                else if (this._decNum<((Hexadecimal)other).getDecNum()) {
                    return -1;
                }
                else {
                    return 1;
                }
            }
            if (other instanceof Rational) {
                if (this._decNum==((Rational)other).floatValue()) {
                    return 0;
                }
                else if (this._decNum<((Rational)other).floatValue()) {
                    return -1;
                }
                else {
                    return 1;
                }
            }
        }
        throw new ClassCastException("\ncompareTo() input not a Binary");
    }


    //main method for testing
    public static void main( String[] args ) {

	System.out.println();
	System.out.println( "Testing ..." );

	Hexadecimal b1 = new Hexadecimal(15);
	Hexadecimal b2 = new Hexadecimal(15);
	Hexadecimal b3 = b1;
	Hexadecimal b4 = new Hexadecimal(7);

	System.out.println( b1 );
	System.out.println( b2 );
	System.out.println( b3 );       
	System.out.println( b4 );

	System.out.println( "\n==..." );
	System.out.println( b1 == b2 ); //should be false
	System.out.println( b1 == b3 ); //should be true

	System.out.println( "\n.equals()..." );
	System.out.println( b1.equals(b2) ); //should be true
	System.out.println( b1.equals(b3) ); //should be true
	System.out.println( b3.equals(b1) ); //should be true
	System.out.println( b4.equals(b2) ); //should be false
	System.out.println( b1.equals(b4) ); //should be false

	System.out.println( "\n.compareTo..." );
	System.out.println( b1.compareTo(b2) ); //should be 0
	System.out.println( b1.compareTo(b3) ); //should be 0
	System.out.println( b1.compareTo(b4) ); //should be neg
	System.out.println( b4.compareTo(b1) ); //should be pos
	
    } //end main()

} //end class

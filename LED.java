import java.awt.Graphics;

@SuppressWarnings("serial")
public class LED extends Display{
	private final static int OFF = 0;
    private final static int ON = 1;
   
    private final static int ZERO[] = { ON, ON, OFF, ON, ON, ON, ON };
    private final static int ONE[] = { OFF, OFF, OFF, ON, OFF, OFF, ON };
    private final static int TWO[] = { ON, OFF, ON, ON, ON, ON, OFF};
    private final static int THREE[] = { ON, OFF, ON, ON, OFF, ON, ON };
    private final static int FOUR[] = { OFF, ON, ON, ON, OFF, OFF, ON };
    private final static int FIVE[] = { ON, ON, ON, OFF, OFF, ON, ON };
    private final static int SIX[] = { ON, ON, ON, OFF, ON, ON, ON };
    private final static int SEVEN[] = { ON, OFF, OFF, ON, OFF, OFF, ON };
    private final static int EIGHT[] = { ON, ON, ON, ON, ON, ON, ON };
    private final static int NINE[] = { ON, ON, ON, ON, OFF, OFF, ON };
    private final static int HEX_A[] = { ON, ON, ON, ON, ON, OFF, ON };
    private final static int HEX_B[] = { OFF, ON, ON, OFF, ON, ON, ON };
    private final static int HEX_C[] = { ON, ON, OFF, OFF, ON, ON, OFF };
    private final static int HEX_D[] = { OFF, OFF, ON, ON, ON, ON, ON };
    private final static int HEX_E[] = { ON, ON, ON, OFF, ON, ON, OFF };
    private final static int HEX_F[] = { ON, ON, ON, OFF, ON, OFF, OFF };
    private final static int ERROR_R[] = { OFF, OFF, ON, OFF, ON, OFF, OFF };
    private final static int ERROR_O[] = { OFF, OFF, ON, OFF, ON, ON, ON };
    private final static int BLANK[] = { OFF, OFF, OFF, OFF, OFF, OFF, OFF };
	
	final private int X_ORIGIN = 225;
	final private int Y_ORIGIN = 10;
	final private int MAX_NUM_LENGTH = 8;
	
	private int x = X_ORIGIN;
	
	public LED(){
		super();
	}
	
	public void paintComponent(Graphics g){
		if(super.getNum().length() > MAX_NUM_LENGTH){
			super.setNum("Error");
		}
		
		// System.out.println("STEP 1: " + super.getNum());
		
		if(super.getNum().length() < 8)
			super.setNum( addBlanks(super.getNum()) );
		
		// System.out.println("STEP 2: " + super.getNum());
		
		x = X_ORIGIN;
		
		for(int i=super.getNum().length()-1; i>=0; i--){
			char c = super.getNum().charAt(i);
			
			int[] stateArray = selectArray(c);
			
			Bar lineA = new Bar(stateArray[0], x+2, Y_ORIGIN, x+10, Y_ORIGIN);
			lineA.paintComponent(g);
			
			Bar lineB = new Bar(stateArray[1], x, Y_ORIGIN+2, x, Y_ORIGIN+10);
			lineB.paintComponent(g);
				
			Bar lineC = new Bar(stateArray[2], x+2, Y_ORIGIN+12, x+10, Y_ORIGIN+12);
			lineC.paintComponent(g);
			
			Bar lineD = new Bar(stateArray[3], x+12, Y_ORIGIN+2, x+12, Y_ORIGIN+10);
			lineD.paintComponent(g);
			
			Bar lineE = new Bar(stateArray[4], x, Y_ORIGIN+14, x, Y_ORIGIN+22);
			lineE.paintComponent(g);
			
			Bar lineF = new Bar(stateArray[5], x+2, Y_ORIGIN+24, x+10, Y_ORIGIN+24);
			lineF.paintComponent(g);
			
			Bar lineG = new Bar(stateArray[6], x+12, Y_ORIGIN+14, x+12, Y_ORIGIN+22);
			lineG.paintComponent(g);
				
			this.x -= 18;
		}
	}
		
	private int[] selectArray(char c){
		if(c=='0') return ZERO;
		else if(c=='1') return ONE;
		else if(c=='2') return TWO;
		else if(c=='3') return THREE;
		else if(c=='4') return FOUR;
		else if(c=='5') return FIVE;
		else if(c=='6') return SIX;
		else if(c=='7') return SEVEN;
		else if(c=='8') return EIGHT;
		else if(c=='9') return NINE;
		else if(c=='A') return HEX_A;
		else if(c=='B') return HEX_B;
		else if(c=='C') return HEX_C;
		else if(c=='D') return HEX_D;
		else if(c=='E') return HEX_E;
		else if(c=='F') return HEX_F;
		else if(c=='r') return ERROR_R;
		else if(c=='o') return ERROR_O;
		else return BLANK;
	}
	
	@Override
	public void addDigit(String d){
		String tempNum = removeBlanks( super.getNum() );
		super.setNum(tempNum);
		super.addDigit(d);
	}
	
	public String addBlanks(String s){
		// System.out.println("ADDING BLANKS TO NUM");
		
		int numZeroesToAdd = MAX_NUM_LENGTH - s.length();
		
		// System.out.println("ADDING " + numZeroesToAdd + " BLANKS TO NUM");
		String leadingBlanks = "";
		
		for(int i=0; i<numZeroesToAdd; i++){
			leadingBlanks = leadingBlanks + "Z";
		}
		// System.out.println("STEP 1.5: " + leadingBlanks + s);
		return leadingBlanks + s;
	}
	
	public String removeBlanks(String s){
		// System.out.println("REMOVING BLANKS");
		return s.replaceAll("Z", "");
	}
}

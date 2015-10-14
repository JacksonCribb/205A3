import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public enum DiceValue {
	CROWN, ANCHOR, HEART, DIAMOND, CLUB, SPADE;


	private static Random RANDOM = new Random();

	private static final Map<DiceValue, String> VALUE_REPR_MAP= new HashMap<DiceValue, String>();
	static {
		VALUE_REPR_MAP.put(DiceValue.CROWN, "Crown");
		VALUE_REPR_MAP.put(DiceValue.ANCHOR, "Anchor");
		VALUE_REPR_MAP.put(DiceValue.HEART, "Heart");
		VALUE_REPR_MAP.put(DiceValue.DIAMOND, "Diamond");
		VALUE_REPR_MAP.put(DiceValue.CLUB, "Club");
		VALUE_REPR_MAP.put(DiceValue.SPADE, "Spade");
	}

	public String toString(DiceValue value) {
		return VALUE_REPR_MAP.get(value);
	}

	public static DiceValue getRandom() {
		int random = RANDOM.nextInt(DiceValue.SPADE.ordinal() + 1);
		if (random == 7){random = 6;} //just to be sure it never rolls a 7, damn near impossible for it to happen though(is it even possible?)

		//System.out.print(random);
		//System.out.println(values()[random]);

		return values()[random];
	}
	
}

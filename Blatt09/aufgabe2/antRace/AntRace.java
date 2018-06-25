package aufgabe2.antRace;


public class AntRace implements AntFields {

	public static void main(String[] args) {

		AntField field = new AntField(FIELD4);

		Ant ant = null;
		try {
			ant = new Ant(field, 2, 4, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		new Thread(ant).start();

		System.out.println(field.toString());
	}
}

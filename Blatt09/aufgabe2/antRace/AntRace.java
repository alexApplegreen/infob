package aufgabe2.antRace;


public class AntRace implements AntFields {

	public static void main(String[] args) {

		AntField field = new AntField(FIELD4);

		Ant ant = null;
		try {
			ant = new Ant(field, 0, 0, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		new Thread(ant).start();

		synchronized (field) {
			if (field.getAntCount() > 0) {
				try {
					field.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println(field.toString());
	}
}

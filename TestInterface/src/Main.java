
public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Person wild = new Person("Naked");
		wild.show();

		Person gentle = new Person("Manners");
		gentle.wear(new Hat());
		gentle.show();

		wild.wear(new Glasses())
			.wear(new Hat());
		wild.show();

	}

}

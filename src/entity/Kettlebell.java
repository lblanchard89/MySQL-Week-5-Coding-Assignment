package entity;

public class Kettlebell {

		private int serial_no; 
		private String weight;
		private String color;
		
		public Kettlebell(int serial_no, String weight, String color) {
			this.setSerial_no(serial_no);
			this.setWeight(weight);
			this.setColor(color);
		}

		public int getSerial_no() {
			return serial_no;
		}

		public void setSerial_no(int serial_no) {
			this.serial_no = serial_no;
		}

		public String getWeight() {
			return weight;
		}

		public void setWeight(String weight) {
			this.weight = weight;
		}

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}
}

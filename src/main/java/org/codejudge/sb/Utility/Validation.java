package org.codejudge.sb.Utility;

public class Validation {

	public static boolean isValidPhoneNumber(long phoneNumber) {
		return String.valueOf(phoneNumber).length() == 10 ? true : false;
	}

	public static boolean isValidCoordinate(double coordinate) {
		return coordinate != 0.0d ? true : false;
	}

}

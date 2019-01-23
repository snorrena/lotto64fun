package com.rsnorrena.lotto64fun;

public class CheckNumbers {
	boolean numberscheck = true;

	// methods to return false if any of the numbers array is a duplicate, out
	// of range or null entry.

	public Boolean GetBoolean(String[] EnteredNumbersArray) {

		// Check for null entries in the edit text fields
		for (int etBlank = 0; etBlank < EnteredNumbersArray.length; etBlank++) {
			if (EnteredNumbersArray[etBlank].matches("")) {
				numberscheck = false;
				break;
			}
		}

		// Check for numbers entered between 1 and 49
		if (numberscheck) {
			for (int i = 0; i < EnteredNumbersArray.length; i++) {
				String number = EnteredNumbersArray[i];
				int a = Integer.parseInt(number);
				if (a < 1 != a > 49) {
					numberscheck = false;
					break;

				}

			}

			// Check for duplicate numbers
			if (numberscheck) {
				for (int j = 0; j < EnteredNumbersArray.length; j++) {
					int sameIndexNumber = j;
					String duplicate = EnteredNumbersArray[j];
					int k = Integer.parseInt(duplicate);
					for (int l = 0; l < EnteredNumbersArray.length; l++) {
						if (l == sameIndexNumber) {
							break;
						} else {
							String dup = EnteredNumbersArray[l];
							int m = Integer.parseInt(dup);
							if (k == m) {
								numberscheck = false;
							}
						}
					}
				}
			}

		}
		return numberscheck;

	}
	
	//method to return enterednumber array stripped of leading zeros
	public String[] GetNewEnteredNumbersArray(String[] EnteredNumbersArray) {
		for (int i = 0; i < EnteredNumbersArray.length; i++) {

			String str1 = EnteredNumbersArray[i].substring(0, 1);
			String str2 = EnteredNumbersArray[i].substring(1);
			if (Integer.valueOf(str1).intValue() == 0) {
				EnteredNumbersArray[i] = str2;
			}

		}
		return EnteredNumbersArray;
	}
}

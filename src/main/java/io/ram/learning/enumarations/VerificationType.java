package io.ram.learning.enumarations;

public enum VerificationType {
	ACCOUNT("ACCOUNT"),
	PASSOWRD("PASSWORD");
	
	private final String type;
	
	VerificationType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type.toLowerCase();
	}

}

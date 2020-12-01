package it.poker.model.user;

public enum Stato {
	CREATO,
	ATTIVO,
	DISABILITATO,
	EMPTY;
	
	@Override
	public String toString() {
		return this == EMPTY ? "" : this.name();
	}
}

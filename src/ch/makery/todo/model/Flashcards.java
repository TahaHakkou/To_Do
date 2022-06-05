package ch.makery.todo.model;

//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.property.StringProperty;

public class Flashcards {

	private String flashcardTitle;
	private String flashcardFront;
	private String flashcardBack;

	public Flashcards() {

	}

	public Flashcards(String flashcardTitle, String flashcardFront, String flashcardBack) {
		this.flashcardTitle = flashcardTitle;
		this.flashcardFront = flashcardFront;
		this.flashcardBack = flashcardBack;
	}

	public String getFlashcardTitle() {
		return flashcardTitle;
	}

	public void setFlashcardTitle(String flashcardTitle) {
		this.flashcardTitle = flashcardTitle;
	}

	// ---------------------------------------------------------------------

	public String getFlashcardFront() {
		return flashcardFront;
	}

	public void setFlashcardFront(String flashcardFront) {
		this.flashcardFront = flashcardFront;
	}

	// ---------------------------------------------------------------------

	public String getFlashcardBack() {
		return flashcardBack;
	}

	public void setFlashcardBack(String flashcardBack) {
		this.flashcardBack = flashcardBack;
	}

	// ---------------------------------------------------------------------

	@Override
	public String toString() {
		return this.flashcardTitle + " | " + this.flashcardFront + " | " + this.flashcardBack;
	}

}

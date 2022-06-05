package ch.makery.todo.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Flashcards")
public class FlashcardsModelForJAXB {
	private List<Flashcards> flashcards;

	@XmlElement(name = "Flashcard")
	public List<Flashcards> getFlashcards() {
		return flashcards;
	}

	public void setFlashcards(List<Flashcards> flashcards) {
		this.flashcards = flashcards;
	}
}

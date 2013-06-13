package numberguess;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Instance;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import qualifiers.MaxNumber;
import qualifiers.Random;

@Named
@SessionScoped
public class Game implements Serializable {

	private static final long serialVersionUID = 1L;

   private int number;
   private int guess;
   private int smallest;
   private int biggest;
   private int remainingGuesses;

   @Inject
   @MaxNumber
   private int maxNumber;

   @Inject
   @Random
   Instance<Integer> randomNumber;
   
   public Game() {
   }

   public int getNumber() {
      return number;
   }

   public int getGuess() {
      return guess;
   }

   public void setGuess(int guess) {
      this.guess = guess;
   }

   public int getSmallest() {
      return smallest;
   }

   public int getBiggest() {
      return biggest;
   }

   public int getRemainingGuesses() {
      return remainingGuesses;
   }

   /*
    * Check whether the current guess is correct, and update the biggest/smallest guesses as needed.
    * Give feedback to the user if they are correct.
    */
   public void check() {
      if (guess > number) {
         biggest = guess - 1;
      } else if (guess < number) {
         smallest = guess + 1;
      } else if (guess == number) {
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Correct!"));
      }
      remainingGuesses--;
   }

   /*
    * Reset the game, by putting all values back to their defaults, and getting a new random number.
    * We also call this method when the user starts playing for the first time using
    * @PostConstruct to set the initial values.
    */
   @PostConstruct
   public void reset() {
      this.smallest = 0;
      this.guess = 0;
      this.remainingGuesses = 10;
      this.biggest = maxNumber;
      this.number = randomNumber.get();
   }

   /*
    * A JSF validation method which checks whether the guess is valid. It might not be valid because
    * there are no guesses left, or because the guess is not in range.
    */
   public void validateNumberRange(FacesContext context, UIComponent toValidate, Object value) {
      if (remainingGuesses <= 0) {
         FacesMessage message = new FacesMessage("No guesses left!");
         context.addMessage(toValidate.getClientId(context), message);
         ((UIInput) toValidate).setValid(false);
         return;
      }
      
      int input = (Integer) value;

      if (input < smallest || input > biggest) {
         ((UIInput) toValidate).setValid(false);

         FacesMessage message = new FacesMessage("Invalid guess");
         context.addMessage(toValidate.getClientId(context), message);
      }
      
   }
   
}

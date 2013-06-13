package numberguess;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import qualifiers.MaxNumber;
import qualifiers.Random;

@ApplicationScoped
public class Generator implements Serializable {

   private static final long serialVersionUID = 1L;

   private java.util.Random random = new java.util.Random(System.currentTimeMillis());
   
   @Produces @MaxNumber
   private int maxNumber = 150;
   
   java.util.Random getRandom() {
      return random;
   }

   @Produces
   @Random
   int next() {
      return getRandom().nextInt(maxNumber - 1) + 1;
   }

//   @Produces
//   @MaxNumber
//   int getMaxNumber() {
//      return maxNumber;
//   }
}

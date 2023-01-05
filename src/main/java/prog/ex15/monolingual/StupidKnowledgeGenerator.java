package prog.ex15.monolingual;


import prog.ex15.exercise.i18ncountries.Category;
import prog.ex15.exercise.i18ncountries.CountryKnowledgeContainer;
import prog.ex15.exercise.i18ncountries.KnowledgeGenerator;

/**
 * Fills knowledge into a CountryKnowledgeContainer. I18N? Never heard.
 */
public class StupidKnowledgeGenerator implements KnowledgeGenerator {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(StupidKnowledgeGenerator.class);

  @Override
  public CountryKnowledgeContainer fillContainer() {
    CountryKnowledgeContainer container = new CountryKnowledgeContainer();
    container.addKnowledge(Category.TRAFFIC, "Maximum speed on highways is 70 mph.");
    container.addKnowledge(Category.FOOD, "Our most prominent food is Fish and Chips.");
    container.addKnowledge(Category.HOLIDAYS,
        "Our most important holiday is  Brexit Day (Joke) on January, the 1, 2022.");
    container.addKnowledge(Category.STATISTICS, "Our population is 66.500.000");
    return container;
  }
}

package prog.ex15.exercise.i18ncountries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This container holds knowledge about a country related to different categories.
 */
public class CountryKnowledgeContainer {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(CountryKnowledgeContainer.class);

  Map<Category, List<String>> categoryKnowledgeMap;

  /**
   * Creates an empty container.
   */
  public CountryKnowledgeContainer() {
    clear();
  }

  /**
   * Adds a new entry to the container.
   *
   * @param category  Category the knowledge is related to. The category must be NotNull.
   * @param knowledge Knowledge string. The string must be NotNull.
   * @throws IllegalArgumentException if either the category or the string is null
   */
  public void addKnowledge(Category category, String knowledge) throws IllegalArgumentException {
    if (category == null) {
      throw new IllegalArgumentException("category is null reference.");
    }
    if (knowledge == null) {
      throw new IllegalArgumentException("knowledge is null reference.");
    }
    List<String> knowledgeList = categoryKnowledgeMap.get(category);
    knowledgeList.add(knowledge);
  }

  /**
   * Returns a List of strings containing the knowledge, related to the given category.
   *
   * @param category selected category. The category must be NotNull.
   * @return List of strings containing the knowledge, related to the given category
   * @throws IllegalArgumentException if the category is null
   */
  public List<String> getKnowledge(Category category) throws IllegalArgumentException {
    if (category == null) {
      throw new IllegalArgumentException("category is null reference.");
    }
    return categoryKnowledgeMap.get(category);
  }

  /**
   * (Re)sets the data structures of the container.
   */
  public void clear() {
    categoryKnowledgeMap = new HashMap<>();

    for (Category category : Category.values()) {
      categoryKnowledgeMap.put(category, new ArrayList<>());
    }
  }
}

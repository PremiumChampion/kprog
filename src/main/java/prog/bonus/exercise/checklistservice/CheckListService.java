package prog.bonus.exercise.checklistservice;

import java.util.List;

/**
 * Service to manage check lists from different users.
 */
public interface CheckListService {

  /**
   * Creates a long term token for a new user.
   *
   * @return Token to identify the user
   */
  Token createUserToken();

  /**
   * Creates a new CheckList.
   *
   * @param user     Owner of the list
   * @param listName name of the list. The name must be unique within the lists of the owner. Ths
   *                 list name must contain readable characters.
   * @return id of the list
   * @throws UnknownTokenException    if the user is not a known token
   * @throws DuplicateNameException   if the name of the new list has already been given to another
   *                                  list of the same owner.
   * @throws IllegalArgumentException if any parameter is null or not a valid list name.
   */
  long createList(Token user, String listName) throws UnknownTokenException,
      DuplicateNameException, IllegalArgumentException;

  /**
   * Returns a list of descriptors of the lists of the user.
   *
   * @param user the owner of the lists
   * @return list of descriptors of the lists of the user. The list must be immutable.
   * @throws IllegalArgumentException if the user is null
   * @throws UnknownTokenException    if the user is not a known token
   */
  List<ListDescriptor> getLists(Token user) throws IllegalArgumentException,
      UnknownTokenException;

  /**
   * Returns a descriptor of the list with the given id.
   *
   * @param user   the owner of the list
   * @param listId the id of the list
   * @return the descriptor of the list
   * @throws UnknownTokenException  if the user is not a known token
   * @throws NotAuthorizedException if the user is not the owner of the list
   */
  ListDescriptor getList(Token user, long listId) throws UnknownTokenException,
      NotAuthorizedException;

  /**
   * Returns entries of a list.
   *
   * @param user   user requesting the list
   * @param listId id of the list
   * @return List of entries. The list must be immutable.
   * @throws UnknownTokenException  if the token is not known
   * @throws NotAuthorizedException if the user is not authorized to access the list
   */
  List<Entry> getEntries(Token user, long listId) throws UnknownTokenException,
      NotAuthorizedException;

  /**
   * Renames a list.
   *
   * @param user    user requesting the renaming
   * @param listId  id of the list
   * @param newName new name of the list
   * @throws UnknownTokenException    if the token is not known
   * @throws IllegalArgumentException if the listId is not a valid id
   * @throws NotAuthorizedException   if the user is not authorized to access the list
   * @throws DuplicateNameException   if the new name is already used by another list of the user
   */
  void renameList(Token user, long listId, String newName) throws UnknownTokenException,
      IllegalArgumentException, DuplicateNameException, NotAuthorizedException;

  /**
   * Deletes a list.
   *
   * @param user   user requesting the deletion
   * @param listId id of the list
   * @throws UnknownTokenException    if the token is not known
   * @throws IllegalArgumentException if the listId is not a valid id
   * @throws NotAuthorizedException   if the user is not authorized to access the list
   */
  void deleteList(Token user, long listId) throws UnknownTokenException, IllegalArgumentException,
      NotAuthorizedException;

  /**
   * Creates a new entry.
   *
   * @param user  user requesting the creation
   * @param list  id of the list
   * @param entry content of the entry
   * @return id of the new entry
   * @throws UnknownTokenException    if the token is not known
   * @throws IllegalArgumentException if the listId is not a valid id
   * @throws NotAuthorizedException   if the user is not authorized to access the list
   */
  long createEntry(Token user, long list, String entry) throws UnknownTokenException,
      IllegalArgumentException, NotAuthorizedException;

  /**
   * Updates an entry.
   *
   * @param user    user requesting the update
   * @param listId  id of the list
   * @param entryId id of the entry
   * @param content new content of the entry
   * @throws UnknownTokenException    if the token is not known
   * @throws IllegalArgumentException if the listId or entryId is not a valid id
   * @throws NotAuthorizedException   if the user is not authorized to access the list
   */
  void updateEntry(Token user, long listId, long entryId, String content)
      throws UnknownTokenException, IllegalArgumentException, NotAuthorizedException;

  /**
   * Updates an entry.
   *
   * @param user    user requesting the update
   * @param listId  id of the list
   * @param entryId id of the entry
   * @param checked new checked state of the entry
   * @throws UnknownTokenException    if the token is not known
   * @throws IllegalArgumentException if the listId or entryId is not a valid id
   */
  void updateEntry(Token user, long listId, long entryId, boolean checked)
      throws UnknownTokenException, IllegalArgumentException, NotAuthorizedException;

  /**
   * Deletes an entry.
   *
   * @param user    user requesting the deletion
   * @param listId  id of the list
   * @param entryId id of the entry
   * @throws UnknownTokenException    if the token is not known
   * @throws IllegalArgumentException if the listId or entryId is not a valid id
   * @throws NotAuthorizedException   if the user is not authorized to access the list
   */
  void deleteEntry(Token user, long listId, long entryId) throws UnknownTokenException,
      IllegalArgumentException, NotAuthorizedException;

  /**
   * Gets all tags associated with the user.
   *
   * @param user user requesting the tags
   * @return list of tags. the list must be immutable
   * @throws UnknownTokenException if the token is not known
   */
  List<Tag> getTags(Token user) throws UnknownTokenException;

  /**
   * Adds a tag.
   *
   * @param user    user requesting the addition
   * @param tagName name of the tag. It must consist of readable characters.
   * @return id of the tag
   * @throws DuplicateNameException   if the tag name already exists
   * @throws UnknownTokenException    if the token is not known
   * @throws IllegalArgumentException if the tag name is not a valid tag name
   */
  long createTag(Token user, String tagName) throws DuplicateNameException, UnknownTokenException,
      IllegalArgumentException;

  /**
   * Updates a tag name.
   *
   * @param user       user requesting the update
   * @param tagId      id of the tag
   * @param newTagName new name of the tag. It must consist of readable characters.
   * @throws UnknownTokenException    if the token is not known
   * @throws IllegalArgumentException if the tag name is not a valid tag name
   * @throws DuplicateNameException   if the tag name already exists
   */
  void updateTag(Token user, long tagId, String newTagName) throws UnknownTokenException,
      IllegalArgumentException, DuplicateNameException;

  /**
   * Deletes a tag.
   *
   * @param user  user requesting the deletion
   * @param tagId id of the tag
   * @throws UnknownTokenException    if the token is not known
   * @throws IllegalArgumentException if the tagId is not a valid id
   * @throws TagInUseException        if the tag is still in use
   */
  void deleteTag(Token user, long tagId) throws UnknownTokenException, IllegalArgumentException,
      TagInUseException;

  /**
   * Assigns a tag to an entry.
   *
   * @param user    user requesting the assignment
   * @param listId  id of the list
   * @param entryId id of the entry
   * @param tagId   id of the tag
   * @throws UnknownTokenException    if the token is not known
   * @throws IllegalArgumentException if the listId or entryId or tagId is not a valid id
   * @throws NotAuthorizedException   if the user is not authorized to access the list
   */
  void addTagToEntry(Token user, long listId, long entryId, long tagId)
      throws UnknownTokenException, IllegalArgumentException, NotAuthorizedException;

  /**
   * Removes a tag from an entry.
   *
   * @param user    user requesting the removal
   * @param listId  id of the list
   * @param entryId id of the entry
   * @param tagId   id of the tag
   * @throws UnknownTokenException    if the token is not known
   * @throws IllegalArgumentException if the listId or entryId or tagId is not a valid id
   * @throws NotAuthorizedException   if the user is not authorized to access the list
   */
  void removeTagFromEntry(Token user, long listId, long entryId, long tagId)
      throws UnknownTokenException, IllegalArgumentException, NotAuthorizedException;
}

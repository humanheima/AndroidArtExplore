// BookManager.aidl
package com.hm.aidlclient;

// Declare any non-default types here with import statements
import com.hm.core.model.Book;
interface BookManager {
List<Book> getBookList();
void addBook(in Book book);
}

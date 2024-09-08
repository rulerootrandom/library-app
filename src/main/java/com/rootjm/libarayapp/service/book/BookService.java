package com.rootjm.libarayapp.service.book;


import com.rootjm.libarayapp.domain.book.BookRepository;
import com.rootjm.libarayapp.domain.user.User;
import com.rootjm.libarayapp.domain.user.UserRepository;
import com.rootjm.libarayapp.domain.user.loanhistory.UserLoanHistory;
import com.rootjm.libarayapp.domain.user.loanhistory.UserLoanHistoryRepository;
import com.rootjm.libarayapp.dto.book.request.BookCreateRequest;
import com.rootjm.libarayapp.dto.book.request.BookLoanRequest;
import com.rootjm.libarayapp.dto.book.request.BookReturnRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.rootjm.libarayapp.domain.book.Book;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository, UserLoanHistoryRepository userLoanHistoryRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveBook(BookCreateRequest request) {
        bookRepository.save(new Book(request.getName()));
    }

    @Transactional
    public void loanBook(BookLoanRequest request) {

        Book book = bookRepository.findByName(request.getBookName())
        .orElseThrow(IllegalArgumentException::new);

        if(userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false))
        {
            throw new IllegalArgumentException("Book already loaned");
        }

        User user =userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);

        user.loanBook(book.getName());

      //  userLoanHistoryRepository.save(new UserLoanHistory(user, book.getName()));
    }

    @Transactional
    public void returnBook(BookReturnRequest request) {

        User user =userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);

        user.returnBook(request.getBookName());
        /*
        UserLoanHistory history = userLoanHistoryRepository.findByUserIdAndBookName(user.getId(), request.getBookName())
                .orElseThrow(IllegalArgumentException::new);

        history.doReturn();
        */
     //   userLoanHistoryRepository.save(history);
    }
}

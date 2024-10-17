package library.management.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private boolean isIssued;
    private Member issuedTo;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isIssued = false;
        this.issuedTo = null;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public Member getIssuedTo() {
        return issuedTo;
    }

    public void issueBook(Member member) {
        this.isIssued = true;
        this.issuedTo = member;
    }

    public void returnBook() {
        this.isIssued = false;
        this.issuedTo = null;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isIssued=" + isIssued +
                ", issuedTo=" + (issuedTo != null ? issuedTo.getName() : "None") +
                '}';
    }
}

class Member {
    private String name;
    private int memberId;

    public Member(String name, int memberId) {
        this.name = name;
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public int getMemberId() {
        return memberId;
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", memberId=" + memberId +
                '}';
    }
}

class Library {
    private List<Book> books;
    private List<Member> members;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    public void addBook(String title, String author) {
        books.add(new Book(title, author));
        System.out.println("Added new book: " + title);
    }

    public void registerMember(String name, int memberId) {
        members.add(new Member(name, memberId));
        System.out.println("Registered new member: " + name);
    }

    public void issueBook(String title, int memberId) {
        Book bookToIssue = null;
        Member member = null;

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && !book.isIssued()) {
                bookToIssue = book;
                break;
            }
        }

        for (Member m : members) {
            if (m.getMemberId() == memberId) {
                member = m;
                break;
            }
        }

        if (bookToIssue != null && member != null) {
            bookToIssue.issueBook(member);
            System.out.println("Issued " + title + " to " + member.getName());
        } else {
            System.out.println("Cannot issue book. It may be already issued or member not found.");
        }
    }

    public void returnBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && book.isIssued()) {
                book.returnBook();
                System.out.println("Returned book: " + title);
                return;
            }
        }
        System.out.println("Cannot return book. It may not be issued.");
    }

    public void showIssuedBooks() {
        for (Book book : books) {
            if (book.isIssued()) {
                System.out.println(book);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Register Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Show Issued Books");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    library.addBook(title, author);
                    break;
                case 2:
                    System.out.print("Enter member name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter member ID: ");
                    int memberId = scanner.nextInt();
                    scanner.nextLine();  // consume the newline character
                    library.registerMember(name, memberId);
                    break;
                case 3:
                    System.out.print("Enter book title: ");
                    String bookTitle = scanner.nextLine();
                    System.out.print("Enter member ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();  // consume the newline character
                    library.issueBook(bookTitle, id);
                    break;
                case 4:
                    System.out.print("Enter book title: ");
                    String returnTitle = scanner.nextLine();
                    library.returnBook(returnTitle);
                    break;
                case 5:
                    library.showIssuedBooks();
                    break;
                case 6:
                    exit = true;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}

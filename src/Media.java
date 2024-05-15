//package Library;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

interface LibraryMember {
    void displayInfo();
}

class Media {
    private String title;
    private int year;

    public Media(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return title + " (" + year + ")";
    }
}

class Book extends Media {
    private String author;

    public Book(String title, int year, String author) {
        super(title, year);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return super.toString() + " by " + author;
    }
}

class User implements LibraryMember {
    private String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public void displayInfo() {
        System.out.println("User: " + username);
    }
}

class Member extends User {
    private int memberId;

    public Member(String username, int memberId) {
        super(username);
        this.memberId = memberId;
    }

    public int getMemberId() {
        return memberId;
    }

    @Override
    public void displayInfo() {
        System.out.println("Member: " + getUsername() + ", Member ID: " + memberId);
    }
}

class Loan {
    private User user;
    private Media media;
    private Date dueDate;

    public Loan(User user, Media media, Date dueDate) {
        this.user = user;
        this.media = media;
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return user.getUsername() + " borrowed " + media + " due on " + dateFormat.format(dueDate);
    }

    public Object getUser() {
        // TODO Auto-generated method stub
        return null;
    }

    public Object getMedia() {
        // TODO Auto-generated method stub
        return null;
    }
}

class Library {
    private List<Media> mediaList;
    private List<User> userList;
    private List<Loan> loanList;

    public Library() {
        this.mediaList = new ArrayList<>();
        this.userList = new ArrayList<>();
        this.loanList = new ArrayList<>();
    }

    public void addMedia(Media media) {
        mediaList.add(media);
    }
    public void addMedia(List<Media> medias)
    {
        this.mediaList.addAll(medias);
    }

    public void removeMedia(Media media) {
        mediaList.remove(media);
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public boolean checkOutMedia(User user, Media media, int loanDays) {
        if (mediaList.contains(media)) {
            Date dueDate = calculateDueDate(loanDays);
            loanList.add(new Loan(user, media, dueDate));
            mediaList.remove(media);
            return true;
        }
        return false;
    }

    public boolean returnMedia(User user, Media media) {
        for (Loan loan : loanList) {
            if (loan.getUser().equals(user) && loan.getMedia().equals(media)) {
                mediaList.add(media);
                loanList.remove(loan);
                return true;
            }
        }
        return false;
    }

    public void displayAvailableMedia() {
        System.out.println("Available Media:");
        for (Media media : mediaList) {
            System.out.println(media);
        }
    }

    public void displayLoans() {
        System.out.println("Current Loans:");
        for (Loan loan : loanList) {
            System.out.println(loan);
        }
    }

    private Date calculateDueDate(int loanDays) {
        long currentTimeMillis = System.currentTimeMillis();
        return new Date(currentTimeMillis + loanDays * 24 * 60 * 60 * 1000);
    }

    public List<Media> getMediaList() {
        return mediaList;
    }

    public List<Loan> getLoanList() {
        return loanList;
    }
}

